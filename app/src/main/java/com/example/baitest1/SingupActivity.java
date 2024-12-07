package com.example.baitest1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitest1.databaes.UserDB;

public class SingupActivity extends AppCompatActivity {

    EditText edtname, edtemail, edtpassword, edtcomper;
    TextView tvSign;
    UserDB userDB;
    Button btnSm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        userDB = new UserDB(SingupActivity.this);
        edtname = findViewById(R.id.edtsuname);
        edtemail = findViewById(R.id.edtsuemail2);
        edtpassword = findViewById(R.id.edtsupass2);
        edtcomper = findViewById(R.id.edtconpass);
        btnSm = findViewById(R.id.btnsubmit);
        tvSign = findViewById(R.id.singg);

        btnSm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String name = edtname.getText().toString().trim();
                String password = edtpassword.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String comperpassword = edtcomper.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    edtname.setError("username can not");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    edtpassword.setError("password khong ton tai");
                    return;

                }
                if (TextUtils.isEmpty(email)) {
                    edtemail.setError("email khong ton tai");
                    return;
                }
                if (TextUtils.isEmpty(comperpassword)) {
                    edtcomper.setError("phone khong ton tai");
                    return;
                }
                long insert = userDB.addNewUser(name, password, email, comperpassword);
                if(insert == -1 ){
                    Toast.makeText(SingupActivity.this,"sinup lost",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SingupActivity.this,"thanh cong",Toast.LENGTH_SHORT).show();
                    // thanh cong thi ve man de dang nhap
                    Intent intent = new Intent(SingupActivity.this,SinginActivity.class);
                    startActivity(intent);
                }

            }

        });
        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2222 = new Intent(SingupActivity.this,SinginActivity.class);
                startActivity(intent2222);
            }
        });


    }
}
