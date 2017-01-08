package model;

public class MatrixTranslate extends Matrix{
	public MatrixTranslate(double x, double y, double z){
		
		this.matrix = new double[][]   {{1, 0, 0, x},
										{0, 1, 0, y},
										{0, 0, 1, z},
										{0, 0 , 0, 1}};
	}
}
