package com.example.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by 5thinstall on 7/7/2014.
 */
public class StartPrefActivity extends Activity{

    public SharedPreferences shareMua;
    public SharedPreferences.Editor edtr;
    EditText changeName;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_layout);
        shareMua = getSharedPreferences("derp", 0);
        changeName = (EditText)findViewById(R.id.prefText);
        changeName.setText(shareMua.getString("DefaultValue",""));
    }

    public void CancelP(View R)
    {
        finish();
    }
    public void SaveP(View R)
    {
        edtr = shareMua.edit();
        edtr.putString("DefaultValue",changeName.getText().toString());
        edtr.commit();
        // in mission critical data use .commit() instead of .apply()
        //.apply() saves when main thread is not bus. Sudden phone shutdown/power loss. data is not stored in persistent internal memory
        Toast.makeText(getBaseContext(),shareMua.getString("DefaultValue",""),Toast.LENGTH_LONG).show();
        finish();
    }
}
