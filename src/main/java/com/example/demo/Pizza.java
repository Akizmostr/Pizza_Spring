package com.example.demo;

import lombok.Data;

import java.util.List;

@Data
public class Pizza {
    private String name;
    private List<String> ingredients;
}
