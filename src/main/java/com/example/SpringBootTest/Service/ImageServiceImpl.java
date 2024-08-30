package com.example.SpringBootTest.Service;

import com.example.SpringBootTest.DTO.CropImageDTO;
import com.example.SpringBootTest.DTO.ImageDTO;
import com.example.SpringBootTest.converter.ImageConverter;
import com.example.SpringBootTest.entity.ImageResult;
import com.example.SpringBootTest.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService{
    private final FileService fileService;
    private final ImageRepository imageRepository;

    @Override
    public int uploadFiles(ImageDTO imageDTO, MultipartFile[] uploadFiles) throws IOException, InterruptedException {
        if(!Objects.isNull(uploadFiles)){
            List<ImageDTO> object = fileService.uploadImageFiles(uploadFiles);
            if(!Objects.isNull(object)){
                return 0;
            }
            else{
                return 500;
            }
        }
        return 500;
    }

    @Override
    public int makeCraftImage() throws IOException, InterruptedException{
        int makeCraft = fileService.makeCraftImage();
        return makeCraft;
    }

    @Override
    public int makeLmdbFiles() throws IOException, InterruptedException{
        int makeLmdb = fileService.makeLmdbFiles();
        return makeLmdb;
    }

    @Override
    public ResponseEntity<byte[]> loadCraftImage() throws IOException {
        Path folderPath = Paths.get("C:/Users/11703/Desktop/handWrite_labeling/deep-text-recognition-benchmark/demo_image/Craft_Result");
        byte[] imageBytes = null;

        try (Stream<Path> files = Files.list(folderPath)) {
            Optional<Path> imagePath = files.filter(file -> !Files.isDirectory(file) && isImageFile(file))
                    .findFirst();

            if (imagePath.isPresent()) {
                imageBytes = Files.readAllBytes(imagePath.get());
            } else {
                // 이미지 파일이 없는 경우의 처리 (예: 예외 발생 또는 404 응답 반환)
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 타입에 맞게 변경

        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }

    @Override
    public int cropImage() throws IOException, InterruptedException {
        int cropImageDTOS = fileService.cropImage();
        return cropImageDTOS;
    }

    @Override
    public List<CropImageDTO> resultImage() throws IOException, InterruptedException {
        int result = fileService.predictImage();
        if(result != 0){
            log.info("predict 실패 resultCode={}", result);
            return new ArrayList<>();
        }
        String filePath = "C:/Users/11703/Desktop/handWrite_labeling/deep-text-recognition-benchmark/results/log_evaluation.txt"; // 여기에 실제 파일 경로를 입력해야 합니다.
        List<CropImageDTO> imageList = new ArrayList<>();

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            boolean headerSkipped = false; // 첫 줄은 헤더이므로 스킵합니다.
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] parts = line.split("\\|"); // "|" 기호로 분리합니다.
                if (parts.length > 3) {
                    String imgName = parts[1].trim();
                    String prediction = parts[2].trim();
                    String imgPath = "C:/Users/11703/Desktop/handWrite_labeling/deep-text-recognition-benchmark/demo_image/crop_img/" + imgName;

                    CropImageDTO cropImage = CropImageDTO.builder()
                            .imgName(imgName)
                            .prediction(prediction)
                            .imgPath(imgPath)
                            .build();

                    imageList.add(cropImage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ImageResult> image = imageList.stream().map(cropImageDTO -> ImageConverter.dtoToEntity(cropImageDTO))
                .collect(Collectors.toList());
        log.info("save predict result = {}", imageList);

        imageRepository.saveAll(image);
        return imageList;
    }

    // 이미지 파일인지 확인하는 메소드
    private boolean isImageFile(Path file) {
        String fileName = file.toString().toLowerCase();
        return Files.isRegularFile(file) && (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".gif"));
    }
}
