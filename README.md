# GroupDocs Viewer – Render EML with Attachments Showcase

## Overview
This repository contains a **runnable Maven Java showcase** that demonstrates how to use **GroupDocs Viewer 25.12** to load an **EML (email) file**, extract a specific attachment stream, and render that attachment to HTML.

The example reads an input EML file from `resources/input/` and writes the rendered HTML pages to `resources/output/`.

## Prerequisites
- **Java Development Kit (JDK) 8** (or newer, but the project targets Java 8)
- **Apache Maven** (3.5+ recommended)
- Internet connection for Maven to download the GroupDocs Viewer dependency

## License
GroupDocs Viewer requires a license file to run without evaluation limitations.

- **Obtain a temporary license** (free 30‑day trial) by visiting: https://purchase.groupdocs.com/temporary-license/
- **Place the license file** (e.g., `GroupDocs.Viewer.Java.lic`) in the **project root directory** (the same folder that contains `pom.xml`).
- **Without a license** the library works in evaluation mode which adds watermarks and may limit some features.

## Project Structure
```
project-root/
├─ pom.xml
├─ GroupDocs.Viewer.Java.lic          # <-- place your license file here (optional)
├─ src/
│   └─ main/java/com/groupdocs/viewer/examples/RenderEmlWithAttachmentsExample.java
├─ resources/
│   ├─ input/
│   │   └─ sample.eml            # Sample EML file used by the demo
│   └─ output/                    # Rendered HTML pages will appear here
└─ README.md
```

*Replace `sample.eml` with any other EML file you wish to test.*

## Build and Run
1. **Clone the repository** (or download the source code):
   ```bash
   git clone https://github.com/your-repo/groupdocs-viewer-java-render-eml-with-attachments.git
   cd groupdocs-viewer-java-render-eml-with-attachments
   ```
2. **Copy your license file** (`GroupDocs.Viewer.Java.lic`) into the project root if you have one.
3. **Verify the sample input** – the folder `resources/input/` already contains `sample.eml`. Feel free to replace it with your own email file.
4. **Build the project**:
   ```bash
   mvn clean compile
   ```
5. **Run the example** (using Maven Exec plugin):
   ```bash
   mvn exec:java
   ```


## Expected Result
- The console will indicate whether the license was loaded and whether the rendering succeeded.
- In `resources/output/` you will find one or more HTML files named `page_*.html` representing the rendered attachment content.

## Notes & Tips
- The example uses **relative paths** so it works regardless of where the project directory is located on your machine.
- Ensure the `resources/output/` directory is writable; the program will create it automatically if it does not exist.
- If you need to render the email body itself or enumerate real attachments, extend the example to list attachments from the EML and render each one.

---
*This is a showcase project intended to help developers quickly understand how to integrate GroupDocs Viewer for rendering EML files with attachments.*
