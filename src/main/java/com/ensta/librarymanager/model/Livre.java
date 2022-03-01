package com.ensta.librarymanager.model;

public class Livre {
    // TODO Create Class Livre
    private int id;
    private String titre;
    private String auteur;
    private String isbn;

    public Livre(int id, String titre, String auteur, String isbn) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
