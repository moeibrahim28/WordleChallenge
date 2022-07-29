package org.example.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Dictionary {
    private static Dictionary INSTANCE;

    private Map<String, Future<Set<String>>> startingLetterToWords;
    private ExecutorService executorService;

    private List<String> fiveLetterWords=new ArrayList<>();
    private final String fileName="5_letter_words.csv";

    private HttpClient client;

    private Dictionary() {
        this.startingLetterToWords = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(1);
        this.client = HttpClient.newHttpClient();

        try (Scanner scanner = new Scanner(new File(fileName))){
            scanner.useDelimiter(",");

            while(scanner.hasNext()){
            fiveLetterWords.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized static Dictionary getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Dictionary();
        }
        return INSTANCE;
    }

    public boolean isValidWord(String wordToCheck) {
        String firstLetter =
                Optional.ofNullable(wordToCheck).filter(word -> word.length() > 0).map(word -> word.substring(0, 1)).orElse(null);

        if (firstLetter != null) {
            startingLetterToWords.computeIfAbsent(firstLetter,
                    (s) -> executorService.submit(() -> getWordsThatStartWith(firstLetter)));
        } else if (firstLetter == null) {
            return false;
        }
        try {
            return startingLetterToWords.get(firstLetter).get().contains(wordToCheck);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Set<String> getWordsThatStartWith(String startingLetter) {
        System.out.println(LocalTime.now() + "--- Fetching words that start with '" + startingLetter + "'");
        startingLetter = startingLetter.toLowerCase();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://localstorage.tools/game/word/dictionary/" + startingLetter + ".txt"))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return Set.of(response.body().split(", ")).stream().map(str -> str.replaceAll("[^a-z]", "")).collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    public String getRandomWord() {
        Random random = new Random();
        int randomIndex= random.nextInt(fiveLetterWords.size());
        return fiveLetterWords.get(randomIndex);
    }
}