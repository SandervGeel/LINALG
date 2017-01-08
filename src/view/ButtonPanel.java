package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.IActionListener;

public class ButtonPanel extends JPanel{
	
	JButton scaleBtn;
	JButton resetBtn;
	JButton transformBtn;
	JTextField startx;
	//-JTextField 
	IActionListener controller;
	
	public ButtonPanel(IActionListener controller)
	{
		ActionListener resetPressed = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				reset();
			}
		};
		
		
		
		ActionListener scalingPressed = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField scaling = new JTextField(5);
				JCheckBox shrink_large = new JCheckBox();
				JPanel tempPanel = new JPanel();
				
				tempPanel.add(new JLabel("scaling factor:"));
				tempPanel.add(scaling);
				tempPanel.add(Box.createHorizontalStrut(15)); // a spacer
				tempPanel.add(new JLabel("check box for shrink"));
				tempPanel.add(shrink_large);
				
			      int result = JOptionPane.showConfirmDialog(null, tempPanel, 
			               "Please Enter scaling", JOptionPane.OK_CANCEL_OPTION);
			      if(result == 0)
			      {
			    	  double scale = 1;
			    	  boolean shrink = false;
			    	  try{
			    		  scale = Double.parseDouble(scaling.getText());
			    		  shrink = shrink_large.isSelected();
			    	  } catch (Exception except){
			    		  // do nothing
			    	  }
			    	  if(shrink)
			    	  {
			    		  scale = 1 / scale;
			    	  }
			    	  scale(scale);
			      }
			}
		};
		
		ActionListener rotate = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JTextField xb_rotate = new JTextField("begin",5);
				JTextField yb_rotate = new JTextField("begin",5);
				JTextField zb_rotate = new JTextField("begin",5);
				JTextField xe_rotate = new JTextField("end",5);
				JTextField ye_rotate = new JTextField("end",5);
				JTextField ze_rotate = new JTextField("end",5);
				JTextField angle = new JTextField(5);
				
				JPanel tempPanel = new JPanel();
				tempPanel.add(new JLabel("rotate angle:"));
				tempPanel.add(angle);
				tempPanel.add(new JLabel("x:"));
				tempPanel.add(xb_rotate);
				tempPanel.add(Box.createHorizontalStrut(15)); // a spacer
				tempPanel.add(new JLabel("y:"));
				tempPanel.add(yb_rotate);
				tempPanel.add(Box.createHorizontalStrut(15)); // a spacer
				tempPanel.add(new JLabel("z:"));
				tempPanel.add(zb_rotate);
				
				tempPanel.add(new JLabel("x:"));
				tempPanel.add(xe_rotate);
				tempPanel.add(Box.createHorizontalStrut(15)); // a spacer
				tempPanel.add(new JLabel("y:"));
				tempPanel.add(ye_rotate);
				tempPanel.add(Box.createHorizontalStrut(15)); // a spacer
				tempPanel.add(new JLabel("z:"));
				tempPanel.add(ze_rotate);
				
			      int result = JOptionPane.showConfirmDialog(null, tempPanel, 
			               "Please Enter all Values", JOptionPane.OK_CANCEL_OPTION);
			      if(result == 0)
			      {
			    	  boolean inputCorrect = true;
			    	  int xb = 0; int yb = 0; int zb = 0; int ang = 0;
			    	  int xe = 0; int ye = 0; int ze = 0;
			    	  try{
			    		 xb = Integer.parseInt(xb_rotate.getText());
			    		 yb = Integer.parseInt(yb_rotate.getText());
			    		 zb = Integer.parseInt(zb_rotate.getText());
			    		 
			    		 xe = Integer.parseInt(xe_rotate.getText());
			    		 ye = Integer.parseInt(ye_rotate.getText());
			    		 ze = Integer.parseInt(ze_rotate.getText());
			    		 
			    		 ang = Integer.parseInt(angle.getText());
			    	  } catch (Exception except){
			    		  inputCorrect = false;
			    	  }
			    	  if(inputCorrect)
			    	  {
							controller.rotate(xb, yb, zb, xe, ye, ze, ang);
			    	  }
			      }
			}
		};
		
		ActionListener transformPressed = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JTextField x_trans = new JTextField(5);
				JTextField y_trans = new JTextField(5);
				JTextField z_trans = new JTextField(5);
				
				JPanel tempPanel = new JPanel();		
				tempPanel.add(new JLabel("x transform:"));
				tempPanel.add(x_trans);
				tempPanel.add(Box.createHorizontalStrut(15)); // a spacer
				tempPanel.add(new JLabel("y transform:"));
				tempPanel.add(y_trans);
				tempPanel.add(Box.createHorizontalStrut(15)); // a spacer
				tempPanel.add(new JLabel("z transform:"));
				tempPanel.add(z_trans);
				
			      int result = JOptionPane.showConfirmDialog(null, tempPanel, 
			               "Please Enter all Values", JOptionPane.OK_CANCEL_OPTION);
			      if(result == 0)
			      {
			    	  boolean inputCorrect = true;
			    	  int x = 0; int y = 0; int z = 0;
			    	  try{
			    		 x = Integer.parseInt(x_trans.getText());
			    		 y = Integer.parseInt(y_trans.getText());
			    		 z = Integer.parseInt(z_trans.getText());
			    	  } catch (Exception except){
			    		  inputCorrect = false;
			    	  }
			    	  if(inputCorrect)
			    	  {
							controller.transform(x, y, z,true);
			    	  }
			      }
			}
		};
		
		transformBtn = new JButton("transform");
		transformBtn.addActionListener(transformPressed);
		
		scaleBtn = new JButton("Scale");
		scaleBtn.addActionListener(scalingPressed);
		
		resetBtn = new JButton("reset");
		resetBtn.addActionListener(resetPressed);
		
		this.controller = controller;
		this.add(resetBtn);
		this.add(scaleBtn);
		this.add(transformBtn);
		this.setBackground(Color.white);
	}
	
	public void scale(double scaling)
	{
		controller.scale(scaling);
	}
	
	public void reset()
	{
		controller.reset();
	}

}