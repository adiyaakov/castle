package com.example.adi.myapplication.Fragment_all_kind;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adi.myapplication.Activites.Alcohol_Activity;
import com.example.adi.myapplication.Activites.MainActivity;
import com.example.adi.myapplication.DataOpenHelper;
import com.example.adi.myapplication.R;


/**
 * Created by Adi on 13/08/2016.
 */
public class Dialog_Fragment extends DialogFragment{
    private Context context;
    //private Activity activity;
    private Main_Fragment fragment;


    EditText nameET, priceET, discriptionET;
    TextView catagoryTV;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(context);

        builder.setTitle("Manager Area");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_add_item, null);

        Bundle mArgs = getArguments();
        final String myValue = mArgs.getString("KEY","-1");
        final String catagoryTag = mArgs.getString("CATAGORY_NUMBER","-2");
        Toast.makeText(context, catagoryTag+"", Toast.LENGTH_SHORT).show();




        nameET = (EditText)view.findViewById(R.id.dialog_fragment_name);
        priceET = (EditText)view.findViewById(R.id.dialog_fragment_price);
        discriptionET = (EditText)view.findViewById(R.id.dialog_fragment_discriptio);
        catagoryTV = (TextView)view.findViewById(R.id.dialog_fragment_catagory);
        catagoryTV.setText("catagory: "+myValue);




        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                String n = nameET.getText().toString();
                String p = priceET.getText().toString();
                String d = discriptionET.getText().toString();



                DataOpenHelper helper = new DataOpenHelper(context);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(DataOpenHelper.COLUMN_NAME,n);
                values.put(DataOpenHelper.COLUMN_DESCRIPTION,d);
                values.put(DataOpenHelper.COLUMN_ITEM_PRICE,p);
                values.put(DataOpenHelper.COLUMN_CATAGORY,catagoryTag);

                db.insert(DataOpenHelper.TABLE_ITEMS,null,values);
                fragment.updateList();

            }
        });
        builder.setView(view);
        return builder.create();
    }

    public void setOnItemDoneListener(Main_Fragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }



}
