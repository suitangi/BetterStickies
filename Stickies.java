import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class Stickies implements ActionListener{

    
    private Color color;
    private Color color2;
    private String text;
    private JFrame frame;
    private JTextPane textPane;
    private JScrollPane scrollPane;
    private JPanel menu;
    private Container pane;
    private JButton plus;
    private JButton options;
    private JButton exit;
    private JTextField title;
    private Style style;
    private boolean set;
    
    
    public Stickies(){
        setColor(new Color(255, 255, 179));
        textPane = new JTextPane();
        textPane.setFont(new Font("Verdana", 0, 15));
        title = new JTextField("Sticky Note");
        title.setBorder(BorderFactory.createEmptyBorder());
        title.addActionListener(this);
        
        
        scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        scrollPane.getVerticalScrollBar().setBounds(999999999, 999999999, 6, 999999999);
        
        set = false;
        frame = new JFrame();
        ComponentResizer cr = new ComponentResizer(frame);
        
        pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        menu = new JPanel();
        menu.setMaximumSize(new Dimension(999999999, 40));
        menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
        
        plus = new JButton(" + ");
        plus.setFont(new Font("Verdana", 0, 25));
        plus.setMaximumSize(new Dimension(40, 40));
        plus.setBorder(BorderFactory.createEmptyBorder());
        
        options = new JButton("  • • •  ");
        options.setFont(new Font("Verdana", 0, 8));
        options.setMaximumSize(new Dimension(40, 40));
        options.setBorder(BorderFactory.createEmptyBorder());
        
        exit = new JButton(" × ");
        exit.setFont(new Font("Verdana", 0, 27));
        exit.setMaximumSize(new Dimension(40, 40));
        exit.setBorder(BorderFactory.createEmptyBorder());
        
        plus.addActionListener(this);
        options.addActionListener(this);
        exit.addActionListener(this);
        
        JLabel label = new JLabel("                    ");
        new JLabel(" ");
        label.setFont(new Font("Verdana", 0, 30));
        plus.setAlignmentX(Component.RIGHT_ALIGNMENT);
        options.setAlignmentX(Component.RIGHT_ALIGNMENT);
        

        title.setFont(new Font("Verdana", 0, 14));
        
        menu.setComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT );
        menu.add(new JLabel("  "));
        menu.add(exit);
        menu.add(new JLabel(" "));
        menu.add(options);
        menu.add(plus);
        menu.add(label);
        menu.add(title);
        pane.add(menu);
        pane.add(scrollPane);
        frame.setTitle(title.getText());
        
        
        FrameDragListener frameDragListener = new FrameDragListener(frame, menu);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);
        frame.setUndecorated(true); 
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        frame.setLocation(MouseInfo.getPointerInfo().getLocation());
        
//        frame.addMouseListener( new MouseAdapter() {
//             public void mouseEntered(MouseEvent e) {
//                 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//             }
//             public void mouseExited(MouseEvent e){
//                 scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
//             }
        
     
//        } );
        
        style = textPane.addStyle("Style", null);
        
        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());
        
        frame.setSize(500,450);
        setColors();
        frame.setVisible(true);
        
    
        
    }
    public JScrollPane getScroll(){
        return scrollPane;
    }
    public JFrame getFrame(){
        return frame;
    }
    public Color getColor(){
    	return color;
    }
    public Color getColor2(){
    	return color2;
    }
    public void setColor(Color c){
        color = c;
        color2 = new Color(color.getRed() - 90, color.getGreen() -90, color.getBlue() - 90);
    }
    public void setColors(){
        pane.setBackground(color);
        frame.setBackground(color);
        scrollPane.setBackground(color);
        scrollPane.setForeground(color);
        scrollPane.getVerticalScrollBar().setBackground(color);
        menu.setBackground(color2);
        plus.setBackground(color2);
        options.setBackground(color2);
        exit.setBackground(color2);
        plus.setForeground(color);
        title.setForeground(color);
        title.setBackground(color2);
        textPane.setBackground(color);
        options.setForeground(color);
        exit.setForeground(color);
        frame.getRootPane().setBackground(color2);
        
        StyleConstants.setForeground(style, color2);
        try{
            textPane.getDocument().insertString(textPane.getText().length(), "New Sticky!", style);
        }
        catch (BadLocationException e){}
        
    }
    public static void main(String[] args) {
        Stickies stickies = new Stickies();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            frame.dispose();
        }
        
        if(e.getSource() == plus){
            new Stickies();
        }
        if(e.getSource() == options){
            new Settings(this);
        }
        if(e.getSource() == title){
            frame.setTitle(title.getText());
            textPane.requestFocus();
        }
    }
    
    
    

}