package controller;

import java.util.ArrayList;

import model.Matrix;
import model.Vector;

public interface IInfoHolder {
	public Matrix get_matrix();
	public Matrix get_plane();
	public Matrix calculate_3D_matrix(Matrix object);
	public int get_screensize();
}
