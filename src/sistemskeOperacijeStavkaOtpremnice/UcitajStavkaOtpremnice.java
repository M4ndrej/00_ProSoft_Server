/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeStavkaOtpremnice;

import java.util.ArrayList;
import java.util.List;
import model.Otpremnica;
import model.StavkaOtpremnice;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class UcitajStavkaOtpremnice extends ApstraktnaGenerickaOperacija{
    
    private List<StavkaOtpremnice> stavke = new ArrayList<>();

    public List<StavkaOtpremnice> getStavke() {
        return stavke;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        brokerSpecific.readStavkeOtpremniceForOtpremnica(stavke, (Otpremnica)obj);
    }
    
}
