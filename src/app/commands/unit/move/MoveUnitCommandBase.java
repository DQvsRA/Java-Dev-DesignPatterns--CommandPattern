package app.commands.unit.move;

import java.awt.Point;

import app.commands.Command;
import app.entities.Unit;

public abstract class MoveUnitCommandBase extends Command
{
	protected Unit unit;
	protected Point position;
		
	public MoveUnitCommandBase(Unit unit, Point position)
	{
		this.unit = unit;
		this.position = position;
	}	
	
	public Point getPosition() {
		return position;
	}
	
	public Unit getUnit() {
		return unit;
	}
}
