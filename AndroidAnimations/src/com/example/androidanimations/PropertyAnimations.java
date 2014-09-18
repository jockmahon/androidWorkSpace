package com.example.androidanimations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PropertyAnimations extends Activity
{

	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.property_animations_layout );

		final Button alphaButton = (Button) findViewById( R.id.alphaButton );
		final Button translateButton = (Button) findViewById( R.id.translateButton );
		final Button rotateButton = (Button) findViewById( R.id.rotateButton );
		final Button scaleButton = (Button) findViewById( R.id.scaleButton );
		final Button setButton = (Button) findViewById( R.id.setButton );

		ObjectAnimator alpha = ObjectAnimator.ofFloat( alphaButton, View.ALPHA, 0 );
		alpha.setRepeatCount( 1 );
		alpha.setRepeatMode( ValueAnimator.REVERSE );

		ObjectAnimator translate = ObjectAnimator.ofFloat( translateButton, View.TRANSLATION_X, 500 );
		translate.setRepeatCount( 1 );
		translate.setRepeatMode( ValueAnimator.REVERSE );

		ObjectAnimator rotate = ObjectAnimator.ofFloat( rotateButton, View.ROTATION, 180 );
		rotate.setRepeatCount( 1 );
		rotate.setRepeatMode( ValueAnimator.REVERSE );

		PropertyValuesHolder pX = PropertyValuesHolder.ofFloat( View.SCALE_X, 2 );
		PropertyValuesHolder pY = PropertyValuesHolder.ofFloat( View.SCALE_Y, 2 );
		ObjectAnimator scale = ObjectAnimator.ofPropertyValuesHolder( scaleButton, pX, pY );
		scale.setRepeatCount( 1 );
		scale.setRepeatMode( ValueAnimator.REVERSE );

		AnimatorSet set = new AnimatorSet();
		set.play( alpha ).after( translate ).after( rotate ).after( scale );

		setUpAnim( alphaButton, alpha );
		setUpAnim( translateButton, translate );
		setUpAnim( rotateButton, rotate );
		setUpAnim( scaleButton, scale );
		setUpAnim( setButton, set );
	}


	private void setUpAnim( View button, final Animator anim )
	{
		button.setOnClickListener( new OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				anim.start();
			}
		} );

	}

}
