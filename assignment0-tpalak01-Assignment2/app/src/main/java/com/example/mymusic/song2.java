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
 * Use the {@link song2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class song2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public song2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment song2.
     */
    // TODO: Rename and change types and number of parameters
    public static song2 newInstance(String param1, String param2) {
        song2 fragment = new song2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private Intent serIntent;
    private Button bStart2, bStop2;
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
        View v= inflater.inflate(R.layout.fragment_song2, container, false);
        bStart2=v.findViewById(R.id.start2);
        bStop2=v.findViewById(R.id.stop2);
        bStart2.setOnClickListener(new View.OnClickListener(){
            Intent serIntent;
            @Override
            public void onClick(View v) {
                serIntent=new Intent(getActivity(),MyService.class);
                serIntent.putExtra("sng","2");
                getActivity().startService(serIntent);
            }
        });
        bStop2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent ts= new Intent(getActivity(),MyService.class);
                //serIntent.putExtra("sng","2");
                getActivity().stopService(ts);
            }
        });

        return v;
    }
}