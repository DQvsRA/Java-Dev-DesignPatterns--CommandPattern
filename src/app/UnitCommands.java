package app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.commands.ICommand;
import app.commands.processing.ChangeUnitMoveTypeCommand;
import app.commands.processing.CompleteUnitMoveCommand;
import app.commands.processing.CreateUnitMoveCommand;
import app.commands.unit.move.JumpMoveUnitCommand;
import app.commands.unit.move.RunMoveUnitCommand;
import app.commands.unit.move.WalkMoveUnitCommand;
import app.entities.Markers;
import app.entities.Path;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;
import app.gui.GUI;
import controlP5.CallbackEvent;
import controlP5.CallbackListener;
import de.looksgood.ani.Ani;
import hype.H;
import hype.HCallback;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class UnitCommands extends PApplet 
{
	static private final int SIZE = 800;
	
	static public final Map<MoveType, Class<?>> unitMoveCommands = new HashMap<MoveType, Class<?>>();
	static public final Map<ProcessCommand, ICommand> processCommands = new HashMap<ProcessCommand, ICommand>();

	static public final List<ICommand> historyList = new ArrayList<ICommand>();
	
	GUI gui;
	Unit unit;
	Markers markers;
	
	private final List<ICommand> commandsList = new ArrayList<ICommand>();
	private final Path path = new Path();

	public boolean clickPossible = true;

	public void settings() { size(SIZE,SIZE); }

	public void setup() 
	{
		Ani.init(this);

		unit = new Unit(this);
		gui = new GUI(this);
		path.setCanvas(this);
		path.setColor(color(30, 30, 30));

		unitMoveCommands.put(MoveType.WALK, WalkMoveUnitCommand.class);
		unitMoveCommands.put(MoveType.RUN, 	RunMoveUnitCommand.class);
		unitMoveCommands.put(MoveType.JUMP, JumpMoveUnitCommand.class);

		processCommands.put(ProcessCommand.CREATE_UNIT_MOVE, new CreateUnitMoveCommand(unitMoveCommands, commandsList, path));
		processCommands.put(ProcessCommand.COMPLETE_UNIT_MOVE, new CompleteUnitMoveCommand(commandsList, path));
		processCommands.put(ProcessCommand.CHANGE_UNIT_MOVE_TYPE, new ChangeUnitMoveTypeCommand(gui.getMenu(), commandsList, path));

		unit.addActionCompleteCallback(new HCallback() {
			public void run(Object arg) { processCommands.get(ProcessCommand.COMPLETE_UNIT_MOVE).execute(); }
		});

		gui.getMenu().onChange(new CallbackListener() {
			public void controlEvent(CallbackEvent event) {
				processCommands.get(ProcessCommand.CHANGE_UNIT_MOVE_TYPE).execute(unit, path.size() > 0 ? path.remove(0) : null);
			}
		});
		gui.getMenu().setSelectedMoveType(MoveType.JUMP);

		markers = new Markers(this, 6, 150, H.BLUE);

		processCommands.get(ProcessCommand.CREATE_UNIT_MOVE).execute(unit, new Point((int)(this.width / 2), (int)(this.height / 2)));
		
		/**
		 * INITIAL PATH
		 */
		int counter = 10;
		while(counter-- > 0)
			processCommands.get(ProcessCommand.CREATE_UNIT_MOVE).execute(unit, new Point((int)(Math.random() * this.width), (int)(Math.random() * this.height)));
	}

	public void mouseReleased(MouseEvent event) {
		if(clickPossible == false) return;
		processCommands.get(ProcessCommand.CREATE_UNIT_MOVE).execute(unit, new Point(event.getX(), event.getY()));
	}

	public void draw() 
	{
		this.clear();
		this.background(color(10,10,10));

		path.draw();
		unit.draw();
		markers.draw();

	}
	
	
}
