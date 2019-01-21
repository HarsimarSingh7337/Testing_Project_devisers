package deviser.com.testing_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Asking_Multiple_Permissions extends AppCompatActivity {

    TextView message;
    ArrayList<String> missedPermissions = new ArrayList<>();
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asking__multiple__permissions);

        message=findViewById(R.id.message);

        if(ContextCompat.checkSelfPermission(Asking_Multiple_Permissions.this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},101);
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},102);
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},103);
        }

        /*View v = LayoutInflater.from(Asking_Multiple_Permissions.this).inflate(R.layout.progress_bar_spinner,null);
        TextView msz = v.findViewById(R.id.progresbar_text);
        msz.setText("Please wait...");
        AlertDialog.Builder ab=new AlertDialog.Builder(Asking_Multiple_Permissions.this);
        ab.setView(v);
        ab.setCancelable(false);
        alertDialog = ab.create();
        alertDialog.show();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(alertDialog!=null && alertDialog.isShowing() ){
                    alertDialog.dismiss();
                    message.setText("Dialog Closed");
                }
            }
        },5000);*/
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        StringBuilder sb=new StringBuilder();
        StringBuilder sb1=new StringBuilder();

        sb.append("Granted Permissions:");
        sb.append("\n");

        sb.append("Not Granted Permissions:");
        sb.append("\n");

        switch (requestCode) {

            case 101:

                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        sb.append(" * "+permissions[0]+",");
                        sb.append("\n");
                }
                else{
                    sb1.append(" - "+permissions[0]+",");
                    sb1.append("\n");
                }
                break;
        }

        AlertDialog.Builder ab=new AlertDialog.Builder(Asking_Multiple_Permissions.this);
        ab.setTitle("Response");
        ab.setMessage(sb.toString()+"\n"+sb1.toString());
        ab.setCancelable(false);
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ab.show();

    }

}
