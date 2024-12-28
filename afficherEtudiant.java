import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class afficherEtudiant {
	JPanel gene, trier, boutt, tab;
	JRadioButton jcne, jnom, jdatnais;
	JButton actualiser;
	JLabel jl, jrech;
	JTextField trech;
	ButtonGroup but;
	DefaultTableModel tableModel;
	JTable tableau;
	String [] columnNames = new String[] {"id","CNE","Nom","Prénom", "Date_nais", "CNI", "Genre", "diplome", "Année Universitaire"};


	   // Récupérer les étudiants depuis la base de données
	   private void loadStudentsData(String recherch) {
		MySqlConnection m = new MySqlConnection();
		try (Connection conn = m.getConnection();
			 Statement stmt = conn.createStatement()) {

	                // Construire la requête SQL avec le critère de recherche
            String query;
            if (recherch == null || recherch.trim().isEmpty()) {
                // Si aucun critère de recherche n'est fourni, sélectionner tous les étudiants
                query = "SELECT * FROM etudiants";
            } else {
                    // Ajouter une clause WHERE pour rechercher les étudiants
                    query = "SELECT * FROM etudiants WHERE Nom LIKE '%" + recherch + "%' OR CNE LIKE '%" + recherch + "%'";
                   }

            ResultSet rs = stmt.executeQuery(query);
	
			// Effacer les lignes précédentes dans le modèle
			tableModel.setRowCount(0);
	
			// Ajouter les nouvelles lignes
			while (rs.next()) {
				Object[] row = new Object[9];
				row[0] = rs.getInt("id");
				row[1] = rs.getString("CNE");
				row[2] = rs.getString("Nom");
				row[3] = rs.getString("Prenom");
				row[4] = rs.getDate("date_naissance"); // Assurez-vous que la colonne Date_nais est du bon type
				row[5] = rs.getString("CNI");
				row[6] = rs.getString("Genre");
				row[7] = rs.getString("diplome");
				row[8] = rs.getString("Annee_U"); // Assurez-vous de la bonne colonne
	
				tableModel.addRow(row);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erreur lors du chargement des données", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}


	 public JPanel getDefaultPanel() {
		 
        // Panneau principal
        gene = new JPanel();
        gene.setLayout(new BorderLayout());
        gene.setBackground(Color.darkGray);
        gene.setOpaque(true);

        // Panneau de tri
        trier = new JPanel();
        trier.setBackground(Color.YELLOW);
        trier.setOpaque(true);
        trier.setLayout(null);
        trier.setPreferredSize(new Dimension(600, 100));

        // Label Trier par:
        jl = new JLabel("Trier par:");
        jl.setBounds(10, 15, 100, 20);
        jl.setFont(new Font("Arial", Font.BOLD, 18));
        trier.add(jl);

        // JRadioButton pour CNE
        jcne = new JRadioButton("CNE");
        jcne.setBounds(110, 15, 80, 20);
        jcne.setBackground(Color.yellow);
        trier.add(jcne);

        // JRadioButton pour Nom
        jnom = new JRadioButton("Nom");
        jnom.setBounds(190, 15, 80, 20);
        jnom.setBackground(Color.yellow);
        trier.add(jnom);

        // JRadioButton pour DateNaiss
        jdatnais = new JRadioButton("DateNaiss");
        jdatnais.setBounds(270, 15, 110, 20);
        jdatnais.setBackground(Color.yellow);
        trier.add(jdatnais);

        // Groupe de boutons
        but = new ButtonGroup();
        but.add(jcne);
        but.add(jnom);
        but.add(jdatnais);

        

        // Label Recherche
        jrech = new JLabel("Recherch :");
        jrech.setFont(new Font("Arial", Font.PLAIN, 18));
        jrech.setBounds(10, 50, 110, 20);
        trier.add(jrech);

        // Champ de texte pour la recherche
        trech = new JTextField();
        trech.setBounds(150, 51, 200, 25);
		trech.setBorder(new LineBorder(Color.BLUE, 4));
        trier.add(trech);

		// Bouton Actualiser
        actualiser = new JButton("Actualiser");
		actualiser.addActionListener(e -> loadStudentsData(trech.getSelectedText()));
        actualiser.setBounds(510, 50, 145, 30);
        actualiser.setBackground(Color.BLUE);
        actualiser.setFont(new Font("Arial", Font.BOLD, 18));
        trier.add(actualiser);
        // Panneau du tableau
        tab = new JPanel();
        tab.setBackground(Color.white);
        tab.setOpaque(true);
        tab.setLayout(new BorderLayout());

        // Initialisation du modèle de tableau
        tableModel = new DefaultTableModel(columnNames, 0);
        tableau = new JTable(tableModel);
        tableau.setFillsViewportHeight(true);
		tableau.setFont(new Font("Arial", Font.BOLD, 16));
        tableau.setBorder(new LineBorder(Color.YELLOW, 5));

        // Création du JScrollPane pour rendre le tableau défilable
        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.setPreferredSize(new Dimension(550, 200)); // Ajustement de la taille du JScrollPane

        // Ajout du JScrollPane dans le panneau du tableau
        tab.add(scrollPane, BorderLayout.CENTER);

        // Ajout des panneaux dans le panneau principal
        gene.add(trier, BorderLayout.NORTH);
        gene.add(tab, BorderLayout.CENTER);

        return gene;
	 }
}
