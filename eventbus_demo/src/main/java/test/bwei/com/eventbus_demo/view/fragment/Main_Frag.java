package test.bwei.com.eventbus_demo.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import test.bwei.com.eventbus_demo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main_Frag extends Fragment {


    public Main_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_, container, false);
        Button button = (Button) view.findViewById(R.id.frag_hello);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post("hello --- Hello_fragment !");
            }
        });
        return view;
    }

}
