package app.commands.history;

import java.awt.Point;

import app.Invoker;
import app.commands.Command;
import app.commands.unit.MoveUnitCommandBase;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;

public class GoForwardInMoveHistoryCommand extends Command 
{
	public void execute() 
	{
		Invoker invoker = Invoker.getInstance();
		System.out.println("GoForwardInMoveHistoryCommand: " + invoker.nextHistoryCommandExist());
		
		if(invoker.nextHistoryCommandExist()) 
		{
			MoveUnitCommandBase command = (MoveUnitCommandBase) invoker.getNextMoveHistoryCommand();
			Unit unit = command.getUnit();
			Point position = command.getToPosition();
						
			if(invoker.nextHistoryCommandExist())
			{
				command = (MoveUnitCommandBase) invoker.getNextMoveHistoryCommand();
				MoveType moveType = command.getMoveType();
				invoker.executeProcessCommand(ProcessCommand.CHANGE_MENU_MOVE_TYPE, moveType);
				unit.setMoveType(moveType);
				invoker.getPreviousMoveHistoryCommand();
			}
			unit.x = position.x; 
			unit.y = position.y;
		}
	}
}
