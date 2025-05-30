/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeProizvod;

import java.util.ArrayList;
import java.util.List;
import model.Proizvod;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class UcitajProizvod extends ApstraktnaGenerickaOperacija{
    
    List<Proizvod> proizvodi = new ArrayList<>();

    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        proizvodi = broker.read(new Proizvod());
    }
    
}
