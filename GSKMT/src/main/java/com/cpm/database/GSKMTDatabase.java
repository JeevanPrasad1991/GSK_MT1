package com.cpm.database;

import java.util.ArrayList;

import com.cpm.Constants.CommonString;


import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.delegates.PerformanceBean;
import com.cpm.delegates.PromotionBean;
import com.cpm.delegates.ReasonModel;
import com.cpm.delegates.ShelfVisibilityBean;
import com.cpm.delegates.SkuBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.TOTBean;
import com.cpm.delegates.TableBean;
import com.cpm.xmlGetterSetter.AdditionalGetterSetter;
import com.cpm.xmlGetterSetter.CompetitionGetterSetter;
import com.cpm.xmlGetterSetter.DisplayGetterSetter;
import com.cpm.xmlGetterSetter.EmpMeetingStatus;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.MappingCompetitionPromotionGetterSetter;
import com.cpm.xmlGetterSetter.MappingWellnessSos;
import com.cpm.xmlGetterSetter.NonWorkingAttendenceGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingGetterSetter;
import com.cpm.xmlGetterSetter.PdrFacingStockGetterSetter;
import com.cpm.xmlGetterSetter.PromotionalDataSetterGetter;
import com.cpm.xmlGetterSetter.QuestionGetterSetter;
import com.cpm.xmlGetterSetter.SKUGetterSetter;
import com.cpm.xmlGetterSetter.SOSTargetGetterSetter;
import com.cpm.xmlGetterSetter.ShelfMaster;
import com.cpm.xmlGetterSetter.StockMappingGetterSetter;
import com.cpm.xmlGetterSetter.StoreWise_Pss;
import com.cpm.xmlGetterSetter.TDSGetterSetter;
import com.cpm.xmlGetterSetter.TargetToothpestforOHCGetterSetter;
import com.cpm.xmlGetterSetter.catmanMapping;
import com.crashlytics.android.Crashlytics;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

