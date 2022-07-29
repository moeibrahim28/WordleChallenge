package org.example.model;

import org.example.application.WordleObservable;
import org.example.dto.WordleDTO;
import org.example.dto.WordleGridDTO;
import org.example.dto.WordleGridRowDTO;
import org.example.dto.WordleGridSquareDTO;
import org.example.service.Dictionary;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static java.rmi.Naming.list;

public class Model extends WordleObservable {
    //ALL GAME LOGIC THAT PASSES WORDLEDTO BACK TO VIEW
    //NEW WORDLEDTO CREATED EACH TIME WE PASS IT BACK

    // TODO what properties does the "Model" need to keep track of?
    private final Dictionary dictionary = Dictionary.getInstance();
    private String wordTried;
    private final String correctWord;
    private int attemptsMade=0;
    private final List<Character> characterListCorrectWord;
    List<WordleGridRowDTO> rows = new ArrayList<>();


    WordleGridDTO wordleGridDTO = new WordleGridDTO();

    public Model() {
        correctWord = dictionary.getRandomWord();
        characterListCorrectWord = new ArrayList<>();
        for(char ch: correctWord.toCharArray()){
            characterListCorrectWord.add(ch);
        }
    }

    public void onWordSubmitted(String word) {
        // TODO what do i want to do with the data i got from the user
        // already checked that it is valid(all letters) and is of length 5
        if (dictionary.isValidWord(word)) {
            wordTried=word;
            this.notifyObservers(createWordleDTO());
        } else {
            JOptionPane.showMessageDialog(null, "That word does not exist.");
        }

    }

    private WordleDTO createWordleDTO() {
        // TODO - what information needs to go in the WordleDTO?
        // This DTO (data transfer object) should be an immutable object used to send messages from the Model to
        // anyone who cares.
        WordleDTO wordleDTO = new WordleDTO();

        WordleGridRowDTO wordleGridRowDTO=null;
        List<WordleGridSquareDTO> squares = new ArrayList<>();

        for(int i = 0; i<wordTried.length();i++){
            wordleGridRowDTO = new WordleGridRowDTO();
            WordleGridSquareDTO wordleGridSquareDTO = new WordleGridSquareDTO();
            wordleGridSquareDTO.setLetter(wordTried.charAt(i));
            squares.add(wordleGridSquareDTO);
        }
        wordleGridRowDTO.setSquares(squares);
        updateSquareStates(wordleGridRowDTO);
        rows.add(wordleGridRowDTO);
        wordleGridDTO.setRows(rows);
        wordleDTO.setGrid(wordleGridDTO);
        attemptsMade++;
        return wordleDTO;
    }

    private void updateSquareStates(WordleGridRowDTO wordleGridRowDTO){
        for(int i =0; i< wordleGridRowDTO.getSquares().size();i++){
            if(wordleGridRowDTO.getSquares().get(i).getLetter()== characterListCorrectWord.get(i)){
                wordleGridRowDTO.getSquares().get(i).setState(LetterSquareState.RIGHT);
            }
            else if(characterListCorrectWord.contains(wordleGridRowDTO.getSquares().get(i).getLetter())){
                wordleGridRowDTO.getSquares().get(i).setState(LetterSquareState.WRONG_PLACE);
            }
            else if(!characterListCorrectWord.contains(wordleGridRowDTO.getSquares().get(i).getLetter())){
                wordleGridRowDTO.getSquares().get(i).setState(LetterSquareState.TOTALLY_WRONG);
            }
        }
    }
}
