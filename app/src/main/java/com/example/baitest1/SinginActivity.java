package com.example.baitest1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitest1.databaes.UserDB;
import com.example.baitest1.modor.User;

public class SinginActivity extends AppCompatActivity {
    EditText edtemail, edtpass;
    Button btnSingin;
    UserDB userDB;
    User user;
    TextView tvsinup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
        edtemail = findViewById(R.id.edtsiemail);
        edtpass = findViewById(R.id.edtsipass);
        btnSingin = findViewById(R.id.btnsibt);
        tvsinup = findViewById(R.id.ggg);

       tvsinup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent33 = new Intent(SinginActivity.this, SingupActivity.class);
               startActivity(intent33);
           }
       });

        userDB = new UserDB(SinginActivity.this);
        checkLoginWithDatabese();

    }
    private void checkLoginWithDatabese(){
        btnSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtemail.getText().toString().trim();
                String password = edtpass.getText().toString().trim();
                if (TextUtils.isEmpty(name)){ // set  user
                    edtemail.setError("user khong ton tai");
                    return;
                }
                if (TextUtils.isEmpty(password)){ // set pass
                    edtpass.setError("password khong ton tai");
                    return;
                }
                user = userDB.getInforuser(name ,password);
                assert user != null;
                if (user.getName() != null){
                    // dang nhap thanh cong
                    Intent intent = new Intent(SinginActivity.this,MenuActivity.class);
                    //ham di chuyen tu trang nay sang trang kia
                    Bundle bundle = new Bundle();
                    bundle.putString("Anhtoan", name);
                    bundle.putInt("Accout", user.getId());
                    //ham gui data sang
                    intent.putExtras(bundle);
                    startActivity(intent);// chuyen sang trang moi
                    finish();// khi login roi
                    // khi an nut quay lai
                    // thi se xoa luon trang dang nhap phai vao lai tu dau
                }else {
                    // dang nhap that bai
                    Toast.makeText(SinginActivity.this,"Exit",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
