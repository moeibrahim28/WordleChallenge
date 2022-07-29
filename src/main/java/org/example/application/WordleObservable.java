package org.example.application;

import org.example.dto.WordleDTO;

import java.util.HashSet;
import java.util.Set;

public abstract class WordleObservable {
    private Set<WordleObserver> observers = new HashSet<>();

    protected void notifyObservers(WordleDTO dto) {
        this.observers.forEach(observer -> observer.onUpdate(dto));
    }

    public void addObserver(WordleObserver wordleObserver){
        observers.add(wordleObserver);
    }
}
