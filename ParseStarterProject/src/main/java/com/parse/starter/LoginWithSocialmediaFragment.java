package pakuteam.bedlam_experiment_0_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnLoginCompleteListener;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.googleplus.GooglePlusSocialNetwork;
import com.github.gorbin.asne.linkedin.LinkedInSocialNetwork;
import com.github.gorbin.asne.twitter.TwitterSocialNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
    * 1 - Twitter
    * 2 - LinkedIn
    * 3 - Google Plus
    * 4 - Facebook
    * 5 - Vkontakte
    * 6 - Odnoklassniki
    * 7 - Instagram
*/
public class LoginWithSocialmediaFragment extends Fragment implements SocialNetworkManager.OnInitializationCompleteListener, OnLoginCompleteListener
{
    public static SocialNetworkManager mSocialNetworkManager;
    //Views binding for buttons
    @BindView(R.layout.login_vk)
    private Button vk;
    @BindView(R.layout.login_google)
    private Button google;
    @BindView(R.layout.login_twitter)
    private Button twitter;
    @BindView(R.layout.login_facebook)
    private Button facebook;

    public LoginWithSocialmediaFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                        Bundle savedInctanceState)
    {
        View view = inflater.inflate(R.layout_loginWithSocialMediaFragment, container, false);
        //init buttons
        ButterKnife.bind(this, view);
        //set Buttons Listeners
        facebook.setOnClickListener(loginClick);

    }
    //
    private View.OnCLickListener loginClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View buttonView)
        {
            int networkId = 0;
            switch(buttonView.getId())
            {
                case R.id.facebook:
                  networkId = FacebookSocialNetwork.ID;
                  break;
                case R.id.vk:
                  networkId = VkSocialNetwork.ID;
                  break;
                case R.id.google:
                  networkId = GooglePlusSocialNetwork.ID;
                  break;
                case R.id.facebook:
                  networkId = TwitterSocialNetwork.ID;
                  break;
            }
            SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
            if(!socialNetwork.isConnected())
            {
                if(networkId != 0)
                {
                  socialNetwork.requestLogin();
                  LoginWithSocialMedia.showProgress("Загрузка");
                }
                else
                {
                  //Add BreadCrumbs and exception handling here
                  Toast.makeText("Wrong network id").show();
                }
            }
            else
            {
              startProfile(socialNetwork.getId());
            }
        }
    }
}

/*
private ViewGroup root;
@Bind(R.layout.login_vk)
protected Button mVkLogin;
@Bind(R.layout.login_google)
protected Button mGoogleLogin;
@Bind(R.layout.login_twitter)
protected Button mTwitterLogin;
@Bind(R.layout.login_facebook)
protected Button mFacebook;

@Override
protected void onCreate(Bundle savedInstance)
{
    super.onCreate(savedInstance);
    setReference();
}


@Override
public void setReference()
{
root = LayoutInflater.from(this).inflate(android.R.id.login_socialMedia);

progressbar = new ProgressBar(this);
progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT, Garavity.CENTER));
getListView(lView).setEmptyView(progressbar);
root.addView(progressbar);

ButterKnife.Bind(this, root);
}

} */
