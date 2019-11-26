//package com.bsidesoft.service;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//
//import java.net.MalformedURLException;
//import java.nio.file.Path;
//
//@Service
//public class FileService {
//
//    private final Path rootLocation = "/com/bsidesoft/produceimage/";
//
//    public Resource loadAsResource(String filename) throws MalformedURLException {
//        Path file = rootLocation.resolve(filename);
//        Resource resource = new UrlResource(file.toUri());
////        if (resource.exists() || resource.isReadable()) {
////            return resource;
////        }
//
//        return resource;
//    }
//}
