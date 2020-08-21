package com.example.onsitethree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onsitethree.Adapter.FileAdapter;
import com.example.onsitethree.StorageData.Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Button storage;
    private List<Model> filesList = new ArrayList<> (  );
    private RecyclerView fileRecycler;
    private MediaPlayer mediaPlayer1,mediaPlayer2;
    private static String used;

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        setContentView ( R.layout.activity_main2 );

        mediaPlayer1 = new MediaPlayer ().create(getApplicationContext (),R.raw.negative);
        mediaPlayer2 = new MediaPlayer ().create(getApplicationContext (),R.raw.rubberone);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());



        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        ListDir(root);
        fileRecycler = findViewById ( R.id.files );
        storage = findViewById ( R.id.storage );

        fileRecycler.setLayoutManager ( new LinearLayoutManager ( getApplicationContext () ) );
        fileRecycler.setHasFixedSize ( true );
        fileRecycler.setAdapter ( new FileAdapter ( filesList ) );

        storage.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick( View v ) {
                mediaPlayer1.start ();
                final AlertDialog.Builder builder = new AlertDialog.Builder ( MainActivity2.this,R.style.CustomDialog );
                View view = LayoutInflater.from ( MainActivity2.this ).inflate ( R.layout.storage_dialog,null,false );

                Button close = view.findViewById ( R.id.info_close );
                TextView infoText = view.findViewById ( R.id.info_text );

                infoText.setText ( "In Percent:\n       Used -- "+String.valueOf (getAvailableSizeInPercent () )+"\n       UnUsed -- "+String.valueOf (100 - getAvailableSizeInPercent () ) +"\n\nIn Size:\n       Available -- "+ getAvailableSizeInMemory ()+"\n       Used -- "+used );

                final AlertDialog alertDialog = builder.create ();
                alertDialog.setView ( view );

                close.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick( View v ) {
                        mediaPlayer2.start ();
                        alertDialog.cancel ();
                        full ();
                    }
                } );

                alertDialog.setCanceledOnTouchOutside ( false );
                alertDialog.show ();
            }
        } );

    }

    void ListDir(File f){
        File[] files=f.listFiles();
        if (files != null){
            filesList.clear();
            for (File file: files){
                SimpleDateFormat currentDate = new SimpleDateFormat("mm/dd/yyyy");
                String date = currentDate.format(file.lastModified ());
                SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
                String time = currentTime.format(file.lastModified ());
                Model model = new Model ();
                model.setName ( file.getName () );
                model.setPath ( file.getAbsolutePath () );
                if (file.getName ().endsWith ( ".gif" ) || file.getName ().endsWith ( ".tif" ) ||file.getName ().endsWith ( ".tiff" ) ||file.getName ().endsWith ( ".png" ) ||file.getName ().endsWith ( ".jpg" ) ||file.getName ().endsWith ( ".jpeg" ) ||file.getName ().endsWith ( ".bmp" ) ||file.getName ().endsWith ( ".esp" ) ||file.getName ().endsWith ( ".raw" ) ||file.getName ().endsWith ( ".cr2" ) ||file.getName ().endsWith ( ".nef" ) ||file.getName ().endsWith ( ".orf" ) ||file.getName ().endsWith ( ".sr2" ) ){
                    model.setType ( "image" );
                }
                else if (file.getName ().endsWith ( ".pdf" )){
                    model.setType ( "pdf" );
                }
                else if (file.getName ().endsWith ( ".ppt" ) || file.getName ().endsWith ( ".pptx" )){
                    model.setType ( "ppt" );
                }
                else if (file.isDirectory ()){
                    model.setType ( "dir" );
                }
                else if (file.getName ().endsWith ( ".mkv" ) ||file.getName ().endsWith ( ".mp4" ) ||file.getName ().endsWith ( ".m4p" ) ||file.getName ().endsWith ( ".mpg" ) ||file.getName ().endsWith ( ".mpeg" ) ||file.getName ().endsWith ( ".3gp" ) ||file.getName ().endsWith ( ".mpe" ) ||file.getName ().endsWith ( ".mpv" ) ||file.getName ().endsWith ( ".mp2" ) ||file.getName ().endsWith ( ".amv" ) ||file.getName ().endsWith ( ".asf" )){
                    model.setType ( "video" );
                }
                else if (file.getName ().endsWith ( ".mp3" ) ||file.getName ().endsWith ( ".acc" ) ||file.getName ().endsWith ( ".ogg" ) ||file.getName ().endsWith ( ".wav" ) ||file.getName ().endsWith ( ".webm" )){
                    model.setType ( "audio" );
                }
                else if (file.getName ().endsWith ( ".docx" )){
                    model.setType ( "docx" );
                }
                else if (file.getName ().endsWith ( ".apk" )){
                    model.setType ( "apk" );
                }
                else {
                    model.setType ( "null" );
                }
                try {
                    if (String.valueOf ( file.listFiles ().length ) == "null"){

                    }
                    else {
                        model.setItems ( String.valueOf ( file.listFiles ().length ) ) ;
                    }
                }
                catch (NullPointerException e){
                    model.setItems ( String.valueOf ( 0) ) ;
                }
                model.setDate ( date+"  "+time );
                model.setClicked ( false );
                filesList.add(model);
            }
        }


    }


    private int getAvailableSizeInPercent() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long totalSize = totalBlocks * blockSize;
        long availableBlocks = stat.getAvailableBlocks();
        long availableSize = availableBlocks * blockSize;
        int size = (int) ((availableSize * 100) / totalSize);
        return 100 - size;
    }

    private String getAvailableSizeInMemory() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();

        String suffix = null;
        long size = availableBlocks * blockSize;
        if (size >= 1024) {
            suffix = "KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MB";
                size /= 1024;
                if (size >= 1024) {
                    suffix = "GB";
                    size /= 1024;
                }
            }
        }
        used = String.valueOf (getAvailableSizeInPercent ()*size);
        StringBuilder stringBuilder = new StringBuilder(Long.toString(size));
        /*int offset = stringBuilder.length() - 3;
        while (offset > 0) {
            stringBuilder.insert(offset, ',');
            offset -= 3;
        }*/

        if (suffix != null) {
            stringBuilder.append(suffix);
            used = used+suffix;
        }
        return stringBuilder.toString();
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
        full ();
        super.onResume ();
    }

    @Override
    public void onBackPressed() {
        exit ();
    }

    @Override
    protected void onPause() {
        super.onPause ();
        overridePendingTransition ( android.R.anim.fade_in,android.R.anim.fade_out );
    }

    private void exit(){
        mediaPlayer1.start ();
        final AlertDialog.Builder builder = new AlertDialog.Builder ( MainActivity2.this,R.style.CustomDialog );
        View view = LayoutInflater.from ( MainActivity2.this ).inflate ( R.layout.dialog,null,false );
        Button yes = view.findViewById ( R.id.yes );
        Button no = view.findViewById ( R.id.no );
        TextView firstText = view.findViewById ( R.id.firstText );
        TextView secondText = view.findViewById ( R.id.secontText );
        ImageView dialogImage = view.findViewById ( R.id.dialog_image );

        firstText.setText ( "Oh noooo !" );
        secondText.setText ( "Wanna move out?" );
        dialogImage.setImageResource ( R.drawable.oh );
        final AlertDialog alertDialog = builder.create ();
        alertDialog.setView ( view );

        no.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick( View v ) {
                full ();
                alertDialog.cancel ();
                mediaPlayer2.start ();
            }
        } );

        yes.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick( View v ) {
                finish ();
                mediaPlayer2.start ();
            }
        } );
        alertDialog.setCanceledOnTouchOutside ( false );
        alertDialog.show ();
    }

}