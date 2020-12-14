package com.example.crudexampletennissqlite;

import android.content.Context;

import androidx.annotation.Nullable;

public class TennisDao extends GenericDAO {

    public static final String TENNIS_TABLE = "TENNIS_TABLE";
    public static final String COLUMN_TENNIS_ID = "TENNIS_TABLE_ID";
    public static final String COLUMN_TENNIS_NAME = "TENNIS_TABLE_NAME";
    public static final String COLUMN_TENNIS_PRICE = "TENNIS_TABLE_PRICE";

    public TennisDao(@Nullable Context context) {
        super(context, TENNIS_TABLE);
        String createTableStatement = "CREATE TABLE " + TENNIS_TABLE + " (" + COLUMN_TENNIS_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TENNIS_NAME + " TEXT, " + COLUMN_TENNIS_PRICE + " REAL)";
        GenericDAO genericDAO = GenericDAO.getInstance(context, "ANDROID_DB", createTableStatement, TENNIS_TABLE);
    }

//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String createTableStatement = "CREATE TABLE " + TENNIS_TABLE + " (" + COLUMN_TENNIS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_TENNIS_NAME + " TEXT, " + COLUMN_TENNIS_PRICE + " REAL)";
//
//        sqLiteDatabase.execSQL(createTableStatement);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE " + TENNIS_TABLE);
//        onCreate(sqLiteDatabase);
//    }

//    public List<TennisModel> getAll() {
//        List<TennisModel> allTennis = new ArrayList<>();
//
//        String query = "SELECT * FROM " + TENNIS_TABLE;
//
//        Cursor cursor = db.query(TENNIS_TABLE, null, null, null, null, null, null);
//
//        while(cursor.moveToNext()) {
//            int tennisId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TENNIS_ID));
//            String tennisName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TENNIS_NAME));
//            double tennisPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TENNIS_PRICE));
//
//            TennisModel tennisModel = new TennisModel();
//            tennisModel.setTennisName(tennisName);
//            tennisModel.setTennisId(tennisId);
//            tennisModel.setTennisPrice(tennisPrice);
//            allTennis.add(tennisModel);
//        }
//
//        cursor.close();
//        return allTennis;
//    }
//
//    public boolean insert(TennisModel tennisModel) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_TENNIS_NAME, tennisModel.getTennisName());
//        cv.put(COLUMN_TENNIS_PRICE, tennisModel.getTennisPrice());
//
//        long insert = db.insert(TENNIS_TABLE, null, cv);
//        db.close();
//
//        return insert != -1;
//    }
//
//    public boolean update(TennisModel tennisModel) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_TENNIS_NAME, tennisModel.getTennisName());
//        cv.put(COLUMN_TENNIS_PRICE, tennisModel.getTennisPrice());
//
//        String selection = COLUMN_TENNIS_ID + " = ?";
//        String[] selectionArgs = { tennisModel.getTennisId() + "" };
//
//        int update = db.update(TENNIS_TABLE, cv, selection, selectionArgs);
//
//        return update != -1;
//    }
//
//    public boolean delete(TennisModel tennisModel) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String selection = COLUMN_TENNIS_ID + " = ?";
//        String[] selectionArgs = {tennisModel.getTennisId() + ""};
//
//        long delete = db.delete(TENNIS_TABLE, selection, selectionArgs);
//        db.close();
//
//        return delete != -1;
//    }
}
