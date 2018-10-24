package ru.webanimal.academy_lessons.business;

public class Interactors {

    private final DigestInteractorImpl digestInteractor;
    public DigestInteractorImpl getDigestInteractor() {
        return digestInteractor;
    }

    public Interactors() {
        this.digestInteractor = new DigestInteractorImpl();
    }
}
