package app.commands.unit;

import java.awt.Point;

import app.commands.Command;
import app.entities.Unit;

public class MoveUnitCommand extends Command
{
	protected Unit unit;
	protected Point position;
		
	public MoveUnitCommand(Unit unit, Point position)
	{
		this.unit = unit;
		this.position = position;
	}
}
