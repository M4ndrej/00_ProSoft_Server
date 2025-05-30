/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacijePrivilegija;

import java.util.List;
import model.Privilegija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Andrej
 */
public class VratiListuPrivilegija extends ApstraktnaGenerickaOperacija {

    private List<Privilegija> lista;

    public List<Privilegija> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object obj) throws Exception {
        lista = broker.read(new Privilegija());
    }

}
