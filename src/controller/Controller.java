/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import email.Email;
import java.util.List;
import javax.mail.MessagingException;
import model.Kupac;
import model.Lokalitet;
import model.Menadzer;
import model.MenadzerPrivilegija;
import model.Otpremac;
import model.Otpremnica;
import model.Privilegija;
import model.Proizvod;
import model.StavkaOtpremnice;
import sistemskeOperacijeKupac.FilterKupac;
import sistemskeOperacijeKupac.IzmeniKupac;
import sistemskeOperacijeKupac.KreirajKupac;
import sistemskeOperacijeKupac.ObrisiKupac;
import sistemskeOperacijeKupac.UcitajKupac;
import sistemskeOperacijeLokalitet.FiltrirajLokalitet;
import sistemskeOperacijeLokalitet.IzmeniLokalitet;
import sistemskeOperacijeLokalitet.KreirajLokalitet;
import sistemskeOperacijeLokalitet.ObrisiLokalitet;
import sistemskeOperacijeLokalitet.UcitajLokalitet;
import sistemskeOperacijeMenadzer.FiltrirajZaposleni;
import sistemskeOperacijeMenadzer.IzmeniMenadzer;
import sistemskeOperacijeMenadzer.KreirajMenadzer;
import sistemskeOperacijeMenadzer.PrijaviMenadzer;
import sistemskeOperacijeMenadzer.UcitajZaposleni;
import sistemskeOperacijeMenadzer.UgasiNalog;
import sistemskeOperacijeOtpremnica.FilterOtpremnica;
import sistemskeOperacijeOtpremnica.IzmeniOtpremnica;
import sistemskeOperacijeOtpremnica.KreirajOtpremnica;
import sistemskeOperacijeOtpremnica.UcitajOtpremnica;
import sistemskeOperacijeOtrpemac.FilterOtpremac;
import sistemskeOperacijeOtrpemac.IzmeniOtpremac;
import sistemskeOperacijeOtrpemac.KreirajOtpremac;
import sistemskeOperacijeOtrpemac.ObrisiOtpremac;
import sistemskeOperacijeOtrpemac.UcitajOtpremac;
import sistemskeOperacijePrivilegija.KreirajMenadzerPrivilegija;
import sistemskeOperacijePrivilegija.VratiListuPrivilegija;
import sistemskeOperacijePrivilegija.VratiPrivilegiju;
import sistemskeOperacijeProizvod.FilterProizvod;
import sistemskeOperacijeProizvod.IzmeniProizvod;
import sistemskeOperacijeProizvod.KreirajProizvod;
import sistemskeOperacijeProizvod.ObrisiProizvod;
import sistemskeOperacijeProizvod.UcitajProizvod;
import sistemskeOperacijeStatistika.UcitajAnalize;
import sistemskeOperacijeStatistika.UcitajDoznaka;
import sistemskeOperacijeStavkaOtpremnice.KreirajStavkaOtpremnice;
import sistemskeOperacijeStavkaOtpremnice.ObrisiStavkaOtpremnice;
import sistemskeOperacijeStavkaOtpremnice.UcitajStavkaOtpremnice;

/**
 *
 * @author Andrej
 */
public class Controller {

    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {

    }

    public Menadzer prijavi(Menadzer menadzer) throws Exception {
        PrijaviMenadzer operacija = new PrijaviMenadzer();
        operacija.izvrsi(menadzer);
        return operacija.getUlogovani();
    }

    public Privilegija vratiPrivilegiju(Menadzer menadzer) throws Exception {
        VratiPrivilegiju operacija = new VratiPrivilegiju();
        operacija.izvrsi(menadzer);
        return operacija.getPrivilegija();
    }

    public List<Privilegija> vratiListuPrivilegija(Privilegija lista) throws Exception {
        VratiListuPrivilegija operacija = new VratiListuPrivilegija();
        operacija.izvrsi(lista);
        return operacija.getLista();
    }

    public void izmeniMenadzera(Menadzer menadzer) throws Exception {
        IzmeniMenadzer operacija = new IzmeniMenadzer();
        operacija.izvrsi(menadzer);
    }

