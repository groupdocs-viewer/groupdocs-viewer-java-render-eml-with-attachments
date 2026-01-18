package com.groupdocs.viewer.examples;

import com.groupdocs.viewer.License;
import com.groupdocs.viewer.Viewer;
import com.groupdocs.viewer.caching.extra.CacheableFactory;
import com.groupdocs.viewer.options.HtmlViewOptions;
import com.groupdocs.viewer.results.Attachment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demonstrates how to render an EML (email) file to HTML while extracting and saving its attachments.
 * <p>
 * The example expects an input EML file located at {@code resources/input/sample.eml} and will write the
 * rendered HTML pages and any extracted attachments to {@code resources/output/}.
 * </p>
 */
public class RenderEmlWithAttachmentsExample {

    private static final String INPUT_EML_PATH = "resources/input/sample.eml";
    private static final String OUTPUT_DIR = "resources/output/";
    private static final String LICENSE_FILE = "GroupDocs.Viewer.Java.lic";

    /**
     * Loads the GroupDocs Viewer license if the license file exists.
     * <p>
     * To obtain a temporary 30‑day license visit:
     * https://purchase.groupdocs.com/temporary-license/
     * </p>
     * <p>
     * Place the license file (e.g., {@code GroupDocs.Viewer.lic}) in the project root directory.
     * </p>
     * <p>
     * Without a license the library works in evaluation mode with watermarks and functional limitations.
     * </p>
     *
     * @param licensePath path to the license file relative to the project root
     */
    private static void loadLicense(String licensePath) {
        File licenseFile = new File(licensePath);
        if (licenseFile.exists()) {
            try {
                License license = new License();
                license.setLicense(licenseFile.getAbsolutePath());
                System.out.println("GroupDocs Viewer license loaded successfully.");
            } catch (Exception e) {
                System.err.println("Failed to load GroupDocs Viewer license: " + e.getMessage());
            }
        } else {
            System.out.println("License file not found. Running in evaluation mode.");
        }
    }

    /**
     * Renders the EML file to HTML and saves any embedded attachments.
     * <p>
     * The rendered HTML files are stored in {@code resources/output/}. Attachments are saved in a subfolder
     * called {@code attachments} inside the output directory.
     * </p>
     *
     * @throws IOException if file operations fail.
     */
    public static void renderEmlWithAttachments() throws IOException  {
        // Ensure output directory exists
        Path outputDir = Paths.get("resources", "output");
        // Ensure output directory exists
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
        }
        Path pageFilePathFormat = outputDir.resolve("page_{0}.html");

        File inputFile = new File(INPUT_EML_PATH);
        if (!inputFile.exists()) {
            System.err.println("Input EML file not found: " + INPUT_EML_PATH);
            return;
        }

        Attachment attachment = CacheableFactory.getInstance().newAttachment("attachment-word.doc", pageFilePathFormat.toString());
        try (ByteArrayOutputStream attachmentStream = new ByteArrayOutputStream();
             Viewer viewer = new Viewer(inputFile.getAbsolutePath())) {

            // Save the current attachment to the output stream.
            viewer.saveAttachment(attachment, attachmentStream);

            // Render the attachment stream as HTML pages with embedded resources.
            try (InputStream inputStream = new ByteArrayInputStream(attachmentStream.toByteArray());
                 Viewer attachmentViewer = new Viewer(inputStream)) {
                HtmlViewOptions options = HtmlViewOptions.forEmbeddedResources(pageFilePathFormat);

                attachmentViewer.view(options);
            }

            System.out.println("EML rendered successfully. Output saved to: " + OUTPUT_DIR);
        } catch (Exception e) {
            System.err.println("Error during rendering: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main entry point.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) throws IOException {
        // Load license if present
        loadLicense(LICENSE_FILE);

        // Perform the rendering demonstration
        renderEmlWithAttachments();
    }
}
