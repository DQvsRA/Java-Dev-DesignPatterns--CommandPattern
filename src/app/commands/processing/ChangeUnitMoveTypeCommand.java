package app.commands.processing;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import app.UnitCommands;
import app.commands.Command;
import app.commands.ICommand;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;
import controlP5.DropdownList;
import de.looksgood.ani.Ani;

public class ChangeUnitMoveTypeCommand extends Command 
{
	private DropdownList moveList;
	private List<ICommand> commandsList;
	private List<Point> tail;

	public ChangeUnitMoveTypeCommand(DropdownList moveList, List<ICommand> commandsList, List<Point> tail)
	{
		this.moveList = moveList;
		this.commandsList = commandsList;
		this.tail = tail;
	}
	
	public void execute(Unit unit, Point position) 
	{
		unit.setMoveType(MoveType.values()[(int)moveList.getValue()]);
		commandsList.clear();
		Ani.killAll();
		if(position != null) {
			List<Point> tempPositionsList = new ArrayList<Point>();
			while(tail.size() > 0) tempPositionsList.add(tail.remove(0));
			tempPositionsList.forEach((Point p) -> {
				((ICommand)UnitCommands.processCommands.get(ProcessCommand.CREATE_UNIT_MOVE)).execute(unit, p);
			});
			tail.add(0, unit.getPosition());				
			tail.add(0, position);
		}
		unit.reset();
		unit.isBusy(false);
	}
}
