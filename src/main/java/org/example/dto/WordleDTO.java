package org.example.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class WordleDTO {
    private WordleGridDTO grid;
    private List<String> previouslyUsedList = new ArrayList<>();
    private int rowInUse =0;
    // TODO - do we need any other data?

}


