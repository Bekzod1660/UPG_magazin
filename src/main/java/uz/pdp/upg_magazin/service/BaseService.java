package uz.pdp.upg_magazin.service;

import java.util.List;

public interface BaseService <T,R>{

    R add(T t);

    R delete(int id);

    List<T>listObject();

    R update(int id,T t);

    T getById(int id);

}
