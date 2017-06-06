package app.commands.history;

import app.Invoker;
import app.commands.Command;
import app.commands.ICommand;

import app.enums.ProcessCommand;

public class GoForwardInMoveHistoryCommand extends Command 
{
	public void execute() 
	{
		Invoker invoker = Invoker.getInstance();
		System.out.println("GoForwardInMoveHistoryCommand: " + invoker.nextHistoryCommandExist());
		
		if(invoker.nextHistoryCommandExist()) 
		{
			ICommand command = invoker.getNextMoveHistoryCommand();
			invoker.executeProcessCommandWithAnotherCommand(ProcessCommand.DO_UNIT_MOVE, command);
			
//			Unit unit = command.getUnit();
//			Point position = command.getPosition();
//			System.out.println("\t\t isBusy: " + unit.isBusy());
//			if(unit.isBusy()) {
//				path.add(position);
//				invoker.queueMoveCommand(command);
//			} else {
//				path.add(unit.getPosition());
//				invoker.setLastMoveCommand(command);
//				
//				command.execute();
//				path.add(position);
//			}
		}
	}
}
