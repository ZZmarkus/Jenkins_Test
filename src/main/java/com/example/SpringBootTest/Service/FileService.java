package com.example.SpringBootTest.Service;

import com.example.SpringBootTest.DTO.CropImageDTO;
import com.example.SpringBootTest.DTO.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<ImageDTO> uploadImageFiles(MultipartFile[] uploadFiles);
    int makeLmdbFiles() throws IOException, InterruptedException;
    int makeCraftImage() throws IOException, InterruptedException;
    int cropImage() throws IOException, InterruptedException;
    int predictImage() throws IOException, InterruptedException;
}
