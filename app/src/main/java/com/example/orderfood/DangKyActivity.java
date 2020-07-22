package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.orderfood.DAO.NhanVienDAO;
import com.example.orderfood.DTO.NhanVienDTO;
import com.example.orderfood.Database.CreateDatabase;
import com.example.orderfood.FragmentApp.DatePickerFragment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText edTenDangNhapDK,edMatKhauDK,edNgaySinhDK,edCMNDDK;
    Button btnDongYDK,btnThoatDK;
    RadioGroup grGioiTinh;
    String  sGioiTinh;
    NhanVienDAO nhanVienDAO;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

//        CreateDatabase createDatabase = new CreateDatabase(this);
//        createDatabase.open();

        edTenDangNhapDK = findViewById(R.id.edTenDangNhanDK);
        edMatKhauDK     = findViewById(R.id.edMatKhauDK);
        edNgaySinhDK    =  findViewById(R.id.edNgaySinhDK);
        edCMNDDK        =   findViewById(R.id.edCMNDDK);
        btnDongYDK      =   findViewById(R.id.btnDongYDK);
        btnThoatDK      = findViewById(R.id.btnThoatDK);
        grGioiTinh      =   findViewById(R.id.grGioiTinh);

        btnDongYDK.setOnClickListener(this);
        btnThoatDK.setOnClickListener(this);
        edNgaySinhDK.setOnFocusChangeListener(this);
        nhanVienDAO = new NhanVienDAO(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDongYDK:
            String sTenDangNhap = edTenDangNhapDK.getText().toString();
            String sMatKhau     =   edMatKhauDK.getText().toString();

            switch (grGioiTinh.getCheckedRadioButtonId()){
                case R.id.rdNam:
                    sGioiTinh="Nam";
                    break;

                case R.id.rdNu:
                    sGioiTinh="Nu";
                    break;
            }
            String snNgaySinh = edNgaySinhDK.getText().toString();
            int sCMND   =   Integer.parseInt(edCMNDDK.getText().toString());
            if (sTenDangNhap==null|| sTenDangNhap.equals("")){
                Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loinhaptendangnhap),Toast.LENGTH_SHORT).show();
            }else if (sMatKhau==null || sMatKhau.equals("")){
                Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loinhapmatkhau),Toast.LENGTH_SHORT).show();
            }else {
                NhanVienDTO nhanVienDTO= new NhanVienDTO();
                nhanVienDTO.setTENDN(sTenDangNhap);
                nhanVienDTO.setMATKHAU(sMatKhau);
                nhanVienDTO.setCMND(sCMND);
                nhanVienDTO.setNGAYSINH(snNgaySinh);
                nhanVienDTO.setGIOITINH(sGioiTinh);
                long kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
                if (kiemtra != 0  ){
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();

                }
            }

            ;break;
            case R.id.btnThoatDK:
                finish();break;
        }

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id  = v.getId();
        switch (id){
            case R.id.edNgaySinhDK:
                if (hasFocus){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(),"Ngày sinh");
                    //xuat poop nagysinh
                }
        }

    }
}
