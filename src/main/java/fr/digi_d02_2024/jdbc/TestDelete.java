package fr.digi_d02_2024.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestDelete {

	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PWD;

	static {

		ResourceBundle dbConfig = ResourceBundle.getBundle("db");
		DB_URL = dbConfig.getString("db.url");
		DB_USER = dbConfig.getString("db.user");
		DB_PWD = dbConfig.getString("db.pwd");
	}

	public static void main(String[] args) throws SQLException {

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				Statement st = connection.createStatement();) {

			// Connectez vous à la base de données compta puis supprimer le fournisseur
			// « La Maison de la Peinture »
			int nbSupp = st.executeUpdate("DELETE FROM FOURNISSEUR WHERE NOM = 'La maison de la peinture'");
			System.out.println("Nombre d'éléments supprimés: " + nbSupp);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Fin du programme");

	}

}
