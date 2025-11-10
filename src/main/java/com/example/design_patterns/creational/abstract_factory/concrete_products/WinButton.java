package com.example.design_patterns.creational.abstract_factory.concrete_products;

import com.example.design_patterns.creational.abstract_factory.interfaces.Button;

public class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a button in Windows style.");
    }
}
