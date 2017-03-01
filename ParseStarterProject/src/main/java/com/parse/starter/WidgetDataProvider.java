package com.parse.starter;


import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * WidgetDataProvider acts as the adapter for the collection view widget,
 * providing RemoteViews to the widget in the getViewAt method.
 */
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory 
{

    private static final String TAG = "WidgetDataProvider";

    List<object> mCollection = new ArrayList<>();
    Context mContext = null;

    public WidgetDataProvider(Context context, Intent intent) 
    {
        mContext = context;
    }

    @Override
    public void onCreate()
    {
        initData();
    }

    @Override
    public void onDataSetChanged() 
    {
        initData();
    }

    @Override
    public void onDestroy() 
    {

    }

    @Override
    public int getCount() 
    {
        return mCollection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) 
    {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        //view.setTextViewText(android.R.id.text1, mCollection.get(position));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() 
    {
        return null;
    }

    @Override
    public int getViewTypeCount() 
    {
        return 1;
    }

    @Override
    public long getItemId(int position) 
    {
        return position;
    }

    @Override
    public boolean hasStableIds() 
    {
        return true;
    }

    public class erItem()
    {
        public erItem()
        {
            Author = this.Author
            Content = this.Content;
        }

        public string Author;
        public string Content;
    }
    Collector<List<Item>, List<erItem>, List<erItem>> fromItemToerItem Collector.of(
                                                                 List<erItem>::new,
                                                                 (c, c1) -> 
                                                                 { 
                                                                    c1.Author = c.Author;          
                                                                    c1.Content = c.Author;

                                                                }
    private void initData() 
    {
        mCollection.clear();
        private ParseUser curr = ParseUser.getCurrentUser();
        private ParseQuery<ParseObject> fav_orig = curr.getQuery("favourite");

        private List<erItem> fav_projected = fav_orig.stream().
                                                forEach().collect(fromItemToerItem));
        static int rand = ThreadLocalRandom.current().nextInt(0, fav_projected.size() - 1);

        mCollection.add(fav_projected(rand));
        
    }

}