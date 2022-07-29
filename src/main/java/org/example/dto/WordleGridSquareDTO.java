package org.example.dto;

import lombok.Data;
import org.example.model.LetterSquareState;

@Data
public class WordleGridSquareDTO {
    private int id;
    private char letter;
    private LetterSquareState state;
}
