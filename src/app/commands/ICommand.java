package app.commands;

import java.awt.Point;

import app.entities.Unit;
import app.enums.MoveType;

public interface ICommand 
{
	void execute();
	void execute(Unit unit);
	void execute(Unit unit, Point to);
	void execute(ICommand commandToExecute);	
	void execute(MoveType moveType);
}