@SuppressLint("LongLogTag")
public class GSKMTDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GSKMTMERC_DATABASENEW";
    public static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;

    public GSKMTDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        try {
            db = this.getWritableDatabase();
        } catch (Exception e) {
            Crashlytics.logException(e);
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("" + TableBean.getCoverage_table());
        db.execSQL(TableBean.getSku_master_table());
        db.execSQL(TableBean.getJorney_plan_table());
        db.execSQL(TableBean.getBrand_master_table());
        db.execSQL(TableBean.getStock_mapping_master_table());
        db.execSQL(TableBean.getDisplay_table());
        db.execSQL(TableBean.getTds_table());
        db.execSQL(TableBean.getCatman_stockafter_table());
        db.execSQL(TableBean.getStock_table());
        db.execSQL(TableBean.getStock_image_table());
        db.execSQL(TableBean.getNonworkingReason_table());
        db.execSQL(TableBean.getNonworkingSubReason_table());
        db.execSQL(TableBean.getAddtional_mapping_table());
        db.execSQL(TableBean.getSos_target_table());
        db.execSQL(TableBean.getCompany_table());
        db.execSQL(TableBean.getShelf_master());
        db.execSQL(TableBean.getMapping_shelf_table());
        db.execSQL(TableBean.getMappingPdrFacingTable());
        db.execSQL(TableBean.getNonwokingAttendenceTable());
        db.execSQL(CommonString.CREATE_TABLE_ADDITIONAL_DETAILS);
        db.execSQL(CommonString.CREATE_TABLE_AFTER_ADDITIONAL_DETAILS);
        db.execSQL(CommonString.CREATE_TABLE_QUESTION_ANSWER);
        db.execSQL(TableBean.getQuestion_table());
        db.execSQL(CommonString.CREATE_TABLE_TOT_AFTER);
        db.execSQL(CommonString.CREATE_TABLE_TOT_BEFORE);
        db.execSQL(TableBean.getQuestion_mapping_table());
        db.execSQL(CommonString.CREATE_TABLE_COVERAGE_DATA);
        db.execSQL(TableBean.getPromotional_mapping_table());
        db.execSQL(CommonString.CREATE_TABLE_GEO_TAG_MAPPING);
        db.execSQL(CommonString.CREATE_TABLE_PROMOTION_DATA);
        db.execSQL(CommonString.CREATE_TABLE_INSERT_AFTERSOCK_OTHER);
        db.execSQL(CommonString.CREATE_TABLE_INSERT_GEOTAG);
        db.execSQL(CommonString.CREATE_TABLE_QUESTION_ANSWER_STOCKAFTER);
        db.execSQL(CommonString.CREATE_TABLE_COMPETITION_INFO);
        db.execSQL(CommonString.CREATE_TABLE_INSERT_SHELF_VISIBILITY);
        db.execSQL(TableBean.getStorewise_pss_table());
        db.execSQL(CommonString.CREATE_TABLE_STOCK_TOT);
        db.execSQL(CommonString.CREATE_TABLE_INSERT_STOCKWAREHOUSE);
        db.execSQL(CommonString.CREATE_TABLE_SALES_STOCK);
        db.execSQL(TableBean.getEmp_meeting_status_table());
        db.execSQL(TableBean.getMAPPINGCompeti_promotionTable());
        db.execSQL(TableBean.getTargetforohctoothpestTable());
        db.execSQL(CommonString.CREATE_TABLE_STORE_GEOTAGGING);
        db.execSQL(CommonString.CREATE_TABLE_INSERT_MERCHANDISER_ATTENDENCE_TABLE);
        db.execSQL(CommonString.CREATE_TABLE_INSERT_COMPETITION_PROMOTION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getSku_master_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getJorney_plan_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getBrand_master_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getSku_master_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getStock_mapping_master_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getDisplay_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getCoverage_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getStock_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getStock_image_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getTot_entry_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getTot_image_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getNonworkingReason_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getNonworkingSubReason_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getAddtional_mapping_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getSos_target_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getCompany_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getShelf_master());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getMapping_shelf_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getStorewise_pss_table());
        db.execSQL("DROP TABLE IF EXISTS " + TableBean.getEmp_meeting_status_table());
        db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_INSERT_ADDTIONAL_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_INSERT_COMPETITION_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + CommonString.CREATE_TABLE_PROMOTION_DATA);
        db.execSQL("DROP TABLE IF EXISTS" + CommonString.CREATE_TABLE_QUESTION_ANSWER);
        db.execSQL("DROP TABLE IF EXISTS" + CommonString.CREATE_TABLE_INSERT_SHELF_VISIBILITY);
        db.execSQL("DROP TABLE IF EXISTS" + CommonString.CREATE_TABLE_STOCK_TOT);
        db.execSQL("DROP TABLE IF EXISTS" + CommonString.CREATE_TABLE_INSERT_STOCKWAREHOUSE);
        db.execSQL("DROP TABLE IF EXISTS" + CommonString.CREATE_TABLE_SALES_STOCK);
        onCreate(db);

    }

    public void deleteAllTables(String storeid, String process_id) {
        db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_QUESTION_ANSWER, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_TOT_BEFORE, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_TOT_AFTER, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete("DR_STOCK", CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete("DR_STOCK_IMAGE", CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_PROMOTION_DATA, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_SHELF_VISIBILITY, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete("QUESTION_ANSWER_TABLE_STOCKAFTER", CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete("QUESTION_ANSWER_TABLE", CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_AFTERSTOCK_OTHER, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_INSERT_STOCK_TOT, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_INSERT_SALES_STOCK, CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, CommonString.KEY_STORE_ID + " ='" + storeid + "'  AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_INSERT_COMPETITION_PROMOTION, CommonString.KEY_STORE_ID + " ='" + storeid + "'  AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_INSERT_COMPETITION_INFO, CommonString.KEY_STORE_ID + " ='" + storeid + "'  AND PROCESS_ID ='" + process_id + "'", null);
        db.delete(CommonString.TABLE_STOCKWAREHOUSE, CommonString.KEY_STORE_ID + " ='" + storeid + "'  AND PROCESS_ID ='" + process_id + "'", null);
    }

    public void deleteAllTables() {
        db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
        db.delete(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, null, null);
        db.delete(CommonString.TABLE_INSERT_AFTER_ADDTIONAL_DETAILS, null, null);
        db.delete(CommonString.TABLE_QUESTION_ANSWER, null, null);
        db.delete(CommonString.TABLE_TOT_BEFORE, null, null);
        db.delete(CommonString.TABLE_TOT_AFTER, null, null);
        db.delete("DR_STOCK", null, null);
        db.delete("DR_STOCK_IMAGE", null, null);
        db.delete(CommonString.TABLE_AFTERSTOCK_OTHER, null, null);
        db.delete(CommonString.TABLE_QUESTION_ANSWER_STOCKAFTER, null, null);
        db.delete(CommonString.TABLE_INSERT_COMPETITION_INFO, null, null);
        db.delete(CommonString.TABLE_SHELF_VISIBILITY, null, null);
        db.delete(CommonString.TABLE_INSERT_SALES_STOCK, null, null);
        db.delete(CommonString.TABLE_PROMOTION_DATA, null, null);
        db.delete(CommonString.TABLE_INSERT_COMPETITION_PROMOTION, null, null);
        db.delete(CommonString.TABLE_STOCKWAREHOUSE, null, null);

    }


    //check if table is empty
    public boolean isCoverageDataFilled(String visit_date) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_VISIT_DATE + "<>'" + visit_date + "' AND STATUS='" + CommonString.KEY_C + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    public boolean isCoverageDataFilledfor(String visit_date) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_VISIT_DATE + "='" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return filled;
        }

        return filled;
    }


    @SuppressLint("LongLogTag")
    public void deletePreviousUploadedData(String visit_date) {
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where VISIT_DATE < '" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getCount();
                dbcursor.close();
                if (icount > 0) {
                    db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
                    db.delete(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, null, null);
                    db.delete(CommonString.TABLE_INSERT_AFTER_ADDTIONAL_DETAILS, null, null);
                    db.delete(CommonString.TABLE_QUESTION_ANSWER, null, null);
                    db.delete(CommonString.TABLE_TOT_BEFORE, null, null);
                    db.delete(CommonString.TABLE_TOT_AFTER, null, null);
                    db.delete("DR_STOCK", null, null);
                    db.delete("DR_STOCK_IMAGE", null, null);
                    db.delete(CommonString.TABLE_PROMOTION_DATA, null, null);
                    db.delete(CommonString.TABLE_AFTERSTOCK_OTHER, null, null);
                    db.delete(CommonString.TABLE_QUESTION_ANSWER_STOCKAFTER, null, null);
                    db.delete(CommonString.TABLE_INSERT_COMPETITION_INFO, null, null);
                    db.delete(CommonString.TABLE_SHELF_VISIBILITY, null, null);
                    db.delete(CommonString.TABLE_INSERT_SALES_STOCK, null, null);
                    db.delete(CommonString.TABLE_INSERT_MERCHANDISER_ATTENDENCE_TABLE, null, null);
                    db.delete(CommonString.TABLE_INSERT_COMPETITION_PROMOTION, null, null);
                    db.delete(CommonString.TABLE_STOCKWAREHOUSE, null, null);
                }
                dbcursor.close();
            }
        } catch (Exception e) {
            Crashlytics.logException(e);
            e.printStackTrace();
        }
    }


    public void deleteCoverage(String storeid) {
        try {
            db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        }
    }

    public void deleteCoverageFOR() {
        try {
            db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!", e.getMessage());
        }
    }


    public long InsertSkuMasterData(SKUGetterSetter data) {
        db.delete("SKU_MASTER", null, null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            for (int i = 0; i < data.getSku_id().size(); i++) {
                values.put("SKU_ID", data.getSku_id().get(i));
                values.put("SKU", data.getSku_name().get(i));
                values.put("BRAND_ID", data.getBrand_id().get(i));
                values.put("BRAND", data.getBrand().get(i));
                values.put("CATEGORY_ID", data.getCategory_id().get(i));
                values.put("CATEGORY", data.getCategory_name().get(i));
                values.put("COMPANY_ID", data.getCompany_id().get(i));
                values.put("MRP", data.getMRP_sku().get(i));
                l = db.insert("SKU_MASTER", null, values);
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ", ex.getMessage());
        }
        return l;
    }


    public void InsertBrandMasterData(SKUGetterSetter data
    ) {

        db.delete("BRAND_MASTER", null, null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.getBrand_id().size(); i++) {
                values.put("BRAND_ID", data.getBrand_id().get(i));
                values.put("BRAND", data.getBrand().get(i));
                values.put("CATEGORY_ID", data.getCategory_id().get(i));

                values.put("CATEGORY", data.getCategory_name().get(i));

                values.put("COMPANY_ID", data.getCompany_id().get(i)
                );

                db.insert("BRAND_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.getMessage());
        }
    }


    public void InsertJCP(JCPGetterSetter data) {
        db.delete("JOURNEY_PLAN", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getSTORE_ID().size(); i++) {
                values.put("STORE_ID", data.getSTORE_ID().get(i));
                values.put("EMP_ID", data.getEMP_ID().get(i));
                values.put("STORE", data.getSTORE().get(i));
                values.put("CITY", data.getCITY().get(i));
                values.put("VISIT_DATE", data.getVISIT_DATE().get(i));
                values.put("PROCESS_ID", data.getPROCESS_ID().get(i));
                values.put("STORETYPE_ID", data.getSTORETYPE_ID().get(i));

                values.put("KEY_ID", data.getKEY_ID().get(i));

                values.put("REGION_ID", data.getREGION_ID().get(i));
                values.put("UPLOAD_STATUS", data.getUPLOAD_STATUS().get(i));

                values.put("CHECKOUT_STATUS", data.getCHECKOUT_STATUS().get(i));

                values.put("PKD_ENABLE", data.getPACKED_KEY().get(i));

                values.put("STATE_ID", data.getSTATE_ID().get(i));
                values.put("CLASS_ID", data.getCLASS_ID().get(i));
                values.put("COMP_ENABLE", data.getCOMP_ENABLE().get(i));
                db.insert("JOURNEY_PLAN", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.getMessage());
        }
    }

    public void InsertSkuMappingData(StockMappingGetterSetter data) {

        db.delete("STOCK_MAPPING", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getBrand_id().size(); i++) {
                values.put("BRAND_ID", data.getBrand_id().get(i));
                values.put("SKU_ID", data.getSku_id().get(i));
                values.put("SKU_SEQUENCE", data.getSku_sequence().get(i));
                values.put("BRAND_SEQUENCE", data.getBrand_sequence().get(i));
                values.put("PROCESS_ID", data.getProcess_id().get(i));
                values.put("KEY_ID", data.getKEY_ID().get(i));
                values.put("STORETYPE_ID", data.getSTORETYPE_ID().get(i));
                values.put("STATE_ID", data.getSTATE_ID().get(i));
                values.put("CLASS_ID", data.getCLASS_ID().get(i));
                db.insert("STOCK_MAPPING", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.getMessage());
        }

    }

    public long InsertDisplay(DisplayGetterSetter displayData) {

        db.delete("DISPLAY_MASTER", null, null);
        ContentValues values = new ContentValues();
        long l = 0;

        try {

            for (int i = 0; i < displayData.getDisplay_id().size(); i++) {

                values.put("DISPLAY_ID", displayData.getDisplay_id().get(i));
                values.put("DISPLAY", displayData.getDisplay().get(i));
                values.put("IMAGE_URL", displayData.getImage_url().get(i));
                values.put("PATH", displayData.getPath().get(i));
                values.put("COMPETITOR_ONLY", displayData.getCOMPETITOR_ONLY().get(i));

                l = db.insert("DISPLAY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.getMessage());
        }
        return l;

    }

    public void InsertTDS(TDSGetterSetter tDsData) {
        db.delete("DTS_MAPPING", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < tDsData.getDISPLAY_ID().size(); i++) {

                values.put("STORE_ID", tDsData.getSTORE_ID().get(i));
                values.put("CATEGORY_ID", tDsData.getCATEGORY_ID().get(i));
                values.put("DISPLAY_ID", tDsData.getDISPLAY_ID().get(i));
                values.put("TARGET_QTY", tDsData.getTARGET_QTY().get(i));
                values.put("PROCESS_ID", tDsData.getPROCESS_ID().get(i));
                values.put("BRAND_ID", tDsData.getBRAND_ID().get(i));
                values.put("TYPE", tDsData.getTYPE().get(i));
                values.put("UID", tDsData.getUID().get(i));


                db.insert("DTS_MAPPING", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.getMessage());
        }
    }


    public void InsertNonWorkingReasonData(NonWorkingGetterSetter reasonData) {
        db.delete("NON_WORKING_REASON", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < reasonData.getREASON_ID().size(); i++) {
                values.put("REASON_ID", reasonData.getREASON_ID().get(i));
                values.put("REASON", reasonData.getREASON().get(i));
                values.put("ENTRY_ALLOW", Integer.parseInt(reasonData.getENTRY_ALLOW().get(i)));
                db.insert("NON_WORKING_REASON", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.getMessage());
        }

    }

    public void InsertNonWoATTENDENCEData(NonWorkingAttendenceGetterSetter reasonData) {
        db.delete("NON_WORKING_ATTENDANCE", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < reasonData.getREASON_ID().size(); i++) {
                values.put("REASON_ID", reasonData.getREASON_ID().get(i));
                values.put("REASON", reasonData.getREASON().get(i));
                values.put("ENTRY_ALLOW", Integer.parseInt(reasonData.getENTRY_ALLOW().get(i)));
                db.insert("NON_WORKING_ATTENDANCE", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.getMessage());
        }

    }

    public ArrayList<NonWorkingAttendenceGetterSetter> getNonWoATTENDENCEData() {
        Cursor cursordata = null;
        ArrayList<NonWorkingAttendenceGetterSetter> storedata = new ArrayList<>();
        try {
            cursordata = db.rawQuery("SELECT  * from NON_WORKING_ATTENDANCE", null);
            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    NonWorkingAttendenceGetterSetter sb = new NonWorkingAttendenceGetterSetter();
                    sb.setREASON_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("REASON_ID")));
                    sb.setREASON(cursordata.getString(cursordata.getColumnIndexOrThrow("REASON")));
                    sb.setENTRY_ALLOW(cursordata.getString(cursordata.getColumnIndexOrThrow("ENTRY_ALLOW")));
                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ", ex.getMessage());
        }
        return storedata;

    }


    public void InsertNonWorkingSubReasonData(NonWorkingGetterSetter reasonData) {
        db.delete("NON_WORKING_SUB_REASON", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < reasonData.getSUB_REASON_ID().size(); i++) {

                values.put("SUB_REASON_ID", reasonData.getSUB_REASON_ID().get(i));
                values.put("SUB_REASON", reasonData.getSUB_REASON().get(i));
                values.put("REASON_ID", reasonData.getREASON_ID().get(i));

                db.insert("NON_WORKING_SUB_REASON", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Store Data ",
                    ex.getMessage());
        }

    }


    public void InsertQuestionData(QuestionGetterSetter data) {
        db.delete("QUESTION_MASTER", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getQuestion_id().size(); i++) {

                values.put("QUESTION_ID", data.getQuestion_id().get(i));
                values.put("QUESTION", data.getQuestion().get(i));

                db.insert("QUESTION_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.getMessage());
        }

    }


    public void InsertQuestionMappingData(QuestionGetterSetter data) {
        db.delete("QUESTION_MAPPING", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getQuestion_id().size(); i++) {

                values.put("QUESTION_ID", data.getQuestion_id().get(i));
                values.put("DISPLAY_ID", data.getDisplay_id().get(i));

                db.insert("QUESTION_MAPPING", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.getMessage());
        }

    }


    public void InsertPromotionalMappingData(PromotionalDataSetterGetter data) {
        db.delete("PROMOTION_MAPPING", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getSKU_ID().size(); i++) {
                values.put("ID", data.getID().get(i));
                values.put("PROCESS_ID", data.getPROCESS_ID().get(i));
                values.put("KEY_ID", data.getKEY_ID().get(i));
                values.put("CATEGORY_ID", data.getCATEGORY_ID().get(i));
                values.put("SKU_ID", data.getSKU_ID().get(i));
                values.put("PROMOTION", data.getPROMOTION().get(i));
                values.put("STATE_ID", data.getSTATE_ID().get(i));
                db.insert("PROMOTION_MAPPING", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.getMessage());
        }

    }


    public void InsertAdditionalMappingData(AdditionalGetterSetter data) {
        db.delete("MAPPING_ADDITIONAL_VISIBILITY", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getDisplay_id().size(); i++) {
                values.put("STORETYPE_ID", data.getStore_type_id().get(i));
                values.put("CATEGORY_ID", data.getCategory_id().get(i));
                values.put("DISPLAY_ID", data.getDisplay_id().get(i));
                values.put("PROCESS_ID", data.getProcess_id().get(i));

                db.insert("MAPPING_ADDITIONAL_VISIBILITY", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.getMessage());
        }

    }

    public void InsertCompetitionData(CompetitionGetterSetter data) {
        db.delete("COMPANY_MASTER", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getCompany_id().size(); i++) {
                values.put("COMPANY_ID", data.getCompany_id().get(i));
                values.put("COMPANY", data.getCompany().get(i));
                values.put("ISCOMPETITOR", data.getIsCompetitor().get(i));

                db.insert("COMPANY_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Question Data ",
                    ex.getMessage());
        }

    }


    public void InsertSOSTargetData(SOSTargetGetterSetter data) {
        db.delete("TARGET_SOS", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getStore_id().size(); i++) {
                values.put("STORE_ID", data.getStore_id().get(i));
                values.put("CATEGORY_ID", data.getCategory_id().get(i));
                values.put("PROCESS_ID", data.getProcess_id().get(i));
                values.put("TARGET", data.getTarget().get(i));


                db.insert("TARGET_SOS", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert SOS Target Data ",
                    ex.getMessage());
        }

    }


    public void InsertShelfMasterData(ShelfMaster data) {
        db.delete("SHELF_MASTER", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getShelf_id().size(); i++) {
                values.put("SHELF_ID", data.getShelf_id().get(i));
                values.put("SHELF", data.getShelf().get(i));


                db.insert("SHELF_MASTER", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert SOS Target Data ",
                    ex.toString());
        }

    }


    public void InsertMappingShelfData(MappingWellnessSos data) {
        db.delete("MAPPING_WELLNESS_SOS", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getSHELF_ID().size(); i++) {
                values.put("SHELF_ID", data.getSHELF_ID().get(i));
                values.put("PROCESS_ID", data.getPROCESS_ID().get(i));
                values.put("STORETYPE_ID", data.getSTORETYPE_ID().get(i));
                values.put("BRAND_ID", data.getBRAND_ID().get(i));
                values.put("REGION_ID", data.getREGION_ID().get(i));


                db.insert("MAPPING_WELLNESS_SOS", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert SOS Target Data ",
                    ex.toString());
        }

    }


    public long InsertStoreWisePss(StoreWise_Pss data) {
        db.delete("STOREWISE_PSS_SCORE", null, null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {


            for (int i = 0; i < data.getStore_id().size(); i++) {
                values.put("PROCESS_ID", data.getProcess_id().get(i));
                values.put("STORE_ID", data.getStore_id().get(i));
                values.put("CATEGORY_ID", data.getCategory_id().get(i));
                values.put("PERIOD", data.getPeriod().get(i));
                values.put("STOCK_SCORE", data.getSTOCK_SCORE().get(i));
                values.put("ASSET_SCORE", data.getASSET_SCORE().get(i));
                values.put("SOS_SCORE", data.getSos().get(i));
                values.put("PROMO_SCORE", data.getPROMO_SCORE().get(i));
                values.put("PSS", data.getPss().get(i));


                l = db.insert("STOREWISE_PSS_SCORE", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert StoreWise Pss Data ",
                    ex.toString());
        }
        return l;
    }


    public long InsertTargetOHC(TargetToothpestforOHCGetterSetter data) {
        db.delete("TARGET_BRAND_GROUP_WISE", null, null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {


            for (int i = 0; i < data.getStore_Id().size(); i++) {
                values.put("PROCESS_ID", data.getProcess_Id().get(i));
                values.put("STORE_ID", data.getStore_Id().get(i));
                values.put("CATEGORY_ID", data.getCategory_Id().get(i));
                values.put("BRAND_GROUP_ID", data.getBrand_group_Id().get(i));
                values.put("BRAND_GROUP", data.getBrand_group().get(i));
                values.put("TARGET", data.getTarget().get(i));

                l = db.insert("TARGET_BRAND_GROUP_WISE", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert StoreWise Pss Data ", ex.toString());
        }
        return l;
    }


    public ArrayList<TargetToothpestforOHCGetterSetter> getTargetOHCCategoryWise(String store_id, String category_id, String process_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<TargetToothpestforOHCGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM TARGET_BRAND_GROUP_WISE WHERE STORE_ID ='" + store_id + "' AND " + CommonString.KEY_CATEGORY_ID + "='" + category_id +
                    "' AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TargetToothpestforOHCGetterSetter sb = new TargetToothpestforOHCGetterSetter();
                    sb.setBrand_group_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_GROUP_ID")));
                    sb.setBrand_group(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_GROUP")));
                    sb.setTarget(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TARGET")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertEmpMeetingStatus(EmpMeetingStatus data) {
        db.delete("EMP_MEETING_STATUS", null, null);
        ContentValues values = new ContentValues();
        try {
            values.put("MEETING", data.getSTATUS().get(0));
            db.insert("EMP_MEETING_STATUS", null, values);
        } catch (Exception ex) {
            Log.d("Database Exception while Insert StoreWise Pss Data ",
                    ex.toString());
        }

    }

    public void InsertGeoTagData(JCPGetterSetter data) {
        db.delete("GEOTAG_STORE", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getSTORE_ID().size(); i++) {


                values.put("STORE_ID", data.getSTORE_ID().get(i));
                values.put("STORENAME", data.getSTORE().get(i));
                values.put("KEYACCOUNT", data.getKEY_ACCOUNT().get(i));

                values.put("STORETYPE", data.getSTORE_TYPE().get(i));
                values.put("CITY", data.getCITY().get(i));


                values.put(CommonString.KEY_LATITUDE, "");
                values.put(CommonString.KEY_LONGITUDE, "");

                values.put("GEO_TAG_STATUS", data.getGEO_TAG_STATUS().get(i));

                db.insert("GEOTAG_STORE", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Geo Tag Data ",
                    ex.toString());
        }

    }


    //inserting catmanafterStock Data below
    public void InsertCatmanStockAfter(catmanMapping data) {
        db.delete("MAPPING_PRIMARY_WINDOW_DISPLAY", null, null);
        ContentValues values = new ContentValues();
        try {

            for (int i = 0; i < data.getStore_ID().size(); i++) {


                values.put("STORE_ID", data.getStore_ID().get(i));
                values.put("CATEGORY_ID", data.getCategoryid().get(i));
                values.put("DISPLAY_ID", data.getDisplayid().get(i));
                values.put("PROCESS_ID", data.getProcessid().get(i));
                values.put("UID", data.getUid().get(i));
                values.put("BRAND_ID", data.getBrand_id().get(i));


                db.insert("MAPPING_PRIMARY_WINDOW_DISPLAY", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Geo Tag Data ",
                    ex.getMessage());
        }

    }


    //ending here


    public ArrayList<StoreBean> getStoreData(String date) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<StoreBean> list = new ArrayList<StoreBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from JOURNEY_PLAN" + " where "
                    + CommonString.KEY_CURRENT_DATETIME + "='" + date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreBean sb = new StoreBean();
                    sb.setSTORE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
                    sb.setSTORE((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE))));
                    sb.setCITY(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ADDRES)));
                    sb.setEMP_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMP_ID")));
                    sb.setPROCESS_ID((dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROCESS_ID"))));
                    sb.setVISIT_DATE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("VISIT_DATE")));
                    sb.setREGION_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REGION_ID")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    sb.setStoreType_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORETYPE_ID")));
                    sb.setUPLOAD_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setCHECKOUT_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS")));
                    sb.setPkdKey(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PKD_ENABLE")));
                    sb.setSTATE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STATE_ID")));
                    sb.setCLASS_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLASS_ID")));
                    sb.setCOMP_ENABLE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMP_ENABLE")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public StoreBean getStoreStatus(String id, String process_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        StoreBean sb = new StoreBean();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + "JOURNEY_PLAN" + "  WHERE STORE_ID = '" + id + "' AND PROCESS_ID ='" + process_id + "'", null);
            if (dbcursor != null) {
                int numrows = dbcursor.getCount();
                dbcursor.moveToFirst();
                for (int i = 0; i < numrows; i++) {
                    sb.setSTORE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
                    sb.setSTORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE)));
                    sb.setCITY(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ADDRES)));
                    sb.setEMP_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMP_ID")));
                    sb.setPROCESS_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setVISIT_DATE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("VISIT_DATE")));
                    sb.setREGION_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REGION_ID")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    sb.setStoreType_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORETYPE_ID")));
                    sb.setUPLOAD_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setCHECKOUT_STATUS(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKOUT_STATUS")));
                    sb.setCLASS_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLASS_ID")));
                    sb.setSTATE_ID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STATE_ID")));
                    sb.setCOMP_ENABLE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMP_ENABLE")));

                    dbcursor.moveToNext();
                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return sb;

    }


    public ArrayList<StoreBean> getGeoStores() {
        ArrayList<StoreBean> list = new ArrayList<StoreBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * FROM GEOTAG_STORE "
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreBean pb = new StoreBean();

                    pb.setSTORE_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    pb.setSTORE(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORENAME")));

                    pb.setStore_type(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORETYPE")));


                    pb.setGeotag_status(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("GEO_TAG_STATUS")));

                    pb.setKey_account(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("KEYACCOUNT")));


                    pb.setCITY(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("CITY")));

                    pb.setLatitude(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_LATITUDE)));
                    pb.setLongitude(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));

                    list.add(pb);
                    dbcursor.moveToNext();

                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
        return list;
    }


    public ArrayList<GeotaggingBeans> getGeotaggingStatusData() {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        ArrayList<GeotaggingBeans> geodata = new ArrayList<GeotaggingBeans>();

        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString.TABLE_INSERT_GEO_TAG, null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();

                dbcursor.moveToFirst();
                for (int i = 1; i <= numrows; ++i) {

                    GeotaggingBeans data = new GeotaggingBeans();
                    data.setStoreid(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    data.setLatitude(Double.parseDouble(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_LATITUDE))));
                    data.setLongitude(Double.parseDouble(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_LONGITUDE))));
                    data.setUrl1(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
                    data.setUrl2(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
                    data.setUrl3(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
                    data.setStatus(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STATUS)));
                    geodata.add(data);
                    dbcursor.moveToNext();

                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return geodata;

    }


    public void InsertStoregeotagging(String storeid, double lat,
                                      double longitude, String path, String path_inside,
                                      String path_inside1, String status) {

        db.delete(CommonString.TABLE_INSERT_GEO_TAG, CommonString.KEY_STORE_ID
                + " = '" + storeid + "'", null);

        ContentValues values = new ContentValues();

        try {

            values.put(CommonString.KEY_STORE_ID, storeid);
            values.put(CommonString.KEY_LATITUDE, Double.toString(lat));
            values.put(CommonString.KEY_LONGITUDE, Double.toString(longitude));
            values.put(CommonString.KEY_IMAGE_PATH1, path);
            values.put(CommonString.KEY_IMAGE_PATH2, path_inside);
            values.put(CommonString.KEY_IMAGE_PATH3, path_inside1);
            values.put(CommonString.KEY_STATUS, status);

            db.insert(CommonString.TABLE_INSERT_GEO_TAG, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }

    }

    public void InsertSTOREgeotag(String storeid, double lat, double longitude, String path, String status) {

        ContentValues values = new ContentValues();

        try {

            values.put("STORE_ID", storeid);
            values.put("LATITUDE", Double.toString(lat));
            values.put("LONGITUDE", Double.toString(longitude));
            values.put("FRONT_IMAGE", path);
            values.put("GEO_TAG", status);
            db.insert(CommonString.TABLE_STORE_GEOTAGGING, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }

    }


    public void updateGeoTagStatus(String id, String status, double lat,
                                   double longtitude) {

        ContentValues values = new ContentValues();

        try {

            values.put("GEO_TAG_STATUS", status);
            values.put(CommonString.KEY_LATITUDE, Double.toString(lat));
            values.put(CommonString.KEY_LONGITUDE, Double.toString(longtitude));

            db.update(CommonString.TABLE_GEO_TAG_MAPPING, values,
                    CommonString.KEY_STORE_ID + "='" + id + "'", null);

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Earned Toggle Data ",
                    ex.toString());
        }

    }


    public ArrayList<SkuBean> getCategoryList(String process_id, String key_id, String state_id, String CLASS_ID) {

        Log.d("FetchingStoredata---------->Start<------------", "----------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_ID, CATEGORY from SKU_MASTER"
                            + " where SKU_ID in (SELECT SKU_ID FROM STOCK_MAPPING WHERE PROCESS_ID ='" + process_id + "' AND KEY_ID ='" + key_id + "' AND STATE_ID ='" + state_id + "' AND CLASS_ID ='" + CLASS_ID + "' )",
                    null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();
                    sb.setCategory_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_ID")));
                    sb.setCategory((dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> getBrandList(String category_id) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM BRAND_MASTER WHERE COMPANY_ID= '1' AND CATEGORY_ID = '" + category_id + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID")));
                    sb.setBrand((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> gettotBrandList(String category_id, String process_id, String state_id, String store_type_id, String key_id, String class_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery(" SELECT * FROM (SELECT DISTINCT BR.BRAND_ID, BR.BRAND FROM BRAND_MASTER BR INNER JOIN SKU_MASTER SK ON SK.BRAND_ID = BR.BRAND_ID "
                            + " INNER JOIN (SELECT * FROM STOCK_MAPPING WHERE PROCESS_ID ='" + process_id + "' AND KEY_ID='" + key_id + "'AND CLASS_ID='" + class_id + "' AND STATE_ID='" + state_id + "' AND STORETYPE_ID ='" +
                            store_type_id + "') AS M ON SK.SKU_ID = M.SKU_ID WHERE BR.CATEGORY_ID = '" + category_id + "' AND BR.COMPANY_ID ='1') AS A",
                    null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID")));
                    sb.setBrand((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> getSKUList(String category_id, String brand_id, String process_id, String state_id, String store_type_id, String key_id, String class_id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM (SELECT SK.SKU_ID, SK.SKU FROM BRAND_MASTER BR INNER JOIN SKU_MASTER SK ON" +
                            " SK.BRAND_ID = BR.BRAND_ID " +
                            " INNER JOIN (SELECT * FROM STOCK_MAPPING WHERE PROCESS_ID ='" + process_id + "' AND KEY_ID='" + key_id + "'AND CLASS_ID='" + class_id + "' AND STATE_ID ='" + state_id + "' AND STORETYPE_ID ='" +
                            store_type_id + "')" + " AS M " +
                            "  ON SK.SKU_ID = M.SKU_ID WHERE " +
                            " BR.CATEGORY_ID ='" + category_id + "' AND BR.COMPANY_ID ='1' AND BR.BRAND_ID ='" + brand_id + "') AS A",
                    null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setSku_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_ID")));

                    sb.setSku_name((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> getCompetitionBrandList(String category_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM BRAND_MASTER WHERE COMPANY_ID != 1 AND CATEGORY_ID = '" + category_id + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID")));

                    sb.setBrand((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> getDisplayList(String cat_id, String store_id, String store_type_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT D.DISPLAY_ID,D.DISPLAY, D.IMAGE_URL FROM DISPLAY_MASTER D INNER JOIN MAPPING_ADDITIONAL_VISIBILITY M" +
                            " WHERE M.DISPLAY_ID=D.DISPLAY_ID AND M.STORETYPE_ID ='" + store_type_id + "' AND M.CATEGORY_ID = '" + cat_id + "' AND" +
                            " M.PROCESS_ID = '" + process_id + "'",
                    null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setDisplay_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID")));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY"))));

                    sb.setImage_url((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE_URL"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> getDisplayListForComp() {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISPLAY_ID,DISPLAY FROM DISPLAY_MASTER WHERE COMPETITOR_ONLY='1'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setDisplay_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID")));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public ArrayList<SkuBean> getDisplayListAfterStock(String catid, String storeid, String process_id) {


        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT M.DISPLAY_ID,D.DISPLAY,M.UID, D.IMAGE_URL, BR.BRAND_ID, BR.BRAND FROM MAPPING_PRIMARY_WINDOW_DISPLAY M INNER " +
                    " JOIN DISPLAY_MASTER D ON M.DISPLAY_ID=D.DISPLAY_ID INNER JOIN BRAND_MASTER BR ON M.BRAND_ID = BR.BRAND_ID " +
                    " WHERE M.STORE_ID = '" + storeid + "' AND M.CATEGORY_ID = '" + catid + "' AND M.PROCESS_ID ='" + process_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setDisplay_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID")));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY"))));


                    sb.setUID((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("UID"))));


                    sb.setImage_url((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE_URL"))));

                    sb.setBrand_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID"))));

                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND"))));

                    sb.setYesNo("");


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> getBrandSkuList(String category_id, String store_id, String process_id, String state_id
            , String store_type_id, String key_id, String class_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT DISTINCT SK.SKU_ID, SK.SKU, SK.BRAND, SK.BRAND_ID, SK.COMPANY_ID from SKU_MASTER SK INNER JOIN STOCK_MAPPING M ON"
                    + " SK.SKU_ID = M.SKU_ID AND SK.CATEGORY_ID ='" + category_id + "' AND M.KEY_ID ='" + key_id + "' AND M.CLASS_ID ='" + class_id + "' AND M.PROCESS_ID ='" + process_id + "' AND" +
                    " M.STATE_ID ='" + state_id + "' AND M.STORETYPE_ID ='" + store_type_id +
                    "' ORDER BY M.BRAND_SEQUENCE, M.SKU_SEQUENCE", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setBrand_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID")));

                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND"))));

                    sb.setSku_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_ID"))));

                    sb.setSku_name((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU"))));


                    sb.setCompany_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY_ID"))));


                    sb.setSales_qty("");
                    sb.setBefore_Stock("");
                    sb.setAfter_Stock("");

                    sb.setBefore_faceup("");
                    sb.setAfter_faceup("");

                    sb.setALAST_THREE("");
                    sb.setAMORE_SIX("");
                    sb.setATHREE_TO_SIX("");

                    sb.setBLAST_THREE("");
                    sb.setBMORE_SIX("");
                    sb.setBTHREE_TO_SIX("");
                    sb.setBackRoomStock("");


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat------------>Stop<-----------", "---------------");
        return list;

    }


    public ArrayList<SkuBean> getBrandSkuListForSales(String category_id, String store_id, String process_id, String state_id
            , String store_type_id, String key_id, String class_id) {


        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT DISTINCT SK.SKU_ID, SK.SKU, SK.BRAND, SK.BRAND_ID, SK.COMPANY_ID from SKU_MASTER SK INNER JOIN STOCK_MAPPING M ON"
                    + " SK.SKU_ID = M.SKU_ID AND SK.CATEGORY_ID ='" + category_id + "' AND M.PROCESS_ID ='" + process_id +
                    "'AND M.STATE_ID ='" + state_id + "' AND M.KEY_ID ='" + key_id + "' AND M.CLASS_ID ='" + class_id + "' AND M.STORETYPE_ID ='" + store_type_id + "' WHERE SK.COMPANY_ID='1'" +
                    " ORDER BY M.BRAND_SEQUENCE, M.SKU_SEQUENCE", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setBrand_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID")));

                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND"))));

                    sb.setSku_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_ID"))));

                    sb.setSku_name((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU"))));


                    sb.setCompany_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY_ID"))));


                    sb.setSales_qty("");
                    sb.setBefore_Stock("");
                    sb.setAfter_Stock("");

                    sb.setBefore_faceup("");
                    sb.setAfter_faceup("");

                    sb.setALAST_THREE("");
                    sb.setAMORE_SIX("");
                    sb.setATHREE_TO_SIX("");

                    sb.setBLAST_THREE("");
                    sb.setBMORE_SIX("");
                    sb.setBTHREE_TO_SIX("");
                    sb.setBackRoomStock("");


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat------------>Stop<-----------", "---------------");
        return list;

    }


    public ArrayList<SkuBean> getBrandSkuListBackroom(String category_id, String store_id, String process_id, String state_id
            , String store_type_id, String key_id, String class_id) {


        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT DISTINCT SK.SKU_ID, SK.SKU, SK.BRAND, SK.BRAND_ID, SK.COMPANY_ID from SKU_MASTER SK INNER JOIN STOCK_MAPPING M ON"
                    + " SK.SKU_ID = M.SKU_ID AND SK.CATEGORY_ID ='" + category_id + "' AND M.PROCESS_ID ='" + process_id +
                    "'AND M.STATE_ID ='" + state_id + "'AND M.KEY_ID ='" + key_id + "'AND M.CLASS_ID ='" + class_id + "' AND M.STORETYPE_ID ='" + store_type_id + "'" + " WHERE SK.COMPANY_ID='1'" +
                    " ORDER BY M.BRAND_SEQUENCE, M.SKU_SEQUENCE", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setBrand_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID")));

                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND"))));

                    sb.setSku_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_ID"))));

                    sb.setSku_name((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU"))));


                    sb.setCompany_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY_ID"))));


                    sb.setBefore_Stock("");
                    sb.setAfter_Stock("");

                    sb.setBefore_faceup("");
                    sb.setAfter_faceup("");

                    sb.setALAST_THREE("");
                    sb.setAMORE_SIX("");
                    sb.setATHREE_TO_SIX("");

                    sb.setBLAST_THREE("");
                    sb.setBMORE_SIX("");
                    sb.setBTHREE_TO_SIX("");
                    sb.setBackRoomStock("");


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat------------>Stop<-----------", "---------------");
        return list;

    }


    public ArrayList<TOTBean> getTOTData(String store_id, String process_id, String cate_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT D.DISPLAY_ID , D.DISPLAY, D.IMAGE_URL, D.PATH, M.TARGET_QTY," +
                    " M.TYPE, M.UID, M.BRAND_ID, BR.BRAND FROM DTS_MAPPING M INNER JOIN DISPLAY_MASTER D ON"
                    + " M.DISPLAY_ID = D.DISPLAY_ID INNER JOIN BRAND_MASTER BR ON M.BRAND_ID = BR.BRAND_ID WHERE " +
                    " M.STORE_ID = '" + store_id + "' AND M.PROCESS_ID = '" + process_id
                    + "' AND M.CATEGORY_ID = '" + cate_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();

                    sb.setDisplay_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID"))));

                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND"))));

                    sb.setBrand_id((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID"))));

                    sb.setDisplay((dbcursor.getString(dbcursor.getColumnIndexOrThrow("DISPLAY"))));
                    sb.setTrg_quantity((dbcursor.getString(dbcursor.getColumnIndexOrThrow("TARGET_QTY"))));
                    sb.setType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("TYPE")));
                    sb.setUnique_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("UID")));
                    sb.setImage_url(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_URL")));
                    sb.setPath(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PATH")));
                    sb.setAFTER_QTY("");
                    sb.setCamera1("NO");
                    sb.setCamera2("NO");
                    sb.setCamera3("NO");
                    sb.setBEFORE_QTY("");
                    sb.setStock_count("");
                    sb.setImage1("");
                    sb.setImage2("");
                    sb.setImage3("");
                    sb.setKEY_ID("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<TOTBean> getInsertedAfterTOTData(String store_id, String cate_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_TOT_AFTER
                            + " WHERE STORE_ID = '" + store_id + "'"
                            + " AND CATEGORY_ID = '" + cate_id + "'"
                            + " AND PROCESS_ID = '" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();


                    sb.setStore_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID))));

                    sb.setDisplay_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID))));

                    sb.setBrand_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID))));

                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND))));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY))));


                    sb.setTrg_quantity((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_TARGER_QUANTITY))));

                    sb.setAFTER_QTY((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_AFTER_QUANTITY))));


                    sb.setStock_count((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_AFTER_STOCK_COUNT))));


                    sb.setImage1((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE1))));

                    sb.setImage2((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE2))));

                    sb.setImage3((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE3))));


                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));


                    sb.setKEY_ID((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_ID))));


                    sb.setType((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_TYPE))));

                    sb.setImage_url((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE_URL))));

                    sb.setUnique_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<TOTBean> getQuestionsData(String display_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT Q.QUESTION, Q.QUESTION_ID FROM QUESTION_MASTER Q INNER JOIN "
                    + " QUESTION_MAPPING MQ ON Q.QUESTION_ID = MQ.QUESTION_ID INNER JOIN"
                    + " DISPLAY_MASTER D ON D.DISPLAY_ID = MQ.DISPLAY_ID"
                    + " WHERE D.DISPLAY_ID='" + display_id + "' order by Q.QUESTION", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();


                    sb.setQuestion((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION"))));

                    sb.setQuestion_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION_ID"))));


                    sb.setAnswer("NO");


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void updateOutTime(String storeid, String outtime) {

        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_OUT_TIME, outtime);

            db.update(CommonString.TABLE_COVERAGE_DATA, values,
                    CommonString.KEY_STORE_ID + "=" + storeid, null);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public ArrayList<SkuBean> getBeforeStockData(String store_id, String cate_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT SKU_ID , BEFORE_STOCK_QTY , BEFORE_FACEUP," +
                            " BLAST_THREE, BTHREE_TO_SIX, BMORE_SIX  FROM DR_STOCK WHERE STORE_ID = '" + store_id + "' " +
                            "AND CATEGORY_ID = '" + cate_id + "' AND PROCESS_ID='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();


                    sb.setSku_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_ID")));

                    sb.setBefore_Stock((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BEFORE_STOCK_QTY"))));

                    sb.setBefore_faceup((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BEFORE_FACEUP"))));

                    sb.setBLAST_THREE((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BLAST_THREE"))));


                    sb.setBTHREE_TO_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BTHREE_TO_SIX"))));

                    sb.setBMORE_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BMORE_SIX"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> getStockDataForUpload(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM DR_STOCK WHERE STORE_ID = '" + store_id + "' AND PROCESS_ID ='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();


                    sb.setSku_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_ID")));

                    sb.setBefore_Stock((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BEFORE_STOCK_QTY"))));

                    sb.setBefore_faceup((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BEFORE_FACEUP"))));

                    sb.setBLAST_THREE((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BLAST_THREE"))));


                    sb.setBTHREE_TO_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BTHREE_TO_SIX"))));

                    sb.setBMORE_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BMORE_SIX"))));


                    sb.setAfter_Stock((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AFTER_STOCK_QTY"))));

                    sb.setAfter_faceup((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AFTER_FACEUP"))));

                    sb.setALAST_THREE((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ALAST_THREE"))));


                    sb.setATHREE_TO_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ATHREE_TO_SIX"))));

                    sb.setAMORE_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AMORE_SIX"))));


                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SkuBean> getBackRoomStockDataForUpload(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_STOCKWAREHOUSE + " WHERE STORE_ID = '"
                            + store_id + "' AND PROCESS_ID ='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();


                    sb.setSku_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setBackroomStockValue((dbcursor.getInt(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STOCK))));

                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));

                    sb.setProcess_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID))));


                    sb.setStore_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public ArrayList<SkuBean> getsalesStockDataForUpload(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_INSERT_SALES_STOCK +
                    " WHERE STORE_ID = '" + store_id + "' AND PROCESS_ID ='" + process_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();
                    sb.setSku_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
                    sb.setSales_qty(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));

                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));

                    sb.setProcess_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID))));


                    sb.setStore_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<TOTBean> getBeforeTOTDataForUpload(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM TOT_BEFORE WHERE STORE_ID = '" + store_id + "' AND PROCESS_ID ='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();

                    sb.setStore_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_ID")));


                    sb.setBrand_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID")));


                    sb.setDisplay_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID"))));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY"))));

                    sb.setBEFORE_QTY((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BEFORE_QUANTITY))));


                    sb.setTrg_quantity((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_TARGER_QUANTITY))));


                    sb.setStock_count((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BEFORE_STOCK_COUNT))));


                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));

                    sb.setImage1((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE1))));

                    sb.setImage2((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE2))));

                    sb.setImage3((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE3))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<TOTBean> getAfterTOTData(String store_id, String cate_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM TOT_AFTER WHERE STORE_ID = '" + store_id + "' AND CATEGORY_ID = '" +
                            cate_id + "' AND PROCESS_ID='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();

                    sb.setStore_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_ID")));


                    sb.setDisplay_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID"))));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY"))));


                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND"))));


                    sb.setBrand_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID"))));

                    sb.setAFTER_QTY((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_AFTER_QUANTITY))));


                    sb.setTrg_quantity((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_TARGER_QUANTITY))));


                    sb.setStock_count((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_AFTER_STOCK_COUNT))));


                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));

                    sb.setImage1((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE1))));

                    sb.setImage2((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE2))));

                    sb.setImage3((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE3))));

                    sb.setUnique_id((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<TOTBean> getAfterTOTDataForUpload(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM TOT_AFTER WHERE STORE_ID = '" + store_id + "' AND PROCESS_ID='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();

                    sb.setKEY_ID(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_ID)));

                    sb.setStore_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_ID")));


                    sb.setDisplay_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID"))));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY"))));


                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND"))));


                    sb.setBrand_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID"))));

                    sb.setAFTER_QTY((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_AFTER_QUANTITY))));


                    sb.setTrg_quantity((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_TARGER_QUANTITY))));


                    sb.setStock_count((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_AFTER_STOCK_COUNT))));


                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));

                    sb.setImage1((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE1))));

                    sb.setImage2((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE2))));

                    sb.setImage3((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE3))));


                    sb.setUnique_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID))));


                    sb.setType((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_TYPE))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<TOTBean> getAfterComplianceData(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM QUESTION_ANSWER_TABLE WHERE STORE_ID = '" + store_id + "' AND PROCESS_ID ='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();


                    sb.setUnique_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID)));

                    sb.setStore_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_ID")));

                    sb.setCategory_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setDisplay_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID"))));

                    sb.setQuestion_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION_ID"))));

                    sb.setAnswer((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


//	fetching stockafter question data

    public ArrayList<TOTBean> getAfterComplianceDataAfterStock(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_QUESTION_ANSWER_STOCKAFTER + " " +
                            " WHERE STORE_ID = '" + store_id + "' AND PROCESS_ID='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();


                    sb.setUnique_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID)));

                    sb.setStore_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("STORE_ID")));

                    sb.setCategory_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setDisplay_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID"))));

                    sb.setQuestion_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION_ID"))));

                    sb.setAnswer((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    //
    public ArrayList<SkuBean> getAfterStockData(String store_id, String cate_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT S.SKU_ID , S.AFTER_STOCK_QTY , S.AFTER_FACEUP," +
                    " S.ALAST_THREE, S.ATHREE_TO_SIX, S.AMORE_SIX, SK.SKU ,SK.COMPANY_ID ,BR.BRAND FROM DR_STOCK S INNER JOIN SKU_MASTER SK " +
                    " ON S.SKU_ID = SK.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID WHERE S.STORE_ID = '" + store_id + "'" +
                    " AND S.CATEGORY_ID = '" + cate_id + "' AND S.PROCESS_ID ='" + process_id + "' order by SK.SKU,BR.BRAND", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setSku_name(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));

                    sb.setSku_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_ID")));

                    sb.setAfter_Stock((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AFTER_STOCK_QTY"))));

                    sb.setAfter_faceup((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AFTER_FACEUP"))));

                    sb.setALAST_THREE((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ALAST_THREE"))));


                    sb.setATHREE_TO_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ATHREE_TO_SIX"))));

                    sb.setAMORE_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AMORE_SIX"))));


                    sb.setCompany_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY_ID"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<PerformanceBean> getStoreWisePerformanceData(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<PerformanceBean> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("Select * from (select STORE_ID,PERIOD, ROUND(AVG(SOS_SCORE),0) AS SOS_SCORE, ROUND(AVG(STOCK_SCORE),0) AS STOCK_SCORE, ROUND(SUM(ASSET_SCORE),0) AS ASSET_SCORE," +
                    "ROUND(AVG(PROMO_SCORE),0) AS PROMO_SCORE,  ROUND(AVG(PSS),0) AS PSS FROM STOREWISE_PSS_SCORE WHERE" +
                    " STORE_ID = '" + store_id + "' AND PROCESS_ID = '" + process_id + "' GROUP BY STORE_ID,PERIOD" + ") As A", null);

            // , ROUND(AVG(ADDITIONAL),0) AS ADDITIONAL
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    PerformanceBean sb = new PerformanceBean();
                    sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")));
                    sb.setPeriod(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PERIOD")));
                    sb.setSOS_SCORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SOS_SCORE")));
                    sb.setSTOCK_SCORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STOCK_SCORE")));
                    sb.setASSET_SCORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ASSET_SCORE")));
                    sb.setPROMO_SCORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROMO_SCORE")));
                    sb.setPss_avg(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PSS")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<PerformanceBean> getCategoryWisePerformanceData(String store_id, String process_id, String cat_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<PerformanceBean> list = new ArrayList<PerformanceBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("Select STORE_ID,PERIOD, SOS_SCORE , STOCK_SCORE, ASSET_SCORE , PROMO_SCORE, PSS FROM STOREWISE_PSS_SCORE WHERE" +
                    " STORE_ID = '" + store_id + "' AND CATEGORY_ID ='" + cat_id + "' AND PROCESS_ID = '" + process_id + "'GROUP BY STORE_ID,PERIOD", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    PerformanceBean sb = new PerformanceBean();
                    sb.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")));
                    sb.setPeriod(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PERIOD")));
                    sb.setSOS_SCORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SOS_SCORE")));
                    sb.setSTOCK_SCORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STOCK_SCORE")));
                    sb.setASSET_SCORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ASSET_SCORE")));
                    sb.setPROMO_SCORE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROMO_SCORE")));
                    sb.setPss_avg(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PSS")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public ArrayList<SkuBean> getBackRoomStock(String store_id, String cate_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_STOCKWAREHOUSE + " WHERE STORE_ID = '"
                            + store_id + "' AND PROCESS_ID ='" + process_id + "' AND CATEGORY_ID ='" + cate_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND)));

                    sb.setBrand_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

                    sb.setSku_name(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_SKUNAME)));


                    sb.setSku_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setBackRoomStock((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STOCK))));

                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));

                    sb.setProcess_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID))));


                    sb.setStore_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public ArrayList<SkuBean> getAfterStockData2(String store_id, String cate_id, String state_id,
                                                 String store_type_id, String process_id, String key_id, String class_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM DR_STOCK WHERE STORE_ID ='" + store_id + "' AND PROCESS_ID = ' " + process_id + "'", null);
            dbcursor = db.rawQuery("SELECT AFTER_FACEUP FROM DR_STOCK WHERE STORE_ID ='" + store_id + "' AND CATEGORY_ID ='" + cate_id + "' " + " AND PROCESS_ID = ' " + process_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                String test = dbcursor.getString(dbcursor.getColumnIndexOrThrow("AFTER_FACEUP"));
                if (test.equals("")) {
                    dbcursor = db.rawQuery("SELECT SK.SKU_ID , '' as AFTER_STOCK_QTY , '' as AFTER_FACEUP," +
                            " '' as ALAST_THREE, '' as ATHREE_TO_SIX, '' as AMORE_SIX, SK.SKU ,SK.COMPANY_ID ,BR.BRAND FROM STOCK_MAPPING M  INNER JOIN SKU_MASTER SK " +
                            " ON M.SKU_ID = SK.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID  WHERE SK.CATEGORY_ID ='" + cate_id + "' AND M.STATE_ID='" + state_id +
                            "' AND M.STORETYPE_ID ='" + store_type_id + "' AND M.KEY_ID ='" + key_id + "' AND M.CLASS_ID ='" + class_id + "' AND M.PROCESS_ID ='" + process_id
                            + "' ORDER BY M.BRAND_SEQUENCE, M.SKU_SEQUENCE", null);
                } else {

                    dbcursor = db.rawQuery("SELECT S.SKU_ID , S.AFTER_STOCK_QTY , S.AFTER_FACEUP," +
                            " S.ALAST_THREE, S.ATHREE_TO_SIX, S.AMORE_SIX, SK.SKU ,SK.COMPANY_ID ,BR.BRAND FROM DR_STOCK S INNER JOIN SKU_MASTER SK " +
                            " ON S.SKU_ID = SK.SKU_ID INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID WHERE S.STORE_ID = '" + store_id + "'" +
                            " AND S.CATEGORY_ID = '" + cate_id + "' AND S.PROCESS_ID ='" + process_id + "'", null);
                }
            }

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setSku_name(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU")));

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND")));

                    sb.setSku_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("SKU_ID")));

                    sb.setAfter_Stock((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AFTER_STOCK_QTY"))));

                    sb.setAfter_faceup((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AFTER_FACEUP"))));

                    sb.setALAST_THREE((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ALAST_THREE"))));


                    sb.setATHREE_TO_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ATHREE_TO_SIX"))));

                    sb.setAMORE_SIX((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("AMORE_SIX"))));


                    sb.setCompany_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("COMPANY_ID"))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public long InsertCoverage(CoverageBean data, String store_id, String date, String process_id) {
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_STORE_ID + "='" + store_id + "' and " + CommonString.KEY_VISIT_DATE + "='" + date + "' AND PROCESS_ID ='" + process_id + "'", null);

            values.put(CommonString.KEY_STORE_ID, data.getStoreId());
            values.put(CommonString.KEY_USER_ID, data.getUserId());
            values.put(CommonString.KEY_IN_TIME, data.getInTime());
            values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
            values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
            values.put(CommonString.KEY_LATITUDE, data.getLatitude());
            values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
            values.put(CommonString.KEY_REASON_ID, data.getReasonid());
            values.put(CommonString.KEY_REMARK, data.getRemark());
            values.put(CommonString.KEY_VERSION, data.getApp_version());
            values.put(CommonString.KEY_PROCESS_ID, data.getProcess_id());
            values.put(CommonString.KEY_STORE_IMAGE, data.getImage());
            values.put(CommonString.KEY_STORE_IMAGE_ALLOW, data.getImage_allow());
            values.put(CommonString.KEY_STATUS, data.getStatus());
            values.put("SUB_REASON_ID", data.getSub_reasonId());
            values.put(CommonString.KEY_CHECKOUT_IMAGE, data.getCHECKOUT_IMG());
            l = db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);

        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return l;

    }

    public void InsertBeforeStockImagese(String store_id, String cat_id, String image1, String image2, String image3,
                                         String image4, String user_id, String process_id) {
        db.delete("DR_STOCK_IMAGE", "STORE_ID"
                + " = '" + store_id + "' AND CATEGORY_ID ='" + cat_id + "' AND " + CommonString.KEY_PROCESS_ID + "='"
                + process_id + "'", null);

        ContentValues values = new ContentValues();

        try {
            values.put(CommonString.KEY_STORE_ID, store_id);
            values.put(CommonString.KEY_CATEGORY_ID, cat_id);
            values.put(CommonString.KEY_IMAGE1, "");
            values.put(CommonString.KEY_IMAGE2, "");
            values.put(CommonString.KEY_IMAGE3, "");
            values.put(CommonString.KEY_IMAGE4, "");


            values.put(CommonString.KEY_IMAGE5, image1);
            values.put(CommonString.KEY_IMAGE6, image2);
            values.put(CommonString.KEY_IMAGE7, image3);
            values.put(CommonString.KEY_IMAGE8, image4);

            values.put(CommonString.KEY_USER_ID, user_id);
            values.put(CommonString.KEY_PROCESS_ID, process_id);


            db.insert("DR_STOCK_IMAGE", null, values);
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Images ",
                    ex.getMessage());
        }

    }


    public void InsertBeforerStockData(String store_id,
                                       ArrayList<SkuBean> sku_brand_list, String user, String cate_id, String process_id) {


        db.delete("DR_STOCK", "STORE_ID"
                + " = '" + store_id + "' AND CATEGORY_ID ='" + cate_id + "' AND " + CommonString.KEY_PROCESS_ID + "='"
                + process_id + "'", null);


        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < sku_brand_list.size(); i++) {
                SkuBean sdata = new SkuBean();

                sdata = sku_brand_list.get(i);
                values.put("STORE_ID", store_id);
                values.put("SKU_ID", sdata.getSku_id());
                values.put("PROCESS_ID", process_id);

                values.put("BEFORE_STOCK_QTY", sdata.getBefore_Stock());
                values.put("BEFORE_FACEUP", sdata.getBefore_faceup());


                values.put("BLAST_THREE", sdata.getBLAST_THREE());
                values.put("BTHREE_TO_SIX", sdata.getBTHREE_TO_SIX());
                values.put("BMORE_SIX", sdata.getBMORE_SIX());

                values.put("AFTER_STOCK_QTY", sdata.getAfter_Stock());
                values.put("AFTER_FACEUP", sdata.getAfter_faceup());

                values.put("ALAST_THREE", sdata.getALAST_THREE());
                values.put("ATHREE_TO_SIX", sdata.getATHREE_TO_SIX());
                values.put("AMORE_SIX", sdata.getAMORE_SIX());

                values.put("USER_ID", user);


                values.put(CommonString.KEY_CATEGORY_ID, cate_id);


                db.insert("DR_STOCK", null, values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Data ",
                    ex.getMessage());
        }

    }

    public void InsertSalesData(String store_id,
                                ArrayList<SkuBean> sku_brand_list, String user, String cate_id, String process_id) {


        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < sku_brand_list.size(); i++) {
                SkuBean sdata = new SkuBean();

                sdata = sku_brand_list.get(i);

                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_SKU_ID, sdata.getSku_id());
                values.put(CommonString.KEY_SKUNAME, sdata.getSku_name());
                values.put("PROCESS_ID", process_id);
                values.put(CommonString.KEY_QUANTITY, Integer.parseInt(sdata.getSales_qty()));


                values.put("USER_ID", user);


                values.put(CommonString.KEY_CATEGORY_ID, cate_id);
                values.put(CommonString.KEY_BRAND_ID, sdata.getBrand_id());
                values.put(CommonString.KEY_BRAND, sdata.getBrand());


                db.insert(CommonString.TABLE_INSERT_SALES_STOCK, null,
                        values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Data ",
                    ex.getMessage());
        }

    }


    public ArrayList<SkuBean> getSalesStockData(String store_id, String cate_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_INSERT_SALES_STOCK + " WHERE STORE_ID = '"
                            + store_id + "' AND PROCESS_ID ='" + process_id + "' AND CATEGORY_ID ='" + cate_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setBrand(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND)));

                    sb.setBrand_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

                    sb.setSku_name(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_SKUNAME)));


                    sb.setSku_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setSales_qty((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_QUANTITY))));

                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID))));

                    sb.setProcess_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID))));


                    sb.setStore_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void InsertStockwareHouseData(String store_id,
                                         ArrayList<SkuBean> sku_brand_list, String user, String cate_id, String process_id) {


        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < sku_brand_list.size(); i++) {
                SkuBean sdata = new SkuBean();

                sdata = sku_brand_list.get(i);
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_SKU_ID, sdata.getSku_id());
                values.put(CommonString.KEY_PROCESS_ID, process_id);
                values.put(CommonString.KEY_STOCK, Integer.parseInt(sdata.getBackRoomStock()));

                values.put(CommonString.KEY_USER_ID, user);

                values.put(CommonString.KEY_CATEGORY_ID, cate_id);

                values.put(CommonString.KEY_SKUNAME, sdata.getSku_name());
                values.put(CommonString.KEY_BRAND_ID, sdata.getBrand_id());
                values.put(CommonString.KEY_BRAND, sdata.getBrand());


                db.insert(CommonString.TABLE_STOCKWAREHOUSE, null,
                        values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Data ",
                    ex.getMessage());
        }

    }

    public void InsertBeforeTOTData(String store_id,
                                    ArrayList<TOTBean> data, String cate_id, String process_id) {

        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.size(); i++) {
                TOTBean sdata = new TOTBean();

                sdata = data.get(i);
                values.put(CommonString.KEY_STORE_ID, store_id);

                values.put(CommonString.KEY_DISPLAY_ID, sdata.getDisplay_id());
                values.put(CommonString.KEY_BEFORE_QUANTITY, sdata.getBEFORE_QTY());

                values.put(CommonString.KEY_DISPLAY, sdata.getDisplay());
                values.put(CommonString.KEY_BEFORE_STOCK_COUNT, sdata.getStock_count());
                values.put(CommonString.KEY_TARGER_QUANTITY, sdata.getTrg_quantity());

                values.put(CommonString.KEY_CATEGORY_ID, cate_id);
                values.put(CommonString.KEY_IMAGE1, sdata.getImage1());

                values.put(CommonString.KEY_IMAGE2, sdata.getImage2());
                values.put(CommonString.KEY_IMAGE3, sdata.getImage3());
                values.put(CommonString.KEY_BRAND_ID, sdata.getBrand_id());

                values.put(CommonString.KEY_TYPE, sdata.getType());
                values.put(CommonString.KEY_PROCESS_ID, process_id);

                db.insert(CommonString.TABLE_TOT_BEFORE, null,
                        values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Data ",
                    ex.getMessage());
        }

    }


    public void InsertAfterTOTData(String store_id,
                                   ArrayList<TOTBean> data, String cate_id, String process_id) {

        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < data.size(); i++) {
                TOTBean sdata = new TOTBean();


                sdata = data.get(i);
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_DISPLAY_ID, sdata.getDisplay_id());
                values.put(CommonString.KEY_AFTER_QUANTITY, sdata.getAFTER_QTY());

                values.put(CommonString.KEY_AFTER_STOCK_COUNT, sdata.getStock_count());

                values.put(CommonString.KEY_TARGER_QUANTITY, sdata.getTrg_quantity());

                values.put(CommonString.KEY_DISPLAY, sdata.getDisplay());

                values.put(CommonString.KEY_CATEGORY_ID, cate_id);
                values.put(CommonString.KEY_IMAGE1, sdata.getImage1());
                values.put(CommonString.KEY_IMAGE2, sdata.getImage2());
                values.put(CommonString.KEY_IMAGE3, sdata.getImage3());
                values.put(CommonString.KEY_BRAND_ID, sdata.getBrand_id());
                values.put(CommonString.KEY_BRAND, sdata.getBrand());
                values.put(CommonString.UNIQUE_KEY_ID, sdata.getUnique_id());
                values.put(CommonString.KEY_TYPE, sdata.getType());
                values.put(CommonString.KEY_IMAGE_URL, sdata.getImage_url());
                values.put(CommonString.KEY_PROCESS_ID, process_id);


                db.insert(CommonString.TABLE_TOT_AFTER, null,
                        values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Data ",
                    ex.getMessage());
        }

    }


    public void InsertQuestionData(
            ArrayList<TOTBean> data, String display_id, String store_id, String cate_id, String unique_id, String process_id) {

        ContentValues values = new ContentValues();


        try {

            for (int i = 0; i < data.size(); i++) {
                TOTBean sdata = new TOTBean();


                sdata = data.get(i);
                values.put("QUESTION_ID", sdata.getQuestion_id());
                values.put("DISPLAY_ID", display_id);
                values.put("ANSWER", sdata.getAnswer());
                values.put("QUESTION", sdata.getQuestion());

                values.put("STORE_ID", store_id);
                values.put(CommonString.KEY_CATEGORY_ID, cate_id);
                values.put(CommonString.UNIQUE_KEY_ID, unique_id);
                values.put(CommonString.KEY_PROCESS_ID, process_id);


                db.insert("QUESTION_ANSWER_TABLE", null,
                        values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Data ",
                    ex.getMessage());
        }

    }


    public void InsertQuestionDataafterStock(
            ArrayList<TOTBean> data, String display_id, String store_id, String cate_id, String unique_id,
            String process_id) {


        db.delete(CommonString.TABLE_QUESTION_ANSWER_STOCKAFTER, "STORE_ID"
                + " = '" + store_id + "' AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "' AND " + CommonString.KEY_CATEGORY_ID +
                " ='" + cate_id + "'", null);

        ContentValues values = new ContentValues();


        try {

            for (int i = 0; i < data.size(); i++) {
                TOTBean sdata = new TOTBean();

                sdata = data.get(i);
                values.put("QUESTION_ID", sdata.getQuestion_id());
                values.put("QUESTION", sdata.getQuestion());
                values.put("DISPLAY_ID", display_id);
                values.put("ANSWER", sdata.getAnswer());

                values.put("STORE_ID", store_id);
                values.put(CommonString.KEY_CATEGORY_ID, cate_id);
                values.put(CommonString.UNIQUE_KEY_ID, unique_id);
                values.put(CommonString.KEY_PROCESS_ID, process_id);


                db.insert(CommonString.TABLE_QUESTION_ANSWER_STOCKAFTER, null,
                        values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Data ",
                    ex.getMessage());
        }

    }


    public void InsertPromotionData(
            ArrayList<PromotionBean> data, String store_id, String cate_id, String process_id) {

        ContentValues values = new ContentValues();


        try {
            for (int i = 0; i < data.size(); i++) {
                PromotionBean sdata = new PromotionBean();
                sdata = data.get(i);
                values.put("SKU_ID", sdata.getSku_id());
                values.put("STOCK", sdata.getStock());
                values.put("POP", sdata.getPop());
                values.put("RUNNING", sdata.getRunning());
                values.put("RUNNING_CHILD_TOGGLE", sdata.getRunning_child_toggle());
                values.put("RUNNING_CHILD_PRICE", sdata.getRunning_child_price());

                values.put("STORE_ID", store_id);
                values.put(CommonString.KEY_CATEGORY_ID, cate_id);
                values.put(CommonString.KEY_PROCESS_ID, process_id);
                values.put("SPECIAL_ID", sdata.getSpecial_id());
                values.put("PROMOTION", sdata.getPromotion());
                values.put("KEY_SKUNAME", sdata.getSku_name());
                values.put(CommonString.KEY_IMAGE, sdata.getPop_img());

                db.insert(CommonString.TABLE_PROMOTION_DATA, null,
                        values);
            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert Stock Data ",
                    ex.getMessage());
        }

    }


    public ArrayList<TOTBean> getInsertedQuestionsData(String store_id, String display_id, String category_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT * FROM QUESTION_ANSWER_TABLE_STOCKAFTER "
                            + " WHERE DISPLAY_ID ='" + display_id + "' AND STORE_ID = '" + store_id + "' AND "
                            + CommonString.KEY_CATEGORY_ID + "='" + category_id + "' AND "
                            + CommonString.KEY_PROCESS_ID + "='" + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();


                    sb.setKEY_ID((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID))));

                    sb.setQuestion((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION"))));

                    sb.setQuestion_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION_ID"))));


                    sb.setAnswer((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER"))));


//						sb.setAnswer("NO");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<TOTBean> getInsertedTOTQuestionsData(String store_id, String display_id,
                                                          String category_id, String unique_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<TOTBean> list = new ArrayList<TOTBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT * FROM QUESTION_ANSWER_TABLE "
                            + " WHERE DISPLAY_ID ='" + display_id + "' AND STORE_ID = '" + store_id + "' AND " + CommonString.KEY_CATEGORY_ID + "='" + category_id + "'" +
                            " AND " + CommonString.UNIQUE_KEY_ID + "='" + unique_id + "' AND " + CommonString.KEY_PROCESS_ID + "='"
                            + process_id + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    TOTBean sb = new TOTBean();


                    sb.setKEY_ID((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID))));

                    sb.setQuestion((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION"))));

                    sb.setQuestion_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("QUESTION_ID"))));


                    sb.setAnswer((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("ANSWER"))));

                    sb.setDisplay_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID"))));


//						sb.setAnswer("NO");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<ShelfVisibilityBean> getInsertedShelfData(String store_id, String category_id, String process_id, String store_typeid) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<ShelfVisibilityBean> list = new ArrayList<ShelfVisibilityBean>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_SHELF_VISIBILITY
                            + " WHERE STORE_ID = '" + store_id + "' AND  CAT_ID ='" + category_id + "'" +
                            " AND  PROCESS_ID = '" + process_id + "' AND " + CommonString.KEY_STORE_TYPEID + "='" + store_typeid + "'"
                    , null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShelfVisibilityBean sb = new ShelfVisibilityBean();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    sb.setBrand_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND")));
                    sb.setFacing_Target(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FACING_TARGET")));
                    sb.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.YESNO)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_URL")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<ShelfVisibilityBean> getInsertedShelfFacingStockDataForUpload(String store_id, String process_id) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<ShelfVisibilityBean> list = new ArrayList<ShelfVisibilityBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_SHELF_VISIBILITY + " WHERE STORE_ID = '" + store_id + "' AND PROCESS_ID='" + process_id + "'", null);
            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShelfVisibilityBean sb = new ShelfVisibilityBean();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    sb.setBrand_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND")));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_URL")));
                    sb.setFacing_Target(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FACING_TARGET")));
                    sb.setProcess_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PROCESS_ID")));
                    sb.setStore_typeid(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_TYPEID)));
                    sb.setCate_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CAT_ID")));
                    sb.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.YESNO)));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public void updateAfterTOTData(String storeid, String brand_id, String display_id, String quantity, String stock_count, String image1,
                                   String image2, String image3, String process_id) {

        try {
            ContentValues values = new ContentValues();


            values.put(CommonString.KEY_AFTER_QUANTITY, quantity);

            values.put(CommonString.KEY_AFTER_STOCK_COUNT, stock_count);


            values.put(CommonString.KEY_IMAGE1, image1);
            values.put(CommonString.KEY_IMAGE2, image2);
            values.put(CommonString.KEY_IMAGE3, image3);


            db.update(CommonString.TABLE_TOT_AFTER, values,
                    " STORE_ID ='" + storeid + "' AND  BRAND_ID = '" + brand_id + "' AND DISPLAY_ID = '" + display_id + "'" +
                            " AND PROCESS_ID ='" + process_id + "'", null);


        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());

        }
    }

    public void updatePromotionData(String store_id, String category_id, String process_id, String Special_id, String sku_id,
                                    String Stock, String Pop, String Running, String running_child_toggle, String pop_image, String running_child_price) {

        try {
            ContentValues values = new ContentValues();


            values.put("STOCK", Stock);
            values.put("POP", Pop);
            values.put("RUNNING", Running);
            values.put("RUNNING_CHILD_TOGGLE", running_child_toggle);
            values.put("RUNNING_CHILD_PRICE", running_child_price);
            values.put(CommonString.KEY_IMAGE, pop_image);


            db.update("PROMOTION_DATA", values,
                    " STORE_ID ='" + store_id + "' AND  CATEGORY_ID = '" + category_id + "' AND PROCESS_ID = '" + process_id + "'" +
                            " AND SPECIAL_ID = '" + Special_id + "' AND SKU_ID = '" + sku_id + "'", null);


        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());

        }
    }


    public void updateBackStoreData(String store_id, String category_id, String process_id, String stock, String sku_id) {

        try {
            ContentValues values = new ContentValues();


            values.put(CommonString.KEY_STOCK, Integer.parseInt(stock));


            db.update(CommonString.TABLE_STOCKWAREHOUSE, values,
                    " STORE_ID ='" + store_id + "' AND  CATEGORY_ID = '" + category_id + "' AND PROCESS_ID = '" + process_id + "'" +
                            " AND  SKU_ID = '" + sku_id + "'", null);


        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());

        }
    }


    public void updateSalesData(String store_id, String category_id, String process_id, String stock, String sku_id) {

        try {
            ContentValues values = new ContentValues();


            values.put(CommonString.KEY_QUANTITY, Integer.parseInt(stock));


            db.update(CommonString.TABLE_INSERT_SALES_STOCK, values,
                    " STORE_ID = '" + store_id + "' AND  CATEGORY_ID = '" + category_id + "' AND PROCESS_ID = '" + process_id + "'" +
                            " AND  SKU_ID = '" + sku_id + "'", null);


        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());

        }
    }


    public void updateGapsData(String storeid, String Cat_id, String display_id, String question_id,
                               String answer, String unique_id, String process_id) {

        try {
            ContentValues values = new ContentValues();

            values.put("ANSWER", answer);

            db.update("QUESTION_ANSWER_TABLE", values,
                    " STORE_ID ='" + storeid + "' AND  CATEGORY_ID = '" + Cat_id + "' AND DISPLAY_ID = '" + display_id + "'" +
                            " AND QUESTION_ID = '" + question_id + "' AND "
                            + CommonString.UNIQUE_KEY_ID + " = '" + unique_id + "'" +
                            " AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'", null);


        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        }
    }


    public long updateShelWithStockfData(ArrayList<ShelfVisibilityBean> shelf_list,
                                         String storeid, String process_id, String store_typeid) {
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            for (int i = 0; i < shelf_list.size(); i++) {
                values.put(CommonString.YESNO, shelf_list.get(i).getAnswer());
                values.put("IMAGE_URL", shelf_list.get(i).getImage());
                l = db.update(CommonString.TABLE_SHELF_VISIBILITY, values,
                        " STORE_ID ='" + storeid + "' AND BRAND_ID ='" + shelf_list.get(i).getBrand_id() +
                                "' AND PROCESS_ID ='" + process_id + "' AND " + CommonString.KEY_STORE_TYPEID +
                                " ='" + store_typeid + "'", null);
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!", e.getMessage());

        }
        return l;
    }

    public long InsertAdditionalInfo(ArrayList<SkuBean> data, String store_id, String cate_id, String process_id) {
        db.delete(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, CommonString.KEY_STORE_ID + " ='" + store_id +
                "' AND PROCESS_ID ='" + process_id + "' AND CATEGORY_ID ='" + cate_id + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;

        try {
            for (int k = 0; k < data.size(); k++) {
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_CATEGORY_ID, cate_id);
                values.put(CommonString.KEY_PROCESS_ID, process_id);
                values.put(CommonString.KEY_BRAND, data.get(k).getBrand());
                values.put(CommonString.KEY_BRAND_ID, data.get(k).getBrand_id());
                values.put(CommonString.KEY_DISPLAY, data.get(k).getDisplay());
                values.put(CommonString.KEY_DISPLAY_ID, data.get(k).getDisplay_id());
                values.put(CommonString.KEY_QUANTITY, data.get(k).getQuantity());
                values.put(CommonString.KEY_IMAGE, data.get(k).getAdditional_image());
                values.put(CommonString.KEY_ADDITIONAL_YESYorNO, data.get(k).getYesorno());
                l = db.insert(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, null, values);
            }


        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Fabricator master data ", ex.toString());
        }
        return l;
    }

    public void deleteProductEntry(String id) {
        try {
            db.delete(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, "KEY_ID" + "='" + id + "'", null);
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    public void deleteProductEntryData(String store_cd, String process_cd, String category_cd) {
        try {

            db.delete(CommonString.TABLE_INSERT_ADDTIONAL_DETAILS, CommonString.KEY_STORE_ID + " = '" + store_cd
                    + "'  AND PROCESS_ID ='" + process_cd + "' AND CATEGORY_ID='" + category_cd + "'", null);
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }


    public void deleteTOTStockEntry(String id) {
        try {
            db.delete(CommonString.TABLE_INSERT_STOCK_TOT, "KEY_ID" + "='" + id + "'", null);
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }


    public void InsertStockTot(TOTBean data) {
        ContentValues values = new ContentValues();

        try {

            values.put(CommonString.KEY_STORE_ID, data.getStore_id());
            values.put(CommonString.KEY_BRAND, data.getBrand());
            values.put(CommonString.KEY_BRAND_ID, data.getBrand_id());

            values.put(CommonString.KEY_DISPLAY_ID, data.getDisplay_id());

            values.put(CommonString.KEY_QUANTITY, data.getQuantity());
            values.put(CommonString.KEY_SKU_ID, data.getSku_id());
            values.put(CommonString.KEY_SKUNAME, data.getSku_name());
            values.put(CommonString.UNIQUE_KEY_ID, data.getUnique_id());

            values.put(CommonString.KEY_CATEGORY_ID, data.getCategory_id());

            values.put(CommonString.KEY_PROCESS_ID, data.getProcess_id());


            db.insert(CommonString.TABLE_INSERT_STOCK_TOT, null, values);


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Fabricator master data ", ex.getMessage());
        }

    }


    public long InsertCompetitionInfo(ArrayList<SkuBean> data, String store_id, String cate_id, String process_id) {
        db.delete(CommonString.TABLE_INSERT_COMPETITION_INFO, CommonString.KEY_STORE_ID + " ='" + store_id +
                "' AND PROCESS_ID ='" + process_id + "' AND CATEGORY_ID ='" + cate_id + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            for (int i = 0; i < data.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_CATEGORY_ID, cate_id);
                values.put(CommonString.KEY_PROCESS_ID, process_id);
                values.put(CommonString.KEY_BRAND, data.get(i).getBrand());
                values.put(CommonString.KEY_BRAND_ID, data.get(i).getBrand_id());
                values.put(CommonString.KEY_DISPLAY, data.get(i).getDisplay());
                values.put(CommonString.KEY_DISPLAY_ID, data.get(i).getDisplay_id());
                values.put(CommonString.KEY_QUANTITY, data.get(i).getQuantity());
                values.put(CommonString.KEY_IMAGE, data.get(i).getAdditional_image());
                values.put(CommonString.KEY_COMP_TRACT_EXIST, data.get(i).isCompTExist());

                l = db.insert(CommonString.TABLE_INSERT_COMPETITION_INFO, null, values);
            }


        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Competition Tracking data ", ex.toString());
        }
        return l;
    }


    public void deleteCompetitionEntry(String id) {
        try {
            db.delete(CommonString.TABLE_INSERT_COMPETITION_INFO, "KEY_ID" + "='" + id + "'", null);
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }


    public ArrayList<SkuBean> getProductEntryDetail(String store_id, String cate_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<SkuBean> productData = new ArrayList<SkuBean>();
        try {
            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_INSERT_ADDTIONAL_DETAILS + " where "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "
                            + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "' AND "
                            + CommonString.KEY_PROCESS_ID + " ='" + process_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SkuBean sb = new SkuBean();
                    sb.setKey_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    sb.setBrand_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    sb.setBrand(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_BRAND)));
                    sb.setCategory_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));
                    sb.setDisplay(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_DISPLAY)));
                    sb.setDisplay_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));
                    sb.setQuantity(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));
                    sb.setAdditional_image(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
                    sb.setYesorno(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_ADDITIONAL_YESYorNO)));
                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();
            }
        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return productData;

    }


    public ArrayList<TOTBean> getTOTStockEntryDetail(String store_id, String cate_id, String process_id,
                                                     String display_id, String unique_id) {
        Cursor cursordata = null;
        ArrayList<TOTBean> productData = new ArrayList<TOTBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_INSERT_STOCK_TOT + " WHERE "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "
                            + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "' AND "
                            + CommonString.KEY_PROCESS_ID + " ='" + process_id + "' AND "
                            + CommonString.KEY_DISPLAY_ID + "= '" + display_id + "' AND "
                            + CommonString.UNIQUE_KEY_ID + "= '" + unique_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    TOTBean sb = new TOTBean();

                    sb.setKEY_ID(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_ID)));

                    sb.setBrand_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

                    sb.setBrand(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND)));

                    sb.setCategory_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setDisplay_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));

                    sb.setQuantity(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));

                    sb.setSku_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setSku_name(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKUNAME)));

                    sb.setProcess_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));

                    sb.setUnique_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID)));


                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<TOTBean> getTOTStockEntryDetailFORVAlidation(String store_id, String cate_id, String process_id,
                                                                  String display_id, String unique_id, String brand_id, String sku_id) {
        Cursor cursordata = null;
        ArrayList<TOTBean> productData = new ArrayList<TOTBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_INSERT_STOCK_TOT + " WHERE "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "
                            + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "' AND "
                            + CommonString.KEY_PROCESS_ID + " ='" + process_id + "' AND "
                            + CommonString.KEY_DISPLAY_ID + "= '" + display_id + "' AND "
                            + CommonString.UNIQUE_KEY_ID + "= '" + unique_id + "' AND "
                            + CommonString.KEY_BRAND_ID + "='" + brand_id + "' AND "
                            + CommonString.KEY_SKU_ID + "='" + sku_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    TOTBean sb = new TOTBean();

                    sb.setKEY_ID(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_ID)));

                    sb.setBrand_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

                    sb.setBrand(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND)));

                    sb.setCategory_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setDisplay_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));

                    sb.setQuantity(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));

                    sb.setSku_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setSku_name(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKUNAME)));

                    sb.setProcess_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));

                    sb.setUnique_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID)));


                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<TOTBean> getTOTStockEntryDetailForUpload(String store_id, String process_id
    ) {
        Cursor cursordata = null;
        ArrayList<TOTBean> productData = new ArrayList<TOTBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_INSERT_STOCK_TOT + " WHERE "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "

                            + CommonString.KEY_PROCESS_ID + " ='" + process_id + "'"
                    ,
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    TOTBean sb = new TOTBean();

                    sb.setKEY_ID(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_ID)));

                    sb.setBrand_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

                    sb.setBrand(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND)));

                    sb.setCategory_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setDisplay_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));

                    sb.setQuantity(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));

                    sb.setSku_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setSku_name(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKUNAME)));

                    sb.setProcess_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));


                    sb.setUnique_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.UNIQUE_KEY_ID)));


                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public void deleteTOTStockEntry(String store_id, String cate_id, String process_id,
                                    String display_id, String unique_id) {

        String a;


        try {
            db.delete(CommonString.TABLE_INSERT_STOCK_TOT, "CATEGORY_ID" + "='" + cate_id + "' AND STORE_ID ='" + store_id + "'" +
                    " AND PROCESS_ID ='" + process_id + "' AND DISPLAY_ID ='" + display_id + "' AND UNIQUE_KEY_ID ='" + unique_id + "'", null);
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }


    public ArrayList<SkuBean> getEnteredCompetitionDetail(String store_id, String cate_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<SkuBean> productData = new ArrayList<>();
        try {
            cursordata = db.rawQuery("SELECT  * from " + CommonString.TABLE_INSERT_COMPETITION_INFO + " where " + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "
                            + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "' AND "
                            + CommonString.KEY_PROCESS_ID + "='" + process_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SkuBean sb = new SkuBean();
                    sb.setKey_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    sb.setBrand_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

                    sb.setBrand(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_BRAND)));
                    sb.setCategory_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));

                    sb.setDisplay(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_DISPLAY)));
                    sb.setDisplay_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));
                    sb.setProcess_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));

                    sb.setQuantity(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));
                    sb.setAdditional_image(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
                    String exists = cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_COMP_TRACT_EXIST));
                    if (exists.equals("1")) {
                        sb.setCompTExist(true);
                    } else {
                        sb.setCompTExist(false);
                    }


                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<SkuBean> getEnteredCompetitionDetailForUploading(String store_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<SkuBean> productData = new ArrayList<SkuBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from " + CommonString.TABLE_INSERT_COMPETITION_INFO + " where " +
                    CommonString.KEY_STORE_ID + "='" + store_id + "' AND PROCESS_ID ='" + process_id + "'", null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SkuBean sb = new SkuBean();
                    sb.setKey_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    sb.setBrand_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    sb.setBrand(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_BRAND)));
                    sb.setCategory_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));
                    sb.setDisplay(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_DISPLAY)));
                    sb.setDisplay_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));
                    sb.setQuantity(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));
                    sb.setAdditional_image(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
                    String exists = cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_COMP_TRACT_EXIST));
                    if (exists.equals("1")) {
                        sb.setCompTExist(true);
                    } else {
                        sb.setCompTExist(false);
                    }

                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<PromotionBean> getInsertedPromoCompliance(String store_id, String cate_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<PromotionBean> productData = new ArrayList<>();

        try {

            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_PROMOTION_DATA + " where "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND " + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "'" +
                            " AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'"
                    ,
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    PromotionBean sb = new PromotionBean();

                    sb.setSpecial_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SPECIAL_ID")));

                    sb.setSku_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setStock(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("STOCK")));

                    sb.setPop(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("POP")));


                    sb.setRunning(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("RUNNING")));

                    sb.setRunning_child_toggle(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("RUNNING_CHILD_TOGGLE")));


                    sb.setRunning_child_price(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("RUNNING_CHILD_PRICE")));

                    sb.setStore_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));

                    sb.setCategory_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setProcess_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));


                    sb.setPromotion(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("PROMOTION")));

                    sb.setSku_name(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("KEY_SKUNAME")));

                    sb.setPop_img(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE)));


                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<PromotionBean> getInsertedPromoCompliances(String store_id, String cate_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<PromotionBean> productData = new ArrayList<PromotionBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_PROMOTION_DATA + " where "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND " + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "'" +
                            " AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'"
                    ,
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    PromotionBean sb = new PromotionBean();

                    sb.setSpecial_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SPECIAL_ID")));

                    sb.setSku_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setStock(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("STOCK")));

                    sb.setPop(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("POP")));


                    sb.setRunning(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("RUNNING")));

                    sb.setStore_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));

                    sb.setCategory_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setProcess_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));


                    sb.setPromotion(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("PROMOTION")));

                    sb.setSku_name(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("KEY_SKUNAME")));


                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return productData;

    }

    public ArrayList<PromotionBean> getInsertedPromoCompliance(String store_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<PromotionBean> productData = new ArrayList<PromotionBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_PROMOTION_DATA + " where "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "
                            + CommonString.KEY_PROCESS_ID + "='" + process_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    PromotionBean sb = new PromotionBean();

                    sb.setSpecial_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SPECIAL_ID")));

                    sb.setSku_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));

                    sb.setStock(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("STOCK")));

                    sb.setPop(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("POP")));


                    sb.setRunning(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("RUNNING")));

                    sb.setStore_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));

                    sb.setCategory_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setProcess_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));

                    sb.setPop_img(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE)));


                    sb.setPromotion(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("PROMOTION")));

                    sb.setRunning_child_toggle(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("RUNNING_CHILD_TOGGLE")));


                    sb.setRunning_child_price(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("RUNNING_CHILD_PRICE")));
                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<SkuBean> getProductEntryDetailForUpload(String store_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<SkuBean> productData = new ArrayList<SkuBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_INSERT_ADDTIONAL_DETAILS + " where "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "
                            + CommonString.KEY_PROCESS_ID + "='" + process_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setKey_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_ID)));

                    sb.setBrand_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

                    sb.setBrand(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND)));

                    sb.setCategory_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));


                    sb.setDisplay(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY)));
                    sb.setDisplay_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));

                    sb.setQuantity(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));


                    sb.setAdditional_image(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE)));

                    sb.setYesorno(cursordata.getString(cursordata.
                            getColumnIndexOrThrow(CommonString.KEY_ADDITIONAL_YESYorNO)));


                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }

    public ArrayList<SkuBean> getSOSTarget(String store_id, String cate_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<SkuBean> productData = new ArrayList<SkuBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from TARGET_SOS" + " where " + CommonString.KEY_STORE_ID + "='" + store_id + "' AND " + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "' AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SkuBean sb = new SkuBean();
                    sb.setStore_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setCategory_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));
                    sb.setProcess_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setSos_target(cursordata.getString(cursordata.getColumnIndexOrThrow("TARGET")));
                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public String getAFTERSOS(String store_id, String cate_id, String process_id) {
        Cursor cursordata = null;
        String calculatedTarget = "";


        try {

            if (cate_id.equals("2") || cate_id.equals("6")) {
                cursordata = db.rawQuery("SELECT " +
                        " CAST(ACH.ACTUAL AS FLOAT)/ CAST(TG.TARGET AS FLOAT)*100" +
                        " AS SOS FROM " + "(SELECT STORE_ID, COUNT(*) AS TARGET FROM SHELF_VISIBILITY_WITH_STOCK " +
                        " WHERE STORE_ID ='" + store_id + "' AND PROCESS_ID ='" + process_id + "' GROUP BY STORE_ID) AS TG" + " INNER JOIN " +
                        "(SELECT STORE_ID, COUNT(*) AS ACTUAL FROM SHELF_VISIBILITY_WITH_STOCK WHERE STORE_ID ='" + store_id + "'" +
                        " AND PROCESS_ID ='" + process_id + "' AND YESNO = 'YES' " + "GROUP BY STORE_ID) AS ACH " +
                        " ON TG.STORE_ID = ACH.STORE_ID", null);
            } else {
                cursordata = db.rawQuery("SELECT CAST(GSK_TOT AS FLOAT)/ CAST(CAT_TOT AS FLOAT)*100 AS SOS FROM" +
                        "(SELECT ST.CATEGORY_ID, SUM(ST.AFTER_FACEUP) AS CAT_TOT FROM DR_STOCK ST INNER JOIN SKU_MASTER SK" +
                        " ON ST.SKU_ID = SK.SKU_ID " +
                        " INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID" +
                        " WHERE SK.SKU NOT LIKE '%brush%' AND ST.STORE_ID ='" + store_id + "'" + " AND ST.CATEGORY_ID = '" + cate_id + "' AND ST.PROCESS_ID='" + process_id + "'" +
                        " GROUP BY ST.CATEGORY_ID) AS CT INNER JOIN " +
                        " (SELECT ST.CATEGORY_ID, SUM(ST.AFTER_FACEUP)AS GSK_TOT FROM DR_STOCK ST INNER JOIN SKU_MASTER SK " +
                        " ON ST.SKU_ID = SK.SKU_ID" +
                        " INNER JOIN BRAND_MASTER BR ON SK.BRAND_ID = BR.BRAND_ID" +
                        " WHERE SK.SKU NOT LIKE '%brush%' AND ST.STORE_ID = '" + store_id + "'" + " AND ST.CATEGORY_ID = '" + cate_id + "' AND ST.PROCESS_ID ='" + process_id + "'" + " AND BR.COMPANY_ID = 1" +
                        " GROUP BY ST.CATEGORY_ID) AS BT" +
                        " ON CT.CATEGORY_ID = BT.CATEGORY_ID", null);
            }


            if (cursordata != null) {
                cursordata.moveToFirst();
                calculatedTarget = cursordata.getString(0);

                cursordata.close();

            }


        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return calculatedTarget;

    }

    public ArrayList<SkuBean> getAfterProductEntryDetail(String store_id) {
        Cursor cursordata = null;
        ArrayList<SkuBean> productData = new ArrayList<SkuBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from "
                            + CommonString.TABLE_INSERT_AFTER_ADDTIONAL_DETAILS + " where "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setKey_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_ID)));

                    sb.setBrand_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));

                    sb.setBrand(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND)));


                    sb.setDisplay(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY)));
                    sb.setDisplay_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));

                    sb.setQuantity(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_QUANTITY)));


                    sb.setAdditional_image(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE)));


                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<SkuBean> getStockImagesLIST(String store_id, String cate_id, String username, String process_id) {
        Cursor cursordata = null;
        ArrayList<SkuBean> productData = new ArrayList<SkuBean>();

        try {


            cursordata = db.rawQuery("SELECT  * from DR_STOCK_IMAGE "
                            + " where "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "
                            + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "' AND "
                            + CommonString.KEY_USER_ID + "='" + username + "' AND "
                            + CommonString.KEY_PROCESS_ID + "='" + process_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setImage1(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE1)));

                    sb.setImage2(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE2)));


                    sb.setImage3(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE3)));


                    sb.setImage4(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE4)));


                    sb.setImage5(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE5)));


                    sb.setImage6(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE6)));


                    sb.setImage7(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE7)));

                    sb.setImage8(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE8)));

                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<SkuBean> getStockImagesForUpload(String store_id, String username, String process_id) {
        Cursor cursordata = null;
        ArrayList<SkuBean> productData = new ArrayList<SkuBean>();

        try {


            cursordata = db.rawQuery("SELECT  * from DR_STOCK_IMAGE "
                            + " where "
                            + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "

                            + CommonString.KEY_USER_ID + "='" + username + "' AND "
                            + CommonString.KEY_PROCESS_ID + "='" + process_id + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setImage1(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE1)));

                    sb.setImage2(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE2)));


                    sb.setImage3(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE3)));


                    sb.setImage4(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE4)));


                    sb.setImage5(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE5)));


                    sb.setImage6(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE6)));


                    sb.setImage7(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE7)));

                    sb.setImage8(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE8)));


                    sb.setCategory_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));

                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return productData;

    }


    public ArrayList<StoreBean> getJCP(String date) {
        Cursor cursordata = null;
        ArrayList<StoreBean> storedata = new ArrayList<StoreBean>();

        try {

            cursordata = db.rawQuery("SELECT  * from JOURNEY_PLAN WHERE VISIT_DATE = '" + date + "'", null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    StoreBean sb = new StoreBean();
                    sb.setKey_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    sb.setSTORE_ID(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setEMP_ID(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_EMP_CD)));
                    sb.setSTORE(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_STORE)));
                    sb.setREGION_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("REGION_ID")));
                    sb.setStoreType_id(cursordata.getString(cursordata.getColumnIndexOrThrow("STORETYPE_ID")));
                    sb.setUPLOAD_STATUS(cursordata.getString(cursordata.getColumnIndexOrThrow("UPLOAD_STATUS")));
                    sb.setPROCESS_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("PROCESS_ID")));
                    sb.setVISIT_DATE(cursordata.getString(cursordata.getColumnIndexOrThrow("VISIT_DATE")));
                    sb.setCHECKOUT_STATUS(cursordata.getString(cursordata.getColumnIndexOrThrow("CHECKOUT_STATUS")));
                    sb.setCITY(cursordata.getString(cursordata.getColumnIndexOrThrow("CITY")));
                    sb.setCLASS_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("CLASS_ID")));
                    sb.setSTATE_ID(cursordata.getString(cursordata.getColumnIndexOrThrow("STATE_ID")));
                    sb.setCOMP_ENABLE(cursordata.getString(cursordata.getColumnIndexOrThrow("COMP_ENABLE")));


                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return storedata;

    }

    public ArrayList<ReasonModel> getNonWorkingReason(boolean upload_status) {
        Cursor cursordata = null;
        String query = "";
        ArrayList<ReasonModel> storedata = new ArrayList<ReasonModel>();
        try {
            if (upload_status) {
                query = "SELECT  * from NON_WORKING_REASON  WHERE ENTRY_ALLOW='1'";
            } else {
                query = "SELECT  * from NON_WORKING_REASON";
            }

            cursordata = db.rawQuery(query, null);
            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    ReasonModel sb = new ReasonModel();
                    sb.setReasonid(cursordata.getString(cursordata.getColumnIndexOrThrow("REASON_ID")));
                    sb.setReason(cursordata.getString(cursordata.getColumnIndexOrThrow("REASON")));
                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();
            }
        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ", ex.getMessage());
        }
        return storedata;

    }

    public ArrayList<ReasonModel> getSubReasonNonWorking(String reasonid) {
        Cursor cursordata = null;
        ArrayList<ReasonModel> storedata = new ArrayList<ReasonModel>();

        try {

            cursordata = db.rawQuery("SELECT  SUB_REASON_ID, SUB_REASON from NON_WORKING_SUB_REASON WHERE REASON_ID = '" + reasonid + "'",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    ReasonModel sb = new ReasonModel();

                    sb.setSub_reasonId(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SUB_REASON_ID")));

                    sb.setSub_reason(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SUB_REASON")));


                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return storedata;

    }


    public ArrayList<PromotionBean> getPromoComplianceData(String key_id, String process_id, String cate_id) {
        Cursor cursordata = null;
        ArrayList<PromotionBean> storedata = new ArrayList<PromotionBean>();

        try {

            cursordata = db.rawQuery("SELECT M.ID, M.SKU_ID, M.PROMOTION, SK.SKU from PROMOTION_MAPPING M INNER JOIN " +
                            " SKU_MASTER SK ON M.SKU_ID = SK.SKU_ID WHERE M.KEY_ID = '" + key_id + "' AND M.PROCESS_ID = '" + process_id + "'" +
                            " AND M.CATEGORY_ID = '" + cate_id + "' order by SK.SKU",
                    null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    PromotionBean sb = new PromotionBean();


                    sb.setSpecial_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("ID")));

                    sb.setSku_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SKU_ID")));

                    sb.setSku_name(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SKU")));


                    sb.setPromotion(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("PROMOTION")));


                    sb.setStock("NO");

                    sb.setPop("NO");

                    sb.setRunning("NO");


                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return storedata;
    }


    public ArrayList<PromotionBean> getPromoComplianceData2(String key_id, String process_id, String cate_id, String state_id) {
        Cursor cursordata = null;
        ArrayList<PromotionBean> storedata = new ArrayList<PromotionBean>();

        try {
            cursordata = db.rawQuery("SELECT M.ID, M.SKU_ID, M.PROMOTION, SK.SKU from PROMOTION_MAPPING M INNER JOIN " +
                    " SKU_MASTER SK ON M.SKU_ID = SK.SKU_ID WHERE M.KEY_ID = '" + key_id + "' AND M.PROCESS_ID = '" + process_id + "'" +
                    " AND M.CATEGORY_ID = '" + cate_id + "' AND M.STATE_ID = '" + state_id + "' order by SK.SKU", null);

            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    PromotionBean sb = new PromotionBean();


                    sb.setSpecial_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("ID")));

                    sb.setSku_id(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SKU_ID")));

                    sb.setSku_name(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("SKU")));


                    sb.setPromotion(cursordata.getString(cursordata
                            .getColumnIndexOrThrow("PROMOTION")));


                    sb.setStock("NO");

                    sb.setPop("NO");

                    sb.setRunning("NO");

                    sb.setRunning_child_toggle("NO");
                    sb.setRunning_child_price("");


                    storedata.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Log.d("Database Exception while Insert Closes Data ",
                    ex.getMessage());
        }
        return storedata;
    }


    public ArrayList<CoverageBean> getCoverageData(String visitdate, String store_id, String process_id) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            if (visitdate == null) {
                dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA, null);
            } else if (store_id == null) {
                dbcursor = db.rawQuery("SELECT  * from "
                                + CommonString.TABLE_COVERAGE_DATA + " where "
                                + CommonString.KEY_VISIT_DATE + "='" + visitdate + "'",
                        null);
            } else {
                dbcursor = db.rawQuery("SELECT  * from "
                        + CommonString.TABLE_COVERAGE_DATA + " where "
                        + CommonString.KEY_VISIT_DATE + "='" + visitdate
                        + "' AND " + CommonString.KEY_STORE_ID + "='"
                        + store_id + "' AND PROCESS_ID ='" + process_id + "'", null);
            }
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();

                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID)));
                    sb.setInTime(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)));
                    sb.setOutTime(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)));
                    sb.setVisitDate(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    sb.setLatitude(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)));
                    sb.setLongitude(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));
                    sb.setReasonid(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REMARK)));
                    sb.setStatus(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATUS)));
                    sb.setProcess_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setImage_allow(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE_ALLOW)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_IMAGE)));
                    ///changes by jeevannnn
                    sb.setCHECKOUT_IMG(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHECKOUT_IMAGE)));
                    sb.setSub_reasonId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_REASON_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());

        } finally {
            if (dbcursor != null) {
                dbcursor.close();
            }
        }
        return list;
    }


    public ArrayList<GeotaggingBeans> getGeotaggingData(String status) {

        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");

        ArrayList<GeotaggingBeans> geodata = new ArrayList<GeotaggingBeans>();

        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString.TABLE_INSERT_GEO_TAG + "  WHERE STATUS = '"
                    + status + "'", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();

                dbcursor.moveToFirst();
                for (int i = 1; i <= numrows; ++i) {

                    GeotaggingBeans data = new GeotaggingBeans();
                    data.setStoreid(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    data.setLatitude(Double.parseDouble(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_LATITUDE))));
                    data.setLongitude(Double.parseDouble(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_LONGITUDE))));
                    data.setUrl1(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH1)));
                    data.setUrl2(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH2)));
                    data.setUrl3(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE_PATH3)));
                    geodata.add(data);
                    dbcursor.moveToNext();
                }

                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
        } finally {
            if (dbcursor != null && !dbcursor.isClosed()) {
                dbcursor.close();
            }
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return geodata;

    }


    public ArrayList<GeotaggingBeans> getinsertGeotaggingData(String status) {


        ArrayList<GeotaggingBeans> geodata = new ArrayList<GeotaggingBeans>();

        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_STORE_GEOTAGGING + "  WHERE GEO_TAG = '" + status + "'", null);

            if (dbcursor != null) {
                int numrows = dbcursor.getCount();

                dbcursor.moveToFirst();
                for (int i = 1; i <= numrows; ++i) {

                    GeotaggingBeans data = new GeotaggingBeans();

                    data.setStoreid(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")));
                    data.setLatitude(Double.parseDouble(dbcursor.getString(dbcursor.getColumnIndexOrThrow("LATITUDE"))));
                    data.setLongitude(Double.parseDouble(dbcursor.getString(dbcursor.getColumnIndexOrThrow("LONGITUDE"))));
                    data.setUrl1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FRONT_IMAGE")));
                    geodata.add(data);
                    dbcursor.moveToNext();
                }

                dbcursor.close();

            }

        } catch (Exception e) {

        } finally {
            if (dbcursor != null && !dbcursor.isClosed()) {
                dbcursor.close();
            }
        }


        return geodata;

    }


    public void updateGeoTagDataInMain(String storeid) {

        try {

            ContentValues values = new ContentValues();
            values.put("GEO_TAG_STATUS", CommonString.KEY_U);

            int l = db.update(CommonString.TABLE_GEO_TAG_MAPPING, values,
                    CommonString.KEY_STORE_ID + "=?", new String[]{storeid});
            System.out.println("update : " + l);
        } catch (Exception e) {

        }
    }


    public void updateGeoTagData(String storeid, String status) {

        try {

            ContentValues values = new ContentValues();
            values.put("GEO_TAG", status);

            int l = db.update(CommonString.TABLE_STORE_GEOTAGGING, values,
                    CommonString.KEY_STORE_ID + "=?", new String[]{storeid});
            System.out.println("update : " + l);
        } catch (Exception e) {
            Log.d("Database Data ", e.toString());

        }
    }

    public void deleteGeoTagData(String storeid) {

        try {
            db.delete(CommonString.TABLE_STORE_GEOTAGGING, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        } catch (Exception e) {

        }
    }

    public long updateStoreStatusOnCheckout(String storeid, String visitdate, String status, String process_id) {
        long l = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_CHECKOUT_STATUS, status);
            l = db.update("JOURNEY_PLAN", values,
                    CommonString.KEY_STORE_CD + "='" + storeid + "' AND " + CommonString.KEY_CURRENT_DATETIME + "='"
                            + visitdate + "' AND PROCESS_ID ='" + process_id + "'", null);
        } catch (Exception e) {
            Crashlytics.logException(e);
            e.printStackTrace();
        }
        return l;
    }


    public long updateStoreStatusAfterImageUpload(String storeid, String visitdate,
                                                  String status, String process_id) {
        long l = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("UPLOAD_STATUS", status);
            l = db.update("JOURNEY_PLAN", values, CommonString.KEY_STORE_CD + "='" + storeid + "' AND " + CommonString.KEY_CURRENT_DATETIME + "='"
                    + visitdate + "' AND PROCESS_ID = '" + process_id + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }


    public long updateStoreStatusOnLeave(String storeId, String visit_date, String status, String process_id) {
        long l = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("UPLOAD_STATUS", status);
            l = db.update("JOURNEY_PLAN", values, CommonString.KEY_STORE_CD + "='" + storeId + "' AND " + CommonString.KEY_CURRENT_DATETIME + "='" + visit_date + "'  AND PROCESS_ID ='" + process_id + "'", null);
        } catch (Exception e) {
            Crashlytics.logException(e);
            e.printStackTrace();

        }
        return l;
    }


    public long updateStoreStatusOnLeaveOrHoliday(ArrayList<StoreBean> list,
                                                  String visit_date, String status) {
        long l = 0;
        try {
            ContentValues values = new ContentValues();
            for (int i = 0; i < list.size(); i++) {
                values.put("UPLOAD_STATUS", status);
                values.put("CHECKOUT_STATUS", status);
                l = db.update("JOURNEY_PLAN", values, CommonString.KEY_CURRENT_DATETIME + "='" + visit_date + "'", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }


    public int CheckMid(String currdate, String storeid, String process_id) {

        Cursor dbcursor = null;
        int mid = 0;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + "  WHERE " + CommonString.KEY_VISIT_DATE + " = '" + currdate + "' AND " +
                    CommonString.KEY_STORE_ID + " ='" + storeid + "' AND PROCESS_ID ='" + process_id + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                mid = dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ID));
                dbcursor.close();

            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!", e.toString());
        }

        return mid;
    }


    public void updateCoverageStatus(String store_id, String status, String process_id) {
        try {
            ContentValues values = new ContentValues();
            values.put(CommonString.KEY_STATUS, status);
            db.update(CommonString.TABLE_COVERAGE_DATA, values,
                    CommonString.KEY_STORE_ID + "='" + store_id + "' AND PROCESS_ID= '" + process_id + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long updateCoverageCheckoutIMAGE(String store_id, String checkout_img, String process_id) {
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            values.put(CommonString.KEY_CHECKOUT_IMAGE, checkout_img);
            l = db.update(CommonString.TABLE_COVERAGE_DATA, values, CommonString.KEY_STORE_ID + "='" + store_id + "' AND PROCESS_ID= '" + process_id + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public void SaveData(ArrayList<SkuBean> sku_brand_list_second,
                         String category_id, String store_id, String process_id) {

        db.delete(CommonString.TABLE_AFTERSTOCK_OTHER, "STORE_ID"
                + " = '" + store_id + "' AND CAT_ID ='" + category_id + "' AND " + CommonString.KEY_PROCESS_ID + "='"
                + process_id + "'", null);
        ContentValues values = new ContentValues();

        try {

            for (int i = 0; i < sku_brand_list_second.size(); i++) {

                values.put(CommonString.YESNO, sku_brand_list_second.get(i).getYesNo());
                values.put(CommonString.DISPLAY_ID, sku_brand_list_second.get(i).getDisplay_id());
                values.put(CommonString.UID, sku_brand_list_second.get(i).getUID());

                values.put("STORE_ID", store_id);

                values.put(CommonString.CAT_ID, category_id);
                values.put(CommonString.DISPLAY, sku_brand_list_second.get(i).getDisplay());
                values.put("IMAGE_URL", sku_brand_list_second.get(i).getImage_url());
                values.put(CommonString.KEY_PROCESS_ID, process_id);
                values.put(CommonString.KEY_BRAND_ID, sku_brand_list_second.get(i).getBrand_id());
                values.put(CommonString.KEY_BRAND, sku_brand_list_second.get(i).getBrand());
                values.put(CommonString.KEY_IMAGE, sku_brand_list_second.get(i).getBrand_img());
                values.put(CommonString.KEY_EXISTS, sku_brand_list_second.get(i).exist);

                db.insert(CommonString.TABLE_AFTERSTOCK_OTHER, null, values);


            }

        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }

    }


    public long SaveShelfStockData(ArrayList<ShelfVisibilityBean> shelf_list, String process_id, String store_typeid,
                                   String category_id, String store_id) {

        db.delete(CommonString.TABLE_SHELF_VISIBILITY, "STORE_ID"
                + " = '" + store_id + "' AND CAT_ID ='" + category_id + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;

        try {
            for (int i = 0; i < shelf_list.size(); i++) {
                values.put("STORE_ID", store_id);
                values.put(CommonString.CAT_ID, category_id);
                values.put(CommonString.KEY_PROCESS_ID, process_id);
                values.put(CommonString.KEY_STORE_TYPEID, store_typeid);
                values.put(CommonString.KEY_BRAND_ID, shelf_list.get(i).getBrand_id());
                values.put("FACING_TARGET", shelf_list.get(i).getFacing_Target());
                values.put("IMAGE_URL", shelf_list.get(i).getImage());
                values.put("BRAND", shelf_list.get(i).getBrand_name());
                values.put(CommonString.YESNO, shelf_list.get(i).getAnswer());
                l = db.insert(CommonString.TABLE_SHELF_VISIBILITY, null, values);
            }

        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Store Data ",
                    ex.toString());
        }
        return l;
    }


    public ArrayList<SkuBean> getInsertedDisplayListAfterStock(
            String category_id, String store_id, String process_id) {


        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {


            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_AFTERSTOCK_OTHER + " WHERE STORE_ID = " +
                    "'" + store_id + "' AND CAT_ID ='" + category_id + "'" +
                    " AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setDisplay_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID")));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY"))));

                    sb.setYesNo((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("YESNO"))));

                    sb.setUID((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("UID"))));

                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.CAT_ID))));


                    sb.setImage_url((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("IMAGE_URL"))));


                    sb.setBrand((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND"))));


                    sb.setBrand_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("BRAND_ID"))));

                    sb.setExist((dbcursor.getInt(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_EXISTS))));
                    sb.setBrand_img((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE))));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;


    }


    public ArrayList<SkuBean> getInsertedDisplayListAfterStockUpload(
            String store_id, String process_id) {


        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SkuBean> list = new ArrayList<SkuBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_AFTERSTOCK_OTHER + " WHERE STORE_ID = " +
                    "'" + store_id + "' AND PROCESS_ID ='" + process_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SkuBean sb = new SkuBean();

                    sb.setDisplay_id(dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY_ID")));

                    sb.setDisplay((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("DISPLAY"))));

                    sb.setYesNo((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("YESNO"))));

                    sb.setUID((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow("UID"))));

                    sb.setCategory_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.CAT_ID))));


                    sb.setBrand_id((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_BRAND_ID))));

                    sb.setExist((dbcursor.getInt(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_EXISTS))));
                    sb.setBrand_img((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IMAGE))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.getMessage());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;


    }


    public ArrayList<ShelfVisibilityBean> getPdrForSelfData(String process_id,
                                                            String state_Id, String store_type_id, String category_cd, String key_Id) {
        Log.d("FetchingStoredata--------------->Start<------------", "------------------");
        ArrayList<ShelfVisibilityBean> list = new ArrayList<ShelfVisibilityBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT DISTINCT M.BRAND_ID, BR.BRAND,M.FACING_TARGET FROM MAPPING_PDR_FACING_STATEWISE M" +
                    " INNER JOIN BRAND_MASTER BR ON M.BRAND_ID = BR.BRAND_ID" +
                    " WHERE M.STORETYPE_ID =' " + store_type_id + "' AND M.STATE_ID ='" + state_Id + "'" +
                    " AND M.PROCESS_ID = '" + process_id + "' AND BR.CATEGORY_ID='" + category_cd + "' AND M.KEY_ID='" + key_Id + "' order by BR.BRAND", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShelfVisibilityBean sb = new ShelfVisibilityBean();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID")));
                    sb.setBrand_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND")));
                    sb.setFacing_Target(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FACING_TARGET")));
                    sb.setAnswer("NO");
                    sb.setCamera("NO");
                    sb.setImage("");
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();

                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;
    }


    public void deleteQuestionList(
            ArrayList<SkuBean> sku_brand_list_second, String store_id,
            String category_id, String process_id) {

        for (int i = 0; i < sku_brand_list_second.size(); i++) {
            if (!sku_brand_list_second.get(i).getYesNo().equalsIgnoreCase("")) {


                db.delete(CommonString.TABLE_QUESTION_ANSWER_STOCKAFTER, CommonString.DISPLAY_ID
                        + " = '" + sku_brand_list_second.get(i).getDisplay_id() + "' AND " + CommonString.KEY_CATEGORY_ID +
                        " ='" + category_id + "' AND " + CommonString.KEY_STORE_ID + "='" + store_id + "' AND PROCESS_ID ='" + process_id + "'", null);

            }
        }


    }


    public void deleteYESNO(
            ArrayList<SkuBean> sku_brand_list_second, String store_id,
            String category_id, String process_id) {

        for (int i = 0; i < sku_brand_list_second.size(); i++) {
//					if (!sku_brand_list_second.get(i).getYesNo().equalsIgnoreCase("")) {


            db.delete(CommonString.TABLE_AFTERSTOCK_OTHER, CommonString.DISPLAY_ID
                    + " = '" + sku_brand_list_second.get(i).getDisplay_id() + "' AND " + CommonString.CAT_ID +
                    " ='" + category_id + "' AND " + CommonString.KEY_STORE_ID + "='" + store_id + "'" +
                    " AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'", null);

//					}
        }


    }


    public ArrayList<SKUGetterSetter> getcomptitiondataforpromotion(String category_Id) {
        ArrayList<SKUGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery(
                    "SELECT DISTINCT CM.COMPANY_ID,CM.COMPANY FROM COMPANY_MASTER CM INNER JOIN MAPPING_COMPETITION_PROMO MP ON  MP.COMPANY_ID=CM.COMPANY_ID WHERE  MP.CATEGORY_ID='" + category_Id + "' ORDER BY CM.COMPANY", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SKUGetterSetter sb = new SKUGetterSetter();
                    sb.setCompany_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY_ID")));
                    sb.setCompany(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception !!!!!!", e.toString());
            return list;
        }


        return list;
    }


    public void updateStatus(String id, String status, double lat, double longtitude) {

        ContentValues values = new ContentValues();

        try {

            values.put("GEO_TAG_STATUS", status);
            db.update(CommonString.TABLE_GEO_TAG_MAPPING, values,
                    CommonString.KEY_STORE_ID + "='" + id + "'", null);

        } catch (Exception ex) {

        }

    }

    public void updateDataStatus(String id, String status) {
        ContentValues values = new ContentValues();
        try {

            values.put("GEO_TAG_STATUS", status);
            db.update(CommonString.TABLE_GEO_TAG_MAPPING, values,
                    CommonString.KEY_STORE_ID + "='" + id + "'", null);

        } catch (Exception ex) {

        }

    }

    public void InsertMappingPDRFACING(PdrFacingStockGetterSetter data) {
        db.delete("MAPPING_PDR_FACING_STATEWISE", null, null);
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < data.getProcess_id().size(); i++) {
                values.put("FACING_TARGET", data.getFACING_TARGET().get(i));
                values.put("PROCESS_ID", data.getProcess_id().get(i));
                values.put("STATE_ID", data.getSTATE_ID().get(i));
                values.put("KEY_ID", data.getKEY_ID().get(i));
                values.put("STORETYPE_ID", data.getSTORETYPE_ID().get(i));
                values.put("BRAND_ID", data.getBRAND_ID().get(i));
                db.insert("MAPPING_PDR_FACING_STATEWISE", null, values);

            }

        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert SOS Target Data ",
                    ex.toString());
        }

    }

    public long insertmappingCompetitionPromotion(MappingCompetitionPromotionGetterSetter data) {
        db.delete("MAPPING_COMPETITION_PROMO", null, null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            for (int i = 0; i < data.getCOMPANY_ID().size(); i++) {
                values.put("SKU_ID", data.getSKU_ID().get(i));
                values.put("SKU", data.getSKU().get(i));
                values.put("BRAND_ID", data.getBRAND_ID().get(i));
                values.put("BRAND", data.getBRAND().get(i));
                values.put("CATEGORY_ID", data.getCATEGORY_ID().get(i));
                values.put("CATEGORY", data.getCATEGORY().get(i));
                values.put("COMPANY_ID", data.getCOMPANY_ID().get(i));
                values.put("MRP", data.getMRP().get(i));

                values.put("COMP_SEGMENT_ID", data.getSegment_Id().get(i));
                values.put("COMP_SEGMENT", data.getSegment().get(i));
                l = db.insert("MAPPING_COMPETITION_PROMO", null, values);

            }

        } catch (Exception ex) {
            Log.d("Database Exception while Insert SOS Target Data ",
                    ex.toString());
        }
        return l;
    }


    public long insertAttendenceData(String user_id, String visit_date, String status, String image, String reason,
                                     String reason_cd, String entry_allow) {
        db.delete(CommonString.TABLE_INSERT_MERCHANDISER_ATTENDENCE_TABLE, null, null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            values.put(CommonString.KEY_USER_ID, user_id);
            values.put(CommonString.KEY_VISIT_DATE, visit_date);
            values.put(CommonString.KEY_STATUS, status);
            values.put(CommonString.KEY_IMAGE, image);
            values.put(CommonString.KEY_REASON, reason);
            values.put(CommonString.KEY_REASON_ID, reason_cd);
            values.put(CommonString.KEY_ENTRY_ALLOW, entry_allow);
            l = db.insert(CommonString.TABLE_INSERT_MERCHANDISER_ATTENDENCE_TABLE, null, values);

        } catch (Exception e) {
            Crashlytics.logException(e);
            e.printStackTrace();
        }
        return l;
    }


    public ArrayList<SKUGetterSetter> getbrandListByCompanyId(String company_Id, String category_id) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SKUGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT distinct BRAND_ID,BRAND FROM MAPPING_COMPETITION_PROMO WHERE COMPANY_ID='" + company_Id + "' AND CATEGORY_ID = '" + category_id + "' ORDER BY BRAND ", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SKUGetterSetter sb = new SKUGetterSetter();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID")));
                    sb.setBrand((dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND"))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public ArrayList<SKUGetterSetter> getSkuListByBrandId(String brand_Id, String category_id, String comp_segment_Id) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SKUGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select * from MAPPING_COMPETITION_PROMO WHERE BRAND_Id='" + brand_Id + "' AND CATEGORY_ID = '" + category_id + "' AND COMP_SEGMENT_ID='" + comp_segment_Id + "' ORDER BY SKU ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SKUGetterSetter sb = new SKUGetterSetter();
                    sb.setSku_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_ID")));
                    sb.setSku_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU")));
                    sb.setMRP_sku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MRP")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();

                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }

    public ArrayList<SKUGetterSetter> getSegmentListByBrandId(String brand_Id, String category_id) {
        Log.d("FetchingStoredata--------------->Start<------------",
                "------------------");
        ArrayList<SKUGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {


            dbcursor = db.rawQuery(" select distinct MP.COMP_SEGMENT_ID,MP.COMP_SEGMENT,MP.BRAND_ID from MAPPING_COMPETITION_PROMO  MP WHERE MP.BRAND_ID='" + brand_Id + "' AND MP.CATEGORY_ID = '" + category_id + "' ORDER BY MP.COMP_SEGMENT ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SKUGetterSetter sb = new SKUGetterSetter();
                    sb.setComp_segment_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMP_SEGMENT_ID")));
                    sb.setComp_segment(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMP_SEGMENT")));
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();

                return list;
            }

        } catch (Exception e) {
            Crashlytics.logException(e);
            Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
                    e.toString());
            return list;
        }

        Log.d("FetchingStoredat---------------------->Stop<-----------",
                "-------------------");
        return list;

    }


    public long InsertCompetitionPromotionListData(ArrayList<SKUGetterSetter> data) {
        db.delete(CommonString.TABLE_INSERT_COMPETITION_PROMOTION, CommonString.KEY_STORE_ID + " ='" + data.get(0).getStore_Id() +
                "' AND " + CommonString.KEY_PROCESS_ID + " ='" + data.get(0).getProcess_Id() + "' AND " + CommonString.KEY_CATEGORY_ID + " ='" + data.get(0).getCategory_id().get(0) + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;
        // values.put(CommonString.KEY_COMP_TRACT_EXIST, data.get(i).isCompIsExist());
        try {
            for (int i = 0; i < data.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, data.get(i).getStore_Id());
                values.put(CommonString.KEY_CATEGORY_ID, data.get(i).getCategory_id().get(0));
                values.put(CommonString.KEY_PROCESS_ID, data.get(i).getProcess_Id());
                values.put(CommonString.KEY_COMPANY_ID, data.get(i).getCompany_id().get(0));
                values.put(CommonString.KEY_COMPANY, data.get(i).getCompany().get(0));
                values.put(CommonString.KEY_BRAND_ID, data.get(i).getBrand_id().get(0));
                values.put(CommonString.KEY_BRAND, data.get(i).getBrand().get(0));
                values.put(CommonString.KEY_SKU_ID, data.get(i).getSku_id().get(0));
                values.put(CommonString.KEY_SKUNAME, data.get(i).getSku_name().get(0));
                values.put(CommonString.KEY_SKU_MRP, data.get(i).getMRP_sku().get(0));
                values.put(CommonString.KEY_PRICEOFF_TOGGLE, data.get(i).getPriceOFFtoggleValue());
                values.put(CommonString.KEY_PRICEOFF, data.get(i).getPriceOFF_edtRS());
                values.put(CommonString.KEY_PROMOTION, data.get(i).getPromotionpercentValue());
                values.put(CommonString.KEY_IMAGE, data.get(i).getComp_img());

                values.put(CommonString.KEY_COMP_SEGMENT_ID, data.get(i).getComp_segment_Id());
                values.put(CommonString.KEY_COMP_SEGMENT, data.get(i).getComp_segment());

                l = db.insert(CommonString.TABLE_INSERT_COMPETITION_PROMOTION, null, values);
            }


        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Competition Tracking data ", ex.getMessage());
        }
        return l;
    }

    public ArrayList<SKUGetterSetter> getcompetitionPromotionfromDatabase(String store_id, String cate_id, String process_id) {
        Cursor cursordata = null;
        ArrayList<SKUGetterSetter> productData = new ArrayList<>();
        try {
            if (!cate_id.equals("")) {
                cursordata = db.rawQuery("SELECT  * from " + CommonString.TABLE_INSERT_COMPETITION_PROMOTION + " where " + CommonString.KEY_STORE_ID + "='" + store_id + "' AND "
                        + CommonString.KEY_CATEGORY_ID + "='" + cate_id + "' AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'", null);
            } else {
                cursordata = db.rawQuery("SELECT  * from " + CommonString.TABLE_INSERT_COMPETITION_PROMOTION + " where " + CommonString.KEY_STORE_ID + "='" + store_id +
                        "' AND " + CommonString.KEY_PROCESS_ID + "='" + process_id + "'", null);
            }


            if (cursordata != null) {
                cursordata.moveToFirst();
                while (!cursordata.isAfterLast()) {
                    SKUGetterSetter sb = new SKUGetterSetter();
                    sb.setKey_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    sb.setStore_Id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setProcess_Id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_PROCESS_ID)));
                    sb.setCategory_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));

                    sb.setCompany_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_COMPANY_ID)));
                    sb.setCompany(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_COMPANY)));
                    sb.setBrand_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    sb.setBrand(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_BRAND)));
                    sb.setSku_id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
                    sb.setSku_name(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_SKUNAME)));
                    sb.setMRP_sku(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_SKU_MRP)));
                    sb.setPriceOFFtoggleValue(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_PRICEOFF_TOGGLE)));
                    sb.setPromotionpercentValue(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_PROMOTION)));
                    sb.setComp_img(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
                    sb.setPriceOFF_edtRS(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_PRICEOFF)));

                    sb.setComp_segment_Id(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_COMP_SEGMENT_ID)));
                    sb.setComp_segment(cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_COMP_SEGMENT)));


                    /*  String exists = cursordata.getString(cursordata.getColumnIndexOrThrow(CommonString.KEY_COMP_TRACT_EXIST));
                     *//* if (exists.equals("1")) {
                        sb.setCompIsExist(true);
                    } else {
                        sb.setCompIsExist(false);
                    }*/
                    productData.add(sb);
                    cursordata.moveToNext();
                }
                cursordata.close();

            }


        } catch (Exception ex) {
            Crashlytics.logException(ex);
            Log.d("Database Exception while Insert Closes Data ",
                    ex.toString());
        }
        return productData;

    }


    public void deleteCompetitionPromotion(String id) {
        try {
            db.delete(CommonString.TABLE_INSERT_COMPETITION_PROMOTION, "KEY_ID" + "='" + id + "'", null);
        } catch (Exception e) {
            Crashlytics.logException(e);
            System.out.println("" + e);
        }
    }
}
