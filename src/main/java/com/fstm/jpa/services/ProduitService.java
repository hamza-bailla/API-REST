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

    /**
     * Récupère la liste de tous les produits
     */
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    /**
     * Trouve un produit par son ID ou lance une erreur s'il n'existe pas
     */
    public Produit getProduitById(int id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'ID : " + id));
    }

    /**
     * Crée un nouveau produit
     */
    public Produit createProduit(Produit produit) {
        // On s'assure que l'ID est null pour forcer la création (Auto-increment)
        produit.setId(null);
        return produitRepository.save(produit);
    }

    /**
     * Met à jour un produit existant
     */
    public Produit updateProduit(int id, Produit newProduit) {
        // On vérifie d'abord si le produit existe
        Produit produitExistant = getProduitById(id);

        // Mise à jour des champs
        produitExistant.setNom(newProduit.getNom());
        produitExistant.setPrix(newProduit.getPrix());

        // Sauvegarde des modifications
        return produitRepository.save(produitExistant);
    }

    /**
     * Supprime un produit par son ID
     */
    public void deleteProduit(int id) {
        Produit produit = getProduitById(id);
        produitRepository.delete(produit);
    }
}