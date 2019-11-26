package swd.project.assetmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import in.mrasif.libs.easyqr.EasyQR;
import in.mrasif.libs.easyqr.QRScanner;
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

    public void onClickScanQR(View view) {
//        Intent intent = new Intent(this, QRScanner.class);
//        startActivityForResult(intent, EasyQR.QR_SCANNER_REQUEST);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EasyQR.QR_SCANNER_REQUEST) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra(EasyQR.DATA);
                Toast.makeText(this, "text: " +contents, Toast.LENGTH_SHORT).show();
            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }
}
