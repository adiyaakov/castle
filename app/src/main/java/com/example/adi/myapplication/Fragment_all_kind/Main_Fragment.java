package com.example.adi.myapplication.Fragment_all_kind;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adi.myapplication.Adapter_package.Curser_Adapter;
import com.example.adi.myapplication.DataOpenHelper;
import com.example.adi.myapplication.R;

/**
 * Created by Adi on 28/08/2016.
 */
public class Main_Fragment extends Fragment {
    private SQLiteDatabase db;
    private Activity activity;
    private Curser_Adapter adapter;
    private ListView listView;
    private Context context;
    private Button manager_btn;
    private Cursor cursor;
    private String getTag = "";
    private Animation anim;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        context = getActivity();
        manager_btn = (Button)v.findViewById(R.id.main_fragment_manager_btn);
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
        TextView title = (TextView)v.findViewById(R.id.main_fragment_header);
        title.setText(getArguments().getString("TITLE"));

        title.setAnimation(anim);
        final int out = getArguments().getInt("OUT");

        Animation a = AnimationUtils.loadAnimation(getActivity(),R.anim.movment);
        title.setAnimation(a);

        final String catagory = getArguments().getString("CATAGORY");

        listView = (ListView) v.findViewById(R.id.main_fragment_listview);

        final DataOpenHelper helper = new DataOpenHelper(getActivity());
        db = helper.getWritableDatabase();

        if (out == 1){
            getCursorData(cursor,db, catagory);
        }
        else if (out == 2){
            manager_btn.setVisibility(View.INVISIBLE);
            getCursorData2(cursor,db, catagory);

        }





        manager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Fragment add_item_fragment = new Dialog_Fragment();
                add_item_fragment.setOnItemDoneListener(Main_Fragment.this);
                Bundle bundle = new Bundle();
                bundle.putString("CATAGORY_NUMBER",catagory);
                add_item_fragment.setArguments(bundle);
                add_item_fragment.show(getFragmentManager(),"");



            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (out==2) {//manager fragment is OPEN == 2
                    Dialog_Fragment dialog_fragment = new Dialog_Fragment();
                    Bundle bundle = new Bundle();

                    dialog_fragment.show(getFragmentManager(),"EDIT AREA");
                    //helper.deleteDataRow(l+"");
                }
                return false;
            }
        });

        return v;
    }

    public void updateList(){
        adapter.changeCursor(db.query(DataOpenHelper.TABLE_ITEMS,
                null,DataOpenHelper.COLUMN_CATAGORY + "=?",
                new String[]{getTag},null,null,null));
        adapter.notifyDataSetChanged();
    }

    public void getCursorData(Cursor cursor, SQLiteDatabase db, String getTag) {
        int viewLayoutDesine = R.layout.item_list;
        this.getTag = getTag;

          cursor = db.query(DataOpenHelper.TABLE_ITEMS,
          null, DataOpenHelper.COLUMN_CATAGORY + "=?",
          new String[]{getTag}, null, null, null);

        adapter = new Curser_Adapter(getActivity(),cursor,4,viewLayoutDesine);
        listView.setAdapter(adapter);

        listView.setAnimation(anim);


    }

    public void getCursorData2(Cursor cursor, SQLiteDatabase db, String getTag) {
        int viewLayoutDesine = R.layout.item_list;
        this.getTag = getTag;
        Toast.makeText(context, "מנהל יקר לחיצה ארוכה על מוצר תאפשר שינויים במוצר", Toast.LENGTH_SHORT).show();

        cursor = db.query(DataOpenHelper.TABLE_ITEMS,
                null, null,
                null, null, null, null);

        adapter = new Curser_Adapter(getActivity(),cursor,4,viewLayoutDesine);
        listView.setAdapter(adapter);

        listView.setAnimation(anim);




    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

    }
}
