package com.ensta.librarymanager.dao;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDao implements IEmpruntDao {
    private static EmpruntDao instance;
    private EmpruntDao() {}

    public static EmpruntDao getInstance() {
        if (instance == null) {
            instance = new EmpruntDao();
        }
        return instance;
    }

    @Override
    public List<Emprunt> getList() throws DaoException {
        List<Emprunt> empruntList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
                    "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, " +
                    "dateRetour " +
                    "FROM emprunt AS e " +
                    "INNER JOIN membre ON membre.id = e.idMembre " +
                    "INNER JOIN livre ON livre.id = e.idLivre " +
                    "ORDER BY dateRetour DESC ");

            while (rs.next()) {
                empruntList.add(new Emprunt(rs.getInt("id"),
                                            rs.getInt("idMembre"),
                                            rs.getInt("idLivre"),
                                            rs.getDate("dateEmprunt").toLocalDate(),
                                            rs.getDate("dateRetour") == null ? null :
                                                    rs.getDate("dateRetour").toLocalDate()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQLException en emprunt getList: " + e.getLocalizedMessage());
        }
        return empruntList;
    }

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        List<Emprunt> empruntList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                        "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
                            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, " +
                            "dateRetour " +
                            "FROM emprunt AS e " +
                            "INNER JOIN membre ON membre.id = e.idMembre " +
                            "INNER JOIN livre ON livre.id = e.idLivre " +
                            "WHERE dateRetour IS NULL");

            while (rs.next()) {
                empruntList.add(new Emprunt(rs.getInt("id"),
                        rs.getInt("idMembre"),
                        rs.getInt("idLivre"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null :
                                rs.getDate("dateRetour").toLocalDate()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQLException en emprunt getListCurrent: " + e.getLocalizedMessage());
        }
        return empruntList;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        List<Emprunt> empruntList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection()){

            PreparedStatement pstm = conn.prepareStatement(
                    "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
                            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, " +
                            "dateRetour " +
                            "FROM emprunt AS e " +
                            "INNER JOIN membre ON membre.id = e.idMembre " +
                            "INNER JOIN livre ON livre.id = e.idLivre " +
                            "WHERE dateRetour IS NULL AND membre.id = ?");
            pstm.setInt(1, idMembre);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                empruntList.add(new Emprunt(rs.getInt("id"),
                        rs.getInt("idMembre"),
                        rs.getInt("idLivre"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null :
                                rs.getDate("dateRetour").toLocalDate()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQLException en emprunt getListCurrentByMembre: " + e.getLocalizedMessage());
        }
        return empruntList;
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        List<Emprunt> empruntList = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection()){

            PreparedStatement pstm = conn.prepareStatement(
                    "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
                            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, " +
                            "dateRetour " +
                            "FROM emprunt AS e " +
                            "INNER JOIN membre ON membre.id = e.idMembre " +
                            "INNER JOIN livre ON livre.id = e.idLivre " +
                            "WHERE dateRetour IS NULL AND livre.id = ?");

            pstm.setInt(1, idLivre);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                empruntList.add(new Emprunt(rs.getInt("id"),
                        rs.getInt("idMembre"),
                        rs.getInt("idLivre"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null :
                                rs.getDate("dateRetour").toLocalDate()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQLException en emprunt getListCurrentByLivre: " + e.getLocalizedMessage());
        }
        return empruntList;
    }

    @Override
    public Emprunt getById(int id) throws DaoException {
        Emprunt emprunt = null;
        try (Connection conn = ConnectionManager.getConnection()){

            PreparedStatement pstm = conn.prepareStatement(
                    "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
                            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, " +
                            "dateRetour " +
                            "FROM emprunt AS e " +
                            "INNER JOIN membre ON membre.id = e.idMembre " +
                            "INNER JOIN livre ON livre.id = e.idLivre " +
                            "WHERE e.id = ?");

            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                emprunt = new Emprunt(rs.getInt("id"),
                        rs.getInt("idMembre"),
                        rs.getInt("idLivre"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetour") == null ? null :
                                rs.getDate("dateRetour").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQLException en emprunt getListCurrentByLivre: " + e.getLocalizedMessage());
        }
        return emprunt;
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection()) {

            PreparedStatement pstm = conn.prepareStatement(
                    "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour)" +
                        "VALUES (?, ?, ?, ?);");

            pstm.setInt(1, idMembre);
            pstm.setInt(2, idLivre);
            pstm.setDate(3, Date.valueOf(dateEmprunt));
            pstm.setDate(4, null);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQLException en emprunt create: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Emprunt emprunt) throws DaoException {
        try (Connection conn = ConnectionManager.getConnection()){

            PreparedStatement pstm = conn.prepareStatement(
                    "UPDATE emprunt " +
                    "SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? " +
                    "WHERE id = ?;");

            pstm.setInt(1, emprunt.getIdMembre());
            pstm.setInt(2, emprunt.getIdLivre());
            pstm.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            pstm.setDate(4, Date.valueOf(emprunt.getDateRetour()));
            pstm.setInt(5, emprunt.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQLException en emprunt update: " + e.getLocalizedMessage());
        }
    }

    @Override
    public int count() throws DaoException {
        int count = 0;
        try (Connection conn = ConnectionManager.getConnection()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id) AS count FROM emprunt");

            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("SQLException en emprunt count: " + e.getLocalizedMessage());
        }

        return count;
    }
}
