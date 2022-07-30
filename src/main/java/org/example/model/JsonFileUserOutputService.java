package org.example.model;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileUserOutputService implements UserOutputService {
    private final static String OUTPUT_FILE_NAME = "user.json";

    public JsonFileUserOutputService() {
    }

    @Override
    public void save(User user) {
        // TODO
        try (FileWriter file = new FileWriter(OUTPUT_FILE_NAME)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(user.toString());
            file.flush();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
