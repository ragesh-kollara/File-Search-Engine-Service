package com.search.file.engine.controller;

import com.search.file.engine.service.PDFProcessor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FileSearchController {

    private final PDFProcessor pdfProcessor;

    @GetMapping("/search-pdfs")
    public List<String> searchWordInFiles(@RequestParam String word) {
        return pdfProcessor.searchWordInPdfs(word);
    }
}
