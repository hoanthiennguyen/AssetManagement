package swd.project.assetmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import swd.project.assetmanagement.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayoutMainMenu;
    ViewPager viewPagerMainContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayoutMainMenu = findViewById(R.id.tabLayoutMainMenu);
        viewPagerMainContent = findViewById(R.id.viewPagerMainContent);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new HomeFragment(),"Home");
        viewPagerAdapter.addFragment(new NotificationFragment(),"Notification");

        viewPagerMainContent.setAdapter(viewPagerAdapter);
        tabLayoutMainMenu.setupWithViewPager(viewPagerMainContent);

    }
    public void onClickScanQR(View view){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
}
