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
    protected TextView about;
    protected TextView change_theme;
    protected TextView leave_review;
    protected TextView reload_frequency;
    protected TextView choose_fontsize;
    protected TextView change_background;

    @Override
    protected void OnCreate(Bundle savedInstance)
    {
      super.OnCreate(savedInstance);
      setContentView(R.Layout.search);

      about = (TextView)findViewById(R.id.about);
      change_theme = (TextView)findViewById(R.id.change_theme);
      leave_review = (TextView)findViewById(R.id.leave_review);
      reload_frequency = (TextView)findViewById(R.id.reload_frequency);
      choose_fontsize = (TextView)findViewById(R.id.choose_fontsize);
      change_background = (TextView) findViewById(R.id.change_background);

      ProgressBar progressbar = new ProgressBar(this);
  		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
  									LayoutParams.WRAP_CONTENT, Garavity.CENTER));

      about.setOnClickListener(OnClickListener c ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(About.This, About.class));
      });

      change_theme.setOnClickListener(OnClickListener c ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(ChangeTheme.this, ChangeTheme.class));
      });

      leave_review.setOnClickListener(OnClickListener c ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(LeaveReview.This, LeaveReview.class));
      });

      reload_frequency.setOnClickListener(OnClickListener c ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(ReloadFrequency.this, ReloadFrequency.class));
      });

      choose_fontsize.setOnClickListener(OnClickListener c ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(ChooseFontsize.this, ChooseFontsize.class));
      });

      change_background.setOnClickListener(OnClickListener c ->
      {
        progressbar.setProgressBarIndeterminateVisibility(true);
        startActivity(new Intent(ChangeBackground.this, ChangeBackground.class));
      })
    }



}
