package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import controller.IActionListener;
import controller.IInfoHolder;

public class MyGui extends JFrame implements KeyListener{
	
	private MyContentPane content;
	private IActionListener controller;
	
	public MyGui(IActionListener controller, IInfoHolder vectors)
	{
		content = new MyContentPane(controller, vectors);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setContentPane(content);
		this.setVisible(true);
		this.pack();
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		this.addKeyListener(this);
		this.getContentPane().addKeyListener(this);
		
		this.controller = controller;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			controller.transform(1, 0, 0, true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			controller.transform(-1, 0, 0, true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			controller.transform(0, -1, 0, true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			controller.transform(0, 1, 0, true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}