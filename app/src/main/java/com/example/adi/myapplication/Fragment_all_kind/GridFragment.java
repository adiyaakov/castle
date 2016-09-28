package com.example.adi.myapplication.Fragment_all_kind;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.adi.myapplication.Adapter_package.Custom_Base_Adapter;
import com.example.adi.myapplication.Item_strqture.MenuItem;
import com.example.adi.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Adi on 13/08/2016.
 */
public class GridFragment extends Fragment {

    private ArrayList<MenuItem> menuList;
    private MenuItem[] items = {
            new MenuItem("חם מהתנור", R.drawable.pizza,null),
            new MenuItem("סלטים",R.drawable.salad,null),
            new MenuItem("סנדוויצים",R.drawable.sandwitch,null),
            new MenuItem("ליד הבירה",R.drawable.nigovim,null),
            new MenuItem("משקאות",R.drawable.mthim,null),
            new MenuItem("קינוחים",R.drawable.cake,null),
            new MenuItem("אלכוהול",R.drawable.alcohol,null),
            new MenuItem("קוקטיילים",R.drawable.cocktalil,null),
            new MenuItem(null,R.drawable.mithim,null),



    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.grid_layout,container,false);
        GridView gridView = (GridView)v.findViewById(R.id.A_M_gridView);
        menuList = new ArrayList<MenuItem>();
        gridView.setAdapter(new Custom_Base_Adapter(getActivity(), items,R.layout.grid_item,R.id.grid_item_buttom));
        return v;
    }
}
