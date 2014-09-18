package com.jock.calculator.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jock.calculator.MainActivity;

public class SimpleMultTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private MainActivity mActivity;

	private Button btn_1;
	private Button btn_2;
	private Button btn_mult;
	private Button btn_equals;
	private Button btn_clr;

	private EditText cal_et;


	@SuppressWarnings("deprecation")
	public SimpleMultTest()
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
		btn_mult = (Button) mActivity.findViewById( com.jock.calculator.R.id.mult_btn );
		btn_equals = (Button) mActivity.findViewById( com.jock.calculator.R.id.equals_btn );
		btn_clr = (Button) mActivity.findViewById( com.jock.calculator.R.id.clr_btn );
		cal_et = (EditText) mActivity.findViewById( com.jock.calculator.R.id.editText );

	}


	public void testCalUI()
	{
		// 12*2
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{

				btn_1.performClick();
				btn_2.performClick();
				btn_mult.performClick();
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

		Log.d( "MOOSE", cal_et.getText().toString().trim() + " = 24" );
		assertTrue( cal_et.getText().toString().trim().equals( "24" ) );

		// 2*2*2
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_2.performClick();
				btn_mult.performClick();
				btn_2.performClick();
				btn_mult.performClick();
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
		Log.d( "MOOSE", cal_et.getText().toString().trim() + " = 8" );
		assertTrue( cal_et.getText().toString().trim().equals( "8" ) );
	}
}
