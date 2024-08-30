package com.example.SpringBootTest.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class FolderInitServiceImpl implements FolderInitService{

    @Override
    public void mainImageFolderInit() throws IOException {
        String cmd = "del /Q C:\\Users\\11703\\Desktop\\handWrite_labeling\\deep-text-recognition-benchmark\\demo_image\\upload_img\\*.*";
        Process p = Runtime.getRuntime().exec("cmd /c" + cmd);

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String l = null;
        StringBuffer sb = new StringBuffer();
        sb.append(cmd);

        while ((l = r.readLine()) != null) {
            sb.append(l);
            sb.append("\n");
        }
    }

    @Override
    public void craftImageFolderInit() throws IOException{
        String cmd = "del /Q C:\\Users\\11703\\Desktop\\handWrite_labeling\\deep-text-recognition-benchmark\\demo_image\\Craft_Result\\*.*";
        Process p = Runtime.getRuntime().exec("cmd /c" + cmd);

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String l = null;
        StringBuffer sb = new StringBuffer();
        sb.append(cmd);

        while ((l = r.readLine()) != null) {
            sb.append(l);
            sb.append("\n");
        }
    }

    @Override
    public void cropImageFolderInit() throws IOException{
        String cmd = "del /Q C:\\Users\\11703\\Desktop\\handWrite_labeling\\deep-text-recognition-benchmark\\demo_image\\crop_img\\*.*";
        Process p = Runtime.getRuntime().exec("cmd /c" + cmd);

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String l = null;
        StringBuffer sb = new StringBuffer();
        sb.append(cmd);

        while ((l = r.readLine()) != null) {
            sb.append(l);
            sb.append("\n");
        }
    }
}
