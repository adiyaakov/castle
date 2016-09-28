package com.example.adi.myapplication.Fragment_all_kind;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adi.myapplication.CircleTransform;
import com.example.adi.myapplication.Object;
import com.example.adi.myapplication.R;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Adi on 12/09/2016.
 */
public class FireBase_Fragment extends Fragment {
    private ListView listView;
    private Context context;
    private Button manager_btn;
    private Animation anim;
    private Activity activity;
    private FirebaseListAdapter<Object> adapter;
    Firebase ref;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_fragment, container, false);

        String path = getArguments().getString("Path");
        TextView title = (TextView)v.findViewById(R.id.main_fragment_header);
        title.setText(getArguments().getString("TITLE"));

        context = getActivity();
        Firebase.setAndroidContext(context);
        manager_btn = (Button) v.findViewById(R.id.main_fragment_manager_btn);
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);




        title.setAnimation(anim);
        Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.movment);
        title.setAnimation(a);

        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startAdapter();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        ref = new Firebase("https://otto-resturant.firebaseio.com/"+path);


        manager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItemDialog addItemDialog = new AddItemDialog();

                addItemDialog.callAddItemMethod(FireBase_Fragment.this);
                addItemDialog.show(getFragmentManager(),"ADD ITEM");


            }
        });


        listView = (ListView) v.findViewById(R.id.main_fragment_listview);

        return v;
    }

    public void startAdapter(){


        adapter = new FirebaseListAdapter<Object>(
                (Activity) context,
                Object.class,
                R.layout.item_list,
                ref
        ) {
            @Override
            protected void populateView(View view, Object chat, int i) {
                ((TextView)view.findViewById(R.id.item_list_samegreg_name_of_item)).setText(chat.getItem_name());
                ((TextView)view.findViewById(R.id.item_list_samegreg_discription_of_item)).setText(chat.getItem_description());
                ((TextView)view.findViewById(R.id.item_list_samegreg_price_of_item)).setText(chat.getItem_price());
                //((ImageView)view.findViewById(R.id.item_list_samegreg_iv)).setImageResource(R.drawable.adamama);

                ImageView iv = (ImageView) view.findViewById(R.id.item_list_samegreg_iv);
                String url = chat.getImgURL();

                Picasso.with(context).load(url).transform(new CircleTransform()).error(R.drawable.cake).into(iv);


            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewOnLongItemClicked dialog = new ListViewOnLongItemClicked();
                dialog.callEditItemMethod(FireBase_Fragment.this);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                dialog.setArguments(bundle);

                dialog.show(getFragmentManager(),"edit dialog");
                return false;


            }
        });

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

    }
    public void updateFireBaseObj(int position, Object updatedObject){
        adapter.getRef(position).setValue(updatedObject);
    }

    public Object getItemfromFireBaseAdapter(int position){
        return adapter.getItem(position);

    }

    public void removeItemFromFireBase(int positionClicked){
        Firebase itemRef = adapter.getRef(positionClicked);
        itemRef.removeValue();

    }

    public void addNewFireBaseItem(Object newObj){

        ref.push().setValue(newObj);
    }


}
