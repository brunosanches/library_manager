package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Livre;
import junit.framework.TestCase;
import org.junit.Test;

public class LivreDaoTest extends TestCase {
    @Test
    public void testGetList() {
        LivreDao livreDao = LivreDao.getInstance();
        try {
            System.out.println(livreDao.getList());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetById() {
        LivreDao livreDao = LivreDao.getInstance();
        try {
            System.out.println(livreDao.getById(1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreate() {
        LivreDao livreDao = LivreDao.getInstance();
        String titre = "La fraternité de l'anneau";
        String auteur = "J. R. R. Tolkien";
        String isbn = "9780007136599";

        try {
            int id = livreDao.create(titre, auteur, isbn);
            Livre l = livreDao.getById(id);
            System.out.println(l);
            assertEquals(titre, l.getTitre());
            assertEquals(auteur, l.getAuteur());
            assertEquals(isbn, l.getIsbn());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdate() {
        LivreDao livreDao = LivreDao.getInstance();
        String titre = "La fraternité de l'anneau";
        String auteur = "J. R. R. Tolkien";
        String isbn = "9780007136599";

        try {
            int id = livreDao.create(titre, auteur, isbn);
            Livre l = livreDao.getById(id);
            System.out.println(l);

            titre = "Le Silmarillion";
            isbn = "9780261103665";
            l.setTitre(titre);
            l.setIsbn(isbn);

            livreDao.update(l);
            System.out.println(l);

            assertEquals(titre, l.getTitre());
            assertEquals(auteur, l.getAuteur());
            assertEquals(isbn, l.getIsbn());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDelete() {
        LivreDao livreDao = LivreDao.getInstance();
        String titre = "La fraternité de l'anneau";
        String auteur = "J. R. R. Tolkien";
        String isbn = "9780007136599";

        try {
            int id = livreDao.create(titre, auteur, isbn);
            Livre l = livreDao.getById(id);
            System.out.println(l);

            livreDao.delete(id);
            l = livreDao.getById(id);

            assertNull(l);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCount() {
        LivreDao livreDao = LivreDao.getInstance();
        try {
            System.out.println(livreDao.count());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}