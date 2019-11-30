package swd.project.assetmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText txtEmail, txtUsername, txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtEmail = findViewById(R.id.txtNewEmail);
        txtUsername = findViewById(R.id.txtNewUsername);
        txtPassword = findViewById(R.id.txtNewPassword);
    }

    public void clickToCreate(View view) {
        String email = txtEmail.getText().toString();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        if(validateEmail(email) && validateUsername(username) && validatePassword(password)) {
            Toast.makeText(RegisterActivity.this, "Create Success!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validateUsername(String username) {
        if(username.isEmpty()) {
            txtUsername.setError("Username can't be empty!");
            return false;
        }//check duplicate username if()
        return true;
    }

    public boolean validatePassword(String password) {
        if(password.isEmpty()) {
            txtPassword.setError("Password can't be empty!");
            return false;
        }else if(password.length() > 0 && password.length() <6) {
            txtPassword.setError("Password must more than 6!");
        }
        return true;
    }
    public boolean validateEmail(String email) {
        if(email.isEmpty()) {
            txtEmail.setError("Email can't be empty!");
            return false;
        }else if(!email.matches("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")) {
            txtEmail.setError("Email wrong format!");
            return false;
        }
        //check duplicate email if()

        return true;
    }
}
