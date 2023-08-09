package com.target.target_common.service;

import java.io.IOException;

public interface FileService {
    /**
     * upload files
     * @param fileBytes byte arr of file
     * @param fileName
     * @return access route
     */
    String uploadImage(byte[] fileBytes, String fileName) throws IOException;

    /**
     * delete file
     * @param filePath
     */
    void delete(String filePath);
}
