/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Menadzer;
import model.Privilegija;
import sistemskeOperacijeMenadzer.IzmeniMenadzer;
import sistemskeOperacijeMenadzer.PrijaviMenadzer;
import sistemskeOperacijeMenadzer.UcitajZaposleni;
import sistemskeOperacijeMenadzer.UgasiNalog;
import sistemskeOperacijePrivilegija.VratiListuPrivilegija;
import sistemskeOperacijePrivilegija.VratiPrivilegiju;

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

}
