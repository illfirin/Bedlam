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

import butterknife.BindView;
import butterknife.ButterKnife;
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
        twitter.setOnClickListener(loginClick);
        google.setOnClickListener(loginClick);
        vk.setOnClickListener(loginClick);

        //Keys for SocialNetwork initiation
        String VK_KEY = getActivity().getString(R.string.vk_app_id);
        String TWITTER_CONSUMER_KEY = getActivity().getString(R.string.twitter_consumer_key);
        String TWITTER_CONSUMER_SECRET = getActivity().getString(R.string.twitter_consumer_secret);
        String TWITTER_CALLBACK_URL = "oauth://ASNE";

        //permissions for facebook
        ArrayList<String> fbScope = new ArrayList<String>();
        fbScope.addAll(Arrays.asList("public_profile, email"));

        //VK permissions
        String[] vkScope = new String[]
        {
          VKScope.WALL,
          VKScope.PHOTOS,
          VKScope.NOHTTPS,
          VKScope.Status,
        };
        mSocialNetworkManager = (SocialNetworkManager) getFragmentManager().findFragmentByTag(LoginWithSocialMedia.SOCIAL_NETWORK_TAG);

        //is manager exist?
        if(mSocialNetworkManager == null)
        {
          mSocialNetworkManager = new SocialNetworkManager();
          //initialize networks
          FacebookSocialNetwork fbNetwork = new FacebookSocialNetwork(this, fbScope);
          TwitterSocialNetwork twNetwork = new TwitterSocialNetwork(this, TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, TWITTER_CALLBACK_URL);
          GooglePlusSocialNetwork gpNetwork = new GooglePlusSocialNetwork(this);
          VkSocialNetwork vkNetwork = new VkSocialNetwork(this, VK_KEY);

          //add networks to manager
          mSocialNetworkManager.addSocialNetwork(fbNetwork);
          mSocialNetworkManager.addSocialNetwork(twNetwork);
          mSocialNetworkManager.addSocialNetwork(gpNetwork);
          mSocialNetworkManager.addSocialNetwork(vkNetwork);

          //initiate all the networks from networkmanager
          getFragmentManager().beginTransaction.add(mSocialNetworkManager, LoginWithSocialMedia.SOCIAL_NETWORK_TAG).commit();
          mSocialNetworkManager.setOnInitializationCompleteListener(this);

        }
        else
        {
            if(!mSocialNetworkManager.getInitializedSocialNetworks().isEmpty())
            {
              List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
              for(SocialNetwork sn : socialNetwork)
              {
                socialNetwork.setOnLoginCompleteListener(this);
                initSocialNetwork(socialNetwork);
              }
            }
        }
        return view;
    }
    //show social network profile if it is connected
    private void initSocialNetwork(SocialNetwork socialNetwork)
    {
      if(socialNetwork.isConnecteed())
      {
          switch(socialNetwork.getId())
          {
            case FacebookSocialNetwork.ID:
              facebook.setText("Show Facebook profile");
              break;
            case TwitterSocialNetwork.ID:
              twitter.setText("Show Twitter profile");
              break;
            case GooglePlusSocialNetwork.ID:
              google.setText("Show GooglePlus profile");
              break;
            case VkSocialNetwork.ID:
              vk.setText("Show Vk profile");
              break;
          }
      }
    }

    //setup login only for initialized social networks
    @Override
    public void onSocialNetworkManagerInitialized()
    {
      for(SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks())
      {
          socialNetwork.setOnLoginCompleteListener(this);
          initSocialNetwork(socialNetwork);
      }
    }

    //Login listener
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
                  Toast.makeText(getActivity(), "Wrong network id", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
              startProfile(socialNetwork.getId());
            }
        }
    };
    @Override
    public void onLoginSuccess(int networkId)
    {
      LoginWithSocialMedia.hideProgress();
      startProfile(networkId);
    }

    @Override
    public void onError(int networkId, String requestID, String errorMessage, Object data)
    {
      LoginWithSocialMedia.hideProgress();
      Toast.makeText(getActivivty(), "ERROR:" + errorMessage, Toast.LENGTH_LONG).show;

    }

    private void startProfile(int networkId)
    {
      ProfileFragment profile = ProfileFragment.newInstance(networkId);
      getActivity().getSupportFragmentManager().beginTransaction()
          .addToBackStack("profile")
          .replace(R.id.container, profile)
          .commit();
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
