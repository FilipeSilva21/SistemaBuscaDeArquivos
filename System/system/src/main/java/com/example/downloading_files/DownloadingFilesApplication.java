package com.example.downloading_files;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static org.springframework.boot.SpringApplication.run;



@SpringBootApplication
@EnableConfigurationProperties(com.example.downloading_files.Storage.StorageProperties.class)
public class DownloadingFilesApplication {
    public static void main(String[] args) { run(DownloadingFilesApplication.class, args);
    }

    @Bean
    CommandLineRunner init(com.example.downloading_files.Storage.StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}

