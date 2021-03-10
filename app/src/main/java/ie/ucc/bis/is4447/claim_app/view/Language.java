package ie.ucc.bis.is4447.claim_app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.LocaleUtils;

public class Language extends AppCompatActivity {
    int flag = 0;

    @BindView(R.id.button_change_locale)
    Button mChangeLocaleButton;
    private int mLanguageIndex = 0;


    @OnClick(R.id.button_change_locale)

    void changeLocale() {
        if (++mLanguageIndex >= LocaleUtils.LocaleDef.SUPPORTED_LOCALES.length) {
            mLanguageIndex = 0;
        }
        ImageView imageView = findViewById(R.id.ivFlag);


        if (flag == 0) {
            imageView.setImageResource(R.drawable.french_flag);
            flag = 1;
        } else if (flag == 1) {
            imageView.setImageResource(R.drawable.spanish_flag);
            flag = 2;
        } else if (flag == 2) {
            imageView.setImageResource(R.drawable.irish_flag);
            flag = 3;
        }  else if (flag == 3) {
            imageView.setImageResource(R.drawable.english_flag);
            flag = 0;
        }



        LocaleUtils.setLocale(this, mLanguageIndex);
        setupUi();
    }

    @BindView(R.id.button_load_activity)
    Button mLoadActivityButton;

    @OnClick(R.id.button_load_activity)
    void loadActivity() {
        startActivity(new Intent(this, Dashboard.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        ButterKnife.bind(this);

        LocaleUtils.initialize(this, LocaleUtils.ENGLISH);
        ImageView imageView = findViewById(R.id.ivFlag);
        imageView.setImageResource(R.drawable.english_flag);

        setupUi();
    }
    private void setupUi() {

        mChangeLocaleButton.setText(R.string.current_language);
        mLoadActivityButton.setText(R.string.openin);

    }
}