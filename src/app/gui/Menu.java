package app.gui;

import app.enums.MoveType;
import controlP5.ControlP5;
import controlP5.DropdownList;

public final class Menu extends DropdownList 
{
	public Menu(ControlP5 controls, String name)
	{
		super(controls, name);
				
		for (MoveType moveType : MoveType.values()) this.addItem(moveType.toString(), moveType);
		
		this.setBarHeight(24);
		this.setType(DropdownList.DROPDOWN);
	}
	
	public void setSelectedMoveType(MoveType value) {
		this.setValue(value.ordinal());
		this.setCaptionLabel(value.toString());
		this.close();
	}
}
