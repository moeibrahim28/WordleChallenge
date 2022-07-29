package org.example.model;

import org.example.application.WordleObservable;
import org.example.dto.WordleDTO;

public class Model extends WordleObservable {
    // TODO what properties does the "Model" need to keep track of?
    public void onWordSubmitted(String word) {
        // TODO
        this.notifyObservers(createWordleDTO());
    }

    private WordleDTO createWordleDTO() {
        // TODO - what information needs to go in the WordleDTO?
        // This DTO (data transfer object) should be an immutable object used to send messages from the Model to
        // anyone who cares.
        return new WordleDTO();
    }
}
