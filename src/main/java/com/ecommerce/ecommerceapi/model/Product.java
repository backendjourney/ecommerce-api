package com.ecommerce.ecommerceapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String description;
    private Integer quantity;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Column(precision = 10, scale = 2)
    private BigDecimal specialPrice;
    @Column(precision = 5, scale = 2)
    private BigDecimal discount;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public void recalculateSpecialPrice() {
        BigDecimal effectiveDiscount = Objects.requireNonNullElse(discount, BigDecimal.ZERO);
        BigDecimal discountAmount = price.multiply(effectiveDiscount)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        this.specialPrice = price.subtract(discountAmount);
    }
}
