package com.example.adi.myapplication.Fragment_all_kind;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adi.myapplication.Activites.CardView_activity;
import com.example.adi.myapplication.Activites.MainActivity;
import com.example.adi.myapplication.Activites.NavigationDrawer_Activity;
import com.example.adi.myapplication.Object;
import com.example.adi.myapplication.R;

/**
 * Created by Adi on 14/09/2016.
 */
public class ListViewOnLongItemClicked extends DialogFragment {
    private Context context;
    private Activity activity;
    private int position = 0;
    private EditText nameET, priceET, discriptionET, imgUrl;
    private FireBase_Fragment fragment;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Manager Area");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_add_item, null);


        nameET = (EditText) view.findViewById(R.id.dialog_fragment_name);
        priceET = (EditText) view.findViewById(R.id.dialog_fragment_price);
        discriptionET = (EditText) view.findViewById(R.id.dialog_fragment_discriptio);
        imgUrl = (EditText) view.findViewById(R.id.dialog_fragment_catagory);
        builder.setView(view);

        position = getArguments().getInt("position");

        final Object obj = NavigationDrawer_Activity.getItemfromFireBaseAdapter(position);
        nameET.setText(obj.getItem_name());
        priceET.setText(obj.getItem_price());
        imgUrl.setText(obj.getImgURL());
        discriptionET.setText(obj.getItem_description());


        builder.setPositiveButton("שמור שינויים", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Object newObj = new Object(discriptionET.getText().toString().trim(),
                        nameET.getText().toString().trim(),
                        imgUrl.getText().toString().trim(),
                        priceET.getText().toString().trim());
                NavigationDrawer_Activity.updateFireBaseObj(position, newObj);
            }
        });
        builder.setNeutralButton("אל תבצע כל שינוי", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        builder.setNegativeButton("מחק מוצר", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NavigationDrawer_Activity.removeItemFromFireBase(position);


            }
        });



        return builder.create();

    }

    public void callEditItemMethod(FireBase_Fragment fireBase_fragment){
        this.fragment = fireBase_fragment;


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
        this.activity = activity;

    }

    public void onResume() {
        super.onResume();

        getDialog().setCanceledOnTouchOutside(false);
        Window window = getDialog().getWindow();
        window.setLayout(600, 450);
        window.setGravity(Gravity.TOP);
    }
}

