import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class info extends JFrame implements ActionListener{
	
	private JButton exit;
	private JPanel menu;
	private JPanel pane;
	private JPanel pane2;
	private JScrollPane scrollPane;
	
	public info(){
		ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());
        
        ComponentResizer cr = new ComponentResizer(this);
        
        exit = new JButton("×");
        exit.setFont(new Font("Verdana", 0, 27));
        exit.setMaximumSize(new Dimension(40, 40));
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.addActionListener(this);
        exit.setFocusPainted(false);
        
        menu = new JPanel();
        menu.add(new JLabel("  "));
        menu.setMaximumSize(new Dimension(999999999, 40));
        menu.setComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT );
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        menu.add(exit);
        
        pane = new JPanel();
        pane2 = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane2.setLayout(new BoxLayout(pane2, BoxLayout.PAGE_AXIS));
        
        scrollPane = new JScrollPane(pane2);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        scrollPane.getVerticalScrollBar().setBounds(999999999, 999999999, 6, 999999999);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        this.add(pane);
        pane.add(menu);
        pane.add(scrollPane);
        
        pane.setBackground(Color.white);
        scrollPane.getVerticalScrollBar().setBackground(Color.white);
        exit.setBackground(Color.white);
        exit.setForeground(Color.DARK_GRAY);
        menu.setBackground(Color.white);
        pane2.setBackground(Color.white);
        
        this.setUndecorated(true); 
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        this.setSize(500,450);
        FrameDragListener frameDragListener = new FrameDragListener(this, menu);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit){
			Settings.infoState = false;
			this.dispose();
		}
		
	}
	
}
