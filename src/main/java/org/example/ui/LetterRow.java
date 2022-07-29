package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LetterRow extends JPanel {
    private int id;
    private List<JButton> squares;
    public LetterRow(int id) {
        super(new GridLayout(1, 5, 0, 5));
        this.squares = Arrays.asList(new LetterSquare(0), new LetterSquare(1),new LetterSquare(2),new LetterSquare(3)
                ,new LetterSquare(4));
        populate();
        this.id = id;
    }
    private final void populate() {
        this.squares.forEach(this::add);
    }
}
