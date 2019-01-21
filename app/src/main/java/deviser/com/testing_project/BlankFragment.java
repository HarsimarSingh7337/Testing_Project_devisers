package deviser.com.testing_project;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramotion.fluidslider.FluidSlider;

import kotlin.Unit;

public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_blank, container, false);

        TextView textView = rootView.findViewById(R.id.textView);

        int max = 100;
        int min = 0;
        int total = max-min;

         final FluidSlider slider = rootView.findViewById(R.id.fluidSlider);
         slider.setPositionListener(pos -> {
         String val =String.valueOf((int)(min + (total * pos)));
         slider.setBubbleText(val);
         return Unit.INSTANCE;
        });

        return rootView;
    }
}
