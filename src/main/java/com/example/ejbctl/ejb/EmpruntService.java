package com.example.ejbctl.ejb;

import com.example.ejbctl.entities.CD;
import com.example.ejbctl.entities.Emprunt;
import com.example.ejbctl.entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;

@Stateless
public class EmpruntService {

    @PersistenceContext
    private EntityManager em;

    public List<CD> listerCDDispo() {
        return em.createQuery("SELECT c FROM CD c WHERE c.disponible = TRUE", CD.class).getResultList();
    }

    public CD emprunterCD(Long utilisateurId, Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isDisponible()) {
            cd.setDisponible(false);
            Emprunt emprunt = new Emprunt();
            emprunt.setCd(cd);
            emprunt.setUtilisateur(em.find(Utilisateur.class, utilisateurId));
            emprunt.setDateEmprunt(new Date());
            em.persist(emprunt);
        }
        return cd;
    }

    public List<Emprunt> voirEmpruntsUtilisateur(Long utilisateurId) {
        return em.createQuery("SELECT e FROM Emprunt e WHERE e.utilisateur.id = :utilisateurId", Emprunt.class)
                .setParameter("utilisateurId", utilisateurId)
                .getResultList();
    }

    public void retournerCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null) {
            CD cd = emprunt.getCd();
            cd.setDisponible(true);
            emprunt.setDateRetour(new Date());
            em.merge(emprunt);
        }
    }

    public void retournerCD(int empruntId, Utilisateur utilisateur) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null && emprunt.getUtilisateur().equals(utilisateur)) {
            emprunt.getCd().setDisponible(true);
            em.remove(emprunt);
        }
    }
}
