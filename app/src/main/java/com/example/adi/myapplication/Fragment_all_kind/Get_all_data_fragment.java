package com.example.adi.myapplication.Fragment_all_kind;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adi.myapplication.Activites.MainActivity;
import com.example.adi.myapplication.Adapter_package.Alcohol_curser_Adapter;
import com.example.adi.myapplication.DataOpenHelper;
import com.example.adi.myapplication.R;

/**
 * Created by Adi on 13/08/2016.
 */
public class Get_all_data_fragment extends Fragment {
    private ListView listView;
    private Cursor cursor;
    private SQLiteDatabase db;
    private Activity activity;
    private Alcohol_curser_Adapter adapter = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.list_layout,container,false);
        Button back_btn = (Button)v.findViewById(R.id.back_to_MA);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_main_list_container, new GridFragment())
                        .commit();

                ((MainActivity)activity).enableView();

            }
        });
        listView = (ListView)v.findViewById(R.id.list_layout_listview);
        final DataOpenHelper helper = new DataOpenHelper(getActivity());
        db = helper.getWritableDatabase();

        getCursorData(cursor,db);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int possintion, long id) {
                Changes_dialog_fragment dialog = new Changes_dialog_fragment();
                Bundle bundle = new Bundle();
                bundle.putLong("LONG",id);
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(),"tag");


                String s = id+"";

                //helper.deleteDataRow(s);
                Toast.makeText(getActivity(), "מוצר זה נמחק ולא ניתן להחזירו", Toast.LENGTH_SHORT).show();

                return false;
            }

        });

        return v;
    }
    //// TODO: 29/08/2016
    public void updateList(){
        adapter.changeCursor(db.query(DataOpenHelper.TABLE_ITEMS,
                null,null,null,null,null,null));
        adapter.notifyDataSetChanged();
    }

    private void getCursorData(Cursor cursor, SQLiteDatabase db){
        cursor = db.query(DataOpenHelper.TABLE_ITEMS,null,null,null,null,null,null,null);
        adapter = new Alcohol_curser_Adapter(getActivity(),cursor,124);
        listView.setAdapter(adapter);
        Toast.makeText(getActivity(), "מנהל יקר שים לב, לחיצה ארוכה על שורה תאפשר לבצע שינויים במוצר", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }
}
