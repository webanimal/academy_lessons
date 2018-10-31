package ru.webanimal.academy_lessons.business;

public class Interactors {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static Interactors instance;
    public static Interactors get() {
        if (instance == null) {
            instance = new Interactors();
        }

        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final DigestInteractorImpl digestInteractor;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private Interactors() {
        this.digestInteractor = new DigestInteractorImpl();
    }


    //==============================================================================================
    // Getters and Setters
    //==============================================================================================

    public DigestInteractorImpl getDigestInteractor() {
        return digestInteractor;
    }
}
