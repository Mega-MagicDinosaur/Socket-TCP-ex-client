package com.fi.meucci;
import java.io.*;
import java.net.*;

public class ClientStr {
    String nomeServer = "localhost";
    int porta = 5500;
    Socket socket;
    BufferedReader tastiera;
    String inputString = "";
    String recievedString = "";
    DataOutputStream outServer;
    BufferedReader inServer;

    public Socket connetti() {
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(nomeServer, porta);
            outServer = new DataOutputStream(socket.getOutputStream());
            inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(IOException e) { System.out.println(e.getMessage()); }

        return socket;
    }

    public void comunica() {
        try {
            while (true) {
                System.out.println("inserisci stringa: ");
                inputString = tastiera.readLine();
                outServer.writeBytes(inputString+"\n");
                recievedString = inServer.readLine();
                System.out.println("response is: " + recievedString);

                if (recievedString.equals(null) || recievedString.equals("") || recievedString.equals("EXIT") || recievedString.equals("CLOSE")) { 
                    socket.close();
                    break; 
                }
            } 
        } catch (IOException e) { System.out.println(e.getMessage()); }
    }
}
