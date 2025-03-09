/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeMenadzer;

import java.util.ArrayList;
import java.util.List;
import model.Menadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class FiltrirajZaposleni extends ApstraktnaGenerickaOperacija{
    
    private List<Menadzer> zaposleni = new ArrayList<>();

    public List<Menadzer> getZaposleni() {
        return zaposleni;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        zaposleni = broker.readWithCondition((Menadzer)obj);
    }
    
}
