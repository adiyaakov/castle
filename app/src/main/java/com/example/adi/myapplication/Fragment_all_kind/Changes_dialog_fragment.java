package com.example.adi.myapplication.Fragment_all_kind;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import com.example.adi.myapplication.DataOpenHelper;
import com.example.adi.myapplication.R;

/**
 * Created by Adi on 15/08/2016.
 */
public class Changes_dialog_fragment extends DialogFragment {
    private Context context;
    private Get_all_data_fragment fragment;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final long l = getArguments().getLong("LONG",-10);
        Toast.makeText(context, ""+l, Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder builder  = new AlertDialog.Builder(context);

        builder.setTitle("שינוי הגדרות מוצר");
        builder.setMessage("שים לב, כל שינוי שיתבצע ישפיע מיידית על התפריט");
        builder.setIcon(R.drawable.manager);
        builder.setIcon(android.R.drawable.ic_dialog_map);
        builder.setNegativeButton("אל תבצע כל שינוי", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        DataOpenHelper helper = new DataOpenHelper(context);
        final SQLiteDatabase db = helper.getWritableDatabase();

        builder.setPositiveButton("מחק מוצר", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.delete(DataOpenHelper.TABLE_ITEMS,DataOpenHelper.COLUMN_ID + "=?",new String[]{l+""});
                fragment.updateList();

            }
        });
        builder.setNegativeButton("שמור שינויים", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //fragment.updateList();
        return builder.create();


    }

    public void setOnItemDoneListener(Get_all_data_fragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context=activity;
    }
}
