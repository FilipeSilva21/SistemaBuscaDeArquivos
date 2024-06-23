package com.example.downloading_files;

import com.example.downloading_files.Storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class FileDownloader {

    private final StorageService storageService;

    @Autowired
    public FileDownloader(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/download/perfil={perfil}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("perfil") String perfil) {
        String description = storageService.getDescription(perfil);

        File file = new File(perfil + ".txt");
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(description);



            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(file.length())
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(500).build();
    }
}

