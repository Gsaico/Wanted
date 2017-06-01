package nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Requisitoriados;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import nextsoftwaresolutions.vicom.mininterrecompensa.Modelos.Requisitoriado;
import nextsoftwaresolutions.vicom.mininterrecompensa.R;
import nextsoftwaresolutions.vicom.mininterrecompensa.ToDoApplication;

/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */


public class RequisitoriadoDetalleFragment extends Fragment {
        private TextView tvApellidosNombresRequisitoriadoDetalle;
        private TextView tvRecompensaRequisitoriadoDetalle;
        private TextView tvDelitoRequisitoriadoDetalle;
        private TextView tvAliasRequisitoriadoDetalle;
        private TextView tvPesoRequisitoriadoDetalle;
        private TextView tvEstaturaRequisitoriadoDetalle;
        private TextView tvColorOjosRequisitoriadoDetalle;
        private TextView tvColorCabelloRequisitoriadoDetalle;
        private TextView tvCicatricesMarcasRequisitoriadoDetalle;
        private TextView tvComplexionRequisitoriadoDetalle;
        private TextView tvOcupacionesRequisitoriadoDetalle;
        private TextView tvFechaNacimientoRequisitoriadoDetalle;
        private TextView tvNacionalidadRequisitoriadoDetalle;
        private TextView tvLugarNacimientoRequisitoriadoDetalle;
        private TextView tvLugarResidenciaRequisitoriadoDetalle;
        private ImageView ivFotoRequisitoriadoDetalle;


    public static RequisitoriadoDetalleFragment newInstance() {
        RequisitoriadoDetalleFragment fragment = new RequisitoriadoDetalleFragment();
        return fragment;
    }

    public RequisitoriadoDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requisitoriado_detalle, container, false);



        tvApellidosNombresRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvApellidosNombresRequisitoriadoDetalle);
        tvRecompensaRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvRecompensaRequisitoriadoDetalle);
        tvDelitoRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvDelitoRequisitoriadoDetalle);
        tvAliasRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvAliasRequisitoriadoDetalle);
        tvPesoRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvPesoRequisitoriadoDetalle);
        tvEstaturaRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvEstaturaRequisitoriadoDetalle);
        tvColorOjosRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvColorOjosRequisitoriadoDetalle);
        tvColorCabelloRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvColorCabelloRequisitoriadoDetalle);
        tvCicatricesMarcasRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvCicatricesMarcasRequisitoriadoDetalle);
        tvComplexionRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvComplexionRequisitoriadoDetalle);
        tvOcupacionesRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvOcupacionesRequisitoriadoDetalle);
        tvLugarNacimientoRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvLugarNacimientoRequisitoriadoDetalle);
        tvFechaNacimientoRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvFechaNacimientoRequisitoriadoDetalle);
        tvNacionalidadRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvNacionalidadRequisitoriadoDetalle);
        tvLugarResidenciaRequisitoriadoDetalle = (TextView) rootView.findViewById(R.id.tvLugarResidenciaRequisitoriadoDetalle);
        ivFotoRequisitoriadoDetalle = (ImageView) rootView.findViewById(R.id.ivFotoRequisitoriadoDetalle);

        //Para usar fuentes
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TravelingTypewriter.ttf");
        tvApellidosNombresRequisitoriadoDetalle.setTypeface(typeFace);


        // get from ToDOAplication
        Requisitoriado requisitoriado1 = new Requisitoriado();

         requisitoriado1 = ((ToDoApplication)  getActivity().getApplication()).getRequisitoriado();

        tvApellidosNombresRequisitoriadoDetalle.setText(requisitoriado1.getApellidoPaterno() + " " + requisitoriado1.getApellidoMaterno() + " " + requisitoriado1.getNombres());
       tvRecompensaRequisitoriadoDetalle.setText("S/. " + Integer.toString(requisitoriado1.getRecompensa()));

        tvDelitoRequisitoriadoDetalle.setText(requisitoriado1.getDescripcionDelito());
        tvAliasRequisitoriadoDetalle.setText(requisitoriado1.getAlias());
        tvPesoRequisitoriadoDetalle.setText(requisitoriado1.getPeso());
        tvEstaturaRequisitoriadoDetalle.setText(requisitoriado1.getEstatura());
        tvColorOjosRequisitoriadoDetalle.setText(requisitoriado1.getOjos());
        tvColorCabelloRequisitoriadoDetalle.setText(requisitoriado1.getCabello());
        tvCicatricesMarcasRequisitoriadoDetalle.setText(requisitoriado1.getCicatricesMarcas());
        tvComplexionRequisitoriadoDetalle.setText(requisitoriado1.getComplexion());
        tvOcupacionesRequisitoriadoDetalle.setText(requisitoriado1.getOcupacion());
        tvLugarNacimientoRequisitoriadoDetalle.setText(requisitoriado1.getLugarNacimiento());
        tvFechaNacimientoRequisitoriadoDetalle.setText(requisitoriado1.getFechaNacimiento());
        tvNacionalidadRequisitoriadoDetalle.setText(requisitoriado1.getNacionalidad());
        tvLugarResidenciaRequisitoriadoDetalle.setText(requisitoriado1.getLugarResidencia());

        Glide.with(this)
                .load(requisitoriado1.getFotoUrl())
               // .centerCrop()
                .placeholder(R.drawable.logomininter)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
               // .crossFade()
                .into(ivFotoRequisitoriadoDetalle);



    return rootView;
    }

}
