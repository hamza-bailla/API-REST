package com.fstm.jpa.services;

import com.fstm.jpa.entities.Produit;
import com.fstm.jpa.repositories.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduitById(int id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé : " + id));
    }

    /**
     * Méthode renommée pour correspondre au contrôleur
     */
    public Produit ajouterProduit(Produit produit) {
        produit.setId(null); // Force l'insertion (ID généré par la base)
        return produitRepository.save(produit);
    }

    public Produit updateProduit(int id, Produit newProduit) {
        Produit produit = getProduitById(id);
        produit.setNom(newProduit.getNom());
        produit.setPrix(newProduit.getPrix());
        return produitRepository.save(produit);
    }

    public void deleteProduit(int id) {
        produitRepository.delete(getProduitById(id));
    }
}