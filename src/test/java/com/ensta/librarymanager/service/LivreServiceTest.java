package com.ensta.librarymanager.service;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class LivreServiceTest extends TestCase {
    @Test
    public void testGetListDispo() {
        LivreService ls = LivreService.getInstance();
        try {
            List<Livre> ll = ls.getListDispo();
            System.out.println(ll);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreate() {
        LivreService ls = LivreService.getInstance();
        try {
          ls.create("", "", "xxxxxxx");
          assertTrue(false);
        } catch (ServiceException e) {
            assertEquals(e.getLocalizedMessage(), "Le titre du livre est vide");
        }
    }
    @Test
    public void testUpdate() {
        LivreService ls = LivreService.getInstance();
        try {
            int id = ls.create("History", "Frank", "sssssssss");
            assertTrue(id != -1);
            Livre l = ls.getById(id);
            l.setTitre("");
            ls.update(l);
            assertTrue(false); // If it comes here it is wrong, need to throw exception
        } catch (ServiceException e) {
            assertEquals(e.getLocalizedMessage(), "Le titre du livre est vide");
        }
    }
}