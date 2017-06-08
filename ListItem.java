import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.undo.UndoManager;

public class ListItem extends JPanel implements ActionListener, KeyListener{

	
	private String text;
	private JTextField textField;
	private JButton checkBox;
	private boolean checked = false;
	private JButton clear;
	private Font font;
	private Color colour;
	private ArrayList<ListItem> list;
	private Stickies sticky;
	private boolean ctrl = false;
	private boolean shift = false;
	private UndoManager manager;
	
	public ListItem(String t, String f, Stickies s){
		font = new Font(f, 0, 15);
		sticky = s;
		list = s.getList();
		UIManager.put("CheckBox.focus",Color.RED);
		checkBox = new JButton(" » ");
		textField = new JTextField(t);
		clear = new JButton(" × ");
		clear.setFont(new Font("Verdana", 0, 17));
		textField.setFont(font);
		clear.setMaximumSize(new Dimension(20,40));
		clear.setBorder(BorderFactory.createEmptyBorder());
//		checkBox.setBorder(BorderFactory.createEmptyBorder());
		checkBox.setFont(new Font("Verdana", 0, 20));
		checkBox.addActionListener(this);
//		checkBox.setMaximumSize(new Dimension(15,15));
		checkBox.setBorder(BorderFactory.createEmptyBorder());
		clear.setFocusPainted(false);
		clear.addActionListener(this);
		
		manager = new UndoManager();
        textField.getDocument().addUndoableEditListener(manager);
		
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(checkBox);
		this.add(new JLabel(" "));
		this.add(textField);
		this.add(clear);
		this.setMaximumSize(new Dimension(9999999, 35));
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
		textField.addKeyListener(this);
		
	}
	
	public String getText(){
		return textField.getText();
	}
	public boolean getChecked(){
		return checked;
	}
	public JTextField getTextField(){
		return textField;
	}
	public void setFont(String s){
		font = new Font(s, 0, 15);
		textField.setFont(font);
	}
	public void setColor(Color color){
		colour = color;
		Color color2 = new Color(color.getRed() - 90, color.getGreen() -90, color.getBlue() - 90);
		textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color2));
		this.setBackground(color);
		clear.setBackground(color);
		clear.setForeground(color2);
		textField.setBackground(color);
		textField.setForeground(color2);
		
		if(checked){
			checkBox.setBackground(color2);
			checkBox.setForeground(color);
		}
		else{
			checkBox.setBackground(color);
			checkBox.setForeground(color2);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == checkBox){
			if(!checked){
				Map  attributes = font.getAttributes();
				attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
				Font newFont = new Font(attributes); 
				textField.setFont(newFont);
			}
			else
			{
				textField.setFont(font);
			}
			checked = !checked;
			setColor(colour);
		}
		if(e.getSource() == clear){
			if(list.size() != 1){
				int index = list.indexOf(this);
				if(index != list.size() - 1)
					list.get(index + 1).getTextField().requestFocus();
				list.remove(this);
				sticky.updateList();
			}
			
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int index = list.indexOf(this);
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        	if(index != list.size()-1)
        		list.get(index + 1).getTextField().requestFocus();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
        	if(index != 0)
        		list.get(index - 1).getTextField().requestFocus();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        	if(textField.getCaretPosition() == 0 && index != 0){
        		list.get(index - 1).getTextField().requestFocus();
        		list.get(index - 1).getTextField().setCaretPosition(
        				list.get(index - 1).getTextField().getText().length());
        	}
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	if(textField.getCaretPosition() == textField.getText().length() && index != list.size()-1){
        		list.get(index + 1).getTextField().requestFocus();
        		list.get(index + 1).getTextField().setCaretPosition(0);
        	}
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
        	int cPos = textField.getCaretPosition();
        	if(cPos != textField.getText().length()){
        		String t = textField.getText();
        		list.add(index + 1, new ListItem(t.substring(cPos), font.getFontName(), sticky));
        		sticky.updateList();
        		textField.setText(t.substring(0,cPos));
        	}
        	else{
        		if(index == list.size()-1){
        			list.add(new ListItem("", font.getFontName(), sticky));
        			sticky.updateList();
        		}
        	}
        	list.get(index + 1).getTextField().requestFocus();
        	list.get(index + 1).getTextField().setCaretPosition(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
        	if(textField.getCaretPosition() == 0 && textField.getSelectedText()==null){
        		if(index != 0){
        			list.get(index - 1).getTextField().requestFocus();
	        		String t = list.get(index - 1).getTextField().getText();
	        		int i = t.length();
	        		t += textField.getText();
	        		list.get(index - 1).getTextField().setText(t);
	        		list.get(index - 1).getTextField().setCaretPosition(i);
					list.remove(this);
					sticky.updateList();
        		}
        	}
        }
        if(e.getKeyCode() == KeyEvent.VK_CONTROL){
			ctrl = true;
		}
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			shift = true;
		}
        if(e.getKeyCode() == KeyEvent.VK_S){
			if(ctrl){
				sticky.export(sticky.delistify());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			if(ctrl){
				sticky.getFrame().dispose();;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){
			if(ctrl){
				System.exit(0);;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_M){
			if(ctrl){
				sticky.getFrame().setState(JFrame.ICONIFIED);
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
	}

}
