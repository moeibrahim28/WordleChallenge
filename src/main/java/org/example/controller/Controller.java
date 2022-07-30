package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.example.model.Model;

@AllArgsConstructor
public class Controller {
    private Model model;

    public void onWordSubmitted(String word) throws JsonProcessingException {
        model.onWordSubmitted(word);
    }
}
