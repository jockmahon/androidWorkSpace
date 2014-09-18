package com.jock.calculator.test;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jock.calculator.MainActivity;

public class SimpleTwoOpTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private MainActivity mActivity;

	private Button btn_5;
	private Button btn_plus;
	private Button btn_mult;
	private Button btn_equals;
	private EditText cal_et;


	@SuppressWarnings("deprecation")
	public SimpleTwoOpTest()
	{
		super( "com.jock.calculator", MainActivity.class );
	}


	@Override
	protected void setUp() throws Exception
	{
		super.setUp();

		setActivityInitialTouchMode( false );

		mActivity = getActivity();

		btn_5 = (Button) mActivity.findViewById( com.jock.calculator.R.id.five_btn );
		btn_plus = (Button) mActivity.findViewById( com.jock.calculator.R.id.plus_btn );
		btn_mult = (Button) mActivity.findViewById( com.jock.calculator.R.id.mult_btn );
		btn_equals = (Button) mActivity.findViewById( com.jock.calculator.R.id.equals_btn );
		cal_et = (EditText) mActivity.findViewById( com.jock.calculator.R.id.editText );

	}


	public void testCalUI()
	{
		// 5*5+5
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{

				btn_5.performClick();
				btn_mult.performClick();
				btn_5.performClick();
				btn_plus.performClick();
				btn_5.performClick();
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
		Log.d( "MOOSE", cal_et.getText().toString().trim() + " = 30" );
		assertTrue( cal_et.getText().toString().trim().equals( "30" ) );
	}
}
