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

        Livre livre = new Livre(-1, titre, auteur, isbn);
        try {
            livre = livreDao.create(livre);
            livre = livreDao.getById(livre.getId());
            System.out.println(livre);
            assertEquals(titre, livre.getTitre());
            assertEquals(auteur, livre.getAuteur());
            assertEquals(isbn, livre.getIsbn());
            livreDao.delete(livre.getId());
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
        Livre livre = new Livre(-1, titre, auteur, isbn);
        try {
            livre = livreDao.create(livre);
            livre = livreDao.getById(livre.getId());
            System.out.println(livre);

            titre = "Le Silmarillion";
            isbn = "9780261103665";
            livre.setTitre(titre);
            livre.setIsbn(isbn);

            livreDao.update(livre);
            System.out.println(livre);

            assertEquals(titre, livre.getTitre());
            assertEquals(auteur, livre.getAuteur());
            assertEquals(isbn, livre.getIsbn());

            livreDao.delete(livre.getId());
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
        Livre livre = new Livre(-1, titre, auteur, isbn);
        try {
            livre = livreDao.create(livre);
            System.out.println(livre);

            livreDao.delete(livre.getId());
            livre = livreDao.getById(livre.getId());

            assertNull(livre);
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