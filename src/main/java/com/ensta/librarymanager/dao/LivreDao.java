package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDao implements ILivreDao {
    private static LivreDao instance;
    private LivreDao(){}

    public static LivreDao getInstance() {
        if (instance == null) {
            instance = new LivreDao();
        }
        return instance;
    }

    @Override
    public List<Livre> getList() throws DaoException {
        List<Livre> livreList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection()){

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT id, titre, auteur, isbn FROM livre");

            while(res.next()) {
                livreList.add(new Livre(res.getInt("id"), res.getString("titre"),
                        res.getString("auteur"), res.getString("isbn")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Exception en getList livre: " + e.getLocalizedMessage());
        }

        return livreList;
    }

    @Override
    public Livre getById(int id) throws DaoException {
        Livre livre = null;
        try (Connection conn = ConnectionManager.getConnection()){

            PreparedStatement pstm = conn.prepareStatement("SELECT id, titre, auteur, isbn FROM livre WHERE id = ?");

            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();

            if (res.next()) {
                livre = new Livre(res.getInt("id"), res.getString("titre"),
                        res.getString("auteur"), res.getString("isbn"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Exception en getListById Livre: " + e.getLocalizedMessage());
        }

        return livre;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        int id = -1;
        try (Connection conn = ConnectionManager.getConnection()){

            PreparedStatement pstm = conn.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, titre);
            pstm.setString(2, auteur);
            pstm.setString(3, isbn);

            pstm.executeUpdate();
            ResultSet resultSet = pstm.getGeneratedKeys();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Expection en create livre: " + e.getLocalizedMessage());
        }
        return id;
    }

    @Override
    public void update(Livre livre) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection()){

            PreparedStatement pstm = conn.prepareStatement("UPDATE livre SET titre = ?, auteur = ?, isbn = ?" +
                                                                "WHERE id = ?");
            pstm.setString(1, livre.getTitre());
            pstm.setString(2, livre.getAuteur());
            pstm.setString(3, livre.getIsbn());
            pstm.setInt(4, livre.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Expection en update: " + e.getLocalizedMessage());
        }

    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection()){

            PreparedStatement pstm = conn.prepareStatement("DELETE FROM livre WHERE id = ?");
            pstm.setInt(1, id);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Expection en delete: " + e.getLocalizedMessage());
        }
    }

    @Override
    public int count() throws DaoException {
        try (Connection conn = ConnectionManager.getConnection()){

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
