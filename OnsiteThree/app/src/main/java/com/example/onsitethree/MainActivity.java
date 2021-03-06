package com.example.onsitethree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView pradeepLogo;
    private ProgressBar indexProgress;
    private CountDownTimer countDownTimer ;
    private int count = 0;
    private static final int CODE = 1000;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView ( R.layout.activity_main );

        if (ContextCompat.checkSelfPermission ( getApplicationContext (), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions ( MainActivity.this ,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},CODE );
        }

        initialization();

        countDownTimer = new CountDownTimer (5000,10) {
            @Override
            public void onTick( long millisUntilFinished ) {
                count += 10;
                indexProgress.setProgress ( count );
            }

            @Override
            public void onFinish() {
                indexProgress.setVisibility ( View.GONE );
                Intent intent = new Intent ( getApplicationContext (),MainActivity2.class );
                startActivity ( intent );
                finish ();
                countDownTimer.cancel ();
            }
        };

        countDownTimer.start ();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CODE:
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText ( getApplicationContext (),"Permission granted",Toast.LENGTH_SHORT ).show ();
                    }
                }
                break;
        }
    }

    private void initialization() {
        pradeepLogo = findViewById ( R.id.pradeep_logo );
        setAlphaAnimation ( pradeepLogo );

        indexProgress = findViewById ( R.id.index_progress );
        indexProgress.setMax ( 2500 );
    }

    public static void setAlphaAnimation(View view) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", .3f, 1f);
        fadeIn.setDuration(1000);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view,"scaleY", 1f, 1.3f);
        scaleY.setDuration(1000);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view,"scaleX", 1f, 1.3f);
        scaleX.setDuration(1000);
        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(view,"scaleY", 1.3f, 1f);
        scaleY2.setDuration(1000);
        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(view,"scaleX", 1.3f, 1f);
        scaleX2.setDuration(1000);

        final AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(scaleX,scaleY,fadeIn);

        final AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(scaleX2,scaleY2);

        animatorSet1.addListener(new AnimatorListenerAdapter () {
            @Override
            public void onAnimationEnd( Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet2.start();
            }
        });
        animatorSet1.start();
    }

    @Override
    protected void onPause() {
        super.onPause ();
        overridePendingTransition ( android.R.anim.fade_in,android.R.anim.fade_out );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ();
        System.exit ( 0 );
    }
    private void full(){
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onResume() {
        super.onResume ();
        full ();
    }
}