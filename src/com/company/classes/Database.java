package com.company.classes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Database {
    /**
     * Серіалізація
     */
    public static void saveJSON(PersonsList personsList, String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("./src/Database/" + filename + ".json"), personsList);
    }

    /**
     * Десеріалізація
     */
    public static PersonsList loadJSON(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filename), PersonsList.class);
    }
}
