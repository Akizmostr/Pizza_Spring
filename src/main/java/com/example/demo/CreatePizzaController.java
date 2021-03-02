package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/create")
public class CreatePizzaController {
    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("CLS22", "Classic base 22cm", Ingredient.Type.BASIC),
                new Ingredient("PEPE", "Pepperoni", Ingredient.Type.MEAT)
                );

        return "design";
    }
}


