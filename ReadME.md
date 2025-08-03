# File-Search-Engine-Service
PDF Search Engine API
A Spring Boot-based REST API service to search for a keyword in PDF files located in a configured directory. The service scans all PDF files in the directory and returns the list of file names containing the keyword.

Features
Read PDF files from a configurable directory path.

Search for a keyword across all PDF files using Apache PDFBox for text extraction.

Expose a simple REST API endpoint to perform keyword search.

Return the matched PDF filenames in the API response.

Easy configuration with application.properties.

Prerequisites
Java 17+ (or your Spring Boot-supported Java version)

Maven

Apache PDFBox (included as dependency)

Spring Boot 3.x or 2.x

Setup and Run
1. Clone the repository (or create your Spring Boot app)
   bash
   git clone <your-repo-url>
   cd pdf-search-engine
2. Configure directory path in src/main/resources/application.properties
   text
# Directory containing PDF files to be searched
file.input-dir=/absolute/path/to/your/pdf/files
Make sure the path exists and contains PDFs.

3. Build the project
   bash
   mvn clean install
4. Run the Spring Boot application
   bash
   mvn spring-boot:run
   Your application will start, by default, on http://localhost:8080

API Usage
Search PDFs by keyword
Endpoint: GET /api/search-pdfs

Query Parameter: word (string) – The keyword to search for within PDF files.

Example Request
text
GET http://localhost:8080/api/search-pdfs?word=example
Response
A JSON array containing file names of PDFs that include the search word (case-insensitive):

json
[
"document1.pdf",
"report2023.pdf",
"presentation.pdf"
]
If no match is found, an empty array [] will be returned.

If there is an error (e.g., invalid directory), an array with the error message string will be returned.

Implementation Details
PDF Text Extraction: Uses Apache PDFBox's Loader.loadPDF(...) (for PDFBox 3.x) or PDDocument.load(...) method (PDFBox 2.x) to open and extract text from PDFs.

Directory Scanning: Reads all .pdf files from the configured directory.

Search Logic: Performs case-insensitive search for the keyword text inside the extracted content.

Framework: Built using Spring Boot with REST controller.

Project Structure
PdfSearchController – REST controller exposing the /api/search-pdfs endpoint.

PdfSearchService – Service class handling PDF reading, text extraction, and keyword matching.

application.properties – Configuration file for setting the input directory.

Dependencies (Maven)
xml
<dependencies>
<!-- Spring Boot Web Starter -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>

    <!-- Apache PDFBox -->
    <dependency>
        <groupId>org.apache.pdfbox</groupId>
        <artifactId>pdfbox</artifactId>
        <version>3.0.0</version> <!-- or 2.x depending on your choice -->
    </dependency>
</dependencies>
Extending the Project
Add support for searching multiple keywords or regex.

Return excerpts/snippets where keywords were matched.

Support .docx or other document formats using Apache POI.

Add pagination for result sets.

Integrate full-text search with Elasticsearch or Apache Lucene for scalable and faster search.

Troubleshooting
PDDocument.load() no method error?
Use Loader.loadPDF() if using PDFBox 3.x.

Ensure the configured directory path in file.input-dir exists and has readable PDF files.

Check application logs for detailed error info.

License

| License | URL |
|----------|----------|
| Apache-2.0   | https://www.apache.org/licenses/LICENSE-2.0.txt  |

	
	

If you want me to generate a ready-to-use .md file or snippet for your repo, just ask!

