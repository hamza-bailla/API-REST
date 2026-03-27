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

    // ÉTAPE 1 : Afficher le formulaire de modification (GET)
    @GetMapping("/produits/modifier/{id}")
    public String montrerFormulaireModification(@PathVariable int id, Model model) {
        // On récupère le produit existant pour pré-remplir le formulaire
        Produit produit = produitService.getProduitById(id);
        model.addAttribute("produit", produit);
        return "form-produit"; // Assurez-vous que form-produit.html existe dans templates
    }

    // ÉTAPE 2 : Traiter la modification (POST)
    @PostMapping("/produits/update")
    public String modifierProduit(@ModelAttribute("produit") Produit produit) {
        // On appelle le service avec l'ID et l'objet rempli par le formulaire
        produitService.updateProduit(produit.getId(), produit);
        // On redirige vers la liste pour voir le changement
        return "redirect:/produits-web";
    }

    // Supprimer un produit
    @GetMapping("/produits/supprimer/{id}")
    public String supprimerProduit(@PathVariable int id) {
        produitService.deleteProduit(id);
        return "redirect:/produits-web";
    }
}