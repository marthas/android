package com.CulturaEnLasCalles;


import android.content.Context;
import android.graphics.drawable.Drawable;

public class ButtonWithID extends android.widget.Button {

	private String resultID;

	private String internalName;
	
	
	public ButtonWithID(Context context) {
		super(context);
		
	}

	public String getresultID() {
		return resultID;
	}

	public void setresultID(String resultID) {
		this.resultID = resultID;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public void setOnClickListener(OnClickListener onClickListener) {
		// TODO Auto-generated method stub
		super.setOnClickListener(onClickListener);
	}

	
	
	

}
