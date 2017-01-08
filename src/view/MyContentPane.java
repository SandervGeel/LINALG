package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.IActionListener;
import controller.IInfoHolder;

public class MyContentPane extends JPanel{

	private ButtonPanel btnp;
	private ViewPanel viewp;
	
	public MyContentPane(IActionListener controller, IInfoHolder vectors)
	{
		this.setLayout(new BorderLayout());
		
		this.setBackground(Color.WHITE);
		
		btnp = new ButtonPanel(controller);
		viewp = new ViewPanel(vectors);

		
		this.add(btnp, BorderLayout.NORTH);	
		this.add(viewp, BorderLayout.SOUTH);
		
	}
}
