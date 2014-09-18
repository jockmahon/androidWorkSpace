package com.jock.calculator.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.jock.calculator.MainActivity;

public class MultiBracketTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private MainActivity mActivity;

	private Button btn_2;
	private Button btn_3;
	private Button btn_5;
	private Button btn_plus;
	private Button btn_equals;
	private Button btn_leftB;
	private Button btn_rightB;
	private Button btn_clr;
	private EditText cal_et;


	@SuppressWarnings("deprecation")
	public MultiBracketTest()
	{
		super( "com.jock.calculator", MainActivity.class );
	}


	@Override
	protected void setUp() throws Exception
	{
		super.setUp();

		setActivityInitialTouchMode( false );

		mActivity = getActivity();

		btn_2 = (Button) mActivity.findViewById( com.jock.calculator.R.id.two_btn );
		btn_3 = (Button) mActivity.findViewById( com.jock.calculator.R.id.three_btn );
		btn_5 = (Button) mActivity.findViewById( com.jock.calculator.R.id.five_btn );
		btn_plus = (Button) mActivity.findViewById( com.jock.calculator.R.id.plus_btn );
		btn_equals = (Button) mActivity.findViewById( com.jock.calculator.R.id.equals_btn );
		btn_leftB = (Button) mActivity.findViewById( com.jock.calculator.R.id.leftBracket_btn );
		btn_rightB = (Button) mActivity.findViewById( com.jock.calculator.R.id.rightBracket_btn );
		btn_clr = (Button) mActivity.findViewById( com.jock.calculator.R.id.clr_btn );
		cal_et = (EditText) mActivity.findViewById( com.jock.calculator.R.id.editText );

	}


	public void testCalUI()
	{
		// (5+3)+(2+3)
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				btn_leftB.performClick();
				btn_5.performClick();
				btn_plus.performClick();
				btn_3.performClick();
				btn_rightB.performClick();
				btn_plus.performClick();
				btn_leftB.performClick();
				btn_2.performClick();
				btn_plus.performClick();
				btn_3.performClick();
				btn_rightB.performClick();

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
		assertTrue( cal_et.getText().toString().trim().equals( "13" ) );

		// (5+(3+2))
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				btn_clr.performLongClick();
				btn_leftB.performClick();
				btn_5.performClick();
				btn_plus.performClick();
				btn_leftB.performClick();
				btn_3.performClick();
				btn_plus.performClick();
				btn_2.performClick();
				btn_rightB.performClick();
				btn_rightB.performClick();

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
		assertTrue( cal_et.getText().toString().trim().equals( "10" ) );

		// (5+(3+2)+2)
		mActivity.runOnUiThread( new Runnable()
		{
			@Override
			public void run()
			{
				mActivity.reset();
				btn_leftB.performClick();
				btn_5.performClick();
				btn_plus.performClick();
				btn_leftB.performClick();
				btn_3.performClick();
				btn_plus.performClick();
				btn_2.performClick();
				btn_rightB.performClick();
				btn_plus.performClick();
				btn_2.performClick();
				btn_rightB.performClick();

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
		assertTrue( cal_et.getText().toString().trim().equals( "12" ) );

	}
}
