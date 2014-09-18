package com.jock.wordup;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WordUpWidgetProvider extends AppWidgetProvider
{

	@Override
	public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds )
	{
		final int N = appWidgetIds.length;

		for(int i = 0; i < N; i++)
		{
			int appWidgetId = appWidgetIds[i];

			Intent intent = new Intent( context, Main.class );
			PendingIntent pendingIntent = PendingIntent.getActivity( context, 0, intent, 0 );

			RemoteViews views = new RemoteViews( context.getPackageName(), R.layout.layout_widget );
			views.setOnClickPendingIntent( R.id.tv_widget_word, pendingIntent );

			appWidgetManager.updateAppWidget( appWidgetId, views );
		}
	}
}
