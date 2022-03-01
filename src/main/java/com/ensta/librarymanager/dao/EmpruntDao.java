package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDao implements IEmpruntDao {
    @Override
    public List<Emprunt> getList() throws DaoException {

        return null;
    }

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        return null;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        return null;
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        return null;
    }

    @Override
    public Emprunt getById(int id) throws DaoException {
        return null;
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {

    }

    @Override
    public void update(Emprunt emprunt) throws DaoException {

    }

    @Override
    public int count() throws DaoException {
        return 0;
    }
}
