package com.example.androidanimations.SlidingFragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.androidanimations.R;

public class MySlidingFragments extends Activity implements OnTextFragmentAnimationEndListener, FragmentManager.OnBackStackChangedListener
{
	ImageFrag fragImage;
	TextFrag fragText;
	View viewDark;

	boolean mDidSlideOut = false;
	boolean mIsAnimating = false;


	@Override
	public void onCreate( Bundle state )
	{
		super.onCreate( state );

		setContentView( R.layout.sliding_frag_layout );

		viewDark = findViewById( R.id.dark_hover_view );
		viewDark.setAlpha( 0 );

		fragImage = (ImageFrag) getFragmentManager().findFragmentById( R.id.move_fragment );
		fragText = new TextFrag();

		getFragmentManager().addOnBackStackChangedListener( this );

		fragImage.setClickListener( mClickListener );
		fragText.setClickListener( mClickListener );
		fragText.setOnTextFragmentAnimationEnd( this );
		viewDark.setOnClickListener( mClickListener );

	}

	View.OnClickListener mClickListener = new OnClickListener()
	{
		@Override
		public void onClick( View v )
		{
			switchFragments();
		}
	};


	public void switchFragments()
	{
		if( mIsAnimating )
		{
			return;
		}

		mIsAnimating = true;

		if( mDidSlideOut )
		{
			mDidSlideOut = false;
			getFragmentManager().popBackStack();
		}
		else
		{
			mDidSlideOut = true;
			AnimatorListener listener = new AnimatorListenerAdapter()
			{
				@Override
				public void onAnimationEnd( Animator arg0 )
				{
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.setCustomAnimations( R.animator.slide_fragment_in, 0, 0, R.animator.slide_fragment_out );
					transaction.add( R.id.move_to_back_container, fragText );
					transaction.addToBackStack( null );
					transaction.commit();
				}
			};
			slideBack( listener );
		}
	}


	@Override
	public void onBackStackChanged()
	{
		if( !mDidSlideOut )
		{
			slideForward( null );
		}

	}


	public void slideBack( AnimatorListener listener )
	{
		View movingFragmentView = fragImage.getView();

		PropertyValuesHolder rotateX = PropertyValuesHolder.ofFloat( "rotationX", 40f );
		PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat( "scaleX", 0.8f );
		PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat( "scaleY", 0.8f );
		ObjectAnimator movingFragmentAnimator = ObjectAnimator.ofPropertyValuesHolder( movingFragmentView, rotateX, scaleX, scaleY );

		ObjectAnimator darkHoverViewAnimator = ObjectAnimator.ofFloat( viewDark, "alpha", 0.0f, 0.5f );

		ObjectAnimator movingFragmentRotator = ObjectAnimator.ofFloat( movingFragmentView, "rotationX", 0 );
		movingFragmentRotator.setStartDelay( getResources().getInteger( R.integer.half_slide_up_down_duration ) );

		AnimatorSet s = new AnimatorSet();
		s.playTogether( movingFragmentAnimator, darkHoverViewAnimator, movingFragmentRotator );
		s.addListener( listener );
		s.start();
	}


	public void slideForward( AnimatorListener listener )
	{
		View movingFragmentView = fragImage.getView();

		PropertyValuesHolder rotateX = PropertyValuesHolder.ofFloat( "rotationX", 40f );
		PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat( "scaleX", 1.0f );
		PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat( "scaleY", 1.0f );
		ObjectAnimator movingFragmentAnimator = ObjectAnimator.ofPropertyValuesHolder( movingFragmentView, rotateX, scaleX, scaleY );

		ObjectAnimator darkHoverViewAnimator = ObjectAnimator.ofFloat( viewDark, "alpha", 0.5f, 0.0f );

		ObjectAnimator movingFragmentRotator = ObjectAnimator.ofFloat( movingFragmentView, "rotationX", 0 );
		movingFragmentRotator.setStartDelay( getResources().getInteger( R.integer.half_slide_up_down_duration ) );

		AnimatorSet s = new AnimatorSet();
		s.playTogether( movingFragmentAnimator, movingFragmentRotator, darkHoverViewAnimator );
		s.setStartDelay( getResources().getInteger( R.integer.slide_up_down_duration ) );
		s.addListener( new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd( Animator animation )
			{
				mIsAnimating = false;
			}
		} );
		s.start();
	}


	@Override
	public void onAnimationEnd()
	{
		mIsAnimating = false;
	}

}
