package com.cpm.gsk_mt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import com.cpm.CompleteDownload.CompleteDownloadActivity;
import com.cpm.Constants.CommonString;
import com.cpm.DailyEntry.CopyOfStorelistActivity;
import com.cpm.autoupdate.AutoupdateActivity;
import com.cpm.database.GSKMTDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.StoreBean;
import com.cpm.geotagging.GeoTagging;
import com.cpm.message.AlertMessage;
import com.cpm.upload.UploadOptionActivity;
import com.example.gsk_mtt.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity {
    Button download, daily_entry, upload, exit, geotag, auto_update;
    String date;
    SharedPreferences preferences;
    GSKMTDatabase db;
    ArrayList<StoreBean> storelist;
    ArrayList<StoreBean> temp = new ArrayList<StoreBean>();
    ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
    private int versionCode;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        download = (Button) findViewById(R.id.dash_download);
        daily_entry = (Button) findViewById(R.id.dash_dailyentry);
        upload = (Button) findViewById(R.id.dash_upload);
        exit = (Button) findViewById(R.id.exit);
        geotag = (Button) findViewById(R.id.geotag);
        auto_update = (Button) findViewById(R.id.auto_update);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        db = new GSKMTDatabase(MainMenuActivity.this);
        storelist = new ArrayList<StoreBean>();
        date = preferences.getString(CommonString.KEY_DATE, null);
        daily_entry.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                db.open();
                storelist = db.getJCP(date);
                if (storelist.size() > 0) {
                    Intent in = new Intent(MainMenuActivity.this, CopyOfStorelistActivity.class);
                    startActivity(in);
                } else {
                    Toast.makeText(MainMenuActivity.this, "Please download the data first", Toast.LENGTH_LONG).show();
                }
            }
        });

        auto_update.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (CheckNetAvailability()) {
                    if (!preferences.getString(CommonString.KEY_VERSION, "").equals(Integer.toString(versionCode))) {
                        Intent intent = new Intent(getBaseContext(), AutoupdateActivity.class);
                        intent.putExtra(CommonString.KEY_PATH, preferences.getString(CommonString.KEY_PATH, ""));
                        intent.putExtra(CommonString.KEY_STATUS, true);
                        startActivity(intent);
                        MainMenuActivity.this.finish();
                    } else {
                        Toast.makeText(getBaseContext(), "No Updates", Toast.LENGTH_LONG).show();
                    }

                } else {
                    showToast("No Network Available");
                }

            }
        });


        geotag.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                db.open();
                temp = db.getGeoStores();
                if (temp.size() == 0) {
                    Toast.makeText(getBaseContext(), "Please Download Data First",
                            Toast.LENGTH_LONG).show();
                } else {
                    if (CheckNetAvailability()) {
                        Intent intent = new Intent(getBaseContext(), GeoTagging.class);
                        startActivity(intent);
                        MainMenuActivity.this.finish();
                    } else {
                        showToast("No Network Available");
                    }
                }

            }
        });

        upload.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (CheckNetAvailability()) {
                    if (Validation()) {
                        Intent in = new Intent(MainMenuActivity.this, UploadOptionActivity.class);
                        startActivity(in);
                        MainMenuActivity.this.finish();
                    } else {
                        showToast("First Checkout");
                    }
                } else {
                    showToast("No Network Available");
                }


            }
        });


        exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
                intent.putExtra("enabled", false);
                AlertMessage message = new AlertMessage(MainMenuActivity.this, AlertMessage.MESSAGE_EXIT, "exit", null);
                message.showMessage();
            }
        });

        download.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (CheckNetAvailability()) {
                    if (AllowDownload()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                MainMenuActivity.this);
                        builder.setMessage("Please Upload Previous Data First")
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {
                                                Intent intent = new Intent(getBaseContext(), UploadOptionActivity.class);
                                                startActivity(intent);

                                            }
                                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                    } else {
                        storelist = db.getJCP(date);
                        if (storelist.size() > 0) {
                            alertMessage("Do you want to download jcp again?", CompleteDownloadActivity.class);
                        } else {
                            Intent intent = new Intent(getBaseContext(), CompleteDownloadActivity.class);
                            startActivity(intent);
                        }
                    }
                } else {
                    showToast("No Network Available");
                }
            }
        });


    }


    private void alertMessage(String msg, final Class<?> class1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainMenuActivity.this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                db.deleteAllTables();
                                Intent startDownload = new Intent(MainMenuActivity.this, class1);
                                startActivity(startDownload);
                                MainMenuActivity.this.finish();

                            }

                        })
                .setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });

        alert = builder.create();
        alert.show();
    }


    public boolean Validation() {
        boolean result = false;
        db.open();
        storelist = db.getJCP(date);
        for (int i = 0; i < storelist.size(); i++) {
            if (storelist.get(i).getCHECKOUT_STATUS()
                    .equalsIgnoreCase(CommonString.KEY_C)
                    || storelist.get(i).getUPLOAD_STATUS()
                    .equalsIgnoreCase(CommonString.STORE_STATUS_LEAVE)
                    || storelist.get(i).getUPLOAD_STATUS()
                    .equalsIgnoreCase(CommonString.KEY_D) || storelist
                    .get(i).getUPLOAD_STATUS().equalsIgnoreCase(CommonString.KEY_P)) {
                result = true;
                break;

            }

        }

        return result;
    }


    private void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    public boolean AllowDownload() {
        boolean result = true;
        db.open();
        coverageBeanlist = db.getCoverageData(null, null, null);
        if (coverageBeanlist.size() == 0) {
            result = false;
        }
        for (int i = 0; i < coverageBeanlist.size(); i++) {
            if (!(coverageBeanlist.get(i).getVisitDate().equalsIgnoreCase(date))) {
                result = false;
                break;
            }
        }
        return result;
    }


    public boolean CheckNetAvailability() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            // we are connected to a network
            connected = true;
        }
        return connected;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(
                    MainMenuActivity.this);
            builder1.setMessage(
                    "Are you sure you want to take the backup of your data")
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    try {
                                        File file = new File(Environment.getExternalStorageDirectory().getPath(), "DroidSystem");
                                        if (!file.isDirectory()) {
                                            file.mkdir();
                                        }
                                        File sd = Environment.getExternalStorageDirectory();
                                        File data = Environment.getDataDirectory();
                                        if (sd.canWrite()) {
                                            String currentDBPath = "//data//com.example.gsk_mtt//databases//" + GSKMTDatabase.DATABASE_NAME;
                                            String backupDBPath = "gsk_backup" + date.replace('/', '-');
                                            File currentDB = new File(data, currentDBPath);
                                            File backupDB = new File(Environment.getExternalStorageDirectory().getPath() + "/DroidSystem/", backupDBPath);
                                            if (currentDB.exists()) {
                                                FileChannel src = new FileInputStream(currentDB).getChannel();
                                                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                                                dst.transferFrom(src, 0, src.size());
                                                src.close();
                                                dst.close();
                                                Toast.makeText(getApplicationContext(), "Backup Successfully", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert1 = builder1.create();
            alert1.show();
            // do your stuff here
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(
                        MainMenuActivity.this);
                builder1.setMessage(
                        "Are you sure you want to take the backup of your data")
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        try {
                                            File file = new File(Environment.getExternalStorageDirectory().getPath(),
                                                    "DroidSystem");
                                            if (!file.isDirectory()) {
                                                file.mkdir();
                                            }
                                            File sd = Environment.getExternalStorageDirectory();
                                            File data = Environment.getDataDirectory();
                                            if (sd.canWrite()) {
                                                String currentDBPath = "//data//com.example.gsk_mtt//databases//" + GSKMTDatabase.DATABASE_NAME;
                                                String backupDBPath = "gsk_backup"
                                                        + date.replace('/', '-');

                                                File currentDB = new File(data, currentDBPath);
                                                File backupDB = new File(Environment.getExternalStorageDirectory().getPath() + "/DroidSystem/", backupDBPath);
                                                if (currentDB.exists()) {
                                                    FileChannel src = new FileInputStream(currentDB).getChannel();
                                                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                                                    dst.transferFrom(src, 0, src.size());
                                                    src.close();
                                                    dst.close();
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert1 = builder1.create();
                alert1.show();

                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}