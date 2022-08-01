package org.example.ui;

import org.example.dto.WordleGridDTO;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LetterGrid extends JPanel {
    private final List<LetterRow> rows;

    public LetterGrid() {
        super(new GridLayout(5, 1, 0, 5));
        this.rows = Arrays.asList(new LetterRow(0), new LetterRow(1), new LetterRow(2), new LetterRow(3),
                new LetterRow(4));
        populate();
    }

    private final void populate() {
        this.rows.forEach(this::add);
    }

    public void updateRows(WordleGridDTO wordleGridDTO) {
        for (int i = 0; i < wordleGridDTO.getRows().size(); i++) {
            rows.get(i).updateRow(wordleGridDTO.getRows().get(i));
        }
    }
}
