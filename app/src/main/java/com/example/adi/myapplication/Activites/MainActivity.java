package com.example.adi.myapplication.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.adi.myapplication.Fragment_all_kind.AddItemDialog;
import com.example.adi.myapplication.Fragment_all_kind.GridFragment;
import com.example.adi.myapplication.Fragment_all_kind.Login_dialog_fragment;
import com.example.adi.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends Activity {



    public static boolean login = false;
    public static Button manager_btn;
    private Animation animation;

    public static int counter = 30;
    public static CountDownTimer countDownTimer;
    public static int hullTimeCounter = 10*1000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        int a = getIntent().getIntExtra("I",-100);
        if (a == -100) {
            Login_dialog_fragment dialog = new Login_dialog_fragment();
            dialog.show(getFragmentManager(), "");
        }


        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //startTimerScreenSaverMethod();

        manager_btn = (Button)findViewById(R.id.MA_btn_manager);
        manager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NavigationDrawer_Activity.class);
                startActivity(i);
            }
        });


        FrameLayout frame = (FrameLayout) findViewById(R.id.activity_main_list_container);


        animation = AnimationUtils.loadAnimation(this, R.anim.fadein);


    }

    public void  startTimerScreenSaverMethod(){
        countDownTimer = new CountDownTimer(hullTimeCounter,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                counter= (int) millisUntilFinished/1000;
                //Toast.makeText(MainActivity.this, counter+"", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onTick: "+counter);

            }

            @Override
            public void onFinish() {
                //Toast.makeText(MainActivity.this, "DONE COUNTING ", Toast.LENGTH_SHORT).show();
                finish();
                Intent i = new Intent(MainActivity.this,ScreenSaverActivity.class);
                startActivity(i);
            }
        }.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        createHomeNavigator();
    }




    public void createHomeNavigator(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_list_container, new GridFragment())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        login=false;
    }

    public static void onTouchEventOnActivity(Activity activity){
        /*
        countDownTimer.cancel();
        hullTimeCounter=10*1000;
        countDownTimer.start();

        if (counter==0 ){

            activity.finish();
        }
        */
    }






}

