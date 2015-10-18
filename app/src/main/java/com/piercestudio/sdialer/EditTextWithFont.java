package com.piercestudio.sdialer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;


public class EditTextWithFont extends EditText
{
	public EditTextWithFont(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTypeface(MainViewPagerActivity.robotoThin);
	}

	public EditTextWithFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setTypeface(MainViewPagerActivity.robotoThin);
	}

	public EditTextWithFont(Context context) {
		super(context);
		this.setTypeface(MainViewPagerActivity.robotoThin);
	}

}