package app.commands.unit;

import java.awt.Point;

import app.Invoker;
import app.commands.Command;
import app.commands.ICommand;
import app.commands.unit.move.MoveUnitCommandBase;
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
		
		MoveUnitCommandBase moveCommand = (MoveUnitCommandBase)commandToExecute;
		
		Invoker invoker = Invoker.getInstance();
		Unit unit = moveCommand.getUnit();
		Point position = moveCommand.getPosition();
		
		if(unit.isBusy()) {
			path.add(position);
			invoker.queueMoveCommand(moveCommand);
		} else {
			path.add(unit.getPosition());
			invoker.setLastMoveCommand(moveCommand);
			Ani.killAll();
			moveCommand.execute();
			path.add(position);
		}
	}
}
