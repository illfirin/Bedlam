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

public static class CreateContent
{
  public static List<CitationView> getMainPageCitations(Date creation_date)
  {
      ParseQuery<ParseObject> query = ParseQuery.getQuery("Citation");
      query.whereEqualTo("createdAt", creation_date);
      query.findInBackground(new FindCallBack<ParseObject>()
      {
         public void done(List<ParseObject> citList, ParseException e)
         {
             if(e == null)
             {
                 List<Item> citation_items = citList.stream().map((o) -> ParseStorage.FromParseObject(o)).collect(Collectors.toList());
                 List<CitationView> cit_listView = citations_items.stream().map((e) -> {new CitationView(e, this)};).collect(Collectors.toList());
                 return cit_listView;
             }
             else
             {
                 Sentry.capture(e)
                 Toast.makeText(getApplicationContext(), getString(R.string.no_such_citationErr, Toast.LENGTH_SHORT));
                 Toast.setGravity(Gravity.CENTER, 0, 0);
             }
         }
      });
  }

  public static CitationView[] getCurrUserFavouriteViewsArray()
	{
		ParseUser curr = ParseUser.getCurrentUser();
		if(curr != null)
		{
			ParseQuery<ParseObject> fav = curr.getQuery("favourite");
			List<Item> citations = fav.stream().map((o) -> ParseStorage.FromParseObject(o)).collect(Collectors.toList());
			CitationView[] citations_views_array = citations.stream().map((e) -> {new CitationView(e, this)};).toArray(CitationView[]::new);

			return citations_views_array;
		}
		else
		{
			startActivity(new Intent(this, LoginActivity.class));
		}
	}
}
