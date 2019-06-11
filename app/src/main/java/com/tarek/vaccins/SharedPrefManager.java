package com.tarek.vaccins;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context context;
    private static String SHARED_PREF_NAME="mysharedpref";
    private static String KEY_FATHER_ID ="id";
    private static String KEY_USER_ID ="user_id";
    private static String KEY_FIRST_NAME="firstname";
    private static String KEY_LAST_NAME="lastsname";
    private static String KEY_EMAIL_FATHER ="email";
    private static String KEY_PHONE_NUMBER ="phonenumber";
    private static String KEY_TOKEN = "token";
    private static String KEY_FIREBASE_TOKEN = "firebase_token";
    private static String KEY_CHILD_ID ="child_id";





    public SharedPrefManager (Context context)
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


    public boolean  fatherRegister(int id ,int userId,String email, String token)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_FATHER_ID,id);
        editor.putInt(KEY_USER_ID,userId);
        editor.putString(KEY_EMAIL_FATHER,email);
        editor.putString(KEY_TOKEN,token);

        editor.apply();
        return true;
    }

    public boolean  storeFireBaseToken(String token)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FIREBASE_TOKEN,token);

        editor.apply();
        return true;
    }



    public boolean  fatherLogin(int id ,int userId,String email,String token)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_FATHER_ID,id);
        editor.putInt(KEY_USER_ID,userId);
        editor.putString(KEY_EMAIL_FATHER,email);
        editor.putString(KEY_TOKEN,token);


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


        public int getUserId()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(KEY_USER_ID,0);
    }

    public String getEmail()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_EMAIL_FATHER,null);
    }

    public String getToken()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_TOKEN,null);
    }

    public String getFireBaseToken()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_FIREBASE_TOKEN,null);
    }


    // for children
    public int getIdChild()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(KEY_CHILD_ID,0);
    }

    public boolean  childAccess(int id)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_CHILD_ID,id);


        editor.apply();
        return true;
    }



}
