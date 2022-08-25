package com.example.dailyexpenses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Lombok;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productType;
    private Date productDate;
    private String productPay;
    private String productName;
    private BigDecimal productAmount;
    private BigDecimal productPrice;

    @Override
    public String toString() {
        return "Do you want to save it? \n" +
                "-------------------------\n"+
                "Category: " + productType + '\n' +
                "Date: " + productDate +'\n'+
                "Payment type: " + productPay + '\n' +
                "Description: " + productName + '\n' +
                "Amount: " + productAmount +'\n'+
                "Price: " + productPrice +" sums"+'\n'+
                "-------------------------\n"+
                "Total: "+productAmount.multiply(productPrice)+" sums";
    }
}
