package com.CulturaEnLasCalles;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class EventImage extends android.widget.ImageView {
	private String title;
	private String description;
	private long milliseconds;
	
	public EventImage(Context context) {
		super(context);
	}
	public EventImage(Context context,ImageView I)
	{		
		super(context);
		super.setImageDrawable(I.getDrawable());
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(long milliseconds) {
		this.milliseconds = milliseconds;
	}
	
	public void setImage(Drawable image)
	{
		super.setImageDrawable(image);
	}
		
	
}
