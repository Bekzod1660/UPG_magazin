package uz.pdp.upg_magazin.service;

import java.util.List;

public interface BaseService <R,T>{

    R add(T t);

    boolean delete(int id);


    boolean update(int id,T t);


}
