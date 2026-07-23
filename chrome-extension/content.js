/**
 * IQP DOM Scanner with Dynamic Wait & MutationObserver
 */

let scanTimeout = null;
let cachedDomSchema = null;

function captureDomSnapshot() {
    console.log("⚡ [DOM Scanner] DOM stabilized. Capturing snapshot...");
    const elements = Array.from(document.querySelectorAll('input, textarea, select'));

    cachedDomSchema = elements.map(el => {
        if (el.type === 'hidden' || el.offsetWidth === 0 || el.offsetHeight === 0) {
            return null; // Ignore invisible or hidden fields
        }

        let labelText = '';
        if (el.id) {
            const labelEl = document.querySelector(`label[for="${el.id}"]`);
            if (labelEl) labelText = labelEl.innerText;
        }
        if (!labelText) {
            labelText = el.placeholder || el.getAttribute('aria-label') || el.name || el.id || '';
        }

        return {
            elementId: el.id || el.name || '',
            tagName: el.tagName.toLowerCase(),
            type: el.tagName.toLowerCase() === 'textarea' ? 'textarea' : (el.type || 'text'),
            label: labelText.trim(),
            maxLength: el.getAttribute('maxlength') ? parseInt(el.getAttribute('maxlength')) : null,
            required: el.hasAttribute('required')
        };
    }).filter(item => item !== null);

    window.__DOM_SCANNER_SCHEMA__ = cachedDomSchema;
    console.log(`✅ [DOM Scanner] Cached ${cachedDomSchema.length} fields in local memory:`, cachedDomSchema);
}

// Observe DOM mutations for slow-loading / dynamically rendered SPAs
const observer = new MutationObserver(() => {
    // Debounce scan: Reset timer every time a new DOM element renders
    clearTimeout(scanTimeout);
    scanTimeout = setTimeout(captureDomSnapshot, 500); // Waits 500ms after last DOM change
});

// Start watching the document body for changes
observer.observe(document.body, {
    childList: true,
    subtree: true
});