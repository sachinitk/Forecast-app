package com.example.sachin.forecast;

import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class tweets extends AppCompatActivity {

    private String consumerKey = "U5iSYtk08rKtv2y5ouKbt3g8v";
    private String consumerSecret = "d3GrAUbUrFpkYSSvZmUO49BKeNW7vmapYcY6qWBC10v6HaQpjQ";
    private String twitterToken = "3235317349-qcP3LRdyq6klXE5qYX55fyJeXb8IrNhcp21U7hD";
    private String twitterSecret = "sxTCgwrlLbjLHI8s65NlYnVPIhcJLHjBw2OsssgYirkSk";
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.tweets);

        ScrollView sv = new ScrollView(this);
        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        if (android.os.Build.VERSION.SDK_INT > 15) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(twitterToken);
        cb.setOAuthAccessTokenSecret(twitterSecret);

        java.util.List statuses = null;

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();

        String userName ="AgriGoI";
        int numTweets = 240;
        String[] twArray = new String[numTweets];



        try {
            statuses = twitter.getUserTimeline(userName);
        }
        catch(TwitterException e) {
        }

        for (int i=0; i<statuses.size(); i++) {
            Status status = (Status)statuses.get(i);


            //twArray[i] = status.getUser().getName() + ": " + status.getText();
            twArray[i] = ""+status.getText();
            TextView t1 = new TextView(this);
            t1.setAutoLinkMask(1);
            t1.setText(twArray[i]);

            t1.setPadding(0,7,0,7);
            t1.setTextColor(Color.WHITE);
            t1.setBackgroundColor(Color.BLACK);


            ll.addView(t1);


        }
        rl.addView(sv);
    }

}