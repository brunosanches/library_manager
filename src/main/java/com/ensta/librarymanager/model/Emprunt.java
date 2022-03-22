package com.ensta.librarymanager.model;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

import java.time.LocalDate;

public class Emprunt {
    private int id;
    private int idMembre;
    private int idLivre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private Membre membre;
    private Livre livre;

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

    public Membre getMembre() {
        if (membre == null) {
            MembreService membreService = MembreService.getInstance();
            try {
                membre = membreService.getById(idMembre);

            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return membre;
    }

    public Livre getLivre() {
        if (livre == null) {
            LivreService livreService = LivreService.getInstance();
            try {
                livre = livreService.getById(idLivre);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return livre;
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
