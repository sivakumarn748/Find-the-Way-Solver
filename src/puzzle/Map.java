package puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Map {
	public JFrame frame = new JFrame("Puzzle");
	public JPanel panel = new JPanel();
	public JPanel control = new JPanel();
	
	public ArrayList<UI_Node> list = new ArrayList<UI_Node>();
	public ArrayList<ArrayList<UI_Node>> table = new ArrayList<ArrayList<UI_Node>>();

	public String searchStatus = "None";
	public JButton play = new JButton("Search");
	public JButton help = new JButton("Help!");
	public JLabel status = new JLabel("Status : " + searchStatus);
	
	public void createNodes(int c, int r) {
		
		for(int i=0; i<c; i++) {
			
			ArrayList<UI_Node> currentList = new ArrayList<UI_Node>();
			
			for(int j=0; j<r; j++) {
				UI_Node currentNode = new UI_Node((i)+","+(j));
				currentList.add(currentNode);  // elements added to list
			}
			
			table.add(currentList); // list is inserted as a row in table.
		}
	}
	
	public void setNeighbours() {
				
		// Connection nodes along the rows
		//ArrayList<UI_Node> currentList = new ArrayList<UI_Node>();
		for(ArrayList<UI_Node> currentList : table) {
			
			
			UI_Node prevNode = null;
			UI_Node currentNode = new UI_Node("");
			
			for(int j=0; j<currentList.size(); j++) {
				currentNode = currentList.get(j);
				
				if (prevNode != null) {
					prevNode.neighbours.add(currentNode);
					currentNode.neighbours.add(prevNode);
				}
				prevNode = currentNode;
			}
		}
		
		
		// Connecting nodes along each column
		for(int i=0; i<table.get(0).size(); i++) {
			UI_Node prevNode = null;
			UI_Node currNode = null;
			
			for(int j=0; j<table.size(); j++) {
				currNode = table.get(j).get(i);
				
				if (prevNode != null) {
					currNode.neighbours.add(prevNode);
					prevNode.neighbours.add(currNode);
				}
				prevNode = currNode;
			}
		}
		
		
		// No. of rows and columns
		System.out.println(table.size() + " x " + table.get(0).size());
	}
	
	public void printMap() {
		
		int r = 0;
		for(ArrayList<UI_Node> row : table) {
			int c = 0;
			for(UI_Node ele : row) {
				ele.makeButton(r, c);
				ele.master = this;
				panel.add(ele.button);
				c = c + 1;
			}
			r = r + 1;
		}
		
	}
	
	public void blockAll() {
		for(ArrayList<UI_Node> row : table) {
			for(UI_Node ele : row) {
				ele.block();
			}
		}
	}
	
	public void virtualBlockAll() {
		for(ArrayList<UI_Node> row : table) {
			for(UI_Node ele : row) {
				ele.block();
			}
		}
	}
	
	public void openAll() {
		for(ArrayList<UI_Node> row : table) {
			for(UI_Node ele : row) {
				ele.open();
			}
		}
	}
	
	public UI_Node at(int i, int j) {
		return table.get(i).get(j);
		
	}

	Map(int r, int c){
		// Position for elements in UI
		frame.setBounds(0, 0, 1000, 1000);
		control.setBounds(0, 0, 600, 50);
		panel.setBounds(0, 50, 1000, 1000);
		status.setBounds(0, 0, 500, 50);
		play.setBounds(500, 0, 500, 50);
		help.setBounds(1000, 0, 500, 50);
		
		// Creating the UI
		createNodes(r, c);
		setNeighbours();
		printMap();	
		
		play.setBackground(Color.green);
		status.setOpaque(true);
		status.setBackground(Color.yellow);
		help.setBackground(Color.cyan);
		
		// Adding status and play elements in control
		control.add(status);
		control.add(play);
		control.add(help);
		
		// Adding panel and control to frame
		frame.add(panel);
		frame.add(control);
		
		// setting panel the BorderLayout
		panel.setLayout(null);
		
		// setting frame properties
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog();
				d.setBounds(100, 100, 500, 80);
				d.setTitle("HELP");
				String msg1 = "Ctrl + (mouse drag) -> to make way //// Single click -> open or close a step";
				d.add(new JLabel(msg1));
				d.setVisible(true);
			}
		});
	}
}
