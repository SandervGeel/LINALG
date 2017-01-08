package model;

import java.util.ArrayList;

public class Matrix {
	protected double[][] matrix;
	
	public Matrix() {
		this.reset();
	}	
	
	public Matrix(double[][] cordinates)
	{
		this.matrix = cordinates;
	}
	
	public Matrix get_inverse_matrix(){
		Matrix right_matrix = new MatrixScale(1,1,1);
		Matrix left_matrix = new Matrix(this.get_matrix());
		
		// 1. set row with highest number on top
		int change_number = 0;
		double highest_number = left_matrix.get_matrix()[0][0];
		for(int j = 0; j < left_matrix.get_matrix().length; j++){
			for(int i = 0; i < left_matrix.get_x().length; i++){
				if(left_matrix.get_matrix()[j][i] > highest_number){
					change_number = j;
					highest_number = left_matrix.get_matrix()[j][i];
				}
			}
		}
		double[] highest_row_left = left_matrix.get_matrix()[change_number];
		double[] highest_row_right = right_matrix.get_matrix()[change_number];
		double[] first_row_left = left_matrix.get_x();
		double[] first_row_right = right_matrix.get_x();
		
		left_matrix.get_matrix()[0] = highest_row_left;
		left_matrix.get_matrix()[change_number] = first_row_left;
		right_matrix.get_matrix()[0] = highest_row_right;
		right_matrix.get_matrix()[change_number] = first_row_right;
		
		// 2. first column
		// 2a M 1,1
		highest_number = left_matrix.get_x()[0];
		for(int i = 0; i < left_matrix.get_x().length; i++){
			left_matrix.get_x()[i] = left_matrix.get_x()[i] / highest_number;
		}
		for(int i = 0; i < right_matrix.get_x().length; i++){
			right_matrix.get_x()[i] = right_matrix.get_x()[i] / highest_number;
		}
		// 2b vegen
		for(int i = 1; i < left_matrix.get_matrix().length - 1; i++){
			if(left_matrix.get_matrix()[i][0] != 0){
				double multiply_num = left_matrix.get_matrix()[i][0] / left_matrix.get_x()[0];
				for(int j = 0; j < left_matrix.get_matrix()[i].length; j++){
					left_matrix.get_matrix()[i][j] -= (left_matrix.get_x()[j] * multiply_num);
				}
				for(int j = 0; j < right_matrix.get_matrix()[i].length; j++){
					right_matrix.get_matrix()[i][j] -= (right_matrix.get_x()[j] * multiply_num);
				}
			}
		}
		
		// 3. second column
		// 3a M 2,2
		highest_number = left_matrix.get_y()[1];
		for(int i = 0; i < left_matrix.get_y().length; i++){
			left_matrix.get_y()[i] = left_matrix.get_y()[i] / highest_number;
		}
		for(int i = 0; i < right_matrix.get_y().length; i++){
			right_matrix.get_y()[i] = right_matrix.get_y()[i] / highest_number;
		}
		// 3b vegen
		for(int i = 0; i < left_matrix.get_matrix().length - 1; i += 2){
			if(left_matrix.get_matrix()[i][1] != 0){
				double multiply_num = left_matrix.get_matrix()[i][1] / left_matrix.get_y()[1];
				for(int j = 1; j < left_matrix.get_matrix()[i].length; j++){
					left_matrix.get_matrix()[i][j] -= (left_matrix.get_y()[j] * multiply_num);
				}
				for(int j = 0; j < right_matrix.get_matrix()[i].length; j++){
					right_matrix.get_matrix()[i][j] -= (right_matrix.get_y()[j] * multiply_num);
				}
			}
		}
		
		// 4. third column
		// 4a M 3,3
		highest_number = left_matrix.get_z()[2];
		for(int i = 0; i < left_matrix.get_z().length; i++){
			left_matrix.get_z()[i] = left_matrix.get_z()[i] / highest_number;
		}
		for(int i = 0; i < right_matrix.get_z().length; i++){
			right_matrix.get_z()[i] = right_matrix.get_z()[i] / highest_number;
		}
		// 4b vegen
		for(int i = 0; i < left_matrix.get_matrix().length - 2; i++){
			if(left_matrix.get_matrix()[i][2] != 0){
				double multiply_num = left_matrix.get_matrix()[i][2] / left_matrix.get_z()[2];
				for(int j = 1; j < left_matrix.get_matrix()[i].length; j++){
					left_matrix.get_matrix()[i][j] -= (left_matrix.get_z()[j] * multiply_num);
				}
				for(int j = 0; j < right_matrix.get_matrix()[i].length; j++){
					right_matrix.get_matrix()[i][j] -= (right_matrix.get_z()[j] * multiply_num);
				}
			}
		}
		
		
		return right_matrix;
	}
	
