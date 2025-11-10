package com.example.design_patterns.creational.abstract_factory.factory;

import com.example.design_patterns.creational.abstract_factory.GUIFactory;
import com.example.design_patterns.creational.abstract_factory.concrete_products.MacTextBox;
import com.example.design_patterns.creational.abstract_factory.concrete_products.WinButton;
import com.example.design_patterns.creational.abstract_factory.concrete_products.WinTextBox;
import com.example.design_patterns.creational.abstract_factory.interfaces.Button;
import com.example.design_patterns.creational.abstract_factory.interfaces.TextBox;

public class WinFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public TextBox createTextBox() {
        return new WinTextBox();
    }
}
