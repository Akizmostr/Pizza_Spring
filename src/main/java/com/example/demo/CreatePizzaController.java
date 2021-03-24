package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/create")
@SessionAttributes("order")
public class CreatePizzaController {
    private final IngredientRepository ingredientRepo;

    private PizzaRepository createRepo;

    @Autowired
    public CreatePizzaController(IngredientRepository ingredientRepo, PizzaRepository createRepo) {
        this.ingredientRepo = ingredientRepo;
        this.createRepo = createRepo;
    }

    @GetMapping
    public String showCreationForm(Model model){
        addCreationFormModelAttributes(model, new Pizza());
        return "create";
    }

    @PostMapping
    public String processCreation(@Valid @ModelAttribute("create") Pizza pizza, @Valid @ModelAttribute("order") Order order, BindingResult result, Model model){
        if(result.hasErrors()){
            addCreationFormModelAttributes(model, pizza);
            return "create";
        }
        //Save creation...
        Pizza saved = createRepo.save(pizza);
        order.addCreation(saved);
        log.info("Processing creation: " + pizza);
        return "redirect:/orders/current";
    }
    private void addCreationFormModelAttributes(Model model, Pizza pizza) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("CLS22", "Classic base 22cm", Ingredient.Type.BASIC),
                new Ingredient("CLS30", "Classic base 30cm", Ingredient.Type.BASIC),
                new Ingredient("PEPE", "Pepperoni", Ingredient.Type.MEAT),
                new Ingredient("HAM", "Ham", Ingredient.Type.MEAT),
                new Ingredient("CHMPG", "Champignons", Ingredient.Type.VEGGIES),
                new Ingredient("CUCU", "Cucumbers", Ingredient.Type.VEGGIES),
                new Ingredient("PARM", "Parmesan cheese", Ingredient.Type.CHEESE),
                new Ingredient("MOZ", "Mozzarella cheese", Ingredient.Type.CHEESE),
                new Ingredient("BARBS", "Barbecue sauce", Ingredient.Type.SAUCE),
                new Ingredient("TOMAS", "Tomato sauce", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("create", pizza);
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        List<Ingredient> filtered = new ArrayList<>();
        for (Ingredient ingredient:ingredients){
            if(ingredient.getType() == type){
                filtered.add(ingredient);
            }
        }
        return filtered;
    }
}


