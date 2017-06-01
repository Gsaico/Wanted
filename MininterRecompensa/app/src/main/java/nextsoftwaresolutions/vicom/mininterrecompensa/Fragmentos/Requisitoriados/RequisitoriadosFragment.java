package nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Requisitoriados;


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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

import nextsoftwaresolutions.vicom.mininterrecompensa.Modelos.Requisitoriado;
import nextsoftwaresolutions.vicom.mininterrecompensa.R;
import nextsoftwaresolutions.vicom.mininterrecompensa.ToDoApplication;

/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */
public class RequisitoriadosFragment extends Fragment {

    private ProgressBar progressBarRequisitoriado;
    private RecyclerView rvRequisitoriados;
    private FirebaseRecyclerAdapter<Requisitoriado, RequisitoriadoViewHolderSDK> mirebaserecyclerAdapter;

private int  startAt;
    public static RequisitoriadosFragment newInstance() {
        RequisitoriadosFragment fragment = new RequisitoriadosFragment();
        return fragment;
    }
    public RequisitoriadosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requisitoriados, container, false);




        //show progresbar
        progressBarRequisitoriado = (ProgressBar) rootView.findViewById(R.id.progressBarRequisitoriados);
        rvRequisitoriados = (RecyclerView) rootView.findViewById(R.id.rvRequisitoriados);
        rvRequisitoriados.setLayoutManager(new LinearLayoutManager(getActivity()));

        mirebaserecyclerAdapter = new FirebaseRecyclerAdapter<Requisitoriado, RequisitoriadoViewHolderSDK>(
                Requisitoriado.class,
                R.layout.cardview_requisitoriados,
                RequisitoriadoViewHolderSDK.class,
                FirebaseDatabase.getInstance().getReference().child("REQUISITORIADOS").orderByChild("EstadoCaptura").equalTo(0)
        ) {
            @Override
            protected void populateViewHolder(RequisitoriadoViewHolderSDK requisitoriadoViewHolderSDK, final Requisitoriado requisitoriado, int position) {
                //hide progresbar
                progressBarRequisitoriado.setVisibility(ProgressBar.INVISIBLE);
                    //Cargar los controles con la Informacion
                    Glide
                            .with(getActivity())
                            .load(requisitoriado.getFotoUrl())
                            // .crossFade()
                            // .centerCrop()
                            .placeholder(R.drawable.logomininter)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .thumbnail(0.5f)
                            .into(requisitoriadoViewHolderSDK.ivFotoRequisitoriado);

                //Para usar fuentes


                 Typeface TravelingTypewriter = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TravelingTypewriter.ttf");
                 Typeface VeteranTypewriter = Typeface.createFromAsset(getActivity().getAssets(), "fonts/VeteranTypewriter.ttf");


                //set fuentes controles
                requisitoriadoViewHolderSDK.tvApellidosNombresRequisitoriado.setTypeface( VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvDelitoRequisitoriado.setTypeface( TravelingTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvObservacionesRequisitoriado.setTypeface(TravelingTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvRecompensaRequisitoriado.setTypeface(TravelingTypewriter, Typeface.BOLD);

                requisitoriadoViewHolderSDK.tvDelito.setTypeface( VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvObservaciones.setTypeface( VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvRecompensa.setTypeface( VeteranTypewriter, Typeface.BOLD);

                //setter valores controles

                    requisitoriadoViewHolderSDK.tvApellidosNombresRequisitoriado.setText(requisitoriado.getApellidoPaterno() + " " + requisitoriado.getApellidoMaterno() + " " + requisitoriado.getNombres());
                    requisitoriadoViewHolderSDK.tvDelitoRequisitoriado.setText(requisitoriado.getDescripcionDelito());
                    requisitoriadoViewHolderSDK.tvObservacionesRequisitoriado.setText(requisitoriado.getObservaciones());
                    requisitoriadoViewHolderSDK.tvRecompensaRequisitoriado.setText("S/. " + Integer.toString(requisitoriado.getRecompensa()));
                    requisitoriadoViewHolderSDK.tvRecompensaRequisitoriado.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Log.d("Requisitoriado ", requisitoriado.toString());
                            // Guardo en la variable global el requisitoriado para acceder desde Detalle requisitoriado
                            ToDoApplication app = (ToDoApplication)getActivity().getApplication();
                            app.setRequisitoriado(requisitoriado);

                            // LLama al fragmento mostrando la informacion detallada del requisitoriado
                            RequisitoriadoDetalleFragment fragment2 = new RequisitoriadoDetalleFragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
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
        rvRequisitoriados.setAdapter(mirebaserecyclerAdapter);

        rvRequisitoriados.addOnScrollListener(new  RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                  /*  Toast toast1 =
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Toast por defecto", Toast.LENGTH_SHORT);

                    toast1.show();*/
                }
            }
        });

        return rootView;
    }

    public static class RequisitoriadoViewHolderSDK extends RecyclerView.ViewHolder {
        private ImageView ivFotoRequisitoriado;
        private TextView tvApellidosNombresRequisitoriado;
        private TextView tvDelitoRequisitoriado;
        private TextView tvObservacionesRequisitoriado;
        private TextView tvRecompensaRequisitoriado;
        private TextView tvDelito;
        private TextView tvObservaciones;
        private TextView tvRecompensa;

        public RequisitoriadoViewHolderSDK(View itemView) {
            super(itemView);
            //AQUI LLAMO LOS OBJETOS
            ivFotoRequisitoriado = (ImageView) itemView.findViewById(R.id.ivFotoRequisitoriado);
            tvApellidosNombresRequisitoriado = (TextView) itemView.findViewById(R.id.tvApellidosNombresRequisitoriado);
            tvDelitoRequisitoriado = (TextView) itemView.findViewById(R.id.tvDelitoRequisitoriado);
            tvObservacionesRequisitoriado = (TextView) itemView.findViewById(R.id.tvObservacionesRequisitoriado);
            tvRecompensaRequisitoriado = (TextView) itemView.findViewById(R.id.tvRecompensaRequisitoriado);
            tvDelito = (TextView) itemView.findViewById(R.id.tvDelito);
            tvObservaciones = (TextView) itemView.findViewById(R.id.tvObservaciones);
            tvRecompensa = (TextView) itemView.findViewById(R.id.tvRecompensa);


        }



    }

}
