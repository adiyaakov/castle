package com.example.adi.myapplication.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adi.myapplication.R;

public class GoodWithBeer extends AppCompatActivity {
    private ImageView imageView;
    private LinearLayout display;
    private TextView item_name;
    private TextView item_discr;
    private TextView item_price;

    private Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_with_beer);


        item_name = (TextView) findViewById(R.id.GWB_itemtitle);
        item_discr = (TextView) findViewById(R.id.GWB_itemdiscriptin);
        item_price = (TextView) findViewById(R.id.GWB_price);
        display = (LinearLayout) findViewById(R.id.GWB_displayImage);
        imageView = (ImageView) findViewById(R.id.GVB_imageView);
    }


    public void onBtnClicked(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        switch (tag) {
            case 0:
                imageView.setImageResource(R.drawable.adamama);
                break;
            case 1:
                imageView.setImageResource(R.drawable.natoss);
                item_name.setText("נאצוס");
                item_discr.setText("שברי טורטיה מטוגנים עם טיבול ביתי");
                item_price.setText("35");
                break;
            case 2:
                imageView.setImageResource(R.drawable.hamotihabit);
                item_name.setText("חמוצי הבית");
                item_discr.setText("חמוצים במתכון מיוחד");
                item_price.setText("35");
                break;
            case 3:
                imageView.setImageResource(R.drawable.meshabacha);
                item_name.setText("מסבחה");
                item_discr.setText("מסבחה לבנונית מוגשת על מצע לחם הבית");
                item_price.setText("31");
                break;
            case 4:
                imageView.setImageResource(R.drawable.adamama);
                item_name.setText("נאצוס");
                item_discr.setText("שברי טורטיה מטוגנים עם טיבול ביתי");
                item_price.setText("35");
                break;
        }
    }
}
