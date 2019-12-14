package swd.project.assetmanagement;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import swd.project.assetmanagement.model.LoginDTO;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeFragment extends Fragment {
    private GoogleSignInClient signInClient;
    Button btnLogout;
    LoginDTO employee;
    TextView txtName, txtEmail;

    public EmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);

        btnLogout = view.findViewById(R.id.btnLogout);
        final MainActivity mainActivity = (MainActivity) view.getContext();
        employee = mainActivity.getEmployee();

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        txtName.setText(employee.getEmployee().getFullName());
        txtEmail.setText(employee.getEmployee().getEmail());

        signInClient = GoogleSignIn.getClient(view.getContext(), signInOptions);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                signInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(view.getContext(), LoginActivity.class);
                        startActivity(intent);
                        mainActivity.finish();
                    }
                });
            }
        });
        return view;
    }

}
