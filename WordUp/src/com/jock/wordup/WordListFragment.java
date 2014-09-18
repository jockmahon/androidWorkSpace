package com.jock.wordup;

import java.util.Random;

import android.app.Fragment;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class WordListFragment extends Fragment implements OnItemClickListener
{

	private static final int MI_CLEAR_COUNTS = 101;
	private static final int MI_START_TEST   = 102;

	public static final String FRAG_TAG = "WORD_LIST";

	private TextView tv_wordOfTheDay;
	private TextView tv_wordOfTheDayDef;
	private ListView lv_words;
	private SimpleCursorAdapter mWordAdapter;

	public static int cnt = 0;;


	@Override
	public View onCreateView( LayoutInflater li, ViewGroup container, Bundle state )
	{
		setHasOptionsMenu( true );

		View view = li.inflate( R.layout.layout_word_list, container, false );

		tv_wordOfTheDay = (TextView) view.findViewById( R.id.tv_wordOfTheDay );
		tv_wordOfTheDayDef = (TextView) view.findViewById( R.id.tv_wordOfTheDayDef );
		lv_words = (ListView) view.findViewById( R.id.lv_wordsListView );

		lv_words.setOnItemClickListener( this );

		String[] fromColumns = { WordUpSQLiteOpenHelper.COLUMN_WORD, WordUpSQLiteOpenHelper.COLUMN_WORD_DEF,
				WordUpSQLiteOpenHelper.COLUMN_WORD_CORRECTLY_SPLET_CNT, WordUpSQLiteOpenHelper.COLUMN_WORD_INCORRECTLY_SPLET_CNT };
		int[] toViews = { R.id.tv_list_word, R.id.tv_list_def, R.id.tv_list_word_correct_cnt, R.id.tv_list_word_incorrect_cnt };

		mWordAdapter = new SimpleCursorAdapter( view.getContext(), R.layout.layout_list_item_card, null, fromColumns, toViews, 0 );
		lv_words.setAdapter( mWordAdapter );

		cnt++;

		return view;
	}


	@Override
	public void onResume()
	{
		super.onResume();

		( (Main) getActivity() ).startLoader();
		( (Main) getActivity() ).addSpinnerToActioBar();
	}


	public void swapCursor( Cursor c )
	{
		if( c != null )
		{
			mWordAdapter.swapCursor( c );

			if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT )
			{

				int wordCount = c.getCount();

				if( wordCount > 0 )
				{
					Random rand = new Random();
					int nextWordIndex = rand.nextInt( wordCount );

					c.moveToPosition( nextWordIndex );

					tv_wordOfTheDay.setText( c.getString( 1 ) );
					tv_wordOfTheDayDef.setText( c.getString( 2 ) );
				}
			}
		}
	}


	@Override
	public void onItemClick( AdapterView<?> parent, View v, int position, long id )
	{
		Cursor selectedRow = (Cursor) parent.getAdapter().getItem( position );
		String selectedWord = selectedRow.getString( selectedRow.getColumnIndex( WordUpSQLiteOpenHelper.COLUMN_WORD ) );
		( (Main) getActivity() ).speakWord( selectedWord );
	}


	@Override
	public void onCreateOptionsMenu( Menu menu, MenuInflater inflater )
	{
		menu.add( Menu.NONE, MI_CLEAR_COUNTS, Menu.NONE, R.string.clear_counts ).setShowAsAction( MenuItem.SHOW_AS_ACTION_NEVER );
		menu.add( Menu.NONE, MI_START_TEST, Menu.NONE, R.string.start_test ).setShowAsAction( MenuItem.SHOW_AS_ACTION_IF_ROOM );
	}


	@Override
	public boolean onOptionsItemSelected( MenuItem mi )
	{

		switch (mi.getItemId())
		{
		case MI_CLEAR_COUNTS:
			( (Main) getActivity() ).clearCounts();
			return true;

		case MI_START_TEST:
			( (Main) getActivity() ).startTest();
			return true;
		default:
			return false;
		}

	}
}