    public boolean ugasiNalog(Menadzer menadzer) throws Exception {
        UgasiNalog operacija = new UgasiNalog();
        operacija.izvrsi(menadzer);
        return operacija.getUgasenNalog();
    }

    public List<Menadzer> vratiListuZaposlenih(List<Menadzer> zaposleni) throws Exception {
        UcitajZaposleni operacija = new UcitajZaposleni();
        operacija.izvrsi(zaposleni);
        return operacija.getZaposleni();

    }

    public List<Menadzer> vratiFilterZaposleni(Menadzer menadzer) throws Exception {
        FiltrirajZaposleni operacija = new FiltrirajZaposleni();
        operacija.izvrsi(menadzer);
        return operacija.getZaposleni();
    }

    public List<Lokalitet> vratiListuLokaliteta(Lokalitet lokalitet) throws Exception {
        UcitajLokalitet operacija= new UcitajLokalitet();
        operacija.izvrsi(lokalitet);
        return operacija.getLokaliteti();
    }

    public List<Lokalitet> vratiFilterLokalitet(Lokalitet lokalitet) throws Exception {
        FiltrirajLokalitet operacija = new FiltrirajLokalitet();
        operacija.izvrsi(lokalitet);
        return operacija.getLokaliteti();
    }

    public void kreirajLokalitet(Lokalitet lokalitet) throws Exception {
        KreirajLokalitet operacija = new KreirajLokalitet();
        operacija.izvrsi(lokalitet);
        
    }

    public void izmeniLokalitet(Lokalitet lokalitet) throws Exception {
        IzmeniLokalitet operacija = new IzmeniLokalitet();
        operacija.izvrsi(lokalitet);
    }

    public void obrisiLokalitet(Lokalitet lokalitet) throws Exception {
        ObrisiLokalitet operacija = new ObrisiLokalitet();
        operacija.izvrsi(lokalitet);
    }

    public List<Proizvod> vratiListuProizvod(Proizvod proizvod) throws Exception {
        UcitajProizvod operacija = new UcitajProizvod();
        operacija.izvrsi(proizvod);
        return operacija.getProizvodi();
    }

    public List<Proizvod> vratiFilterProizvod(Proizvod proizvod) throws Exception {
        FilterProizvod operacija = new FilterProizvod();
        operacija.izvrsi(proizvod);
        return operacija.getProizvodi();
    }

    public void kreirajProizvod(Proizvod proizvod) throws Exception {
        KreirajProizvod operacija = new KreirajProizvod();
        operacija.izvrsi(proizvod);
    }

    public void izmeniProizvod(Proizvod proizvod) throws Exception {
        IzmeniProizvod operacija = new IzmeniProizvod();
        operacija.izvrsi(proizvod);
    }

    public void obrisiProizvod(Proizvod proizvod) throws Exception {
        ObrisiProizvod operacija = new ObrisiProizvod();
        operacija.izvrsi(proizvod);
    }

    public List<Otpremac> vratiListuOtpremac(Otpremac otpremac) throws Exception {
        UcitajOtpremac operacija = new UcitajOtpremac();
        operacija.izvrsi(otpremac);
        return operacija.getOtpremaci();
    }

    public List<Otpremac> vratiFilterOtpremac(Otpremac otpremac) throws Exception {
        FilterOtpremac operacija = new FilterOtpremac();
        operacija.izvrsi(otpremac);
        return operacija.getOtpremaci();
    }

    public void izmeniOtpremac(Otpremac otpremac) throws Exception {
        IzmeniOtpremac operacija = new IzmeniOtpremac();
        operacija.izvrsi(otpremac);
    }

    public void kreirajOtpremac(Otpremac otpremac) throws Exception {
        KreirajOtpremac operacija = new KreirajOtpremac();
        operacija.izvrsi(otpremac);
    }

    public void obrisiOtpremac(Otpremac otpremac) throws Exception {
        ObrisiOtpremac operacija = new ObrisiOtpremac();
        operacija.izvrsi(otpremac);
    }

