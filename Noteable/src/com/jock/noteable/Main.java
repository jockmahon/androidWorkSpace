package com.jock.noteable;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Main extends Activity
{

	public static final String APP_TAG = "NOTEABLE";
	public boolean mIncludeSharps = true;
	public boolean mIncludeFlats = true;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.main );

		FragmentManager fragMan = getFragmentManager();
		FragmentTransaction tran = fragMan.beginTransaction();
		StaffDisplay staff = new StaffDisplay();
		tran.add( R.id.mainAppFrame, staff );
		tran.commit();
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}


	@Override
	public boolean onMenuItemSelected( int id, MenuItem item )
	{
		switch (item.getItemId())
		{
		case R.id.mi_include_sharp:
			item.setChecked( !item.isChecked() );
			mIncludeSharps = item.isChecked();
			return true;
		case R.id.mi_include_flat:
			item.setChecked( !item.isChecked() );
			mIncludeFlats = item.isChecked();
			return true;

		default:
			return super.onMenuItemSelected( id, item );

		}

	}


	public boolean testWithSharps()
	{
		return mIncludeSharps;

	}


	public boolean testWithFlats()
	{
		return mIncludeFlats;

	}
}
