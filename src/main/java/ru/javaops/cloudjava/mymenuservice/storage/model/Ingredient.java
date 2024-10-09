package ru.javaops.cloudjava.mymenuservice.storage.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private String name;
    private int calories;
}
