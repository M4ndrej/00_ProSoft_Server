/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeProizvod;

import model.Proizvod;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class IzmeniProizvod extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object obj) throws Exception {
        boolean uspesno = broker.existsInDb((Proizvod) obj);
        if (uspesno) {
            throw new Exception();
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        broker.update((Proizvod)obj);
    }
    
}
