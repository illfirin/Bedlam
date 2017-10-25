package pakuteam.bedlam_experiment_0_1;

import com.parse.starter.CitationView;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import butterKnife.bind;
import butterKnife.ButterKnife;

public class CitationActivity extends BaseActivity
{
    protected View rootView;
    private AdView mAdView;
    @Bind(android.R.id.citationViewLayout)
    private LinearLayout citationViewContainer;
    
    public CitationActivity(CitationView v)
    {
        citationViewContainer.addView(v);
    }

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setReference();
        setSimpleToolbar(true);
        mAdView = new AdView(this);
        mAdView.size(AdSize.BANNER);
        mAdView.setAdUnitId(R.string.c_ad_unitId);
		mAdView = (AdView) findViewById(R.id.View);
		AdRequest adReq = new AdRequest.Builder().build();
		mAdView.loadAd(adReq);
    }

    @Override
    public void setReference()
    {
        root = LayoutInflater.from(this).inflate(android.R.id.activity_main_CitationActivity_container, container);
        ButterKnife.bind(this, root);
    }
}
