/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Operacija;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;
import model.Lokalitet;
import model.Menadzer;
import model.Privilegija;
import model.Proizvod;

/**
 *
 * @author Andrej
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public void prekiniNit() throws IOException {
        kraj = true;
        socket.close();
        interrupt();
    }

    @Override
    public void run() {

        while (!kraj) {
            Request request = (Request) receiver.receive();
            Response response = new Response();

            switch (request.getOperacija()) {
            case Operacija.PRIJAVA: {
                prijava(request, response);
                break;
            }
            case Operacija.VRATI_PRIVILEGIJU: {
                vratiPrivilegiju(request, response);
                break;
            }
            case Operacija.VRATI_LISTU_PRIVILEGIJA: {
                vratiListuPrivilegija(request, response);
                break;
            }
            case Operacija.IZMENI_MENADZERA: {
                izmeniMenadzera(request, response);
                break;
            }
            case Operacija.UGASI_NALOG: {
                ugasiNalog(request, response);
                break;
            }
            case Operacija.VRATI_ZAPOSLENE: {
                vratiListuZaposlenih(request, response);
                break;
            }
            case Operacija.FILTRIRAJ_ZAPOSLENI: {
                vratiFilterZaposleni(request, response);
                break;
            }
            case Operacija.VRATI_LOKALITET: {
                vratiListuLokaliteta(request,response);
                break;
            }
            case Operacija.FILTER_LOKALITET: {
                vratiFilterLokalitet(request,response);
                break;
            }
            case Operacija.KREIRAJ_LOKALITET:{
                kreirajLokalitet(request,response);
                break;
            }
            case Operacija.IZMENI_LOKALITET:{
                izmeniLokalitet(request,response);
                break;
            }
            case Operacija.OBRISI_LOKALITET:{
                obrisiLokalitet(request,response);
                break;
            }
            case Operacija.VRATI_PROIZVOD:{
                vratiListuProizvod(request,response);
                break;
            }
            case Operacija.FILTER_PROIZVOD: {
                vratiFilterProizvod(request,response);
                break;
            }
            case Operacija.KREIRAJ_PROIZVOD:{
                kreirajProizvod(request,response);
                break;
            }
            default:
                System.out.println("Greska");
            }
            sender.send(response);
        }
    }

    private void prijava(Request request, Response response) {
        Menadzer menadzer = (Menadzer) request.getParametar();
        try {
            menadzer = Controller.getInstance().prijavi(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
        response.setOdgovor(menadzer);
    }

    private void vratiPrivilegiju(Request request, Response response) {
        Menadzer menadzer = (Menadzer) request.getParametar();
        Privilegija privilegija = null;

        try {
            privilegija = Controller.getInstance().vratiPrivilegiju(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(privilegija);
    }

    private void vratiListuPrivilegija(Request request, Response response) {
        List<Privilegija> lista = new ArrayList<>();
        try {
            lista = Controller.getInstance().vratiListuPrivilegija(lista);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(lista);
    }

    private void izmeniMenadzera(Request request, Response response) {
        Menadzer menadzer = (Menadzer) request.getParametar();
        boolean uspesno = false;
        try {
            uspesno = Controller.getInstance().izmeniMenadzera(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(uspesno);
    }

    private void ugasiNalog(Request request, Response response) {
        Menadzer menadzer = (Menadzer)request.getParametar();
        boolean uspesno = false;
        
        try {
            uspesno = Controller.getInstance().ugasiNalog(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.setOdgovor(uspesno);
    }

    private void vratiListuZaposlenih(Request request, Response response) {
        List<Menadzer> zaposleni = new ArrayList<>();
        
        try {
            zaposleni = Controller.getInstance().vratiListuZaposlenih(zaposleni);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.setOdgovor(zaposleni);
    }

    private void vratiFilterZaposleni(Request request, Response response) {
        List<Menadzer> zaposleni = new ArrayList<>();
        Menadzer menadzer = (Menadzer)request.getParametar();
        try {
            zaposleni = Controller.getInstance().vratiFilterZaposleni(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.setOdgovor(zaposleni);
    }

    private void vratiListuLokaliteta(Request request, Response response) {
        List<Lokalitet> lokaliteti = new ArrayList<>();
        try {
            lokaliteti = Controller.getInstance().vratiListuLokaliteta(new Lokalitet());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(lokaliteti);
    }

    private void vratiFilterLokalitet(Request request, Response response) {
        List<Lokalitet> lokaliteti = new ArrayList<>();
        Lokalitet lokalitet = (Lokalitet)request.getParametar();
        try {
            lokaliteti = Controller.getInstance().vratiFilterLokalitet(lokalitet);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(lokaliteti);
    }

    private void kreirajLokalitet(Request request, Response response) {
        Lokalitet lokalitet = (Lokalitet)request.getParametar();
        
        try {
            Controller.getInstance().kreirajLokalitet(lokalitet);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void izmeniLokalitet(Request request, Response response) {
        Lokalitet lokalitet = (Lokalitet)request.getParametar();
        
        try {
            Controller.getInstance().izmeniLokalitet(lokalitet);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void obrisiLokalitet(Request request, Response response) {
        Lokalitet lokalitet = (Lokalitet)request.getParametar();
        
        try {
            Controller.getInstance().obrisiLokalitet(lokalitet);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
        
    }

    private void vratiListuProizvod(Request request, Response response) {
        List<Proizvod> proizvodi = new ArrayList<>();
        
        try {
            proizvodi = Controller.getInstance().vratiListuProizvod(new Proizvod());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(proizvodi);
    }

    private void vratiFilterProizvod(Request request, Response response) {
        List<Proizvod> proizvodi = new ArrayList<>();
        Proizvod proizvod = (Proizvod)request.getParametar();
        try {
            proizvodi = Controller.getInstance().vratiFilterProizvod(proizvod);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(proizvodi);
     }

    private void kreirajProizvod(Request request, Response response) {
        Proizvod proizvod = (Proizvod)request.getParametar();
        try {
            Controller.getInstance().kreirajProizvod(proizvod);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

}
