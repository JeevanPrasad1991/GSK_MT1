package com.cpm.DailyEntry;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.net.nsd.NsdManager.RegistrationListener;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
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
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString;
import com.cpm.database.GSKMTDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.ShelfVisibilityBean;
import com.cpm.delegates.SkuBean;
import com.cpm.delegates.TOTBean;
import com.example.gsk_mtt.R;

public class AfterStockActivity extends Activity implements OnClickListener {

	private SharedPreferences preferences;
	private SharedPreferences.Editor editor = null;

	public ArrayList<TOTBean> question_list = new ArrayList<>();
	ListView lv, lv2;
	Button save, save2, shelf_visibility, cancel;
	ImageView cam1, cam2, cam3, cam4;
	public static ArrayList<SkuBean> sku_brand_list = new ArrayList<>();
	private CustomKeyboardView mKeyboardView;
	boolean check = true;
	LinearLayout imglayout;
	TextView sos, sos_target;
	String primary_windowIssues = "";
	static int mposition = -1;
	Boolean update = false;
	Boolean update1 = false;

	public static ArrayList<SkuBean> sku_brand_list_second = new ArrayList<SkuBean>();
	public static ArrayList<ShelfVisibilityBean> shelf_list = new ArrayList<ShelfVisibilityBean>();
	public static ArrayList<SkuBean> stock_images_list = new ArrayList<SkuBean>();

	public static ArrayList<SkuBean> sos_target_list = new ArrayList<SkuBean>();

	GSKMTDatabase db;
	public String store_id, category_id, process_id, intime, username, app_version, region_id, store_type_id;

	String paked_key;

	protected static String _pathforcheck_gap = "";
	protected static String _pathforcheck = "";
	protected static String _pathforcheck1 = "";
	public String image1 = "", image2 = "", image3 = "", _path, date, imgDate, image4 = "";
	private static String str, path;
	String img1 = "", img2 = "", reason_id = "0", remark = "", img = "";
	String _Currentdate;
	static int row_pos = 10000;
	public static ArrayList<String> allthreeDates = new ArrayList<String>();


	ArrayList<CoverageBean> coveragelist = new ArrayList<CoverageBean>();
	static int currentVersion = 1;
	private Keyboard mKeyboard;
	Button primarywindow;
	int child_position = -1;

	String error_msg = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sku_afterstockdisplay);

		lv = (ListView) findViewById(R.id.ListView01);

		primarywindow = (Button) findViewById(R.id.primary_window);

		currentVersion = android.os.Build.VERSION.SDK_INT;

		preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
		category_id = preferences.getString(CommonString.KEY_CATEGORY_ID, null);
		process_id = preferences.getString(CommonString.KEY_PROCESS_ID, null);
		date = preferences.getString(CommonString.KEY_DATE, null);
		intime = preferences.getString(CommonString.KEY_IN_TIME, null);
		username = preferences.getString(CommonString.KEY_USERNAME, null);
		app_version = preferences.getString(CommonString.KEY_VERSION, null);
		region_id = preferences.getString(CommonString.region_id, null);
		store_type_id = preferences.getString(CommonString.storetype_id, null);


		paked_key = preferences.getString(CommonString.KEY_PACKED_KEY, null);

		if (isTablet(getApplicationContext())) {
			mKeyboard = new Keyboard(this, R.xml.keyboard);
//			Toast.makeText(AfterStockActivity.this, "Hi ! i am tablet", Toast.LENGTH_LONG).show();

		} else {
			mKeyboard = new Keyboard(this, R.xml.keyboard1);

		}


		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this));

//		sos = (TextView)findViewById(R.id.textBtn);
		save = (Button) findViewById(R.id.savebtn);

		cam1 = (ImageView) findViewById(R.id.cam1);
		cam2 = (ImageView) findViewById(R.id.cam2);
		cam3 = (ImageView) findViewById(R.id.cam3);
		cam4 = (ImageView) findViewById(R.id.cam4);
		LinearLayout otherStockafter = (LinearLayout) findViewById(R.id.listlayout);
		LinearLayout test = (LinearLayout) findViewById(R.id.test);
		imglayout = (LinearLayout) findViewById(R.id.imglayout);

		sos_target = (TextView) findViewById(R.id.sos_target);
		shelf_visibility = (Button) findViewById(R.id.shelf_visibility);

		db = new GSKMTDatabase(AfterStockActivity.this);
		db.open();
//		save2.setOnClickListener(this);
		sku_brand_list_second = db.getInsertedDisplayListAfterStock(category_id, store_id, process_id);


		/*if (!sku_brand_list_second.get(0).getYesNo().equalsIgnoreCase("")) {

			primarywindow.setBackgroundColor(Color.GREEN);
			primarywindow.setText("Primary Window (Saved)");

		}else{
			primarywindow.setBackgroundColor(Color.parseColor("#ff9933"));
			primarywindow.setText("Primary Window");
		}*/


		sos_target_list = db.getSOSTarget(store_id, category_id, process_id);

		if (sos_target_list.size() > 0) {
			if (!sos_target_list.get(0).getSos_target().equalsIgnoreCase("")) {
				sos_target.setText(sos_target_list.get(0).getSos_target());
			} else {
				sos_target.setText("0");
			}
		} else {
			sos_target.setText("0");
		}


		if (sku_brand_list_second.size() == 0) {
			sku_brand_list_second = db.getDisplayListAfterStock(category_id, store_id, process_id);

			if (sku_brand_list_second.size() == 0) {
				primarywindow.setEnabled(false);


			} else {
				primarywindow.setEnabled(true);

			}

		}

		if (sku_brand_list_second.size() == 0) {
			/*save2.setVisibility(View.INVISIBLE);
			otherStockafter.setVisibility(View.GONE);
			imglayout.setVisibility(View.VISIBLE);
			test.getLayoutParams().height = 460;*/
		/*
			LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) lv.getLayoutParams();
			 lp.height = 300;
			 lv.setLayoutParams(lp);
			*/

		}


