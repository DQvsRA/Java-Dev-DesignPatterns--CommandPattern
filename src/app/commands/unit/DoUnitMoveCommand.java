package app.commands.unit;

import java.awt.Point;

import app.Invoker;
import app.commands.Command;
import app.commands.ICommand;
import app.entities.Path;
import app.entities.Unit;
import de.looksgood.ani.Ani;

public final class DoUnitMoveCommand extends Command 
{
	private Path path;

	public DoUnitMoveCommand(Path path) {
		this.path = path;
	}
	
	@Override
	public void execute(ICommand commandToExecute) 
	{
		System.out.println("DoUnitMoveCommand");
		
		Invoker invoker = Invoker.getInstance();
		
		MoveUnitCommandBase moveCommand = (MoveUnitCommandBase)commandToExecute;
		Point toPosition = moveCommand.getToPosition();
		Unit unit = moveCommand.getUnit();
		Point fromPosition = unit.getPosition();
		
		if(path.size() == 0) path.add(fromPosition);
		
		if(unit.isBusy()) 
		{
			invoker.queueMoveCommand(commandToExecute);
		} 
		else 
		{
			invoker.setCurrentMoveCommand(commandToExecute);
			moveCommand.execute();
		}
		path.add(toPosition);
	}
}
