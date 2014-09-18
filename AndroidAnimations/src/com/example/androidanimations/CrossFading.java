package com.example.androidanimations;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CrossFading extends Activity
{
	int mCurrentDrawable = 0;


	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.cross_fade_layout );

		final ImageView imageview = (ImageView) findViewById( R.id.imageView );

		Bitmap bitmap0 = Bitmap.createBitmap( 500, 500, Bitmap.Config.ARGB_8888 );
		Bitmap bitmap1 = Bitmap.createBitmap( 500, 500, Bitmap.Config.ARGB_8888 );
		Canvas canvas = new Canvas( bitmap0 );
		canvas.drawColor( Color.RED );
		canvas = new Canvas( bitmap1 );
		canvas.drawColor( Color.GREEN );

		BitmapDrawable drawables[] = new BitmapDrawable[2];
		drawables[0] = new BitmapDrawable( getResources(), bitmap0 );
		drawables[1] = new BitmapDrawable( getResources(), bitmap1 );

		final TransitionDrawable crossFade = new TransitionDrawable( drawables );

		imageview.setImageDrawable( crossFade );

		imageview.setOnClickListener( new View.OnClickListener()
		{

			@Override
			public void onClick( View v )
			{
				if( mCurrentDrawable == 0 )
				{
					crossFade.startTransition( 500 );
					mCurrentDrawable = 1;
				}
				else
				{
					crossFade.reverseTransition( 500 );
					mCurrentDrawable = 0;
				}
			}
		} );
	}
}
