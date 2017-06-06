import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Settings extends JFrame implements ActionListener{
    
    private JButton exit;
    private Stickies sticky;
    private Color blue = new Color(195, 214, 244);
    private Color yellow = new Color(255, 255, 179);
    private Color green = new Color(206, 239, 206);
    private Color gray = new Color(209, 209, 209);
    private Color purple = new Color(214, 199, 219);
    private Color red = new Color(237, 192, 211);
    
    public Settings (Stickies s){
        sticky = s;
       
        this.setSize(s.getFrame().getSize());
        this.setLocation(s.getFrame().getLocation());
        
        this.setUndecorated(true);
        
        JPanel pane = new JPanel();
        pane.setBackground(s.getColor());
        this.add(pane);
        this.setVisible(true);
        
        exit = new JButton("×");
        exit.setFont(new Font("Verdana", 0, 27));
        exit.setMaximumSize(new Dimension(40, 40));
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setBackground(s.getColor());
        exit.setForeground(s.getColor2());
        exit.addActionListener(this);
        
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        
        JPanel menu = new JPanel();
        menu.setBackground(s.getColor());
        menu.setComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT );
        menu.add(exit);
        menu.add(new JLabel("  "));
        menu.setMaximumSize(new Dimension(999999999, 40));
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        
        JPanel colorMenu = new JPanel();
        colorMenu.setBackground(s.getColor());
//        colorMenu.
        
        pane.add(menu);
        
    }

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit){
            this.dispose();
        }
	}

}