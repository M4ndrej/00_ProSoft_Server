/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.sql.SQLException;
import repository.db.DbRepository;
import repository.db.implementation.DbRepositoryGeneric;
import repository.RepositoryGeneric;
import repository.RepositorySpecific;
import repository.db.implementation.DbRepositorySpecific;

/**
 *
 * @author Andrej
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected final RepositoryGeneric broker;
    protected final RepositorySpecific brokerSpecific;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
        this.brokerSpecific = new DbRepositorySpecific();
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
