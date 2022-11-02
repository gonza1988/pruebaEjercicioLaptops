
package com.example.obEj456.entities;

import javax.persistence.Entity;

@Entity
public class Laptop extends Ordenador{
    
    private String model;

    
    public Laptop() {
    }


    public Laptop(String model, Long id, double price) {
        super(id, price);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Laptop{" + "model=" + model + '}';
    }
    
    
    
}
