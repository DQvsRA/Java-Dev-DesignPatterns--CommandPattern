package app.commands.processing;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import app.Invoker;
import app.commands.Command;
import app.commands.ICommand;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;

public class CreateUnitMoveCommand extends Command
{
	public void execute(Unit unit, Point position) 
	{
		Invoker invoker = Invoker.getInstance();
		
		if(invoker.playingHistory == false)
		{
			MoveType moveType = unit.getMoveType();
			Class<?> unitMoveCommandClassRef = invoker.findMoveCommandClass(moveType);
			System.out.println("> CreateUnitMoveCommand : " + position.x + "x" + position.y + "| " + moveType + " isBusy: " + unit.isBusy());
			
			invoker.executeProcessCommand(ProcessCommand.UNLOCK_MENU_MOVE_TYPE);
			
			if(unitMoveCommandClassRef != null) 
			{
				try {
					Constructor<?> ctor = unitMoveCommandClassRef.getConstructor(Unit.class, Point.class);
					ICommand command = (ICommand) ctor.newInstance(unit, position);
					invoker.executeProcessCommandWithAnotherCommand(ProcessCommand.DO_UNIT_MOVE, command);
	
				} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