//		lv2.setAdapter(new skulistDataOther(this));


		coveragelist = db.getCoverageData(date, store_id, process_id);

		if (coveragelist.get(0).getImage_allow().equalsIgnoreCase("NOT Allowed")) {

			cam1.setEnabled(false);
			cam2.setEnabled(false);
			cam3.setEnabled(false);

		} else {


			cam1.setEnabled(true);
			cam2.setEnabled(true);
			cam3.setEnabled(true);
		}


		if ((new File(Environment.getExternalStorageDirectory() + "/MT_GSK_Images/")).exists()) {
			Log.i("directory is created", "directory is created");
		} else {
			(new File(Environment.getExternalStorageDirectory() + "/MT_GSK_Images/")).mkdir();
		}

		str = Environment.getExternalStorageDirectory() + "/MT_GSK_Images/";

		imgDate = date.replace("/", "-");
		String pkd;
		sku_brand_list = db.getAfterStockData2(store_id, category_id, region_id, store_type_id, process_id);

		if (sku_brand_list.size() == 0) {

			sku_brand_list = db.getBrandSkuList(category_id, store_id, process_id, region_id, store_type_id);
		}


		if (paked_key.equalsIgnoreCase("n")) {
			for (int i = 0; i < sku_brand_list.size(); i++)

			{

				sku_brand_list.get(i).setATHREE_TO_SIX("0");
				sku_brand_list.get(i).setALAST_THREE("0");
				sku_brand_list.get(i).setAMORE_SIX("0");

			}

		}
		if (sku_brand_list.size() == 0) {

			sku_brand_list = db.getBrandSkuList(category_id, store_id, process_id, region_id, store_type_id);

		} else {
			update = true;
			save.setText("SAVE");

			if (sku_brand_list.size() > 0) {
				System.out.println("" + sku_brand_list.size());
				lv.setAdapter(new MyAdaptor(this, paked_key));

				stock_images_list = db.getStockImagesLIST(store_id, category_id, username, process_id);

				for (int i = 0; i < stock_images_list.size(); i++) {

					if (!stock_images_list.get(i).getImage5().equalsIgnoreCase("")) {
						image1 = stock_images_list.get(i).getImage5();
						cam1.setBackgroundResource(R.drawable.camera_tick_ico);

					} else {
						cam1.setBackgroundResource(R.drawable.camera_ico);

					}


					if (!stock_images_list.get(i).getImage6().equalsIgnoreCase("")) {
						image2 = stock_images_list.get(i).getImage6();
						cam2.setBackgroundResource(R.drawable.camera_tick_ico);
					} else {
						cam2.setBackgroundResource(R.drawable.camera_ico);


					}

					if (!stock_images_list.get(i).getImage7().equalsIgnoreCase("")) {
						image3 = stock_images_list.get(i).getImage7();
						cam3.setBackgroundResource(R.drawable.camera_tick_ico);

					} else {
						cam3.setBackgroundResource(R.drawable.camera_ico);


					}

					if (!stock_images_list.get(i).getImage8().equalsIgnoreCase("")) {
						image4 = stock_images_list.get(i).getImage8();
						cam4.setBackgroundResource(R.drawable.camera_tick_ico);
					} else {
						cam4.setBackgroundResource(R.drawable.camera_ico);


					}

				}

			}


		}






				/*update = true;
				save2.setText("Update");
				for (int i2 = 0; i2 < shelf_list.size(); i2++) {

					if (!shelf_list.get(i2).getImage().equalsIgnoreCase("")) {

						shelf_list.get(i2).setCamera("YES");
						shelf_list.get(i2).setImage(shelf_list.get(i2).getImage());
					} else {
						shelf_list.get(i2).setCamera("NO");
						shelf_list.get(i2).setImage("");*/


		allthreeDates = getpackedDate(category_id);

		save.setOnClickListener(this);

		if (!category_id.equalsIgnoreCase("2")) {
			shelf_visibility.setEnabled(false);
		} else {
			shelf_visibility.setEnabled(true);
		}


		primarywindow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final Dialog dialog2 = new Dialog(AfterStockActivity.this);
				dialog2.setContentView(R.layout.primarywindow);
				dialog2.setTitle("Primary Category");
				dialog2.setCancelable(false);

				lv2 = (ListView) dialog2.findViewById(R.id.listView2);
				save2 = (Button) dialog2.findViewById(R.id.Save2);
				cancel = (Button) dialog2.findViewById(R.id.cancel);


				sku_brand_list_second = db.getInsertedDisplayListAfterStock(category_id, store_id, process_id);

				if (sku_brand_list_second.size() == 0) {
					sku_brand_list_second = db.getDisplayListAfterStock(category_id, store_id, process_id);
				}

				lv2.setAdapter(new skulistDataOther(AfterStockActivity.this));


				save2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
