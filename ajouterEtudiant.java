import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ajouterEtudiant {
	
	JPanel gen, titre, info, photo;
	JLabel lcne, lnom ,lprenom, ldat, lemail, lpassword, lgenre, lpays, ldiplome, lphoto , jphoto, ltitre;
	JTextField tcne, tnom ,tprenom, tdat, tcni, tpassword;
	JButton eff, enr, uploadButton;
	JRadioButton homme, femme;
	JComboBox gpays;
	JCheckBox li, ma, doc;
	
    public JPanel getDefaultPanel() {
    	
        gen = new JPanel();
        gen.setLayout(new BorderLayout());
        
        titre = new JPanel();
        ltitre = new JLabel("Ajout d'un Etudiant");
        ltitre.setFont(new Font("Arial", Font.BOLD, 22));
        titre.add(ltitre);
        titre.setBackground(Color.yellow);
        titre.setOpaque(true);
        titre.setPreferredSize(new Dimension(200,50));
        
        
        gen.add(titre, BorderLayout.NORTH);
        
        
        info = new JPanel();
        info.setOpaque(true);
        info.setPreferredSize(new Dimension(500,200));
        info.setLayout(null); 
        
        lcne = new JLabel("CNE");
        lcne.setOpaque(true);
        lcne.setBounds(10, 10, 130, 30);
        lcne.setBorder(new LineBorder(Color.YELLOW, 4));
        
        lnom = new JLabel("NOM");
        lnom.setOpaque(true);
        lnom.setBounds(10, 45, 130, 30);
        lnom.setBorder(new LineBorder(Color.YELLOW, 4));
        
        lprenom = new JLabel("Prénom");
        lprenom.setOpaque(true);
        lprenom.setBounds(10, 80, 130, 30);
        lprenom.setBorder(new LineBorder(Color.YELLOW, 4));
        
        ldat = new JLabel("Date");
        ldat.setOpaque(true);
        ldat.setBounds(10, 115, 130, 30);
        ldat.setBorder(new LineBorder(Color.YELLOW, 4));
        
        lemail = new JLabel("CNI");
        lemail.setOpaque(true);
        lemail.setBounds(10, 150, 130, 30);
        lemail.setBorder(new LineBorder(Color.YELLOW, 4));
        
        lpassword = new JLabel("Password");
        lpassword.setOpaque(true);
        lpassword.setBounds(10, 185, 130, 30);
        lpassword.setBorder(new LineBorder(Color.YELLOW, 4));
        
        lgenre = new JLabel("Genre");
        lgenre.setOpaque(true);
        lgenre.setBounds(10, 220, 130, 30);
        lgenre.setBorder(new LineBorder(Color.YELLOW, 4));
        
        lpays = new JLabel("Pays");
        lpays.setOpaque(true);
        lpays.setBounds(10, 255, 130, 30);
        lpays.setBorder(new LineBorder(Color.YELLOW, 4));
        
        ldiplome = new JLabel("Diplome");
        ldiplome.setOpaque(true);
        ldiplome.setBounds(10, 290, 130, 30);
        ldiplome.setBorder(new LineBorder(Color.YELLOW, 4));
        
        lphoto = new JLabel("Photo");
        lphoto.setOpaque(true);
        lphoto.setBounds(10, 325, 130, 30);
        lphoto.setBorder(new LineBorder(Color.YELLOW, 4));
        
        info.add(lcne);
        info.add(lnom);
        info.add(lprenom);
        info.add(ldat);
        info.add(lemail);
        info.add(lpassword);
        info.add(lgenre);
        info.add(lpays);
        info.add(ldiplome);
        info.add(lphoto);
        
        
        tcne = new JTextField();
        tcne.setBounds(165, 10, 200, 30);
        tcne.setBorder(new LineBorder(Color.BLUE, 4));
        
        tnom = new JTextField();
        tnom.setBounds(165, 45, 200, 30);
        tnom.setBorder(new LineBorder(Color.BLUE, 4));
        
        tprenom = new JTextField();
        tprenom.setBounds(165, 80, 200, 30);
        tprenom.setBorder(new LineBorder(Color.BLUE, 4));
        
        tdat = new JTextField();
        tdat.setBounds(165, 115, 200, 30);
        tdat.setBorder(new LineBorder(Color.BLUE, 4));
        
        tcni = new JTextField();
        tcni.setBounds(165, 150, 200, 30);
        tcni.setBorder(new LineBorder(Color.BLUE, 4));
        
        tpassword = new JTextField();
        tpassword.setBounds(165, 185, 200, 30);
        tpassword.setBorder(new LineBorder(Color.BLUE, 4));
        
        
        eff = new JButton("Effacer");
        eff.setBounds(90, 380, 120, 40);
        eff.setBackground(Color.BLUE);
        eff.addActionListener(this::effacerChamps);
        eff.setFont(new Font("Arial", Font.BOLD, 16));
        eff.setBorder(new LineBorder(Color.YELLOW, 5));
        
        enr = new JButton("Génére la Carte");
        enr.setBounds(230, 380, 180, 40);
        enr.setBackground(Color.BLUE);
        enr.setFont(new Font("Arial", Font.BOLD, 16));
        enr.setBorder(new LineBorder(Color.YELLOW, 5));
        
        info.add(tcne);
        info.add(tnom);
        info.add(tprenom);
        info.add(tdat);
        info.add(tcni);
        info.add(tpassword);
        
        info.add(eff);
        info.add(enr);
        
        homme = new JRadioButton("Homme", true);
        homme.setBounds(165, 220, 91, 30);
        
        femme = new JRadioButton("Femme");
        femme.setBounds(270, 220, 91, 30);
        
        ButtonGroup gr = new ButtonGroup();
        gr.add(homme);
        gr.add(femme);
        
        info.add(homme);
        info.add(femme);
        
        String pay[] = {"Maroc", "Tunisie", "U.S.A", "Egypt", "Italy", "Mali"};
        gpays = new JComboBox(pay);
        gpays.setBounds(165, 255, 200, 30);
        gpays.setBorder(new LineBorder(Color.BLUE, 4));
        
        info.add(gpays);
        
        li = new JCheckBox("Licence");
        li.setBounds(165, 290, 90, 30);
        
        ma = new JCheckBox("Master");
        ma.setBounds(265, 290, 90, 30);
        
        doc = new JCheckBox("Doctorat");
        doc.setBounds(365, 290, 90, 30);
        
        info.add(li);
        info.add(ma);
        info.add(doc);
        
        photo = new JPanel();
        photo.setBackground(Color.GRAY);
        photo.setOpaque(true);
        photo.setLayout(null);
        // Définir une nouvelle taille préférée avec une hauteur réduite
        photo.setPreferredSize(new Dimension(245, 50)); // Largeur 250px, Hauteur 50px
        photo.setMaximumSize(new Dimension(245, 50));
        
        
        
        // Bouton pour uploader une photo
        uploadButton = new JButton("Choisir Photo");
        uploadButton.setBounds(165, 325, 200, 30);
        uploadButton.setBorder(new LineBorder(Color.BLUE, 5));
        info.add(uploadButton);
        
        jphoto = new JLabel();
        jphoto.setBounds(10, 10, 300, 300);
        jphoto.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        

         // Chargez l'image depuis le chemin spécifié
        ImageIcon imageIcon = new ImageIcon("unknown person.jpeg");

         // Ajustez la taille de l'image si nécessaire
         Image image = imageIcon.getImage(); // Récupère l'objet Image
         Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH); // Redimensionne l'image
         imageIcon = new ImageIcon(scaledImage); // Crée une nouvelle icône à partir de l'image redimensionnée

         jphoto.setIcon(imageIcon); // Définit l'image comme icône
         jphoto.setOpaque(true);
         
         photo.add(jphoto);
         gen.add(photo, BorderLayout.EAST);
         uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Fichiers Image", "jpg", "png", "jpeg"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    // Charger et afficher l'image
                    ImageIcon imageIcon = new ImageIcon(filePath);
                    Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    jphoto.setIcon(new ImageIcon(image));
                    jphoto.setText("");
                    }
            }
            } );

		gen.add(info);
        

        
        return gen;
        
    
	}
    private void effacerChamps(ActionEvent e) {
    	  
    	tcne.setText("");
    	tnom.setText("");
    	tprenom.setText("");
    	tcni.setText("");
    	tpassword.setText("");
    	tdat.setText("");
    }
}

