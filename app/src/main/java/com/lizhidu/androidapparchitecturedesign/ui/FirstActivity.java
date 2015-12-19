package com.lizhidu.androidapparchitecturedesign.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lizhidu.androidapparchitecturedesign.R;

public class FirstActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
	}
	
	public void click(View v){
		Intent intent = new Intent();
		intent.setClass(this, MainActivity1.class);
		startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}
}
