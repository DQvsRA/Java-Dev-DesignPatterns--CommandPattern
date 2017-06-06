package app.commands.processing;

import app.Invoker;
import app.commands.Command;
import app.commands.ICommand;
import app.entities.Path;
import de.looksgood.ani.Ani;

public class CompleteUnitMoveCommand extends Command {

	private Path path;
	
	public CompleteUnitMoveCommand(Path path) {
		this.path = path;
	}

	public void execute() 
	{
		Ani.killAll();
		
		Invoker invoker = Invoker.getInstance();
		
		ICommand command = invoker.getLastMoveCommand();
		if(command != null)	invoker.storeCommandToHistory(command);
		
		if(invoker.hasQueueMoveCommand()) {
			path.remove(0);
			command = (ICommand) invoker.getNextQueueMoveCommand();
			invoker.setLastMoveCommand(command);
			command.execute();
		} else {
			path.clear();
		}
	}
}
