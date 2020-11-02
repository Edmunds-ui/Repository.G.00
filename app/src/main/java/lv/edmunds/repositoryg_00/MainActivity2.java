package lv.edmunds.repositoryg_00;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    SharedPreferences sharedTheme;
    String themeName;

    SharedPreferences sharedpreferences;
    TextView name;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set theme
        sharedTheme = getSharedPreferences("Theme", Context.MODE_PRIVATE);
        themeName = sharedTheme.getString("ThemeName", "Default");
        if (themeName.equalsIgnoreCase("DarkTheme")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            setTheme(R.style.DarkTheme);
        } else if (themeName.equalsIgnoreCase("LightTheme")) {
            setTheme(R.style.LightTheme);
        } else  {
            setTheme(R.style.AppTheme);
        }



        setContentView(R.layout.activity_main2);
    }
    public void btnBack(View v) {
        finish();
    }

    public void Get(View view) {
        name = (TextView) findViewById(R.id.textView2);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
    }
}