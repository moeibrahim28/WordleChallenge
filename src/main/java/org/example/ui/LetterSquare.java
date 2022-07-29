package org.example.ui;

import org.example.model.LetterSquareState;

import javax.swing.*;

public class LetterSquare extends JButton {
    private int id;
    private char letter;

    private LetterSquareState state;
    public LetterSquare(int id) {
        this.id = id;
        this.letter = ' ';
        this.state = LetterSquareState.EMPTY;
    }

    public LetterSquareState getState() {
        return state;
    }

    public void setState(LetterSquareState state) {
        this.state = state;
    }
}
