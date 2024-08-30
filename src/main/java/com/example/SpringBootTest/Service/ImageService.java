package com.example.SpringBootTest.Service;

import com.example.SpringBootTest.DTO.CropImageDTO;
import com.example.SpringBootTest.DTO.ImageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    int uploadFiles(ImageDTO imageDTO, MultipartFile[] uploadFiles) throws IOException, InterruptedException;
    int makeLmdbFiles() throws IOException, InterruptedException;
    int makeCraftImage() throws IOException, InterruptedException;
    ResponseEntity<byte[]> loadCraftImage() throws IOException;
    int cropImage() throws IOException, InterruptedException;
    List<CropImageDTO> resultImage() throws IOException, InterruptedException;
}
