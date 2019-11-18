package io.turntabl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class ClientManager {
    private File csFile = new File("ClientStore.json");

    public void addNewClient(Client client) {
        if (!csFile.exists()) {
            try {
                csFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (csFile.exists()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type clientType = new TypeToken<List<Client>>() {
            }.getType();

            try (FileReader fileReader = new FileReader(csFile)) {
                List<Client> clients = gson.fromJson(fileReader, clientType);

                if (clients == null) {
                    clients = new ArrayList<>();
                }

                clients.add(client);

                try (FileWriter fw = new FileWriter(csFile)) {
                    gson.toJson(clients, fw);
                    System.out.println("New Client added successfully");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public List<Client> getAllClients() {
        Gson gson = new Gson();
        Type clientType = new TypeToken<List<Client>>() {
        }.getType();
        try (FileReader fileReader = new FileReader(csFile)) {
            // deserialization
            List<Client> clients = gson.fromJson(fileReader, clientType);
            if (clients.isEmpty()) {
                return new ArrayList<>();
            }
            return clients;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}



