package com.company.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.stream.Collectors;

public class ServerOlympic implements HttpHandler {
    static int requestCounter = 0;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST");
        String[] requestParams = null;
        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParams = getRequestParams(httpExchange);
        }
        if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParams = getRequestParams(httpExchange);
        }
        returnResponse(httpExchange, requestParams);
    }

    private String[] getRequestParams(HttpExchange httpExchange) {
        String parameters = httpExchange.getRequestURI().toString().split("\\?")[1];
        return parameters.split("&");
    }

    private void returnResponse(HttpExchange httpExchange, String[] requestParamValues) throws IOException {
        requestCounter++;
        System.out.println("Request received: " + requestCounter);
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder response = new StringBuilder();

        /*
         * Функція звернення до сервера з запитам на додавання об'єкту
         */
        if (requestParamValues[0].equals("addAthlete")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Person tempPerson = objectMapper.readValue(new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).lines().collect(Collectors.joining("\n")), Person.class);
            PersonsList tempPersonList = Database.loadJSON("./src/Database/AthletesDataBase.json");
            int tempSize = tempPersonList.getList().size();
            tempPersonList.add(tempPerson);
            Person.calculateRegistrationNumber(tempPersonList);
            Person.calculateRatingPlace(tempPersonList);
            tempPersonList.getList().sort(Person.byRegistrationNumber);
            Database.saveJSON(tempPersonList, "AthletesDataBase");
            if (tempSize < tempPersonList.getList().size()) {
                response.append("{\"Adding\": \"Added\"}");
            } else {
                response.append("{\"Adding\": \"notAdded\"}");
            }
        }

        /*
         * Функція звернення до сервера для перевірки існування об`єкту
         */
        if (requestParamValues[0].equals("isAthleteExist")) {
            String tempPersonSurname = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).lines().collect(Collectors.joining("\n"));
            PersonsList tempPersonList = Database.loadJSON("./src/Database/AthletesDataBase.json");
            boolean isPersonExist = false;
            for (Person person : tempPersonList.getList()) {
                if (person.getSurname().equals(tempPersonSurname)) {
                    isPersonExist = true;
                    break;
                }
            }
            if (isPersonExist) {
                response.append("{\"Person\": \"Exist\"}");
            } else {
                response.append("{\"Person\": \"notExist\"}");
            }
        }

        /*
         * Функція звернення до сервера з запитам на видалення об'єкту
         */
        if (requestParamValues[0].equals("deleteAthlete")) {
            String tempPersonSurname = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).lines().collect(Collectors.joining("\n"));
            PersonsList tempPersonList = Database.loadJSON("./src/Database/AthletesDataBase.json");
            int tempSize = tempPersonList.getList().size();
            tempPersonList.deleteBySurname(tempPersonSurname);
            Person.calculateRegistrationNumber(tempPersonList);
            Person.calculateRatingPlace(tempPersonList);
            tempPersonList.getList().sort(Person.byRegistrationNumber);
            Database.saveJSON(tempPersonList, "AthletesDataBase");
            if (tempSize > tempPersonList.getList().size()) {
                response.append("{\"Deletion\": \"Deleted\"}");
            } else {
                response.append("{\"Deletion\": \"notDeleted\"}");
            }
        }

        /*
         * Функція звернення до сервера з запитам на редагування об'єкту
         */
        if (requestParamValues[0].equals("changeAthlete")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Person tempPerson = objectMapper.readValue(new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).lines().collect(Collectors.joining("\n")), Person.class);
            PersonsList tempPersonList = Database.loadJSON("./src/Database/AthletesDataBase.json");
            tempPersonList.getList().set(tempPerson.getRegistrationNumber() - 1, tempPerson);
            Person.calculateRatingPlace(tempPersonList);
            tempPersonList.getList().sort(Person.byRegistrationNumber);
            Database.saveJSON(tempPersonList, "AthletesDataBase");
            response.append("{\"Changing\": \"Changed\"}");
        }

        /*
         * Функція звернення до сервера для підтвердження паролю
         */
        if (requestParamValues[0].equals("login")) {
            String pass = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).lines().collect(Collectors.joining("\n"));
            String password = "pass123";
            if (pass.equals(password)) {
                response.append("true");
            } else {
                response.append("false");
            }
        }

        /*
         * Функція звернення до сервера для отримання даних
         */
        if (requestParamValues[0].equals("info")) {
            response.append(new BufferedReader(new FileReader("./src/Database/AthletesDataBase.json")).readLine());
        }
        httpExchange.sendResponseHeaders(0, response.length());
        outputStream.write(response.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}

