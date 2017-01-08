package model;

public class MatrixScale extends Matrix{
	
	public MatrixScale(double scaling_x, double scaling_y, double scaling_z)
	{
		this.matrix = new double[][] 	{{scaling_x, 0, 0, 0 },
										{0, scaling_y, 0 , 0},
										{0, 0, scaling_z, 0},
										{0, 0, 0, 1}};
	}
}
