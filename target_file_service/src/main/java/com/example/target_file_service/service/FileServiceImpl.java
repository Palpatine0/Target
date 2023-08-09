package com.example.target_file_service.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.target.target_common.exception.BusException;
import com.target.target_common.result.CodeEnum;
import com.target.target_common.service.FileService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@DubboService
public class FileServiceImpl implements FileService {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Value("${fdfs.fileUrl}")
    private String fileUrl;     //the url for accessing FastDFS proposes for Nginx

    @Override
    public String uploadImage(byte[] fileBytes, String fileName) {
        if (fileBytes.length != 0) {
            try {
                //1.convert the byte array in to input stream
                InputStream inputStream = new ByteArrayInputStream(fileBytes);
                //2.capture the substring of the file
                String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //3.upload
                StorePath storePath = fastFileStorageClient.uploadFile(inputStream, inputStream.available(), fileSuffix, null);
                // 4.capture the img route and return
                String imageUrl = fileUrl + "/"+storePath.getFullPath();
                return imageUrl;
            } catch (IOException ioException) {
                throw new BusException(CodeEnum.UPLOAD_FILE_ERROR);
            }
        } else {
            throw new BusException(CodeEnum.UPLOAD_FILE_ERROR);
        }
    }

    @Override
    public void delete(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
    }
}
