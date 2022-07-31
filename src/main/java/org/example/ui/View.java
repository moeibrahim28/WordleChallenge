package org.example.ui;

import org.example.application.WordleObserver;
import org.example.controller.Controller;
import org.example.dto.WordleDTO;

import javax.swing.*;
import java.awt.*;

public class View implements WordleObserver {
    private final Controller controller;

    private final LetterGrid letterGrid;
    private final WordEntry wordEntry;
    private final LettersUsedBox lettersUsedBox;

    public View(Controller controller) {
        this.controller = controller;
        this.letterGrid = new LetterGrid();
        this.wordEntry = new WordEntry(controller);
        this.lettersUsedBox = new LettersUsedBox();
    }

    public void initialize() {
        JFrame frame = new JFrame("Wordle");
        frame.setLayout(new GridLayout(1, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPage = new JPanel(new GridLayout(3, 1));
        mainPage.add(letterGrid);
        mainPage.add(lettersUsedBox);
        mainPage.add(wordEntry);
        frame.getContentPane().add(mainPage, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void onUpdate(WordleDTO dto) {
        // TODO - what should happen to the "view" when the model has updated data?
        letterGrid.updateRows(dto.getGrid());
        lettersUsedBox.updateLettersUsedBox(dto);


    }
}
