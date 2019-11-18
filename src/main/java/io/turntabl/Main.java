package io.turntabl;

import io.turntabl.Client;
import io.turntabl.ClientController;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Doreen Dodoo","Libya Street","0244333441","doreen@turntabl.io");

        ClientController clientController = new ClientController();
        //Adding new client
        clientController.addNewClient(client);

        //Getting all clients
        System.out.println(clientController.getAllClients());
    }
}
