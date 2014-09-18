package com.jock.calculator.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.jock.calculator.MainActivity;

public class SimpleMissingOpTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private MainActivity mActivity;

	private Button btn_5;
	private Button btn_plus;
	private Button btn_minus;
	private Button btn_equals;
	private Button btn_divide;
	private Button btn_clr;
	private EditText cal_et;


	@SuppressWarnings("deprecation")
	public SimpleMissingOpTest()
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
		btn_minus = (Button) mActivity.findViewById( com.jock.calculator.R.id.minus_btn );
		btn_divide = (Button) mActivity.findViewById( com.jock.calculator.R.id.divide_btn );
		btn_equals = (Button) mActivity.findViewById( com.jock.calculator.R.id.equals_btn );
		btn_clr = (Button) mActivity.findViewById( com.jock.calculator.R.id.clr_btn );
		cal_et = (EditText) mActivity.findViewById( com.jock.calculator.R.id.editText );

	}


	public void testCalUI()
	{
		// 5+
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				btn_5.performClick();
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
		assertTrue( cal_et.getText().toString().trim().equals( "5" ) );

		// +5
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
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
		assertTrue( cal_et.getText().toString().trim().equals( "5" ) );

		// 5-
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{

				mActivity.reset();
				btn_5.performClick();
				btn_minus.performClick();
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
		assertTrue( cal_et.getText().toString().trim().equals( "5" ) );

		// -5
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_minus.performClick();
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
		assertTrue( cal_et.getText().toString().trim().equals( "5" ) );

		// 5/
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_5.performClick();
				btn_divide.performClick();
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
		assertTrue( cal_et.getText().toString().trim().equals( "5" ) );

		// /5
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_divide.performClick();
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
		assertTrue( cal_et.getText().toString().trim().equals( "5" ) );

	}
}