	public void multiply_with_matrix(Matrix other_matrix){
			double[][] temp_matrix = new double[matrix.length][other_matrix.get_x().length];
			for(int i = 0; i < matrix.length; i++){
				for(int j = 0; j < other_matrix.get_x().length; j++){
					for(int k = 0; k < other_matrix.get_matrix().length; k++){
						temp_matrix[i][j] += other_matrix.get_matrix()[k][j] * this.matrix[i][k];
					}
			}
		}
		this.matrix = temp_matrix;
	}

	public double[][] get_matrix()
	{
		return matrix;
	}

	public double[] get_x() {
		return matrix[0];
	}
	
	public double[] get_y(){
		return matrix[1];
	}
	
	public double[] get_z(){
		return matrix[2];
	}

	public void reset() {
		
		matrix = new double[][] {
			{0 ,10,0 ,0 ,0 ,0 ,10,10,10,0 ,10,10,0 ,0 ,0 ,10,0 ,0 ,10,0 ,10,10,10,10},
			{0 ,0 ,0 ,10,0 ,0 ,0 ,0 ,0 ,0 ,0 ,10,10,0 ,10,10,10,10,10,10,10,10,10,0 },
			{0 ,0 ,0 ,0 ,0 ,10,10,0 ,10,10,10,10,10,10,10,10,10,0 ,0 ,0 ,0 ,10,0 ,0 },
			{1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 }
		};
		/*
				matrix = new double[][]{	//cube	
					  {  10, 20, 20, 10, 10, 20, 10, 20, 15},	  //x row
					  {  10, 20, 10, 20, 10, 20, 20, 10, 15},	  //y row
					  {  10, 20, 10, 10, 20, 10, 20, 20, 20},	  //z row
					  {  1,1,1,1,1,1,1,1,1}   // 9 zeros cube + 1
					};
		*/
				// matrix = new double[][]{	//cube				// 4 front				// 3 back		// 1 top			//6 bottom								// 2 left				// 5 right
				//	  {  1, 2, 2, 1, 1, 2, 1, 2 /* */, 1.25, 1.75, 1.75, 1.25 /* */, 1.50, 1.75, 1.25 /* */, 1.50 /* */, 1.25, 1.25, 1.25, 1.75, 1.75, 1.75 /* */, 1.00, 1.00 /* */, 2.00, 2.00, 2.00, 2.00, 2.00},	  //x row
				//	  {  1, 2, 1, 2, 1, 2, 2, 1 /* */, 1.25, 1.25, 1.75, 1.75 /* */, 1.50, 1.25, 1.75 /* */, 1.00 /* */, 2.00, 2.00, 2.00, 2.00, 2.00, 2.00 /* */, 1.25, 1.75 /* */, 1.25, 1.25, 1.75, 1.75, 1.50},   //y row
				//	  {  1, 2, 1, 1, 2, 1, 2, 2 /* */, 2.00, 2.00, 2.00, 2.00 /* */, 1.00, 1.00, 1.00 /* */, 1.50 /* */, 1.25, 1.50, 1.75, 1.25, 1.50, 1.75 /* */, 1.75, 1.25 /* */, 1.25, 1.75, 1.25, 1.75, 1.50},	//z row
				//	  {  1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}   // 29 zeros (cube(2^3) + 1 + 2 + 3 + 4 + 5 + 6
				//	};
	}
	
}
