package com.example.androidanimations;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class BitmapScaling extends Activity
{

	// how to load in a bitmap from resources and then get smaller versions
	// so that you are not using up more memory than needed , can be used to
	// load
	// smaller version of images into grids etc

	CheckBox matchSize;
	ImageView originalView;
	ImageView halfView;
	ImageView quaterView;


	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.bitmap_scaling_layout );

		matchSize = (CheckBox) findViewById( R.id.matchSize );
		originalView = (ImageView) findViewById( R.id.originalImage );
		halfView = (ImageView) findViewById( R.id.originalImageHalf );
		quaterView = (ImageView) findViewById( R.id.originalImageQuater );

		Bitmap bitmap = BitmapFactory.decodeResource( getResources(), R.drawable.android );
		originalView.setImageBitmap( bitmap );

		Bitmap bitmapHalf = BitmapFactory.decodeResource( getResources(), R.drawable.android, getFactory( 2 ) );
		halfView.setImageBitmap( bitmapHalf );

		Bitmap bitmapQuater = BitmapFactory.decodeResource( getResources(), R.drawable.android, getFactory( 4 ) );
		quaterView.setImageBitmap( bitmapQuater );

	}


	public void onMatchSize( View v )
	{
		LayoutParams layout;
		if( matchSize.isChecked() )
		{
			layout = new LayoutParams( 200, 200 );
		}
		else
		{
			layout = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		}
		originalView.setLayoutParams( layout );
		halfView.setLayoutParams( layout );
		quaterView.setLayoutParams( layout );
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.bitmap_scaling, menu );
		return true;
	}


	private BitmapFactory.Options getFactory( int size )
	{
		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inSampleSize = size;

		return options;

	}
}
