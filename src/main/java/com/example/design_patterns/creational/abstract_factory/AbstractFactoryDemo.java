package com.example.design_patterns.creational.abstract_factory;

import com.example.design_patterns.creational.abstract_factory.factory.MacFactory;
import com.example.design_patterns.creational.abstract_factory.factory.WinFactory;
import com.example.design_patterns.creational.abstract_factory.interfaces.Button;
import com.example.design_patterns.creational.abstract_factory.interfaces.TextBox;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory = new WinFactory();
        Button btn = factory.createButton();
        TextBox textBox = factory.createTextBox();
        btn.paint();
        textBox.render();

        GUIFactory macFactory = new MacFactory();
        Button macBtn = macFactory.createButton();
        TextBox macTextBox = macFactory.createTextBox();

        macBtn.paint();
        macTextBox.render();

    }
}
