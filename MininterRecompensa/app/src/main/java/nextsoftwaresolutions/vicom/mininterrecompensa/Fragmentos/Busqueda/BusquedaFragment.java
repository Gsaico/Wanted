package nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Busqueda;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import nextsoftwaresolutions.vicom.mininterrecompensa.R;

/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */
public class BusquedaFragment extends Fragment implements View.OnClickListener   {


    private Button btnBuscarPorDni;
    private Button btnBuscarPorNombre;
    private Button btnBuscarPorApellidoPaterno;
    private Button btnBuscarPorApellidoMaterno;

    private EditText tvDNIBusqueda;
    private EditText tvNombresBusqueda;
    private EditText tvApellidoPaternoBusqueda;
    private EditText tvApellidoMaternoBusqueda;

    public static BusquedaFragment newInstance() {
        BusquedaFragment fragment = new BusquedaFragment();
        return fragment;
    }
    public BusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_busqueda, container, false);
        btnBuscarPorDni = (Button) rootView.findViewById(R.id.btnBuscarPorDni);
        btnBuscarPorNombre = (Button) rootView.findViewById(R.id.btnBuscarPorNombre);
        btnBuscarPorApellidoPaterno = (Button) rootView.findViewById(R.id.btnBuscarPorApellidoPaterno);
        btnBuscarPorApellidoMaterno = (Button) rootView.findViewById(R.id.btnBuscarPorApellidoMaterno);


        tvDNIBusqueda = (EditText) rootView.findViewById(R.id.tvDNIBusqueda);
        tvNombresBusqueda = (EditText) rootView.findViewById(R.id.tvNombresBusqueda);
        tvApellidoPaternoBusqueda = (EditText) rootView.findViewById(R.id.tvApellidoPaternoBusqueda);
        tvApellidoMaternoBusqueda = (EditText) rootView.findViewById(R.id.tvApellidoMaternoBusqueda);

        btnBuscarPorDni.setOnClickListener(this);
        btnBuscarPorNombre.setOnClickListener(this);
        btnBuscarPorApellidoPaterno.setOnClickListener(this);
        btnBuscarPorApellidoMaterno.setOnClickListener(this);



        return rootView;
    }

    @Override
    public void onClick(View v) {
        Fragment fragmentox = new BusquedaResultadosFragment();
        Bundle args = new Bundle();
        switch(v.getId()) {
            case R.id.btnBuscarPorDni:
                // Opcion DNI
                args.putString("opcion", "dni");
                args.putString("parametro",tvDNIBusqueda.getText().toString());
                fragmentox.setArguments(args);
                break;
            case R.id.btnBuscarPorNombre:
                // Opcion Nombre
                args.putString("opcion", "nombres");
                args.putString("parametro",tvNombresBusqueda.getText().toString());
                fragmentox.setArguments(args);
                break;
            case R.id.btnBuscarPorApellidoPaterno:
                // Opcion Apellido Paterno
                args.putString("opcion", "apellidopaterno");
                args.putString("parametro",tvApellidoPaternoBusqueda.getText().toString());
                fragmentox.setArguments(args);
                break;
            case R.id.btnBuscarPorApellidoMaterno:
                // Opcion Nombre
                args.putString("opcion", "apellidomaterno");
                args.putString("parametro",tvApellidoMaternoBusqueda.getText().toString());
                fragmentox.setArguments(args);
                break;

        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragmentox);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuscarPorDni:

                // LLama al fragmento mostrando la informacion detallada del requisitoriado
                CapturadoDetalleFragment fragment2 = new CapturadoDetalleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();




                break;
           default:

                break;
        }
    }*/
}
