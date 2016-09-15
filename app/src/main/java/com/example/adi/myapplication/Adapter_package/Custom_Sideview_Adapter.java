package com.example.adi.myapplication.Adapter_package;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.adi.myapplication.Item_strqture.Catagory;
import com.example.adi.myapplication.R;

/**
 * Created by Adi on 23/07/2016.
 */
public class Custom_Sideview_Adapter extends ArrayAdapter<Catagory> {
    private Context context;
    private Catagory[] catagories;



    public Custom_Sideview_Adapter(Context context, Catagory[] catagories){
        super(context, R.layout.catagory_desine, catagories);
        this.context = context;
        this.catagories = catagories;
    }

    private class ViewHolder {
        TextView catagory_name;
        Button btn_image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {  // recycle the views!!!
            Log.d("Maayan", "new");
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.catagory_desine, null);
            viewHolder.catagory_name = (TextView) convertView.findViewById(R.id.catagory_desine_name);
            viewHolder.btn_image = (Button) convertView.findViewById(R.id.catagory_desine_imageBtn);
            convertView.setTag(viewHolder);
        }else{
            Log.d("Maayan", "recycled");
            viewHolder =(ViewHolder) convertView.getTag();
        }
        viewHolder.catagory_name.setText(catagories[position].getCatagory_name());
        viewHolder.btn_image.setBackgroundResource(catagories[position].getImg_int());
        return convertView;
    }
}
