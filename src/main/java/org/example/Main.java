package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.WordleObservable;
import org.example.controller.Controller;
import org.example.dto.WordleDTO;
import org.example.model.FileReader;
import org.example.model.Model;
import org.example.model.User;
import org.example.ui.View;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        // 0. Create the model/view/controller
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);
        view.initialize();
        model.addObserver(view);

    }


}