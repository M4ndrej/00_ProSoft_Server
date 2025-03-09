/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Lokalitet;
import model.Menadzer;
import model.Privilegija;
import model.Proizvod;
import sistemskeOperacijeLokalitet.FiltrirajLokalitet;
import sistemskeOperacijeLokalitet.IzmeniLokalitet;
import sistemskeOperacijeLokalitet.KreirajLokalitet;
import sistemskeOperacijeLokalitet.ObrisiLokalitet;
import sistemskeOperacijeLokalitet.UcitajLokalitet;
import sistemskeOperacijeMenadzer.FiltrirajZaposleni;
import sistemskeOperacijeMenadzer.IzmeniMenadzer;
import sistemskeOperacijeMenadzer.PrijaviMenadzer;
import sistemskeOperacijeMenadzer.UcitajZaposleni;
import sistemskeOperacijeMenadzer.UgasiNalog;
import sistemskeOperacijePrivilegija.VratiListuPrivilegija;
import sistemskeOperacijePrivilegija.VratiPrivilegiju;
import sistemskeOperacijeProizvod.FilterProizvod;
import sistemskeOperacijeProizvod.KreirajProizvod;
import sistemskeOperacijeProizvod.UcitajProizvod;

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

    public List<Privilegija> vratiListuPrivilegija(List<Privilegija> lista) throws Exception {
        VratiListuPrivilegija operacija = new VratiListuPrivilegija();
        operacija.izvrsi(lista);
        return operacija.getLista();
    }

    public boolean izmeniMenadzera(Menadzer menadzer) throws Exception {
        IzmeniMenadzer operacija = new IzmeniMenadzer();
        operacija.izvrsi(menadzer);
        return operacija.getIzmenjenMenadzer();
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
    
}
