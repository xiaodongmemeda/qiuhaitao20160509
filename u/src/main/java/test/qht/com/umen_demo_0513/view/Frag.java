package test.qht.com.umen_demo_0513.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.qht.com.umen_demo_0513.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag extends Fragment {


    private View view;

    public Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_, container, false);
        initView();
        return view;
    }

    private void initView() {

    }

}
