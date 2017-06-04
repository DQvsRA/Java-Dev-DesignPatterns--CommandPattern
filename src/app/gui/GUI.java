package app.gui;

import app.UnitCommands;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;
import processing.core.PApplet;

public final class GUI extends ControlP5 {

	private Menu _menu;
	private PApplet _canvas;
	
	public GUI(PApplet canvas) {
		super(canvas);
		_canvas = canvas;
		_menu = new Menu(this, "Move Types");
		
		this.setPosition(0, 0);
		this.addCallback(new CallbackListener() {
			public void controlEvent(CallbackEvent theEvent) {
				switch(theEvent.getAction()) {
					case(ControlP5.ACTION_ENTER):
						((UnitCommands)_canvas).clickPossible = false;
						_canvas.cursor(PApplet.HAND);
					break;
					case(ControlP5.ACTION_LEAVE):
						((UnitCommands)_canvas).clickPossible = true;
						_canvas.cursor(PApplet.ARROW);
					break;
				}
			}
		});	
		
	}

	public Menu getMenu() {
		return _menu;
	}
}
