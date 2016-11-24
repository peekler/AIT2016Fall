package hu.ait.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by peter on 2016. 11. 24..
 */

public class FragmentMain extends Fragment {

    public static final String TAG = "FragmentMain";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, null);

        Button button = (Button) root.findViewById(R.id.btnDemo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Demo working", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }



}
