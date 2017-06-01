package nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Busqueda;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Capturados.CapturadoDetalleFragment;
import nextsoftwaresolutions.vicom.mininterrecompensa.Modelos.Requisitoriado;
import nextsoftwaresolutions.vicom.mininterrecompensa.R;
import nextsoftwaresolutions.vicom.mininterrecompensa.ToDoApplication;

/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */
public class BusquedaResultadosFragment extends Fragment {

    private ProgressBar progressBarBusquedaResultados;
    private RecyclerView rvCapturados;
    private FirebaseRecyclerAdapter<Requisitoriado, RequisitoriadoBRViewHolderSDK> mirebaserecyclerAdapterCapturados;




    public static BusquedaResultadosFragment newInstance() {

        BusquedaResultadosFragment fragment = new BusquedaResultadosFragment();
        return fragment;
    }



    public BusquedaResultadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_busqueda_resultados, container, false);


        Bundle bundle = getArguments();
        String opcion = bundle.getString("opcion");
        String parametro = bundle.getString("parametro");
        Toast toast1 =  Toast.makeText(getActivity().getApplicationContext(), opcion + parametro, Toast.LENGTH_SHORT);
        toast1.show();

        Query queryfirebase = null;



        switch(opcion) {
            case "dni":
                // Opcion DNI                                                       revizar la consulta no funciona
                queryfirebase =  FirebaseDatabase.getInstance().getReference().child("REQUISITORIADOS").child(parametro);
                break;
            case "nombres":
                // Opcion Nombres                                                   revizar la consulta no funciona
                queryfirebase =  FirebaseDatabase.getInstance().getReference().child("REQUISITORIADOS").orderByChild("Nombres").equalTo(parametro);
                break;
            case "apellidopaterno":
                // Opcion paterno
                queryfirebase =  FirebaseDatabase.getInstance().getReference().child("REQUISITORIADOS").orderByChild("ApellidoPaterno").equalTo(parametro);
                break;
            case "apellidomaterno":
                // Opcion materno
                queryfirebase =  FirebaseDatabase.getInstance().getReference().child("REQUISITORIADOS").orderByChild("ApellidoMaterno").equalTo(parametro);
                break;

        }




        //show progresbar
        progressBarBusquedaResultados = (ProgressBar) rootView.findViewById(R.id.progressBarBusquedaResultados);
        rvCapturados = (RecyclerView) rootView.findViewById(R.id.rvBusquedaResultados);
        rvCapturados.setLayoutManager(new LinearLayoutManager(getActivity()));

        mirebaserecyclerAdapterCapturados = new FirebaseRecyclerAdapter<Requisitoriado, RequisitoriadoBRViewHolderSDK>(
                Requisitoriado.class,
                R.layout.cardview_busqueda_resultados,
                RequisitoriadoBRViewHolderSDK.class,
                queryfirebase
        ) {
            @Override
            protected void populateViewHolder(RequisitoriadoBRViewHolderSDK requisitoriadoViewHolderSDK, final Requisitoriado requisitoriado, int position) {
                //hide progresbar
                progressBarBusquedaResultados.setVisibility(ProgressBar.INVISIBLE);
                //Cargar los controles con la Informacion
                Glide
                        .with(getActivity())
                        .load(requisitoriado.getFotoUrl())
                        // .crossFade()
                        // .centerCrop()
                        .placeholder(R.drawable.logomininter)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .thumbnail(0.5f)
                        .into(requisitoriadoViewHolderSDK.ivFotoBR);
                //Para usar fuentes
                Typeface MonTypewriter = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MonTypewriter.ttf");
                Typeface TravelingTypewriter = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TravelingTypewriter.ttf");
                Typeface VeteranTypewriter = Typeface.createFromAsset(getActivity().getAssets(), "fonts/VeteranTypewriter.ttf");


                //set fuentes controles
                requisitoriadoViewHolderSDK.tvLetraBR.setTypeface(MonTypewriter);
                requisitoriadoViewHolderSDK.tvApellidosNombresBR.setTypeface(VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvDelitoBR.setTypeface(TravelingTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvObservacionesBR.setTypeface(TravelingTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvRecompensaBR.setTypeface(TravelingTypewriter, Typeface.BOLD);

                requisitoriadoViewHolderSDK.tvDelitoLetraBR.setTypeface(VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvObservacionesLetraBR.setTypeface(VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvRecompensaLetraBR.setTypeface(VeteranTypewriter, Typeface.BOLD);

                //setter valores controles
                requisitoriadoViewHolderSDK.tvApellidosNombresBR.setText(requisitoriado.getApellidoPaterno() + " " + requisitoriado.getApellidoMaterno() + " " + requisitoriado.getNombres());
                requisitoriadoViewHolderSDK.tvDelitoBR.setText(requisitoriado.getDescripcionDelito());
                requisitoriadoViewHolderSDK.tvObservacionesBR.setText(requisitoriado.getObservaciones());
                requisitoriadoViewHolderSDK.tvRecompensaBR.setText("S/. " + Integer.toString(requisitoriado.getRecompensa()));
                requisitoriadoViewHolderSDK.tvRecompensaBR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("Requisitoriado ", requisitoriado.toString());
                        // Guardo en la variable global el requisitoriado para acceder desde Detalle requisitoriado
                        ToDoApplication app = (ToDoApplication) getActivity().getApplication();
                        app.setRequisitoriado(requisitoriado);

                        // LLama al fragmento mostrando la informacion detallada del requisitoriado
                        CapturadoDetalleFragment fragment2 = new CapturadoDetalleFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content, fragment2);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
/*
                            Intent myIntent = new Intent(getActivity(), NewActivity.class);
                            getActivity().startActivity(myIntent);
*/

                    }
                });
            }
        };
        rvCapturados.setAdapter(mirebaserecyclerAdapterCapturados);

        return rootView;


    }

    public static class RequisitoriadoBRViewHolderSDK extends RecyclerView.ViewHolder {
        private ImageView ivFotoBR;
        private TextView tvApellidosNombresBR;
        private TextView tvDelitoBR;
        private TextView tvObservacionesBR;
        private TextView tvRecompensaBR;
        private TextView tvLetraBR;

        private TextView tvDelitoLetraBR;
        private TextView tvObservacionesLetraBR;
        private TextView tvRecompensaLetraBR;

        public RequisitoriadoBRViewHolderSDK(View itemView) {
            super(itemView);
            //AQUI LLAMO LOS OBJETOS
            ivFotoBR = (ImageView) itemView.findViewById(R.id.ivFotoBR);
            tvApellidosNombresBR = (TextView) itemView.findViewById(R.id.tvApellidosNombresBR);
            tvDelitoBR = (TextView) itemView.findViewById(R.id.tvDelitoBR);
            tvObservacionesBR = (TextView) itemView.findViewById(R.id.tvObservacionesBR);
            tvRecompensaBR = (TextView) itemView.findViewById(R.id.tvRecompensaBR);
            tvLetraBR = (TextView) itemView.findViewById(R.id.tvLetraBR);

            tvDelitoLetraBR = (TextView) itemView.findViewById(R.id.tvDelitoLetraBR);
            tvObservacionesLetraBR = (TextView) itemView.findViewById(R.id.tvObservacionesLetraBR);
            tvRecompensaLetraBR = (TextView) itemView.findViewById(R.id.tvRecompensaLetraBR);
        }


    }

}
