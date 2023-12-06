package Mehdi.Produit;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl extends AbstractDao implements IProduitDao{
    @Override
    public void add(Produit obj) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO produit (designation, quantite, prix, date) VALUES (?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, obj.getDesignation());
            pst.setInt(2, obj.getQte());
            pst.setDouble(3, obj.getPrix());
            pst.setDate(4, Date.valueOf(obj.getDate())); // Assuming obj.getDate() returns a LocalDate
            pst.executeUpdate();
            System.out.println("Produit ajouté!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM produit WHERE id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Produit getById(int id) {
        return null;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> listeProduits = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "Select * from produit";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();//rs pointe sur la première ligne du tableau resultant de la requête sql
            System.out.println("requête getAll (PS) bien executée!");
            while(rs.next()) {
                // System.out.println(rs.getInt(1));//Attetion, ici le premier champs est = 1 (voir sqladmin)
                // <=>  System.out.println(rs.getInt("id"));
                Date date = rs.getDate("date");//we do this because the dates type returned is sql.date but produits has a type LocalDate!! => conversion to LocalDate
                listeProduits.add(new Produit(rs.getInt("id"),
                        rs.getString("designation"),
                        rs.getInt("quantite"),
                        rs.getDouble("prix"),
                        date.toLocalDate()));

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return listeProduits;
    }

    @Override
    public List<Produit> getProduitByKeyword(String keyword) {
        List<Produit> listeProduits = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "Select * from produit where designation like ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, "%"+keyword+"%");
            rs = pst.executeQuery();//rs pointe sur la première ligne du tableau resultant de la requête sql
            System.out.println("requête keyword (PS) bien executée!");
            while(rs.next()) {
                // System.out.println(rs.getInt(1));//Attetion, ici le premier champs est = 1 (voir sqladmin)
                // <=>  System.out.println(rs.getInt("id"));
                Date date = rs.getDate("date");//we do this because the dates type returned is sql.date but produits has a type LocalDate!! => conversion to LocalDate
                listeProduits.add(new Produit(rs.getInt("id"),
                        rs.getString("designation"),
                        rs.getInt("quantite"),
                        rs.getDouble("prix"),
                        date.toLocalDate()));

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return listeProduits;
    }

}
