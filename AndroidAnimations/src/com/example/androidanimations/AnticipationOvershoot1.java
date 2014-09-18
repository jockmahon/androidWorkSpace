package com.example.androidanimations;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

public class AnticipationOvershoot1 extends Activity
{
	DecelerateInterpolator mDecel = new DecelerateInterpolator();
	OvershootInterpolator mOverShoot = new OvershootInterpolator( 10f );


	@Override
	public void onCreate( Bundle state )
	{
		super.onCreate( state );
		setContentView( R.layout.anticip_overshoot_layout );

		final Button but = (Button) findViewById( R.id.clickMe );

		but.animate().setDuration( 200 );

		but.setOnTouchListener( new OnTouchListener()
		{
			@Override
			public boolean onTouch( View v, MotionEvent event )
			{
				if( event.getAction() == MotionEvent.ACTION_DOWN )
				{
					but.animate().setInterpolator( mDecel ).scaleX( 0.7f ).scaleY( 0.7f );
				}
				else if( event.getAction() == MotionEvent.ACTION_UP )
				{
					but.animate().setInterpolator( mOverShoot ).scaleX( 1f ).scaleY( 1f );
				}
				return false;
			}
		} );

	}
}
