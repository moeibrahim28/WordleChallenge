package org.example.ui;

import lombok.AllArgsConstructor;
import org.example.application.WordleObserver;
import org.example.controller.Controller;
import org.example.dto.WordleDTO;

import javax.swing.*;
import java.awt.*;

public class View implements WordleObserver {
    private Controller controller;

    private LetterGrid letterGrid;
    private WordEntry wordEntry;

    public View(Controller controller) {
        this.controller = controller;
        this.letterGrid = new LetterGrid();
        this.wordEntry = new WordEntry(controller);
    }

    public void initialize() {
        JFrame frame = new JFrame("Wordle");
        frame.setLayout(new GridLayout(1, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPage = new JPanel(new GridLayout(2, 1));
        mainPage.add(letterGrid);
        mainPage.add(wordEntry);
        frame.getContentPane().add(mainPage, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void onUpdate(WordleDTO dto) {
        // TODO - what should happen to the "view" when the model has updated data?
        //display the WordleDTO


        letterGrid.updateRows(dto.getGrid());


    }
}
