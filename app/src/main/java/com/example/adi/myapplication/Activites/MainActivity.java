package com.example.adi.myapplication.Activites;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.adi.myapplication.Adapter_package.Curser_Adapter;
import com.example.adi.myapplication.DataOpenHelper;
import com.example.adi.myapplication.Fragment_all_kind.Get_all_data_fragment;
import com.example.adi.myapplication.Fragment_all_kind.GridFragment;
import com.example.adi.myapplication.Fragment_all_kind.Main_Fragment;
import com.example.adi.myapplication.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity  {

    private static final String PRICE = "price";
    private static final String DESCRIBE = "describe";

    public boolean toogle = false;
    private Button manager_btn, icon;
    private Button loginBTN;
    private EditText passwordET;

    private Button face_btn = null;

    private SQLiteDatabase db;
    private DataOpenHelper helper;

    private Animation animation;

//    Firebase ref = new Firebase("https://firstfirebase-e7e01.firebaseio.com/chat");



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        passwordET = (EditText)findViewById(R.id.MA_password_editText);
        loginBTN = (Button)findViewById(R.id.MA_loginBTN);
        manager_btn = (Button)findViewById(R.id.MA_btn_manager);
        icon = (Button)findViewById(R.id.AC_icon);
        enableView();

        FrameLayout frame = (FrameLayout)findViewById(R.id.activity_main_list_container);

        helper = new DataOpenHelper(this);

        db = helper.getReadableDatabase();

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(passwordET.getText().toString());
                if (i==1234) {
                    //TODO: CHANGE THE FRAGMENT!!!
                        openNewFragmentCatagory(null,"אזור ניהול תפריט");
                    }
            }
        });

        animation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        //Animation anima = AnimationUtils.loadAnimation(this, R.anim.movment);
        //frame.setAnimation(anima);


        face_btn = (Button)findViewById(R.id.face_btn);


        face_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://www.facebook.com/Otto.Rishon/?fref=ts");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        });


        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager_btn.setVisibility(View.VISIBLE);
                passwordET.setVisibility(View.VISIBLE);
                loginBTN.setVisibility(View.VISIBLE);
            }
        });


    }


    public void enableView(){
        manager_btn.setVisibility(View.INVISIBLE);
        passwordET.setVisibility(View.INVISIBLE);
        loginBTN.setVisibility(View.INVISIBLE);
        passwordET.setText("אנא הקש סיסמא");
    }


    public void manager_area_active(View view) {

        if (passwordET.getText().toString() == "1234") {
            if (toogle = true) {

                Get_all_data_fragment fragment = new Get_all_data_fragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_main_list_container, fragment)
                        .commit();
            }

        }
    }

    public Curser_Adapter getQuary(String getTagOrdered){
        DataOpenHelper helper = new DataOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(DataOpenHelper.TABLE_ITEMS,
                null,DataOpenHelper.COLUMN_CATAGORY + "=?",
                new String[]{getTagOrdered},null,null,null);
        int rid = R.layout.item_list;
        Curser_Adapter adapter = new Curser_Adapter(this,cursor,12345,rid);

        return adapter;
    }

    public SQLiteDatabase getSQL(){
        return helper.getWritableDatabase();

    }

    @Override
    protected void onResume() {
        super.onResume();
        createHomeNavigator();
    }

    @Override
    protected void onStart() {
        super.onStart();
        face_btn.setAnimation(animation);
        icon.setAnimation(animation);


    }

    public void homeClicked(View view) {
        createHomeNavigator();
    }

    public void createHomeNavigator(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_list_container, new GridFragment())
                .commit();
    }

    public void openNewFragmentCatagory(String catagory_number_in_db, String title){


        Main_Fragment fragment = new Main_Fragment();

        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        bundle.putString("CATAGORY", catagory_number_in_db);
        bundle.putInt("OUT",2);
        fragment.setArguments(bundle);
            this.getFragmentManager().beginTransaction()
                .replace(R.id.activity_main_list_container, fragment)
                .commit();
    }
}