    public List<Otpremnica> vratiListuOtpremnica(Otpremnica otpremnica) throws Exception {
        UcitajOtpremnica operacija = new UcitajOtpremnica();
        operacija.izvrsi(new Otpremnica());
        return operacija.getOtpremnice();
    }

    public List<Otpremnica> vratiFilterOtpremnica(Otpremnica otpremnica) throws Exception {
        FilterOtpremnica operacija = new FilterOtpremnica();
        operacija.izvrsi(otpremnica);
        return operacija.getOtpremnice();
    }

    public List<Kupac> vratiListuKupac(Kupac kupac) throws Exception {
        UcitajKupac operacija  = new UcitajKupac();
        operacija.izvrsi(kupac);
        return operacija.getKupci();
    }

    public List<Kupac> vratiFilterKupac(Kupac kupac) throws Exception {
        FilterKupac operacija = new FilterKupac();
        operacija.izvrsi(kupac);
        return operacija.getKupci();
    }

    public void kreirajOtpremnica(Otpremnica otpremnica) throws Exception {
        KreirajOtpremnica operacija = new KreirajOtpremnica();
        operacija.izvrsi(otpremnica);
    }

    public List<StavkaOtpremnice> vratiListuStavkaOtpremnice(Otpremnica otpremnica) throws Exception {
        UcitajStavkaOtpremnice operacija = new UcitajStavkaOtpremnice();
        operacija.izvrsi(otpremnica);
        return operacija.getStavke();
    }

    public void izmeniOtpremnica(Otpremnica otpremnica) throws Exception {
        IzmeniOtpremnica operacija = new IzmeniOtpremnica();
        operacija.izvrsi(otpremnica);
    }

    public void obrisiStavkaOtpremnice(StavkaOtpremnice stavka) throws Exception {
        ObrisiStavkaOtpremnice operacija = new ObrisiStavkaOtpremnice();
        operacija.izvrsi(stavka);
    }

    public void kreirajStavkaOtpremnice(StavkaOtpremnice stavka) throws Exception {
        KreirajStavkaOtpremnice operacija = new KreirajStavkaOtpremnice();
        operacija.izvrsi(stavka);
    }

    public void izmeniKupca(Kupac kupac) throws Exception {
        IzmeniKupac operacija = new IzmeniKupac();
        operacija.izvrsi(kupac);
    }

    public void kreirajKupca(Kupac kupac) throws Exception {
        KreirajKupac operacija = new KreirajKupac();
        operacija.izvrsi(kupac);
    }

    public void obrisiKupca(Kupac kupac) throws Exception {
        ObrisiKupac operacija = new ObrisiKupac();
        operacija.izvrsi(kupac);
    }

    public List<Object[]> vratiListuDoznaka(Object object) throws Exception {
        UcitajDoznaka operacija = new UcitajDoznaka();
        operacija.izvrsi(object);
        return operacija.getDoznake();
    }

    public List<Object[]> analize(Object[] object) {
        UcitajAnalize operacija = new UcitajAnalize();
        StavkaOtpremnice so = (StavkaOtpremnice)object[0];
        String k1 = (String)object[1];
        String k2 = (String)object[2];
        operacija.analiziraj(so,k1,k2);
        return operacija.getPodaci();
    }

    public void kreirajMenadzera(Menadzer menadzer) throws Exception {
        KreirajMenadzer operacija = new KreirajMenadzer();
        operacija.izvrsi(menadzer);
        
    }

    public void kreirajMenadzerPrivilegiju(MenadzerPrivilegija menadzerPrivilegija) throws Exception {
        KreirajMenadzerPrivilegija operacija = new KreirajMenadzerPrivilegija();
        operacija.izvrsi(menadzerPrivilegija);
    }

    public void autentifikuj(String email, String valueOf) throws MessagingException {
        Email.autentifikacija(email, valueOf);
    }

    public void promenaDoznake(double staraDoznaka, double novaDoznaka, Menadzer menadzer, Lokalitet lokalitet) throws MessagingException {
        Email.promeniDoznaku(staraDoznaka, novaDoznaka, menadzer, lokalitet);
    }
    
}
