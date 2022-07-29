package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class WordleGridRowDTO {
    private List<WordleGridSquareDTO> squares;


}
