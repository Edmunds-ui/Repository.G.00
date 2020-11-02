package lv.edmunds.repositoryg_00;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    Spinner themeSpinner;
    SharedPreferences sharedTheme;
    RadioButton defaultTheme, darkTheme, lightTheme;
    RadioGroup group;
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
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            setTheme(R.style.LightTheme);
        } else  {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_main);
        themeSpinner = (Spinner) findViewById(R.id.ThemeSpinner);

        // Class Spinner implementing onItemSelectedListener
        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // Get Selected Class name from the list
                String selectedClass = parent.getItemAtPosition(position).toString();

                switch (selectedClass) {
                    case "Default Theme":
                        defaultTheme.setChecked(true);
                        break;

                    case "Dark Theme":
                        darkTheme.setChecked(true);
                        break;

                    case "Light Theme":
                        lightTheme.setChecked(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // init
        group = (RadioGroup) findViewById(R.id.group);
        defaultTheme = (RadioButton) findViewById(R.id.defaultTheme);
        darkTheme = (RadioButton) findViewById(R.id.darkTheme);
        lightTheme = (RadioButton) findViewById(R.id.lighttheme);


        if (themeName.equalsIgnoreCase("DarkTheme")) {
            darkTheme.setChecked(true);
        } else if (themeName.equalsIgnoreCase("LightTheme")) {
            lightTheme.setChecked(true);
        } else  {
            defaultTheme.setChecked(true);
        }
        // Called when the checked radio button has changed.
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.defaultTheme) {
                    setTheme("Default");
                } else if (checkedId == R.id.darkTheme) {
                    setTheme("DarkTheme");
                } else if (checkedId == R.id.lighttheme) {
                    setTheme("LightTheme");
                }
            }
        });

        name = (TextView) findViewById(R.id.editTextTextPersonName);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
    }

    public void goToAnActivity(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void Save(View view) {
        String n = name.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.commit();
    }

    public void setTheme(String name) {
        // Create preference to store theme name
        SharedPreferences preferences = getSharedPreferences("Theme", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ThemeName", name);
        editor.apply();
        recreate();
    }
}