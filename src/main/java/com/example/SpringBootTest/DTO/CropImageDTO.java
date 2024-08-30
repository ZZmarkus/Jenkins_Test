package com.example.SpringBootTest.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CropImageDTO {
    private String imgName;
    private String prediction;
    private String imgPath;
}
