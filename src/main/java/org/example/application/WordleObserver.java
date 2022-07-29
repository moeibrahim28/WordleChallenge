package org.example.application;

import org.example.dto.WordleDTO;

public interface WordleObserver {
    public void onUpdate(WordleDTO dto);
}
