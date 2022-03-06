package com.ensta.librarymanager.service;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;
import junit.framework.TestCase;
import org.junit.Test;

public class LivreServiceTest extends TestCase {

    @Test
    public void testGetList() {
        LivreService ls = LivreService.getInstance();
        try {
            System.out.println(ls.getList());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetById() {
        LivreService ls = LivreService.getInstance();
        try {
            System.out.println(ls.getById(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCount() {
        LivreService ls = LivreService.getInstance();
        try {
            System.out.println(ls.count());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreate() {
        String titre = "La fraternité de l'anneau";
        String auteur = "J. R. R. Tolkien";
        String isbn = "9780007136599";

        try {
            LivreService ls = LivreService.getInstance();
            int id = ls.create(titre, auteur, isbn);
            Livre l = ls.getById(id);
            System.out.println(l);
            assertEquals(titre, l.getTitre());
            assertEquals(auteur, l.getAuteur());
            assertEquals(isbn, l.getIsbn());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdate() {
        String titre = "La fraternité de l'anneau";
        String auteur = "J. R. R. Tolkien";
        String isbn = "9780007136599";

        try {
            LivreService ls = LivreService.getInstance();
            int id = ls.create(titre, auteur, isbn);
            Livre l = ls.getById(id);
            System.out.println(l);

            titre = "Le Silmarillion";
            isbn = "9780261103665";
            l.setTitre(titre);
            l.setIsbn(isbn);

            ls.update(l);
            System.out.println(l);

            assertEquals(titre, l.getTitre());
            assertEquals(auteur, l.getAuteur());
            assertEquals(isbn, l.getIsbn());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDelete() {
        String titre = "La fraternité de l'anneau";
        String auteur = "J. R. R. Tolkien";
        String isbn = "9780007136599";

        try {
            LivreService ls = LivreService.getInstance();
            int id = ls.create(titre, auteur, isbn);
            Livre l = ls.getById(id);
            System.out.println(l);

            ls.delete(id);
            l = ls.getById(id);

            assertNull(l);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}