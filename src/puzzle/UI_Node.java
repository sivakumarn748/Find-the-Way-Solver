package puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UI_Node extends JButton implements MouseListener {
	
	public String name;
	public ArrayList<UI_Node> neighbours = new ArrayList<UI_Node>();
	public UI_Node parent = null;
	public UI_Node child = null;
	public Map master = null;
	
	public JButton button;
	public boolean blocked = false;
	public Color defColor = Color.white;
	public int L=12, W=12;

	public void connect(UI_Node... ele) {
		for (UI_Node i : ele) {
			neighbours.add(i);
		}
	}
	
	public void remove(UI_Node ele) {
		neighbours.remove(ele);
	}
	
	public void makeButton(int x, int y) {

		// parameters
		final int len = L;
		final int wid = W;
		
		int i = x*len;
		int j = y*wid + 30;

		// button configurations
		button.setBounds(i, j, len, wid);
		block();

		// button tooltip
		String tooltip = "(" + (x+1) + "," + (y+1) + ")";
		button.setToolTipText(tooltip);
		
		button.addMouseListener(this);
		
	}

	
	// constructor
	public UI_Node(String n) {
		name = n;
		button = new JButton();
	}
	
	public UI_Node(String n, int l, int w) {
		name = n;
		L = l;
		W = w;
		button = new JButton();
	}
	
	// block a path
	public void block() {
		blocked = true;
		button.setBackground(Color.RED);
	}
	
	public void virtualBlock() {
		button.setBackground(Color.red);
	}
	
	
	// unblock a path
	public void open() {
		blocked = false;
		button.setBackground(Color.WHITE);
	}
	
	
	/***************************************************************************/
	// Mouse Events
	
	public void mouseClicked(MouseEvent e) {
		if (blocked) {
			open();
		}
		else {
			block();
		}
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {
		if(e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
			block();
		}
		if(e.getModifiersEx() == MouseEvent.ALT_DOWN_MASK) {
			open();
		}
	}

	public void mouseExited(MouseEvent e) {}
	
	public void Disable() {
		button.removeMouseListener(this);
	}
	
	public void Enable() {
		button.addMouseListener(this);
	}
}

