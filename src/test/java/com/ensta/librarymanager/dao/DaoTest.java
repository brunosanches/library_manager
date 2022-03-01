package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import junit.framework.TestCase;

public class DaoTest extends TestCase {

    public void testGetLivreList() {
        LivreDao livre = new LivreDao();

        try {
            System.out.println(livre.getList());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testGetLivreById() {
        LivreDao livre = new LivreDao();

        try{
            System.out.println(livre.getById(1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void testCount() {
        LivreDao livre = new LivreDao();

        try{
            System.out.println(livre.count());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}