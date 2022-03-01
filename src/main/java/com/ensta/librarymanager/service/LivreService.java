package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Livre;

import java.util.List;

public class LivreService implements ILivreService {
    private LivreDao livreDao;

    public LivreService() {
        livreDao = new LivreDao();
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
        return 0;
    }

    @Override
    public void update(Livre livre) throws ServiceException {

    }

    @Override
    public void delete(int id) throws ServiceException {

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
