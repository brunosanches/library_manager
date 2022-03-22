package com.ensta.librarymanager.service;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class LivreServiceTest extends TestCase {
    @Test
    public void testGetListDispo() {
        LivreService livreService = LivreService.getInstance();
        try {
            List<Livre> ll = livreService.getListDispo();
            System.out.println(ll);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreate() {
        LivreService livreService = LivreService.getInstance();
        try {
            Livre livre = new Livre(-1, "", "", "xxxxxx");
            livre = livreService.create(livre);
            assertTrue(false);
        } catch (ServiceException e) {
            assertEquals(e.getLocalizedMessage(), "Le titre du livre est vide");
        }
    }
    @Test
    public void testUpdate() {
        LivreService livreService = LivreService.getInstance();
        try {
            Livre livre = new Livre(-1, "History", "Frank", "ssssss");
            livre = livreService.create(livre);
            assertTrue(livre.getId() != -1);
            livre.setTitre("");
            livreService.update(livre);
            assertTrue(false); // If it comes here it is wrong, need to throw exception
        } catch (ServiceException e) {
            assertEquals(e.getLocalizedMessage(), "Le titre du livre est vide");
        }
    }
}