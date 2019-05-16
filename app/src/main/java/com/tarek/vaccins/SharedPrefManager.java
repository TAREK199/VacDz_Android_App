package com.tarek.vaccins;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context context;
    private static String SHARED_PREF_NAME="mysharedpref";
    private static String KEY_FATHER_ID ="id";
    private static String KEY_FIRST_NAME="firstname";
    private static String KEY_LAST_NAME="lastsname";
    private static String KEY_EMAIL_FATHER ="email";
    private static String KEY_PHONE_NUMBER ="phonenumber";




    private SharedPrefManager (Context context)
    {
        this.context=context;
    }

    public static synchronized SharedPrefManager getmInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean buyerLogin(int id , String email, String username , String firstname , String lastname)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_FATHER_ID,id);
        editor.putString(KEY_FIRST_NAME,firstname);
        editor.putString(KEY_LAST_NAME,lastname);
        editor.putString(KEY_EMAIL_FATHER,email);


        editor.apply();
        return true;
    }

    public boolean sellerLogin(int id)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_FATHER_ID,id);
        editor.apply();
        return true;
    }

    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        if(sharedPreferences.getString(KEY_EMAIL_FATHER,null)!=null)
        {
            return true;
        }
        return false;
    }

    public boolean logOut(){
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


    public int getIdFather()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(KEY_FATHER_ID,0);
    }

    public String getFirstName()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_FIRST_NAME,null);
    }

    public String getLastName()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_LAST_NAME,null);
    }

    public String getEmail()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_EMAIL_FATHER,null);
    }

    public String getPhoneNumber()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_PHONE_NUMBER,null);
    }

}
