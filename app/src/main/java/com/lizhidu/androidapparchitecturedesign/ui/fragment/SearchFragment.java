package com.lizhidu.androidapparchitecturedesign.ui.fragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.lizhidu.androidapparchitecturedesign.R;

public class SearchFragment extends Fragment {
	private AutoCompleteTextView acTextView;  
	private Button btnSearch;
	private int length;
	  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        return inflater.inflate(R.layout.search, container, false);
    }  
  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        
        initView();
		initData();
		setListener();
    } 
    
    private void initView() {
		// TODO Auto-generated method stub
		acTextView = (AutoCompleteTextView) getView().findViewById(R.id.autoCompleteTextView1);
		btnSearch = (Button) getView().findViewById(R.id.btn_search);
	}

	private void initData() {
		// TODO Auto-generated method stub
	}

	private void setListener() {
		// TODO Auto-generated method stub
		acTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				acTextView.showDropDown();
				initAutoComplete();
			}
		});
		
		//内容变化监听
		acTextView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				initAutoComplete();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//最近搜索列表点击事件
		acTextView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				getData();
			}
		});
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveHistory();
				getData();
			}
		});
	}
	
	/**
	 * 通过输入内容查找数据
	 */
	private void getData(){
		//一般为网路请求
	}
	
	/**
	 * 显示最近10条搜索内容
	 */
	private void initAutoComplete() {  
        SharedPreferences sp = getActivity().getSharedPreferences("content", 0);  
        String longhistory = sp.getString("history", "没有记录");  
        String[] histories = longhistory.split(",");  
        length = histories.length;
        ArrayAdapter<String> adapter;
        if(length != 1){
        	String[] newHistories = new String[length-1];
        	System.arraycopy(histories, 0, newHistories, 0, length-1);
        	
        	adapter = new ArrayAdapter<String>(getActivity(),  
        			R.layout.simple_dropdown_item_1, newHistories); 
        }else{
        	 adapter = new ArrayAdapter<String>(getActivity(),  
        			 R.layout.simple_dropdown_item_1, histories); 
        }
        
        if (histories.length > 9) {
        	Editor editor = sp.edit();  
	        //移除数据  
	        editor.remove("history");  
	        editor.commit();
	        
            String[] newHistories = new String[10];  
            System.arraycopy(histories, 0, newHistories, 0, 10);  
            
            String str = newHistories[0];
            for (int i = 1; i < newHistories.length; i++) {
	            str += "," + newHistories[i];
			}
            editor.putString("history", str).commit();
            adapter = new ArrayAdapter<String>(getActivity(),  
                    R.layout.simple_dropdown_item_1, newHistories); 
        }  
        acTextView.setAdapter(adapter);  
        acTextView.setOnFocusChangeListener(new OnFocusChangeListener() {  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
                AutoCompleteTextView view = (AutoCompleteTextView) v;  
                if (hasFocus) {  
                    view.showDropDown();  
                }  
            }  
        });  
    }  
	
	/**
	 * 保存搜索内容
	 */
	private void saveHistory() {  
        String text = acTextView.getText().toString();  
        SharedPreferences sp = getActivity().getSharedPreferences("content", 0);  
        String longhistory = sp.getString("history", "没有记录"); 
        if (!text.equals("") && !longhistory.contains(text + ",")) {  
            StringBuilder sb = new StringBuilder(longhistory);  
            sb.insert(0, text + ",");  
            sp.edit().putString("history", sb.toString()).commit();  
        }  
    }
}
