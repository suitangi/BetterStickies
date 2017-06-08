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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.undo.UndoManager;

public class Stickies implements ActionListener, KeyListener{

    public static final int TEXT = 0;
    public static final int LIST = 1;
    public static final int FONT_SMALL = 15;
    public static final int FONT_SMADIUM = 30;
    public static final int FONT_MEDIUM = 50;
    public static final int FONT_MEDARGE = 70;
    public static final int FONT_LARGE = 110;
    public static final String FONT_STANDARD = "Verdana";
    public static final String FONT_NARROW = "Tahoma";
    public static final String FONT_SERIF = "Times New Roman";
    
    private Color color;
    private Color color2;
    private String text;
    private JFrame frame;
    private JTextPane textPane;
    private JScrollPane scrollPane;
    private JScrollPane listPane;
    private JPanel menu;
    private Container pane;
    private JButton plus;
    private JButton options;
    private JButton exit;
    private JTextField title;
    private Style style;
    private int fontSize = 15;
    private String font = Stickies.FONT_STANDARD;
    private JPanel listPanel;
    private ArrayList<ListItem> listList;
    private int styleSelected = 0;
    private boolean ctrl = false;
    private boolean shift = false;
    private UndoManager manager;
    
    public Stickies(){
        setColor(new Color(255, 255, 179));
        textPane = new JTextPane();
        textPane.setFont(new Font(Stickies.FONT_STANDARD, 0, 15));
        title = new JTextField("Sticky Note");
        title.setBorder(BorderFactory.createEmptyBorder());
        title.addActionListener(this);
        listList = new ArrayList<ListItem> (0);
        textPane.addKeyListener(this);
        title.addKeyListener(this);
        
        manager = new UndoManager();
        textPane.getDocument().addUndoableEditListener(manager);
        
        scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        scrollPane.getVerticalScrollBar().setBounds(999999999, 999999999, 6, 999999999);
        
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
        
        options = new JButton("   • • •   ");
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
        
        plus.setFocusPainted(false);
        options.setFocusPainted(false);
        exit.setFocusPainted(false);
        
        JLabel label = new JLabel("                    ");
        new JLabel(" ");
        label.setFont(new Font("Verdana", 0, 30));
        plus.setAlignmentX(Component.RIGHT_ALIGNMENT);
        options.setAlignmentX(Component.RIGHT_ALIGNMENT);
        

        title.setFont(new Font("Verdana", 0, 14));
        
        menu.setComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT );
//        menu.add(new JLabel("  "));
        menu.add(exit);
//        menu.add(new JLabel(" "));
        menu.add(options);
        menu.add(plus);
        menu.add(label);
        menu.add(title);
        pane.add(menu);
        pane.add(scrollPane);
        
        listPanel = new JPanel();
        
        listPane = new JScrollPane(listPanel);
        listPane.setBorder(BorderFactory.createEmptyBorder());
        listPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
        listPane.getVerticalScrollBar().setBounds(999999999, 999999999, 6, 999999999);
        listPane.getVerticalScrollBar().setUnitIncrement(16);
        
        
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));

        frame.setTitle(title.getText());
        
        
        FrameDragListener frameDragListener = new FrameDragListener(frame, menu);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);
        frame.setUndecorated(true); 
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        frame.setLocation(MouseInfo.getPointerInfo().getLocation());
        
        
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
    public JScrollPane getListPane(){
    	return listPane;
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
    public int getStyleSelected(){
    	return styleSelected;
    }
    public JTextPane getTextPane(){
    	return textPane;
    }
    public int getFontSize(){
    	return fontSize;
    }
    public ArrayList<ListItem> getList(){
    	return listList;
    }
    public String getFont(){
    	return font;
    }
    public void setColor(Color c){
        color = c;
        color2 = new Color(color.getRed() - 90, color.getGreen() -90, color.getBlue() - 90);
    }
    public void setStyle(int s){

    	styleSelected = s;
    }
    public void setFontSize(int s){
    	fontSize = s;
    	textPane.setFont(new Font(font, 0, s));
    }
    public void setFont(String s){
    	font = s;
    	textPane.setFont(new Font(font,0, fontSize));
    	for(ListItem item: listList){
    		item.setFont(s);;
    	}
    	
    }
    public void listify(){
    	
    	listList = new ArrayList<ListItem>();
    	String t = textPane.getText();
    	int i = t.indexOf('\n'); 
    	while(i != -1){
    		ListItem item = new ListItem(t.substring(0,i-1), font, this);
    		listList.add(item);
    		t = t.substring(i+1);
    		i = t.indexOf('\n');
    	}
    	ListItem item = new ListItem(t, font, this);
		listList.add(item);
    	updateList();
    }
    public String delistify(){
    	textPane.setText("");
    	String t = "";
    	for(ListItem item: listList){
    		t+= item.getText() + "\n";
    	}
    	
    	StyleConstants.setForeground(style, color2);
        try{
        	textPane.getDocument().insertString(textPane.getText().length(), t, style);
        	manager.discardAllEdits();
        }
        catch (BadLocationException e){}
        return t;
    }
    public void export(String s){
    	JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt");
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showSaveDialog(new JFrame("Save File"));
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String fname = fc.getSelectedFile().getAbsolutePath();
			if(fname.indexOf(".txt")== -1)
				fname += ".txt";
			try {
				PrintWriter out = new PrintWriter(fname);
				out.println(s.replaceAll("\n", System.lineSeparator()));
				out.close();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(new JFrame("Export Error"),
					    "Failed to export.",
					    "Export Errorrror",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
    }
    public void updateList(){
    	listPanel.removeAll();
    	listPanel.setVisible(false);
    	for(ListItem item: listList){
    		if(item!= null)
    			listPanel.add(item);
    	}
    	setColors();
    	listPanel.setVisible(true);
    	listPanel.repaint();
    	listPane.scrollRectToVisible(new Rectangle(0,0, 1, 1));
    }
    public void setColors(){
        pane.setBackground(color);
        frame.setBackground(color);
        scrollPane.setBackground(color);
        listPane.setBackground(color);
        scrollPane.setForeground(color);
        scrollPane.getVerticalScrollBar().setBackground(color);
        listPane.getVerticalScrollBar().setBackground(color);
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
        listPanel.setBackground(color);
        for(ListItem item: listList){
        	item.setColor(color);
        }
        
        StyleConstants.setForeground(style, color2);
        try{
        	if(textPane.getText().length() == 0)
        		textPane.getDocument().insertString(textPane.getText().length(), "New Sticky!", style);
        	else{
        		text = textPane.getText();
        		textPane.setText("");
        		textPane.getDocument().insertString(textPane.getText().length(), text, style);
        	}
        	
        		
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
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL){
			ctrl = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			shift = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			if(ctrl){
				export(textPane.getText());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			if(ctrl){
				frame.dispose();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){
			if(ctrl){
				System.exit(0);;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_M){
			if(ctrl){
				frame.setState(JFrame.ICONIFIED);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_Z){
			if(ctrl && manager.canUndo() && !shift){
				manager.undo();
			}
			if(ctrl && manager.canRedo() && shift){
				manager.redo();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_T){
			if(ctrl){
				new Stickies();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_EQUALS){
			if(ctrl && fontSize<=120){
				
				setFontSize(fontSize+5);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_MINUS){
			if(ctrl && fontSize>=15){
				setFontSize(fontSize-5);
			}
		}
		
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL){
			ctrl = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			shift = false;
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    
    

}