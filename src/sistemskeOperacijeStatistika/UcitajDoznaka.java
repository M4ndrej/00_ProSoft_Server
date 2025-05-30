/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeStatistika;

import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class UcitajDoznaka extends ApstraktnaGenerickaOperacija{
    
    private List<Object[]> doznake = new ArrayList<>();

    public List<Object[]> getDoznake() {
        return doznake;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        brokerSpecific.readWithConditionDoznakaOtprema(doznake);
    }
    
}
