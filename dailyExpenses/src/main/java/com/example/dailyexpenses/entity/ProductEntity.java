package com.example.dailyexpenses.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "product")
public class ProductEntity {

    public ProductEntity(String productType, String productPay, String productName, BigDecimal productAmount, BigDecimal productPrice, Date productDate,UserEntity user) {
        this.productType = productType;
        this.productPay = productPay;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
        this.productDate = productDate;
        this.user=user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(
            name = "product_type",
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String productType;

    @Column(
            name = "product_pay",
            nullable = false,
            columnDefinition = "VARCHAR(5)"
    )
    private String productPay;

    @Column(
            name = "product_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String productName;

    @Column(
            name = "product_amount",
            nullable = false,
            columnDefinition = "DECIMAL"
    )
    private BigDecimal productAmount;

    @Column(
            name = "product_price",
            nullable = false,
            columnDefinition = "DECIMAL"
    )
    private BigDecimal productPrice;

    @Column(
            name = "product_date",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private Date productDate;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserEntity user;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId=" + productId +
                ", productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                ", productPrice=" + productPrice +
                ", productDate=" + productDate +
                '}';
    }
}
