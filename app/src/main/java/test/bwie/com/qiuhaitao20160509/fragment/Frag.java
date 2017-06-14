package test.bwie.com.qiuhaitao20160509.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import test.bwie.com.qiuhaitao20160509.R;
import test.bwie.com.qiuhaitao20160509.utils.JsonUtils;

/**
 * A simple {@link Fragment} subclass.
 * fragment类
 */
public class Frag extends Fragment {


    private ListView listView;

    public Frag() {
        // Required empty public constructor
    }

    public String url;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_,null);
        listView = (ListView) view.findViewById(R.id.lv);
        JsonUtils jsonUtils = new JsonUtils(getActivity(),listView);
        jsonUtils.getJson(url);
        return view;
    }

}
