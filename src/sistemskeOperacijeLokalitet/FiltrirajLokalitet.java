/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeLokalitet;

import java.util.ArrayList;
import java.util.List;
import model.Lokalitet;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class FiltrirajLokalitet extends ApstraktnaGenerickaOperacija{
    
    private List<Lokalitet> lokaliteti = new ArrayList<>();

    public List<Lokalitet> getLokaliteti() {
        return lokaliteti;
    }
    
    

    @Override
    protected void preduslovi(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        lokaliteti = broker.readWithCondition((Lokalitet)obj);
    }
    
}
