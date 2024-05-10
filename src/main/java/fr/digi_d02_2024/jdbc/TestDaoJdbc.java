package fr.digi_d02_2024.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDao;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc2;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.SQLException;
import java.util.List;

public class TestDaoJdbc {
	public static void main(String[] args) throws SQLException {
		// Insérer un fournisseur
		FournisseurDao fournisseurDao = new FournisseurDaoJdbc2();
		try {
			fournisseurDao.insert(new Fournisseur (" France de matériaux"));
			
			// Extraire et afficher la liste des fournisseurs
			List<Fournisseur> fournisseurs = fournisseurDao.extraire();
			for (Fournisseur f : fournisseurs) {
				System.out.println(f.getId() + " : " + f.getNom());
			}
			
			// Modifier un fournisseur
			fournisseurDao.update("France de matériaux", "France matériaux");
			
			// Supprimer un fournisseur
			fournisseurDao.delete(new Fournisseur(1, "France matériaux"));
			
			// Extraire et afficher à nouveau la liste des fournisseurs
			fournisseurs = fournisseurDao.extraire();
			for (Fournisseur f : fournisseurs) {
				System.out.println(f.getId() + " : " + f.getNom());
			}
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		 
	}
}