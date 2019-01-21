package deviser.com.testing_project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class Main2Activity extends AppCompatActivity {


    private Fragment fragment1,fragment2;
    private boolean temp=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,new AppCompatFragmentExample(),"").commit();

        fragment1 = fragmentManager.findFragmentByTag("appCompatFragment");
        fragment2= fragmentManager.findFragmentByTag("blankFragment");
    }

    public void changeFragment(View v){

        if(!temp){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_up,0);
            fragmentTransaction.replace(R.id.fragment,new BlankFragment(),"");
            fragmentTransaction.commit();
            temp=true;
        }
        else{
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_up,0);
            fragmentTransaction.replace(R.id.fragment,new AppCompatFragmentExample(),"");
            fragmentTransaction.commit();
            temp=false;
        }
    }
}
