package ru.ssau.OOP_lab.components;

import org.springframework.stereotype.Component;

public class AmountOfPointsComponent implements Components {
    private Integer amount = 0;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;


    }
}
