package com.jock.wordup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ResultItemAdapter extends ArrayAdapter<HashMap<String, String>>
{
	private List<HashMap<String, String>> myData = new ArrayList<HashMap<String, String>>();
	private Context mcontext;


	public ResultItemAdapter(Context context, int resource, List<HashMap<String, String>> objects)
	{
		super( context, resource, resource, objects );

		mcontext = context;
		myData = objects;
	}


	@Override
	public View getView( int position, View convertView, ViewGroup parent )
	{
		View v = convertView;

		if( v == null )
		{
			LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService( mcontext.LAYOUT_INFLATER_SERVICE );
			v = inflater.inflate( R.layout.layout_result_item, null );
		}

		HashMap<String, String> hasValues = myData.get( position );

		if( hasValues != null )
		{
			String correctWord = hasValues.keySet().toString();
			String attemptWord = hasValues.values().toString();

			TextView tv_correct_word = (TextView) v.findViewById( R.id.tv_result_test_word );
			tv_correct_word.setText( correctWord.substring( 1, correctWord.length() - 1 ) );
			tv_correct_word.setTextAlignment( View.TEXT_ALIGNMENT_VIEW_END );

			TextView tv_attempt_word = (TextView) v.findViewById( R.id.tv_result_attempt_word );
			tv_attempt_word.setText( attemptWord.substring( 1, attemptWord.length() - 1 ) );

			
			Log.d( Main.APP_TAG, ":" + String.valueOf( correctWord ) + ":" );
			Log.d( Main.APP_TAG, ":" + String.valueOf( attemptWord )+ ":" );
			
			if( !correctWord.equals( attemptWord ) )
			{
				tv_attempt_word.setTextColor( Color.RED );
			}
		}
		return v;
	}
}
