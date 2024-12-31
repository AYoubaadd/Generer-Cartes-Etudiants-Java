import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
	
    public static Connection getConnection() {
       
        String url = "jdbc:mysql://localhost:3306/generer_carte_java"; 
        String username = "root"; 
        String password = "";

        Connection connection = null;

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion réussie à la base de données MySQL!");

        } catch (ClassNotFoundException e) {
            System.out.println("Pilote JDBC introuvable !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données !");
            e.printStackTrace();
        } 
        return connection;
    }
}
