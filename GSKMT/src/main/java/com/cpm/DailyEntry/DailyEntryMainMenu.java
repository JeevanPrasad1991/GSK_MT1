package com.cpm.DailyEntry;

import java.util.ArrayList;

import com.cpm.Constants.CommonString;
import com.cpm.database.GSKMTDatabase;
import com.cpm.delegates.PromotionBean;
import com.cpm.delegates.SkuBean;
import com.cpm.delegates.TOTBean;
import com.example.gsk_mtt.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DailyEntryMainMenu extends Activity {
	
	public Button/* before_stock,*/ after_stock, /*before_tot,*/ after_tot, before_add , after_add, addtional_info_before,
	 promo_compliance, comp, backroom_stock, sales;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor = null;
	String store_id, category_id, date, process_id, key_id, cat_name;
	TextView category_name;
	
	GSKMTDatabase db;
	String afterStockQuantity= "";
	ArrayList<SkuBean>/* beforeStockData,*/ beforeAddtionalData, afterStockData, sos_data_before,sos_target_list, entered_comp_data,
	backRoomStockData;
	ArrayList<TOTBean> /*beforeTOTData,*/ afterTOTData, totstockdata;
	ArrayList<TOTBean> mappingDataTOTCategoryWise;
	ArrayList<SkuBean> salesData;
	ArrayList<PromotionBean> mappingPromotion, promotionData;
	String sos_before= "", sos_after= "";
	TextView /*before_sos,*/ after_sos;
	public static ArrayList<TOTBean> TOTdata = new ArrayList<TOTBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dailyentry);
		//before_stock = (Button)findViewById(R.id.before_stock);
		after_stock = (Button)findViewById(R.id.after_stock);
		after_tot = (Button)findViewById(R.id.after_tot);
		//before_tot = (Button)findViewById(R.id.befor_tot);
		addtional_info_before = (Button)findViewById(R.id.before_additional);
		promo_compliance = (Button)findViewById(R.id.promotional_data);
		category_name = (TextView)findViewById(R.id.cat_name);
		comp = (Button)findViewById(R.id.competitionTracking);
		sales = (Button)findViewById(R.id.salesTracking);
		
		backroom_stock = (Button)findViewById(R.id.backroom_stock);

		//before_sos = (TextView)findViewById(R.id.sos);
		after_sos = (TextView)findViewById(R.id.sos_after);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		date = preferences.getString(CommonString.KEY_DATE, null);
		store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
		category_id = preferences.getString(CommonString.KEY_CATEGORY_ID, null);
		cat_name = preferences.getString(CommonString.KEY_CATEGORY_NAME, null);
		process_id = preferences.getString(CommonString.KEY_PROCESS_ID, null);
		key_id = preferences.getString(CommonString.KEY_ID, null);

		//if(category_id)
		 
		//beforeStockData = new ArrayList<SkuBean>();
		beforeAddtionalData = new ArrayList<SkuBean>();
		//beforeTOTData = new ArrayList<TOTBean>();
		afterTOTData = new ArrayList<TOTBean>();
		totstockdata = new ArrayList<TOTBean>();
		mappingPromotion = new ArrayList<PromotionBean>();
		sos_target_list = new ArrayList<SkuBean>();
		entered_comp_data = new ArrayList<SkuBean>();
		
		salesData = new ArrayList<SkuBean>();
		
		mappingDataTOTCategoryWise = new ArrayList<TOTBean>();
		
		db = new GSKMTDatabase(DailyEntryMainMenu.this);
		db.open();
		
		
		category_name.setText(cat_name);
		
		//beforeStockData = db.getBeforeStockData(store_id, category_id, process_id);
		
		afterStockData = db.getAfterStockData(store_id, category_id, process_id);
		
		backRoomStockData = db.getBackRoomStock(store_id, category_id, process_id);
		
		salesData = db.getSalesStockData(store_id, category_id, process_id);
		
		/*if(process_id.equalsIgnoreCase("2")){
			sales.setVisibility(View.VISIBLE);
			sales.setEnabled(true);

		}else{
			sales.setVisibility(View.GONE);
			sales.setEnabled(false);
		}*/

		sales.setVisibility(View.GONE);
		sales.setEnabled(false);

		if (salesData.size()>0) {
			
			sales.setBackgroundResource(R.drawable.sales_tick);
			
		} else {
			sales.setBackgroundResource(R.drawable.sales);
		}
		
		
		for(int i=0 ;i < afterStockData.size();i++){
			afterStockQuantity = afterStockData.get(i).getAfter_Stock();
		}
		
		
		beforeAddtionalData = db.getProductEntryDetail(store_id, category_id, process_id);
		
		//beforeTOTData = db.getBeforeTOTData(store_id, category_id, process_id);

		afterTOTData = db.getAfterTOTData(store_id, category_id, process_id);
		
		
		
		promotionData = db.getInsertedPromoCompliances(store_id, category_id,process_id);
		
		entered_comp_data = db.getEnteredCompetitionDetail(store_id, category_id, process_id);
		
		
		sos_target_list = db.getSOSTarget(store_id, category_id, process_id);
		
		if (backRoomStockData.size()>0) {
			backroom_stock.setBackgroundResource(R.drawable.backroom_tick);
			
		} else {
			backroom_stock.setBackgroundResource(R.drawable.backroom);
		}

		mappingDataTOTCategoryWise = db.getTOTData(store_id, process_id, category_id);
		
		mappingPromotion = db.getPromoComplianceData(key_id, process_id, category_id);

		if (/*beforeStockData.size() > 0 &&*/ !afterStockQuantity.equals("")) {
			after_stock.setBackgroundResource(R.drawable.tick_stock_after_ico);
			
			sos_after = db.getAFTERSOS(store_id, category_id, process_id);
			
			if (category_id.equalsIgnoreCase("2")) {
				if (sos_target_list.size()>0) {
					if (sos_after == null) {
						after_sos.setText("SOS : 0");
					}
					else{
						
					
					if (!sos_after.equalsIgnoreCase("")) {
						float b = Float.parseFloat(sos_after);
						int s_after = (int)Math.round(b);
						
						String s_target = sos_target_list.get(0).getSos_target();
						int target = Integer.parseInt(s_target);
						
						if (s_after == 100) {
							
							after_sos.setText("SOS: 100");
							after_sos.setTextColor(Color.GREEN);
							
						} else {
							after_sos.setText("SOS: 0");
							after_sos.setTextColor(Color.RED);
						}
					} else{
						after_sos.setText("SOS : 0");
					}
					}
				} else {
					after_sos.setText("SOS : 0");
				}
			} else {

			
			
			
			if (sos_target_list.size()>0) {
				if (sos_after == null) {
					after_sos.setText("SOS : 0");
				}
				else{
					
				
				if (!sos_after.equalsIgnoreCase("")) {
					float b = Float.parseFloat(sos_after);
					int s_after = (int)Math.round(b);
					
					String s_target = sos_target_list.get(0).getSos_target();
					int target = Integer.parseInt(s_target);
					
					if (s_after <target) {
						
						after_sos.setText("SOS: "+ s_after);
						after_sos.setTextColor(Color.RED);
						
					} else {
						after_sos.setText("SOS: "+ s_after);
						after_sos.setTextColor(Color.GREEN);
					}
				} else{
					after_sos.setText("SOS : 0");
				}
				}
			} else {
				after_sos.setText("SOS : 0");
			}
			
			}

		}
		
		
		if (/*beforeStockData.size() > 0 &&*/ !afterStockQuantity.equalsIgnoreCase("") /*&& beforeTOTData.size()>0*/) {
			
			//before_tot.setEnabled(false);

			//after_tot.setBackgroundResource(R.drawable.tot_after_ico);
			//before_tot.setBackgroundResource(R.drawable.disabled_tot_before_tick_ico);

			TOTdata = db.getTOTData(store_id, process_id, category_id);

			if (TOTdata.size()>0) {
				after_tot.setEnabled(true);
				after_tot.setBackgroundResource(R.drawable.tot_after_ico);

			} else {
				after_tot.setBackgroundResource(R.drawable.disabled_tot_after_ico);
			}

		} 
		
	/*	if (check_conditionForSTOCK()) {
			System.out.println("zjskdfhkjhdfkhfkj" );
		} else {

		}*/
		

		if (/*beforeStockData.size() > 0 &&*/ !afterStockQuantity.equalsIgnoreCase("") /*&& beforeTOTData.size()>0*/ && afterTOTData
				.size()>0 /*&& check_conditionForSTOCK()*/) {
			/*addtional_info_before.setEnabled(true);
			addtional_info_before.setBackgroundResource(R.drawable.additional_display_144);*/




			after_tot.setBackgroundResource(R.drawable.tick_tot_after_ico);
		} 
		
		
		if (mappingDataTOTCategoryWise.size()>0) {
			if (/*beforeStockData.size() > 0 &&*/ /*!afterStockQuantity.equalsIgnoreCase("")*/ /*&& beforeTOTData.size()>0*//* && afterTOTData
					.size()>0 &&*/ beforeAddtionalData.size()>0) {
				
				//addtional_info_before.setEnabled(true);
				addtional_info_before.setBackgroundResource(R.drawable.additional_display_tick);
				
			} 
		} else {
			
			if (/*beforeStockData.size() > 0 &&*/ /*!afterStockQuantity.equalsIgnoreCase("") &&*/ beforeAddtionalData.size()>0) {
				
				///addtional_info_before.setEnabled(true);
				addtional_info_before.setBackgroundResource(R.drawable.additional_display_tick);
				
			} 

		}
		
		
		if (mappingPromotion.size()>0) {
			
			promo_compliance.setEnabled(true);
			promo_compliance.setBackgroundResource(R.drawable.promo_compliance);
			
			if (promotionData.size()>0) {
				
				promo_compliance.setBackgroundResource(R.drawable.promo_compliance_tick);
				
			}else{
				promo_compliance.setBackgroundResource(R.drawable.promo_compliance);
			}
			
		}else{
			promo_compliance.setEnabled(false);
			promo_compliance.setBackgroundResource(R.drawable.disabled_promo_compliance);
			
		}
		
		
		if (entered_comp_data.size()>0) {
			comp.setBackgroundResource(R.drawable.tick_competition_tracking);
			
		}else{
			comp.setBackgroundResource(R.drawable.competition_tracking);
		}

		
		promo_compliance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(getBaseContext(), PromoCompliance.class);
				startActivity(in);
				DailyEntryMainMenu.this.finish();
				
			}
		});
	
		
		sales.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent in = new Intent(getBaseContext(), Sales.class);
				startActivity(in);
				DailyEntryMainMenu.this.finish();
				
			}
		});

		addtional_info_before.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent in = new Intent(getBaseContext(), BeforeAdditionalDisplay.class);
				startActivity(in);
				DailyEntryMainMenu.this.finish();
				
			}
		});
		

		after_stock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(getBaseContext(), AfterStockActivity.class);
				startActivity(in);
				DailyEntryMainMenu.this.finish();
				
			}
		});
		
		after_tot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(getBaseContext(), AfterTOT.class);
				startActivity(in);
				DailyEntryMainMenu.this.finish();
				
			}
		});
		
		comp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(getBaseContext(), CompetitionTracking.class);
				startActivity(in);
				DailyEntryMainMenu.this.finish();
				
			}
		});

	backroom_stock.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent in = new Intent(getBaseContext(), StockwareHouse.class);
			startActivity(in);
			DailyEntryMainMenu.this.finish();
		}
	});
		
	}
	
	@Override
	public void onBackPressed() {
		
		super.onBackPressed();
		Intent in = new Intent(DailyEntryMainMenu.this, CopyOfStorevisitedYesMenu.class);
		startActivity(in);
		DailyEntryMainMenu.this.finish();
		
	}

}
