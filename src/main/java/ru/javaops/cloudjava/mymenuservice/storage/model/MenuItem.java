package ru.javaops.cloudjava.mymenuservice.storage.model;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javaops.cloudjava.mymenuservice.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Type;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menu_items")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(name = "time_to_cook", nullable = false)
    private Long timeToCook;

    @Column(nullable = false)
    private Double weight;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Type(JsonBinaryType.class)
    @Column(name = "ingredient_collection", columnDefinition = "jsonb", nullable = false)
    private IngredientCollection ingredientCollection;
}
