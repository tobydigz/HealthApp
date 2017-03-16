package com.digzdigital.healthapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.databinding.ActivityResetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<Void> {

    private ActivityResetPasswordBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        auth = FirebaseAuth.getInstance();
        binding.btnResetPassword.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_reset_password:
                resetPassword();
                break;
            case R.id.btn_back:
                switchActivity(LoginActivity.class);
                break;
        }
    }

    private void resetPassword() {
        String email = binding.email.getText().toString().trim();
        if (!validate(email))return;
        binding.btnResetPassword.setEnabled(false);
        binding.progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this);
    }

    private boolean validate(String email) {
        boolean state = true;
        if (TextUtils.isEmpty(email)) {
            Snackbar.make(binding.activityResetPassword, "Enter email address!", Snackbar.LENGTH_SHORT).show();
            binding.email.setError("Enter email address!");
            state =  false;
        } else binding.email.setError(null);

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(binding.activityResetPassword, "Enter a valid email address!", Snackbar.LENGTH_SHORT).show();
            binding.email.setError("Enter a valid email address!");
            state = false;
        } else binding.email.setError(null);

        return state;
    }

    private void switchActivity(Class classFile) {
        Intent intent = new Intent(this, classFile);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        binding.progressBar.setVisibility(View.GONE);
        if (task.isSuccessful()){
            Snackbar.make(binding.activityResetPassword, "Instructions have been sent to your email", Snackbar.LENGTH_SHORT).show();
            switchActivity(LoginActivity.class);
        }else {
            binding.btnResetPassword.setEnabled(true);
            Snackbar.make(binding.activityResetPassword, "Failed to send reset instructions", Snackbar.LENGTH_SHORT).show();
        }
    }
}

