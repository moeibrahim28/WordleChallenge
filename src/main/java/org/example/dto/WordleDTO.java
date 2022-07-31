package org.example.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
public class WordleDTO {
    private WordleGridDTO grid;
    private List<String> previouslyUsedList = new ArrayList<>();
    private Set<Character> previouslyUsedCharacters = new HashSet<>();
    // TODO - do we need any other data?

}


