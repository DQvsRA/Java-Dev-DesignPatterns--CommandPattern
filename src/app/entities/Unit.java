package app.entities;

import java.awt.Point;

import app.enums.MoveType;
import hype.H;
import hype.HCallback;
import processing.core.PApplet;

public class Unit
{	
	public final static float SIZE = 25f;
	
	private MoveType _moveType;
	private Boolean _busy = false;
	private HCallback _callback;
	private PApplet _canvas;
	private int _color;
	private int _endCounter;
	
	public float radius;
	public float x, y;
	
	public Unit(PApplet canvas) {
		_canvas = canvas;
		radius = SIZE;
	}
	
	public void draw() 
	{
		if(radius > 0) {
			_canvas.stroke(_canvas.color(255, 255, 200));
			_canvas.strokeWeight(2);
			_canvas.fill(_color);
			_canvas.smooth();
			_canvas.ellipse(x, y, radius, radius);
		}
	}
	
	public void setMoveType(MoveType value) 
	{
		switch (value) {
		case JUMP: 	_color = H.MAGENTA; 	break;
		case WALK: 	_color = H.CYAN; 	break;
		case RUN: 	_color = H.GREEN; 	break;
		case FLOW: 	_color = H.RED; 		break;
		
		default:
			_color = H.LGREY;
			break;
		}
		_moveType = value;
	}
	
	public MoveType getMoveType() { return _moveType; }
	public Point getPosition() { return new Point((int)this.x, (int)this.y); }

	public Boolean isBusy() { return _busy; }
	public void isBusy(Boolean _busy) { 
		this._busy = _busy;
		if(_busy == false) {
			_callback.run(null);
		}
	}
	
	public void reset() {
		radius = SIZE;
	}
	
	public void commandStart(){
		_endCounter = 0;
		isBusy(true);
	}
	
	public void commandEnd(){
		_endCounter += 1;
		if(_endCounter == 2)
			isBusy(false);
	}
	
	public void addActionCompleteCallback(HCallback callback) {
		_callback = callback;
	}

	public PApplet getCanvas() {
		return _canvas;
	}
}
