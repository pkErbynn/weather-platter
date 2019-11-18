package io.turntabl;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Doreen Dodoo","Libya Street","0244333441","doreen@turntabl.io");
        Client client2 = new Client("Erbynn","Norway Street","0244333441","erbynn@turntabl.io");
        Client client3 = new Client("John","Gauda Street","0244333441","me@turntabl.io");

        ClientManager clientManager = new ClientManager();
        clientManager.addNewClient(client);
        clientManager.addNewClient(client2);
        clientManager.addNewClient(client3);

        System.out.println(clientManager.getAllClients());
    }
}
