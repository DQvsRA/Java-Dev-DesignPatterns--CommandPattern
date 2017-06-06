package app.commands;

import java.awt.Point;

import app.entities.Unit;

public interface ICommand 
{
	void execute();
	void execute(Unit unit);
	void execute(Unit unit, Point position);
	void execute(ICommand commandToExecute);
}
