package com.fstm.jpa.controllers;

import com.fstm.jpa.entities.Produit;
import com.fstm.jpa.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProduitViewController {

    @Autowired
    private ProduitService produitService;

    // Afficher la liste des produits
    @GetMapping("/produits-web")
    public String afficherProduits(Model model) {
        model.addAttribute("produits", produitService.getAllProduits());
        return "liste-produits";
    }

    // ÉTAPE 1 : Afficher le formulaire d'ajout (Nouveau produit)
    @GetMapping("/produits/ajouter")
    public String montrerFormulaireAjout(Model model) {
        // On envoie un objet vide pour que le formulaire soit vierge
        model.addAttribute("produit", new Produit());
        return "form-produit";
    }

    // ÉTAPE 2 : Afficher le formulaire de modification (Produit existant)
    @GetMapping("/produits/modifier/{id}")
    public String montrerFormulaireModification(@PathVariable int id, Model model) {
        // On récupère le produit existant pour pré-remplir le formulaire
        Produit produit = produitService.getProduitById(id);
        model.addAttribute("produit", produit);
        return "form-produit";
    }

    // ÉTAPE 3 : Enregistrer (Ajout ou Modification)
    @PostMapping("/produits/save")
    public String enregistrerProduit(@ModelAttribute("produit") Produit produit) {
        if (produit.getId() == null) {
            // Si l'ID est null, c'est un nouvel ajout
            produitService.ajouterProduit(produit);
        } else {
            // Si l'ID existe, c'est une mise à jour
            produitService.updateProduit(produit.getId(), produit);
        }
        return "redirect:/produits-web";
    }

    // Supprimer un produit
    @GetMapping("/produits/supprimer/{id}")
    public String supprimerProduit(@PathVariable int id) {
        produitService.deleteProduit(id);
        return "redirect:/produits-web";
    }
}