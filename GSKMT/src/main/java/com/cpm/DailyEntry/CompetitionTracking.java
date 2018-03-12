package com.cpm.DailyEntry;

import java.io.File;
import java.util.ArrayList;

import com.cpm.Constants.CommonString;
import com.cpm.DailyEntry.BeforeAdditionalDisplay.MyAdaptor;
import com.cpm.DailyEntry.BeforeAdditionalDisplay.MyAdaptor.ViewHolder;
import com.cpm.database.GSKMTDatabase;
import com.cpm.delegates.SkuBean;
import com.example.gsk_mtt.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CompetitionTracking extends Activity{
	
	 Spinner competition, display;public ListView listview;
	 EditText quantity; Button add;
	 ImageView cam;
	 private ArrayAdapter<CharSequence> brandAdaptor, displayAdaptor;
	 SharedPreferences preferences;
	 String store_id, category_id, process_id, date, intime, username, app_version, store_type_id, imgDate,_path
	 , brand_name, brand_id, display_name, display_id;
	 GSKMTDatabase db;private static String str;
	 String img1 = "", image1= "";
	 protected static String _pathforcheck = "";
	 public ArrayList<SkuBean> display_list = new ArrayList<SkuBean>();
	 ArrayList<SkuBean> brand_list = new ArrayList<SkuBean>();
	 ArrayList<SkuBean> list = new ArrayList<SkuBean>();
	 MyAdaptor  adapterData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.competition_tracking);
		
		display = (Spinner)findViewById(R.id.display_name);
		competition = (Spinner)findViewById(R.id.competition);
		quantity = (EditText)findViewById(R.id.qty_bought);
		add = (Button)findViewById(R.id.add_btn);
		cam = (ImageView)findViewById(R.id.camera);
		listview = (ListView)findViewById(R.id.lv);
		
		
		preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
		category_id = preferences.getString(CommonString.KEY_CATEGORY_ID, null);
		process_id = preferences.getString(CommonString.KEY_PROCESS_ID, null);
		date = preferences.getString(CommonString.KEY_DATE, null);
		intime = preferences.getString(CommonString.KEY_IN_TIME, null);
		username = preferences.getString(CommonString.KEY_USERNAME, null);
		app_version = preferences.getString(CommonString.KEY_VERSION, null);
		store_type_id = preferences.getString(CommonString.storetype_id, null);
		imgDate = date.replace("/", "-");
		
		db = new GSKMTDatabase(CompetitionTracking.this);
		db.open();
		
		
		str = Environment.getExternalStorageDirectory()+"/MT_GSK_Images/";
		brand_list = db.getCompetitionBrandList(category_id);
		display_list = db.getDisplayListForComp();
		
		brandAdaptor = new ArrayAdapter<CharSequence>(CompetitionTracking.this, android.R.layout.simple_spinner_item);
		brandAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		displayAdaptor = new ArrayAdapter<CharSequence>(CompetitionTracking.this, android.R.layout.simple_spinner_item);
		displayAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		brandAdaptor.add("Select Competition");
		for(int i=0 ; i<brand_list.size();i++){
		brandAdaptor.add(brand_list.get(i).getBrand());
	}
	
		displayAdaptor.add("Select Display");
		for(int i=0 ; i<display_list.size();i++){
			displayAdaptor.add(display_list.get(i).getDisplay());
		}
		
		competition.setAdapter(brandAdaptor);
		display.setAdapter(displayAdaptor);
		
		
		competition.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				if (position != 0) {
					brand_name = brand_list.get(position - 1).getBrand();
					brand_id = brand_list.get(position- 1).getBrand_id();
					
				} else {
					
					brand_name = "";
					brand_id = "";
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
				
			}
		});
		
		
		display.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				if (position != 0) {
					display_name = display_list.get(position - 1).getDisplay();
					display_id = display_list.get(position- 1).getDisplay_id();
					
				} else {
					
					display_name = "";
					display_id = "";
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
				
			}
		});
		
		
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			String quan = 	quantity.getText().toString();
			
			
				if (!quan.equalsIgnoreCase("") && !image1.equalsIgnoreCase("")) {

					
					if (validatedata()) {
						SkuBean ab = new SkuBean();
						
						ab.setQuantity(quan);
						ab.setBrand(brand_name);
						ab.setBrand_id(brand_id);
						ab.setDisplay(display_name);
						ab.setDisplay_id(display_id);
						ab.setAdditional_image(image1);
						
		
						db.InsertCompetitionInfo(ab, store_id, category_id, process_id);
						quantity.setText("");
						
						competition.setSelection(0);
						display.setSelection(0);
						image1 = "";
						cam.setBackgroundResource(R.drawable.camera_ico);
						
						
						list = db.getEnteredCompetitionDetail(store_id, category_id, process_id);
				        adapterData = new MyAdaptor(CompetitionTracking.this, list);
				        
				    	listview.setAdapter(adapterData);
				    	listview.invalidateViews();
					} else {
						Toast.makeText(getBaseContext(), "Please Select the Product,Display", Toast.LENGTH_LONG).show();
					}
					
		        
					
				} else {
					
					Toast.makeText(getBaseContext(), "Please fill Correct data", Toast.LENGTH_LONG).show();

				}
			} 
			
			
				
			
		});
		
		list = db.getEnteredCompetitionDetail(store_id, category_id, process_id);
		
		if (list.size()>0) {
			adapterData = new MyAdaptor(CompetitionTracking.this,list);
			
	    	listview.setAdapter(adapterData);
	    	listview.invalidateViews();
		}
		
		
	}
	
	
	 public void onButtonClick(View v){
        
        
        if (v.getId() == R.id.camera) {

       	 if (brand_name != null && !brand_id.equalsIgnoreCase("") && display_name != null && !display_id.equalsIgnoreCase("")) {
       		 _pathforcheck = store_id+"_1_BA"+ imgDate + brand_id + display_id +".jpg";
                _path = str + _pathforcheck;
                startCameraActivity();
    		}
       	 
       	 else{
       		 Toast.makeText(CompetitionTracking.this, "Please select Brand and Display first", Toast.LENGTH_LONG).show();
       	 }

        
        
        }          
        
}
	 
	 public boolean validatedata() {
			boolean result = false;
			if (brand_name != null && !brand_id.equalsIgnoreCase("") && display_name != null && !display_id.equalsIgnoreCase("")) {
				result = true;
			}

			return result;

		}
	
	
	protected void startCameraActivity() {

        try {
                        Log.i("MakeMachine", "startCameraActivity()");
                        File file = new File(_path);
                        Uri outputFileUri = Uri.fromFile(file);

                        Intent intent = new Intent( android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                        startActivityForResult(intent, 0);
        } catch (Exception e) { 
                        e.printStackTrace();
        }
	 	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
        case 0:
                        Log.i("MakeMachine", "User cancelled");
                        break;
                       
        case -1:
                        if (_pathforcheck != null && !_pathforcheck.equals("")) {
                                        if (new File((str + _pathforcheck).trim()).exists()) {
                                        				
                                   img1 = _pathforcheck;
                                    String value = img1.substring(img1.indexOf("_")+1, img1.lastIndexOf("_"));
                                     if(value != null){
                                        if(value.equalsIgnoreCase("1")){
                                       	 
                                          image1 = img1;
                                           cam.setBackgroundResource(R.drawable.camera_tick_ico);
                                                                        }                                                                        
                                                                        
                                                                        
                                                                                                                                 }
                        //                            audit_lv.invalidateViews();
                                                        _pathforcheck = "";
                                                        break;

                                        						}
                        									}

                        break;
        }
}
	
	@Override
	public void onBackPressed() {
		
		super.onBackPressed();
		Intent in = new Intent(CompetitionTracking.this, DailyEntryMainMenu.class);
		startActivity(in);
		CompetitionTracking.this.finish();
	}
	
	public class MyAdaptor extends BaseAdapter{
		
		private LayoutInflater mInflater;
		private Context mcontext;
		private ArrayList<SkuBean> list;

	public MyAdaptor(Activity activity, ArrayList<SkuBean> list1) {
		
		mInflater = LayoutInflater.from(getBaseContext());
		mcontext = activity;
		list = list1;
		}

	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		
		return position;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}
	
	class ViewHolder {
		TextView brand, qty_bought, display;
		Button save,delete;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		final ViewHolder holder;
		
		if (convertView == null) {

			convertView = mInflater
					.inflate(R.layout.addtional_list, null);
			holder = new ViewHolder();
		
			holder.brand = (TextView) convertView.findViewById(R.id.brand_name);

			holder.display = (TextView) convertView.findViewById(R.id.display_name);
			holder.qty_bought = (TextView) convertView.findViewById(R.id.qty_bought);
			
			

			holder.delete = (Button) convertView.findViewById(R.id.delete_btn);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						CompetitionTracking.this);
		 
					// set title
					alertDialogBuilder.setTitle("Do You Want To Delete?");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Click Yes To Delete!")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								
								db.deleteCompetitionEntry(list.get(position).getKey_id());
								
								adapterData.notifyDataSetChanged();
								
								list = db.getEnteredCompetitionDetail(store_id, category_id, process_id);
								listview.setAdapter(new MyAdaptor(CompetitionTracking.this, list));
								listview.invalidateViews();
								
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
			
			}			
		});
		

		
		holder.brand.setText(list.get(position).getBrand()
					.toString());
		holder.display.setText(list.get(position).getDisplay().toString());
		
		holder.qty_bought.setText(list.get(position).getQuantity());
		
	
		
		
		return convertView;
	}
	}

}
