package app.commands.history;

import java.awt.Point;

import app.Invoker;
import app.commands.Command;
import app.commands.unit.MoveUnitCommandBase;
import app.entities.Unit;
import app.enums.ProcessCommand;

public class GoBackInMoveHistoryCommand extends Command 
{
	public void execute() 
	{
		Invoker invoker = Invoker.getInstance();
		System.out.println("GoBackInMoveHistoryCommand: " + invoker.previousHistoryCommandExist());
		
		if(invoker.previousHistoryCommandExist()) 
		{
			MoveUnitCommandBase currentCommand = (MoveUnitCommandBase) invoker.getCurrentMoveCommand();
			MoveUnitCommandBase previousCommand = (MoveUnitCommandBase) invoker.getPreviousMoveHistoryCommand();
			Unit unit = previousCommand.getUnit();
			Point position = previousCommand.getToPosition();
			unit.setMoveType(currentCommand.getMoveType());
			unit.x = position.x; 
			unit.y = position.y;
			
			invoker.executeProcessCommand(ProcessCommand.LOCK_MENU_MOVE_TYPE);
		}
	}
}
