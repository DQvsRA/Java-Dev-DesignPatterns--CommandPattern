package app.commands.unit.move;

import java.awt.Point;

import app.commands.unit.MoveUnitCommand;
import app.entities.Unit;
import de.looksgood.ani.Ani;

public class JumpMoveUnitCommand extends MoveUnitCommand 
{
	public JumpMoveUnitCommand(Unit unit, Point position) 
	{
		super(unit, position);
	}
	
	@Override
	public void execute() 
	{
		unit.isBusy(true);		
		Ani.to(unit, 1f, "radius", 0f, Ani.ELASTIC_IN, this, "onEnd:setFinalPosition");
	}
	
	public void setFinalPosition() {
		unit.x = position.x;
		unit.y = position.y;
		Ani.to(unit, 1f, "radius", Unit.SIZE, Ani.ELASTIC_OUT, this, "onEnd:finilize");
	}
	
	public void finilize() {
		unit.isBusy(false);
	}
}
