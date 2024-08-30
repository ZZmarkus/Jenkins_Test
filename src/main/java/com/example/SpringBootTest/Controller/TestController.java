package com.example.SpringBootTest.Controller;

import com.example.SpringBootTest.DTO.CropImageDTO;
import com.example.SpringBootTest.DTO.ImageDTO;
import com.example.SpringBootTest.Service.FileService;
import com.example.SpringBootTest.Service.FolderInitService;
import com.example.SpringBootTest.Service.ImageService;
import com.example.SpringBootTest.Util.CResponseEntity;
import com.example.SpringBootTest.entity.ImageResult;
import com.example.SpringBootTest.enumeration.StatusCode;
import com.example.SpringBootTest.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/image")
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final ImageService imageService;
    private final FileService fileService;
    private final FolderInitService folderInitService;
    private final ImageRepository imageRepository;

    /**
     * 이미지의 craft, trba 결과 정보
     * @param imgName 이미지 이름
     * @param predict 인식 결과
     * @param imgPath 이미지 저장 경로
     * @return 처리 결과
     */
    @PostMapping("/jpaTest")
    public CResponseEntity<ImageResult> testMember(String imgName, String predict, String imgPath){
        ImageResult image = ImageResult.builder()
                .imgName(imgName)
                .prediction(predict)
                .imgPath(imgPath)
                .build();
        log.info("jpa Test result : {} {} {}", image.getImgName(), image.getPrediction(), image.getImgPath());
        imageRepository.save(image);
        return new CResponseEntity<>(true, StatusCode.OK, image);
    }

    /**
     * 이미지의 craft, trba 결과 정보
     * @param imageDTO 이미지 정보
     * @param files 이미지 파일
     * @return 처리 결과
     */
    @PostMapping("/fileUpload")
    public CResponseEntity<Integer> registerController(ImageDTO imageDTO, MultipartFile[] files) throws IOException, InterruptedException {
        log.info("이미지 등록 imageDTO = {}", imageDTO);
        folderInitService.mainImageFolderInit();
        int result = imageService.uploadFiles(imageDTO, files);
        return new CResponseEntity<>(true, StatusCode.OK, result);
    }

    /**
     * 이미지의 craft, trba 결과 정보
     * @return 처리 결과
     */
    @PostMapping("/makeCraft")
    public ResponseEntity<byte[]> makeCraftController() throws IOException, InterruptedException {
        folderInitService.craftImageFolderInit();
        int result = imageService.makeCraftImage();
        log.info("Craft 이미지 생성 resultCode={}", result);

        ResponseEntity<byte[]> response = imageService.loadCraftImage();
        return response;
    }

    /**
     * 이미지의 craft, trba 결과 정보
     * @return 처리 결과
     */
    @PostMapping("/cropCraftImage")
    public CResponseEntity<Integer> CropImageController() throws IOException, InterruptedException {
        folderInitService.cropImageFolderInit();

        int cropImageDTOS = imageService.cropImage();
        log.info("Craft 이미지 Crop resultCode={}", cropImageDTOS);

        int makeLmdb = imageService.makeLmdbFiles();
        log.info("Lmdb 파일 생성 resultCode={}", makeLmdb);

        return new CResponseEntity<>(true, StatusCode.OK, makeLmdb);
    }


    /**
     * 이미지의 craft, trba 결과 정보
     * @return 처리 결과
     */
    @PostMapping("/result")
    public List<CropImageDTO> resultController() throws IOException, InterruptedException {
        List<CropImageDTO> cropImageDTOS = imageService.resultImage();

        return cropImageDTOS;
    }

}
