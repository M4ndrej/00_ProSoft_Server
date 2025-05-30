/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeStavkaOtpremnice;

import data_export.DataExport;
import model.StavkaOtpremnice;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class KreirajStavkaOtpremnice extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object obj) throws Exception {
        boolean uspesno = broker.existsInDb((StavkaOtpremnice) obj);
        if (uspesno) {
            throw new Exception();
        }
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        broker.create((StavkaOtpremnice) obj);
        DataExport de = new DataExport();
        de.dodajPodatkeUExcel((StavkaOtpremnice)obj);
    }

}
