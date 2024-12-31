import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ajouterEtudiant {
    
    JPanel gen, titre, info, photo;
    JLabel lcne, lnom ,lprenom, ldat, lemail, lgenre, lpays, ldiplome, lphoto , jphoto, ltitre;
    JTextField tcne, tnom ,tprenom, tdat, tcni;
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
        info.setBackground(new Color(200,200,200));
        
        lcne = new JLabel("CNE");
        lcne.setFont(new Font("Consolas", Font.PLAIN, 18));
        lcne.setOpaque(true);
        lcne.setBackground(new Color(200,200,200));
        lcne.setBounds(30, 20, 130, 30);
        
        lnom = new JLabel("NOM");
        lnom.setFont(new Font("Consolas", Font.PLAIN, 18));
        lnom.setBackground(new Color(200,200,200));
        lnom.setOpaque(true);
        lnom.setBounds(30, 60, 130, 30);
        
        lprenom = new JLabel("Prénom");
        lprenom.setFont(new Font("Consolas", Font.PLAIN, 18));
        lprenom.setBackground(new Color(200,200,200));
        lprenom.setOpaque(true);
        lprenom.setBounds(30, 100, 130, 30);
        
        ldat = new JLabel("Date");
        ldat.setFont(new Font("Consolas", Font.PLAIN, 18));
        ldat.setBackground(new Color(200,200,200));
        ldat.setOpaque(true);
        ldat.setBounds(30, 140, 130, 30);
        
        lemail = new JLabel("CNI");
        lemail.setFont(new Font("Consolas", Font.PLAIN, 18));
        lemail.setBackground(new Color(200,200,200));
        lemail.setOpaque(true);
        lemail.setBounds(30, 180, 130, 30);
        
        lgenre = new JLabel("Genre");
        lgenre.setFont(new Font("Consolas", Font.PLAIN, 18));
        lgenre.setBackground(new Color(200,200,200));
        lgenre.setOpaque(true);
        lgenre.setBounds(30, 220, 130, 30);
        
        lpays = new JLabel("Pays");
        lpays.setFont(new Font("Consolas", Font.PLAIN, 18));
        lpays.setBackground(new Color(200,200,200));
        lpays.setOpaque(true);
        lpays.setBounds(30, 260, 130, 30);
        
        ldiplome = new JLabel("Diplome");
        ldiplome.setFont(new Font("Consolas", Font.PLAIN, 18));
        ldiplome.setBackground(new Color(200,200,200));
        ldiplome.setOpaque(true);
        ldiplome.setBounds(30, 300, 130, 30);
        
        lphoto = new JLabel("Photo");
        lphoto.setFont(new Font("Consolas", Font.PLAIN, 18));
        lphoto.setBackground(new Color(200,200,200));
        lphoto.setOpaque(true);
        lphoto.setBounds(30, 340, 130, 30);
        
        info.add(lcne);
        info.add(lnom);
        info.add(lprenom);
        info.add(ldat);
        info.add(lemail);
        info.add(lgenre);
        info.add(lpays);
        info.add(ldiplome);
        info.add(lphoto);
        
        tcne = new JTextField();
        tcne.setBounds(200, 20, 270, 33);
        tcne.setFont(new Font("Consolas", Font.PLAIN, 16));
        tcne.setBorder(new LineBorder(Color.BLUE, 4));
        
        tnom = new JTextField();
        tnom.setBounds(200, 60, 270, 33);
        tnom.setFont(new Font("Consolas", Font.PLAIN, 16));
        tnom.setBorder(new LineBorder(Color.BLUE, 4));
        
        tprenom = new JTextField();
        tprenom.setBounds(200, 100, 270, 33);
        tprenom.setFont(new Font("Consolas", Font.PLAIN, 16));
        tprenom.setBorder(new LineBorder(Color.BLUE, 4));
        
        tdat = new JTextField();
        tdat.setFont(new Font("Consolas", Font.PLAIN, 16));
        tdat.setBounds(200, 140, 270, 33);
        tdat.setBorder(new LineBorder(Color.BLUE, 4));
        
        tcni = new JTextField();
        tcni.setBounds(200, 180, 270, 33);
        tcni.setBorder(new LineBorder(Color.BLUE, 4));
        
        eff = new JButton("Effacer");
        eff.setBounds(160, 440, 120, 40);  // Ajusté la position Y
        eff.setBackground(new Color(0,233,255));
        eff.addActionListener(this::effacerChamps);
        eff.setFont(new Font("Arial", Font.BOLD, 16));
        eff.setBorder(new LineBorder(Color.YELLOW, 5));
        
        enr = new JButton("Génére la Carte");
        enr.setBounds(300, 440, 180, 40);  // Ajusté la position Y
        enr.setBackground(new Color(0,233,255));
        enr.setFont(new Font("Arial", Font.BOLD, 16));
        enr.setBorder(new LineBorder(Color.YELLOW, 5));
        
        enr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer toutes les valeurs du formulaire
            	
            	String nomValue= tnom.getText();
                String prenomValue = tprenom.getText();
                String cneValue = tcne.getText();
                String dateValue = tdat.getText();
                String cniValue = tcni.getText();
                String paysValue = (String) gpays.getSelectedItem();
                
                // Déterminer le genre sélectionné
                String genreValue = homme.isSelected() ? "Homme" : "Femme";
                
                // Construire la liste des diplômes sélectionnés
                StringBuilder diplomes = new StringBuilder();
                if (li.isSelected()) diplomes.append("Licence ");
                if (ma.isSelected()) diplomes.append("Master ");
                if (doc.isSelected()) diplomes.append("Doctorat");
             // Récupérer la photo
                ImageIcon photoIcon = (ImageIcon) jphoto.getIcon();
            	
            	if (cneValue.trim().isEmpty() || nomValue.trim().isEmpty() ||
            			prenomValue.trim().isEmpty() || dateValue.trim().isEmpty() || 
            			cniValue.trim().isEmpty()) {
                	    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoires.", 
                	                                  "Erreur", JOptionPane.ERROR_MESSAGE);
                	    return;
                }
            	else {
            		 // Créer et afficher la carte
                    CarteEtudiant carte = new CarteEtudiant(
                        nomValue, prenomValue, cneValue, dateValue, 
                        cniValue, paysValue, genreValue, 
                        diplomes.toString(), photoIcon
                    );
                    carte.setVisible(true);
            	}
                
            }
        });
        
        info.add(tcne);
        info.add(tnom);
        info.add(tprenom);
        info.add(tdat);
        info.add(tcni);
        info.add(eff);
        info.add(enr);
        
        homme = new JRadioButton("Homme", true);
        homme.setFont(new Font("Consolas", Font.PLAIN, 18));
        homme.setBackground(new Color(200,200,200));
        homme.setBounds(200, 220, 91, 33);
        
        femme = new JRadioButton("Femme");
        femme.setFont(new Font("Consolas", Font.PLAIN, 18));
        femme.setBackground(new Color(200,200,200));
        femme.setBounds(370, 220, 91, 30);
        
        ButtonGroup gr = new ButtonGroup();
        gr.add(homme);
        gr.add(femme);
        
        info.add(homme);
        info.add(femme);
        
        String pay[] = {"Maroc", "Tunisie", "U.S.A", "Egypt", "Italy", "Mali"};
        gpays = new JComboBox(pay);
        gpays.setBackground(new Color(200,200,200));
        gpays.setFont(new Font("Consolas", Font.PLAIN, 18));
        gpays.setBounds(200, 260, 270, 33);
        gpays.setBorder(new LineBorder(Color.BLUE, 4));
        
        info.add(gpays);
        
        li = new JCheckBox("Licence");
        li.setFont(new Font("Consolas", Font.PLAIN, 16));
        li.setBackground(new Color(200,200,200));
        li.setBounds(200, 300, 90, 30);
        
        ma = new JCheckBox("Master");
        ma.setFont(new Font("Consolas", Font.PLAIN, 16));
        ma.setBackground(new Color(200,200,200));
        ma.setBounds(300, 300, 90, 30);
        
        doc = new JCheckBox("Doctorat");
        doc.setFont(new Font("Consolas", Font.PLAIN, 16));
        doc.setBackground(new Color(200,200,200));
        doc.setBounds(400, 300, 100, 30);
        
        info.add(li);
        info.add(ma);
        info.add(doc);
        
        photo = new JPanel();
        photo.setBackground(new Color(200,200,200));
        photo.setOpaque(true);
        photo.setLayout(null);
        photo.setPreferredSize(new Dimension(335, 0));
        
        uploadButton = new JButton("Choisir Photo");
        uploadButton.setFont(new Font("Consolas", Font.PLAIN, 18));
        uploadButton.setBounds(200, 340, 270, 33);
        uploadButton.setBorder(new LineBorder(Color.BLUE, 5));
        info.add(uploadButton);
        
        jphoto = new JLabel();
        jphoto.setBounds(0, 70, 300, 300);
        jphoto.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        
        ImageIcon imageIcon = new ImageIcon("images\\unknow.jpg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        jphoto.setIcon(imageIcon);
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
                    ImageIcon imageIcon = new ImageIcon(filePath);
                    Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    jphoto.setIcon(new ImageIcon(image));
                    jphoto.setText("");
                }
            }
        });
        
        gen.add(info);
        return gen;
    }
    
    private void effacerChamps(ActionEvent e) {
        tcne.setText("");
        tnom.setText("");
        tprenom.setText("");
        tcni.setText("");
        tdat.setText("");
        ImageIcon imageIcon = new ImageIcon("images\\unknow.jpg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        jphoto.setIcon(imageIcon);
    }
}