/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.implementation;

import database.DBBroker;
import database.Konekcija;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OpstiDomenskiObjekat;
import repository.db.DbRepository;

/**
 *
 * @author Andrej
 */
public class DbRepositoryGeneric implements DbRepository<OpstiDomenskiObjekat> {

    @Override
    public List<OpstiDomenskiObjekat> read(OpstiDomenskiObjekat odo) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + odo.vratiImeKlase();

        try (
                Statement statement = Konekcija.getInstance().getConnection().createStatement(); ResultSet rs = statement.executeQuery(upit)) {
            while (rs.next()) {
                try {
                    OpstiDomenskiObjekat noviObjekat = odo.getClass().getDeclaredConstructor().newInstance();
                    if (noviObjekat.napuni(rs)) {
                        lista.add(noviObjekat);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }


    @Override
    public boolean create(OpstiDomenskiObjekat odo) {
        String upit = "INSERT INTO " + odo.vratiImeKlase() + " "
                + odo.vratiNaziveKolona() + " VALUES " + odo.vratiVrednostiKolona();

        try (
                Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            int affectedRows = statement.executeUpdate(upit);
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(OpstiDomenskiObjekat odo) {
        System.out.println("usao");
        String naziviKolona = odo.vratiNaziveKolona().replace("(", "").replace(")", "");
        String nazivVrednosti = odo.vratiVrednostiKolona().replace("(", "").replace(")", "");
        String[] kolone = naziviKolona.split(",");
        String[] vrednosti = nazivVrednosti.split(",");

        StringBuilder set = new StringBuilder();
        for (int i = 0; i < kolone.length; i++) {
            set.append(kolone[i]).append(" = ").append(vrednosti[i]).append(", ");
        }
        set.setLength(set.length() - 2); // Uklanja poslednji zarez i razmak

        String upit = "UPDATE " + odo.vratiImeKlase() + " SET " + set + " WHERE " + odo.vratiUslovNadjiSlog();
        
        try (
                Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            int affectedRows = statement.executeUpdate(upit);
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(OpstiDomenskiObjekat odo) {
        String upit = "DELETE FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovNadjiSlog();

        try (
                Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            int affectedRows = statement.executeUpdate(upit);
            Konekcija.getInstance().getConnection().commit();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<OpstiDomenskiObjekat> readWithCondition(OpstiDomenskiObjekat odo) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovNadjiSlogove();
        System.out.println("Usao u bazu" + odo.vratiUslovNadjiSlogove());
        try (
                Statement statement = Konekcija.getInstance().getConnection().createStatement(); ResultSet rs = statement.executeQuery(upit)) {
            while (rs.next()) {
                try {
                    OpstiDomenskiObjekat noviObjekat = odo.getClass().getDeclaredConstructor().newInstance();
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

    @Override
    public boolean existsInDb(OpstiDomenskiObjekat odo) {
        
        try(Statement statement = Konekcija.getInstance().getConnection().createStatement()){
                    String upit = "SELECT * FROM "+odo.vratiImeKlase()+" WHERE "+odo.vratiUslovNadjiSlog();
                    try(ResultSet rs = statement.executeQuery(upit)){
                        return rs.next();
                    }
        }catch(SQLException ex){
            return false;
        }
    }

    @Override
    public boolean existRelation(OpstiDomenskiObjekat subjekat, OpstiDomenskiObjekat objekat) {
        try(Statement statement = Konekcija.getInstance().getConnection().createStatement()){
                    String upit = "SELECT * FROM "+objekat.vratiImeKlase()+" WHERE "+subjekat.vratiUslovObrisiSlog();
                    try(ResultSet rs = statement.executeQuery(upit)){
                        return rs.next();
                    }
        }catch(SQLException ex){
            return false;
        }
    }

}
