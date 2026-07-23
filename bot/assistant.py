import os
import json
import re
from dotenv import load_dotenv
from google import genai
from google.genai import types

# 1. Load .env file from the root ADV folder (one directory up from bot/)
load_dotenv(dotenv_path=os.path.join(os.path.dirname(__file__), '..', '.env'))

api_key = os.getenv("api_key")
if not api_key:
    raise ValueError("❌ GEMINI_API_KEY missing! Add it to your .env file.")

client = genai.Client(api_key=api_key)

def clean_json_string(text: str) -> str:
    """Removes markdown code blocks and trailing commas that break json.loads."""
    # Remove markdown code blocks if present
    text = re.sub(r'```json\s*', '', text)
    text = re.sub(r'```\s*$', '', text)
    # Remove trailing commas before closing brackets or braces
    text = re.sub(r',\s*([\]}])', r'\1', text)
    return text.strip()

def generate_test_data(instruction: str, output_filename="test_data.json"):
    print(f"🤖 Analyzing instruction: '{instruction}'...")

    system_prompt = """
    You are an expert QA Automation Engineer.
    Generate a boundary and edge-case dataset based on instructions.
    
    STRICT JSON RULES:
    1. Output MUST be valid JSON raw array of objects.
    2. Escape all special characters inside string values properly (e.g. quotes, newlines, backslashes).
    3. Do NOT include any markdown, commentary, or text outside the JSON array.
    4. Ensure every JSON property and item is properly comma-separated.

    Required Object Keys:
      - "inputValue": String
      - "shouldFail": Boolean
      - "description": String
    """

    try:
        response = client.models.generate_content(
            model='gemini-3.5-flash',
            contents=instruction,
            config=types.GenerateContentConfig(
                system_instruction=system_prompt,
                response_mime_type="application/json",
                temperature=0.1  # Low temperature prevents syntax drift
            )
        )

        raw_text = clean_json_string(response.text)
        data = json.loads(raw_text)

        if isinstance(data, dict):
            data = data.get("testCases") or data.get("test_cases") or [data]

        # Save output directly inside ADV/bot directory
        output_path = os.path.abspath(os.path.join(os.path.dirname(__file__), output_filename))
        
        with open(output_path, "w") as f:
            json.dump(data, f, indent=2)

        print(f"✅ Success! Generated {len(data)} test scenarios.")
        print(f"📁 Saved to: {output_path}")

    except json.JSONDecodeError as e:
        print(f"❌ JSON Syntax Error in model output: {e}")
        print("💡 Lowering temperature and reinforcing prompt rules resolves this.")
    except Exception as e:
        print(f"❌ Error generating test data: {e}")

if __name__ == "__main__":
    test_instruction = (
        "Test the Item Description textarea field. "
        "It has a maximum length of 30 characters. "
        "Include a valid input, an exact 30 char boundary, a 31 char overflow, "
        "an empty string, and a basic XSS script injection attempt."
    )
    
    generate_test_data(test_instruction)