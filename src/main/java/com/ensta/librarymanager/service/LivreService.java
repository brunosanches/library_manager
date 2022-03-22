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
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Livre> getListDispo() throws ServiceException {
        List<Livre> le = null;
        try {
            le = livreDao.getList();
            EmpruntService es = EmpruntService.getInstance();
            le.stream().filter(item -> {
                try {
                    return es.isLivreDispo(item.getId());
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } catch (DaoException e) {
            throw new ServiceException("Exception en livre getListDispo en verifiant disponibilité" +
                    e.getLocalizedMessage());
        }
        return le;
    }

    @Override
    public Livre getById(int id) throws ServiceException {
        try {
            return livreDao.getById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Livre create(Livre livre) throws ServiceException {
        try {
            if (livre.getTitre() == null || livre.getTitre().equals(""))
                throw new ServiceException("Le titre du livre est vide");
            return livreDao.create(livre);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Livre livre) throws ServiceException {
        try {
            if (livre.getTitre() == null || livre.getTitre().equals(""))
                throw new ServiceException("Le titre du livre est vide");
            livreDao.update(livre);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            EmpruntService empruntService = EmpruntService.getInstance();
            if (empruntService.getListCurrentByLivre(id).isEmpty())
                livreDao.delete(id);
            else throw new ServiceException("Vous ne pouvez pas deleter un livre qui n'as pas encore été retourné");
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            return livreDao.count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
