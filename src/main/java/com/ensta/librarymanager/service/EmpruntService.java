package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Membre;

import java.time.LocalDate;
import java.util.List;

public class EmpruntService implements IEmpruntService{
    private EmpruntDao empruntDao;
    private static EmpruntService instance;

    private EmpruntService() {
        empruntDao = EmpruntDao.getInstance();
    }

    public static EmpruntService getInstance() {
        if (instance == null) {
            instance = new EmpruntService();
        }
        return instance;
    }

    @Override
    public List<Emprunt> getList() throws ServiceException {
        try {
            return empruntDao.getList();
        } catch (DaoException e) {
            throw new ServiceException("SQL Exception en emprunt getList: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<Emprunt> getListCurrent() throws ServiceException {
        try {
            return empruntDao.getListCurrent();
        } catch (DaoException e) {
            throw new ServiceException("SQL Exception en emprunt getListCurrent: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        try {
            return empruntDao.getListCurrentByMembre(idMembre);
        } catch (DaoException e) {
            throw new ServiceException("SQL Exception en emprunt getListCurrentByMembre: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        try {
            return empruntDao.getListCurrentByLivre(idLivre);
        } catch (DaoException e) {
            throw new ServiceException("SQL Exception en emprunt getListCurrentByLivre: " + e.getLocalizedMessage());
        }
    }

    @Override
    public Emprunt getById(int id) throws ServiceException {
        try {
            return empruntDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("SQL Exception en emprunt getById: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
        try {
            empruntDao.create(idMembre, idLivre, dateEmprunt);
        } catch (DaoException e) {
            throw new ServiceException("SQL Exception en emprunt create: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void returnBook(int id) throws ServiceException {
        try {
            Emprunt e = empruntDao.getById(id);
            e.setDateRetour(LocalDate.now());
            empruntDao.update(e);
        } catch (DaoException e) {
            throw new ServiceException("SQL Exception en emprunt returnBook: " + e.getLocalizedMessage());
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            return empruntDao.count();
        } catch (DaoException e) {
            throw new ServiceException("SQL Exception en emprunt count: " + e.getLocalizedMessage());
        }
    }

    @Override
    public boolean isLivreDispo(int idLivre) throws ServiceException {
        try {
            List<Emprunt> le = empruntDao.getListCurrentByLivre(idLivre);
            return !le.stream().anyMatch(item -> item.getDateRetour() == null);

        } catch (DaoException e) {
            throw new ServiceException("Exception en isLivreDispo");
        }
    }

    @Override
    public boolean isEmpruntPossible(Membre membre) throws ServiceException {
        try {
            List<Emprunt> le = empruntDao.getListCurrentByMembre(membre.getId());
            int count = le.size();
            return count < membre.getAbonnement().getMaxEmprunt();
        } catch (DaoException e) {
            throw new ServiceException("Exception en isEmpruntPossible");
        }
    }
}
