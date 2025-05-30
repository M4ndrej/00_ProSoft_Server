
package niti;

import controller.Controller;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import komunikacija.Operacija;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;
import model.Kupac;
import model.Lokalitet;
import model.Menadzer;
import model.MenadzerPrivilegija;
import model.Otpremac;
import model.Otpremnica;
import model.Privilegija;
import model.Proizvod;
import model.StavkaOtpremnice;

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
                vratiListuLokaliteta(request, response);
                break;
            }
            case Operacija.FILTER_LOKALITET: {
                vratiFilterLokalitet(request, response);
                break;
            }
            case Operacija.KREIRAJ_LOKALITET: {
                kreirajLokalitet(request, response);
                break;
            }
            case Operacija.IZMENI_LOKALITET: {
                izmeniLokalitet(request, response);
                break;
            }
            case Operacija.OBRISI_LOKALITET: {
                obrisiLokalitet(request, response);
                break;
            }
            case Operacija.VRATI_PROIZVOD: {
                vratiListuProizvod(request, response);
                break;
            }
            case Operacija.FILTER_PROIZVOD: {
                vratiFilterProizvod(request, response);
                break;
            }
            case Operacija.KREIRAJ_PROIZVOD: {
                kreirajProizvod(request, response);
                break;
            }
            case Operacija.IZMENI_PROIZVOD: {
                izmeniProizvod(request, response);
                break;
            }
            case Operacija.OBRISI_PROIZVOD: {
                obrisiProizvod(request, response);
                break;
            }
            case Operacija.VRATI_OTPREMAC: {
                vratiListuOtpremac(request, response);
                break;
            }
            case Operacija.FILTER_OTPREMAC: {
                vratiFilterOtpremac(request, response);
                break;
            }
            case Operacija.IZMENI_OTPREMAC: {
                izmeniOtpremac(request, response);
                break;
            }
            case Operacija.KREIRAJ_OTPREMAC: {
                kreirajOtpremac(request, response);
                break;
            }
            case Operacija.OBRISI_OTPREMAC: {
                obrisiOtpremac(request, response);
                break;
            }
            case Operacija.VRATI_OTPREMNICA: {
                vratiListuOtpremnica(request, response);
                break;
            }
            case Operacija.FILTER_OTPREMNICA: {
                vratiFilterOtpremnica(request, response);
                break;
            }
            case Operacija.VRATI_KUPAC: {
                vratiListuKupac(request, response);
                break;
            }
            case Operacija.FILTER_KUPAC: {
                vratiFilterKupac(request, response);
                break;
            }
            case Operacija.KREIRAJ_OTPREMNICA: {
                kreirajOtpremnica(request, response);
                break;
            }
            case Operacija.VRATI_STAVKAOTPREMNICE: {
                vratiListuStavkaOtpremnice(request, response);
                break;
            }
            case Operacija.IZMENI_OTPREMNICA: {
                izmeniOtpremnica(request, response);
                break;
            }
            case Operacija.OBRISI_STAVKAOTPREMNICE: {
                obrisiStavkaOtpremnice(request, response);
                break;
            }
            case Operacija.KREIRAJ_STAVKAOTPREMNICE: {
                kreirajStavkaOtpremnice(request, response);
                break;
            }
            case Operacija.IZMENI_KUPAC: {
                izmeniKupac(request, response);
                break;
            }
            case Operacija.KREIRAJ_KUPAC: {
                kreirajKupac(request, response);
                break;
            }
            case Operacija.OBRISI_KUPAC: {
                obrisiKupac(request, response);
                break;
            }
            case Operacija.VRATI_DOZNAKA: {
                vratiListuDoznaka(request, response);
                break;
            }
            case Operacija.ANALIZIRAJ: {
                analiziraj(request, response);
                break;
            }
            case Operacija.KREIRAJ_MENADZER: {
                kreirajMenadzer(request,response);
                break;
            }
            case Operacija.KREIRAJ_MENADZERPRIVILEGIJA: {
                kreirajMenadzerPrivilegija(request,response);
                break;
            }
            case Operacija.EMAIL: {
                email(request,response);
                break;
            }
            case Operacija.PROMENA_DOZNAKE:{
                obavestiPromenaDoznake(request,response);
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
        Privilegija privilegija = (Privilegija)request.getParametar();
        try {
            lista = Controller.getInstance().vratiListuPrivilegija(privilegija);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(lista);
    }

    private void izmeniMenadzera(Request request, Response response) {
        Menadzer menadzer = (Menadzer) request.getParametar();
        try {
            Controller.getInstance().izmeniMenadzera(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void ugasiNalog(Request request, Response response) {
        Menadzer menadzer = (Menadzer) request.getParametar();
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
        Menadzer menadzer = (Menadzer) request.getParametar();
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
        Lokalitet lokalitet = (Lokalitet) request.getParametar();
        try {
            lokaliteti = Controller.getInstance().vratiFilterLokalitet(lokalitet);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(lokaliteti);
    }

    private void kreirajLokalitet(Request request, Response response) {
        Lokalitet lokalitet = (Lokalitet) request.getParametar();

        try {
            Controller.getInstance().kreirajLokalitet(lokalitet);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void izmeniLokalitet(Request request, Response response) {
        Lokalitet lokalitet = (Lokalitet) request.getParametar();

        try {
            Controller.getInstance().izmeniLokalitet(lokalitet);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void obrisiLokalitet(Request request, Response response) {
        Lokalitet lokalitet = (Lokalitet) request.getParametar();

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
        Proizvod proizvod = (Proizvod) request.getParametar();
        try {
            proizvodi = Controller.getInstance().vratiFilterProizvod(proizvod);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(proizvodi);
    }

    private void kreirajProizvod(Request request, Response response) {
        Proizvod proizvod = (Proizvod) request.getParametar();
        try {
            Controller.getInstance().kreirajProizvod(proizvod);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void izmeniProizvod(Request request, Response response) {
        Proizvod proizvod = (Proizvod) request.getParametar();

        try {
            Controller.getInstance().izmeniProizvod(proizvod);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void obrisiProizvod(Request request, Response response) {
        Proizvod proizvod = (Proizvod) request.getParametar();

        try {
            Controller.getInstance().obrisiProizvod(proizvod);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void vratiListuOtpremac(Request request, Response response) {
        List<Otpremac> otpremaci = new ArrayList<>();

        try {
            otpremaci = Controller.getInstance().vratiListuOtpremac(new Otpremac());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(otpremaci);
    }

    private void vratiFilterOtpremac(Request request, Response response) {
        List<Otpremac> otpremaci = new ArrayList<>();
        Otpremac otpremac = (Otpremac) request.getParametar();

        try {
            otpremaci = Controller.getInstance().vratiFilterOtpremac(otpremac);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(otpremaci);
    }

    private void izmeniOtpremac(Request request, Response response) {
        Otpremac otpremac = (Otpremac) request.getParametar();

        try {
            Controller.getInstance().izmeniOtpremac(otpremac);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void kreirajOtpremac(Request request, Response response) {
        Otpremac otpremac = (Otpremac) request.getParametar();

        try {
            Controller.getInstance().kreirajOtpremac(otpremac);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void obrisiOtpremac(Request request, Response response) {
        Otpremac otpremac = (Otpremac) request.getParametar();

        try {
            Controller.getInstance().obrisiOtpremac(otpremac);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void vratiListuOtpremnica(Request request, Response response) {
        List<Otpremnica> otpremnice = new ArrayList<>();
        try {
            otpremnice = Controller.getInstance().vratiListuOtpremnica(new Otpremnica());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(otpremnice);
    }

    private void vratiFilterOtpremnica(Request request, Response response) {
        List<Otpremnica> otpremnice = new ArrayList<>();
        Otpremnica otpremnica = (Otpremnica) request.getParametar();

        try {
            otpremnice = Controller.getInstance().vratiFilterOtpremnica(otpremnica);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(otpremnice);
    }

    private void vratiListuKupac(Request request, Response response) {
        List<Kupac> kupci = new ArrayList<>();
        try {
            kupci = Controller.getInstance().vratiListuKupac(new Kupac());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(kupci);
    }

    private void vratiFilterKupac(Request request, Response response) {
        List<Kupac> kupci = new ArrayList<>();
        Kupac kupac = (Kupac) request.getParametar();
        try {
            kupci = Controller.getInstance().vratiFilterKupac(kupac);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(kupci);
    }

    private void kreirajOtpremnica(Request request, Response response) {
        Otpremnica otpremnica = (Otpremnica) request.getParametar();

        try {
            Controller.getInstance().kreirajOtpremnica(otpremnica);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void vratiListuStavkaOtpremnice(Request request, Response response) {
        List<StavkaOtpremnice> stavke = new ArrayList<>();
        Otpremnica otpremnica = (Otpremnica) request.getParametar();

        try {
            stavke = Controller.getInstance().vratiListuStavkaOtpremnice(otpremnica);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(stavke);
    }

    private void izmeniOtpremnica(Request request, Response response) {
        Otpremnica otpremnica = (Otpremnica) request.getParametar();

        try {
            Controller.getInstance().izmeniOtpremnica(otpremnica);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void obrisiStavkaOtpremnice(Request request, Response response) {
        StavkaOtpremnice stavka = (StavkaOtpremnice) request.getParametar();

        try {
            Controller.getInstance().obrisiStavkaOtpremnice(stavka);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void kreirajStavkaOtpremnice(Request request, Response response) {
        StavkaOtpremnice stavka = (StavkaOtpremnice) request.getParametar();

        try {
            Controller.getInstance().kreirajStavkaOtpremnice(stavka);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void izmeniKupac(Request request, Response response) {
        Kupac kupac = (Kupac) request.getParametar();

        try {
            Controller.getInstance().izmeniKupca(kupac);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void kreirajKupac(Request request, Response response) {
        Kupac kupac = (Kupac) request.getParametar();

        try {
            Controller.getInstance().kreirajKupca(kupac);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void obrisiKupac(Request request, Response response) {
        Kupac kupac = (Kupac) request.getParametar();

        try {
            Controller.getInstance().obrisiKupca(kupac);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void vratiListuDoznaka(Request request, Response response) {
        List<Object[]> doznake = new ArrayList<>();
        try {
            doznake = Controller.getInstance().vratiListuDoznaka(new Object());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(doznake);
    }

    private void analiziraj(Request request, Response response) {
        List<Object[]> analize;
        Object[] podaci = request.getParametri();
        analize = Controller.getInstance().analize(podaci);
        response.setOdgovor(analize);
    }

    private void kreirajMenadzer(Request request, Response response) {
        Menadzer menadzer = (Menadzer)request.getParametar();
        try {
            Controller.getInstance().kreirajMenadzera(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void kreirajMenadzerPrivilegija(Request request, Response response) {
        MenadzerPrivilegija menadzerPrivilegija = (MenadzerPrivilegija)request.getParametar();
        try {
            Controller.getInstance().kreirajMenadzerPrivilegiju(menadzerPrivilegija);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void email(Request request, Response response) {
        Object[] podaci = request.getParametri();
        String email = (String)podaci[0];
        String valueOf = (String)podaci[1];
        try {
            Controller.getInstance().autentifikuj(email,valueOf);
        } catch (MessagingException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void obavestiPromenaDoznake(Request request, Response response) {
        Object[] podaci = request.getParametri();
        double staraDoznaka = (double) podaci[0];
        double novaDoznaka = (double) podaci[1];
        Menadzer menadzer = (Menadzer) podaci[2];
        Lokalitet lokalitet = (Lokalitet) podaci[3];
        try {
            Controller.getInstance().promenaDoznake(staraDoznaka,novaDoznaka,menadzer,lokalitet);
        } catch (MessagingException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
