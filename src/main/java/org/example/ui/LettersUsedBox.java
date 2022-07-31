package org.example.ui;

import org.example.dto.WordleDTO;
import org.example.dto.WordleGridDTO;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class LettersUsedBox extends JPanel {
    @Override
    public void setLayout(LayoutManager mgr) {
        super.setLayout(new GridLayout(0,1));
    }

    JLabel label = new JLabel("Letters Used", SwingConstants.CENTER);
    JLabel lettersUsedLabel = new JLabel("",SwingConstants.CENTER);
    private List<LetterRow> usedLetterRows;
    public LettersUsedBox() {

        this.add(label);
        this.add(lettersUsedLabel);

    }
    void updateLettersUsedBox(WordleDTO wordleDTO){
        this.lettersUsedLabel.setText(wordleDTO.getPreviouslyUsedCharacters().toString());
    }
}
