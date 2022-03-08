package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Membre;

import java.util.List;

public class MembreService implements IMembreService{
    private MembreDao membreDao;
    private static MembreService instance;
    private MembreService() {
        membreDao = MembreDao.getInstance();
    }

    public static MembreService getInstance() {
        if(instance == null) {
            instance = new MembreService();
        }
        return instance;
    }
    @Override
    public List<Membre> getList() throws ServiceException {
        try {
            return membreDao.getList();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service membre GetList");
        }
    }

    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        return null;
    }

    @Override
    public Membre getById(int id) throws ServiceException {
        try {
            return membreDao.getById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service membre GetById");
        }
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException {
        try {
            if (nom == null || prenom == null || nom.equals("") || prenom.equals(""))
                throw new ServiceException("Le nom ou prenom du membre est vide");
            return membreDao.create(nom.toUpperCase(), prenom, adresse, email, telephone);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service membre create");
        }
    }

    @Override
    public void update(Membre membre) throws ServiceException {
        try {
            if (membre.getNom() == null || membre.getPrenom() == null ||
                    membre.getNom().equals("") || membre.getPrenom().equals(""))
                throw new ServiceException("Le nom ou prenom du membre est vide");

            membre.setNom(membre.getNom().toUpperCase());
            membreDao.update(membre);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service membre update");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            membreDao.delete(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service membre delete");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            return membreDao.count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problème au service membre count");
        }
    }
}
