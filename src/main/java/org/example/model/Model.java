package org.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.WordleObservable;
import org.example.dto.WordleDTO;
import org.example.dto.WordleGridDTO;
import org.example.dto.WordleGridRowDTO;
import org.example.dto.WordleGridSquareDTO;
import org.example.service.Dictionary;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model extends WordleObservable {
    //ALL GAME LOGIC THAT PASSES WORDLEDTO BACK TO VIEW
    //NEW WORDLEDTO CREATED EACH TIME WE PASS IT BACK

    // TODO what properties does the "Model" need to keep track of?
    private final Dictionary dictionary = Dictionary.getInstance();
    private final String correctWord;
    private final List<Character> characterListCorrectWord;
    private final String fileName = "user.json";
    private final User user;
    private final JsonFileUserOutputService jsonFileUserOutputService = new JsonFileUserOutputService();
    List<WordleGridRowDTO> rows = new ArrayList<>();
    WordleGridDTO wordleGridDTO = new WordleGridDTO();
    private int attemptsMade = 0;
    private String wordTried;
    private boolean winner = false;

    public Model() throws JsonProcessingException {
        correctWord = dictionary.getRandomWord();
        characterListCorrectWord = new ArrayList<>();
        for (char ch : correctWord.toCharArray()) {
            characterListCorrectWord.add(ch);
        }
        user = readUserFromJSON();
    }

    public User getUser() {
        return user;
    }

    public User readUserFromJSON() throws JsonProcessingException {

        User user = null;
        // If the File of this file name does not exist, exit program
        if (!new File(fileName).exists()) {
            System.out.println("No user exists. Creating a new profile.");
            user = new User();
        } else {
            //Get the contents of the file from our File Reader, made by curriculum.
            String jsonFileContents = FileReader.readFromFile(fileName);
            try {
                user = new ObjectMapper().readValue(jsonFileContents, User.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void onWordSubmitted(String word) throws JsonProcessingException {
        // TODO what do i want to do with the data i got from the user
        // already checked that it is valid(all letters) and is of length 5
        if (dictionary.isValidWord(word)) {
            wordTried = word;
            this.notifyObservers(createWordleDTO());
        } else {
            JOptionPane.showMessageDialog(null, "That word does not exist.");
        }
    }

    private WordleDTO createWordleDTO() throws JsonProcessingException {
        // TODO - what information needs to go in the WordleDTO?
        // This DTO (data transfer object) should be an immutable object used to send messages from the Model to
        // anyone who cares.
        WordleDTO wordleDTO = new WordleDTO();

        WordleGridRowDTO wordleGridRowDTO = null;
        List<WordleGridSquareDTO> squares = new ArrayList<>();

        for (int i = 0; i < wordTried.length(); i++) {
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
        return wordleDTO;
    }

    private void updateSquareStates(WordleGridRowDTO wordleGridRowDTO) throws JsonProcessingException {

        for (int i = 0; i < wordleGridRowDTO.getSquares().size(); i++) {
            if (wordleGridRowDTO.getSquares().get(i).getLetter() == characterListCorrectWord.get(i)) {
                wordleGridRowDTO.getSquares().get(i).setState(LetterSquareState.RIGHT);
            } else if (characterListCorrectWord.contains(wordleGridRowDTO.getSquares().get(i).getLetter())) {
                wordleGridRowDTO.getSquares().get(i).setState(LetterSquareState.WRONG_PLACE);
            } else if (!characterListCorrectWord.contains(wordleGridRowDTO.getSquares().get(i).getLetter())) {
                wordleGridRowDTO.getSquares().get(i).setState(LetterSquareState.TOTALLY_WRONG);
            }
        }
        attemptsMade++;
        if (wordTried.equals(correctWord)) {
            JOptionPane.showMessageDialog(null, "CONGRATS YOU GOT THE WORD RIGHT.");
            this.winner = true;
            user.setGamesWon(user.getGamesWon() + 1);
            user.setGamesPlayed(user.getGamesPlayed() + 1);
            double percentage = user.getGamesWon() / user.getGamesPlayed();
            user.setWinPercentage(percentage);
            jsonFileUserOutputService.save(user);
            JOptionPane.showMessageDialog(null, String.format("Games Won: " + user.getGamesWon() + "\nGames Played: " +
                    user.getGamesPlayed() + "\nWin Percentage: " + user.getWinPercentage()));
            writeJson(user);
        }
        if (attemptsMade == 5 && !wordTried.equals(correctWord)) {
            user.setGamesPlayed(user.getGamesPlayed() + 1);
            double percentage = user.getGamesWon() / user.getGamesPlayed();
            user.setWinPercentage(percentage);
            jsonFileUserOutputService.save(user);
            JOptionPane.showMessageDialog(null, "Sorry you lost. The correct word was: " + correctWord);
            JOptionPane.showMessageDialog(null, String.format("Games Won: " + user.getGamesWon() + "\nGames Played: " +
                    user.getGamesPlayed() + "\nWin Percentage: " + user.getWinPercentage()));
            writeJson(user);
        }
    }

    public void writeJson(User user) throws JsonProcessingException {

        String json = new ObjectMapper().writeValueAsString(user);
        String fileName = "user.json";
        try {
            FileReader.writeToFile(fileName, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

