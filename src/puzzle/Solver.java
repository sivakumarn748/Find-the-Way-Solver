package puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Solver {
	public final UI_Node START;
	public final UI_Node END;
	
	private ArrayList<UI_Node> visited = new ArrayList<UI_Node>();
	private boolean foundend = false;
	
	public boolean goThrough(UI_Node n) {
		// System.out.println(n.name);
		visited.add(n);
		
		if(n == END) {
			foundend = true;
			System.out.println("Found End");
			return foundend;
		}
		
		for(UI_Node neigh : n.neighbours) {
			if(!(foundend || visited.contains(neigh) || neigh.blocked)) {
				n.child = neigh;
				neigh.parent = n;
				// System.out.println(" " + n.name + " - " + n.child.name + " "); 
				goThrough(neigh);
			}
		}
		
		return foundend;
	}
	
	public void activate() {		
		START.open();
		START.button.setBackground(Color.GREEN);
		START.Disable();
		
		END.open();
		END.button.setBackground(Color.BLACK);
		END.Disable();
	}
	
	public Solver(UI_Node s, UI_Node e) {
		START = s;
		END = e;
		START.master.blockAll();
		activate();
	}
	
	public boolean search() {
		visited.clear();
		foundend = false;
		return goThrough(START);
	}
	
	public void tracePath() {
		activate();
		for(UI_Node now = START.child; now != END && now != null; now = now.child) {
			now.button.setBackground(Color.yellow);
		}
	}
}
