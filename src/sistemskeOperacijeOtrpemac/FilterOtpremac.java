/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeOtrpemac;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SpringLayout;
import model.Otpremac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class FilterOtpremac extends ApstraktnaGenerickaOperacija{
    
    private List<Otpremac> otpremaci = new ArrayList<>();

    public List<Otpremac> getOtpremaci() {
        return otpremaci;
    }
    

    @Override
    protected void preduslovi(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {

        brokerSpecific.readWithConditionOtpremacLokalitet((Otpremac)obj,otpremaci); 
    }
    
}
