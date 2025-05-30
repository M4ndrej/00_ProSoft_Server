/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeOtrpemac;

import model.Otpremac;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class KreirajOtpremac extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object obj) throws Exception {
        boolean uspesno = broker.existsInDb((Otpremac) obj);
        if (uspesno) {
            throw new Exception();
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        broker.create((Otpremac) obj);
    }

}
