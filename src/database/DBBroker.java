/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OpstiDomenskiObjekat;
import java.sql.ResultSet;
import java.util.Map;
import model.Kupac;
import model.Lokalitet;
import model.Menadzer;
import model.MenadzerPrivilegija;
import model.Otpremac;
import model.Otpremnica;
import model.Privilegija;
import model.Proizvod;
import model.StavkaOtpremnice;

/**
 *
 * @author Andrej
 */
public class DBBroker {

    private Konekcija konekcija = Konekcija.getInstance();
    private Statement statement;

    public List<OpstiDomenskiObjekat> read(OpstiDomenskiObjekat odo) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM " + odo.vratiImeKlase();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                OpstiDomenskiObjekat noviObjekat;
                try {
                    noviObjekat = odo.getClass().getDeclaredConstructor().newInstance();
                    if (noviObjekat.napuni(rs)) {
                        lista.add(noviObjekat);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean readOtpremacWithLokalitet(List<Otpremac> lista) {
        try {
            String upit = "SELECT * FROM otpremac o JOIN lokalitet l ON o.lokalitet = l.id";
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Lokalitet lokalitet = new Lokalitet();
                lokalitet.napuni(rs);
                Otpremac otpremac = new Otpremac();
                otpremac.napuni(rs);
                otpremac.setLokalitet(lokalitet);
                lista.add(otpremac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    public boolean readOtpremnicaWithMenadzerOtpremacKupac(List<Otpremnica> lista) {
        try {
            String upit = "SELECT * FROM otpremnica otp JOIN menadzer m ON otp.menadzer = m.jmbgMenadzer "
                    + "JOIN otpremac o ON otp.otpremac = o.jmbgOtpremac "
                    + "JOIN kupac k ON otp.kupac = k.id";
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
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
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean readStavkeOtpremniceForOtpremnica(List<StavkaOtpremnice> lista, Otpremnica otpremnica) {
        try {
            String upit = "SELECT * FROM  otpremnica o JOIN stavka_otpremnice s ON s.otpremnica = o.brojOtpremnice "
                    + "JOIN proizvod p ON s.proizvod = p.id WHERE o.brojOtpremnice = " + otpremnica.getBrojOtpremnice();
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Proizvod proizvod = new Proizvod();
                proizvod.napuni(rs);
                StavkaOtpremnice stavka = new StavkaOtpremnice();
                stavka.napuni(rs);
                stavka.setProizvod(proizvod);
                stavka.setOtpremnica(otpremnica);
                lista.add(stavka);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean create(OpstiDomenskiObjekat odo) {
        int affectedRows = 0;
        try {
            String upit = "INSERT INTO " + odo.vratiImeKlase() + " " + odo.vratiNaziveKolona() + " VALUES " + odo.vratiVrednostiKolona();
            statement = konekcija.getConnection().createStatement();
            affectedRows = statement.executeUpdate(upit);
            konekcija.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return (affectedRows > 0);
    }

    public boolean update(OpstiDomenskiObjekat odo) {
        int affectedRows = 0;
        try {
            String naziviKolona = odo.vratiNaziveKolona().replace("(", "").replace(")", "");
            String nazivVrednosti = odo.vratiVrednostiKolona().replace("(", "").replace(")", "");
            String[] kolone = naziviKolona.split(",");
            String[] vrednosti = nazivVrednosti.split(",");

            StringBuilder set = new StringBuilder();
            for (int i = 0; i < kolone.length; i++) {
                set.append(kolone[i]).append(" = ").append(vrednosti[i]).append(", ");
            }
            set.setLength(set.length() - 2);

            String upit = "UPDATE " + odo.vratiImeKlase() + " SET " + set + " WHERE " + odo.vratiUslovNadjiSlog();
            statement = konekcija.getConnection().createStatement();
            affectedRows = statement.executeUpdate(upit);
            konekcija.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return (affectedRows > 0);
    }

    public boolean delete(OpstiDomenskiObjekat odo) {
        int affectedRows = 0;
        try {
            String upit = "DELETE FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovNadjiSlog();
            statement = konekcija.getConnection().createStatement();
            affectedRows = statement.executeUpdate(upit);
            konekcija.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return (affectedRows > 0);
    }

    public List<OpstiDomenskiObjekat> readWithCondition(OpstiDomenskiObjekat odo) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovNadjiSlogove();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                OpstiDomenskiObjekat noviObjekat;
                try {
                    noviObjekat = odo.getClass().getDeclaredConstructor().newInstance();
                    if (noviObjekat.napuni(rs)) {
                        lista.add(noviObjekat);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lista;
    }

    public boolean readWithConditionOtpremacLokalitet(Otpremac otpremac, List<Otpremac> lista) {
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM otpremac o JOIN lokalitet l ON o.lokalitet = l.id WHERE " + otpremac.vratiUslovNadjiSlogove();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Otpremac o = new Otpremac();
                Lokalitet l = new Lokalitet();
                if (o.napuni(rs) && l.napuni(rs)) {
                    o.setLokalitet(l);
                    lista.add(o);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean readWithConditionOtpremnicaKupacOtpremac(Otpremnica otpremnica, List<Otpremnica> lista) {
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM otpremnica o JOIN kupac k ON o.kupac = k.id JOIN otpremac otp ON o.otpremac = otp.jmbgOtpremac JOIN menadzer m ON o.menadzer = m.jmbgMenadzer WHERE " + otpremnica.vratiUslovNadjiSlogove();
            ResultSet rs = statement.executeQuery(upit);
            System.out.println(upit);
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
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean readWithConditionDoznakaOtprema(List<Object[]> podaci) {
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT l.jedinicaGazdinstva,l.odsekOdeljenje,l.doznaka,l.doznaka, COALESCE(SUM(so.kolicina),0) AS otprema FROM lokalitet l "
                    + "LEFT JOIN otpremac otp ON l.id = otp.lokalitet "
                    + "LEFT JOIN otpremnica o ON otp.jmbgOtpremac = o.otpremac "
                    + "LEFT JOIN stavka_otpremnice so ON o.brojOtpremnice = so.otpremnica "
                    + "GROUP BY l.id";
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                String lokalitet = rs.getString("jedinicaGazdinstva");
                String odsek = rs.getString("odsekOdeljenje");
                double doznaka = rs.getDouble("doznaka");
                double otprema = rs.getDouble("otprema");
                podaci.add(new Object[]{lokalitet, odsek, doznaka, otprema});
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    

    public List<OpstiDomenskiObjekat> readMenadzerPrivilegijaWithPrivilegijaMenadzer(MenadzerPrivilegija menadzerPrivilegija) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            String upit = "SELECT * FROM menadzer_privilegija mp JOIN menadzer m ON mp.menadzer = m.jmbgMenadzer "
                    + "JOIN privilegija p ON mp.privilegija = p.id ";
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
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
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
