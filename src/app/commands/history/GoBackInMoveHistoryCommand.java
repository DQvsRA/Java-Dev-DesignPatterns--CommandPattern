package app.commands.history;

import app.Invoker;
import app.commands.Command;
import app.commands.unit.move.MoveUnitCommandBase;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;

public class GoBackInMoveHistoryCommand extends Command {

	private MoveType _moveType;
	
	public void execute() 
	{
		Invoker invoker = Invoker.getInstance();
		System.out.println("GoBackInMoveHistoryCommand: " + invoker.previousHistoryCommandExist());
		
		if(invoker.previousHistoryCommandExist()) 
		{
			MoveUnitCommandBase command = (MoveUnitCommandBase) invoker.getPreviousMoveHistoryCommand();
			Unit unit = command.getUnit();
			MoveType commandUnitMoveType = unit.getMoveType();
			if(_moveType != null && _moveType != commandUnitMoveType) {
				invoker.executeProcessCommand(ProcessCommand.CHANGE_UNIT_MOVE_TYPE, unit);
			}
			_moveType = commandUnitMoveType;
			invoker.executeProcessCommandWithAnotherCommand(ProcessCommand.DO_UNIT_MOVE, command);
			
//			Unit unit = command.getUnit();
//			Point position = command.getPosition();
//			System.out.println("\t\t isBusy " + unit.isBusy());
//			if(unit.isBusy()) {
//				path.add(position);
//				invoker.queueMoveCommand(command);
//			} else {
//				path.add(unit.getPosition());
//				invoker.setLastMoveCommand(command);
//				Ani.killAll();
//				command.execute();
//				path.add(position);
//			}
		}
	}
}
