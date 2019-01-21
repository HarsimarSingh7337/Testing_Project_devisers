package deviser.com.testing_project;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class Initial_Password_Fragment extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialogFragment=getDialog();
        dialogFragment.setCancelable(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().setTitle("Set Initial Password");
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        View rootView = inflater.inflate(R.layout.fragment_initial__password_, container, false);

        TextInputEditText password = rootView.findViewById(R.id.password);
        TextInputEditText confirmPassword = rootView.findViewById(R.id.confirmpassword);
        TextInputLayout wrapperPassword = rootView.findViewById(R.id.wrapper_password);
        TextInputLayout wrapperConfirmPassword = rootView.findViewById(R.id.wrapper_confirmpassword);
        ImageView button = rootView.findViewById(R.id.imagebtn);

        button.setOnClickListener(v ->{

            if(password.getText().toString().trim().length()==0){
                wrapperPassword.setError("Invalid Field");
            }
            else if (confirmPassword.getText().toString().trim().length()==0){
                wrapperConfirmPassword.setError("Invalid Field");
            }
            else if (!password.getText().toString().equals(confirmPassword.getText().toString())){
                Toast.makeText(getActivity(),"Passwords did not Match",Toast.LENGTH_SHORT).show();
            }
            else{
                SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("MyPrefs",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("password",password.getText().toString());
                editor.apply();

                // Move to Next Activity now
                getDialog().dismiss();
                Toast.makeText(getActivity(),"Password set successfully",Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                AuthorizeUser_Fragment authorizeUser_fragment = new AuthorizeUser_Fragment();
                authorizeUser_fragment.show(fragmentManager,"");

            }
        });

        getDialog().setOnCancelListener(dialog -> Toast.makeText(getActivity(),"Back Pressed",Toast.LENGTH_SHORT).show());
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
