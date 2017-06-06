package app.commands.processing.menu;

import app.commands.Command;
import controlP5.DropdownList;

public class UnLockMenuMoveTypeCommand extends Command 
{
	private DropdownList moveTypesList;
	
	public UnLockMenuMoveTypeCommand(DropdownList moveTypesList)
	{
		this.moveTypesList = moveTypesList;
	}
	
	public void execute() 
	{
		if(moveTypesList.isLock()) {
			System.out.println("UnLockMenuMoveTypeCommand");
			moveTypesList.unlock();
		}
	}
}
