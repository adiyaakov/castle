package com.example.adi.myapplication.Adapter_package;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.adi.myapplication.DataOpenHelper;
import com.example.adi.myapplication.R;

/**
 * Created by Adi on 13/08/2016.
 */
public class Alcohol_curser_Adapter extends CursorAdapter {
    public Alcohol_curser_Adapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.alcohol_custom_adapter,viewGroup,false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name =(TextView) view.findViewById(R.id.ACAdapter_itemname);
        TextView price =(TextView) view.findViewById(R.id.ACAdapter_itemprice);

        name.setText(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_NAME)));
        price.setText(cursor.getString(cursor.getColumnIndex(DataOpenHelper.COLUMN_ITEM_PRICE)));

    }
}
