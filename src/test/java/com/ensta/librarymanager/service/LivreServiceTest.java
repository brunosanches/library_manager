package com.ensta.librarymanager.service;

import com.ensta.librarymanager.exception.ServiceException;
import junit.framework.TestCase;

public class LivreServiceTest extends TestCase {

    public void testGetList() {
        LivreService ls = new LivreService();
        try {
            System.out.println(ls.getList());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testGetById() {
        LivreService ls = new LivreService();
        try {
            System.out.println(ls.getById(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void testCount() {
        LivreService ls = new LivreService();
        try {
            System.out.println(ls.count());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}