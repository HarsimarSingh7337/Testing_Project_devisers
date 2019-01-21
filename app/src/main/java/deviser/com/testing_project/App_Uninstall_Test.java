package deviser.com.testing_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import androidx.fragment.app.FragmentManager;

public class App_Uninstall_Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app__uninstall__test);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",0);

        try{
            if(sharedPreferences.getBoolean("isBlocked",false)){
            }
        }
        catch(NullPointerException ignored){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isBlocked",false);
            editor.apply();
        }
        try{
            String pass = sharedPreferences.getString("password",null);
                if (pass!=null ){
                    if(sharedPreferences.getBoolean("isBlocked",false)){
                        AlertDialog.Builder ab=new AlertDialog.Builder(App_Uninstall_Test.this);
                        ab.setTitle("User Blocked!!!");
                        ab.setMessage("You have made 3 incorrect attempts. You are blocked to access this Application");
                        ab.setCancelable(false);
                        ab.setNeutralButton("Ok", (dialog, which) -> {
                            dialog.dismiss();
                            finishAffinity();
                        });
                        ab.create().show();
                    }
                    else{
                        FragmentManager fragmentManager=getSupportFragmentManager();
                        AuthorizeUser_Fragment authorizeUser_fragment = new AuthorizeUser_Fragment();
                        authorizeUser_fragment.show(fragmentManager,"");
                    }
                }
                else{
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    Initial_Password_Fragment initial_password_fragment = new Initial_Password_Fragment();
                    initial_password_fragment.show(fragmentManager,"");
                }
        }
        catch(NullPointerException npe){
            FragmentManager fragmentManager=getSupportFragmentManager();
            Initial_Password_Fragment initial_password_fragment = new Initial_Password_Fragment();
            initial_password_fragment.show(fragmentManager,"");
        }
    }

    public void uninstallApp(View c){
        // code to uninstall this app
        Uri packageName = Uri.parse("package:deviser.com.testing_project");
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE);
        uninstallIntent.setData(packageName);
        startActivity(uninstallIntent);
    }

    public void clearCredentials(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("password");
        editor.remove("wrongattempt");
        editor.remove("isBlocked");
        editor.apply();
        finishAffinity();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}