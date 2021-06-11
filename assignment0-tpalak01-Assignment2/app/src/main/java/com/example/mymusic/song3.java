package com.example.mymusic;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link song3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class song3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public song3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment song3.
     */
    // TODO: Rename and change types and number of parameters
    public static song3 newInstance(String param1, String param2) {
        song3 fragment = new song3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private Intent serIntent;
    private Button bStart3, bStop3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void onBackPressed()
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_song3, container, false);
        bStart3=v.findViewById(R.id.start3);
        bStop3=v.findViewById(R.id.stop3);
        bStart3.setOnClickListener(new View.OnClickListener(){
            Intent serIntent;
            @Override
            public void onClick(View v) {
                serIntent=new Intent(getActivity(),MyService.class);
                serIntent.putExtra("sng","3");
                getActivity().startService(serIntent);
            }
        });
        bStop3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent ts= new Intent(getActivity(),MyService.class);
                //serIntent.putExtra("sng","3");
                getActivity().stopService(ts);
            }
        });

        return v;
    }
}