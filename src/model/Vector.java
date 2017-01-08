package model;

import java.awt.Color;

public class Vector extends Matrix{
	
	public Vector(double x, double y, double z, double w)
	{
		this.matrix = new double[][]{{x, y, z, w }};	
	}
	
	public Vector(double x, double y, double z)
	{
		this.matrix = new double[][]{{x, y, z, 1 }};	
	}
	
	public double in_product(Vector other_vector){
		return ((this.matrix[0][0] * other_vector.get_x_vec()) + (this.matrix[0][1] * other_vector.get_y_vec()) + (this.matrix[0][2] * other_vector.get_z_vec()));
	}
	
	public Vector uit_product(Vector other_vector)
	{
		Vector temp;
		temp = new Vector(	(this.matrix[0][1] * other_vector.get_z_vec()) - (this.matrix[0][2] * other_vector.get_y_vec()),
							(this.matrix[0][2] * other_vector.get_x_vec()) - (this.matrix[0][0] * other_vector.get_z_vec()),
							(this.matrix[0][0] * other_vector.get_y_vec()) - (this.matrix[0][1] * other_vector.get_x_vec())
						);
		return temp;
	}

	public double get_x_vec()
	{
		return this.matrix[0][0];
	}
	public double get_y_vec()
	{
		return this.matrix[0][1];
	}
	public double get_z_vec()
	{
		return this.matrix[0][2];
	}
	
	public void normalize()
	{
		double norm = Math.sqrt((Math.pow(this.matrix[0][0], 2) + Math.pow(this.matrix[0][1], 2) + Math.pow(this.matrix[0][2], 2)));
		for(int i = 0; i < 3; i++){
			this.matrix[0][i] = (this.matrix[0][i] / norm);
		}
	}

	public double get_w_vec() 	{
		return this.matrix[0][3];
	}
}
