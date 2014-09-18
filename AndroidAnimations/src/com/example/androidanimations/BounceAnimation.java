package com.example.androidanimations;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class BounceAnimation extends Activity
{

	@Override
	public void onCreate( Bundle state )
	{
		super.onCreate( state );
		setContentView( R.layout.bounce_activity_layout );

	}

	static class BounceMe extends View
	{
		Bitmap mDroid;
		Paint paint = new Paint();
		int mShapeW, mShapeH, mShapeX, mShapeY;


		public BounceMe(Context c)
		{
			super( c );
			setUpShape();
		}


		public BounceMe(Context context, AttributeSet attrs)
		{
			super( context, attrs );
			setUpShape();
		}


		public BounceMe(Context context, AttributeSet attrs, int defStyle)
		{
			super( context, attrs, defStyle );
			setUpShape();
		}


		@Override
		protected void onSizeChanged( int w, int h, int oldw, int oldh )
		{
			mShapeX = ( w - mDroid.getWidth() ) / 2;
			mShapeY = 0;
		}


		@Override
		protected void onDraw( Canvas canvas )
		{
			canvas.drawBitmap( mDroid, mShapeX, mShapeY, paint );
		}


		private void setUpShape()
		{
			mDroid = BitmapFactory.decodeResource( getResources(), R.drawable.android_logo );
			mShapeW = mDroid.getWidth();
			mShapeH = mDroid.getHeight();

			setOnClickListener( new OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					startAnim();
				}
			} );
		}


		public void setShapeX( int shapeX )
		{
			int minX = mShapeX;
			int maxX = mShapeX + mShapeW;
			mShapeX = shapeX;
			minX = Math.min( mShapeX, minX );
			maxX = Math.max( mShapeX + mShapeW, maxX );
			invalidate( minX, mShapeY, maxX, mShapeY + mShapeH );
		}


		public void setShapeY( int shapeY )
		{
			int minY = mShapeY;
			int maxY = mShapeY + mShapeH;
			mShapeY = shapeY;
			minY = Math.min( mShapeY, minY );
			maxY = Math.max( mShapeY + mShapeH, maxY );
			invalidate( mShapeX, minY, mShapeX + mShapeW, maxY );
		}


		private void startAnim()
		{
			ObjectAnimator objAnim = ObjectAnimator.ofInt( this, "shapeY", 0, ( getHeight() - mShapeY ) );

			objAnim.setRepeatCount( ObjectAnimator.INFINITE );
			objAnim.setRepeatMode( ObjectAnimator.REVERSE );
			objAnim.setInterpolator( new AccelerateInterpolator() );

			objAnim.start();
		}
	}

}
