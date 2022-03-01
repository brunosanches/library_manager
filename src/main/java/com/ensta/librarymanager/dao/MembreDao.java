package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Membre;

import java.util.List;

public class MembreDao implements IMembreDao {
    @Override
    public List<Membre> getList() throws DaoException {
        return null;
    }

    @Override
    public Membre getById(int id) throws DaoException {
        return null;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        return 0;
    }

    @Override
    public void update(Membre membre) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }

    @Override
    public int count() throws DaoException {
        return 0;
    }
}
