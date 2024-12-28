
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

import javax.swing.border.LineBorder;



public class principale {
	JFrame j;
	JMenuBar jm;
	JMenu fichier, affichage, aide;
	JMenuItem fenre, fqui, page1, page2, page3, apro;
    JPanel mainPanel, sidebarPanel;
    JButton générer, année, affich;

	public principale() {
		demarer();
	}
	
	public void demarer() {
		
		j = new JFrame("Générer les cartes d'Etudiants");
		j.setSize(900, 600);
		j.setLayout(new BorderLayout());
		j.setResizable(false);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jm = new JMenuBar();
		fichier = new JMenu("Fichier");
		affichage = new JMenu("Affichage");
		aide = new JMenu("Aide");
		
		fenre = new JMenuItem("Enregistrer");
		fqui = new JMenuItem("Quitter");
		fqui.addActionListener(e -> {
		    int confirm = JOptionPane.showConfirmDialog(
		        j, 
		        "Êtes-vous sûr de vouloir quitter l'application ?", 
		        "Confirmation", 
		        JOptionPane.YES_NO_OPTION
		    );
		    if (confirm == JOptionPane.YES_OPTION) {
		        System.exit(0);
		    }
		});
		
		fichier.add(fenre);
		fichier.addSeparator();
		fichier.add(fqui);
		
		page1 = new JMenuItem("Page_1");
		page2 = new JMenuItem("Page_2");
		page3 = new JMenuItem("page_3");
		
		 // Actions des menus
        page1.addActionListener(e -> showPage("Page_1"));
        page2.addActionListener(e -> showPage("Page_2"));
        page3.addActionListener(e -> showPage("Page_3"));
        
		affichage.add(page1);
		affichage.add(page2);
		affichage.add(page3);
		
		apro = new JMenuItem("A propos");
		apropos app = new apropos(j);
		aide.add(app.getMenuItem());
		
		jm.add(fichier);
		jm.add(affichage);
		jm.add(aide);
		
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout()); // Utilisation de CardLayout pour changer facilement les panneaux
        
        j.add(mainPanel, BorderLayout.CENTER);
        
        
        // Création du panneau de la barre latérale
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS)); // Disposition verticale
        sidebarPanel.setBackground(Color.blue);
        sidebarPanel.setPreferredSize(new Dimension(200, 600)); // Taille préférée

        // Boutons
         générer = new JButton("Générer la carte");
         année = new JButton("Année universitaire");
         affich = new JButton("Historique");
         
        générer.addActionListener(e -> showPage("Page_1"));
        générer.setMaximumSize(new Dimension(198, 30));
        
        affich.addActionListener(e -> showPage("Page_2"));
        affich.setMaximumSize(new Dimension(198, 30));
        
        année.addActionListener(e -> JOptionPane.showInputDialog(j, "Entrer l'Année Universitaire"));
        année.setMaximumSize(new Dimension(198, 30));
        
        générer.setFont(new Font("Arial", Font.BOLD, 16)); // Taille de police 16
        année.setFont(new Font("Arial", Font.BOLD, 16));
        affich.setFont(new Font("Arial", Font.BOLD, 16));
        
        générer.setBackground(Color.YELLOW);
        année.setBackground(Color.YELLOW);
        affich.setBackground(Color.YELLOW);
        
        // Espaces entre les composants
        sidebarPanel.add(Box.createRigidArea(new Dimension(10, 20))); // Espacement en haut
        sidebarPanel.add(new JLabel("Générer des cartes")); // Titre
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement
        sidebarPanel.add(générer); // Ajouter le bouton
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement
        sidebarPanel.add(affich); // Ajouter le bouton
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement
        sidebarPanel.add(année); // Ajouter le bouton
        sidebarPanel.setBorder(new LineBorder(Color.black,2));
        j.add(sidebarPanel, BorderLayout.WEST); // Ajout à gauche

  
        
		j.setJMenuBar(jm);
		// Initialisation des panneaux
        initPages();
		j.setVisible(true);

		
	}
	

    private void initPages() {
    	
    	JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(Color.WHITE); 
        // Panneau pour Page 1
        ajouterEtudiant aj = new ajouterEtudiant();
        JPanel page1Panel = aj.getDefaultPanel();

        // Panneau par défaut pour Page 2
        afficherEtudiant  listEtudiant = new afficherEtudiant();
        JPanel page2Panel = listEtudiant.getDefaultPanel();

        // Panneau par défaut pour Page 3
        JPanel page3Panel = new JPanel();
        page3Panel.setBackground(Color.PINK);
        page3Panel.add(new JLabel("Ceci est la Page 3"));

        // Ajouter les panneaux au mainPanel
        mainPanel.add(defaultPanel, "Default");
        mainPanel.add(page1Panel, "Page_1");
        mainPanel.add(page2Panel, "Page_2");
        mainPanel.add(page3Panel, "Page_3");
    }
    
    private void showPage(String pageName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, pageName);
        
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         principale  p = new principale();
	}
}