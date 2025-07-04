/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeLokalitet;

import model.Lokalitet;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class KreirajLokalitet extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object obj) throws Exception {
        boolean uspesno = broker.existsInDb((Lokalitet) obj);
        if (uspesno) {
            throw new Exception();
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        broker.create((Lokalitet) obj);
    }

}
