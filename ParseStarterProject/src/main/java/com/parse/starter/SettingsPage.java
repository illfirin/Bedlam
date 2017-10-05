package pakuteam.bedlam_experiment_0_1;

import android.content.CursorLoader;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import android.animation.Animator;

public class SettingsPage extends LinearLayout
{
    @BindView(R.id.about) TextView about;
    @BindView(R.id.change_theme) TextView change_theme;
    @BindView(R.id.leave_review) TextView leave_review;
    @BindView(R.id.reload_frequency) TextView reload_frequency;
    @BindView(R.id.choose_fontsize) TextView choose_fontsize;
    @BindView(R.id.change_background) TextView change_background;

    protected ProgerssBar prog_bar;

    @Override
    protected void OnCreate(Bundle savedInstance)
    {
      super.OnCreate(savedInstance);
      setContentView(R.Layout.search);
      setReference();


      ProgressBar progressbar = new ProgressBar(this);
  		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
  									LayoutParams.WRAP_CONTENT, Garavity.CENTER));

      about.setOnClickListener((View v) ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(About.This, About.class));
      });

      change_theme.setOnClickListener((View v) ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(ChangeTheme.this, ChangeTheme.class));
      });

      leave_review.setOnClickListener((View v) ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(LeaveReview.This, LeaveReview.class));
      });

      reload_frequency.setOnClickListener((View v) ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(ReloadFrequency.this, ReloadFrequency.class));
      });

      choose_fontsize.setOnClickListener((View v) ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(ChooseFontsize.this, ChooseFontsize.class));
      });

      change_background.setOnClickListener((View v) ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(ChangeBackground.this, ChangeBackground.class));
      })
    }

    @Override
    protected void setReference()
    {
        root = LayoutInflater.from(this).inflate(R.id.settings_layout);
        prog_bar = new ProgressBar(this);
        getLinearLayout(root).setEmptyLayout(prog_bar);
        ButterKnife.Bind(this, roote)
    }


}
