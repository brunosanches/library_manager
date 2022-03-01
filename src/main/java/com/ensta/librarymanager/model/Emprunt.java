package com.ensta.librarymanager.model;

import java.time.LocalDate;

public class Emprunt {
    // TODO Create class Emprunt
    private int id;
    private int idMembre;
    private int idLivre;
    private LocalDate dateEmprunt;
    private LocalDate dateTerour;


    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", idMembre=" + idMembre +
                ", idLivre=" + idLivre +
                ", dateEmprunt=" + dateEmprunt +
                ", dateTerour=" + dateTerour +
                '}';
    }
}
