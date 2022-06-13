package com.company;

import com.company.classes.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    /**
     * Запуск сервера
     */
    public static void olympicServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/back", new ServerOlympic());
        server.start();
        System.out.println(System.lineSeparator() + "Server started at: localhost:8080");
    }

    public static void main(String[] args) throws IOException {
        PersonsList personsList = Database.loadJSON("./src/Database/AthletesDataBase.json");
        Person.calculateRatingPlace(personsList);
        personsList.getList().sort(Person.byRegistrationNumber);
        Database.saveJSON(personsList, "AthletesDataBase");
        olympicServer();
    }


}
