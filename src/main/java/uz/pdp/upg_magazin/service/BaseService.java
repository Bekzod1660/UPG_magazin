package uz.pdp.upg_magazin.service;

import java.util.List;

public interface BaseService <T>{

    boolean add(T t);

    boolean delete(int id);


    boolean update(int id,T t);


}
