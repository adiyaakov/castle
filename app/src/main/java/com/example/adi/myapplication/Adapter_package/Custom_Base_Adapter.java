package com.example.adi.myapplication.Adapter_package;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.adi.myapplication.Activites.CardView_activity;
import com.example.adi.myapplication.Activites.MainActivity;
import com.example.adi.myapplication.Activites.NavigationDrawer_Activity;
import com.example.adi.myapplication.Fragment_all_kind.FireBase_Fragment;
import com.example.adi.myapplication.Item_strqture.MenuItem;
import com.example.adi.myapplication.R;

/**
 * Created by Adi on 21/07/2016.
 */
public class Custom_Base_Adapter extends BaseAdapter {

    private Context context;
    private MenuItem[] items;
    private int layout;
    private int btn;
    private int textView ;
    private int constructorCalled;


    public Custom_Base_Adapter(Context context, MenuItem[] items, int layout, int btn){
        this.context = context;
        this.items = items;
        this.layout = layout;
        this.btn = btn;
        constructorCalled = 1;


    }


    private static class ViewHolder {
        Button btn;
        TextView textView;
        TextView title;

    }

    @Override
    public int getCount() {
        int s = items.length;
        return s;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(layout, null, false);
            viewHolder.btn = (Button)convertView.findViewById(btn);
            viewHolder.textView = (TextView)convertView.findViewById(textView);
            viewHolder.title = (TextView)convertView.findViewById(R.id.grid_item_title);
            convertView.setTag(viewHolder);


        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.btn.setBackgroundResource(items[position].getImg_R());
        viewHolder.btn.setTag(position);
        //viewHolder.btn.setText(items[position].getItem_name());
        //viewHolder.btn.setTextSize(40);

        viewHolder.title.setText(items[position].getItem_name());



        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int btn_tag = Integer.parseInt(view.getTag().toString());
                if(constructorCalled == 1){
                   onMainActivityBtnClick(position);




                }
            }
        });


        return convertView;
    }
    private void onMainActivityBtnClick(int position){
        switch (position){
            case 0:

                openCardActivity(R.string.path_pizza,R.string.menu_pizza);

                break;
            case 1:

                openCardActivity(R.string.path_salad,R.string.menu_salad);


                break;
            case 2:

                openCardActivity(R.string.path_sandwitch,R.string.menu_sandwitch);


                break;
            case 3:

                openCardActivity(R.string.path_nextTObeer,R.string.menu_nextTObeer);

                break;
            case 4:
                openCardActivity(R.string.path_softDrink,R.string.menu_softDrink);


                break;
            case 5:

                openCardActivity(R.string.path_cakes,R.string.menu_cakes);

                break;
            case 6:

                openCardActivity(R.string.path_alcohol,R.string.menu_alchol);

                break;
            case 7:

                openCardActivity(R.string.path_cooktails,R.string.menu_cocktails);
                break;
            case 8:
                //firebaseFragment("sales","תפריט מבצעים");



                break;


        }


    }


    private void openCardActivity(int fire_base_path, int headerTitle){

        ((MainActivity)context).countDownTimer.cancel();
        ((MainActivity)context).counter = 30;
        ((MainActivity)context).startTimerScreenSaverMethod();

        Intent i = new Intent(context, NavigationDrawer_Activity.class);
        i.putExtra("PATH", fire_base_path);
        i.putExtra("HEADER", headerTitle);
        ((MainActivity)(context)).startActivity(i);
    }

    }

