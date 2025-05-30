/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.implementation;

import database.DBBroker;
import database.Konekcija;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Kupac;
import model.Lokalitet;
import model.Menadzer;
import model.MenadzerPrivilegija;
import model.OpstiDomenskiObjekat;
import model.Otpremac;
import model.Otpremnica;
import model.Privilegija;
import model.Proizvod;
import model.StavkaOtpremnice;
import repository.RepositorySpecific;

/**
 *
 * @author andrej-maksimovic
 */
public class DbRepositorySpecific implements RepositorySpecific{
    @Override
    public boolean readOtpremacWithLokalitet(List<Otpremac> lista) {
        String upit = "SELECT * FROM otpremac o JOIN lokalitet l ON o.lokalitet = l.id";

        try (
                Statement statement = Konekcija.getInstance().getConnection().createStatement(); ResultSet rs = statement.executeQuery(upit)) {
            while (rs.next()) {
                Lokalitet lokalitet = new Lokalitet();
                lokalitet.napuni(rs);
                Otpremac otpremac = new Otpremac();
                otpremac.napuni(rs);
                otpremac.setLokalitet(lokalitet);
                lista.add(otpremac);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean readOtpremnicaWithMenadzerOtpremacKupac(List<Otpremnica> lista) {
        String upit = "SELECT * FROM otpremnica otp JOIN menadzer m ON otp.menadzer = m.jmbgMenadzer "
                + "JOIN otpremac o ON otp.otpremac = o.jmbgOtpremac "
                + "JOIN kupac k ON otp.kupac = k.id";

        try (
                Statement statement = Konekcija.getInstance().getConnection().createStatement(); ResultSet rs = statement.executeQuery(upit)) {
            while (rs.next()) {
                Menadzer menadzer = new Menadzer();
                menadzer.napuni(rs);
                Otpremac otpremac = new Otpremac();
                otpremac.napuni(rs);
                Kupac kupac = new Kupac();
                kupac.napuni(rs);
                Otpremnica otpremnica = new Otpremnica();
                otpremnica.napuni(rs);
                otpremnica.setMenadzer(menadzer);
                otpremnica.setOtpremac(otpremac);
                otpremnica.setKupac(kupac);
                lista.add(otpremnica);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean readStavkeOtpremniceForOtpremnica(List<StavkaOtpremnice> lista, Otpremnica otpremnica) {
        String upit = "SELECT * FROM otpremnica o "
                + "JOIN stavka_otpremnice s ON s.otpremnica = o.brojOtpremnice "
                + "JOIN proizvod p ON s.proizvod = p.id "
                + "WHERE o.brojOtpremnice = " + otpremnica.getBrojOtpremnice();

        try (
                Statement statement = Konekcija.getInstance().getConnection().createStatement(); ResultSet rs = statement.executeQuery(upit)) {
            while (rs.next()) {
                Proizvod proizvod = new Proizvod();
                proizvod.napuni(rs);
                StavkaOtpremnice stavka = new StavkaOtpremnice();
                stavka.napuni(rs);
                stavka.setProizvod(proizvod);
                stavka.setOtpremnica(otpremnica);
                lista.add(stavka);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean readWithConditionOtpremacLokalitet(Otpremac otpremac, List<Otpremac> lista) {
        try (Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            String upit = "SELECT * FROM otpremac o JOIN lokalitet l ON o.lokalitet = l.id WHERE " + otpremac.vratiUslovNadjiSlogove();
            try (ResultSet rs = statement.executeQuery(upit)) {
                while (rs.next()) {
                    Otpremac o = new Otpremac();
                    Lokalitet l = new Lokalitet();
                    if (o.napuni(rs) && l.napuni(rs)) {
                        o.setLokalitet(l);
                        lista.add(o);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean readWithConditionOtpremnicaKupacOtpremac(Otpremnica otpremnica, List<Otpremnica> lista) {
        try (Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            String upit = "SELECT * FROM otpremnica o JOIN kupac k ON o.kupac = k.id JOIN otpremac otp ON o.otpremac = otp.jmbgOtpremac JOIN menadzer m ON o.menadzer = m.jmbgMenadzer WHERE " + otpremnica.vratiUslovNadjiSlogove();
            System.out.println(upit);
            try (ResultSet rs = statement.executeQuery(upit)) {
                while (rs.next()) {
                    Otpremac otp = new Otpremac();
                    Kupac k = new Kupac();
                    Menadzer m = new Menadzer();
                    Otpremnica o = new Otpremnica();
                    if (otp.napuni(rs) && k.napuni(rs) && o.napuni(rs) && m.napuni(rs)) {
                        o.setOtpremac(otp);
                        o.setKupac(k);
                        o.setMenadzer(m);
                        lista.add(o);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean readWithConditionDoznakaOtprema(List<Object[]> podaci) {
        try (Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            String upit = "SELECT l.jedinicaGazdinstva,l.odsekOdeljenje,l.doznaka,l.doznaka, COALESCE(SUM(so.kolicina),0) AS otprema FROM lokalitet l "
                    + "LEFT JOIN otpremac otp ON l.id = otp.lokalitet "
                    + "LEFT JOIN otpremnica o ON otp.jmbgOtpremac = o.otpremac "
                    + "LEFT JOIN stavka_otpremnice so ON o.brojOtpremnice = so.otpremnica "
                    + "GROUP BY l.id";
            try (ResultSet rs = statement.executeQuery(upit)) {
                while (rs.next()) {
                    String lokalitet = rs.getString("jedinicaGazdinstva");
                    String odsek = rs.getString("odsekOdeljenje");
                    double doznaka = rs.getDouble("doznaka");
                    double otprema = rs.getDouble("otprema");
                    podaci.add(new Object[]{lokalitet, odsek, doznaka, otprema});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<OpstiDomenskiObjekat> readMenadzerPrivilegijaWithPrivilegijaMenadzer(MenadzerPrivilegija menadzerPrivilegija) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try (Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            String upit = "SELECT * FROM menadzer_privilegija mp JOIN menadzer m ON mp.menadzer = m.jmbgMenadzer "
                    + "JOIN privilegija p ON mp.privilegija = p.id ";
            try (ResultSet rs = statement.executeQuery(upit)) {
                while (rs.next()) {
                    Menadzer menadzer = new Menadzer();
                    menadzer.napuni(rs);
                    Privilegija privilegija = new Privilegija();
                    privilegija.napuni(rs);
                    MenadzerPrivilegija mp = new MenadzerPrivilegija();
                    mp.napuni(rs);
                    mp.setMenadzer(menadzer);
                    mp.setPrivilegija(privilegija);
                    lista.add(mp);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
