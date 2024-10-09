package ru.javaops.cloudjava.mymenuservice.storage.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCollection {
    private List<Ingredient> ingredients;
}
