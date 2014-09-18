package com.example.androidanimations.SlidingFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidanimations.R;

public class ImageFrag extends Fragment
{
	View.OnClickListener clickListener;


	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
	{
		View view = inflater.inflate( R.layout.image_fragment, container, false );
		view.setOnClickListener( clickListener );
		return view;
	}


	public void setClickListener( View.OnClickListener clickListener )
	{
		this.clickListener = clickListener;
	}
}
