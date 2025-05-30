/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijePrivilegija;

import java.util.List;
import model.Menadzer;
import model.MenadzerPrivilegija;
import model.OpstiDomenskiObjekat;
import model.Privilegija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class VratiPrivilegiju extends ApstraktnaGenerickaOperacija{
    
    private Privilegija privilegija;
    
    public Privilegija getPrivilegija(){
        return privilegija;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        Menadzer menadzer = (Menadzer)obj;
        List<OpstiDomenskiObjekat> lista = brokerSpecific.readMenadzerPrivilegijaWithPrivilegijaMenadzer(new MenadzerPrivilegija());
        for(OpstiDomenskiObjekat o: lista){
            MenadzerPrivilegija mp = (MenadzerPrivilegija)o;
            if(mp.getMenadzer().equals(menadzer)){
                privilegija = mp.getPrivilegija();
            }
        }
        
        
    }
    
}
