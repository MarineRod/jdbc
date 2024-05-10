package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	private static final String INSERT_QUERY = "INSERT INTO FOURNISSEUR(NOM) VALUES('%s')";

	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PWD;

	static {

		ResourceBundle dbConfig = ResourceBundle.getBundle("db");
		DB_URL = dbConfig.getString("db.url");
		DB_USER = dbConfig.getString("db.user");
		DB_PWD = dbConfig.getString("db.pwd");
	}

	@Override
	public List<Fournisseur> extraire() throws SQLException {
		ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				Statement st = connection.createStatement();) {
			ResultSet rs = st.executeQuery("SELECT*FROM FOURNISSEUR");

			while (rs.next()) {
				Integer id = rs.getInt("ID");// getInt(1);
				String nom = rs.getString("NOM");// getInt(2);
				fournisseurs.add(new Fournisseur(id, nom));

			}

		}
		return fournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) throws SQLException {

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				Statement st = connection.createStatement();) {

			// Insertion
			String query = String.format(INSERT_QUERY, fournisseur.getNom());
			st.executeUpdate(query);

		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) throws SQLException {
		// Déclaration de la requête SQL pour mettre à jour le nom du fournisseur
		String sql = "UPDATE FOURNISSEUR SET nom = ? WHERE nom = ?";
		int nb;
		try (
				// Établir une connexion à la base de données et préparer la déclaration
				Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				PreparedStatement statement = connection.prepareStatement(sql);) {
			// Paramétrer les valeurs des paramètres de la requête
			statement.setString(1, nouveauNom);
			statement.setString(2, ancienNom);

			// Exécuter la requête et retourner le nombre de lignes affectées
			nb = statement.executeUpdate();
		}
		return nb;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) throws SQLException {
		// Déclaration de la requête SQL pour supprimer un fournisseur
		String sql = "DELETE FROM FOURNISSEUR WHERE id = ?";

				// Établir une connexion à la base de données et préparer la déclaration
				Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				PreparedStatement statement = connection.prepareStatement(sql); 
			// Paramétrer la valeur du paramètre de la requête avec l'ID du fournisseur à
			// supprimer
			statement.setInt(1, fournisseur.getId());

			// Exécuter la requête et vérifier si une ligne a été affectée
			int rowsAffected = statement.executeUpdate();
		
			statement.close();
			connection.close();
			return rowsAffected > 0; // Retourner vrai si au moins une ligne a été affectée
			

	}

}