package nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Capturados;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
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
public class CapturadosFragment extends Fragment implements SearchView.OnQueryTextListener  {
    private ProgressBar progressBarCapturados;
    private RecyclerView rvCapturados;
    private FirebaseRecyclerAdapter<Requisitoriado, RequisitoriadoViewHolderSDK> mirebaserecyclerAdapterCapturados;

    public static CapturadosFragment newInstance() {
        CapturadosFragment fragment = new CapturadosFragment();
        return fragment;
    }
    public CapturadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_capturados, container, false);
        //show progresbar
        progressBarCapturados = (ProgressBar) rootView.findViewById(R.id.progressBarCapturados);
        rvCapturados = (RecyclerView) rootView.findViewById(R.id.rvCapturados);
        rvCapturados.setLayoutManager(new LinearLayoutManager(getActivity()));

        mirebaserecyclerAdapterCapturados = new FirebaseRecyclerAdapter<Requisitoriado, RequisitoriadoViewHolderSDK>(
                Requisitoriado.class,
                R.layout.cardview_capturados,
                RequisitoriadoViewHolderSDK.class,
                FirebaseDatabase.getInstance().getReference().child("REQUISITORIADOS").orderByChild("EstadoCaptura").equalTo(1)
        ) {
            @Override
            protected void populateViewHolder(RequisitoriadoViewHolderSDK requisitoriadoViewHolderSDK, final Requisitoriado requisitoriado, int position) {
                //hide progresbar
                progressBarCapturados.setVisibility(ProgressBar.INVISIBLE);
                //Cargar los controles con la Informacion
                Glide
                        .with(getActivity())
                        .load(requisitoriado.getFotoUrl())
                        // .crossFade()
                        // .centerCrop()
                        .placeholder(R.drawable.logomininter)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .thumbnail(0.5f)
                        .into(requisitoriadoViewHolderSDK.ivFotoCapturado);
                //Para usar fuentes
                Typeface MonTypewriter = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MonTypewriter.ttf");
                Typeface TravelingTypewriter = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TravelingTypewriter.ttf");
                Typeface VeteranTypewriter = Typeface.createFromAsset(getActivity().getAssets(), "fonts/VeteranTypewriter.ttf");



                //set fuentes controles
                requisitoriadoViewHolderSDK.tvLetraCapturado.setTypeface(MonTypewriter);
                requisitoriadoViewHolderSDK.tvApellidosNombresCapturado.setTypeface( VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvDelitoCapturado.setTypeface( TravelingTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvObservacionesCapturado.setTypeface(TravelingTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvRecompensaCapturado.setTypeface(TravelingTypewriter, Typeface.BOLD);

                requisitoriadoViewHolderSDK.tvDelito.setTypeface( VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvObservaciones.setTypeface( VeteranTypewriter, Typeface.BOLD);
                requisitoriadoViewHolderSDK.tvRecompensa.setTypeface( VeteranTypewriter, Typeface.BOLD);

                //setter valores controles
                requisitoriadoViewHolderSDK.tvApellidosNombresCapturado.setText(requisitoriado.getApellidoPaterno() + " " + requisitoriado.getApellidoMaterno() + " " + requisitoriado.getNombres());
                requisitoriadoViewHolderSDK.tvDelitoCapturado.setText(requisitoriado.getDescripcionDelito());
                requisitoriadoViewHolderSDK.tvObservacionesCapturado.setText(requisitoriado.getObservaciones());
                requisitoriadoViewHolderSDK.tvRecompensaCapturado.setText("S/. " + Integer.toString(requisitoriado.getRecompensa()));
                requisitoriadoViewHolderSDK.tvRecompensaCapturado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("Requisitoriado ", requisitoriado.toString());
                        // Guardo en la variable global el requisitoriado para acceder desde Detalle requisitoriado
                        ToDoApplication app = (ToDoApplication)getActivity().getApplication();
                        app.setRequisitoriado(requisitoriado);

                        // LLama al fragmento mostrando la informacion detallada del requisitoriado
                        CapturadoDetalleFragment fragment2 = new CapturadoDetalleFragment();
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
        rvCapturados.setAdapter(mirebaserecyclerAdapterCapturados);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public static class RequisitoriadoViewHolderSDK extends RecyclerView.ViewHolder {
        private ImageView ivFotoCapturado;
        private TextView tvApellidosNombresCapturado;
        private TextView tvDelitoCapturado;
        private TextView tvObservacionesCapturado;
        private TextView tvRecompensaCapturado;
        private TextView tvLetraCapturado;

        private TextView tvDelito;
        private TextView tvObservaciones;
        private TextView tvRecompensa;

        public RequisitoriadoViewHolderSDK(View itemView) {
            super(itemView);
            //AQUI LLAMO LOS OBJETOS
            ivFotoCapturado = (ImageView) itemView.findViewById(R.id.ivFotoCapturado);
            tvApellidosNombresCapturado = (TextView) itemView.findViewById(R.id.tvApellidosNombresCapturado);
            tvDelitoCapturado = (TextView) itemView.findViewById(R.id.tvDelitoCapturado);
            tvObservacionesCapturado = (TextView) itemView.findViewById(R.id.tvObservacionesCapturado);
            tvRecompensaCapturado = (TextView) itemView.findViewById(R.id.tvRecompensaCapturado);
            tvLetraCapturado = (TextView) itemView.findViewById(R.id.tvLetraCapturado);

            tvDelito = (TextView) itemView.findViewById(R.id.tvDelito);
            tvObservaciones = (TextView) itemView.findViewById(R.id.tvObservaciones);
            tvRecompensa= (TextView) itemView.findViewById(R.id.tvRecompensa);
        }



    }
}
