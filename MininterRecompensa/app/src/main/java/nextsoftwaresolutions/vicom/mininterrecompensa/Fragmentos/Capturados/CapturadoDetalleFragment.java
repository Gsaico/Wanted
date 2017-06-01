package nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Capturados;


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
public class CapturadoDetalleFragment extends Fragment {
    private TextView tvApellidosNombresCapturadoDetalle;
    private TextView tvRecompensaCapturadoDetalle;
    private TextView tvDelitoCapturadoDetalle;
    private TextView tvAliasCapturadoDetalle;
    private TextView tvPesoCapturadoDetalle;
    private TextView tvEstaturaCapturadoDetalle;
    private TextView tvColorOjosCapturadoDetalle;
    private TextView tvColorCabelloCapturadoDetalle;
    private TextView tvCicatricesMarcasCapturadoDetalle;
    private TextView tvComplexionCapturadoDetalle;
    private TextView tvOcupacionesCapturadoDetalle;
    private TextView tvFechaNacimientoCapturadoDetalle;
    private TextView tvNacionalidadCapturadoDetalle;
    private TextView tvLugarNacimientoCapturadoDetalle;
    private TextView tvLugarResidenciaCapturadoDetalle;
    private ImageView ivFotoCapturadoDetalle;


    public static CapturadoDetalleFragment newInstance() {
        CapturadoDetalleFragment fragment = new CapturadoDetalleFragment();
        return fragment;
    }


    public CapturadoDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_capturado_detalle, container, false);


        tvApellidosNombresCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvApellidosNombresCapturadoDetalle);
        tvRecompensaCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvRecompensaCapturadoDetalle);
        tvDelitoCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvDelitoCapturadoDetalle);
        tvAliasCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvAliasCapturadoDetalle);
        tvPesoCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvPesoCapturadoDetalle);
        tvEstaturaCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvEstaturaCapturadoDetalle);
        tvColorOjosCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvColorOjosCapturadoDetalle);
        tvColorCabelloCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvColorCabelloCapturadoDetalle);
        tvCicatricesMarcasCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvCicatricesMarcasCapturadoDetalle);
        tvComplexionCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvComplexionCapturadoDetalle);
        tvOcupacionesCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvOcupacionesCapturadoDetalle);
        tvLugarNacimientoCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvLugarNacimientoCapturadoDetalle);
        tvFechaNacimientoCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvFechaNacimientoCapturadoDetalle);
        tvNacionalidadCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvNacionalidadCapturadoDetalle);
        tvLugarResidenciaCapturadoDetalle = (TextView) rootView.findViewById(R.id.tvLugarResidenciaCapturadoDetalle);
        ivFotoCapturadoDetalle = (ImageView) rootView.findViewById(R.id.ivFotoCapturadoDetalle);

        // get from ToDOAplication
        Requisitoriado requisitoriado = new Requisitoriado();

        requisitoriado = ((ToDoApplication)  getActivity().getApplication()).getRequisitoriado();

        tvApellidosNombresCapturadoDetalle.setText(requisitoriado.getApellidoPaterno() + " " + requisitoriado.getApellidoMaterno() + " " + requisitoriado.getNombres());
        tvRecompensaCapturadoDetalle.setText("S/. " + Integer.toString(requisitoriado.getRecompensa()));

        tvDelitoCapturadoDetalle.setText(requisitoriado.getDescripcionDelito());
        tvAliasCapturadoDetalle.setText(requisitoriado.getAlias());
        tvPesoCapturadoDetalle.setText(requisitoriado.getPeso());
        tvEstaturaCapturadoDetalle.setText(requisitoriado.getEstatura());
        tvColorOjosCapturadoDetalle.setText(requisitoriado.getOjos());
        tvColorCabelloCapturadoDetalle.setText(requisitoriado.getCabello());
        tvCicatricesMarcasCapturadoDetalle.setText(requisitoriado.getCicatricesMarcas());
        tvComplexionCapturadoDetalle.setText(requisitoriado.getComplexion());
        tvOcupacionesCapturadoDetalle.setText(requisitoriado.getOcupacion());
        tvLugarNacimientoCapturadoDetalle.setText(requisitoriado.getLugarNacimiento());
        tvFechaNacimientoCapturadoDetalle.setText(requisitoriado.getFechaNacimiento());
        tvNacionalidadCapturadoDetalle.setText(requisitoriado.getNacionalidad());
        tvLugarResidenciaCapturadoDetalle.setText(requisitoriado.getLugarResidencia());

        Glide.with(this)
                .load(requisitoriado.getFotoUrl())
                // .centerCrop()
                .placeholder(R.drawable.logomininter)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                // .crossFade()
                .into(ivFotoCapturadoDetalle);



        return rootView;
    }

}

