package com.example.userprofile;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView infoMessage;
    private Button buttonSubmit;
    private TextView emailTextView;
    private TextView passwordTextView;
    private boolean emailChanged = false;
    private boolean passwordChanged = false;
    private String tempEmail = "";
    private String tempPasswordFirst = "";
    private String tempPasswordLast = "";
    private int errorType = 0;
    private int errors = 0;

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
                alertDialogChangeEmail();
            }
        });
    }
    private void alertDialogChangeEmail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zmień Email");
        builder.setMessage("Podaj nowy email:");

        EditText changeEmail = new EditText(this);
        changeEmail.setHint("Nowy email");
        builder.setView(changeEmail);

        builder.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tempEmail = String.valueOf(changeEmail.getText());
                emailChanged = true;
                alertDialogChangePassword();
            }
        });

        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialogChangePassword();
            }
        });
        builder.create().show();
    }

    private void alertDialogChangePassword(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zmień Hasło");
        builder.setMessage("Podaj nowe hasło:");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 10); // чуть-чуть отступов

        EditText changePasswordFirst = new EditText(this);
        changePasswordFirst.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(changePasswordFirst);

        TextView middleMessage = new TextView(this);
        middleMessage.setText("Powtórz hasło:");
        middleMessage.setPadding(0, 20, 0, 10);
        layout.addView(middleMessage);

        EditText changePasswordLast = new EditText(this);
        changePasswordLast.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(changePasswordLast);

        builder.setView(layout);

        builder.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tempPasswordFirst = String.valueOf(changePasswordFirst.getText());
                tempPasswordLast = String.valueOf(changePasswordLast.getText());
                passwordChanged = true;
                validation();
            }
        });

        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                validation();
            }
        });
        builder.create().show();
    }

    private void validation(){
        if(emailChanged){
            if(!(tempEmail.contains("@"))){
                errorType = 1;
                errors += 1;
            }
        }

        if(passwordChanged){
            if(!(tempPasswordFirst.equals(tempPasswordLast))){
                errorType = 2;
                errors += 1;
            }
        }

        if(errors==2){
            infoMessage.setTextColor(Color.RED);
            infoMessage.setText("Błąd: Nieprawidłowy format emaila." + "\n" + "Błąd: Hasła nie pasują do siebie.");
        }

        if(errors==1){
            if(errorType==1){
                infoMessage.setTextColor(Color.RED);
                infoMessage.setText("Błąd: Nieprawidłowy format emaila.");
            }

            if(errorType==2){
                infoMessage.setTextColor(Color.RED);
                infoMessage.setText("Błąd: Hasła nie pasują do siebie.");
            }
        }

        if(errors==0){
            Log.d("123", String.valueOf(emailChanged));
            Log.d("123", String.valueOf(passwordChanged));
            if((!emailChanged) && (!passwordChanged)){
                infoMessage.setTextColor(Color.GRAY);
                infoMessage.setText("Edycja profilu anulowana");
            }

            if(errorType==0 && errors==0){
                if(emailChanged || passwordChanged){
                    infoMessage.setTextColor(Color.GREEN);
                    infoMessage.setText("Profil zaktualizowany!");
                }
                if(emailChanged){
                    emailTextView.setText(tempEmail);
                    tempEmail="";
                    emailChanged=false;
                }
                if(passwordChanged){
                    passwordTextView.setText(tempPasswordFirst);
                    tempPasswordFirst="";
                    tempPasswordLast="";
                    passwordChanged=false;
                }
            }
        }
        emailChanged=false;
        passwordChanged=false;
        errors=0;
        errorType = 0;
    }
}