package com.fstm.jpa.controllers;

import com.fstm.jpa.entities.Produit;
import com.fstm.jpa.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<Produit> list() {
        return produitService.getAllProduits();
    }

    @PostMapping
    public Produit save(@RequestBody Produit produit) {
        // Correction ici : on utilise le nouveau nom 'ajouterProduit'
        return produitService.ajouterProduit(produit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        produitService.deleteProduit(id);
    }
}