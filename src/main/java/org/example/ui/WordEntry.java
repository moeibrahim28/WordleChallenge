package org.example.ui;

import org.example.controller.Controller;

import javax.swing.*;
import java.util.Locale;

public class WordEntry extends JTextField {
    public WordEntry(Controller controller) {
        super(null, 5);
        this.addActionListener((e) -> {
            if(isTextValid()) {
                System.out.println("Valid");
                controller.onWordSubmitted(getText());
                setText("");
            }
            else {
                System.out.println("Invalid");
                // TODO - how do you want to handle an invalid word?
            }
        });
    }

    private String normalized() {
        return getText().toUpperCase(Locale.ROOT).trim().replaceAll("[^A-Z]+", "");
    }
    private boolean isTextValid() {
        String text = normalized();
        return text.length() == 5;
    }
}
