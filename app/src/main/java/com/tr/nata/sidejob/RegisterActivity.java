package com.tr.nata.sidejob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tr.nata.sidejob.DatabaseHelper.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    Button btn_register;
    private EditText etUserName,etUserEmail,etUserPassword,etUserJenisKelamin, etUserNoTelp, etUserTanggalLahir;
    RadioGroup rgJenisKelamin;
    RadioButton rbjenis_kelamin_Laki,rbjenis_kelamin_Perempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mydb = new DatabaseHelper(this);

        btn_register = findViewById(R.id.btn_register);
        etUserName = findViewById(R.id.etUserName);
        etUserEmail = findViewById(R.id.etUserMail);
        etUserPassword=findViewById(R.id.etUserPassword);
//        etUserJenisKelamin=findViewById(R.id.etUserJenisKelamin);
        etUserNoTelp=findViewById(R.id.etUserNoTelp);
        etUserTanggalLahir=findViewById(R.id.etUserTanggalLahir);

        rgJenisKelamin=(RadioGroup) findViewById(R.id.rg_JenisKelamin);
//        int selectedId = rgJenisKelamin.getCheckedRadioButtonId();
//        rbjenis_kelamin=(RadioButton) findViewById(selectedId);
        rbjenis_kelamin_Laki=findViewById(R.id.rbLakiLaki);
        rbjenis_kelamin_Perempuan=findViewById(R.id.rbPerempuan);

//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"Will be save",Toast.LENGTH_SHORT).show();
//            }
//        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etUserName.getText().toString();
                String email = etUserEmail.getText().toString();
                String password = etUserPassword.getText().toString();
                String jenis_kelamin;
                if (rbjenis_kelamin_Laki.isChecked()){
                    jenis_kelamin = "L";
                }else{
                    jenis_kelamin = "P";
                }
                String no_telp=etUserNoTelp.getText().toString();
                String tanggal_lahir=etUserTanggalLahir.getText().toString();

                boolean isInserted = mydb.insertDataUser(name,email,jenis_kelamin,password,no_telp,tanggal_lahir);
                if (isInserted==true){
                    Toast.makeText(getApplicationContext(),"Berhasil Menyimpan",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Gagal Menyimpan",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
