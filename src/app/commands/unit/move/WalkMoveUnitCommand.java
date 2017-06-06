package app.commands.unit.move;

import java.awt.Point;

import app.entities.Unit;
import de.looksgood.ani.Ani;

public class WalkMoveUnitCommand extends MoveUnitCommandBase 
{
	protected int _speed = 200;

	public WalkMoveUnitCommand(Unit unit, Point finalPosition) 
	{
		super(unit, finalPosition);
	}

	public void execute() 
	{
		float distance = (float)Math.abs(Point.distance(unit.x, unit.y, position.x, position.y));
		float time = distance / _speed;

		if(time < 0.2) time = 0.2f;
		Ani.to(unit, time, "y", position.y, Ani.LINEAR, "onEnd:commandEnd");
		Ani.to(unit, time, "x", position.x, Ani.LINEAR, "onStart:commandStart, onEnd:commandEnd");


	}
}
