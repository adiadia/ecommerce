package com.aditya.major.controller;

import com.aditya.major.model.Category;
import com.aditya.major.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model model){
        model.addAttribute("category",new Category());
        return  "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String addCategoryInDatabase(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getById(id);
        if(!category.isPresent()){
            return "404";
        }
        model.addAttribute("category",category.get());
        return  "categoriesAdd";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}
