package org.example;

import org.example.controller.Controller;
import org.example.model.Model;
import org.example.ui.View;

public class Main {
    public static void main(String[] args) {
        // 0. Create the model/view/controller
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);
        view.initialize();

    }
}