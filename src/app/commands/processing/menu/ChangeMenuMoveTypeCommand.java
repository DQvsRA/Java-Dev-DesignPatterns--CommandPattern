package app.commands.processing.menu;

import app.commands.Command;
import app.enums.MoveType;
import controlP5.DropdownList;

public class ChangeMenuMoveTypeCommand extends Command 
{
	private DropdownList moveTypesList;
	
	public ChangeMenuMoveTypeCommand(DropdownList moveTypesList)
	{
		this.moveTypesList = moveTypesList;
	}
	
	public void execute(MoveType moveType) 
	{
		moveTypesList.setValue(moveType.ordinal());
		moveTypesList.setCaptionLabel(moveType.toString());
		moveTypesList.close();
	}
}
