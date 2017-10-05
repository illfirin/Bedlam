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

    @Bind(android.R.id.citationViewLayout)
    private LinearLayout citationViewConteiner;

    public CitationActivity(CitationView v)
    {
        citationViewConteiner.addView(v);
    }

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setReference();
        setSimpleToolbar(true);
    }

    @Override
    public void setReference()
    {
        root = LayoutInflater.from(this).inflate(android.R.id.activity_main_CitationActivity_container, container);
        ButterKnife.bind(this, root);
    }
}
