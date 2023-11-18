package Mehdi.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDataAccess {    //cette classe contient toutes le methodes necessaires pour interagir avec la BD sql

    //1 connexion à la BD
    String db = "supermarche_db";
    String user = "root";
    String pwd = "";
    String url = "jdbc:mysql://localhost:3306/"+db;
    Connection connection = null;
    public ProduitDataAccess() {
        try {
            connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion réussie!");

        } catch (SQLException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }
    //once connection is established, use PreparedStatement
    public List<Produit> getAll(){
        List<Produit> listeProduits = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "Select * from produit";
        try {
            pst = connection.prepareStatement(sql);
            System.out.println("requête getAll (PS) bien executée!");
            rs = pst.executeQuery();//rs pointe sur la première ligne du tableau resultant de la requête sql
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

    public List<Produit> getProduitsByKeyword(String keyword){
        List<Produit> listeProduits = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "Select * from produit where designation like ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, "%"+keyword+"%");
            System.out.println("requête keyword (PS) bien executée!");
            rs = pst.executeQuery();//rs pointe sur la première ligne du tableau resultant de la requête sql
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

