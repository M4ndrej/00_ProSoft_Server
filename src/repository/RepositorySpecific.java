/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Menadzer;
import model.MenadzerPrivilegija;
import model.OpstiDomenskiObjekat;
import model.Otpremac;
import model.Otpremnica;
import model.StavkaOtpremnice;

/**
 *
 * @author andrej-maksimovic
 */
public interface RepositorySpecific {
    public boolean readOtpremacWithLokalitet(List<Otpremac> lista);

    public boolean readOtpremnicaWithMenadzerOtpremacKupac(List<Otpremnica> lista);

    public boolean readStavkeOtpremniceForOtpremnica(List<StavkaOtpremnice> lista, Otpremnica otpremnica);
    
     public boolean readWithConditionOtpremacLokalitet(Otpremac otpremac, List<Otpremac> lista);

    public boolean readWithConditionOtpremnicaKupacOtpremac(Otpremnica otpremnica, List<Otpremnica> lista);

    public boolean readWithConditionDoznakaOtprema(List<Object[]> podaci);

    public List<OpstiDomenskiObjekat> readMenadzerPrivilegijaWithPrivilegijaMenadzer(MenadzerPrivilegija menadzerPrivilegija);
    
    public boolean prijavi(Menadzer menadzer);
}
