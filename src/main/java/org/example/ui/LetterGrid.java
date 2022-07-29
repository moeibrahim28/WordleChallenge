package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LetterGrid extends JPanel {
    private List<LetterRow> rows;
    public LetterGrid() {
        super(new GridLayout(5, 1, 0, 5));
        this.rows = Arrays.asList(new LetterRow(0), new LetterRow(1), new LetterRow(2), new LetterRow(3),
                new LetterRow(4));
        populate();
    }

    private final void populate() {
        this.rows.forEach(this::add);
    }
}
