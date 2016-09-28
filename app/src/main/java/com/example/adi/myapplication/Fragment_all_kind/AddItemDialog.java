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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adi.myapplication.Activites.CardView_activity;
import com.example.adi.myapplication.Activites.NavigationDrawer_Activity;
import com.example.adi.myapplication.Object;
import com.example.adi.myapplication.R;


/**
 * Created by Adi on 14/09/2016.
 */
public class AddItemDialog extends DialogFragment{

    private Context context;
    private Activity activity;
    private FireBase_Fragment fragment;
    private EditText nameET, priceET, discriptionET, imgUrl;
    private boolean ifEmpty;




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(context);


        builder.setTitle("Manager Area");

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_add_item, null);

        nameET = (EditText)view.findViewById(R.id.dialog_fragment_name);
        priceET = (EditText)view.findViewById(R.id.dialog_fragment_price);
        discriptionET = (EditText)view.findViewById(R.id.dialog_fragment_discriptio);
        imgUrl = (EditText)view.findViewById(R.id.dialog_fragment_catagory);



        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                String n = nameET.getText().toString().trim();
                String p = priceET.getText().toString().trim();
                String d = discriptionET.getText().toString().trim();
                String url = imgUrl.getText().toString().trim();

                checkIfNull(n);
                if (ifEmpty){
                    Object object = new Object(d,n,url,p);
                    //fragment.addNewFireBaseItem(object);
                    ((NavigationDrawer_Activity)(activity)).addNewFireBaseItem(object);

                }



                    }
        });


        builder.setView(view);
        return builder.create();
    }
    public void callAddItemMethod(FireBase_Fragment fireBase_fragment){
        this.fragment = fireBase_fragment;


    }

    public boolean checkIfNull(String editText){
        if (editText.equals("")){
            nameET.setError("error");
            ifEmpty=false;

            return ifEmpty;
        }
        ifEmpty=true;
        return ifEmpty;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
        this.activity = activity;
    }

    public void onResume() {
        super.onResume();

        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(600, 400);
        window.setGravity(Gravity.TOP);

        getDialog().setCanceledOnTouchOutside(false);
    }

}
