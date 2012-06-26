package com.wenhuabin.database;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.util.Log;

import com.wenhuabin.netmanager.Showmain;

/**
 * <pre>
 * ҵ����:
 * ����˵��: 
 * ��д����:	2012-3-13
 * 
 * ��ʷ��¼
 * 1���޸����ڣ�
 *    �޸��ˣ�
 *    �޸����ݣ�
 * </pre>
 */
public class MyReceiver extends BroadcastReceiver {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		DataSupport minsert = new DataSupport(context);
		long g3_down_total = TrafficStats.getMobileRxBytes(); // ��ȡͨ��Mobile�����յ����ֽ���������Android123��ʾ��Ҳ���WiFi
		long g3_up_total = TrafficStats.getMobileTxBytes(); // Mobile���͵����ֽ���
		long mrdown_total = TrafficStats.getTotalRxBytes(); // ��ȡ�ܵĽ����ֽ����Mobile��WiFi��
		long mtup_total = TrafficStats.getTotalTxBytes(); // �ܵķ����ֽ����Mobile��WiFi��

		minsert.insertNow(g3_down_total, Showmain.RXG, Showmain.RX3G,
				Showmain.SHUTDOWN);
		minsert.insertNow(g3_up_total, Showmain.TXG, Showmain.TX3G,
				Showmain.SHUTDOWN);
		minsert.insertNow(mrdown_total, Showmain.RX, Showmain.RXT,
				Showmain.SHUTDOWN);
		minsert.insertNow(mtup_total, Showmain.TX, Showmain.TXT,
				Showmain.SHUTDOWN);
		if (Showmain.isLog) {
			Log.i("liuliang", "shutdown>>>>>>>>>start");
		}
	}

}
