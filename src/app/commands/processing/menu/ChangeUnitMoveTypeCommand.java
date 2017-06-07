package app.commands.processing.menu;

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
	private DropdownList moveTypesList;
	private List<Point> path;

	public ChangeUnitMoveTypeCommand(DropdownList moveTypesList, List<Point> path)
	{
		this.moveTypesList = moveTypesList;
		this.path = path;
	}
	
	public void execute(Unit unit) 
	{
		Invoker invoker = Invoker.getInstance();
		unit.setMoveType(MoveType.values()[(int)moveTypesList.getValue()]);
		
		if(unit.isBusy() && invoker.playingHistory == false) 
		{
			invoker.clearMoveCommandQueue();
			invoker.setCurrentMoveCommand(null);
			
			Ani.killAll();
			unit.reset();
			
			System.out.println("ChangeUnitMoveTypeCommand: " + path.size());
			
			if(path.size() > 0)
			{
				path.remove(0);
				List<Point> tempPositionsList = new ArrayList<Point>();
				while(path.size() > 0) tempPositionsList.add(path.remove(0));
				unit.isBusy(false);
				tempPositionsList.forEach((Point p) -> {
					invoker.executeProcessCommand(ProcessCommand.CREATE_UNIT_MOVE, unit, p);
				});
			}
		}
	}
}
