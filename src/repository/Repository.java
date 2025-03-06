/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.MenadzerPrivilegija;
import model.Otpremac;
import model.Otpremnica;

/**
 *
 * @author Andrej
 */
public interface Repository<T> {

    public List<T> read(T t);

    public boolean readOtpremacWithLokalitet(List<T> t);

    public boolean readOtpremnicaWithMenadzerOtpremacKupac(List<T> t);

    public boolean readStavkeOtpremniceForOtpremnica(List<T> t, Otpremnica otpremnica);

    public boolean create(T t);

    public boolean update(T t);

    public boolean delete(T t);

    public List<T> readWithCondition(T t);

    public boolean readWithConditionOtpremacLokalitet(Otpremac otpremac, List<Otpremac> lista);

    public boolean readWithConditionOtpremnicaKupacOtpremac(Otpremnica otpremnica, List<Otpremnica> lista);

    public boolean readWithConditionDoznakaOtprema(List<Object[]> podaci);

    public List<T> readMenadzerPrivilegijaWithPrivilegijaMenadzer(MenadzerPrivilegija menadzerPrivilegija);

}
