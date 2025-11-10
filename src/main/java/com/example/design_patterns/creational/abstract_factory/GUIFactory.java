package com.example.design_patterns.creational.abstract_factory;

import com.example.design_patterns.creational.abstract_factory.interfaces.Button;
import com.example.design_patterns.creational.abstract_factory.interfaces.TextBox;
import org.w3c.dom.Text;

public interface GUIFactory {
    Button createButton();
    TextBox createTextBox();
}
