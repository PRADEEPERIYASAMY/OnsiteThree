package com.example.onsitethree.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onsitethree.MainActivity2;
import com.example.onsitethree.R;
import com.example.onsitethree.StorageData.Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.MyViewHolder> {

    private List<Model> fileModel;
    private static int count = 1;

    public FileAdapter( List<Model> fileModel ) {
        this.fileModel = fileModel;
    }

    @NonNull
    @Override
    public FileAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.file_item,parent,false );
        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder( @NonNull FileAdapter.MyViewHolder holder , int position ) {
        holder.name.setText ( fileModel.get ( position ).getName () );
        holder.size.setText ( fileModel.get ( position ).getItems ()+" items" );
        holder.date.setText ( fileModel.get ( position ).getDate () );
        if (fileModel.get ( position ).getType ().equals ( "image" )){
            holder.icon.setImageResource ( R.drawable.image );
        }
        else if (fileModel.get ( position ).getType ().equals ( "pdf" )){
            holder.icon.setImageResource ( R.drawable.pdf );
        }
        else if (fileModel.get ( position ).getType ().equals ( "ppt" )){
            holder.icon.setImageResource ( R.drawable.ppt );
        }
        else if (fileModel.get ( position ).getType ().equals ( "video" )){
            holder.icon.setImageResource ( R.drawable.video );
        }
        else if (fileModel.get ( position ).getType ().equals ( "audio" )){
            holder.icon.setImageResource ( R.drawable.music );
        }
        else if (fileModel.get ( position ).getType ().equals ( "docx" )){
            holder.icon.setImageResource ( R.drawable.doccument );
        }
        else if (fileModel.get ( position ).getType ().equals ( "dir" )){
            holder.icon.setImageResource ( R.drawable.left );
        }
        else if (fileModel.get ( position ).getType ().equals ( "apk" )){
            holder.icon.setImageResource ( R.drawable.apk );
        }
        else {
            holder.icon.setImageResource ( R.drawable.unknown );
        }

        if (count == 1){
            holder.relativeLayout.setBackgroundResource ( R.drawable.bordertwo );
            count = 2;
        }
        else if (count == 2){
            holder.relativeLayout.setBackgroundResource ( R.drawable.border );
            count = 3;
        }
        else if (count == 3){
            holder.relativeLayout.setBackgroundResource ( R.drawable.button_back );
            count = 4;
        }
        else if (count == 4){
            holder.relativeLayout.setBackgroundResource ( R.drawable.buttonback3 );
            count = 5;
        }
        else if (count == 5){
            holder.relativeLayout.setBackgroundResource ( R.drawable.buttonback4 );
            count = 6;
        }
        else if (count == 6){
            holder.relativeLayout.setBackgroundResource ( R.drawable.buttonback6 );
            count = 1;
        }
    }

    @Override
    public int getItemCount() {
        return fileModel.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,size,date;
        RecyclerView recyclerView ;
        private List<Model> filesList = new ArrayList<> (  );
        ImageView icon;
        RelativeLayout relativeLayout;

        public MyViewHolder( @NonNull final View itemView ) {
            super ( itemView );
            name = itemView.findViewById ( R.id.name );
            date = itemView.findViewById ( R.id.date );
            size = itemView.findViewById ( R.id.size );
            recyclerView = itemView.findViewById ( R.id.item_recycler );
            icon = itemView.findViewById ( R.id.icon );
            relativeLayout = itemView.findViewById ( R.id.relativelayout );


            itemView.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {

                    if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "image" )){
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        File viewer = new File(fileModel.get ( getLayoutPosition () ).getPath ());
                        intent.setDataAndType( Uri.fromFile(viewer), "image/*");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        itemView.getContext ().startActivity(intent);
                    }
                    else if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "pdf" )){
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        File viewer = new File(fileModel.get ( getLayoutPosition () ).getPath ());
                        intent.setDataAndType( Uri.fromFile(viewer), "application/pdf");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        itemView.getContext ().startActivity(intent);
                    }
                    else if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "ppt" )){
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        File viewer = new File(fileModel.get ( getLayoutPosition () ).getPath ());
                        intent.setDataAndType( Uri.fromFile(viewer), "application/vnd.ms-powerpoint");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        itemView.getContext ().startActivity(intent);
                    }
                    else if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "video" )){
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        File viewer = new File(fileModel.get ( getLayoutPosition () ).getPath ());
                        intent.setDataAndType( Uri.fromFile(viewer), "video/*");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        itemView.getContext ().startActivity(intent);
                    }
                    else if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "audio" )){
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        File viewer = new File(fileModel.get ( getLayoutPosition () ).getPath ());
                        intent.setDataAndType( Uri.fromFile(viewer), "audio/*");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        itemView.getContext ().startActivity(intent);
                    }
                    else if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "docx" )){
                        Intent intent = new Intent();
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        File viewer = new File(fileModel.get ( getLayoutPosition () ).getPath ());
                        intent.setDataAndType( Uri.fromFile(viewer), "application/msword");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        itemView.getContext ().startActivity(intent);
                    }
                    else if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "dir" )){
                        File sub = new File ( fileModel.get ( getAdapterPosition () ).getPath () );
                        if (sub.isDirectory () && !fileModel.get ( getAdapterPosition () ).getClicked ()){
                            ListDir ( sub );
                            fileModel.get ( getAdapterPosition () ).setClicked ( true );
                            recyclerView.setLayoutManager ( new LinearLayoutManager ( itemView.getContext () ) );
                            recyclerView.setHasFixedSize ( true );
                            recyclerView.setAdapter ( new FileAdapter ( filesList ) );
                            recyclerView.setVisibility ( View.VISIBLE );
                            if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "dir" )){
                                icon.setImageResource ( R.drawable.down );
                            }
                        }
                        else if (fileModel.get ( getAdapterPosition () ).getClicked ()){
                            recyclerView.setVisibility ( View.GONE );
                            fileModel.get ( getAdapterPosition () ).setClicked ( false );
                            if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "dir" )){
                                icon.setImageResource ( R.drawable.left );
                            }
                        }
                    }
                    else if (fileModel.get ( getAdapterPosition () ).getType ().equals ( "apk" )){
                        Toast.makeText ( itemView.getContext (),"Its a apk file",Toast.LENGTH_SHORT ).show ();
                    }
                    else {
                        Toast.makeText ( itemView.getContext (),"not supported formet",Toast.LENGTH_SHORT ).show ();
                    }


                }
            } );
        }

        void ListDir( File f){
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
                            model.setItems ( String.valueOf ( 0) ) ;
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
    }
}
