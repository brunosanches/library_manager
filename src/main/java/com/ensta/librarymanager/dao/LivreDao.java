package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDao implements ILivreDao {
    @Override
    public List<Livre> getList() throws DaoException {
        List<Livre> livreList = new ArrayList<Livre>();
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT id, titre, auteur, isbn FROM livre");

            while(res.next()) {
                livreList.add(new Livre(res.getInt("id"), res.getString("titre"),
                        res.getString("auteur"), res.getString("isbn")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Exception en getList Emprunt: " + e.getLocalizedMessage());
        }

        return livreList;
    }

    @Override
    public Livre getById(int id) throws DaoException {
        Livre livre;
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstm = conn.prepareStatement("SELECT id, titre, auteur, isbn FROM livre WHERE id = ?");

            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();

            res.next();

            livre = new Livre(res.getInt("id"), res.getString("titre"),
                    res.getString("auteur"), res.getString("isbn"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Exception en getList Emprunt: " + e.getLocalizedMessage());
        }

        return livre;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        return 0;
    }

    @Override
    public void update(Livre livre) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }

    @Override
    public int count() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT Count(*) FROM livre");

            res.next();

            return res.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Exception en getList Emprunt: " + e.getLocalizedMessage());
        }
    }
}
