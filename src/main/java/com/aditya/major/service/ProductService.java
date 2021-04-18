package com.aditya.major.service;

import com.aditya.major.model.Product;
import com.aditya.major.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id){ productRepository.deleteById(id);    }

    public List<Product> getProductByCategoryId(int id){return productRepository.findAllByCategory_Id(id);}
}
