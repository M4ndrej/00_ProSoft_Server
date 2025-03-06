/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeMenadzer;

import model.Menadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class IzmeniMenadzer extends ApstraktnaGenerickaOperacija{
    
    private boolean izmenjenMenadzer;
    
    public boolean getIzmenjenMenadzer(){
        return izmenjenMenadzer;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        izmenjenMenadzer = broker.update((Menadzer)obj);
    }
    
}
