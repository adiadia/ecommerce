package com.aditya.major.service;

import com.aditya.major.model.Category;
import com.aditya.major.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
       categoryRepository.save(category);
    }

    public  Optional<Category> getById(int id){
        return categoryRepository.findById(id);
    }

    public boolean deleteCategory(int id){
        Optional<Category> category = this.getById(id);
      if(category.isPresent()){
          categoryRepository.deleteById(id);
          return true;
      }
      return false;
    }
}
