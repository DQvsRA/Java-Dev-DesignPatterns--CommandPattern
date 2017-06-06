package app.commands.processing.menu;

import app.commands.Command;
import controlP5.DropdownList;

public class LockMenuMoveTypeCommand extends Command 
{
	private DropdownList moveTypesList;
	
	public LockMenuMoveTypeCommand(DropdownList moveTypesList)
	{
		this.moveTypesList = moveTypesList;
	}
	
	public void execute() 
	{
		if(moveTypesList.isLock() == false) {
			System.out.println("LockMenuMoveTypeCommand");
			if(moveTypesList.isOpen())
				moveTypesList.close();
			moveTypesList.lock();
		}
	}
}
