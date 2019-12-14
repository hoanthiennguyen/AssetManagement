package swd.project.assetmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import in.mrasif.libs.easyqr.EasyQR;
import in.mrasif.libs.easyqr.QRScanner;
import swd.project.assetmanagement.api_util.TokenManagement;
import swd.project.assetmanagement.model.Employee;
import swd.project.assetmanagement.model.LoginDTO;

public class MainActivity extends AppCompatActivity {
    LoginDTO employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationBar);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new HomeFragment();
        transaction.replace(R.id.fragContainer,fragment);
        transaction.commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_qr:
                        Intent intent = new Intent(MainActivity.this, QRScanner.class);
                        startActivityForResult(intent, EasyQR.QR_SCANNER_REQUEST);
                        return true;
                    case R.id.nav_request:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.nav_profile:
                        fragment = new EmployeeFragment();
                        break;
                }
                transaction.replace(R.id.fragContainer, fragment);
                transaction.commit();
                return true;
            }
        });

        //Token
        TokenManagement.loadAccessToken(this);

        Intent intent = getIntent();
        employee = (LoginDTO) intent.getSerializableExtra("employee");
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

    public LoginDTO getEmployee() {
        return employee;
    }
}
