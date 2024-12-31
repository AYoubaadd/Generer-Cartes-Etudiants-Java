import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
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
    String[] columnNames = new String[]{"id", "CNE", "Nom", "Prénom", "Date_nais", "CNI", "Genre", "diplome", "AU"};
    String tri = "Nom"; // Par défaut, trier par Nom

 // Méthode pour initialiser le tableau avec un design amélioré
    private JTable createTable() {
        // Initialisation du modèle de tableau avec les noms de colonnes
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // Création du tableau avec le modèle
        JTable table = new JTable(tableModel);
        
        // Activer le remplissage du tableau dans le viewport
        table.setFillsViewportHeight(true);
        
        // Personnalisation du design du tableau
        
        // Définir la couleur de fond des entêtes de colonne
        table.getTableHeader().setBackground(new Color(51, 153, 255)); // Bleu clair
        table.getTableHeader().setForeground(Color.WHITE); // Texte blanc pour les entêtes
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Police en gras
        
        // Définir l'apparence des lignes
        table.setRowHeight(30); // Hauteur des lignes
        table.setFont(new Font("Arial", Font.PLAIN, 12)); // Police du texte des cellules
        
        // Appliquer une couleur de fond alternée pour les lignes
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(240, 240, 240)); // Gris clair pour les lignes paires
                } else {
                    c.setBackground(Color.WHITE); // Blanc pour les lignes impaires
                }
                return c;
            }
        });
        
        // Personnaliser l'alignement des colonnes
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer()); // Centrer les cellules de la première colonne
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer()); // Centrer la deuxième colonne
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer()); // Aligner à gauche pour la colonne "Nom"
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer()); // Aligner à gauche pour la colonne "Prénom"*/
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer()); // Centrer la colonne "Date_naissance"
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer()); // Centrer les cellules de la première colonne
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer()); // Centrer la deuxième colonne
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer()); // Aligner à gauche pour la colonne "Nom"
        table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer()); // Aligner à gauche pour la colonne "Prénom"*/
        
        // Retourner le tableau personnalisé
        return table;
    }

    // Méthode pour définir un alignement centré pour les cellules
    private DefaultTableCellRenderer centerRenderer() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        return renderer;
    }

    // Méthode pour charger les données des étudiants depuis la base de données
    private void loadStudentsData(String recherche, String tri) {
        String query = buildQuery(recherche, tri);

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (recherche != null && !recherche.trim().isEmpty()) {
                String rechercheLike = "%" + recherche + "%";
                stmt.setString(1, rechercheLike);
                stmt.setString(2, rechercheLike);
            }

            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0); // Réinitialiser le modèle du tableau

            while (rs.next()) {
                Object[] row = new Object[9];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("CNE");
                row[2] = rs.getString("Nom");
                row[3] = rs.getString("Prenom");
                row[4] = rs.getDate("date_naissance");
                row[5] = rs.getString("CNI");
                row[6] = rs.getString("Genre");
                row[7] = rs.getString("diplome");
                row[8] = rs.getString("Annee_U");
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement des données", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Construire la requête SQL en fonction des critères
    private String buildQuery(String recherche, String tri) {
        String query;
        if (recherche == null || recherche.trim().isEmpty()) {
            query = "SELECT * FROM etudiants ORDER BY " + tri;
        } else {
            query = "SELECT * FROM etudiants WHERE Nom LIKE ? OR CNE LIKE ? ORDER BY " + tri;
        }
        return query;
    }

    // Initialiser les composants graphiques
    public JPanel getDefaultPanel() {
        // Panneau principal
        gene = new JPanel(new BorderLayout());
        gene.setBackground(new Color(20,167,173));

        // Panneau de tri
        trier = createSortPanel();

        // Panneau de tableau
        tab = createTablePanel();

        // Ajouter les panneaux dans le panneau principal
        gene.add(trier, BorderLayout.NORTH);
        gene.add(tab, BorderLayout.CENTER);

        return gene;
    }

    // Créer le panneau de tri
    private JPanel createSortPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(600, 100));

        jl = new JLabel("Trier par:");
        jl.setBounds(10, 15, 100, 20);
        jl.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(jl);

        jcne = new JRadioButton("CNE");
        jcne.setBounds(110, 15, 80, 20);
        jcne.setBackground(Color.YELLOW);
        panel.add(jcne);

        jnom = new JRadioButton("Nom");
        jnom.setBounds(190, 15, 80, 20);
        jnom.setBackground(Color.YELLOW);
        panel.add(jnom);

        jdatnais = new JRadioButton("DateNaiss");
        jdatnais.setBounds(270, 15, 110, 20);
        jdatnais.setBackground(Color.YELLOW);
        panel.add(jdatnais);

        but = new ButtonGroup();
        but.add(jcne);
        but.add(jnom);
        but.add(jdatnais);

        jrech = new JLabel("Recherche :");
        jrech.setFont(new Font("Arial", Font.PLAIN, 18));
        jrech.setBounds(10, 50, 110, 20);
        panel.add(jrech);

        trech = new JTextField();
        trech.setBounds(150, 51, 200, 25);
        trech.setBorder(new LineBorder(Color.BLUE, 4));
        panel.add(trech);

        actualiser = new JButton("Actualiser");
        actualiser.addActionListener(e -> {
            if (jcne.isSelected()) tri = "CNE";
            else if (jnom.isSelected()) tri = "Nom";
            else if (jdatnais.isSelected()) tri = "date_naissance";
            else tri = "Nom";

            loadStudentsData(trech.getText(), tri);
        });
        actualiser.setBounds(510, 50, 145, 30);
        actualiser.setBackground(Color.BLUE);
        actualiser.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(actualiser);

        return panel;
    }

    // Créer le panneau du tableau
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(20,167,173));

        // Initialisation du tableau
        tableau = createTable();

        // Ajout du tableau dans un JScrollPane pour pouvoir défiler
        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.setPreferredSize(new Dimension(550, 200));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}
