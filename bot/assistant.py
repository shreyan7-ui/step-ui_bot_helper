import os
import json
import re
from dotenv import load_dotenv

try:
    from openai import AzureOpenAI
except ImportError as exc:
    raise ImportError("openai package is required. Install with: pip install openai") from exc

# 1. Load .env file from the root ADV folder (one directory up from bot/)
load_dotenv(dotenv_path=os.path.join(os.path.dirname(__file__), '..', '.env'))

api_key = os.getenv("AZURE_OPENAI_API_KEY")
azure_endpoint = os.getenv("AZURE_OPENAI_ENDPOINT", "https://apim-adusa-coregenai-prde201.azure-api.net")
api_version = os.getenv("OPENAI_API_VERSION", "2024-06-01")
deployment_name = os.getenv("AZURE_DEPLOYMENT_NAME", "gpt-4o")

if not api_key:
    raise ValueError("AZURE_OPENAI_API_KEY missing! Add it to your .env file.")

client = AzureOpenAI(
    api_key=api_key,
    azure_endpoint=azure_endpoint,
    api_version=api_version,
)

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
        response = client.chat.completions.create(
            model=deployment_name,
            messages=[
                {"role": "system", "content": system_prompt},
                {"role": "user", "content": instruction}
            ],
            temperature=0.1
        )

        content = response.choices[0].message.content if response.choices else ""
        if not content:
            raise ValueError("Empty response from Azure OpenAI")

        raw_text = clean_json_string(content)
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