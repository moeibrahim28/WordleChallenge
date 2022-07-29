package org.example.ui;

import org.example.dto.WordleGridRowDTO;
import org.example.dto.WordleGridSquareDTO;
import org.example.model.LetterSquareState;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LetterRow extends JPanel {
    private final int id;
    private final List<LetterSquare> squares;

    public LetterRow(int id) {
        super(new GridLayout(1, 5, 0, 5));
        this.squares = Arrays.asList(new LetterSquare(0), new LetterSquare(1), new LetterSquare(2), new LetterSquare(3)
                , new LetterSquare(4));
        populate();
        this.id = id;
    }

    private final void populate() {
        this.squares.forEach(this::add);
    }

    public void updateRow(WordleGridRowDTO wordleGridRowDTO) {
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).setText(wordleGridRowDTO.getSquares().get(i).getLetter() + "");
        }
        updateSquareColor(wordleGridRowDTO);
    }

    public void updateSquareColor(WordleGridRowDTO wordleGridRowDTO) {
        for (int i = 0; i < wordleGridRowDTO.getSquares().size(); i++) {
            WordleGridSquareDTO wordleGridSquareDTO = wordleGridRowDTO.getSquares().get(i);
            if (wordleGridSquareDTO.getState().equals(LetterSquareState.RIGHT)) {
                squares.get(i).setBackground(Color.GREEN);
            } else if (wordleGridSquareDTO.getState().equals(LetterSquareState.WRONG_PLACE)) {
                squares.get(i).setBackground(Color.YELLOW);
            } else if (wordleGridSquareDTO.getState().equals(LetterSquareState.TOTALLY_WRONG)) {
                squares.get(i).setBackground(Color.GRAY);
            }
            squares.get(i).setOpaque(true);
            squares.get(i).setBorderPainted(false);

        }
    }


    public List<LetterSquare> getSquares() {
        return squares;
    }
}
