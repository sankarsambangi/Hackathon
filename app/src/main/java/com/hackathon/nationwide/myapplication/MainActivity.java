package com.hackathon.nationwide.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hackathon.nationwide.myapplication.Controller.VolleyController;
import com.hackathon.nationwide.myapplication.Fragments.ATMFragment;
import com.hackathon.nationwide.myapplication.Fragments.FirstFragment;
import com.hackathon.nationwide.myapplication.Fragments.ItemFragment;
import com.hackathon.nationwide.myapplication.Fragments.dummy.DummyContent;
import com.hackathon.nationwide.myapplication.Models.BranchResponseModel;
import com.hackathon.nationwide.myapplication.Network.JacksonRequest;

import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity
        implements FirstFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener, ATMFragment.OnFragmentInteractionListener{

    public enum ResponseType {
        BRANCH,
        ATM
    }

    private FragmentPagerAdapter mSeciontsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handleSSLHandshake();
        mSeciontsPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSeciontsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        DummyContent.ITEMS.clear();
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onFragmentInteraction(List<JSONObject> list, ResponseType responseType) {
        try{
            Log.d("Hackthon", "Filtered list size"+list.size());
            ItemFragment firstFragment = ItemFragment.newInstance(1);
            String parameter = responseType == ResponseType.BRANCH ? "Name" : "Identification";

            for(int i=0; i<list.size(); i++) {
                JSONObject branch = list.get(i);
                JSONObject latlng = new JSONObject();
                if(responseType == ResponseType.BRANCH) {
                    latlng = branch.optJSONObject("PostalAddress").optJSONObject("GeoLocation").optJSONObject("GeographicCoordinates");
                } else if (responseType == ResponseType.ATM) {
                    latlng = branch.optJSONObject("Location").optJSONObject("PostalAddress").optJSONObject("GeoLocation").optJSONObject("GeographicCoordinates");
                }

                DummyContent.DummyItem item = new DummyContent.DummyItem(i+"", branch.getString(parameter), branch.getString(parameter), latlng.optString("Latitude"), latlng.optString("Longitude"));
                DummyContent.addItem(item);
            }
            Toast.makeText(this, "Branches found : "+ list.size(), Toast.LENGTH_LONG).show();
            if (firstFragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, firstFragment);
                ft.addToBackStack("list");
                ft.commit();
            }
        } catch (Exception e){
            Log.e("Hackathon", e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.i("Hackathon", item.details);
        Toast.makeText(this,item.details, Toast.LENGTH_SHORT).show();

        Double latitude = Double.parseDouble(item.latitude);
        Double longitude = Double.parseDouble(item.longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<" + latitude  + ">,<" + longitude + ">?q=<" + latitude  + ">,<" + longitude + ">(" + item.details + ")"));


//        String urlAddress = "http://maps.google.com/maps?q="+ item.latitude  +"," + item.longitude +"("+ item.details + ")&iwloc=A&hl=es";
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));

        startActivity(intent);
    }
}
