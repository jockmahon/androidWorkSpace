package com.example.androidanimations;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class LayoutTrans extends Activity
{
	@Override
	public void onCreate( Bundle savedInstanceState )
	{

		super.onCreate( savedInstanceState );
		setContentView( R.layout.layour_trans_layout );

		final Button addButton = (Button) findViewById( R.id.addButton );
		final Button removeButton = (Button) findViewById( R.id.button );
		final LinearLayout container = (LinearLayout) findViewById( R.id.container );
		final Context context = this;

		container.addView( new ColourView( this ) );
		container.addView( new ColourView( this ) );

		addButton.setOnClickListener( new OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				container.addView( new ColourView( context ), 1 );
			}
		} );
		removeButton.setOnClickListener( new OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				container.removeViewAt( Math.min( 1, container.getChildCount() - 1 ) );
			}
		} );

		// animate layout changes to the container
		container.setLayoutTransition( new LayoutTransition() );

		LayoutTransition t = container.getLayoutTransition();

		// animate any change of the container
		t.enableTransitionType( LayoutTransition.CHANGING );

	}

	private static class ColourView extends View
	{
		private Boolean isExpanded = false;
		private LayoutParams mCompressedLayout = new LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 50 );
		private LayoutParams mExpandedLayout = new LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 200 );


		public ColourView(Context c)
		{
			super( c );
			int red = (int) ( Math.random() * 128 + 127 );
			int green = (int) ( Math.random() * 128 + 127 );
			int blue = (int) ( Math.random() * 128 + 127 );
			int color = 0xff << 24 | ( red << 16 ) | ( green << 8 ) | blue;
			setBackgroundColor( color );

			setLayoutParams( mCompressedLayout );
			setOnClickListener( new OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					Log.d( "MOOSE", String.valueOf( isExpanded ) );
					Log.d( "MOOSE", String.valueOf( isExpanded ) );
					setLayoutParams( isExpanded ? mCompressedLayout : mExpandedLayout );
					isExpanded = !isExpanded;
					requestLayout();
				}
			} );
		}

	}

}
