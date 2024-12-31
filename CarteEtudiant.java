import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.LineBorder;

public class CarteEtudiant extends JFrame {
    private JPanel cardPanel;
    private String nom, prenom, cne, date, cni, pays;
    private ImageIcon photo;
    private String genre;
    private String diplomes;
    
    public CarteEtudiant(String nom, String prenom, String cne, String date, 
                        String cni, String pays, String genre, 
                        String diplomes, ImageIcon photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.date = date;
        this.cni = cni;
        this.pays = pays;
        this.genre = genre;
        this.diplomes = diplomes;
        this.photo = photo;
        
        setTitle("Carte d'Étudiant");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initializeCard();
    }
    
    private void initializeCard() {
        cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Améliorer la qualité du rendu
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                
             // Définir les couleurs en fonction du diplôme
                Color startColor = Color.WHITE;
                Color endColor;
                
                // Sélection de la couleur en fonction du diplôme
                switch (diplomes.toLowerCase().trim()) {
                    case "licence":
                        endColor = new Color(135, 206, 235); // Bleu ciel
                        break;
                    case "master":
                        endColor = new Color(192, 192, 192); // Gris
                        break;
                    case "doctorat":
                        endColor = new Color(230, 230, 250); // Lavande
                        break;
                    default:
                        endColor = new Color(230, 230, 250); // Couleur par défaut
                }
                
                // Fond de la carte
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255), 
                                                         getWidth(), getHeight(), 
                                                         endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // En-tête
                g2d.setColor(new Color(0, 51, 102)); // Bleu plus foncé pour UMI
                g2d.fillRect(0, 0, getWidth(), 100); // En-tête plus grand
                
                // Titre de l'université
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                g2d.drawString("UNIVERSITÉ MOULAY ISMAIL", 220, 35);
                
                // Sous-titre faculté
                g2d.setFont(new Font("Arial", Font.BOLD, 18));
                g2d.drawString("Faculté des Sciences et Techniques Errachidia", 180, 65);
                
                // Logo UMI (simulé par un cercle plus élaboré)
                g2d.setColor(new Color(0, 51, 102));
                g2d.fillOval(50, 120, 100, 100);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 28));
                g2d.drawString("UMI", 70, 180);
                
             // Ajouter les logos
                try {
                    // Charger les logos
                    BufferedImage logoUMI = ImageIO.read(new File("images/Logo_UMI.jpg")); // Chemin du logo UMI
                    BufferedImage logoFST = ImageIO.read(new File("images/Logo_FST.jpg")); // Chemin du logo FST

                    // Dessiner les logos
                    g2d.drawImage(logoUMI, 	10, 10, 150, 80, null); // Position et taille pour le logo UMI
                    g2d.drawImage(logoFST, getWidth() - 170, 10, 150, 80, null); // Position et taille pour le logo FST
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                // Photo de l'étudiant
                if (photo != null) {
                    Image img = photo.getImage();
                    g2d.drawImage(img, 600, 120, 150, 150, null);
                    // Ajouter une bordure à la photo
                    g2d.setColor(new Color(0, 51, 102));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(600, 120, 150, 150);
                }
                
                // Informations de l'étudiant
                g2d.setColor(new Color(0, 51, 102));
                g2d.setFont(new Font("Arial", Font.BOLD, 16));
                
                int startY = 270;
                int lineHeight = 30;
                
                g2d.drawString("Nom: " + nom.toUpperCase(), 50, startY);
                g2d.drawString("Prénom: " + prenom, 50, startY + lineHeight);
                g2d.drawString("CNE: " + cne, 50, startY + 2 * lineHeight);
                g2d.drawString("Date de naissance: " + date, 50, startY + 3 * lineHeight);
                g2d.drawString("CNI: " + cni, 400, startY + 3 * lineHeight);
                g2d.drawString("Nationalité: " + pays, 400, startY + lineHeight);
                g2d.drawString("Genre: " + genre, 400, startY);
                g2d.drawString("Diplôme: " + diplomes, 400, startY +  2 * lineHeight);
                
                // Bordure de la carte
                g2d.setColor(new Color(0, 51, 102));
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRect(10, 10, getWidth() - 20, getHeight() - 20);
            }
        };
        
        cardPanel.setPreferredSize(new Dimension(800, 500));
        add(cardPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        // Bouton imprimer
        JButton printButton = new JButton("Imprimer la carte");
        printButton.setFont(new Font("Arial", Font.BOLD, 14));
        printButton.addActionListener(e -> printCard());
        
        // Bouton télécharger
        JButton downloadButton = new JButton("Télécharger la carte");
        downloadButton.setFont(new Font("Arial", Font.BOLD, 14));
        downloadButton.addActionListener(e -> downloadCard());
        
        buttonPanel.add(printButton);
        buttonPanel.add(downloadButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void printCard() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }
            
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            
            // Mettre à l'échelle pour l'impression
            double scaleX = pageFormat.getImageableWidth() / cardPanel.getWidth();
            double scaleY = pageFormat.getImageableHeight() / cardPanel.getHeight();
            double scale = Math.min(scaleX, scaleY);
            
            g2d.scale(scale, scale);
            
            cardPanel.print(g2d);
            
            return Printable.PAGE_EXISTS;
        });
        
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Erreur lors de l'impression: " + ex.getMessage(),
                    "Erreur d'impression",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void downloadCard() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sauvegarder la carte");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Images PNG", "png"));
            
        // Proposer un nom de fichier par défaut
        String defaultFileName = "carte_" + nom.toLowerCase() + "_" + prenom.toLowerCase() + ".png";
        fileChooser.setSelectedFile(new File(defaultFileName));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Ajouter l'extension .png si elle n'est pas présente
            if (!file.getName().toLowerCase().endsWith(".png")) {
                file = new File(file.getAbsolutePath() + ".png");
            }
            
            try {
                // Créer une image de la carte
                BufferedImage image = new BufferedImage(
                    cardPanel.getWidth(),
                    cardPanel.getHeight(),
                    BufferedImage.TYPE_INT_RGB
                );
                Graphics2D g2d = image.createGraphics();
                cardPanel.paint(g2d);
                g2d.dispose();
                
                ImageIO.write(image, "png", file);
                
                JOptionPane.showMessageDialog(this,
                    "Carte sauvegardée avec succès !",
                    "Téléchargement réussi",
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Erreur lors de la sauvegarde: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}