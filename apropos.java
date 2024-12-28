import javax.swing.*;


public class apropos {

	    private JMenuItem apro;

	    public apropos(JFrame parentFrame) {
	        apro = new JMenuItem("A propos");
	        apro.addActionListener(e -> JOptionPane.showMessageDialog(parentFrame, "Version '1.0' AYOUB ADDICHANE & ANWAR BAHIDA"));
	    }

	    
	    public JMenuItem getMenuItem() {
	        return apro;
	    }

}