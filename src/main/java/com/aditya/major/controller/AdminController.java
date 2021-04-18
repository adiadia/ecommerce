package com.aditya.major.controller;

import com.aditya.major.dto.ProductDTO;
import com.aditya.major.model.Category;
import com.aditya.major.model.Product;
import com.aditya.major.service.CategoryService;
import com.aditya.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

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

    @GetMapping("/admin/products")
    public String getProduct(Model model){
        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model){
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String addProductInDatabase(@ModelAttribute("productDTO") ProductDTO productDTO){
        Product product = new Product();
        product.setCategory(categoryService.getById(productDTO.getCategoryId()).get());
        product.setDescription(productDTO.getDescription());
        product.setId(productDTO.getId());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setName(productDTO.getName());
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        Optional<Product> productOptional = productService.getById(id);
        if(!productOptional.isPresent()){
            return "404";
        }
        Product product = productOptional.get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategoryId(product.getCategory().getId());
        model.addAttribute("productDTO",productDTO);
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }
}
