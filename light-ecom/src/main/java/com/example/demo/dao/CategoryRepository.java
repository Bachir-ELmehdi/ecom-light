package com.example.demo.dao;

import com.example.demo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Locale;
@CrossOrigin("*")
@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
