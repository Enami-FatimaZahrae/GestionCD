package com.example.ejbctl.ejb;

import com.example.ejbctl.entities.CD;
import com.example.ejbctl.entities.Emprunt;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateful
public class CDService {

    @PersistenceContext
    private EntityManager em;

    public void ajouterCD(CD cd) {
        em.persist(cd);
    }

    public void modifierCD(Long cdId, String nouveauTitre, String nouvelAuteur) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            cd.setTitre(nouveauTitre);
            cd.setAuteur(nouvelAuteur);
            em.merge(cd);
        }
    }

    public void supprimerCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public List<Emprunt> listerEmpruntsEnCours() {
        return em.createQuery("SELECT e FROM Emprunt e WHERE e.dateRetour IS NULL", Emprunt.class).getResultList();
    }

    public List<CD> listerTousLesCDs() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}
