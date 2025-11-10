package com.example.design_patterns.creational.abstract_factory.concrete_products;

import com.example.design_patterns.creational.abstract_factory.interfaces.TextBox;

public class WinTextBox implements TextBox {
    @Override
    public void render() {
        System.out.println("Rendering a text box in Windows style.");
    }
}
