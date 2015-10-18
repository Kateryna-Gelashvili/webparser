package org.k;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Kateryna on 02.08.2015.
 */
public abstract class DataBaseService {
    final static Logger logger = Logger.getLogger(DataBaseService.class);

    public static void updateListOfLaptopsInDatabase(ArrayList<Product> listFromWebsite){
        ArrayList<Product> listFromDataBase = getListOfLaptops();
        Connection con = ConnectionManager.openConnection("web");
        PreparedStatement psUpdate = null;
        PreparedStatement psInsert = null;
        PreparedStatement psDelete = null;
        try {
            psUpdate = con.prepareStatement(
                    "UPDATE \"List of laptops\" " +
                            "SET \"Price\" = ?, \"Currency\" = ? " +
                            "WHERE \"Name\" = ? AND \"Website\" LIKE ?");
            psInsert = con.prepareStatement(
                    "INSERT INTO \"List of laptops\"(\"Name\",\"Price\",\"Currency\",\"Website\") " +
                            "VALUES (?,?,?,?)");
            psDelete = con.prepareStatement(
                    "DELETE FROM \"List of laptops\" WHERE \"Name\" = ? AND \"Website\" LIKE ?");
        for (Product laptop: listFromWebsite) {
            if (listFromDataBase.contains(laptop)) {
                if (laptop.getPrice().compareTo(new BigDecimal(0)) > 0 && laptop.getCurrency() != null) {
                    psUpdate.setBigDecimal(1, laptop.getPrice());
                    psUpdate.setInt(2, laptop.getCurrency().getID());
                    psUpdate.setString(3, laptop.getName());
                    psUpdate.setString(4, laptop.getWebsite());
                    psUpdate.addBatch();
                } else {
                    psDelete.setString(1, laptop.getName());
                    psDelete.setString(2, laptop.getWebsite());
                    psDelete.addBatch();
                }
            } else {
                if (laptop.getPrice().compareTo(new BigDecimal(0)) > 0) {
                    psInsert.setString(1, laptop.getName());
                    psInsert.setBigDecimal(2, laptop.getPrice());
                    psInsert.setInt(3, laptop.getCurrency().getID());
                    psInsert.setString(4, laptop.getWebsite());
                    psInsert.addBatch();
                }
            }
        }
            psDelete.executeBatch();
            psUpdate.executeBatch();
            psInsert.executeBatch();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }finally {
            ConnectionManager.closeConnection(con, psInsert,null);
            ConnectionManager.closeConnection(null, psUpdate,null);
            ConnectionManager.closeConnection(null, psDelete,null);
        }
        logger.info("Database was updated");
    }

    public static ArrayList<Product> getListOfLaptops(){
        ArrayList<Product> listOfProduct = new ArrayList<>();
        Connection con = ConnectionManager.openConnection("web");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(
                    "SELECT list.* , c.\"Name\" AS \"cname\"" +
                            "FROM \"List of laptops\" list LEFT JOIN \"Currencies\" c ON list.\"Currency\" = c.id");
            rs = ps.executeQuery();
            while (rs.next()){
               Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setCurrency(Currency.getCurrencyByName(rs.getString("cname")));
                product.setWebsite(rs.getString("Website"));
                listOfProduct.add(product);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }finally {
            ConnectionManager.closeConnection(con,ps,rs);
        }
        //System.out.println(listOfProduct.toString());
        return listOfProduct;
    }

    public static ArrayList<Product> getListOfLaptopsByParameters(String name, BigDecimal minPrice, BigDecimal maxPrice){
        ArrayList<Product> listOfProduct = new ArrayList<>();

        String sqlString = "SELECT l.* , c.\"Name\" AS \"cname\"" +
                "FROM \"List of laptops\" l LEFT JOIN \"Currencies\" c " +
                "ON l.\"Currency\" = c.id WHERE lower(l.\"Name\") LIKE ?" +
                "AND l.\"Price\" >= ?";

        PreparedStatement ps = null;
        Connection con = ConnectionManager.openConnection("web");
        ResultSet rs = null;

        try {
            if (maxPrice.compareTo(BigDecimal.ZERO)>0){
                sqlString = sqlString + " AND l.\"Price\" <= ?";
            }

            ps = con.prepareStatement(sqlString);
            ps.setString(1,"%" + name + "%");
            ps.setBigDecimal(2,minPrice);
            if (maxPrice.compareTo(BigDecimal.ZERO)>0){
                ps.setBigDecimal(3,maxPrice);
            }

            rs = ps.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setCurrency(Currency.getCurrencyByName(rs.getString("cname")));
                product.setWebsite(rs.getString("Website"));
                listOfProduct.add(product);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }finally {
            ConnectionManager.closeConnection(con,ps,rs);
        }
        //System.out.println(listOfProduct.toString());
        return listOfProduct;
    }
}
