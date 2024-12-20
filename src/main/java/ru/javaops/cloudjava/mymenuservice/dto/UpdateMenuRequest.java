package ru.javaops.cloudjava.mymenuservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateMenuRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Long timeToCook;
    private String imageUrl;
}
