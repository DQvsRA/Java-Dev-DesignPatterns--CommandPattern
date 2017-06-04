package app.commands.unit.move;

import java.awt.Point;

import app.entities.Unit;

public class RunMoveUnitCommand extends WalkMoveUnitCommand
{
	public RunMoveUnitCommand(Unit unit, Point finalPosition) 
	{
		super(unit, finalPosition);
		_speed = 500;
	}
}
