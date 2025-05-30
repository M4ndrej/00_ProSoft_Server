/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeMenadzer;

import java.util.List;
import model.Menadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class PrijaviMenadzer extends ApstraktnaGenerickaOperacija {
    
    private Menadzer ulogovani;

    public Menadzer getUlogovani() {
        return ulogovani;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        List<Menadzer> menadzeri = broker.read((Menadzer)obj);
        for(Menadzer m: menadzeri){
            if(m.getJmbg().equals(((Menadzer)obj).getJmbg()) && m.getLozinka().equals(((Menadzer)obj).getLozinka())){
                ulogovani = m;
                return;
            }
        }
        ulogovani = null;
    }

}
