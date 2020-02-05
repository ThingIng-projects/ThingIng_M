package com.thinging.project.controller;

import com.thinging.project.dto.FileResponse;
import com.thinging.project.service.sys.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileServerController {

    private StorageService storageService;

    public FileServerController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/info")
    public String listAllFiles(List<String> listFiles) {

        return "files";
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/upload-file")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }

    @PostMapping("/upload-multiple-files")
    public List<FileResponse> uploadMultipleFiles(@RequestParam List<MultipartFile> files) {
        System.out.println(files);
        return files.stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
}
