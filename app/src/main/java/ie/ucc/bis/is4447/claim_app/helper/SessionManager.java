package ie.ucc.bis.is4447.claim_app.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import ie.ucc.bis.is4447.claim_app.view.Dashboard;
import ie.ucc.bis.is4447.claim_app.view.UserLoginActivity;

public class SessionManager {


    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME ="LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String EMAIL = "EMAIL";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String Email){
        editor.putBoolean(LOGIN, true);
        editor.putString(EMAIL, Email);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if (!this.isLoggin()){
            Intent i = new Intent(context, UserLoginActivity.class);
            context.startActivity(i);
            ((Dashboard)context).finish();
        }
    }
    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
                Intent i = new Intent(context, UserLoginActivity.class);
                context.startActivity(i);
        ((Dashboard)context).finish();
    }
}
