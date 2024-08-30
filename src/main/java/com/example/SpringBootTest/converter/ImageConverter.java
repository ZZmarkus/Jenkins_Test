package com.example.SpringBootTest.converter;

import com.example.SpringBootTest.DTO.CropImageDTO;
import com.example.SpringBootTest.entity.ImageResult;

public class ImageConverter {

    public static CropImageDTO imgEntityToDto(ImageResult imageResult){
        return CropImageDTO.builder()
                .imgName(imageResult.getImgName())
                .prediction(imageResult.getPrediction())
                .imgPath(imageResult.getImgPath())
                .build();
    }

    public static ImageResult dtoToEntity(CropImageDTO imageDTO){
        return ImageResult.builder()
                .imgName(imageDTO.getImgName())
                .prediction(imageDTO.getPrediction())
                .imgPath(imageDTO.getImgPath())
                .build();
    }
}
