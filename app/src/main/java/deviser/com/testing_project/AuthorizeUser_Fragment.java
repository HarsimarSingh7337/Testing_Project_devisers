package deviser.com.testing_project;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class AuthorizeUser_Fragment extends DialogFragment {

    private SharedPreferences sharedPreferences;

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialogFragment=getDialog();
        dialogFragment.setCancelable(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_authorize_user_, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setTitle("Authorize User");

        TextView attempts = rootView.findViewById(R.id.attempts);
        TextInputEditText password = rootView.findViewById(R.id.authpassword);
        TextInputLayout wrapperPassword = rootView.findViewById(R.id.wrapper_authpassword);
        ImageView button = rootView.findViewById(R.id.btn);

        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("MyPrefs",0);

        if(sharedPreferences.getBoolean("isBlocked",false)){
            password.setFocusable(false);
            password.setEnabled(false);
        }

        try{
             sharedPreferences.getInt("wrongattempt",0);
        }
        catch(NullPointerException ignored){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("wrongattempt",0);
            editor.apply();
        }

        button.setOnClickListener(v -> {
                // wrong attempts performed for 3 times
                //  text field will get disabled
                // and button click will display only app uninstall window
                if(sharedPreferences.getBoolean("isBlocked",false)){
                    AlertDialog.Builder ab=new AlertDialog.Builder(getActivity());
                    ab.setTitle("User Blocked!!!");
                    ab.setMessage("You have made 3 incorrect attempts. You are blocked to access this Application");
                    ab.setCancelable(false);
                    ab.setNeutralButton("Ok", (dialog, which) -> {
                        dialog.dismiss();
                        password.setEnabled(false);
                        password.setFocusable(false);
                        Uri packageName = Uri.parse("package:deviser.com.testing_project");
                        Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                        uninstallIntent.setData(packageName);
                        startActivity(uninstallIntent);
                    });
                    ab.create().show();
                }
                else{
                        //normal button click code below
                    if(password.getText().toString().trim().length()==0){
                        wrapperPassword.setError("Invalid Field");
                    }
                    else{
                        String pass = sharedPreferences.getString("password",null);
                        if(password.getText().toString().equals(pass)){
                            getDialog().dismiss();
                        }
                        else{
                            AlertDialog.Builder ab=new AlertDialog.Builder(getActivity());
                            ab.setCancelable(false);
                            View view = LayoutInflater.from(getActivity()).inflate(R.layout.progress_bar_spinner,null);
                            TextView textView = view.findViewById(R.id.text);
                            textView.setText("");
                            ab.setView(view);
                            AlertDialog abb = ab.create();
                            abb.show();

                            Handler handler=new Handler();
                            handler.postDelayed(() -> {
                                abb.dismiss();
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                int temp = sharedPreferences.getInt("wrongattempt",0);
                                temp+=1;
                                editor.putInt("wrongattempt",temp);
                                editor.apply();
                                attempts.setText("Wrong Attempts: "+sharedPreferences.getInt("wrongattempt",0));

                                if(sharedPreferences.getInt("wrongattempt",0)==3){
                                    editor.putBoolean("isBlocked",true);
                                    editor.apply();
                                }

                            },1500);
                        }
                    }
                }
        });
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
