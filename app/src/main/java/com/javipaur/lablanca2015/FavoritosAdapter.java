package com.javipaur.lablanca2015;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
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
public class FavoritosAdapter extends ArrayAdapter<ProgramaClass> {
    private Activity activity;
    private Context context;
    DBAdapter dbAdapter;
    View v;

    private static LayoutInflater inflater = null;

    public FavoritosAdapter(Context context, List<ProgramaClass> objects, DBAdapter dbAdapter) {
        super(context, 0, objects);
        this.dbAdapter=dbAdapter;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View vi = convertView;
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi =convertView;

        if (convertView == null)
            vi = inflater.inflate(R.layout.celda,parent, false);

        try {

            ImageView tipo= (ImageView) vi.findViewById(R.id.tipo); // tipo
            TextView dia = (TextView) vi.findViewById(R.id.dia); // dia
            TextView hora= (TextView) vi.findViewById(R.id.hora); // hora
            TextView acto= (TextView) vi.findViewById(R.id.acto); // acto
            TextView lugar= (TextView) vi.findViewById(R.id.lugar); // lugar
            ToggleButton favorito= (ToggleButton) vi.findViewById(R.id.favorito); // favorito

            favorito.setTag(position);

            ProgramaClass item = getItem(position);
            tipo.setBackgroundResource(R.drawable.unnamed);
            if(item.getTipo().equals("1")){
                tipo.setBackgroundResource(R.drawable.global);
            }
            if(item.getTipo().equals("2")){
                tipo.setBackgroundResource(R.drawable.fuegos);
            }
            if(item.getTipo().equals("3")){
                tipo.setBackgroundResource(R.drawable.festival);
            }
            if(item.getTipo().equals("4")){
                tipo.setBackgroundResource(R.drawable.infantil);
            }
            hora.setText(item.getHora());
            dia.setText(item.getFecha());
            acto.setText(item.getActo());
            lugar.setText(item.getLugar());

            if(item.getFavorito().equals("1")){
                favorito.setChecked(true);

                favorito.setBackgroundResource( R.drawable.favoritoicon_activo );
            }else{
                favorito.setChecked(false);
                favorito.setBackgroundResource( R.drawable.favoritoicon);
            }
           /* dia.setText("Dia " + (position + 1));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date d = new Date();

            acto.setText(android.text.format.DateFormat.getMediumDateFormat(activity.getApplicationContext()).format(d) + " " +
                            android.text.format.DateFormat.getTimeFormat(activity.getApplicationContext()).format(d)
            );*/



            favorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    //No borrar bajo ningun convepto aqui averiguamos el id del listview a traves del parametro tag();

                    RelativeLayout rl = (RelativeLayout)buttonView.getParent();
                    TextView tv = (TextView)rl.findViewById(R.id.dia);
                    // tv.setText(buttonView.getTag().toString());

                    String idBD=buttonView.getTag().toString();
                    ProgramaClass itemId=getItem(Integer.parseInt(idBD));
                    if (isChecked && itemId.getFavorito().equals("0")) {
                        dbAdapter.updateFavoritos(Integer.toString(itemId.getID()), true);
                        Context context= buttonView.getContext();
                        buttonView.setBackgroundResource(R.drawable.favoritoicon_activo);
                        buttonView.setChecked(true);
                        itemId.setFavorito("1");

                        CharSequence text = "Añadido a favorito !";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                    if (! isChecked && itemId.getFavorito().equals("1")) {
                        dbAdapter.updateFavoritos(Integer.toString(itemId.getID()), false);
                        Context context= buttonView.getContext();
                        buttonView.setBackgroundResource(R.drawable.favoritoicon);
                        buttonView.setChecked(false);
                        itemId.setFavorito("0");

                        CharSequence text = "Borrado de favoritos!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vi;
    }
}
