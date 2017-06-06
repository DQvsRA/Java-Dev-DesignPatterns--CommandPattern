package app.commands.processing;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import app.Invoker;
import app.commands.Command;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;
import controlP5.DropdownList;
import de.looksgood.ani.Ani;

public class ChangeUnitMoveTypeCommand extends Command 
{
	private DropdownList moveList;
	private List<Point> path;

	public ChangeUnitMoveTypeCommand(DropdownList moveList, List<Point> path)
	{
		this.moveList = moveList;
		this.path = path;
	}
	
	public void execute(Unit unit) 
	{
		Invoker invoker = Invoker.getInstance();
		unit.setMoveType(MoveType.values()[(int)moveList.getValue()]);
		invoker.commandQueue().clear();
		Ani.killAll();
		
		if(path.size() > 0)
		{
			Point currentMoveStartPosition = path.remove(0);
			List<Point> tempPositionsList = new ArrayList<Point>();
			while(path.size() > 0) tempPositionsList.add(path.remove(0));
			tempPositionsList.forEach((Point p) -> {
				invoker.executeProcessCommand(ProcessCommand.CREATE_UNIT_MOVE, unit, p);
			});
			path.add(0, unit.getPosition());				
			path.add(0, currentMoveStartPosition);
		}
		
		unit.reset();
		unit.isBusy(false);
	}
}
