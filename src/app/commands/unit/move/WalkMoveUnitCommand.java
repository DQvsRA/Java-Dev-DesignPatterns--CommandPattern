package app.commands.unit.move;

import java.awt.Point;

import app.commands.unit.MoveUnitCommandBase;
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
		super.execute();
		
		float distance = (float)Math.abs(Point.distance(unit.x, unit.y, to.x, to.y));
		float time = distance / _speed;

		System.out.println("WalkMoveUnitCommand: " + time + " | " + distance);
		
		if(time < 0.2) time = 0.2f;
		Ani.to(unit, time, "y", to.y, Ani.LINEAR, "onEnd:commandEnd");
		Ani.to(unit, time, "x", to.x, Ani.LINEAR, "onStart:commandStart, onEnd:commandEnd");
	}
}
