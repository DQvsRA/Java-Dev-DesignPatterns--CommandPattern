package app.commands.processing;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import app.commands.Command;
import app.commands.ICommand;
import app.entities.Unit;
import app.enums.MoveType;

public class CreateUnitMoveCommand extends Command
{
	private Map<MoveType, Class<?>> unitMoveCommands;
	private List<ICommand> commandsList;
	private List<Point> tail;

	public CreateUnitMoveCommand(Map<MoveType, Class<?>> unitMoveCommands, List<ICommand> commandsList, List<Point> tail) 
	{
		this.unitMoveCommands = unitMoveCommands;
		this.commandsList = commandsList;
		this.tail = tail;
	}

	@Override
	public void execute(Unit unit, Point position) 
	{
		Class<?> unitMoveCommandClassRef = unitMoveCommands.get(unit.getMoveType());
		System.out.println("> mouseClicked : " + position.x + "x" + position.y + "| " + unit.getMoveType() + " isBusy: " + unit.isBusy());
		
		if(unitMoveCommandClassRef != null) 
		{
			try {
				Constructor<?> ctor = unitMoveCommandClassRef.getConstructor(Unit.class, Point.class);
				ICommand command = (ICommand) ctor.newInstance(unit, position);
				if(unit.isBusy()) {
					commandsList.add(command);
					tail.add(position);
					System.out.println("Add to stack " + commandsList.size());
				} else {
					tail.add(unit.getPosition());
					command.execute();
					tail.add(position);
				}
			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
