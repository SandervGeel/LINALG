package controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import model.Coordinate;
import model.Matrix;
import model.MatrixRotate;
import model.MatrixScale;
import model.MatrixTranslate;
import model.Vector;
import view.MyGui;

public class Controller implements IActionListener, IInfoHolder {
	private MyGui myGui;
	private Matrix object;
	private Matrix enemy;
	private Thread _thread;
	private int _screensize = 500;
	
	//private Matrix camera_matrix;
	//private Matrix perspective_matrix;
	private Matrix threeD_matrix;
	
	public Controller()
	{
		this.enemy = new Matrix();
		this.reset();
		this.init();
		myGui = new MyGui(this, this);
	}
	
	public void start()
	{
		this._thread = new Thread();
		_thread.start();
	}
	
	private void update()
	{
		if(this.enemy.get_z()[0] < 250){
			this.enemy = this.transform(0, 0, 5, this.enemy);
		}
		//this.rotate(0, 0, 0, 1, 0, 0, 3);
		myGui.repaint();
	}
	
	public void run()
	{
		try {
			this.update();
			_thread.sleep(300);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void init()
	{
		// camera matrix
		Vector eye;
		Vector lookAt;
		Vector direction_up;
		
		Vector x;
		Vector y;
		Vector z;
		
		eye = new Vector(0,0,300);
		lookAt = new Vector(0,0,0);
		direction_up = new Vector(0,1,0);
		
		z = new Vector((eye.get_x_vec() - lookAt.get_x_vec()), (eye.get_y_vec() - lookAt.get_y_vec()), (eye.get_z_vec() - lookAt.get_z_vec()), (eye.get_w_vec() - lookAt.get_w_vec()));
		z.normalize();
		
		y = new Vector(direction_up.get_x_vec(), direction_up.get_y_vec(), direction_up.get_z_vec());
		y.normalize();
		
		x = y.uit_product(z);
		x.normalize();
		
		y = z.uit_product(x);
		y.normalize();
		
		Matrix camera_matrix = new Matrix(new double[][]{	
											{x.get_x_vec(), x.get_y_vec(), x.get_z_vec(), -(x.in_product(eye))},											
											{y.get_x_vec(), y.get_y_vec(), y.get_z_vec(), -(y.in_product(eye))},
											{z.get_x_vec(), z.get_y_vec(), z.get_z_vec(), -(z.in_product(eye))},
											{0			  ,0			 ,0				,1					  }
		});
		
		// perspective matrix
		double near = 30;
		double far = 400;
		double fieldOfView = 90;
		double scale = near * Math.tan(((Math.PI/180) * fieldOfView) * 0.5);
		
		
		Matrix perspective_matrix = new Matrix(new double[][]{
															{scale,0 ,0 ,0 },											
															{0 ,scale ,0 ,0 },
															{0 ,0 ,((-far) / (far - near)) ,-1 },
															{0 ,0 ,(((-far) * near)/(far - near)),0 },
		});
		
		perspective_matrix.multiply_with_matrix(camera_matrix);
		this.threeD_matrix = perspective_matrix;
	}
	
	@Override
	public void scale(double scaling)
	{
		Matrix translation_matrix_to_o = new MatrixTranslate((object.get_x()[0] * -1),(object.get_y()[0] * -1) , (object.get_z()[0] * -1));
		Matrix translate_back_matrix = new MatrixTranslate(object.get_x()[0], object.get_y()[0], object.get_z()[0]);
		Matrix scale_matrix = new MatrixScale(scaling, scaling, scaling);
		
		translate_back_matrix.multiply_with_matrix(scale_matrix);
		translate_back_matrix.multiply_with_matrix(translation_matrix_to_o);
		translate_back_matrix.multiply_with_matrix(object);
		this.object = translate_back_matrix;
		//myGui.repaint();
	}
	
	private void scale(double scaling, Matrix object)	
	{
		Matrix translation_matrix_to_o = new MatrixTranslate((object.get_x()[0] * -1),(object.get_y()[0] * -1) , (object.get_z()[0] * -1));
		Matrix translate_back_matrix = new MatrixTranslate(object.get_x()[0], object.get_y()[0], object.get_z()[0]);
		Matrix scale_matrix = new MatrixScale(scaling, scaling, scaling);
		
		translate_back_matrix.multiply_with_matrix(scale_matrix);
		translate_back_matrix.multiply_with_matrix(translation_matrix_to_o);
		translate_back_matrix.multiply_with_matrix(object);
		object = translate_back_matrix;
	}

	@Override
	public void reset()
	{
		this.object = new Matrix(new double[][]{
			{5,5,5,4,5,6,5,6,5,4,5 ,5 ,5 ,4,5 ,6,5 ,6,5 ,4,4,4,4,4,6,6,6,6,4,4,4,4,6,6,6,6,4,0,4,0,6,10,6,10,4,0,4,0,6,10,6,10,6,6,4,4,6,6,4,4,0,0,0,0,0,0,0,0,10,10,10,10,10,10},
			{0,2,0,0,0,0,2,2,2,2,0 ,2 ,0 ,0,0 ,0,2 ,2,2 ,2,0,0,2,2,0,0,2,2,0,0,2,2,0,0,2,2,0,0,2,2,0,0 ,2,2 ,0,0,2,2,0,0 ,2,2 ,0,2,0,2,0,2,0,2,0,0,0,2,2,0,2,2,0 ,2 ,2 ,0 ,2 ,2 },
			{0,0,0,1,0,1,0,1,0,1,10,10,10,9,10,9,10,9,10,9,1,3,1,3,1,3,1,3,9,7,9,7,9,7,9,7,3,4,3,4,3,4 ,3,4 ,7,6,7,6,7,6,7 ,6,3 ,3,3,3,7,7,7,7,4,6,4,4,6,6,6,4,4 ,4 ,6 ,6 ,6 ,4 },
			{1,1,1,1,1,1,1,1,1,1,1 ,1 ,1 ,1,1 ,1,1 ,1,1 ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 ,1,1 ,1,1,1,1,1,1,1 ,1,1 ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 ,1 ,1 ,1 ,1 ,1 }
		});
		enemy.reset();
		
		this.transform(0, 0, 250, true);
		this.transform(10, -20, 0, false);
	}
	
	@Override
	public Matrix get_plane()
	{
		return this.object;
	}

	@Override
	public Matrix get_matrix() {
		return this.enemy;
	}

	@Override
	public void transform(int x, int y, int z, boolean plane) {
		if(plane){
					Matrix translate_matrix = new MatrixTranslate(x, y, z);
					translate_matrix.multiply_with_matrix(this.object);
					this.object = translate_matrix;
					//myGui.repaint();
		}else{
					Matrix translate_matrix = new MatrixTranslate(x, y, z);
					translate_matrix.multiply_with_matrix(this.enemy);
					this.enemy = translate_matrix;
					//myGui.repaint();

		}
	}

	private Matrix transform(int x, int y, int z, Matrix object) {
					Matrix translate_matrix = new MatrixTranslate(x, y, z);
					translate_matrix.multiply_with_matrix(object);
					object = translate_matrix;
					//myGui.repaint();
					return object;
	}

	@Override
	public void rotate(int x_begin, int y_begin, int z_begin, int x_end, int y_end, int z_end, int angle) {
		Matrix back_translation = new MatrixTranslate(x_begin, y_begin, z_begin);
		Matrix back_to_xyz = new MatrixRotate('y',Math.atan2((z_end - z_begin), (x_end - x_begin)),true);
		Matrix back_to_xy = new MatrixRotate('z', Math.atan2((y_end - y_begin), Math.sqrt(Math.pow((x_end - x_begin), 2) + Math.pow((z_end - z_begin), 2))), true);
		Matrix rotation = new MatrixRotate('x', (angle * (Math.PI / 180)), true);	
		Matrix to_x = new MatrixRotate('z', Math.atan2((y_end - y_begin), Math.sqrt(Math.pow((x_end - x_begin), 2) + Math.pow((z_end - z_begin), 2))), false);	
		Matrix to_xy = new MatrixRotate('y',Math.atan2((z_end - z_begin), (x_end - x_begin)),false);	
		Matrix translation = new MatrixTranslate(-x_begin, -y_begin, -z_begin);
		
		back_translation.multiply_with_matrix(back_to_xyz);
		back_translation.multiply_with_matrix(back_to_xy);
		back_translation.multiply_with_matrix(rotation);
		back_translation.multiply_with_matrix(to_x);
		back_translation.multiply_with_matrix(to_xy);
		back_translation.multiply_with_matrix(translation);
		back_translation.multiply_with_matrix(this.object);
		
		this.object = back_translation;
		//myGui.repaint();
	}
	
	private void rotate(int x_begin, int y_begin, int z_begin, int x_end, int y_end, int z_end, int angle, Matrix object) {
		Matrix back_translation = new MatrixTranslate(x_begin, y_begin, z_begin);
		Matrix back_to_xyz = new MatrixRotate('y',Math.atan2((z_end - z_begin), (x_end - x_begin)),true);
		Matrix back_to_xy = new MatrixRotate('z', Math.atan2((y_end - y_begin), Math.sqrt(Math.pow((x_end - x_begin), 2) + Math.pow((z_end - z_begin), 2))), true);
		Matrix rotation = new MatrixRotate('x', (angle * (Math.PI / 180)), true);	
		Matrix to_x = new MatrixRotate('z', Math.atan2((y_end - y_begin), Math.sqrt(Math.pow((x_end - x_begin), 2) + Math.pow((z_end - z_begin), 2))), false);	
		Matrix to_xy = new MatrixRotate('y',Math.atan2((z_end - z_begin), (x_end - x_begin)),false);	
		Matrix translation = new MatrixTranslate(-x_begin, -y_begin, -z_begin);
		
		back_translation.multiply_with_matrix(back_to_xyz);
		back_translation.multiply_with_matrix(back_to_xy);
		back_translation.multiply_with_matrix(rotation);
		back_translation.multiply_with_matrix(to_x);
		back_translation.multiply_with_matrix(to_xy);
		back_translation.multiply_with_matrix(translation);
		back_translation.multiply_with_matrix(object);
		
		object = back_translation;
		//myGui.repaint();
	}

	@Override
	public Matrix calculate_3D_matrix(Matrix object) {
		Matrix return_matrix = object;
		Matrix transform_matrix = new Matrix(this.threeD_matrix.get_matrix());
		transform_matrix.multiply_with_matrix(return_matrix);
		return_matrix = transform_matrix;
		
		//naberekening
		for(int j = 0; j < 2; j++){
			for(int i = 0; i < return_matrix.get_x().length; i++){
				return_matrix.get_matrix()[j][i] = (this._screensize / 2) + ((return_matrix.get_matrix()[j][i] + 1)/ return_matrix.get_matrix()[3][i]) * (this._screensize / 2);
			}
		}
		for(int i = 0; i < return_matrix.get_x().length; i++){
			return_matrix.get_matrix()[2][i] = -1 * return_matrix.get_matrix()[2][i];
		}
		return return_matrix;
	}

	@Override
	public int get_screensize() {
		return this._screensize;
	}

	}
