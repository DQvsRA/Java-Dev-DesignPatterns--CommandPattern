package app.commands.unit;

import java.awt.Point;

import app.Invoker;
import app.commands.Command;
import app.entities.Unit;
import app.enums.MoveType;
import app.enums.ProcessCommand;

public abstract class MoveUnitCommandBase extends Command
{
	protected Unit unit;
	protected Point to;
	protected MoveType moveType;
		
	public MoveUnitCommandBase(Unit unit, Point to)
	{
		this.unit = unit;
		this.to = to;
		moveType = unit.getMoveType();
	}	
	
	public MoveType getMoveType() {
		return moveType;
	}
	
	public Point getToPosition() {
		return to;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	@Override
	public void execute() 
	{
		if(unit.getMoveType() != moveType) {
			Invoker.getInstance().executeProcessCommand(ProcessCommand.CHANGE_MENU_MOVE_TYPE, moveType);
		}
	}
}
