package app.commands.history;

import app.Invoker;
import app.commands.Command;
import app.enums.ProcessCommand;

public class PlayMoveHistoryFromCurrentCommand extends Command 
{
	public void execute() 
	{
		Invoker invoker = Invoker.getInstance();
		System.out.println("PlayMoveHistoryFromCurrentCommand: " + invoker.nextHistoryCommandExist());
		
		if(invoker.nextHistoryCommandExist()) 
		{
			invoker.playingHistory = true;
			invoker.clearMoveCommandQueue();
			invoker.setCurrentMoveCommand(null);
			invoker.getMoveHistoryFromCurrentCommand().forEach((c)->{
				invoker.executeProcessCommandWithAnotherCommand(ProcessCommand.DO_UNIT_MOVE, c);
			});
		}
	}
}
