package app;

import java.awt.Point;

import app.commands.history.GoBackInMoveHistoryCommand;
import app.commands.history.GoForwardInMoveHistoryCommand;
import app.commands.history.PlayMoveHistoryFromCurrentCommand;
import app.commands.processing.CompleteUnitMoveCommand;
import app.commands.processing.CreateUnitMoveCommand;
import app.commands.processing.menu.ChangeMenuMoveTypeCommand;
import app.commands.processing.menu.ChangeUnitMoveTypeCommand;
import app.commands.processing.menu.LockMenuMoveTypeCommand;
import app.commands.processing.menu.UnLockMenuMoveTypeCommand;
import app.commands.unit.DoUnitMoveCommand;
import app.commands.unit.move.JumpMoveUnitCommand;
import app.commands.unit.move.RunMoveUnitCommand;
import app.commands.unit.move.WalkMoveUnitCommand;
import app.entities.History;
import app.entities.Markers;
import app.entities.Path;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;
import app.gui.GUI;
import app.gui.Menu;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import controlP5.Controller;
import de.looksgood.ani.Ani;
import hype.H;
import hype.HCallback;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class UnitCommands extends PApplet 
{
	static private final int SIZE = 300;
	
	GUI gui;
	Unit unit;
	Markers markers;
	History history;
	
	private final Path path = new Path();
	
	private final Invoker _invoker = Invoker.getInstance();

	public boolean clickPossible = true;
	public boolean showHistoryPath = false;

	public void settings() { size(SIZE,SIZE); }

	public void setup() 
	{
		Ani.init(this);

		unit = new Unit(this);
		gui = new GUI(this);
		path.setCanvas(this);
		path.setColor(color(50, 50, 50));
		markers = new Markers(this, 6, H.BLUE);
		history = new History(this);
		
		Menu menu = gui.getMenu();
		
		_invoker.registerMoveCommand(MoveType.WALK, 	WalkMoveUnitCommand.class	);
		_invoker.registerMoveCommand(MoveType.RUN, 		RunMoveUnitCommand.class	);
		_invoker.registerMoveCommand(MoveType.JUMP, 	JumpMoveUnitCommand.class	);
		
		_invoker.registerProcessCommand(ProcessCommand.DO_UNIT_MOVE, new DoUnitMoveCommand(path));
		_invoker.registerProcessCommand(ProcessCommand.CREATE_UNIT_MOVE, new CreateUnitMoveCommand());
		_invoker.registerProcessCommand(ProcessCommand.COMPLETE_UNIT_MOVE, new CompleteUnitMoveCommand(path));
		_invoker.registerProcessCommand(ProcessCommand.LOCK_MENU_MOVE_TYPE, new LockMenuMoveTypeCommand(menu));
		_invoker.registerProcessCommand(ProcessCommand.UNLOCK_MENU_MOVE_TYPE, new UnLockMenuMoveTypeCommand(menu));
		_invoker.registerProcessCommand(ProcessCommand.CHANGE_MENU_MOVE_TYPE, new ChangeMenuMoveTypeCommand(menu));
		_invoker.registerProcessCommand(ProcessCommand.CHANGE_UNIT_MOVE_TYPE, new ChangeUnitMoveTypeCommand(menu, path));
		_invoker.registerProcessCommand(ProcessCommand.GO_BACK_IN_MOVE_HISTORY, new GoBackInMoveHistoryCommand());
		_invoker.registerProcessCommand(ProcessCommand.GO_FORWARD_IN_MOVE_HISTORY, new GoForwardInMoveHistoryCommand());
		_invoker.registerProcessCommand(ProcessCommand.PLAY_MOVE_HISTORY_FROM_CURRENT, new PlayMoveHistoryFromCurrentCommand());
		
		CallbackListener menuCallbackListener = new CallbackListener() {
			public void controlEvent(CallbackEvent event) 
			{ 
				Controller<?> controller = event.getController();
				
				if(controller.equals(gui.getMenu())) {
					_invoker.executeProcessCommand(ProcessCommand.CHANGE_UNIT_MOVE_TYPE, unit);
				}
				else if(controller.equals(gui.getHistoryForwardButton())) {
					_invoker.executeProcessCommand(ProcessCommand.GO_FORWARD_IN_MOVE_HISTORY);
				}
				else if(controller.equals(gui.getHistoryBackButton())) {
					_invoker.executeProcessCommand(ProcessCommand.GO_BACK_IN_MOVE_HISTORY);
				}
				else if(controller.equals(gui.getHistoryPlayButton())) {
					_invoker.executeProcessCommand(ProcessCommand.PLAY_MOVE_HISTORY_FROM_CURRENT);
				}
				else if(controller.equals(gui.getHistoryShowPathToggle())) {
					showHistoryPath = !showHistoryPath;
				}
			}
		};
		
		unit.addActionCompleteCallback(new HCallback() {
			public void run(Object arg) { _invoker.executeProcessCommand(ProcessCommand.COMPLETE_UNIT_MOVE); }
		});

		gui.setCallbackListener(menuCallbackListener);
		gui.getMenu().setSelectedMoveType(MoveType.RUN);
		
		/**
		 * INITIAL PATH
		 */
		_invoker.executeProcessCommand(ProcessCommand.CREATE_UNIT_MOVE, unit, new Point((int)(this.width / 2), (int)(this.height / 2)));
		int counter = 1;
		while(counter-- > 0) {
			_invoker.executeProcessCommand(ProcessCommand.CREATE_UNIT_MOVE, unit, new Point((int)(Math.random() * this.width), (int)(Math.random() * this.height)));
		}
	}

	public void mouseReleased(MouseEvent event) {
		if(clickPossible == false) return;
		_invoker.executeProcessCommand(ProcessCommand.CREATE_UNIT_MOVE, unit, new Point(event.getX(), event.getY()));
	}
	
	public void draw() 
	{
		this.clear();
		this.background(color(10,10,10));

		path.draw();
		unit.draw();
		markers.draw();
		
		if(showHistoryPath) {
			history.draw();
		}
	}
}
