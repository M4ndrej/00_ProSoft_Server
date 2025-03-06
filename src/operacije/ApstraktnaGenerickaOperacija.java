/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.sql.SQLException;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.implementation.DbRepositoryGeneric;

/**
 *
 * @author Andrej
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    public final void izvrsi(Object obj) throws Exception {
        try {
            preduslovi(obj);
            zapocniTranskaciju();
            izvrsiOperaciju(obj);
            potvrdiTranskaciju();

        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        }

    }

    ;

    protected abstract void preduslovi(Object obj) throws Exception;

    private void zapocniTranskaciju() {
        ((DbRepository) broker).connect();
    }

    protected abstract void izvrsiOperaciju(Object obj) throws Exception;

    private void ponistiTransakciju() throws SQLException {
        ((DbRepository) broker).rollback();
    }

    private void potvrdiTranskaciju() throws SQLException {
        ((DbRepository) broker).commit();
    }

}
