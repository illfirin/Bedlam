package com.parse.starter;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;
import java.util.concurrent.ThreadLocalRandom;


public class CollectionWidget extends AppWidgetProvider
{
	private ParseUser curr = ParseUser.getCurrentUser();
	private ParseQuery<ParseObject> fav = curr.getQuery("favourite");

	private List<Item> citations = new ArrayList<Item>();
	static int rand = ThreadLocalRandom.current().nextInt(0, fav.size() - 1);

	static void updateAppWidget(Context context, 
	    				AppWidgetManager appWidgetManager, int appWidgetId) 
	{
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bedlam_widget);
	    views.setTextViewText(R.id.bedlamWidget_author, fav.getItem(rand).Author);
	    

        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) 
        {
            setRemoteAdapter(context, views);
        } 
        else 
        {
            setRemoteAdapterV11(context, views);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    //TODO Rewrite using ParseStorage

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) 
    {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) 
        {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) 
    {
        // when the first widget is created
    }

    @Override
    public void onDisabled(Context context) 
    {
        // when the last widget is disabled
    }

    /**
     * Sets the remote adapter used to fill in the list items
     * @param views RemoteViews to set the RemoteAdapter
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) 
    {
        views.setRemoteAdapter(R.id.widget_list,
                new Intent(context, WidgetService.class));
    }

    /**
     * Sets the remote adapter used to fill in the list items
     * @param views RemoteViews to set the RemoteAdapter
     */
    @SuppressWarnings("deprecation")
    private static void setRemoteAdapterV11(Context context, @NonNull final RemoteViews views) 
    {
        views.setRemoteAdapter(0, R.id.widget_list,
                new Intent(context, WidgetService.class));
	}
}