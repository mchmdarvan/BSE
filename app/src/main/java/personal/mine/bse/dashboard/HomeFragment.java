package personal.mine.bse.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import personal.mine.bse.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageSlider imageSlider=view.findViewById(R.id.slider);
        List<SlideModel> slideModelLs=new ArrayList<>();
        slideModelLs.add(new SlideModel(R.drawable.banner, ScaleTypes.CENTER_INSIDE));
        slideModelLs.add(new SlideModel(R.drawable.banner, ScaleTypes.CENTER_INSIDE));
        slideModelLs.add(new SlideModel(R.drawable.banner, ScaleTypes.CENTER_INSIDE));

        imageSlider.setImageList(slideModelLs, ScaleTypes.FIT);
    }
}
