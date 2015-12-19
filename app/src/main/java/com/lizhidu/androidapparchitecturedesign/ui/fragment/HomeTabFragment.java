package com.lizhidu.androidapparchitecturedesign.ui.fragment;

import com.lizhidu.androidapparchitecturedesign.BaseFragment;
import com.lizhidu.androidapparchitecturedesign.R;
import com.lizhidu.androidapparchitecturedesign.ui.FirstActivity;
import com.lizhidu.androidapparchitecturedesign.ui.MainActivity1;
import com.lizhidu.androidapparchitecturedesign.utils.CommonUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * 首页fragment ViewPager tab
 *
 */
public class HomeTabFragment extends BaseFragment implements View.OnClickListener {

	private static final String TAG = "HomeTabFragment";
	private Activity mActivity;
	private TextView mMsgTv;
	private String mMsgName;

	public void setMsgName(String msgName) {
		this.mMsgName = msgName;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home_tab, container, false);
		Button button = (Button) view.findViewById(R.id.button);
		button.setOnClickListener(this);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initDisplay();
	}

	private void initViews(View view) {
		mMsgTv = (TextView) view.findViewById(R.id.msg_tv);
	}

	private void initDisplay() {
		mMsgTv.setText(mMsgName + "");
	}

	@Override
	public String getFragmentName() {
		return TAG;
	}

	@Override
	public void onClick(View v) {
		CommonUtil.launchActivity(this.getActivity(), FirstActivity.class);
	}
}
