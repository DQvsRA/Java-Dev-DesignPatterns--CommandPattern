package app.commands.history;

import java.awt.Point;

import app.Invoker;
import app.commands.Command;
import app.commands.unit.MoveUnitCommandBase;
import app.entities.Unit;
import app.enums.MoveType;
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
			MoveType moveType = currentCommand.getMoveType();
			invoker.executeProcessCommand(ProcessCommand.CHANGE_MENU_MOVE_TYPE, moveType);
			
			unit.setMoveType(moveType);
			unit.x = position.x; 
			unit.y = position.y;
		}
	}
}
