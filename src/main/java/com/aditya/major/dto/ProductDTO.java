package com.aditya.major.dto;

import com.aditya.major.model.Category;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ProductDTO {

    private Long id;

    private String name;
    private int category;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}