/*

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.MobileAds;
import com.parse.ParseAnalytics;
import java.util.Calendar;
import java.util.Date;
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

public class MainActivity extends BaseActivity
{
    private ViewGroup root;
    ProgressBar progressbar;

    @Bind(R.layout.Mainpage_citations)
    protected ListView mView;

    Calendar rightNow = Calendar.getInstance();
    Calendar forOneDayCalendar = Calendar.getInstance();
    Calendar forThreeDaysCalendar = Calendar.getInstance();
    Calendar forTwoWeeksCalendar = Calendar.getInstance();

    Date rightNowDate = new Date(rightNow.getTimeInMillis());
    Date oneDaysDate = new Date(rightNow.getTimeInMillis() - 86400000L);
    Date threeDaysDate = new Date(rightNow.getTimeInMillis() - 86400000L*3L);
    Date twoWeeksDate = new Date(rightNow.getTimeInmillis() - 86400000L*12L);

    ArrayAdapter<CitationView> lAdapter;
    List<CitationView> content_list;
    ptivate AdView adView;

    /*
        По дефолту - цитаты за сегодняшний день, кнопки с показать за последние 3 дня, 7 дней, 2 недели.

    */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView = new AdView(this);
        adView.size(AdSize.BANNER);
        adView.setAdUnitId(R.string.constructor_ad_unitId);

        adView = (AdView) findViewById(R.id.mainAdvView);
        AdRequest adReq = new AdRequest.Builder().build();
        adView.loadAd(adReq);
        
        setReference();


        content_list = new ArrayList<CitationView>();
        CitationView[] viewArr = content_list.toArray(new CitationView[content_list.size()]);

        lAdapter = new ArrayAdapter<CitationView>(this, content_list, viewArr);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());


    }


    @OnClick(R.id.threedays_button)
    public void threedays_button()
    {
        int one_day = oneDaysDate.getDate();
        int one_day_month = oneDaysDate.getMounth();
        int one_day_year = oneDaysDate.getYear();
        for(int i = oneDaysDate; i< rightNowDate.getDate(); i++)
        {

        }
    }

    @OnCreate(R.id)
    @OnClick(R.id.twoWeeks_button)
    public void twoWeeks_button()
    {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;

            case android.R.id.activity_constructor:
                startActivity(new Intent(ConstructorPage.This, ConstructorPage.class));
                break;

            case android.R.id.activity_favourite:
                startActivity(new Intent(FavouriteCitations.This, FavouriteCitations.class));
                break;

            case android.R.id.activity_search:
                startActivity(new Intent(SearchPage.This, SearchPage.class));
                break;

            case android.R.id.activity_settings:
                startActivity(new Intent(FavouriteCitations.This, FavouriteCitations.class));
                break;
    }

    @Override
    public void setReference()
    {
        root = LayoutInflater.from(this).inflate(R.id.main_layout);
        progressbar = new ProgressBar(this);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT, Garavity.CENTER));
        getLinearLayout(root).setEmptyLayout(progressbar);
        ButterKnife.Bind(this, root);
    }

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings)
    {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data);

    Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
    if (fragment != null)
    {
        fragment.onActivityResult(requestCode, resultCode, data);
    }
  }

  public void setDefaulMainPageCitations()
  {

  }

  public static CitationView[] getMainPageCitations(Date creation_date)
  {
      ParseQuery<ParseObject> query = ParseQuery.getQuery("Citation");
      query.whereEqualTo("createdAt", creation_date);
      query.findInBackground(new FindCallBack<ParseObject>()
      {
         public void done(List<ParseObject> citList, ParseException e)
         {
             if(e == null)
             {
                 List<Item> citation_items = citList.stream().map((0) -> ParseStorage.FromParseObject(0)).collect(Collectors.toList());
                 CitationView[] cit_array = citations_items.stream().map((e) -> {new CitationView(e, this)}).toArray(CitationView[]::new);
             }
             else
             {
                 Toast.makeText(getApplicationContext(), getString(R.string.no_such_citationErr, Toast.LENGTH_SHORT));
                 Toast.setGravity(Gravity.CENTER, 0, 0);
             }
         }
      });
  }
}
