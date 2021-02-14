package com.example.demo.web;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
public class CatalogueRestController {
    private ProductRepository productRepository;

    public CatalogueRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Product p = productRepository.findById(id).get();
        System.out.println(System.getProperty("user.home"));
       return  Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));
    }
    @PostMapping("/uploadPhoto/{id}")
    public void uploadPhoto( @RequestBody MultipartFile file, @PathVariable Long id) throws IOException {
        Product p = productRepository.findById(id).get();
        p.setPhotoName(id+".jpg");
        Files.write(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()),file.getBytes());
        productRepository.save(p);
    }
}
