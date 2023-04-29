package com.abhiprojects.blog.blogappapis.services.impl;

import com.abhiprojects.blog.blogappapis.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File name
        String name=file.getOriginalFilename();

        //Full path
        String filePath=(path+ File.separator+name);

        //create folder if not created
        File file1=new File(path);
        if(!file1.exists())
        {
            file1.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(),Paths.get(filePath));
        //file.transferTo(filePath);
        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullPath);
        return is;

    }
}
