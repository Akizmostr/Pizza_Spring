package com.example.demo;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Pizza {
    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<String> ingredients;
}
