package app.entities;

import java.awt.Point;

import processing.core.PApplet;

public class Markers {
	private PApplet _canvas;
	private int 
	_size, 
	_padding, 
	_color
	;
	
	private Point[] _points;
	private Point _tempPoint;
	private int _pointsCounter;
	
	public Markers(PApplet canvas, int size, int padding, int color) {
		_canvas = canvas;
		_size = size;
		_padding = padding;
		_color = color;
		_points = new Point[5];
		_points[0] = new Point(_padding, _padding);
		_points[1] = new Point(_canvas.width-_padding, _padding);
		_points[2] = new Point(_canvas.width-_padding, _canvas.height-_padding);
		_points[3] = new Point(_padding, _canvas.height-_padding);
		_points[4] = new Point(_canvas.width/2, _canvas.height/2);
	}

	public void draw() 
	{
		_canvas.noFill(); 
		_canvas.strokeWeight(1); 
		_canvas.stroke(_color);

		_pointsCounter = _points.length;
		while(_pointsCounter-- > 0) {
			_tempPoint = _points[_pointsCounter];
			_canvas.ellipse(_tempPoint.x, _tempPoint.y, _size, _size);
		}
	}
}
