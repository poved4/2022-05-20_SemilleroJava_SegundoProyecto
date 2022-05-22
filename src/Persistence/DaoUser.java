/* @author Juan Poveda */
package Persistence;

import Model.User;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DaoUser {

    private String query;
    private final DataBase db;

    public DaoUser() {
        this.query = "";
        this.db = new DataBase();
    }
    
    public User getUser(String iduser) {//no funciona

        User user = null;
        this.query = "SELECT * FROM user WHERE id = ?;";

        try {
            PreparedStatement ps = db.connection().prepareStatement(query);
            ps.setString(1, iduser);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                user = new User(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return user;
    }

    public ArrayList<User> listUser() {

        ArrayList<User> users = new ArrayList<User>();
        this.query = "SELECT * FROM user";

        try {

            PreparedStatement ps = db.connection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                users.add(new User(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getString("lastname"),
                        res.getString("email"),
                        res.getString("phone")
                ));
            }

            res.close();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return users;
    }

    public void newUser(User user) {

        this.query = "INSERT INTO user (name, lastname, email, phone) VALUES (?,?,?,?);";

        try {

            PreparedStatement ps = db.connection().prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    public void updateUser(String id, String name, String lastname, String email, String phone) {

        this.query = "UPDATE user SET name = ?, lastname = ?, email = ?, phone = ? WHERE id = ?";

        try {
            PreparedStatement inst = db.connection().prepareStatement(query);
            inst.setString(1, name);
            inst.setString(2, lastname);
            inst.setString(3, email);
            inst.setString(4, phone);
            inst.setString(5, id);

            inst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void updateUserBalance(String iduser, String balanceuser) {

        this.query = "UPDATE user SET balanceuser = ? WHERE iduser = ?;";

        try {
            PreparedStatement ps = db.connection().prepareStatement(query);
            ps.setString(1, balanceuser);
            ps.setString(2, iduser);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void deleteUser(String id) {

        this.query = "DELETE FROM user WHERE id = ?;";

        try {
            PreparedStatement inst = db.connection().prepareStatement(query);
            inst.setString(1, id);
            inst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}