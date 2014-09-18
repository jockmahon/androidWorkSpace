package com.jock.calculator.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jock.calculator.MainActivity;

;

public class SimpleAddTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private MainActivity mActivity;

	private Button btn_1;
	private Button btn_2;
	private Button btn_plus;
	private Button btn_equals;
	private Button btn_clr;
	private EditText cal_et;


	@SuppressWarnings("deprecation")
	public SimpleAddTest()
	{
		super( "com.jock.calculator", MainActivity.class );
	}


	@Override
	protected void setUp() throws Exception
	{
		super.setUp();

		setActivityInitialTouchMode( false );

		mActivity = getActivity();

		btn_1 = (Button) mActivity.findViewById( com.jock.calculator.R.id.one_btn );
		btn_2 = (Button) mActivity.findViewById( com.jock.calculator.R.id.two_btn );
		btn_plus = (Button) mActivity.findViewById( com.jock.calculator.R.id.plus_btn );
		btn_equals = (Button) mActivity.findViewById( com.jock.calculator.R.id.equals_btn );
		btn_clr = (Button) mActivity.findViewById( com.jock.calculator.R.id.clr_btn );
		cal_et = (EditText) mActivity.findViewById( com.jock.calculator.R.id.editText );

	}


	public void testCalUI()
	{

		// 2
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_2.performClick();
				btn_equals.performClick();

			}
		} );

		try
		{
			Thread.sleep( 1000 );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.d( "MOOSE", cal_et.getText().toString().trim() + " = 2" );
		assertTrue( cal_et.getText().toString().trim().equals( "2" ) );

		// +
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_equals.performClick();
			}
		} );

		try
		{
			Thread.sleep( 1000 );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.d( "MOOSE", cal_et.getText().toString().trim() + " " );
		assertTrue( cal_et.getText().toString().trim().equals( "" ) );

		// +2
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				btn_plus.performClick();
				btn_2.performClick();
				btn_equals.performClick();

			}
		} );

		try
		{
			Thread.sleep( 1000 );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.d( "MOOSE", cal_et.getText().toString().trim() + " = 2" );
		assertTrue( cal_et.getText().toString().trim().equals( "2" ) );

		// 2+
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_2.performClick();
				btn_plus.performClick();
				btn_equals.performClick();

			}
		} );

		try
		{
			Thread.sleep( 1000 );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.d( "MOOSE", cal_et.getText().toString().trim() + " = 2" );
		assertTrue( cal_et.getText().toString().trim().equals( "2" ) );

		// 1+2
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_1.performClick();
				btn_plus.performClick();
				btn_2.performClick();
				btn_equals.performClick();

			}
		} );

		try
		{
			Thread.sleep( 1000 );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.d( "MOOSE", cal_et.getText().toString().trim() + " = 3" );
		assertTrue( cal_et.getText().toString().trim().equals( "3" ) );

		// 2+1+2
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_2.performClick();
				btn_plus.performClick();
				btn_1.performClick();
				btn_plus.performClick();
				btn_2.performClick();
				btn_equals.performClick();

			}
		} );

		try
		{
			Thread.sleep( 1000 );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		Log.d( "MOOSE", cal_et.getText().toString().trim() + " = 5" );
		assertTrue( cal_et.getText().toString().trim().equals( "5" ) );
	}
}
