/* @author Juan Poveda*/

package Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
    db = cruddb
    table = user
        columns = id, name, lastname, email, phone
*/

public class DataBase {

    private final String db, driver, url;

    public DataBase() {
        this.db = "cruddb";
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://localhost/" + this.db;
    }

    public DataBase(String db, String driver, String url) {
        this.db = db;
        this.url = url;
        this.driver = driver;
    }

    public Connection connection() {

        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        return connection;
    }
}