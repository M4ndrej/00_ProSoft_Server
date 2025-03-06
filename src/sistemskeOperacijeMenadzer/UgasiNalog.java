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
public class UgasiNalog extends ApstraktnaGenerickaOperacija{
    
    private boolean ugasenNalog;
    
    public boolean getUgasenNalog(){
        return ugasenNalog;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        ugasenNalog = broker.delete((Menadzer)obj);
    }
    
}
