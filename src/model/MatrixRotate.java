package model;

public class MatrixRotate extends Matrix{

	public MatrixRotate(char which_axis, double angle, boolean positive_direction){
		switch (which_axis){
		case 'x':
			if(positive_direction){
				this.matrix = new double[][]   
						{{1, 0, 0, 0},
						{0, Math.cos(angle), -Math.sin(angle), 0},
						{0, Math.sin(angle), Math.cos(angle), 0},
						{0, 0 ,0, 1}};
			} else {
				this.matrix = new double[][]   
						{{1, 0, 0, 0},
						{0, Math.cos(angle), Math.sin(angle), 0},
						{0, -Math.sin(angle), Math.cos(angle), 0},
						{0, 0 ,0, 1}};
			}
			break;
		case'y':
			if(positive_direction){
				this.matrix = new double[][]   
						{{Math.cos(angle), 0, -Math.sin(angle), 0},
						{0, 1, 0, 0},
						{Math.sin(angle), 0, Math.cos(angle), 0},
						{0, 0 ,0, 1}};
			} else {
				this.matrix = new double[][]   
						{{Math.cos(angle), 0, Math.sin(angle), 0},
						{0, 1, 0, 0},
						{-Math.sin(angle), 0, Math.cos(angle), 0},
						{0, 0 ,0, 1}};
			}
			break;
			
		case'z':
			if(positive_direction){
				this.matrix = new double[][]   
						{{Math.cos(angle), -Math.sin(angle), 0, 0},
						{Math.sin(angle), Math.cos(angle), 0, 0},
						{0, 0, 1, 0},
						{0, 0 ,0, 1}};
			} else {
				this.matrix = new double[][]   
						{{Math.cos(angle), Math.sin(angle), 0, 0},
						{-Math.sin(angle), Math.cos(angle), 0, 0},
						{0, 0, 1, 0},
						{0, 0 ,0, 1}};
			}
			break;
		default:
			break;
		}
	}
}
