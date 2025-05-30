/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijeStatistika;

import java.util.ArrayList;
import java.util.List;
import model.StavkaOtpremnice;
import statistika.Statistika;

/**
 *
 * @author Andrej
 */
public class UcitajAnalize {

    private final List<Object[]> podaci = new ArrayList<>();

    public List<Object[]> getPodaci() {
        return podaci;
    }

    public void analiziraj(StavkaOtpremnice so, String prosekUkupno, String cenaKolicina) {
        Statistika statistika = new Statistika();
        statistika.analize(so, prosekUkupno, cenaKolicina, podaci);
    }

}
