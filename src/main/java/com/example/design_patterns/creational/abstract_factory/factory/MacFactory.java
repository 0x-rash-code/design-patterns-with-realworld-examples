package com.example.design_patterns.creational.abstract_factory.factory;

import com.example.design_patterns.creational.abstract_factory.GUIFactory;
import com.example.design_patterns.creational.abstract_factory.concrete_products.MacButton;
import com.example.design_patterns.creational.abstract_factory.concrete_products.MacTextBox;
import com.example.design_patterns.creational.abstract_factory.interfaces.Button;
import com.example.design_patterns.creational.abstract_factory.interfaces.TextBox;

public class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public TextBox createTextBox() {
        return new MacTextBox();
    }
}
