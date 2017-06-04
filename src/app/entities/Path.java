package app.entities;

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;

public class Path extends ArrayList<Point>
{
	private static final long serialVersionUID = 1L;
	
	private PApplet _canvas;
	private int 
	_size = 2, 
	_sizeVert = _size * 2, 
	_color = 0
	;
	
	public void draw() 
	{
		if(_canvas != null && size() > 0) 
		{
			_canvas.noFill();
			_canvas.strokeWeight(_size);
			_canvas.stroke(_color);
			_canvas.strokeJoin(PApplet.MITER);
			_canvas.beginShape();
			this.forEach((Point p)->{
				_canvas.vertex(p.x, p.y);
				_canvas.ellipse(p.x, p.y, _sizeVert, _sizeVert);
			});
			_canvas.endShape();	
			
		}
	}

	public void setCanvas(PApplet _canvas) {
		this._canvas = _canvas;
	}

	public void setColor(int color) {
		this._color = color;
	}
}
