package app.gui;

import app.UnitCommands;
import controlP5.Button;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.ControlP5;
import controlP5.Toggle;
import processing.core.PApplet;

public final class GUI extends ControlP5 
{
	public static final int BUTTON_WIDTH = 96;
	public static final int DEFAULT_HEIGHT = 24;
	public static final int OFFSET = 4;

	private PApplet _canvas;

	private Menu 	_menu;
	private Button 	_historyBackButton;
	private Button 	_historyForwardButton;
	private Button 	_historyPlayButton;
	private Toggle 	_showHistoryPathToggle;
	
	public GUI(PApplet canvas) 
	{
		super(canvas);
		_canvas = canvas;
		_menu = new Menu(this, "Move Types", DEFAULT_HEIGHT);
		
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
		
		_historyBackButton = this.addButton("Back")
			.setPosition(canvas.width - BUTTON_WIDTH, 0)
		   	.setSize(BUTTON_WIDTH, DEFAULT_HEIGHT)
		;
		
		_historyForwardButton = this.addButton("Forward")
			.setPosition(canvas.width - BUTTON_WIDTH, DEFAULT_HEIGHT + OFFSET)
		   	.setSize(BUTTON_WIDTH, DEFAULT_HEIGHT)
		;
		
		_historyPlayButton = this.addButton("Play")
			.setPosition(canvas.width - BUTTON_WIDTH, (DEFAULT_HEIGHT + OFFSET) * 2)
		   	.setSize(BUTTON_WIDTH, DEFAULT_HEIGHT)
		;
		
		_showHistoryPathToggle = this.addToggle("Show History")
	     	.setPosition(canvas.width - BUTTON_WIDTH, (DEFAULT_HEIGHT + OFFSET) * 3)
	     	.setSize(BUTTON_WIDTH,DEFAULT_HEIGHT)
	     	.setMode(ControlP5.SWITCH)
	     	.setValue(false)
	    ;
	}

	public Menu getMenu() { return _menu; }
	
	public Button getHistoryPlayButton() { return _historyPlayButton; }
	public Button getHistoryBackButton() { return _historyBackButton; }
	public Button getHistoryForwardButton() { return _historyForwardButton; }
	public Toggle getHistoryShowPathToggle() { return _showHistoryPathToggle; }

	public void setCallbackListener(CallbackListener menuCallbackListener) 
	{
		_menu.onChange(menuCallbackListener);
		_historyForwardButton.onRelease(menuCallbackListener);
		_historyBackButton.onRelease(menuCallbackListener);
		_historyPlayButton.onRelease(menuCallbackListener);
		_showHistoryPathToggle.onChange(menuCallbackListener);
	}
}
