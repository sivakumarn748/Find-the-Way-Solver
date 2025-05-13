package puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class MAIN {
	public static void main(String[] args) throws IOException {
		System.out.println("Main Function Starts!");
		
		Map a = new Map(50, 50);

		Solver s = new Solver(a.at(0, 0), a.at(45, 34));
		
		a.play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
								
				boolean found = s.search();
				
				if(found) {
					a.virtualBlockAll();
					s.activate();
					a.searchStatus = "Homecoming";
					s.tracePath();
				}
				else {
					//a.virtualBlockAll();
					s.activate();
					a.searchStatus = "No Way Home";
				}
				
				a.status.setText("Status : " + a.searchStatus);		
				
			}
		});
		
		System.out.println("Main Function Ends!");
		
	}
}
