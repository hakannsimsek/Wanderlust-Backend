package com.example.signinandup.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public void getAllCategories() {
        List<Category> categories = repository.findAll();

        System.out.println("categories = " + categories);
    }
}
