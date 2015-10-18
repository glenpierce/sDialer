package com.piercestudio.sdialer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextViewWithFont extends TextView
{
	public TextViewWithFont(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTypeface(MainViewPagerActivity.robotoThin);
	}

	public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setTypeface(MainViewPagerActivity.robotoThin);
	}

	public TextViewWithFont(Context context) {
		super(context);
		this.setTypeface(MainViewPagerActivity.robotoThin);
	}

}