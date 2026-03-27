package com.fstm.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUIT")
    private Integer id;

    @Column(name = "NOM_PRODUIT")
    private String nom;

    private Double prix;
}