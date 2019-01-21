package deviser.com.testing_project;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {


    CoordinatorLayout bottomSheet;
    BottomSheetBehavior bottomSheetBehavior;
    private float pf=500f;
    private float nf=-500f;
    private boolean temp=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomSheet=findViewById(R.id.bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setHideable(false);

        final TextView textView=findViewById(R.id.bottom_sheet_text);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v){
                if(!temp){
                    ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(v,"translationX",0f,nf);
                    objectAnimator.setDuration(800);
                    objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    objectAnimator.start();
                    temp=true;
                }
                else{
                    ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(v,"translationX",nf,0f);
                    objectAnimator.setDuration(800);
                    objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    objectAnimator.start();
                    temp=false;
                }
            }
        });

        final Button btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("You Clicked Button");
                v.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch(event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        Button btnn=findViewById(R.id.bottom_sheet_btn);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText("Bottom Sheet's Button Clicked");
            }
        });

        FloatingActionButton fabb=findViewById(R.id.fabb);
        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}