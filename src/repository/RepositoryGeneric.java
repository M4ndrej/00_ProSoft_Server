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
public interface RepositoryGeneric<T> {

    public List<T> read(T t);

    public boolean create(T t);

    public boolean update(T t);

    public boolean delete(T t);

    public List<T> readWithCondition(T t);
    
    public boolean existsInDb(T t);
    
    public boolean existRelation(T subjekat, T objekat);

}
