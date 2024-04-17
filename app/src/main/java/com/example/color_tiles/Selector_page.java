package com.example.color_tiles;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.color_tiles.databinding.ActivitySelectorPageBinding;

import java.util.Locale;

public class Selector_page extends AppCompatActivity {

    private ActivitySelectorPageBinding binding;
    TextView dialog_language;
    int lang_selected;
    RelativeLayout show_lan_dialog;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectorPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        res.updateConfiguration(conf, res.getDisplayMetrics());

        dialog_language = (TextView)findViewById(R.id.dialog_language);
        show_lan_dialog = (RelativeLayout)findViewById(R.id.showlangdialog);
        if(LocaleHelper.getLanguage(Selector_page.this).equalsIgnoreCase("en"))
        {
            context = LocaleHelper.setLocale(Selector_page.this,"en");
            resources =context.getResources();
            dialog_language.setText("ENGLISH");
            setTitle(resources.getString(R.string.app_name));
            lang_selected = 0;

        }else if(LocaleHelper.getLanguage(Selector_page.this).equalsIgnoreCase("frm")){
            context = LocaleHelper.setLocale(Selector_page.this,"frm");
            resources =context.getResources();
            dialog_language.setText("FRANCAIS");
            setTitle(resources.getString(R.string.app_name));
            lang_selected =1;
        }else if(LocaleHelper.getLanguage(Selector_page.this).equalsIgnoreCase("ja")){
            context = LocaleHelper.setLocale(Selector_page.this,"ja");
            resources =context.getResources();
            dialog_language.setText("JAPENESE");
            setTitle(resources.getString(R.string.app_name));
            lang_selected =2;
        }
        show_lan_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] Language = {"ENGLISH", "FRANCAIS", "JAPANESE"};
                final int checkItem;
                Log.d("Clicked", "Clicked");
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Selector_page.this);
                dialogBuilder.setTitle("Select a Language")
                        .setSingleChoiceItems(Language, lang_selected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog_language.setText(Language[i]);
                                if (Language[i].equals("ENGLISH")) {
                                    context = LocaleHelper.setLocale(Selector_page.this, "en");
                                    resources = context.getResources();
                                    lang_selected = 0;
                                    setTitle(resources.getString(R.string.app_name));
                                }
                                if (Language[i].equals("FRANCAIS")) {
                                    context = LocaleHelper.setLocale(Selector_page.this, "frm");
                                    resources = context.getResources();
                                    lang_selected = 1;
                                    setTitle(resources.getString(R.string.app_name));

                                }
                                if (Language[i].equals("JAPANESE")) {
                                    context = LocaleHelper.setLocale(Selector_page.this, "ja");
                                    resources = context.getResources();
                                    lang_selected = 2;
                                    setTitle(resources.getString(R.string.app_name));

                                }
                                res.updateConfiguration(conf, res.getDisplayMetrics());
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent refresh = getIntent();
                                finish();
                                startActivity(refresh);

                            }
                        });
                dialogBuilder.create().show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.imageButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this, ActivityGame.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", 4);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.imageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this, ActivityGame.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", 6);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.imageButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this, ActivityGame.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", 8);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        binding.imageButton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this, ScorePage.class);
                startActivity(intent);
            }
        });
        binding.imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selector_page.this,MainActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonToRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Selector_page.this,RulesActivity.class);
                startActivity(intent);
            }
        });
    }
}