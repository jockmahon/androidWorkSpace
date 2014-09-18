package com.example.androidanimations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

public class AnticiButton extends Button implements OnTouchListener
{
	private static final LinearInterpolator mLinearInter = new LinearInterpolator();
	private static final DecelerateInterpolator mDecelInter = new DecelerateInterpolator( 8 );
	private static final AccelerateInterpolator mAccInter = new AccelerateInterpolator();
	private static final OvershootInterpolator mOverInter = new OvershootInterpolator();
	private static final DecelerateInterpolator mQuickDecl = new DecelerateInterpolator();

	private float mSkewX = 0;
	ObjectAnimator downAnim = null;
	boolean mOnLeft = true;
	RectF mTempRect = new RectF();


	public AnticiButton(Context c)
	{
		super( c );
		init();

	}


	public AnticiButton(Context context, AttributeSet attrs, int defStyle)
	{
		super( context, attrs, defStyle );
		init();
	}


	public AnticiButton(Context context, AttributeSet attrs)
	{
		super( context, attrs );
		init();
	}


	@Override
	public void draw( Canvas c )
	{
		if( mSkewX != 0 )
		{
			c.translate( 0, getHeight() );
			c.skew( mSkewX, 0 );
			c.translate( 0, -getHeight() );
		}

		super.draw( c );
	}


	private void init()
	{
		setOnTouchListener( this );

		setOnClickListener( new OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				runClickAnim();
			}
		} );
	}


	private void runClickAnim()
	{
		ObjectAnimator finishDownAnim = null;
		if( downAnim != null && downAnim.isRunning() )
		{
			downAnim.cancel();
			finishDownAnim = ObjectAnimator.ofFloat( this, "skewX", mOnLeft ? .5f : -.5f );
			finishDownAnim.setDuration( 150 );
			finishDownAnim.setInterpolator( mQuickDecl );
		}

		ObjectAnimator moveAnim = ObjectAnimator.ofFloat( this, View.TRANSLATION_X, mOnLeft ? 400 : 0 );
		moveAnim.setInterpolator( mLinearInter );
		moveAnim.setDuration( 150 );

		ObjectAnimator skewAnim = ObjectAnimator.ofFloat( this, "skewX", mOnLeft ? -.5f : .5f );
		skewAnim.setInterpolator( mQuickDecl );
		skewAnim.setDuration( 100 );

		ObjectAnimator wobbleAnim = ObjectAnimator.ofFloat( this, "skewX", 0 );
		wobbleAnim.setInterpolator( mOverInter );
		wobbleAnim.setDuration( 150 );
		AnimatorSet set = new AnimatorSet();
		set.playSequentially( moveAnim, skewAnim, wobbleAnim );
		if( finishDownAnim != null )
		{
			set.play( finishDownAnim ).before( moveAnim );
		}
		set.start();
		mOnLeft = !mOnLeft;
	}


	private void runCancelAnim()
	{
		if( downAnim != null && downAnim.isRunning() )
		{
			downAnim.cancel();
			ObjectAnimator reverser = ObjectAnimator.ofFloat( this, "skewX", 0 );
			reverser.setDuration( 200 );
			reverser.setInterpolator( mAccInter );
			reverser.start();
			downAnim = null;
		}
	}


	private void runPressAnim()
	{
		downAnim = ObjectAnimator.ofFloat( this, "skewX", mOnLeft ? .5f : -.5f );
		downAnim.setDuration( 2500 );
		downAnim.setInterpolator( mDecelInter );
		downAnim.start();
	}


	@Override
	public boolean onTouch( View v, MotionEvent e )
	{
		switch (e.getAction())
		{
		case MotionEvent.ACTION_UP:
			if( isPressed() )
			{
				performClick();
				setPressed( false );
				break;
			}
		case MotionEvent.ACTION_CANCEL:
			runCancelAnim();
			break;
		case MotionEvent.ACTION_MOVE:
			float x = e.getX();
			float y = e.getY();
			boolean isInside = ( x > 0 && x < getWidth() && y > 0 && y < getHeight() );
			if( isPressed() != isInside )
			{
				setPressed( isInside );
			}
			break;
		case MotionEvent.ACTION_DOWN:
			setPressed( true );
			runPressAnim();
		}

		return true;
	}


	public void setSkewX( float value )
	{
		if( value != mSkewX )
		{
			mSkewX = value;
			invalidate(); // force button to redraw with new skew value
			invalidateSkewedBounds(); // also invalidate appropriate area of
										// parent
		}
	}


	private void invalidateSkewedBounds()
	{
		if( mSkewX != 0 )
		{
			Matrix matrix = new Matrix();
			matrix.setSkew( -mSkewX, 0 );
			mTempRect.set( 0, 0, getRight(), getBottom() );
			matrix.mapRect( mTempRect );
			mTempRect.offset( getLeft() + getTranslationX(), getTop() + getTranslationY() );
			( (View) getParent() ).invalidate( (int) mTempRect.left, (int) mTempRect.top, (int) ( mTempRect.right + .5f ),
					(int) ( mTempRect.bottom + .5f ) );
		}
	}
}
