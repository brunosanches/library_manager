package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Abonnement;
import com.ensta.librarymanager.model.Livre;
import com.ensta.librarymanager.model.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDao implements IMembreDao {
    private static MembreDao instance;
    private MembreDao(){}

    public static MembreDao getInstance() {
        if (instance == null) {
            instance = new MembreDao();
        }
        return instance;
    }
    @Override
    public List<Membre> getList() throws DaoException {
        List<Membre> membresList = new ArrayList<Membre>();
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT id, nom, prenom, adresse, email, telephone, abonnement " +
                    "FROM membre " +
                    "ORDER BY nom, prenom");

            while(res.next()) {
                membresList.add(new Membre(res.getInt("id"), res.getString("nom"),
                        res.getString("prenom"), res.getString("adresse"), res.getString("email"),
                        res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Exception en getList Membre: " + e.getLocalizedMessage());
        }

        return membresList;
    }

    @Override
    public Membre getById(int id) throws DaoException {
        Membre membre = null;
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstm = conn.prepareStatement("SELECT id, nom, prenom, adresse, email, telephone, abonnement " +
                                                               "FROM membre WHERE id = ?");

            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();

            if (res.next()) {
                membre = new Membre(res.getInt("id"), res.getString("nom"),
                        res.getString("prenom"), res.getString("adresse"), res.getString("email"),
                        res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Exception en getList Membre: " + e.getLocalizedMessage());
        }

        return membre;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        int id = -1;
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstm = conn.prepareStatement("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) " +
                                                               "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, nom);
            pstm.setString(2, prenom);
            pstm.setString(3, adresse);
            pstm.setString(4, email);
            pstm.setString(5, telephone);
            pstm.setString(6, Abonnement.BASIC.name());

            pstm.executeUpdate();
            ResultSet resultSet = pstm.getGeneratedKeys();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Expection en create membre: " + e.getLocalizedMessage());
        }
        return id;
    }

    @Override
    public void update(Membre membre) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstm = conn.prepareStatement("UPDATE membre SET nom = ?, prenom = ?, adresse = ?," +
                                                               "email = ?, telephone = ?, abonnement = ?" +
                                                               "WHERE id = ?");
            pstm.setString(1, membre.getNom());
            pstm.setString(2, membre.getPrenom());
            pstm.setString(3, membre.getAdresse());
            pstm.setString(4, membre.getEmail());
            pstm.setString(5, membre.getTelephone());
            pstm.setString(6, membre.getAbonnement().name());
            pstm.setInt(7, membre.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Expection en update membre: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            PreparedStatement pstm = conn.prepareStatement("DELETE FROM membre WHERE id = ?");
            pstm.setInt(1, id);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Expection en delete: " + e.getLocalizedMessage());
        }
    }

    @Override
    public int count() throws DaoException {
        try {
            Connection conn = ConnectionManager.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT Count(*) FROM membre");

            res.next();

            return res.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQL Exception en getList Emprunt: " + e.getLocalizedMessage());
        }
    }
}
