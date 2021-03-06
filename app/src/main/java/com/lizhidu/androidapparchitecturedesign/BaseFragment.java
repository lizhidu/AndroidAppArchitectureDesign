package com.lizhidu.androidapparchitecturedesign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lizhidu.androidapparchitecturedesign.utils.LogUtils;

import org.xutils.x;

/**
 * Created by wyouflf on 15/11/4.
 */
public abstract class BaseFragment extends Fragment {

    private boolean injected = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(getFragmentName(), " onCreate()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i(getFragmentName(), " onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i(getFragmentName(), " onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(getFragmentName(), " onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(getFragmentName(), " onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.i(getFragmentName(), " onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(getFragmentName(), " onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(getFragmentName(), " onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.i(getFragmentName(), " onDetach()");
    }

    /**
     * fragment name
     */
    public abstract String getFragmentName();


}
