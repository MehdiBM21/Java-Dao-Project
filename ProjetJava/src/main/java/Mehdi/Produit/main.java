package Mehdi.Produit;

import java.time.LocalDate;
import java.util.List;

public class main {
    public static void main(String[] args) {
        ProduitDaoImpl dao = new ProduitDaoImpl();
        Produit p = new Produit(1, "LA MALA", 2, 5000, LocalDate.now());
        dao.add(p);

    }

}
