package Mehdi.Produit;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Produit {
    private int id;
    private String designation;
    private int qte;
    private double prix;
    private LocalDate date;

    public Produit(int id, String designation, int qte, double prix, LocalDate date) {
        this.id = id;
        this.designation = designation;
        this.qte = qte;
        this.prix = prix;
        this.date = date;
    }


}
