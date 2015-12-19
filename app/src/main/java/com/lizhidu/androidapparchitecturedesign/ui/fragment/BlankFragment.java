package com.lizhidu.androidapparchitecturedesign.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lizhidu.androidapparchitecturedesign.R;

public class BlankFragment extends Fragment{

	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
		View v = inflater.inflate(R.layout.blank_fragment, container, false);
        return v;  
    }  
  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
    } 
}
