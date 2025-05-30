/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeOtpremnica;

import java.util.ArrayList;
import java.util.List;
import model.Otpremnica;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class FilterOtpremnica extends ApstraktnaGenerickaOperacija{
    
    private List<Otpremnica> otpremnice = new ArrayList<>();

    public List<Otpremnica> getOtpremnice() {
        return otpremnice;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        
        brokerSpecific.readWithConditionOtpremnicaKupacOtpremac((Otpremnica)obj,otpremnice);
    }
    
}
