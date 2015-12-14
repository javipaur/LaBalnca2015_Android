package com.javipaur.lablanca2015;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Javipaur on 01/03/2015.
 */
public class informacion extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.infromacion);
    }
}
