package app.commands.processing;

import app.Invoker;
import app.commands.Command;
import app.commands.ICommand;
import app.entities.Path;
import app.enums.ProcessCommand;
import de.looksgood.ani.Ani;

public class CompleteUnitMoveCommand extends Command {

	private Path path;

	public CompleteUnitMoveCommand(Path path) {
		this.path = path;
	}

	public void execute() 
	{
		Invoker invoker = Invoker.getInstance();
		
		ICommand command = invoker.getCurrentMoveCommand();
		if(command != null)	invoker.storeCommandToHistory(command);
		
		Ani.killAll();
		if(invoker.hasQueueMoveCommand()) {
			path.remove(0);
			command = (ICommand) invoker.getNextQueueMoveCommand();
			invoker.setCurrentMoveCommand(command);
			command.execute();
		} else {
			path.clear();
			if(invoker.playingHistory) {
				invoker.playingHistory = false;
				invoker.executeProcessCommand(ProcessCommand.CHANGE_UNIT_MOVE_TYPE);
			}
		}
	}
}
