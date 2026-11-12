package com.ecole221.planification.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    /** Message uniforme pour le cas le plus courant : « Cours introuvable : 12 ». */
    public EntityNotFoundException(String entite, Object identifiant) {
        super(entite + " introuvable : " + identifiant);
    }
}
