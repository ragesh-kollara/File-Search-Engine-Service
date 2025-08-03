package com.search.file.engine.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 */
@Service
public class PDFProcessor {

    @Value("${file.search.path}")
    private String pathDir;

    public List<String> searchWordInPdfs(String word) {
        List<String> matches = new ArrayList<>();
        Path dirPath = Paths.get(pathDir);

        if (Files.notExists(dirPath) || !Files.isDirectory(dirPath)) {
            matches.add("Directory does not exist or is not a directory");
            return Collections.emptyList();
        }

        try (Stream<Path> files = Files.list(dirPath)) {
            files.filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".pdf"))
                    .forEach(path -> {
                        try (PDDocument document = Loader.loadPDF(path.toFile())) {
                            String text = new PDFTextStripper().getText(document);
                            if (text.toLowerCase().contains(word.toLowerCase())) {
                                matches.add(path.getFileName().toString());
                            }
                        } catch (IOException e) {
                            matches.add("Failed to read: " + path.getFileName());
                        }
                    });
        } catch (IOException e) {
            matches.add("Error processing directory: " + e.getMessage());
        }
        return matches;
    }
}
