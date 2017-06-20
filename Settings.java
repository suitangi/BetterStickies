import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class Settings extends JFrame implements ActionListener{
    
	public static boolean infoState = false;
	public static info infoFrame;
    private JButton exit;
    private Stickies sticky;
    private Color blue = new Color(166, 189, 234);
    private Color yellow = new Color(255, 255, 179);
    private Color green = new Color(206, 239, 206);
    private Color gray = new Color(193, 193, 193);
    private Color purple = new Color(194, 139, 232);
    private Color red = new Color(232, 139, 152);
    private JButton blueB;
    private JButton yellowB;
    private JButton greenB;
    private JButton grayB;
    private JButton purpleB;
    private JButton redB;
    private JPanel colorMenu;
    private JPanel menu;
    private JPanel menu2;
    private JPanel pane;
    private JPanel pane2;
    private JPanel styleMenu;
    private JPanel fontSizeMenu;
    private JPanel fontMenu;
    private JButton small;
    private JButton medium;
    private JButton large;
    private JButton smadium;
    private JButton medarge;
    private JButton list;
    private JButton standard;
    private JButton narrow;
    private JButton serif;
    private JButton text;
    private JButton info;
    private String font;
    private JScrollPane scrollPane;
    
    public Settings (Stickies s){
        sticky = s;
        font = s.getFont();
        s.getFrame().setVisible(false);
        this.setSize(s.getFrame().getSize());
        this.setLocation(s.getFrame().getLocation());
        
        this.setUndecorated(true);
       
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());
       
        pane = new JPanel();
        pane2 = new JPanel();
        scrollPane = new JScrollPane(pane2);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        scrollPane.getVerticalScrollBar().setBounds(999999999, 999999999, 6, 999999999);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(pane);
        this.setVisible(true);
        
        exit = new JButton("«");
        exit.setFont(new Font("Verdana", 0, 33));
        exit.setMaximumSize(new Dimension(40, 40));
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.addActionListener(this);
        exit.setFocusPainted(false);
        
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane2.setLayout(new BoxLayout(pane2, BoxLayout.PAGE_AXIS));
        
        menu = new JPanel();
        menu.add(exit);
        menu.add(new JLabel("  "));
        menu.setMaximumSize(new Dimension(999999999, 40));
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
       
        info = new JButton("i");
        info.setFont(new Font("Verdana", 0, 20));
        info.setMaximumSize(new Dimension(40, 40));
        info.setBorder(BorderFactory.createEmptyBorder());
        info.addActionListener(this);
        info.setFocusPainted(false);
        
        menu2 = new JPanel();
        menu2.add(info);
        menu2.add(new JLabel("  "));
        menu2.setMaximumSize(new Dimension(999999999, 40));
        menu2.setLayout(new BoxLayout(menu2, BoxLayout.LINE_AXIS));
        
        colorMenu = new JPanel();
        colorMenu.setLayout(new BoxLayout(colorMenu, BoxLayout.LINE_AXIS));
       
        blueB = new JButton("•");
        blueB.addActionListener(this);
        blueB.setFont(new Font("Verdana", 0, 120));
        blueB.setForeground(toColor2(blue));
        blueB.setBorder(BorderFactory.createEmptyBorder());
        blueB.setFocusPainted(false);
        blueB.setContentAreaFilled(false);
        blueB.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        greenB = new JButton("•");
        greenB.addActionListener(this);
        greenB.setFont(new Font("Verdana", 0, 120));
        greenB.setForeground(toColor2(green));
        greenB.setBorder(BorderFactory.createEmptyBorder());
        greenB.setFocusPainted(false);
        greenB.setContentAreaFilled(false);
        greenB.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        yellowB = new JButton("•");
        yellowB.addActionListener(this);
        yellowB.setFont(new Font("Verdana", 0, 120));
        yellowB.setForeground(toColor2(yellow));
        yellowB.setBorder(BorderFactory.createEmptyBorder());
        yellowB.setFocusPainted(false);
        yellowB.setContentAreaFilled(false);
        yellowB.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        purpleB = new JButton("•");
        purpleB.addActionListener(this);
        purpleB.setFont(new Font("Verdana", 0, 120));
        purpleB.setForeground(toColor2(purple));
        purpleB.setBorder(BorderFactory.createEmptyBorder());
        purpleB.setFocusPainted(false);
        purpleB.setContentAreaFilled(false);
        purpleB.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        redB = new JButton("•");
        redB.addActionListener(this);
        redB.setFont(new Font("Verdana", 0, 120));
        redB.setForeground(toColor2(red));
        redB.setBorder(BorderFactory.createEmptyBorder());
        redB.setFocusPainted(false);
        redB.setContentAreaFilled(false);
        redB.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        grayB = new JButton("•");
        grayB.addActionListener(this);
        grayB.setFont(new Font("Verdana", 0, 120));
        grayB.setForeground(toColor2(gray));
        grayB.setBorder(BorderFactory.createEmptyBorder());
        grayB.setFocusPainted(false);
        grayB.setContentAreaFilled(false);
        grayB.setAlignmentX(Component.CENTER_ALIGNMENT);

        colorMenu.add(new JLabel("     "));
        colorMenu.add(blueB);
        colorMenu.add(greenB);
        colorMenu.add(yellowB);
        colorMenu.add(redB);
        colorMenu.add(purpleB);
        colorMenu.add(grayB);
        colorMenu.setMaximumSize(new Dimension(9999999, 80));
        
        styleMenu = new JPanel();
        styleMenu.setLayout(new BoxLayout(styleMenu, BoxLayout.LINE_AXIS));
        styleMenu.setMaximumSize(new Dimension(9999999, 80));
        
        fontSizeMenu = new JPanel();
        fontSizeMenu.setLayout(new BoxLayout(fontSizeMenu, BoxLayout.LINE_AXIS));
        fontSizeMenu.setMaximumSize(new Dimension(9999999, 80));
        
        fontMenu = new JPanel();
        fontMenu.setLayout(new BoxLayout(fontMenu, BoxLayout.LINE_AXIS));
        fontMenu.setMaximumSize(new Dimension(9999999, 80));
        
        narrow = new JButton(" Narrow ");
        narrow.addActionListener(this);
        narrow.setFocusPainted(false);
        narrow.setFont(new Font(Stickies.FONT_NARROW,0,24));
        narrow.setBorder(BorderFactory.createEmptyBorder());
        
        standard = new JButton(" Standard ");
        standard.addActionListener(this);
        standard.setFocusPainted(false);
        standard.setFont(new Font(Stickies.FONT_STANDARD,0,24));
        standard.setBorder(BorderFactory.createEmptyBorder());
        
        serif = new JButton(" Serif ");
        serif.addActionListener(this);
        serif.setFocusPainted(false);
        serif.setFont(new Font(Stickies.FONT_SERIF,0,24));
        serif.setBorder(BorderFactory.createEmptyBorder());
        
        
        small = new JButton(" A ");
        small.addActionListener(this);
        small.setFocusPainted(false);
        small.setBorder(BorderFactory.createEmptyBorder());
        
        medium = new JButton(" A ");
        medium.addActionListener(this);
        medium.setFocusPainted(false);
        medium.setBorder(BorderFactory.createEmptyBorder());
        
        large = new JButton(" A ");
        large.addActionListener(this);
        large.setFocusPainted(false);
        large.setBorder(BorderFactory.createEmptyBorder());
        
        smadium = new JButton(" A ");
        smadium.addActionListener(this);
        smadium.setFocusPainted(false);
        smadium.setBorder(BorderFactory.createEmptyBorder());
        
        medarge = new JButton(" A ");
        medarge.addActionListener(this);
        medarge.setFocusPainted(false);
        medarge.setBorder(BorderFactory.createEmptyBorder());
        
        fontSizeMenu.add(new JLabel("      "));
        fontSizeMenu.add(small);
        fontSizeMenu.add(new JLabel("      "));
        fontSizeMenu.add(smadium);
        fontSizeMenu.add(new JLabel("      "));
        fontSizeMenu.add(medium);
        fontSizeMenu.add(new JLabel("      "));
        fontSizeMenu.add(medarge);
        fontSizeMenu.add(new JLabel("   "));
        fontSizeMenu.add(large);
        
        fontMenu.add(new JLabel("      "));
        fontMenu.add(standard);
        fontMenu.add(new JLabel("      "));
        fontMenu.add(narrow);
        fontMenu.add(new JLabel("      "));
        fontMenu.add(serif);
        
        list = new JButton(" List ");
        list.addActionListener(this);
        list.setFocusPainted(false);
        
        text = new JButton(" Text ");
        text.addActionListener(this);
        text.setFocusPainted(false);
        
        list.setBorder(BorderFactory.createEmptyBorder());
        text.setBorder(BorderFactory.createEmptyBorder());
        
        styleMenu.add(new JLabel("      "));
        styleMenu.add(text);
        styleMenu.add(new JLabel("      "));
        styleMenu.add(list);
        
        pane.add(menu);
        pane.add(scrollPane);
        pane2.add(colorMenu);
        pane2.add(styleMenu);
        pane2.add(fontSizeMenu);
        pane2.add(fontMenu);
        pane.add(menu2);
        
        setColors();
        FrameDragListener frameDragListener = new FrameDragListener(this, menu);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        
        
    }
    public void setColors(){

        colorMenu.setBackground(sticky.getColor());
        blueB.setBackground(sticky.getColor());
        exit.setBackground(sticky.getColor());
        exit.setForeground(sticky.getColor2());
        info.setBackground(sticky.getColor());
        info.setForeground(sticky.getColor2());
        menu.setBackground(sticky.getColor());
        menu2.setBackground(sticky.getColor());
        styleMenu.setBackground(sticky.getColor());
        pane.setBackground(sticky.getColor());
        pane2.setBackground(sticky.getColor());
        fontSizeMenu.setBackground(sticky.getColor());
        fontMenu.setBackground(sticky.getColor());
        scrollPane.setForeground(sticky.getColor());
        scrollPane.getVerticalScrollBar().setBackground(sticky.getColor());
        
        medarge.setFont(new Font(font, 0, Stickies.FONT_MEDARGE));
        smadium.setFont(new Font(font, 0, Stickies.FONT_SMADIUM));
        large.setFont(new Font(font, 0, Stickies.FONT_LARGE));
        medium.setFont(new Font(font, 0, Stickies.FONT_MEDIUM));
        small.setFont(new Font(font, 0, Stickies.FONT_SMALL));
        list.setFont(new Font(font,0,24));
        text.setFont(new Font(font,0,24));
        
        if(sticky.getStyleSelected() == Stickies.TEXT){
        	text.setBackground(sticky.getColor2());
            text.setForeground(sticky.getColor());
            list.setBackground(sticky.getColor());
	        list.setForeground(sticky.getColor2());

        }
        else if(sticky.getStyleSelected() == Stickies.LIST){
        	text.setBackground(sticky.getColor());
            text.setForeground(sticky.getColor2());
	        list.setBackground(sticky.getColor2());
	        list.setForeground(sticky.getColor());
        }
        
        
        if(sticky.getFontSize() == Stickies.FONT_SMALL){
        	small.setBackground(sticky.getColor2());
            medium.setBackground(sticky.getColor());
            large.setBackground(sticky.getColor());
            medarge.setBackground(sticky.getColor());
            smadium.setBackground(sticky.getColor());
            small.setForeground(sticky.getColor());
            medium.setForeground(sticky.getColor2());
            large.setForeground(sticky.getColor2());
            medarge.setForeground(sticky.getColor2());
            smadium.setForeground(sticky.getColor2());
            
        }
        else if(sticky.getFontSize() == Stickies.FONT_MEDIUM){
        	small.setBackground(sticky.getColor());
            medium.setBackground(sticky.getColor2());
            large.setBackground(sticky.getColor());
            medarge.setBackground(sticky.getColor());
            smadium.setBackground(sticky.getColor());
            small.setForeground(sticky.getColor2());
            medium.setForeground(sticky.getColor());
            large.setForeground(sticky.getColor2());
            medarge.setForeground(sticky.getColor2());
            smadium.setForeground(sticky.getColor2());
        }
        else if(sticky.getFontSize() == Stickies.FONT_LARGE){
        	small.setBackground(sticky.getColor());
            medium.setBackground(sticky.getColor());
            large.setBackground(sticky.getColor2());
            medarge.setBackground(sticky.getColor());
            smadium.setBackground(sticky.getColor());
            small.setForeground(sticky.getColor2());
            medium.setForeground(sticky.getColor2());
            large.setForeground(sticky.getColor());
            medarge.setForeground(sticky.getColor2());
            smadium.setForeground(sticky.getColor2());
        }
        else if(sticky.getFontSize() == Stickies.FONT_MEDARGE){
        	small.setBackground(sticky.getColor());
            medium.setBackground(sticky.getColor());
            large.setBackground(sticky.getColor());
            medarge.setBackground(sticky.getColor2());
            smadium.setBackground(sticky.getColor());
            small.setForeground(sticky.getColor2());
            medium.setForeground(sticky.getColor2());
            large.setForeground(sticky.getColor2());
            medarge.setForeground(sticky.getColor());
            smadium.setForeground(sticky.getColor2());
        }
        else if(sticky.getFontSize() == Stickies.FONT_SMADIUM){
        	small.setBackground(sticky.getColor());
            medium.setBackground(sticky.getColor());
            large.setBackground(sticky.getColor());
            medarge.setBackground(sticky.getColor());
            smadium.setBackground(sticky.getColor2());
            small.setForeground(sticky.getColor2());
            medium.setForeground(sticky.getColor2());
            large.setForeground(sticky.getColor2());
            medarge.setForeground(sticky.getColor2());
            smadium.setForeground(sticky.getColor());
        }
        else{
        	small.setBackground(sticky.getColor());
            medium.setBackground(sticky.getColor());
            large.setBackground(sticky.getColor());
            medarge.setBackground(sticky.getColor());
            smadium.setBackground(sticky.getColor());
            small.setForeground(sticky.getColor2());
            medium.setForeground(sticky.getColor2());
            large.setForeground(sticky.getColor2());
            medarge.setForeground(sticky.getColor2());
            smadium.setForeground(sticky.getColor2());
        }
        
        
        if(sticky.getFont().equals(Stickies.FONT_STANDARD)){
        	narrow.setBackground(sticky.getColor());
            narrow.setForeground(sticky.getColor2());
            serif.setBackground(sticky.getColor());
            serif.setForeground(sticky.getColor2());
	        standard.setBackground(sticky.getColor2());
	        standard.setForeground(sticky.getColor());
        }
        else if(sticky.getFont().equals(Stickies.FONT_SERIF)){
        	narrow.setBackground(sticky.getColor());
            narrow.setForeground(sticky.getColor2());
            serif.setBackground(sticky.getColor2());
            serif.setForeground(sticky.getColor());
	        standard.setBackground(sticky.getColor());
	        standard.setForeground(sticky.getColor2());
        }
        else if(sticky.getFont().equals(Stickies.FONT_NARROW)){
        	narrow.setBackground(sticky.getColor2());
            narrow.setForeground(sticky.getColor());
            serif.setBackground(sticky.getColor());
            serif.setForeground(sticky.getColor2());
	        standard.setBackground(sticky.getColor());
	        standard.setForeground(sticky.getColor2());
        }


    }
    
    public Color toColor2(Color color){
    	return new Color(color.getRed() - 90, color.getGreen() -90, color.getBlue() - 90);
    }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit){
			sticky.getFrame().setLocation(this.getLocation());
			sticky.getFrame().setVisible(true);
            this.dispose();
        }
		if(e.getSource() == blueB){
			sticky.setColor(blue);
			sticky.setColors();
			setColors();
		}
		if(e.getSource() == greenB){
			sticky.setColor(green);
			sticky.setColors();
			setColors();
		}
		if(e.getSource() == grayB){
			sticky.setColor(gray);
			sticky.setColors();
			setColors();
		}
		if(e.getSource() == redB){
			sticky.setColor(red);
			sticky.setColors();
			setColors();
		}
		if(e.getSource() == purpleB){
			sticky.setColor(purple);
			sticky.setColors();
			setColors();
		}
		if(e.getSource() == yellowB){
			sticky.setColor(yellow);
			sticky.setColors();
			setColors();
		}
		if(e.getSource() == list){
			sticky.listify();
			sticky.getFrame().getContentPane().remove(sticky.getScroll());
			sticky.getFrame().getContentPane().add(sticky.getListPane());
	        sticky.setStyle(Stickies.LIST);
			setColors();
		}
		if(e.getSource() == text){
			sticky.delistify();
			sticky.getFrame().getContentPane().add(sticky.getScroll());
			sticky.getFrame().getContentPane().remove(sticky.getListPane());
	        sticky.setStyle(Stickies.TEXT);
			setColors();
		}
		if(e.getSource()== small){
			sticky.setFontSize(Stickies.FONT_SMALL);
			setColors();
		}
		if(e.getSource()== smadium){
			sticky.setFontSize(Stickies.FONT_SMADIUM);
			setColors();
		}
		if(e.getSource()== medium){
			sticky.setFontSize(Stickies.FONT_MEDIUM);
			setColors();
		}
		if(e.getSource()== medarge){
			sticky.setFontSize(Stickies.FONT_MEDARGE);
			setColors();
		}
		if(e.getSource()== large){
			sticky.setFontSize(Stickies.FONT_LARGE);
			setColors();
		}
		if(e.getSource() == standard){
			sticky.setFont(Stickies.FONT_STANDARD);
			font = Stickies.FONT_STANDARD;
			setColors();
		}
		if(e.getSource() == narrow){
			sticky.setFont(Stickies.FONT_NARROW);
			font = Stickies.FONT_NARROW;
			setColors();
		}
		if(e.getSource() == serif){
			sticky.setFont(Stickies.FONT_SERIF);
			font = Stickies.FONT_SERIF;
			setColors();
		}
		if(e.getSource() == info){
			if(!infoState)
				infoFrame = new info();
			else
				infoFrame.requestFocus();
			infoState = true;
		}
	}

}