package app.commands.unit.move;

import java.awt.Point;

import app.commands.unit.MoveUnitCommandBase;
import app.entities.Unit;
import de.looksgood.ani.Ani;

public class JumpMoveUnitCommand extends MoveUnitCommandBase 
{
	public JumpMoveUnitCommand(Unit unit, Point position) 
	{
		super(unit, position);
	}
	
	@Override
	public void execute() 
	{
		super.execute();
		
		unit.isBusy(true);		
		Ani.to(unit, 1f, "radius", 0f, Ani.ELASTIC_IN, this, "onEnd:setFinalPosition");
	}
	
	public void setFinalPosition() {
		unit.x = to.x;
		unit.y = to.y;
		Ani.to(unit, 1f, "radius", Unit.SIZE, Ani.ELASTIC_OUT, this, "onEnd:finilize");
	}
	
	public void finilize() {
		unit.isBusy(false);
	}
}
