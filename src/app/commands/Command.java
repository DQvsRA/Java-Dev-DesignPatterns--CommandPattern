package app.commands;

import java.awt.Point;

import app.entities.Unit;
import app.enums.MoveType;

public abstract class Command implements ICommand 
{
	@Override
	public void execute() { }

	@Override
	public void execute(Unit unit, Point to) { }
	
	@Override
	public void execute(Unit unit) { }
	
	@Override
	public void execute(ICommand commandToExecute) { }
	
	@Override
	public void execute(MoveType moveType) { }

}
