package com.example.adi.myapplication.Adapter_package;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adi.myapplication.DataOpenHelper;
import com.example.adi.myapplication.R;

/**
 * Created by Adi on 28/08/2016.
 */
public class Curser_Adapter extends CursorAdapter {
    private int viewLayoutDesine;

    public Curser_Adapter(Context context, Cursor c, int flags,int viewLayoutDesine) {
        super(context, c, flags);
        this.viewLayoutDesine = viewLayoutDesine;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(viewLayoutDesine,viewGroup,false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView item_price = (TextView) view.findViewById(R.id.item_list_samegreg_price_of_item);
        TextView item_description = (TextView)view.findViewById(R.id.item_list_samegreg_discription_of_item);
        TextView item_name = (TextView)view.findViewById(R.id.item_list_samegreg_name_of_item);
        ImageView item_img = (ImageView)view.findViewById(R.id.item_list_samegreg_iv);

        item_price.setText(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_ITEM_PRICE)));
        item_description.setText(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_DESCRIPTION)));
        item_name.setText(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_NAME)));






    }
}
