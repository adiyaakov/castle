package com.example.adi.myapplication.Adapter_package;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.adi.myapplication.Activites.Alcohol_Activity;
import com.example.adi.myapplication.Activites.GoodWithBeer;
import com.example.adi.myapplication.Activites.MainActivity;
import com.example.adi.myapplication.Fragment_all_kind.FireBase_Fragment;
import com.example.adi.myapplication.Fragment_all_kind.Main_Fragment;
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
    public Custom_Base_Adapter(Context context, MenuItem[] items, int layout, int btn, int textView){
        this.context = context;
        this.items = items;
        this.layout = layout;
        this.btn = btn;
        this.textView = textView;
        constructorCalled = 2;
    }

    private static class ViewHolder {
        Button btn;
        TextView textView;

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
            convertView.setTag(viewHolder);


        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //viewHolder.btn.setTextAppearance(R.style.btn_style);
        viewHolder.btn.setBackgroundResource(items[position].getImg_R());
        viewHolder.btn.setTag(position);
        viewHolder.btn.setText(items[position].getItem_name());
        viewHolder.btn.setTextSize(40);

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
                //openNewFragmentCatagory("10", "תפריט פיצות");
                firebaseFragment("pizza","תפריט פיצות");



                break;
            case 1:
                //openNewFragmentCatagory("20", "תפריט סלטים");
                firebaseFragment("salad","תפריט סלטים");


                break;
            case 2:
                //openNewFragmentCatagory("30","תפריט כריכים");
                firebaseFragment("sandwitch","תפריט כריכים");


                break;
            case 3:
                /*Intent i3 = new Intent(context, GoodWithBeer.class);
                context.startActivity(i3);*/
                firebaseFragment("goodwithbeer","הכי טוב עם הבירה");



                break;
            case 4:
                //openNewFragmentCatagory("50", "משקאות קלים");

                firebaseFragment("softdrink","משקאות קלים");


                break;
            case 5:

                //openNewFragmentCatagory("60", "תפריט קינוחים");
                firebaseFragment("cakes","תפריט קינוחים");

                break;
            case 6:
                /*Intent i6 = new Intent(context, Alcohol_Activity.class);
                context.startActivity(i6);*/
                firebaseFragment("alcohol","תפריט אלכוהול");
                break;
            case 7:
                //openNewFragmentCatagory("80", "תפריט קוקטיילים");
                firebaseFragment("cocktails","תפריט קוקטיילים");
                break;
            case 8:
                firebaseFragment("sales","תפריט מבצעים");


                break;


        }


    }
    public void openNewFragmentCatagory(String catagory_number_in_db, String title){


        Main_Fragment fragment = new Main_Fragment();

        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        bundle.putString("CATAGORY", catagory_number_in_db);
        bundle.putInt("OUT",1);
        fragment.setArguments(bundle);
        ((MainActivity)context).getFragmentManager().beginTransaction()
                .replace(R.id.activity_main_list_container, fragment)
                .commit();
    }
    public void firebaseFragment(String path, String title){
        Bundle b = new Bundle();
        b.putString("Path",path );
        b.putString("TITLE", title);
        FireBase_Fragment fragment = new FireBase_Fragment();
        fragment.setArguments(b);
        ((MainActivity)context).getFragmentManager().beginTransaction()
                .replace(R.id.activity_main_list_container, fragment)
                .commit();

    }

    }

