package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;

import java.util.List;

public class LivreService implements ILivreService {
    private LivreDao livreDao;
    private static LivreService instance;
    private LivreService() {
        livreDao = LivreDao.getInstance();
    }

    public static LivreService getInstance() {
        if(instance == null) {
            instance = new LivreService();
        }
        return instance;
    }


    @Override
    public List<Livre> getList() throws ServiceException {
        try {
            return livreDao.getList();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service livre GetList");
        }
    }

    @Override
    public List<Livre> getListDispo() throws ServiceException {
        return null;
    }

    @Override
    public Livre getById(int id) throws ServiceException {
        try {
            return livreDao.getById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service livre GetById");
        }
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws ServiceException {
        try {
            return livreDao.create(titre, auteur, isbn);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service livre create");
        }
    }

    @Override
    public void update(Livre livre) throws ServiceException {
        try {
            livreDao.update(livre);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service livre update");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            livreDao.delete(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service livre delete");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            return livreDao.count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service livre count");
        }
    }
}
