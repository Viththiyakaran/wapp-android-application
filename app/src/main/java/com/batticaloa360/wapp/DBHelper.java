package com.batticaloa360.wapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private Context mContext;

    public  static final String DATABASE_NAME = "my-dic.db";
    public static final int DATABASE_VERSION = 1;


    private  String DATABASE_LOCATION = "";
    private  String DATABASE_FULL_PATH = "";
    private final  String TBL_ENG_TA = "eng_ta";
    private final String TBL_TA_ENG = "ta_eng";
    private final String TBL_TA_TA = "ta_ta";
    private final String  TBL_BOOKMARK ="bookmark";


    private final String COL_KEY = "key";
    private final String COL_VALUE = "value";
    public  SQLiteDatabase mDB;




    public  DBHelper(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        mContext = context;
        DATABASE_LOCATION = "data/data/"+ mContext.getPackageName() + "/database/";
        DATABASE_FULL_PATH =  DATABASE_LOCATION  + DATABASE_NAME;



        if(!isExistingDB())
        {
            try
            {
                extractAssetToDatabaseDirectory(DATABASE_NAME);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        mDB = SQLiteDatabase.openOrCreateDatabase(DATABASE_FULL_PATH,null);
    }


    public  void extractAssetToDatabaseDirectory(String filename) throws IOException
    {


        int length;
        InputStream sourceDatabase = this.mContext.getAssets().open(filename);
        File destinationpath = new File(DATABASE_FULL_PATH);
        OutputStream  destination = new FileOutputStream(destinationpath);

        byte[] buffer = new byte[4096];
        while((length = sourceDatabase.read(buffer))> 0)
        {
            destination.write(buffer,0,length);
        }

        sourceDatabase.close();
        destination.flush();
        destination.close();




    }

    boolean isExistingDB()
    {
        File file = new File(DATABASE_FULL_PATH);

        return  file.exists();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public ArrayList<String> getWord(int dicType)
    {
        String tableName = getTablename(dicType);
        String q = "Select * from " + tableName;
        Cursor result = mDB.rawQuery(q,null);
        ArrayList<String> source = new ArrayList<>();

        while(result.moveToNext() )
        {
            source.add(result.getString(result.getColumnIndex(COL_KEY)));
        }

        return source;
    }

    public  void addBookmark(Word word)
    {
        try {
            String q = "Insert into bookmark ([" + COL_KEY + "],[" + COL_VALUE + "]) values (?,?);";
            mDB.execSQL(q, new Object[]{word.key, word.value});
        }
        catch (SQLException ex)
        {

        }

    }

    public  void removeBookmark(Word word)
    {
        try {
            String q = "DELETE from bookmark where (["+COL_KEY+"]) = ? AND ["+ COL_VALUE +"] = ?;";
            mDB.execSQL(q, new Object[]{word.key, word.value});
        }
        catch (SQLException ex)
        {

        }

    }

    public  ArrayList<String> getAllWordFromBookmark(String key)
    {

        String q = "Select * from bookmark order by [date] desc";
        Cursor result = mDB.rawQuery(q,new String[]{key });

       ArrayList<String> source = new ArrayList<>();
        while(result.moveToNext() )
        {
           source.add(result.getString(result.getColumnIndex(COL_KEY)));

        }

        return source;

    }


    public  boolean isWordMark(Word word){

        String q = "select * from bookmark where [key] = ? and [value] = ? ";
        Cursor result = mDB.rawQuery(q,new String[]{word.key,word.value});

        return result.getCount() >  0;

    }
    public  Word getWordFromBookmark(String key){

        String q = "select * from bookmark where [key] = ?";
        Cursor result = mDB.rawQuery(q,null);

        Word word = new Word();
        while(result.moveToNext() )
        {
            word.key = result.getString(result.getColumnIndex(COL_KEY));
            word.value = result.getString(result.getColumnIndex(COL_VALUE));
        }

        return  word;
    }

    public Word getWord(String key,int dicType)
    {
        String tableName = getTablename(dicType);
        String q = "Select * from " + tableName +" Where [key] =?";
        Cursor result = mDB.rawQuery(q,new String[]{key });

        Word word =new Word();
        while(result.moveToNext() )
        {
           word.key = result.getString(result.getColumnIndex(COL_KEY));
           word.value= result.getString(result.getColumnIndex(COL_VALUE));
        }

        return word;

    }

    public  String getTablename(int dicType)
    {
        String tableName ="";
        if(dicType == R.id.action_eng_ta)
        {
            tableName = TBL_ENG_TA;
        }
        else if(dicType ==R.id.action_ta_eng)
        {
            tableName = TBL_TA_ENG;
        }
        else if(dicType ==R.id.action_ta_ta)
        {
            tableName = TBL_TA_TA;
        }

        return tableName;
    }
}
