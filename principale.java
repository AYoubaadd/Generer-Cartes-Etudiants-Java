import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class principale {
    JFrame j;
    JMenuBar jm;
    JMenu aide,fichier ,affichage;
    JMenuItem fenre,fqui,page1,page2,page3,apro;
    JPanel mainPanel;
    JButton générer, année, affich, SeDecon;
    JLabel lblTitre,imageLabel;

    public principale() {
        demarer();
    }

    public void demarer() {
        j = new JFrame(" Générer les cartes d'Etudiants");
        j.setSize(1200, 700);
        j.setLayout(null); // Utilisation de null pour positionnement absolu
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLocationRelativeTo(null);

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
        mainPanel.setLayout(new CardLayout());
        mainPanel.setBounds(250, 0, 950, 700); // Positionner mainPanel
        
     // Redimensionner l'image et l'ajouter à la Page 3
        ImageIcon originalIcon = new ImageIcon("images/photo.jpg"); // Charger l'image
        Image originalImage = originalIcon.getImage(); // Obtenir l'image
        Image scaledImage = originalImage.getScaledInstance(930, 690, Image.SCALE_SMOOTH); // Redimensionner l'image
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Créer un nouveau ImageIcon avec l'image redimensionnée

        // Créer un JLabel avec l'image redimensionnée
        JLabel imageLabel = new JLabel(scaledIcon); 

        // Positionner l'image avec setBounds
        imageLabel.setBounds(10, 10, 930, 690); // Par exemple, 10 pixels de marge à gauche et en haut, puis taille de l'image

        // Ajouter l'image au mainPanel
        mainPanel.add(imageLabel);
        
        j.add(mainPanel);

        // Barre latérale
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(null); // Utilisation de null pour positionnement absolu
        sidebarPanel.setBackground(new Color(20,167,173));
        sidebarPanel.setBounds(0, 0, 250, 700); // Positionner la barre latérale
        sidebarPanel.setBorder(new LineBorder(Color.BLACK, 2));

        // Boutons
        générer = new JButton("Générer la carte");
        année = new JButton("Année universitaire");
        affich = new JButton("Historique");
        SeDecon = new JButton("Quitter");

        générer.setBounds(10, 100, 236, 50); // Positionner générer
        affich.setBounds(10, 160, 236, 50); // Positionner affich
        année.setBounds(10, 220, 236, 50); // Positionner année
        SeDecon.setBounds(10, 280, 236, 50); // Positionner SeDecon

        générer.addActionListener(e -> showPage("Page_1"));
        affich.addActionListener(e -> showPage("Page_2"));
        année.addActionListener(e ->{
        	fichier f = new fichier();
        	String s =JOptionPane.showInputDialog(j, "Entrer l'Année Universitaire",f.afficherAnne());
        	if (s!=null)
        	    f.setAnneeUni(s);
        	});        
        SeDecon.addActionListener(e -> {
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

        styleButton(générer);
        styleButton(année);
        styleButton(affich);
        styleButton(SeDecon);

        lblTitre = new JLabel(" Générer des cartes");
        lblTitre.setForeground(Color.WHITE);
        lblTitre.setFont(new Font("Consolas", Font.PLAIN, 20));
        lblTitre.setBounds(10, 20, 230, 40); // Positionner le titre

        sidebarPanel.add(lblTitre);
        sidebarPanel.add(générer);
        sidebarPanel.add(affich);
        sidebarPanel.add(année);
        sidebarPanel.add(SeDecon);

        j.add(sidebarPanel);

        j.setJMenuBar(jm);

        // Initialisation des pages
        initPages();

        j.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Consolas", Font.BOLD, 18));
        button.setBackground(Color.YELLOW);
    }

    private void initPages() {
        // Panneau par défaut
        JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(Color.WHITE);

        // Page 1
        ajouterEtudiant aj = new ajouterEtudiant();
        JPanel page1Panel = aj.getDefaultPanel();

        // Page 2
        afficherEtudiant listEtudiant = new afficherEtudiant();
        JPanel page2Panel = listEtudiant.getDefaultPanel();

        // Page 3
        JPanel page3Panel = new JPanel();
        page3Panel.setBackground(Color.PINK);
        JLabel imageLabel = new JLabel(new ImageIcon("photo.jpg"));
        page3Panel.add(imageLabel);

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
        SwingUtilities.invokeLater(principale::new);
    }
}
