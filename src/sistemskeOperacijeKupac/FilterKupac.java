/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeKupac;

import java.util.ArrayList;
import java.util.List;
import model.Kupac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class FilterKupac extends ApstraktnaGenerickaOperacija{
    
    private List<Kupac> kupci = new ArrayList<>();

    public List<Kupac> getKupci() {
        return kupci;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        kupci = broker.readWithCondition((Kupac)obj);
    }
    
}
