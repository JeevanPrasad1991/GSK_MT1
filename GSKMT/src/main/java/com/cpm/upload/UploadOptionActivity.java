package com.cpm.upload;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.GSKMTDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.StoreBean;
import com.cpm.gsk_mt.MainMenuActivity;
import com.cpm.message.AlertMessage;
import com.example.gsk_mtt.R;

public class UploadOptionActivity extends Activity {
    private SharedPreferences preferences;
    private static GSKMTDatabase database;
    ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
    StoreBean storestatus = new StoreBean();
    private Intent intent;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_option);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        date = preferences.getString(CommonString.KEY_DATE, null);
        database = new GSKMTDatabase(this);
        database.open();
    }


    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.upload_data:
                cdata = database.getCoverageData(date, null, null);
                if (cdata.size() == 0) {
                    Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG).show();
                } else {
                    if ((validate_data())) {
                        Intent i = new Intent(UploadOptionActivity.this, UploadDataActivity.class);
                        startActivity(i);
                        UploadOptionActivity.this.finish();
                    } else {
                        Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case R.id.upload_image:
                cdata = database.getCoverageData(date, null, null);
                if (cdata.size() == 0) {
                    Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_IMAGE, Toast.LENGTH_LONG).show();
                } else {
                    if (validate()) {
                        intent = new Intent(this, UploadImageActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_DATA_FIRST, Toast.LENGTH_LONG).show();
                    }
                }
                break;

        }
    }


    public boolean validate_data() {
        boolean result = false;
        database.open();
        cdata = database.getCoverageData(date, null, null);
        for (int i = 0; i < cdata.size(); i++) {
            storestatus = database.getStoreStatus(cdata.get(i).getStoreId(), cdata.get(i).getProcess_id());
            if (!storestatus.getUPLOAD_STATUS().equalsIgnoreCase(CommonString.KEY_D)) {
                if ((storestatus.getCHECKOUT_STATUS().equalsIgnoreCase(
                        CommonString.KEY_C) || storestatus.getUPLOAD_STATUS().equalsIgnoreCase(CommonString.KEY_P) ||
                        storestatus.getUPLOAD_STATUS().equalsIgnoreCase(CommonString.STORE_STATUS_LEAVE) ||
                        storestatus.getCHECKOUT_STATUS().equalsIgnoreCase(CommonString.STORE_STATUS_LEAVE))) {
                    result = true;
                    break;

                }
            }
        }

        return result;
    }

    public boolean validate() {
        boolean result = false;
        database.open();
        cdata = database.getCoverageData(date, null, null);
        for (int i = 0; i < cdata.size(); i++) {
            if (cdata.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_D)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
        UploadOptionActivity.this.finish();
    }

}
