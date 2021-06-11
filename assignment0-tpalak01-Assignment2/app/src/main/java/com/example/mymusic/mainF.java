package com.example.mymusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mainF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mainF extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public mainF() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mainF.
     */
    // TODO: Rename and change types and number of parameters
    public static mainF newInstance(String param1, String param2) {
        mainF fragment = new mainF();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
     Button bf1,bf2,bf3,bneto;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_LOW");
    private BroadcastReceiver battery_lowBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getContext(),"Battery Low",Toast.LENGTH_LONG).show();
        }
    };
    IntentFilter filter1 = new IntentFilter("android.intent.action.BATTERY_OKAY");
    private BroadcastReceiver battery_okBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getContext(),"Battery OK",Toast.LENGTH_LONG).show();
        }
    };
    IntentFilter filter2 = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
    private BroadcastReceiver powerBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getContext(),"Power Disconnected",Toast.LENGTH_LONG).show();
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_main, container, false);
        bf1=v.findViewById(R.id.f1);
        bf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song1 s1=new song1();
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout,s1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        bf2=v.findViewById(R.id.f2);
        bf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song2 s2=new song2();
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout,s2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        bf3=v.findViewById(R.id.f3);
        bf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song3 s3=new song3();
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout,s3);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        bneto=v.findViewById(R.id.bnet);
        bneto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), downMusic.class);
                startActivity(intent1);
            }
        });
        getActivity().registerReceiver(battery_lowBroadcastReceiver, filter);
        getActivity().registerReceiver(battery_okBroadcastReceiver, filter1);
        getActivity().registerReceiver(powerBroadcastReceiver, filter2);
        return v;
    }
}