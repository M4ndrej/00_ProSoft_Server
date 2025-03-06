/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeMenadzer;

import database.Konekcija;
import java.util.ArrayList;
import java.util.List;
import model.Menadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class UcitajZaposleni extends ApstraktnaGenerickaOperacija{
    
    private List<Menadzer> lista = new ArrayList<>();
    
    public List<Menadzer> getZaposleni(){
        return lista;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        lista = broker.read(new Menadzer());
    }
    
}
