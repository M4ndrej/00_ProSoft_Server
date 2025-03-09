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
public class IzmeniLokalitet extends ApstraktnaGenerickaOperacija{
    
    

    @Override
    protected void preduslovi(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        broker.update((Lokalitet)obj);
    }
    
}
