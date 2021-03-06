package com.ensta.librarymanager.service;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Livre;
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
        List<Membre> membreList;
        try {
            membreList = membreDao.getList();
            EmpruntService empruntService = EmpruntService.getInstance();
            membreList.stream().filter(item -> {
                try {
                    return empruntService.isEmpruntPossible(item);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                return false;
            });
        } catch (DaoException e) {
            throw new ServiceException("Exception en livre getListDispo en verifiant disponibilité" +
                    e.getLocalizedMessage());
        }
        return membreList;
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
    public Membre create(Membre membre) throws ServiceException {
        try {
            if (membre.getNom() == null || membre.getPrenom() == null ||
                    membre.getNom().equals("") || membre.getPrenom().equals(""))
                throw new ServiceException("Le nom ou prenom du membre est vide");
            return membreDao.create(membre);
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
            EmpruntService empruntService = EmpruntService.getInstance();
            List<Emprunt> empruntList = empruntService.getListCurrentByMembre(id);

            if (empruntList.isEmpty()) {
                membreDao.delete(id);
            }
            else {
                throw new ServiceException("Le membre ne peut pas être deleté, il y a encore des emprunts");
            }
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
