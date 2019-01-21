package deviser.com.testing_project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView count;
    private ImageView imageView;
    private EditText username,password;
    private Animation animation,animationScale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count=findViewById(R.id.count);
        imageView=findViewById(R.id.imageView);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake_anim);
        animation.setDuration(50);
        animationScale=new ScaleAnimation(0,5,0,5,Animation.RELATIVE_TO_SELF,50.0f);
        animationScale.setDuration(500);
    }

    public void increaseQty(View v){

        imageView.startAnimation(animation);
        String qty = count.getText().toString();
        int qtyy=Integer.parseInt(qty);
        qtyy+=1;
        if(qtyy%5==0){
            imageView.startAnimation(animationScale);
        }
        count.setText(String.valueOf(qtyy));
    }

    public void submitData(View v){

        if(username.getText().toString().trim().length()==0){
            username.startAnimation(animation);
        }
        else if(password.getText().toString().trim().length()==0){
            password.startAnimation(animation);
        }
        else{
            Toast.makeText(getApplicationContext(),"Success!!!",Toast.LENGTH_SHORT).show();
        }
    }
}