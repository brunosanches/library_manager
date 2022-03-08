package com.ensta.librarymanager.model;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

import java.time.LocalDate;

public class Emprunt {
    // TODO Create class Emprunt
    private int id;
    private int idMembre;
    private int idLivre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(int id, int idMembre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.id = id;
        this.idMembre = idMembre;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public int getId() {
        return id;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getMembreNom() {
        MembreService membreService = MembreService.getInstance();
        try {
            return membreService.getById(idMembre).getNom();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getMembrePrenom() {
        MembreService membreService = MembreService.getInstance();

        try {
            return membreService.getById(idMembre).getPrenom();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getLivreTitre() {
        LivreService livreService = LivreService.getInstance();

        try {
            return livreService.getById(idLivre).getTitre();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getAuteurNom() {
        LivreService livreService = LivreService.getInstance();

        try {
            return livreService.getById(idLivre).getAuteur();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", idMembre=" + idMembre +
                ", idLivre=" + idLivre +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
