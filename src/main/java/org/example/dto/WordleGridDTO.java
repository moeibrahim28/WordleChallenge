package org.example.dto;

import lombok.Data;

import java.util.List;
@Data
public class WordleGridDTO {
    private List<WordleGridRowDTO> rows;
}
