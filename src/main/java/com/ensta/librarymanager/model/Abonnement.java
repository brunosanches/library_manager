package com.ensta.librarymanager.model;

public enum Abonnement {
    BASIC(2), PREMIUM(5), VIP(20);
    private final int maxEmprunt;

    Abonnement(int i) {
        maxEmprunt = i;
    }

    public int getMaxEmprunt() {
        return maxEmprunt;
    }
}
