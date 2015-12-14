package com.javipaur.lablanca2015;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.javipaur.lablanca2015.db.DBAdapter;

import java.util.List;

/**
 * Created by Javipaur on 21/02/2015.
 */
public class DiasAdapter extends ArrayAdapter<ProgramaClass> {
    private Activity activity;
    private Context context;
    DBAdapter dbAdapter;
    View v;

    private static LayoutInflater inflater = null;

    public DiasAdapter(Context context, List<ProgramaClass> objects, DBAdapter dbAdapter) {
        super(context, 0, objects);
        this.dbAdapter=dbAdapter;
    }

    /**
     * getView
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View vi = convertView;
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi =convertView;

        if (convertView == null)
            vi = inflater.inflate(R.layout.rowdias,parent, false);

        try {

            TextView dia = (TextView) vi.findViewById(R.id.dia); // dia
            ImageView idia=(ImageView) vi.findViewById(R.id.idia);//imagendia

            ProgramaClass item = getItem(position);
            /*
            dia.setBackgroundResource(R.drawable.unnamed);
            if(item.getDia().equals("1")){
                dia.setBackgroundResource(R.drawable.Dia1);
            }
            */
        dia.setText(item.getFecha());
            idia.setBackgroundResource(R.drawable.domingo);
            if(dia.getText().equals("Dia 04")){
            idia.setBackgroundResource(R.drawable.martes4);
            }
            if(dia.getText().equals("Dia 05")){
                idia.setBackgroundResource(R.drawable.miercoles);
            }
            if(dia.getText().equals("Dia 06")){
                idia.setBackgroundResource(R.drawable.jueves);
            }
            if(dia.getText().equals("Dia 07")){
                idia.setBackgroundResource(R.drawable.viernes);
            }
            if(dia.getText().equals("Dia 08")){
                idia.setBackgroundResource(R.drawable.sabado);
            }
            if(dia.getText().equals("Dia 09")){
                idia.setBackgroundResource(R.drawable.domingo);
            }


    } catch (Exception e) {
            e.printStackTrace();
        }

        return vi;
    }
}
