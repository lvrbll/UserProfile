package com.example.userprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView infoMessage;
    private Button buttonSubmit;
    private TextView emailTextView;
    private TextView passwordTextView;
    private Button buttonSubmitEmailDialog;
    private Button buttonTerminateEmailDialog;
    private Button buttonSubmitPasswordDialog;
    private Button buttonTerminatePasswordDialog;
    private EditText editTextEmailDialog;
    private EditText editTextPasswordDialogFirst;
    private EditText editTextPasswordDialogLast;
    private String email;
    private String password;
    private String tempEmail = "";
    private String tempPasswordFirst = "";
    private String tempPasswordLast = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        infoMessage = findViewById(R.id.infoMessage);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);

        infoMessage.setText("Witaj! Aplikacja wykonana przez: Artema Harashchenko");

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogChangeEmail();
            }
        });
    }
    private void customDialogChangeEmail(){
        final android.app.Dialog dialog = new android.app.Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_change_email);

        buttonSubmitEmailDialog = dialog.findViewById(R.id.buttonSubmitEmailDialog);
        buttonTerminateEmailDialog = dialog.findViewById(R.id.buttonTerminateEmailDialog);
        editTextEmailDialog = dialog.findViewById(R.id.editTextEmailDialog);

        buttonSubmitEmailDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogChangePassword();
                dialog.dismiss();
                tempEmail = String.valueOf(editTextEmailDialog.getText());
            }
        });

        buttonTerminateEmailDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogChangePassword();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void customDialogChangePassword(){
        final android.app.Dialog dialog = new android.app.Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_change_password);

        buttonSubmitPasswordDialog = dialog.findViewById(R.id.buttonSubmitPasswordDialog);
        buttonTerminatePasswordDialog = dialog.findViewById(R.id.buttonTerminatePasswordDialog);
        editTextPasswordDialogFirst = dialog.findViewById(R.id.editTextPasswordDialogFirst);
        editTextPasswordDialogLast = dialog.findViewById(R.id.editTextPasswordDialogLast);

        buttonSubmitPasswordDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempPasswordFirst = String.valueOf(editTextPasswordDialogFirst.getText());
                tempPasswordLast = String.valueOf(editTextPasswordDialogLast.getText());
                dialog.dismiss();
            }
        });

        buttonTerminatePasswordDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
