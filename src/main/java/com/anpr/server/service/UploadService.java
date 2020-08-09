//package com.anpr.server.service;
//
//import com.anpr.server.exception.FileStorageException;
//import com.azure.storage.blob.BlobClient;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobServiceClient;
//import com.azure.storage.blob.BlobServiceClientBuilder;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Service
//@Slf4j
//public class UploadService {
//
//    private String key = "dbKMfiEwR4THi4k/uPnBjJCz/JYqu0iiNQcNNg36R0vTksSqkhYDr7GUJ0s5N3+3CfrhkcByggJG/JEB3I/Wfg==";
//    private String connectionString  = "DefaultEndpointsProtocol=https;AccountName=anprimages;AccountKey=dbKMfiEwR4THi4k/uPnBjJCz/JYqu0iiNQcNNg36R0vTksSqkhYDr7GUJ0s5N3+3CfrhkcByggJG/JEB3I/Wfg==;EndpointSuffix=core.windows.net";
//    private String container = "vehicle";
//
//    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
//
//    public void write(MultipartFile file, Path dir) {
//
//        Path filepath = Paths.get(dir.toString(), file.getOriginalFilename());
//
//        try {
//            try (OutputStream os = Files.newOutputStream(filepath)) {
//                os.write(file.getBytes());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new FileStorageException("Could not store file " +
//                    file.getOriginalFilename() + ". Please try again!");
//        }
//    }
//
//    public String uploadImage(MultipartFile file){
//
//        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
//                .connectionString(connectionString).buildClient();
//
//        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container);
//
//        String localPath = "/home/puneet/IdeaProjects/licenseplatedetector/src/main/java/com/anpr/server/fileupload/sample.txt";
//
//        InputStream inputStream;
//
//        try {
//            inputStream = file.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new FileStorageException("Could not store file " +
//                    file.getOriginalFilename() + ". Please try again!");
//        }
//
//
//        BlobClient blobClient = containerClient.getBlobClient("sample");
//        blobClient.upload(inputStream,file.length());
//
//        return "done";
//
//    }
//
//
//}