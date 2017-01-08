package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.IInfoHolder;
import model.Coordinate;
import model.Matrix;
import model.Vector;

public class ViewPanel extends JPanel{

	private IInfoHolder info_holder;	
	
	public ViewPanel(IInfoHolder vectors)
	{
		this.add(new JLabel("View"));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.info_holder = vectors;
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(vectors.get_screensize(), vectors.get_screensize()));
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Matrix plane = info_holder.calculate_3D_matrix(info_holder.get_plane());
		Matrix object = info_holder.calculate_3D_matrix(info_holder.get_matrix());
		super.paintComponent(g);
		g.drawLine(info_holder.get_screensize() / 2, info_holder.get_screensize() / 2, info_holder.get_screensize() * 2, info_holder.get_screensize() / 2);	//x axis
		g.drawLine(info_holder.get_screensize() / 2, 0, info_holder.get_screensize() / 2, info_holder.get_screensize() /2);	//y axis
		g.drawLine(info_holder.get_screensize() / 2, info_holder.get_screensize() / 2, 0, info_holder.get_screensize()); //z axis
			
		
		for(int i = 0; i < plane.get_x().length; i = i +2){
				g.drawLine((int)(plane.get_x()[i]), (int)(plane.get_y()[i]), (int)(plane.get_x()[i+1]), (int)(plane.get_y()[i+1]));
				//g.fillOval((int)(temp.get_x()[i]), (int)(temp.get_y()[i]), 5, 5);	
			}
		for(int i = 0; i < object.get_x().length; i = i +2){
			g.drawLine((int)(object.get_x()[i]), (int)(object.get_y()[i]), (int)(object.get_x()[i+1]), (int)(object.get_y()[i+1]));
		}
	}
}