//						sku_brand_list_second = db.getInsertedDisplayListAfterStock(category_id, store_id);
						if (conditionForPrimaryWindows()) {
							AlertDialog.Builder builder = new AlertDialog.Builder(AfterStockActivity.this);
							builder.setMessage("Are you sure you want to save").setCancelable(false).setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog1, int id) {


//				              db.InsertCoverage(store_id, date, intime, getCurrentTime(),
//												reason_id, remark,username,app_version,process_id);

//				                	 db.deleteQuestionList(sku_brand_list_second, store_id, category_id);`


											db.SaveData(sku_brand_list_second, category_id, store_id, process_id);

											dialog2.dismiss();

										}
									}).setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog1, int id) {
											dialog1.cancel();
										}
									});
							AlertDialog alert = builder.create();

							alert.show();
						} else {
							Toast.makeText(AfterStockActivity.this, error_msg, Toast.LENGTH_LONG).show();

						}


					}
				});


				cancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						AlertDialog.Builder builder = new AlertDialog.Builder(AfterStockActivity.this);
						builder.setMessage("Are you sure you want to quit?").setCancelable(false).setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {

										if (conditionForPrimaryWindowsa()) {

											dialog2.dismiss();
//				        			Intent in = new Intent(AfterStockActivity.this, AfterStockActivity.class);
//									startActivity(in);
//									AfterStockActivity.this.finish();
										} else {
											db.deleteQuestionList(sku_brand_list_second, store_id, category_id, process_id);
											dialog2.dismiss();
								/*Intent in = new Intent(AfterStockActivity.this, AfterStockActivity.class);
								startActivity(in);*/
//								AfterStockActivity.this.finish();
										}


									}
								}).setNegativeButton("Back",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										dialog.cancel();
									}
								});
						AlertDialog alert = builder.create();

						alert.show();

					}
				});

				dialog2.show();


			}
		});


		shelf_visibility.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog2 = new Dialog(AfterStockActivity.this);
				dialog2.setContentView(R.layout.dialog_shelf);
				dialog2.setTitle("Shelf Visibility");

				lv2 = (ListView) dialog2.findViewById(R.id.listView2);
				save2 = (Button) dialog2.findViewById(R.id.Save2);

				if (category_id.equalsIgnoreCase("2")) {


					shelf_list = db.getInsertedShelfData(store_id, category_id, process_id, store_type_id);
					if (shelf_list.size() == 0) {
						shelf_list = db.getShelfData(process_id, region_id, store_type_id);
					} else {
						update1 = true;
						save2.setText("Update");
						for (int i2 = 0; i2 < shelf_list.size(); i2++) {

							if (!shelf_list.get(i2).getImage().equalsIgnoreCase("")) {

								shelf_list.get(i2).setCamera("YES");
								shelf_list.get(i2).setImage(shelf_list.get(i2).getImage());
							} else {
								shelf_list.get(i2).setCamera("NO");
								shelf_list.get(i2).setImage("");
							}

						}

					}


				}


				lv2.setAdapter(new ShelfVisibility(AfterStockActivity.this));



				save2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (Checkcondition()) {
							AlertDialog.Builder builder = new AlertDialog.Builder(AfterStockActivity.this);
							builder.setMessage("Are you sure you want to save").setCancelable(false).setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog1, int id) {
			                	 /*shelf_list, String process_id, String store_typeid,
			 					String category_id, String store_id*/

											if (update1) {
												for (int i = 0; i < shelf_list.size(); i++) {

													db.updateShelfData(store_id, shelf_list.get(i).getBrand_id(), shelf_list.get(i).getShelf_id(),
															process_id, store_type_id, shelf_list.get(i).getAnswer(), shelf_list.get(i).getImage());

													dialog2.dismiss();
												}
											} else {
												db.SaveShelfData(shelf_list, process_id, store_type_id, category_id, store_id);
												dialog2.dismiss();

											}

										}
									}).setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog1, int id) {
											dialog1.cancel();
										}
									});
							AlertDialog alert = builder.create();

							alert.show();
						} else {
							Toast.makeText(AfterStockActivity.this, "All images are compulsory !", Toast.LENGTH_LONG).show();

						}


					}
				});

				dialog2.show();
			}
		});

		lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

			}

			@Override
			public void onScrollStateChanged(AbsListView arg0, int scrollState) {
				lv.clearFocus();
				if (SCROLL_STATE_TOUCH_SCROLL == scrollState) {
					View currentFocus = getCurrentFocus();
					if (currentFocus != null) {
						currentFocus.clearFocus();
					}
				}
			}

		});

	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
	}


	@Override
	public void onBackPressed() {

		if (mKeyboardView.getVisibility() == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
			/*mKeyboardView.requestFocusFromTouch();*/
		} else {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to quit ?").setCancelable(false).setPositiveButton("YES",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							Intent in = new Intent(AfterStockActivity.this, DailyEntryMainMenu.class);
							startActivity(in);
							AfterStockActivity.this.finish();

						}
					}).setNegativeButton("NO",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert = builder.create();

			alert.show();
		}

	}


	public boolean Checkcondition() {
		boolean result = true;

		for (int i = 0; i < shelf_list.size(); i++) {

			if (shelf_list.get(i).getImage().equalsIgnoreCase("")) {


				result = false;
				break;
			}


		}
		return result;
	}


	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}


	static class ViewHolder {


		TextView text, brand_name;

		LinearLayout l1;
		LinearLayout l2;
		EditText last_one, last_two, last_three;
		EditText stockQuantity, faceup;

	}


	public void onButtonClick(View v) {
		switch (v.getId()) {

			case R.id.cam1:
				_pathforcheck = store_id + "_1_AS" + process_id + username + imgDate + category_id + process_id + ".jpg";
				_path = str + _pathforcheck;
				startCameraActivity(0);
				break;
			case R.id.cam2:
				_pathforcheck = store_id + "_2_AS" + process_id + username + imgDate + category_id + process_id + ".jpg";
				_path = str + _pathforcheck;
				startCameraActivity(0);
				break;
			case R.id.cam3:
				_pathforcheck = store_id + "_3_AS" + process_id + username + imgDate + category_id + process_id + ".jpg";
				_path = str + _pathforcheck;
				startCameraActivity(0);
				break;


			case R.id.cam4:
				_pathforcheck = store_id + "_4_AS" + process_id + username + imgDate + category_id + process_id + ".jpg";
				_path = str + _pathforcheck;
				startCameraActivity(0);
				break;

		}
	}

	protected void startCameraActivity(int request_code) {

		try {
			Log.i("MakeMachine", "startCameraActivity()");
			File file = new File(_path);
			Uri outputFileUri = Uri.fromFile(file);

			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

			startActivityForResult(intent, request_code);
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
				if(requestCode==1){
					if (_pathforcheck_gap != null && !_pathforcheck_gap.equals("")) {
						if (new File(str + _pathforcheck_gap).exists()) {
							img = _pathforcheck_gap;
							lv2.invalidateViews();
							_pathforcheck_gap = "";
						}
					}
				}
				else {

						if (_pathforcheck != null && !_pathforcheck.equals("")) {
							if (new File((str + _pathforcheck).trim()).exists()) {

								img1 = _pathforcheck;
								String value = img1.substring(img1.indexOf("_") + 1, img1.lastIndexOf("_"));
								if (value != null) {
									if (value.equalsIgnoreCase("1")) {

//                                                                                      stockdata.get(0).setCamImage1(img1.replace("_", ""));
										image1 = img1;
										cam1.setBackgroundResource(R.drawable.camera_tick_ico);
										imglayout.setBackgroundColor(Color.WHITE);
									} else if (value.equalsIgnoreCase("2")) {
//                                                                                         stockdata.get(0).setCamImage2(img1.replace("_", ""));
										image2 = img1;
										cam2.setBackgroundResource(R.drawable.camera_tick_ico);
										imglayout.setBackgroundColor(Color.WHITE);
									} else if (value.equalsIgnoreCase("3")) {
//                                                                           stockdata.get(0).setCamImage2(img1.replace("_", ""));
										image3 = img1;
										cam3.setBackgroundResource(R.drawable.camera_tick_ico);
										imglayout.setBackgroundColor(Color.WHITE);
									} else {
//                                                                                         stockdata.get(0).setCamImage3(img1.replace("_", ""));
										image4 = img1;
										cam4.setBackgroundResource(R.drawable.camera_tick_ico);
										imglayout.setBackgroundColor(Color.WHITE);
									}
								}
								//                            audit_lv.invalidateViews();
								_pathforcheck = "";
								break;

							}
						} else if (_pathforcheck1 != null && !_pathforcheck1.equals("")) {
							if (new File(str + _pathforcheck1).exists()) {

								img2 = _pathforcheck1;
								lv2.invalidateViews();
								_pathforcheck1 = "";
								break;

							}

						}

				}


				break;
		}
	}


	private class MyAdaptor extends BaseAdapter {
		LayoutInflater mInflater;
		String packedKey;
		private Context mcontext;

		public MyAdaptor(Context context, String key) {

			mInflater = LayoutInflater.from(context);
			mcontext = context;
			packedKey = key;

		}

		@Override
		public int getCount() {

			return sku_brand_list.size();
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.sku_row_after, null);
				holder = new ViewHolder();

				holder.brand_name = (TextView) convertView
						.findViewById(R.id.brand);

				holder.text = (TextView) convertView
						.findViewById(R.id.mainpage_rememberme);
				holder.stockQuantity = (EditText) convertView
						.findViewById(R.id.editText2);
				holder.faceup = (EditText) convertView
						.findViewById(R.id.editText1);
				holder.l1 = (LinearLayout) convertView
						.findViewById(R.id.mainpage_header);
				holder.l2 = (LinearLayout) convertView
						.findViewById(R.id.mainpage_header2);

				holder.last_one = (EditText) convertView
						.findViewById(R.id.last_one);

				holder.last_two = (EditText) convertView
						.findViewById(R.id.last_two);

				holder.last_three = (EditText) convertView
						.findViewById(R.id.last_three);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}


			if (currentVersion >= 11) {
				holder.stockQuantity.setTextIsSelectable(true);
				holder.stockQuantity.setRawInputType(InputType.TYPE_CLASS_TEXT);


				holder.faceup.setTextIsSelectable(true);
				holder.faceup.setRawInputType(InputType.TYPE_CLASS_TEXT);


				holder.last_one.setTextIsSelectable(true);
				holder.last_one.setRawInputType(InputType.TYPE_CLASS_TEXT);

				holder.last_two.setTextIsSelectable(true);
				holder.last_two.setRawInputType(InputType.TYPE_CLASS_TEXT);

				holder.last_three.setTextIsSelectable(true);
				holder.last_three.setRawInputType(InputType.TYPE_CLASS_TEXT);


			} else {
				holder.stockQuantity.setInputType(0);
				holder.faceup.setInputType(0);

				holder.last_one.setInputType(0);
				holder.last_two.setInputType(0);
				holder.last_three.setInputType(0);


			}


			holder.last_one.setHint(allthreeDates.get(0));
			holder.last_two.setHint(allthreeDates.get(1));
			holder.last_three.setHint("<" + allthreeDates.get(2));


			if (position == 0) {
				holder.brand_name.setText(sku_brand_list.get(position).getBrand());
				holder.text.setText(sku_brand_list.get(position).getSku_name());

			} else {

				if (sku_brand_list.get(position - 1).getBrand()
						.equalsIgnoreCase(sku_brand_list.get(position).getBrand())) {
					holder.brand_name.setText("");
					holder.text.setText(sku_brand_list.get(position).getSku_name());

				} else {

					holder.brand_name.setText(sku_brand_list.get(position).getBrand());
					holder.text.setText(sku_brand_list.get(position).getSku_name());

				}

			}


			holder.stockQuantity.setOnFocusChangeListener(new OnFocusChangeListener() {
				public void onFocusChange(View v, boolean hasFocus) {

					if(hasFocus){
						showKeyboardWithAnimation();
					}

					if (!hasFocus) {

						hide();

						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();

						if (value1.equals("")) {

							sku_brand_list.get(position).setAfter_Stock("");
							lv.invalidateViews();

						} else {

							sku_brand_list.get(position).setAfter_Stock(value1);
							lv.invalidateViews();
						}

					}
				}
			});

			holder.faceup.setOnFocusChangeListener(new OnFocusChangeListener() {
				public void onFocusChange(View v, boolean hasFocus) {

					if(hasFocus){
						showKeyboardWithAnimation();
					}

					if (!hasFocus) {

						hide();

						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();

						if (value1.equals("")) {

							sku_brand_list.get(position).setAfter_faceup("");

						} else {

							sku_brand_list.get(position).setAfter_faceup(value1);
						}

					}
				}
			});


			holder.last_one.setOnFocusChangeListener(new OnFocusChangeListener() {
				public void onFocusChange(View v, boolean hasFocus) {

					if(hasFocus){
						showKeyboardWithAnimation();
					}

					if (!hasFocus) {

						hide();

						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();
						if (value1.equals("")) {
							sku_brand_list.get(position).setALAST_THREE("");
						} else {

							sku_brand_list.get(position).setALAST_THREE(value1);

						}

					}
				}
			});

			holder.last_two.setOnFocusChangeListener(new OnFocusChangeListener() {
				public void onFocusChange(View v, boolean hasFocus) {

					if(hasFocus){
						showKeyboardWithAnimation();
					}

					if (!hasFocus) {

						hide();

						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();
						if (value1.equals("")) {
							sku_brand_list.get(position).setATHREE_TO_SIX("");
						} else {
							sku_brand_list.get(position).setATHREE_TO_SIX(value1);

						}

					}
				}
			});

			holder.last_three
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {
							if(hasFocus){
								showKeyboardWithAnimation();
							}

							if (!hasFocus) {

								hide();

								showKeyboardWithAnimation();
								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();
								if (value1.equals("")) {
									sku_brand_list.get(position).setAMORE_SIX("");
								} else {
									sku_brand_list.get(position).setAMORE_SIX(value1);
								}

							}
						}
					});


			if (!sku_brand_list.get(position).getCompany_id().equalsIgnoreCase("1")) {

				sku_brand_list.get(position).setAfter_Stock("0");
				sku_brand_list.get(position).setALAST_THREE("0");
				sku_brand_list.get(position).setATHREE_TO_SIX("0");
				sku_brand_list.get(position).setAMORE_SIX("0");

				holder.l1.setBackgroundColor(Color.YELLOW);
				holder.l2.setVisibility(View.GONE);
				holder.stockQuantity.setVisibility(View.INVISIBLE);

				holder.faceup.setEnabled(true);

			} else {

				if (sku_brand_list.get(position).getAfter_Stock().equalsIgnoreCase("0")) {

					holder.stockQuantity.setVisibility(View.VISIBLE);
					holder.l1.setBackgroundColor(Color.WHITE);
					holder.l2.setVisibility(View.VISIBLE);
					holder.l2.setBackgroundColor(Color.WHITE);

					sku_brand_list.get(position).setAfter_faceup("0");
					sku_brand_list.get(position).setALAST_THREE("0");
					sku_brand_list.get(position).setATHREE_TO_SIX("0");
					sku_brand_list.get(position).setAMORE_SIX("0");

					holder.faceup.setText("0");
					holder.faceup.setEnabled(false);
					holder.last_one.setText("0");
					holder.last_one.setEnabled(false);
					holder.last_two.setText("0");
					holder.last_two.setEnabled(false);
					holder.last_three.setText("0");
					holder.last_three.setEnabled(false);


				} else {

					holder.stockQuantity.setVisibility(View.VISIBLE);
					holder.l1.setBackgroundColor(Color.WHITE);
					holder.l2.setVisibility(View.VISIBLE);
					holder.l2.setBackgroundColor(Color.WHITE);
					holder.faceup.setEnabled(true);
					holder.last_one.setEnabled(true);
					holder.last_two.setEnabled(true);
					holder.last_three.setEnabled(true);


				}

			}


			if (packedKey.equalsIgnoreCase("n")) {
				holder.last_one.setText("0");
				holder.last_two.setText("0");
				holder.last_three.setText("0");

				holder.last_one.setEnabled(false);
				holder.last_two.setEnabled(false);
				holder.last_three.setEnabled(false);
				holder.last_one.setBackgroundColor(Color.DKGRAY);
				holder.last_two.setBackgroundColor(Color.DKGRAY);
				holder.last_three.setBackgroundColor(Color.DKGRAY);


			}

			if (position == row_pos - 1) {

				holder.l1.setBackgroundColor(Color.RED);
				holder.l2.setBackgroundColor(Color.RED);
			}


			if (!check) {

				if (holder.stockQuantity.getText().toString().equalsIgnoreCase("")) {
					holder.stockQuantity.setHint("Empty");
					holder.stockQuantity.setHintTextColor(Color.RED);
				}


				if (holder.faceup.getText().toString().equalsIgnoreCase("")) {
					holder.faceup.setHint("Empty");
					holder.faceup.setHintTextColor(Color.RED);
				}

				if (holder.last_one.getText().toString().equalsIgnoreCase("")) {
					holder.last_one.setHint("Empty");
					holder.last_one.setHintTextColor(Color.RED);
				}


				if (holder.last_two.getText().toString().equalsIgnoreCase("")) {
					holder.last_two.setHint("Empty");
					holder.last_two.setHintTextColor(Color.RED);
				}


				if (holder.last_three.getText().toString().equalsIgnoreCase("")) {
					holder.last_three.setHint("Empty");
					holder.last_three.setHintTextColor(Color.RED);
				}

			}

			holder.stockQuantity.setText(sku_brand_list.get(position).getAfter_Stock());
			holder.faceup.setText(sku_brand_list.get(position).getAfter_faceup());

			holder.last_one.setText(sku_brand_list.get(position).getALAST_THREE());
			holder.last_two.setText(sku_brand_list.get(position).getATHREE_TO_SIX());
			holder.last_three.setText(sku_brand_list.get(position).getAMORE_SIX());

			holder.stockQuantity.setId(position);
			holder.faceup.setId(position);

			holder.last_one.setId(position);
			holder.last_two.setId(position);
			holder.last_three.setId(position);


			return convertView;
		}

	}


	private void showKeyboardWithAnimation() {

		if (mKeyboardView.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils
					.loadAnimation(AfterStockActivity.this,
							R.anim.slide_in_bottom);
			mKeyboardView.showWithAnimation(animation);
		} else if (mKeyboardView.getVisibility() == View.INVISIBLE) {
			mKeyboardView.setVisibility(View.VISIBLE);
		}
	}


	public ArrayList<String> getpackedDate(String catid) {

		ArrayList<String> threeDates = new ArrayList<String>();

		if (catid.equalsIgnoreCase("1")) {


			SimpleDateFormat df = new SimpleDateFormat("MMM-yy");
			//String formattedDate = df.format(c.getTime());


			Date currentDate = new Date();
			System.out.println(currentDate);
			long milliseconds = (long) 365 * 24 * 60 * 60 * 1000;


			Date oneYearBefore = new Date(currentDate.getTime() - milliseconds);
			System.out.println(oneYearBefore);


			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(oneYearBefore);
			cal2.add(Calendar.MONTH, 1);
			String firstTextBO = df.format(cal2.getTime());

			cal2.setTime(oneYearBefore);
			cal2.add(Calendar.MONTH, 3);

			String firstTextBoxEndvalue = df.format(cal2.getTime());

			String first_date = firstTextBO + " " + firstTextBoxEndvalue;


			cal2.add(Calendar.MONTH, +1);

			String secondTextbox = df.format(cal2.getTime());

			cal2.add(Calendar.MONTH, +2);

			String secondTextboxEndValue = df.format(cal2.getTime());

			String SecondTextValue = secondTextbox + " " + secondTextboxEndValue;


			cal2.add(Calendar.MONTH, +1);

			String lasttextBox = df.format(cal2.getTime());
			String last = " above " + lasttextBox;


			String thrrdto_sixdate = "";
			String sixtonine = "";
			threeDates.add(first_date);
			threeDates.add(SecondTextValue);
			threeDates.add(last);


		} else {


			SimpleDateFormat df = new SimpleDateFormat("MMM-yy");
			//String formattedDate = df.format(c.getTime());


			Calendar cal = Calendar.getInstance();  //Get current date/month
			cal.add(Calendar.MONTH, -6);   //Go to date, 6 months ago


			String lastabove = "above" + df.format(cal.getTime());
			System.out.println(lastabove);

//	            cal.add(Calendar.MONTH, -7);


			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(cal.getTime());
			cal1.add(Calendar.MONTH, -1);

			String secondboxlast = df.format(cal1.getTime());
			System.out.println("" + secondboxlast);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(cal1.getTime());
			cal2.add(Calendar.MONTH, -6);
			String secondBoxFirst = df.format(cal2.getTime());
			System.out.println("" + secondBoxFirst);


			String secondTextValue = secondBoxFirst + " " + secondboxlast;

			System.out.println("" + secondTextValue);


			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(cal2.getTime());
			cal3.add(Calendar.MONTH, -1);
			String firstBoxLast = df.format(cal3.getTime());
			System.out.println("" + firstBoxLast);


			Calendar cal4 = Calendar.getInstance();
			cal4.setTime(cal3.getTime());
			cal4.add(Calendar.MONTH, -6);
			String firstBoxFirst = df.format(cal4.getTime());


			String first_date = firstBoxFirst + " " + firstBoxLast;


	         /*   Date currentDate = new Date();
	            System.out.println(currentDate);
	            long milliseconds = (long) 365 * 24 * 60 * 60 * 1000;


	            Date oneYearBefore = new Date(currentDate.getTime() - milliseconds);
	            System.out.println(oneYearBefore);
	          */

	          /*  Calendar cal2=Calendar.getInstance();
	            cal2.setTime(oneYearBefore);
	            cal2.add(Calendar.MONTH,-11);
	            String firstTextBO = df.format(cal2.getTime());
	                       */
	           /* cal2.setTime(oneYearBefore);
	            cal2.add(Calendar.MONTH,5);
	            cal2.add(Calendar.MONTH,-11);

	            String firstTextBoxEndvalue=df.format(cal2.getTime());

//	            String first_date =firstTextBO+" "+firstTextBoxEndvalue;




	            cal2.add(Calendar.MONTH,+1);


	            String secondTextbox=df.format(cal2.getTime());

	            cal2.add(Calendar.MONTH,5);


	            String secondTextboxEndValue=df.format(cal2.getTime());

	            String SecondTextValue=secondTextbox+" "  +secondTextboxEndValue;


	            cal2.add(Calendar.MONTH,+1);

	            String lasttextBox=df.format(cal2.getTime());
	            String last=" above "+lasttextBox;

	            */

			String thrrdto_sixdate = "";
			String sixtonine = "";
			threeDates.add(first_date);
			threeDates.add(secondTextValue);
			threeDates.add(lastabove);


		}

		return threeDates;


	}

	public ArrayList<String> getpackedDatedd2() {

		ArrayList<String> threeDates = new ArrayList<String>();

		SimpleDateFormat df = new SimpleDateFormat("MMM-yy");
		//String formattedDate = df.format(c.getTime());
		String firstto_3date = "";
		String thrrdto_sixdate = "";
		String sixtonine = "";


		Calendar cal = Calendar.getInstance();
		String formattedDatecurrent = df.format(cal.getTime());
		firstto_3date = formattedDatecurrent;
		// substract 7 days

		cal.set(Calendar.DAY_OF_MONTH, -1);

		// convert to date
		Date myDate = cal.getTime();

		String formattedDate2 = df.format(cal.getTime());

		cal.set(Calendar.DAY_OF_MONTH, -1);

		// convert to date

		String formattedDate3 = df.format(cal.getTime());

		firstto_3date += " " + formattedDate3;
		threeDates.add(firstto_3date);

		cal.set(Calendar.DAY_OF_MONTH, -1);

		// convert to date
		String formattedDate4 = df.format(cal.getTime());

		thrrdto_sixdate = formattedDate4;
		cal.set(Calendar.DAY_OF_MONTH, -1);

		// convert to date
		String formattedDate5 = df.format(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, -1);

		// convert to date
		String formattedDate6 = df.format(cal.getTime());
		thrrdto_sixdate += " " + formattedDate6;
		threeDates.add(thrrdto_sixdate);

		cal.set(Calendar.DAY_OF_MONTH, -1);

		// convert to date
		String formattedDate7 = df.format(cal.getTime());
		sixtonine = formattedDate7;

		cal.set(Calendar.DAY_OF_MONTH, -1);

		// convert to date
		String formattedDate8 = df.format(cal.getTime());
		cal.set(Calendar.DAY_OF_MONTH, -1);

		// convert to date
		String formattedDate9 = df.format(cal.getTime());
		sixtonine += " " + formattedDate9;
		threeDates.add(sixtonine);

		// convert to date
		return threeDates;
	}


	public boolean validate_values() {
		boolean result = true;

		for (int i = 0; i < sku_brand_list.size(); i++) {

			if (sku_brand_list.get(i).getAfter_Stock().equals("")) {

				if (!sku_brand_list.get(i).getAfter_faceup().equals("")) {
					result = false;
					break;
				}

			}

			if (sku_brand_list.get(i).getAfter_faceup().equals("")) {

				if (!sku_brand_list.get(i).getAfter_Stock().equals("")) {
					result = false;
					break;
				}

			}

		}

		return result;

	}


	public boolean conditionForPrimaryWindows() {
		boolean result = true;

		for (int i = 0; i < sku_brand_list_second.size(); i++) {

			if (sku_brand_list_second.get(i).getExist()==-1) {

				result = false;
				error_msg = "Select exist spinner";
				break;
			} else if (sku_brand_list_second.get(i).getExist()==1) {
				question_list = db.getInsertedQuestionsData(store_id, sku_brand_list_second.get(i).getDisplay_id(),
						category_id, process_id);

				if (question_list.size() == 0) {
					result = false;
					error_msg = "Please fill Gaps data";
					break;

				} else {
					result = true;
				}

				if(result){
					if(sku_brand_list_second.get(i).getBrand_img().equals("")){
						result = false;
						error_msg = "Please click image";
						break;
					}
				}

			}

		}
		return result;
	}


	public boolean conditionForPrimaryWindowsa() {
		boolean result = true;

		sku_brand_list_second = db.getInsertedDisplayListAfterStock(category_id, store_id, process_id);

		for (int i = 0; i < sku_brand_list_second.size(); i++) {

			if (sku_brand_list_second.get(i).getYesNo().equalsIgnoreCase("NO")) {

				question_list = db.getInsertedQuestionsData(store_id, sku_brand_list_second.get(i).getDisplay_id(),
						category_id, process_id);

				if (question_list.size() > 0) {
					result = false;
					break;
				} else {
					result = true;
				}


			} else {
				result = true;
			}

		}
		return result;
	}

		/*
		public boolean conditionForPrimaryWindows() {
			boolean result = true;

			sku_brand_list_second = db.getInsertedDisplayListAfterStock(category_id, store_id);

			if (sku_brand_list_second.size() == 0) {

				sku_brand_list_second = db.getDisplayListAfterStock(category_id, store_id);

				if (sku_brand_list_second.size()>0) {
					for (int i = 0; i < sku_brand_list_second.size(); i++) {

						if (sku_brand_list_second.get(i).getYesNo().equalsIgnoreCase("")){

					result = false;
					break;
						}else{
							result = true;
						}

					}
				}

			}


			return result;
		}*/


	public boolean condition() {
		boolean result = true;

		for (int i = 0; i < sku_brand_list.size(); i++) {

			if (sku_brand_list.get(i).getCompany_id().equalsIgnoreCase("1")) {

				if (!sku_brand_list.get(i).getAfter_Stock().equalsIgnoreCase("")
						&& !sku_brand_list.get(i).getAfter_faceup().equalsIgnoreCase("")) {

					if (Integer.parseInt(sku_brand_list.get(i).getAfter_faceup()) > (Integer
							.parseInt(sku_brand_list.get(i).getAfter_Stock()))) {
						row_pos = i + 1;
						lv.invalidateViews();
						result = false;
						break;
					}
				}
			} else {
				result = true;
			}

		}

		return result;
	}


	public boolean condition_lastmonth_stock() {
		boolean result = true;

		for (int i = 0; i < sku_brand_list.size(); i++) {

			if (sku_brand_list.get(i).getCompany_id().equalsIgnoreCase("1")) {

				if (!sku_brand_list.get(i).getALAST_THREE().equalsIgnoreCase("")
						&& !sku_brand_list.get(i).getAMORE_SIX().equalsIgnoreCase("")
						&& !sku_brand_list.get(i).getATHREE_TO_SIX().equalsIgnoreCase("")) {
					if (!paked_key.equalsIgnoreCase("n")) {

						if ((Integer.parseInt(sku_brand_list.get(i).getALAST_THREE())
								+ Integer.parseInt(sku_brand_list.get(i).getAMORE_SIX()) + Integer
								.parseInt(sku_brand_list.get(i).getATHREE_TO_SIX())) != (Integer
								.parseInt(sku_brand_list.get(i).getAfter_Stock()))) {
							row_pos = i + 1;
							lv.invalidateViews();
							result = false;
							break;
						}
					}
				}
			}


		}

		return result;
	}


	public boolean validate_allvalues_laststock() {
		boolean result = true;

		for (int i = 0; i < sku_brand_list.size(); i++) {

			if (sku_brand_list.get(i).getALAST_THREE().equals("")
					|| sku_brand_list.get(i).getAMORE_SIX().equals("")
					|| sku_brand_list.get(i).getATHREE_TO_SIX().equals("")) {
				row_pos = i + 1;
				lv.invalidateViews();
				result = false;
				break;
			}

		}

		return result;

	}

	public boolean validate_primaryWindows() {
		boolean result = true;

		if (!coveragelist.get(0).getImage_allow().equalsIgnoreCase("NOT Allowed")) {

			if (image1.equalsIgnoreCase("") && image2.equalsIgnoreCase("") && image3.equalsIgnoreCase("") && image4.equalsIgnoreCase("")) {
				result = false;
				imglayout.setBackgroundColor(Color.RED);
			} else {
				imglayout.setBackgroundColor(Color.WHITE);
			}

		}

//	     		if (image1.equalsIgnoreCase("") && image2.equalsIgnoreCase("") && image3.equalsIgnoreCase("")) {
//	     			result = false;
//	     		}
//


		return result;

	}


	public boolean validate_primaryWindowsData() {
		boolean result = true;
		sku_brand_list_second = db.getDisplayListAfterStock(category_id, store_id, process_id);
		if (sku_brand_list_second.size() > 0) {
			sku_brand_list_second = db.getInsertedDisplayListAfterStock(category_id, store_id, process_id);
			if (!(sku_brand_list_second.size() > 0)) {

				result = false;

			}
		}

		return result;

	}


	public boolean validate_ShelfVisibilityData() {
		boolean result = true;

		if (category_id.equalsIgnoreCase("2")) {
			shelf_list = db.getShelfData(process_id, region_id, store_type_id);
			if (shelf_list.size() > 0) {
				shelf_list = db.getInsertedShelfData(store_id, category_id, process_id, store_type_id);
				if (!(shelf_list.size() > 0)) {

					result = false;

				}
			}
		}


		return result;

	}

	public void ShowToast(String message) {

		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toastview,
				(ViewGroup) findViewById(R.id.toast_layout_root));

		ImageView image = (ImageView) layout.findViewById(R.id.image);
		image.setImageResource(R.drawable.ic_launcher);
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText(message);

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();

	}

	public boolean validate_allvalues() {
		boolean result = true;

		for (int i = 0; i < sku_brand_list.size(); i++) {

			if (sku_brand_list.get(i).getAfter_Stock().equals("")
					|| sku_brand_list.get(i).getAfter_faceup().equals("")
					|| sku_brand_list.get(i).getALAST_THREE().equals("")
					|| sku_brand_list.get(i).getAMORE_SIX().equals("")
					|| sku_brand_list.get(i).getATHREE_TO_SIX().equals("")) {

				result = false;
				check = false;
				break;

			}

		}

		return result;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.savebtn) {

			lv.clearFocus();
			row_pos = 10000;

			if (validate_ShelfVisibilityData()) {


				if (validate_allvalues()) {
					if (validate_values()) {
						if (condition()) {

							if (condition_lastmonth_stock()) {

								if (validate_primaryWindows()) {

									if (validate_primaryWindowsData()) {

										if (conditionForPrimaryWindows()) {


											AlertDialog.Builder builder = new AlertDialog.Builder(this);
											builder.setMessage("Are you sure you want to save").setCancelable(false).setPositiveButton("Yes",
													new DialogInterface.OnClickListener() {
														public void onClick(DialogInterface dialog, int id) {


															//db.InsertCoverage(store_id, date, intime, getCurrentTime(), reason_id, remark,username,app_version,process_id);

															db.InsertBeforerStockData(store_id, sku_brand_list, username, category_id, process_id);

															db.InsertBeforeStockImagese(store_id, category_id, image1, image2, image3, image4, username, process_id);





							/* db.updateAfterImage(store_id,category_id, image1, image2, image3, image4
									 ,username, process_id);



						for(int i =0; i < sku_brand_list.size();i++){
							db.updateAfterStockData(store_id, sku_brand_list.get(i).getSku_id(),
									sku_brand_list.get(i).getAfter_Stock(), sku_brand_list.get(i).getAfter_faceup(),
									username, sku_brand_list.get(i).getALAST_THREE(), sku_brand_list.get(i).getAMORE_SIX(),
									sku_brand_list.get(i).getATHREE_TO_SIX(), category_id, process_id);
						}
						*/


															Intent in = new Intent(AfterStockActivity.this, DailyEntryMainMenu.class);
															startActivity(in);
															AfterStockActivity.this.finish();


														}
													}).setNegativeButton("No",
													new DialogInterface.OnClickListener() {
														public void onClick(DialogInterface dialog, int id) {
															dialog.cancel();
														}
													});
											AlertDialog alert = builder.create();

											alert.show();
										} else {
											Toast.makeText(getBaseContext(), "Please fill questions in primary category "
													, Toast.LENGTH_LONG).show();
										}
									} else {

										sku_brand_list_second = db.getDisplayListAfterStock(category_id, store_id, process_id);
										Toast.makeText(getBaseContext(), "Please fill Primary window "
												, Toast.LENGTH_LONG).show();
									}
								} else {
									Toast.makeText(getBaseContext(), "Please click image! "
											, Toast.LENGTH_LONG).show();
								}
							} else {

								Toast.makeText(getBaseContext(), "Sum of entered stock is not equal to the total stock at line"
										+ row_pos, Toast.LENGTH_LONG).show();

							}

						} else {

							Toast.makeText(getBaseContext(), "Invalid Faceup, Faceup Should Be Less " +
									"Than The Stock at line " + row_pos, Toast.LENGTH_LONG).show();
						}
					} else {
						Toast.makeText(getBaseContext(), "Incorrect data ", Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getBaseContext(), "Enter Data In All Fields",
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getBaseContext(), "Please fill Shelf Visibility", Toast.LENGTH_LONG).show();
			}

		}

		if (v.getId() == R.id.Save2) {


			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to save").setCancelable(false).setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {


//              db.InsertCoverage(store_id, date, intime, getCurrentTime(),
//								reason_id, remark,username,app_version,process_id);


							db.SaveData(sku_brand_list_second, category_id, store_id, process_id);


						}
					}).setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert = builder.create();

			alert.show();


		}


	}

	private class ShelfVisibility extends BaseAdapter {
		LayoutInflater mInflater;
		private Context mcontext;

		public ShelfVisibility(Context context) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
		}


		@Override
		public int getCount() {

			return shelf_list.size();
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder2 holder;
			if (convertView == null) {

				convertView = mInflater.inflate(R.layout.shelf_visibility,
						null);
				holder = new ViewHolder2();
				holder.brandName = (TextView) convertView
						.findViewById(R.id.brandname);

				holder.shelf_name = (TextView) convertView
						.findViewById(R.id.shelfname);

				holder.answerShelf = (ToggleButton) convertView.findViewById(R.id.answer);
				holder.cameraShelf = (ImageView) convertView.findViewById(R.id.camera);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder2) convertView.getTag();
			}


			holder.cameraShelf.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mposition = position;
					_pathforcheck1 = store_id + "_" + shelf_list.get(position).getShelf_id() + "_" + shelf_list.get(position).getBrand_id()
							+ process_id + imgDate + ".jpg";

					_path = str + _pathforcheck1;

					startCameraActivity(0);
				}
			});

			holder.answerShelf.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {


					final int position = v.getId();
					final ToggleButton Caption = (ToggleButton) v;
					String value1 = Caption.getText().toString();

					if (value1.equals("")) {

						shelf_list.get(position).setAnswer("");

					} else {

						shelf_list.get(position).setAnswer(value1);


					}


				}
			});

			if (!img2.equalsIgnoreCase("")) {
				if (position == mposition) {
					shelf_list.get(position).setCamera("YES");
					shelf_list.get(position).setImage(img2);
					img1 = "";

				}
			}
			if (shelf_list.get(position).getCamera().equalsIgnoreCase("NO")) {
				holder.cameraShelf.setBackgroundResource(R.drawable.camera_ico);
			} else {
				holder.cameraShelf.setBackgroundResource(R.drawable.camera_tick_ico);
			}


			if (shelf_list.get(position).getAnswer().equalsIgnoreCase("NO")) {
				holder.answerShelf.setChecked(false);
			} else {
				holder.answerShelf.setChecked(true);
			}


			holder.brandName.setText(shelf_list.get(position).getBrand_name());
			holder.shelf_name.setText(shelf_list.get(position).getShelf());

			holder.answerShelf.setId(position);
			holder.cameraShelf.setId(position);
			holder.brandName.setId(position);
			holder.shelf_name.setId(position);

			return convertView;
		}

	}


	private class skulistDataOther extends BaseAdapter {

		LayoutInflater mInflater;
		private Context mcontext;


		public skulistDataOther(Context context) {

			mInflater = LayoutInflater.from(context);
			mcontext = context;

		}


		@Override
		public int getCount() {

			return sku_brand_list_second.size();
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder2 holder;
			if (convertView == null) {
				final boolean[] userSelect = {false};
				convertView = mInflater.inflate(R.layout.stockafter_adapter,
						null);
				holder = new ViewHolder2();

				holder.brand_name = (TextView) convertView
						.findViewById(R.id.brand);
				holder.SkuOtherName = (TextView) convertView
						.findViewById(R.id.question);

			/*	holder.issues = (Button) convertView.findViewById(R.id.issues);
				holder.no_issues = (Button) convertView.findViewById(R.id.no_issues);*/
				holder.ref_img = (Button) convertView.findViewById(R.id.refimg);
				holder.spin_exist = (Spinner) convertView.findViewById(R.id.spin_exist);
				holder.cam_img = (ImageView) convertView.findViewById(R.id.img_cam);
				holder.gaps = (Button) convertView.findViewById(R.id.gaps);
				holder.lay_cam_n_gap = (LinearLayout) convertView.findViewById(R.id.lay_cam_n_gap);

				ArrayList<AnswerGetterSetter> ans_list = new ArrayList<>();
				AnswerGetterSetter select = new AnswerGetterSetter();
				select.setAns(getString(R.string.select));
				select.setAns_id(0);
				ans_list.clear();
				ans_list.add(select);

				select = new AnswerGetterSetter();
				select.setAns(getString(R.string.yes));
				select.setAns_id(1);
				ans_list.add(select);

				select = new AnswerGetterSetter();
				select.setAns(getString(R.string.no));
				select.setAns_id(2);
				ans_list.add(select);

				CustomSpinnerAdapter skuadapter = new CustomSpinnerAdapter(AfterStockActivity.this, R.layout.custom_spinner_layout, ans_list);
				holder.spin_exist.setAdapter(skuadapter);

				holder.spin_exist.setSelection(0);

				//holder.tv_display.setTypeface(FontManager.getTypeface(getApplicationContext(),FontManager.FONTAWESOME));

           /* Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
            FontManager.markAsIconContainer(findViewById(R.id.icons_container), iconFont);
*/

				holder.cam_img.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						_pathforcheck_gap = "Brand_Image_" + store_id + sku_brand_list_second.get(position).getBrand_id()
								+ "_" + category_id + date.replace("/", "") + "_" +
								getCurrentTime().replace(":", "") + ".jpg";
						child_position = position;
						_path = str + _pathforcheck_gap;

						startCameraActivity(1);
					}
				});

				holder.gaps.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {

						question_list = db.getInsertedQuestionsData(store_id, sku_brand_list_second.get(position).getDisplay_id(),
								category_id, process_id);

						if (question_list.size() == 0) {
							question_list = db.getQuestionsData(sku_brand_list_second.get(position).getDisplay_id());
						}


						primary_windowIssues = "YES";
						sku_brand_list_second.get(position).setYesNo(primary_windowIssues);
//					v.setBackgroundColor(Color.RED);


						lv2.invalidateViews();

						final Dialog dialog = new Dialog(AfterStockActivity.this);
						dialog.setContentView(R.layout.dialog);
						dialog.setTitle("Issues");

						ListView listofquestion = (ListView) dialog.findViewById(R.id.list_q);
						Button save_q = (Button) dialog.findViewById(R.id.save_q);

						if (question_list.size() > 0) {

							listofquestion.setAdapter(new questionAdaptor(AfterStockActivity.this));

						}


						save_q.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {

								db.InsertQuestionDataafterStock(question_list, sku_brand_list_second.get(position).getDisplay_id(), store_id
										, category_id, sku_brand_list_second.get(position).getUID(), process_id);

//							db.InsertTEMPQuestionData(question_list, data.get(position).getDisplay_id(), store_id
//									,category_id);

								dialog.dismiss();


							}
						});


						dialog.show();

					}
				});

           holder.spin_exist.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View view, MotionEvent motionEvent) {
						userSelect[0] = true;
						return false;
					}
				});

				holder.spin_exist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
						if (userSelect[0]) {
							userSelect[0] = false;

							switch (pos){

								case 0:
									sku_brand_list_second.get(position).setExist(-1);

									//delete brand image
									if (!sku_brand_list_second.get(position).getBrand_img().equals("")) {
										new File(str + sku_brand_list_second.get(position).getBrand_img()).delete();
										sku_brand_list_second.get(position).setBrand_img("");
									}

									//delete gpas data
									db.deleteQuestionList(sku_brand_list_second, store_id, category_id, process_id);
									if (sku_brand_list_second.size() > 0) {
										db.deleteYESNO(sku_brand_list_second, store_id, category_id, process_id);
									}
									lv2.invalidateViews();
									break;

								case 1:
									sku_brand_list_second.get(position).setExist(1);
									lv2.invalidateViews();
									break;

								case 2:
									sku_brand_list_second.get(position).setExist(0);

									//delete brand image
									if (!sku_brand_list_second.get(position).getBrand_img().equals("")) {
										new File(str + sku_brand_list_second.get(position).getBrand_img()).delete();
										sku_brand_list_second.get(position).setBrand_img("");
									}

									//delete gpas data
									db.deleteQuestionList(sku_brand_list_second, store_id, category_id, process_id);
									if (sku_brand_list_second.size() > 0) {
										db.deleteYESNO(sku_brand_list_second, store_id, category_id, process_id);
									}
									lv2.invalidateViews();
									break;
							}
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> adapterView) {

					}
				});

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder2) convertView.getTag();
			}

			if(!img.equals("")){
				if(position == child_position){
					sku_brand_list_second.get(position).setBrand_img(img);
					img = "";
				}
			}

			if(sku_brand_list_second.get(position).getExist()==1){
				holder.lay_cam_n_gap.setVisibility(View.VISIBLE);
				holder.spin_exist.setSelection(1);
				if(sku_brand_list_second.get(position).getBrand_img().equals("")){
					holder.cam_img.setBackgroundResource(R.drawable.camera);
				}
				else {
					holder.cam_img.setBackgroundResource(R.drawable.camera_done);
				}

			}else {
				if(sku_brand_list_second.get(position).getExist()==0){
					holder.spin_exist.setSelection(2);
				}
				else {
					holder.spin_exist.setSelection(0);
				}
				holder.lay_cam_n_gap.setVisibility(View.GONE);
			}

			holder.brand_name.setText(sku_brand_list_second.get(position).getBrand());
			holder.SkuOtherName.setText(sku_brand_list_second.get(position).getDisplay());


