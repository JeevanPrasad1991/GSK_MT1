package com.cpm.DailyEntry;

import java.io.File;
import java.util.ArrayList;

import com.cpm.Constants.CommonString;


import com.cpm.database.GSKMTDatabase;


import com.cpm.delegates.SkuBean;
import com.example.gsk_mtt.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ToggleButton;

public class BeforeAdditionalDisplay extends Activity{
	
	public ListView listview; Spinner brand, display;
	EditText quantity; Button add;
	public ArrayList<SkuBean> brand_list = new ArrayList<SkuBean>();
	public ArrayList<SkuBean> display_list = new ArrayList<SkuBean>();
	private ArrayAdapter<CharSequence> brandAdaptor, displayAdaptor;
	GSKMTDatabase db; String brand_name, brand_id, display_name, display_id, store_id,store_type_id, image_url;
	private SharedPreferences preferences;
	MyAdaptor  adapterData;Button show;
	ImageView cam; String img1 = "";
	ArrayList<SkuBean> list = new ArrayList<SkuBean>();
	private static String str, path;
	protected static String _pathforcheck = "";
	public String category_id, process_id, date, intime, username, app_version,imgDate, _path , image1="";
	LinearLayout l1, l2;
	ToggleButton toggle;
	String s, yesorno;
	Button refImage;

	String reference_image_path = Environment.getExternalStorageDirectory() + "/GSKMT Planogram Images/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtional_before_display);
		listview = (ListView)findViewById(R.id.lv);
		cam = (ImageView)findViewById(R.id.camera);
		brand = (Spinner)findViewById(R.id.brand_name);
		display = (Spinner)findViewById(R.id.display_name);
		quantity = (EditText)findViewById(R.id.qty_bought);
		toggle = (ToggleButton)findViewById(R.id.toggle);
		add = (Button)findViewById(R.id.add_btn);
		refImage = (Button)findViewById(R.id.refimage);
		l1 = (LinearLayout)findViewById(R.id.addtional_layout);
		l2 = (LinearLayout)findViewById(R.id.list_layout);
		
//		show = (Button)findViewById(R.id.show);
		
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
		
		
		db = new GSKMTDatabase(BeforeAdditionalDisplay.this);
		db.open();
		
		toggle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 s = toggle.getText().toString();
				 
