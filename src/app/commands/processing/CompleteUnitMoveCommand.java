package app.commands.processing;

import java.util.List;

import app.UnitCommands;
import app.commands.Command;
import app.commands.ICommand;
import app.entities.Path;
import de.looksgood.ani.Ani;

public class CompleteUnitMoveCommand extends Command {

	private List<ICommand> commandsList;
	private Path path;
	private ICommand _lastCommand = null;

	public CompleteUnitMoveCommand(List<ICommand> commandsList, Path path) {
		this.commandsList = commandsList;
		this.path = path;
	}
	
	public void execute() {
		System.out.println("> ActionCompleteCallback : " + commandsList.size());
		Ani.killAll();
		if(_lastCommand != null) UnitCommands.historyList.add(_lastCommand);
		
		if(commandsList.size() > 0) {
			path.remove(0);
			_lastCommand = (ICommand) commandsList.remove(0);
			_lastCommand.execute();
		} else {
			path.clear();
		}

	}

}
