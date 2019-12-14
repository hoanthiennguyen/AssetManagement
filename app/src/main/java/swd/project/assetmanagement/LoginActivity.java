package swd.project.assetmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.LoginDTO;
import swd.project.assetmanagement.repository.AuthRepositoryImpl;

public class LoginActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    Button loginGG;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private GoogleSignInClient signInClient;
    private int RC_SIGN_IN = 0;
    private AuthRepositoryImpl authRepo = new AuthRepositoryImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        loginGG = findViewById(R.id.loginGG);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(this, signInOptions);
        loginGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Waiting...");
    }

    public void clickToLogin(View view) {
//        String username = txtUsername.getText().toString();
//        String password = txtPassword.getText().toString();
//        if(checkLogin(username, password)) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//        }
    }

    public void clickToRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public boolean checkLogin(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please input Username or Password!!!", Toast.LENGTH_SHORT).show();
            return false;
                }else if(username.matches("xcuong") && password.matches("123456")) {
            return true;
        }
        return false;
    }

    @Override
    protected void onStart() {
        //check login before
        GoogleSignInAccount currentUser = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
        if(currentUser != null) {
            progressDialog.show();
            authRepo.loginByGG(currentUser.getIdToken(), new CallbackData<LoginDTO>() {
                @Override
                public void onSuccess(LoginDTO employee) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("employee", employee);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFail(String msg) {

                }
            });
        }
        super.onStart();
    }

    private void signIn() {
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completeTask) {
        try {
            GoogleSignInAccount account = completeTask.getResult(ApiException.class);
            progressDialog.show();
            authRepo.loginByGG(account.getIdToken(), new CallbackData<LoginDTO>() {
                @Override
                public void onSuccess(LoginDTO employee) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("employee", employee);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFail(String msg) {

                }
            });
        }catch (ApiException e) {
            Toast.makeText(this, "Error!!!", Toast.LENGTH_SHORT).show();
            Log.w("MainActivity", "signInResult: failed code=" +e.getStatusCode());
        }
    }
}
