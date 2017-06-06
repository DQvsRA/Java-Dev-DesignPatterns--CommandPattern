package app.commands;

import java.awt.Point;

import app.entities.Unit;

public abstract class Command implements ICommand {

	@Override
	public void execute() { }

	@Override
	public void execute(Unit unit, Point position) { }
	
	@Override
	public void execute(Unit unit) { }
	
	@Override
	public void execute(ICommand commandToExecute) { }

}
