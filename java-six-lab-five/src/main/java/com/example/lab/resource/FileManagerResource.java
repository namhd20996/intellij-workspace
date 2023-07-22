package com.example.lab.resource;

import com.example.lab.service.FileManagerService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileManagerResource {

    private final FileManagerService service;

    @GetMapping("/rest/files/{folder}/{file}")
    public ResponseEntity<?> download(@PathVariable("folder") String folder, @PathVariable("file") String file) {
        return new ResponseEntity<>(service.read(folder, file), HttpStatus.OK);
    }

    @PostMapping("/rest/files/{folder}")
    public ResponseEntity<List<String>> upload(@PathVariable("folder") String folder, @PathParam("files") MultipartFile[] files) {
        return new ResponseEntity<>(service.save(folder, files), HttpStatus.OK);
    }

    @DeleteMapping("/rest/files/{folder}/{file}")
    public ResponseEntity<?> delete(@PathVariable("folder") String folder, @PathVariable("file") String file) {
        service.delete(folder, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rest/files/{folder}")
    public ResponseEntity<List<String>> list(@PathVariable("folder") String folder) {
        return new ResponseEntity<>(service.list(folder), HttpStatus.OK);
    }
}
