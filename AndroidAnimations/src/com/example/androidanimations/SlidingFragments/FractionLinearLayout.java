/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidanimations.SlidingFragments;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class FractionLinearLayout extends LinearLayout
{

	private float mYFraction;
	private int mScreenHeight;


	public FractionLinearLayout(Context context)
	{
		super( context );
	}


	public FractionLinearLayout(Context context, AttributeSet attrs)
	{
		super( context, attrs );
	}


	@Override
	protected void onSizeChanged( int w, int h, int oldw, int oldh )
	{
		super.onSizeChanged( w, h, oldw, oldh );
		mScreenHeight = h;
		setY( mScreenHeight );
	}


	public float getYFraction()
	{
		return mYFraction;
	}


	public void setYFraction( float yFraction )
	{
		mYFraction = yFraction;
		setY( ( mScreenHeight > 0 ) ? ( mScreenHeight - yFraction * mScreenHeight ) : 0 );
	}
}
