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
import swd.project.assetmanagement.api_util.TokenManagement;

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
        TokenManagement.loadAccessToken(this);

    }

    public void onClickScanQR(View view) {
        Intent intent = new Intent(this, QRScanner.class);
        startActivityForResult(intent, EasyQR.QR_SCANNER_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EasyQR.QR_SCANNER_REQUEST) {

            if (resultCode == RESULT_OK) {

                try{
                    String assetId = data.getStringExtra(EasyQR.DATA);
                    Intent intent = new Intent(this,AssetDetailsActivity.class);
                    intent.putExtra("assetId",Integer.parseInt(assetId));
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(this, "Invalid QR", Toast.LENGTH_SHORT).show();
                }
            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }
}
