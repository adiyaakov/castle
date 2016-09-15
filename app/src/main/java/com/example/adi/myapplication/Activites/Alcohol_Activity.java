package com.example.adi.myapplication.Activites;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adi.myapplication.Adapter_package.Alcohol_curser_Adapter;
import com.example.adi.myapplication.DataOpenHelper;
import com.example.adi.myapplication.Fragment_all_kind.Dialog_Fragment;
import com.example.adi.myapplication.R;

public class Alcohol_Activity extends AppCompatActivity{
    private Button addBTN;
    private String btnText;
    private int btnPressTag;

    private TextView headerTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alcohol);
        addBTN = (Button)findViewById(R.id.addBTN);
        addBTN.setVisibility(View.INVISIBLE);

        headerTitle = (TextView)findViewById(R.id.AC_header);
        headerTitle.setPaintFlags(headerTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }


    public void onCATAGORYclick(View view) {
        Button b = (Button)view;
        btnText = b.getText().toString();
        Toast.makeText(Alcohol_Activity.this, ""+btnText, Toast.LENGTH_SHORT).show();

        ListView listView;
        listView = (ListView)findViewById(R.id.AA_listView);
        DataOpenHelper helper = new DataOpenHelper(Alcohol_Activity.this);
        SQLiteDatabase db = helper.getWritableDatabase();


        btnPressTag = Integer.parseInt(view.getTag().toString());
        String getTag = "";
        switch (btnPressTag){
            case 0:
                addBTN.setVisibility(View.VISIBLE);
                getTag = "0";
                break;

            case 1:
                addBTN.setVisibility(View.VISIBLE);
                getTag = "1";
                break;
            case 2:
                addBTN.setVisibility(View.VISIBLE);
                getTag = "2";
                break;

            case 3:
                addBTN.setVisibility(View.VISIBLE);
                getTag = "3";
                break;
            case 4:
                addBTN.setVisibility(View.VISIBLE);
                getTag = "4";
                break;
        }
        Cursor cursor = db.query(DataOpenHelper.TABLE_ITEMS,
                null,DataOpenHelper.COLUMN_CATAGORY + "=?",
                new String[]{getTag},null,null,null);


        Alcohol_curser_Adapter adapter = new Alcohol_curser_Adapter(Alcohol_Activity.this,cursor,125);
        listView.setAdapter(adapter);
        }




    public void onAddBtn(View view) {

        //create dialog add_item_fragment
        Dialog_Fragment fragment = new Dialog_Fragment();

        //create bundle which send catagory button text
        Bundle bundle = new Bundle();
        bundle.putString("KEY",btnText);
        bundle.putString("CATAGORY_NUMBER",btnPressTag+"");


        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "");
    }


    }




