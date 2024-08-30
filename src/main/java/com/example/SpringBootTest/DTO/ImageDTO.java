package com.example.SpringBootTest.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageDTO {
    private Long id;
    private String imageName;
    private String path;

    public String GetImageURL() {
        return path + "/" + imageName;
    }
}
