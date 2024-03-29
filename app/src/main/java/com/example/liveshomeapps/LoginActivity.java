package com.example.liveshomeapps;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail,edPassword;
    TextView tvreg,tvforgot;
    Button btnLogin;
    SharedPreferences sharedPreferences;
    CheckBox cbrem,cbshwPassword;
    Dialog dialogforgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edEmail = findViewById(R.id.editTextEmail);
        edPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        tvreg = findViewById(R.id.tvRegister);
        cbrem = findViewById(R.id.checkBox);
        cbshwPassword=findViewById(R.id.checkboxPassword);
        tvforgot = findViewById(R.id.textForgot);
        tvreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String pass = edPassword.getText().toString();

                if(edEmail.length()==0)
                {
                    edEmail.setError("Please Enter a Valid Email");
                }

                else if (edPassword.length()==0)
                {
                    edPassword.setError("Please Enter Your Password");
                }
                loginUser(email,pass);
            }
        });


        cbshwPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        cbrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbrem.isChecked()){
                    String email = edEmail.getText().toString();
                    String pass = edPassword.getText().toString();
                    savePref(email,pass);
                }
            }
        });
        tvforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordDialog();
            }
        });
        loadPref();
    }
    private void savePref(String e, String p) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", e);
        editor.putString("password", p);
        editor.commit();
        Toast.makeText(this, "Preferences has been saved", Toast.LENGTH_SHORT).show();
    }
    private void loadPref() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String premail = sharedPreferences.getString("email", "");
        String prpass = sharedPreferences.getString("password", "");
        if (premail.length()>0){
            cbrem.setChecked(true);
            edEmail.setText(premail);
            edPassword.setText(prpass);
        }
    }
    private void loginUser(final String email, final String pass) {
        class LoginUser extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this,
                        "Login user","...",false,false);
            }
            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("email",email);
                hashMap.put("password",pass);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest
                        ("https://dharnisha250665.000webhostapp.com/login.php",hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("failed")){
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }else if (s.length()>7){
                    //Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
                    String[] val = s.split(",");
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("userid",email);
                    bundle.putString("name",val[0]);
                    bundle.putString("phone",val[1]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }
        LoginUser loginUser = new LoginUser();
        loginUser.execute();
    }
    void forgotPasswordDialog(){
        dialogforgotpass = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
        dialogforgotpass.setContentView(R.layout.forgot_password);
        dialogforgotpass.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        final EditText edemail = dialogforgotpass.findViewById(R.id.edtEmail);
        Button btnsendemail = dialogforgotpass.findViewById(R.id.btnsendemail);
        btnsendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgotemail =  edemail.getText().toString();
                sendPassword(forgotemail);
            }
        });
        dialogforgotpass.show();

    }

    private void sendPassword(final String forgotemail) {
        class SendPassword extends AsyncTask<Void,String,String>{

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap();
                hashMap.put("email",forgotemail);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("https://dharnisha250665.000webhostapp.com/verify_email.php",hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equalsIgnoreCase("success")){
                    Toast.makeText(LoginActivity.this, "Success. Check your email", Toast.LENGTH_LONG).show();
                    dialogforgotpass.dismiss();
                }else{
                    Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
        SendPassword sendPassword = new SendPassword();
        sendPassword.execute();
    }
}
