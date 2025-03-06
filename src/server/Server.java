/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Andrej
 */
public class Server extends Thread{
    
    boolean kraj = false;
    ServerSocket serverSocket;
    List<ObradaKlijentskihZahteva> klijent;
    
    public Server(){
     this.klijent = new ArrayList<>();   
    }

    @Override
    public void run() {
        try {
            int port = 9000;
            //port = Integer.parseInt(Konfiguracija.getInstance().getPropertie("port"));
            serverSocket = new ServerSocket(port);
            while(!kraj){
                Socket socket = serverSocket.accept();
                System.out.println("Klijent povezan");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(socket);
                klijent.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void zaustaviServer() throws IOException{
        for(ObradaKlijentskihZahteva okz: klijent){
            okz.prekiniNit();
        }
        kraj = true;
        serverSocket.close();
    }
    
    
}
