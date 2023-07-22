package com.example.lab.service.impl;

import com.example.lab.service.FileManagerService;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileManagerServiceImpl implements FileManagerService {

    private final ServletContext context;

    @Override
    public Path getPath(String folder, String filename) {
        File dir = Paths.get(context.getRealPath("/files/"), folder).toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return Paths.get(dir.getAbsolutePath(), filename);
    }

    @Override
    public byte[] read(String folder, String filename) {
        Path path = getPath(folder, filename);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> save(String folder, MultipartFile[] files) {
        List<String> filenames = new ArrayList<>();
        for (MultipartFile file : files) {
            String name = System.currentTimeMillis() + file.getOriginalFilename();
            String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
            Path path = getPath(folder, filename);
            try {
                file.transferTo(path);
                filenames.add(filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filenames;
    }

    @Override
    public void delete(String folder, String filename) {
        Path path = getPath(folder, filename);
        path.toFile().deleteOnExit();
    }

    @Override
    public List<String> list(String folder) {
        List<String> filenames = new ArrayList<>();
        File dir = Paths.get(context.getRealPath("/files/"), folder).toFile();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            Arrays.stream(files)
                    .forEach(item -> filenames.add(item.getName()));
        }
        return filenames;
    }
}
