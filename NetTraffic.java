package com.wenhuabin.netmanager;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator; 
import java.util.HashMap;

import com.wenhuabin.database.Adapterforimage;
import com.wenhuabin.netmanager.R;
import com.wenhuabin.netmanager.AppInfo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;

public class NetTraffic extends Activity{
	private ListView showListview;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appnettra);
		showListview = (ListView) findViewById(R.id.apps_data_listview);
		show_data_onlistviw();
	}

	private void show_data_onlistviw() {
		PackageManager pckMan = getPackageManager();
		List<PackageInfo> packs = pckMan.getInstalledPackages(0);
		ArrayList<HashMap<String, Object>> item = new ArrayList<HashMap<String, Object>>();
		for (PackageInfo p:packs) {
			if((p.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0&&  
       (p.applicationInfo.flags&ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)==0){ 		
			int appid = p.applicationInfo.uid;
			long rxdata = TrafficStats.getUidRxBytes(appid);
			rxdata = rxdata / 1024 ;   
			long txdata = TrafficStats.getUidTxBytes(appid);
			txdata = txdata / 1024 ;
			long data_total = rxdata + txdata;
			HashMap<String, Object> items = new HashMap<String, Object>();
			Drawable drawable=p.applicationInfo.loadIcon(getPackageManager());
			Log.i("TAG", ""+drawable);
			items.put("appsimage",p.applicationInfo.loadIcon(getPackageManager()));
			items.put("appsname", p.applicationInfo.loadLabel(getPackageManager()).toString());
			items.put("rxdata", rxdata + "");
			items.put("txdata", txdata + "");
			items.put("alldata", data_total + "");
			item.add(items);
			}
		}
		Adapterforimage adapter=new Adapterforimage(this, item);
		
		showListview.setAdapter(adapter);
	}
}