				 if (s.equalsIgnoreCase("YES")) {
						l1.setVisibility(View.VISIBLE);
						l2.setVisibility(View.VISIBLE);
						yesorno = "YES";
					} else if (s.equalsIgnoreCase("NO")) {
						l1.setVisibility(View.GONE);
						l2.setVisibility(View.GONE);
						yesorno = "NO";
					}
				
			}
		});
		
		if (toggle.getText().toString().equalsIgnoreCase("YES")) {
			l1.setVisibility(View.VISIBLE);
			l2.setVisibility(View.VISIBLE);
			yesorno = "YES";
			quantity.setText("1");
			
		} else if (toggle.getText().toString().equalsIgnoreCase("NO")) {
			
			
			l1.setVisibility(View.GONE);
			l2.setVisibility(View.GONE);
			yesorno = "NO";
			
			brand_name = "";
			brand_id = "";
			
			display_id = "";
			display_name = "";
			
			quantity.setText("1");
			
			image1 = "";                    
					
		}
		
		str = Environment.getExternalStorageDirectory()+"/MT_GSK_Images/";
		brand_list = db.getBrandList(category_id);
		display_list = db.getDisplayList(category_id, store_id, store_type_id,process_id);
		
		brandAdaptor = new ArrayAdapter<CharSequence>(BeforeAdditionalDisplay.this, android.R.layout.simple_spinner_item);
		brandAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		displayAdaptor = new ArrayAdapter<CharSequence>(BeforeAdditionalDisplay.this, android.R.layout.simple_spinner_item);
		displayAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		brandAdaptor.add("Select Brand");
		for(int i=0 ; i<brand_list.size();i++){
		brandAdaptor.add(brand_list.get(i).getBrand());
	}
	
		displayAdaptor.add("Select Display");
		for(int i=0 ; i<display_list.size();i++){
			displayAdaptor.add(display_list.get(i).getDisplay());
		}
		
		brand.setAdapter(brandAdaptor);
		display.setAdapter(displayAdaptor);
		
		brand.setOnItemSelectedListener(new OnItemSelectedListener() {

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
					image_url = display_list.get(position-1).getImage_url();
					
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
			
			if (yesorno.equalsIgnoreCase("YES")) {
				if (!quan.equalsIgnoreCase("") && !image1.equalsIgnoreCase("")) {

					
					if (validatedata()) {
						SkuBean ab = new SkuBean();
						
						ab.setQuantity(quan);
						ab.setBrand(brand_name);
						ab.setBrand_id(brand_id);
						ab.setDisplay(display_name);
						ab.setDisplay_id(display_id);
						ab.setAdditional_image(image1);
						ab.setYesorno(yesorno);
		
						db.InsertAdditionalInfo(ab, store_id, category_id, process_id);
						quantity.setText("1");
						
						brand.setSelection(0);
						display.setSelection(0);
						image1 = "";
						cam.setBackgroundResource(R.drawable.camera_ico);
						
						
						list = db.getProductEntryDetail(store_id, category_id, process_id);
				        adapterData = new MyAdaptor(BeforeAdditionalDisplay.this,list);
				        
				    	listview.setAdapter(adapterData);
				    	listview.invalidateViews();
					} else {
						Toast.makeText(getBaseContext(), "Please Select the Product,Display", Toast.LENGTH_LONG).show();
					}

				} else {
					
					Toast.makeText(getBaseContext(), "Please Take a photo", Toast.LENGTH_LONG).show();

				}
			} else {
//				db.deleteProductEntry(store_id,category_id);
//				adapterData.notifyDataSetChanged();
//				listview.invalidateViews();
				
				SkuBean ab = new SkuBean();
				ab.setQuantity("0");
				ab.setBrand("0");
				ab.setBrand_id("0");
				ab.setDisplay("0");
				ab.setDisplay_id("0");
				ab.setAdditional_image("0");
				ab.setYesorno(yesorno);
				db.InsertAdditionalInfo(ab, store_id, category_id, process_id);
				
				Intent in = new Intent(BeforeAdditionalDisplay.this, DailyEntryMainMenu.class);
				startActivity(in);
				BeforeAdditionalDisplay.this.finish();

			}
			
			
				
			}
		});
		
		
		
		refImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (!display_id.equalsIgnoreCase("")) {

					
					final Dialog dialog = new Dialog(BeforeAdditionalDisplay.this);
					dialog.setContentView(R.layout.popup);
					ImageView refimage = (ImageView)dialog.findViewById(R.id.displayimage);
					dialog.setTitle("Reference Image");

					Bitmap bitmap = BitmapFactory.decodeFile(reference_image_path + image_url);
					Drawable mDrawable = new BitmapDrawable(getResources(), bitmap);
					refimage.setImageDrawable(mDrawable);


					/*if (image_url.equalsIgnoreCase("Chem1st.jpg")) {
						refimage.setBackgroundResource(R.drawable.chemfirst);
					} 					
					 else if (image_url.equalsIgnoreCase("DumpBIn.jpg")){
						 
							refimage.setBackgroundResource(R.drawable.dumpbin);
						}
					
					 else if (image_url.equalsIgnoreCase("FloorStickers.jpg")){
							refimage.setBackgroundResource(R.drawable.floorstickers);
						}
					
					 else if (image_url.equalsIgnoreCase("Onewayvision.jpg")){
							refimage.setBackgroundResource(R.drawable.onewayvision);
						}
					
					 else if (image_url.equalsIgnoreCase("DropDown.jpg")){
							refimage.setBackgroundResource(R.drawable.dropdown);                                                                                                                                           
						}

					 else if (image_url.equalsIgnoreCase("Skirting.jpg")){
							refimage.setBackgroundResource(R.drawable.skirting);                                                                                                                                           
						}
					
					 else if (image_url.equalsIgnoreCase("FloorStack.jpg")){
							refimage.setBackgroundResource(R.drawable.floorstack);                                                                                                                                           
						}
					
					 else if (image_url.equalsIgnoreCase("Endcap.jpg")){
							refimage.setBackgroundResource(R.drawable.endcap);                                                                                                                                           
						}
					
					 else if (image_url.equalsIgnoreCase("FSU.jpg")){
							refimage.setBackgroundResource(R.drawable.fsu);                                                                                                                                           
						}
					
					 else if (image_url.equalsIgnoreCase("PillarBranding.jpg")){
							refimage.setBackgroundResource(R.drawable.pillarbranding);                                                                                                                                           
						}
					
					 else if (image_url.equalsIgnoreCase("Parasite.jpg")){
							refimage.setBackgroundResource(R.drawable.parasite);                                                                                                                                           
						}
					 else if (image_url.equalsIgnoreCase("Chem1st.jpg")){
							refimage.setBackgroundResource(R.drawable.chemfirst);                                                                                                                                           
						}
					
					 else if (image_url.equalsIgnoreCase("ClipStrips.jpg")){
							refimage.setBackgroundResource(R.drawable.clipstrips);                                                                                                                                           
						}*/
					dialog.show();
					
					
				
				} else {
					
					Toast.makeText(BeforeAdditionalDisplay.this, "Please Select Display", Toast.LENGTH_LONG).show();

				}
				
				
			}
		});
		
		list = db.getProductEntryDetail(store_id, category_id, process_id);
		
		if (list.size()>0) {
			adapterData = new MyAdaptor(BeforeAdditionalDisplay.this,list);
			
	    	listview.setAdapter(adapterData);
	    	listview.invalidateViews();
		}
		
//	show.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//			
//				} else {
//					Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_LONG).show();
//				}
//				
//				
//			}
//		});
		
	
		
	}
	
	
	
	 public void onButtonClick(View v){
         
         
         if (v.getId() == R.id.camera) {

        	 if (brand_name != null && !brand_id.equalsIgnoreCase("") && display_name != null && !display_id.equalsIgnoreCase("")) {
        		 _pathforcheck = store_id+"_1_BA"+ imgDate + brand_id + display_id +".jpg";
                 _path = str + _pathforcheck;
                 startCameraActivity();
     		}
        	 
        	 else{
        		 Toast.makeText(BeforeAdditionalDisplay.this, "Please select Brand and Display first", Toast.LENGTH_LONG).show();
        	 }

         
         
         }          
         
}
	 
	 
	 @Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent in = new Intent(BeforeAdditionalDisplay.this, DailyEntryMainMenu.class);
		startActivity(in);
		BeforeAdditionalDisplay.this.finish();
		
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

	
	public boolean validatedata() {
		boolean result = false;
		if (brand_name != null && !brand_id.equalsIgnoreCase("") && display_name != null && !display_id.equalsIgnoreCase("")) {
			result = true;
		}

		return result;

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
						BeforeAdditionalDisplay.this);
		 
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
								
								db.deleteProductEntry(list.get(position).getKey_id());
								
								adapterData.notifyDataSetChanged();
								
								list = db.getProductEntryDetail(store_id, category_id, process_id);
								listview.setAdapter(new MyAdaptor(BeforeAdditionalDisplay.this, list));
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
