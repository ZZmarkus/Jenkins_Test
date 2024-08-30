package com.example.SpringBootTest.Service;

import java.io.IOException;

public interface FolderInitService {
    void mainImageFolderInit() throws IOException;
    void craftImageFolderInit() throws IOException;
    void cropImageFolderInit() throws IOException;
}
