package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.Model;

@AllArgsConstructor
public class Controller {
    private Model model;

    public void onWordSubmitted(String word) {
        model.onWordSubmitted(word);
    }
}
