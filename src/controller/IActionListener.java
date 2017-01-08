package controller;

import java.awt.Color;

public interface IActionListener {

	public void scale(double scaling);
	public void reset();
	
	public void transform(int x, int y, int z, boolean plane);
	public void rotate(int x_begin, int y_begin, int z_begin, int x_end, int y_end, int z_end,  int angle);
}
