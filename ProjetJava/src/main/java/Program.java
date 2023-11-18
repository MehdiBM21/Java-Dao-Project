// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import Mehdi.Produit.Produit;
import Mehdi.Produit.ProduitDataAccess;

import java.util.List;
public class Program {
    public static void main(String[] args) {
       ProduitDataAccess produitDataAccess = new ProduitDataAccess();
       List<Produit> listeProduits = produitDataAccess.getAll();
        List<Produit> listeProduitsKw = produitDataAccess.getProduitsByKeyword("RS4");
        System.out.println("Tous les produits");
        for (Produit prod : listeProduits) {
           System.out.println(prod.toString());
        }
        System.out.println("Produits by keyWord");
        for (Produit prodKw : listeProduitsKw) {
            System.out.println(prodKw.toString());
        }
    }
}