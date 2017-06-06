package app.entities;

import java.awt.Point;

import app.Invoker;
import app.commands.unit.move.MoveUnitCommandBase;
import processing.core.PApplet;

public final class History 
{
	private PApplet _canvas;
	private Invoker _invoker = Invoker.getInstance();
	private int _size = 3, _sizeVert = 5;
	
	private Point _tempPoint;
	
	public History(PApplet canvas) {
		_canvas = canvas;
	}
	
	public void draw()
	{
		_canvas.noFill();
		_canvas.strokeWeight(_size);
		_canvas.stroke(_canvas.color(230, 230, 50));
		_canvas.strokeJoin(PApplet.MITER);
		_canvas.beginShape();
		_invoker.goThroughMovesHistory((c)->{
			_tempPoint = ((MoveUnitCommandBase)c).getPosition(); 
			_canvas.vertex(_tempPoint.x, _tempPoint.y);
			_canvas.ellipse(_tempPoint.x, _tempPoint.y, _sizeVert, _sizeVert);
		});
		_canvas.endShape();	
		_tempPoint = null;
	}

}
