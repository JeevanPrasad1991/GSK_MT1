package com.cpm.DailyEntry;

import java.util.ArrayList;

import com.cpm.Constants.CommonString;

import com.cpm.database.GSKMTDatabase;
import com.cpm.delegates.PerformanceBean;
import com.example.gsk_mtt.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoreWisePerformance extends Activity {
    private SharedPreferences preferences;
    String date, store_id, store_intime, process_id;

    GSKMTDatabase database;
    ListView lv;
    ArrayList<PerformanceBean> per_list;
    Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_performance);
        lv = (ListView) findViewById(R.id.listviewperformance);
        ok = (Button) findViewById(R.id.ok);

        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        date = preferences.getString(CommonString.KEY_DATE, null);
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        store_intime = preferences.getString(CommonString.KEY_STORE_IN_TIME, "");
        process_id = preferences.getString(CommonString.KEY_PROCESS_ID, "");

        database = new GSKMTDatabase(StoreWisePerformance.this);
        database.open();


        per_list = database.getStoreWisePerformanceData(store_id, process_id);


        if (per_list.size() > 0) {

            lv.setAdapter(new MyAdaptor());

        }

        ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent in = new Intent(StoreWisePerformance.this, StoreVisitedActivity.class);
                startActivity(in);
                StoreWisePerformance.this.finish();

            }
        });

    }


    private class ViewHolder {

        TextView period, sos, tot, paid, additional, pss;

    }


    private class MyAdaptor extends BaseAdapter {


        public MyAdaptor() {
            super();
            // TODO Auto-generated constructor stub
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return per_list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_storewiseperformace_list, null);

                holder.period = (TextView) convertView
                        .findViewById(R.id.period);

                holder.sos = (TextView) convertView
                        .findViewById(R.id.sos);

                holder.tot = (TextView) convertView
                        .findViewById(R.id.tot);

                holder.paid = (TextView) convertView
                        .findViewById(R.id.paid);


                holder.additional = (TextView) convertView
                        .findViewById(R.id.additional);

                holder.pss = (TextView) convertView
                        .findViewById(R.id.pss);


                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.period.setText(per_list.get(position).getPeriod());
//			holder.sos.setText(per_list.get(position).getSos_avg());
//			holder.tot.setText(per_list.get(position).getTot_avg());

            if (per_list.get(position).getPaid_avg() == null) {
                holder.paid.setText("0");
            } else {
                holder.paid.setText(per_list.get(position).getPaid_avg());
            }


            if (per_list.get(position).getSos_avg() == null) {
                holder.sos.setText("0");
            } else {
                holder.sos.setText(per_list.get(position).getSos_avg());
            }


            if (per_list.get(position).getTot_avg() == null) {
                holder.tot.setText("0");
            } else {
                holder.tot.setText(per_list.get(position).getTot_avg());
            }


            if (per_list.get(position).getAdditional_avg() == null) {
                holder.additional.setText("0");
            } else {
                holder.additional.setText(per_list.get(position).getAdditional_avg());
            }


            if (per_list.get(position).getPss_avg() == null) {
                holder.pss.setText("0");
            } else {
                holder.pss.setText(per_list.get(position).getPss_avg());
            }


//			holder.additional.setText(per_list.get(position).getAdditional_avg());
//			holder.pss.setText(per_list.get(position).getPss_avg());
            int value1 = ragAnalysis(per_list.get(position).getPss_avg());
            holder.pss.setBackgroundColor(value1);


            return convertView;
        }

    }


    private int ragAnalysis(String string) {
        int color;
        int c = (int) Math.round(Double.parseDouble(string));
        if (c > 70) {
            color = Color.GREEN;
        } else if (c > 39 && c < 71) {
            color = Color.parseColor("#FFBF00");
        } else {
            color = Color.RED;
        }
        return color;

    }

}