//			holder.SkuOtherName.setText("Catman Execution Status");


/*			holder.issues.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

//					if (sku_brand_list_second.get(position).getYesNo().equalsIgnoreCase("YES")) {


					question_list = db.getInsertedQuestionsData(store_id, sku_brand_list_second.get(position).getDisplay_id(),
							category_id, process_id);

					if (question_list.size() == 0) {
						question_list = db.getQuestionsData(sku_brand_list_second.get(position).getDisplay_id());
					}


					primary_windowIssues = "YES";
					sku_brand_list_second.get(position).setYesNo(primary_windowIssues);
//					v.setBackgroundColor(Color.RED);


					lv2.invalidateViews();

					final Dialog dialog = new Dialog(AfterStockActivity.this);
					dialog.setContentView(R.layout.dialog);
					dialog.setTitle("Issues");

					ListView listofquestion = (ListView) dialog.findViewById(R.id.list_q);
					Button save_q = (Button) dialog.findViewById(R.id.save_q);

					if (question_list.size() > 0) {

						listofquestion.setAdapter(new questionAdaptor(AfterStockActivity.this));

					}


					save_q.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							db.InsertQuestionDataafterStock(question_list, sku_brand_list_second.get(position).getDisplay_id(), store_id
									, category_id, sku_brand_list_second.get(position).getUID(), process_id);

//							db.InsertTEMPQuestionData(question_list, data.get(position).getDisplay_id(), store_id
//									,category_id);

							dialog.dismiss();


						}
					});


					dialog.show();


				}
			});


			holder.no_issues.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					final int position1 = v.getId();

//					sku_brand_list_second = db.getDisplayListAfterStock(category_id, store_id);

					db.deleteQuestionList(sku_brand_list_second, store_id, category_id, process_id);
					primary_windowIssues = "NO";
					sku_brand_list_second.get(position1).setYesNo(primary_windowIssues);

//					sku_brand_list_second = db.getInsertedDisplayListAfterStock(category_id, store_id);

					if (sku_brand_list_second.size() > 0) {

//						if (sku_brand_list_second.get(position1).getYesNo().equalsIgnoreCase("YES")) {
						db.deleteYESNO(sku_brand_list_second, store_id, category_id, process_id);

//						}
					}


					//v.setBackgroundColor(Color.GREEN);

					lv2.invalidateViews();

				}
			});

*/
			holder.ref_img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
				/*	final Dialog dialog = new Dialog(AfterStockActivity.this);
					dialog.setContentView(R.layout.popup);
					ImageView refimage = (ImageView) dialog.findViewById(R.id.displayimage);
					dialog.setTitle("Reference Image");
					if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("Chem1st.jpg")) {
						refimage.setBackgroundResource(R.drawable.chemfirst);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("DumpBIn.jpg")) {

						refimage.setBackgroundResource(R.drawable.dumpbin);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("FloorStickers.jpg")) {
						refimage.setBackgroundResource(R.drawable.floorstickers);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("Onewayvision.jpg")) {
						refimage.setBackgroundResource(R.drawable.onewayvision);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("DropDown.jpg")) {
						refimage.setBackgroundResource(R.drawable.dropdown);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("Skirting.jpg")) {
						refimage.setBackgroundResource(R.drawable.skirting);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("FloorStack.jpg")) {
						refimage.setBackgroundResource(R.drawable.floorstack);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("Endcap.jpg")) {
						refimage.setBackgroundResource(R.drawable.endcap);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("FSU.jpg")) {
						refimage.setBackgroundResource(R.drawable.fsu);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("PillarBranding.jpg")) {
						refimage.setBackgroundResource(R.drawable.pillarbranding);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("Parasite.jpg")) {
						refimage.setBackgroundResource(R.drawable.parasite);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("catman.jpg")) {
						refimage.setBackgroundResource(R.drawable.catman);
					} else if (sku_brand_list_second.get(position).getImage_url().equalsIgnoreCase("ClipStrips.jpg")) {
						refimage.setBackgroundResource(R.drawable.clipstrips);
					}


					dialog.show();*/

					//final Dialog dialog = new Dialog(Stock_FacingActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
					final Dialog dialog = new Dialog(AfterStockActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					//dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
					dialog.setContentView(R.layout.planogram_dialog_layout);
					dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
					dialog.setCancelable(false);

					//ImageView img_planogram = (ImageView) dialog.findViewById(R.id.img_planogram);
					WebView webView = (WebView) dialog.findViewById(R.id.webview);
					webView.setWebViewClient(new MyWebViewClient());

					webView.getSettings().setAllowFileAccess(true);
					webView.getSettings().setJavaScriptEnabled(true);
					webView.getSettings().setBuiltInZoomControls(true);

					String planogram_image = "";
					planogram_image = sku_brand_list_second.get(position).getImage_url();
					if (!planogram_image.equals("")) {
						if (new File(CommonString.FILE_PATH_PLANOGRAM + planogram_image).exists()) {
							Bitmap bmp = BitmapFactory.decodeFile(CommonString.FILE_PATH_PLANOGRAM + planogram_image);
							// img_planogram.setRotation(90);
							//img_planogram.setImageBitmap(bmp);
							setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

							String imagePath = "file://" + CommonString.FILE_PATH_PLANOGRAM  + planogram_image;
							String html = "<html><head></head><body><img src=\"" + imagePath + "\"></body></html>";
							webView.loadDataWithBaseURL("", html, "text/html", "utf-8", "");

							dialog.show();
						} /*else {
                //webView.loadUrl(String.valueOf(R.drawable.sad_cloud));

                //img_planogram.setBackgroundResource(R.drawable.sad_cloud);
            }*/
					}


					ImageView cancel = (ImageView) dialog.findViewById(R.id.img_cancel);
					cancel.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
							dialog.dismiss();
						}
					});

				}
			});


			/*if (sku_brand_list_second.get(position).getYesNo().equalsIgnoreCase("NO")) {
				holder.Yesno.setChecked(false);
			} else {
				holder.Yesno.setChecked(true);
			}
			holder.Yesno.setId(position);*/


		/*	if (sku_brand_list_second.get(position).getYesNo().equalsIgnoreCase("YES")) {

				holder.issues.setBackgroundColor(Color.RED);
				holder.no_issues.setBackgroundColor(Color.GRAY);

			}
			if (sku_brand_list_second.get(position).getYesNo().equalsIgnoreCase("NO")) {
				holder.issues.setBackgroundColor(Color.GRAY);
				holder.no_issues.setBackgroundColor(Color.GREEN);
			}*/

			holder.SkuOtherName.setId(position);
			/*holder.issues.setId(position);
			holder.no_issues.setId(position);*/
			holder.ref_img.setId(position);

			return convertView;
		}

	}

	static class ViewHolder2 {
		TextView brand_name, display_name, target_quantity, SkuOtherName, brandName, shelf_name;
		ImageView image1, image2, image3, cameraShelf, cam_img;
		EditText actual_quanity, stock_count;
		//Button issues, no_issues, ref_img;
		Button ref_img, gaps;
		ToggleButton Yesno, answerShelf;
		Spinner spin_exist;
		LinearLayout lay_cam_n_gap;

	}


	private class questionAdaptor extends BaseAdapter {

		LayoutInflater mInflater;
		private Context mcontext;


		public questionAdaptor(Context context) {

			mInflater = LayoutInflater.from(context);
			mcontext = context;

		}


		@Override
		public int getCount() {

			return question_list.size();
		}

		@Override
		public Object getItem(int position) {

			return position;
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder3 holder;
			if (convertView == null) {

				convertView = mInflater.inflate(R.layout.question_view_list,
						null);
				holder = new ViewHolder3();
				holder.question_text = (TextView) convertView
						.findViewById(R.id.question);

				holder.answer = (ToggleButton) convertView
						.findViewById(R.id.answer);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder3) convertView.getTag();
			}

			holder.question_text.setText(question_list.get(position).getQuestion());


			holder.answer.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {


					final int position = v.getId();
					final ToggleButton Caption = (ToggleButton) v;
					String value1 = Caption.getText().toString();

					if (value1.equals("")) {

						question_list.get(position).setAnswer("");

					} else {

						question_list.get(position).setAnswer(value1);


					}


				}
			});


			if (question_list.get(position).getAnswer().equalsIgnoreCase("NO")) {
				holder.answer.setChecked(false);
			} else {
				holder.answer.setChecked(true);
			}
			holder.answer.setId(position);
			holder.question_text.setId(position);

			return convertView;
		}

	}

	static class ViewHolder3 {
		TextView brand_name, display_name, target_quantity, question_text;
		ImageView image1, image2, image3;
		EditText actual_quanity, stock_count;
		Button question_lv;
		ToggleButton answer;

	}


	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK)
				>= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public void hide() {
		mKeyboardView.setVisibility(View.INVISIBLE);
		/*	// mKeyboardView.clearFocus();
		mKeyboardView.requestFocusFromTouch();*/

	}

	private class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			view.clearCache(true);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}
	}

	public class CustomSpinnerAdapter extends ArrayAdapter<String> {

		AnswerGetterSetter tempValues = null;
		LayoutInflater inflater;
		private Activity activity;
		private ArrayList data;

		/*************
		 * CustomAdapter Constructor
		 *****************/
		public CustomSpinnerAdapter(
				AfterStockActivity activitySpinner,
				int textViewResourceId,
				ArrayList objects

		) {
			super(activitySpinner, textViewResourceId, objects);

			/********** Take passed values **********/
			activity = activitySpinner;
			data = objects;
			/***********  Layout inflator to call external xml layout () **********************/
			inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}

		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		// This funtion called for each row ( Called data.size() times )
		public View getCustomView(int position, View convertView, ViewGroup parent) {

			/********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
			View row = inflater.inflate(R.layout.custom_spinner_item, parent, false);

			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (AnswerGetterSetter) data.get(position);

			TextView label = (TextView) row.findViewById(R.id.tv_text);

			if (position == 0) {

				// Default selected Spinner item
				label.setText(getString(R.string.select));
				//sub.setText("");
			} else {
				// Set values for spinner each row
				label.setText(tempValues.getAns());
			}

			return row;
		}
	}

	class AnswerGetterSetter{

		int ans_id;
		String ans;

		public int getAns_id() {
			return ans_id;
		}

		public void setAns_id(int ans_id) {
			this.ans_id = ans_id;
		}

		public String getAns() {
			return ans;
		}

		public void setAns(String ans) {
			this.ans = ans;
		}
	}
}
