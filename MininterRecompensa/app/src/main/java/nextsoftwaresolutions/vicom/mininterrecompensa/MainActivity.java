package nextsoftwaresolutions.vicom.mininterrecompensa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Busqueda.BusquedaFragment;
import nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Capturados.CapturadosFragment;
import nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Requisitoriados.RequisitoriadoDetalleFragment;
import nextsoftwaresolutions.vicom.mininterrecompensa.Fragmentos.Requisitoriados.RequisitoriadosFragment;
import nextsoftwaresolutions.vicom.mininterrecompensa.Modelos.Requisitoriado;
/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */
public class MainActivity extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private TextView mTextMessage;
    private int contadorrequisitoriados, contadorcapturados;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_profugos:
                    selectedFragment = RequisitoriadosFragment.newInstance();
                    break;
                case R.id.navigation_capturados:
                    selectedFragment = CapturadosFragment.newInstance();
                    break;
                case R.id.navigation_busquedas:
                    selectedFragment = BusquedaFragment.newInstance();
                    break;

            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, selectedFragment);
            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("news");

        getSupportActionBar().hide();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        if( ((ToDoApplication) getApplication().getApplicationContext()).getRequisitoriado()== null)
        {   //Manually displaying the first fragment - one time only
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, CapturadosFragment.newInstance());
            transaction.commit();
            navigation.getMenu().getItem(1).setChecked(true);
        }
        else
        {
            //Manually displaying the first fragment - one time only
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, RequisitoriadoDetalleFragment.newInstance());
            transaction.commit();
            //Used to select an item programmatically
            navigation.getMenu().getItem(0).setChecked(true);
        }



        ContarRequisitoriadosYCapturados();







    }

    public void ContarRequisitoriadosYCapturados() {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("REQUISITORIADOS");

        // RECUPERO TODOS LOS CHILD DE REQUISITORIADOS
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("REQUISITORIADOS MAMINA", "Value is: " + dataSnapshot);
                Requisitoriado requisitoriado = new Requisitoriado();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    /*dataSnapshot.getChildren() RECUPERAR TODOS LOS HIJOS DEL NODO REQUISITORIADOS*/

                    requisitoriado = postSnapshot.getValue(Requisitoriado.class);

                    if(requisitoriado.getEstadoCaptura()== 0)
                    {//0 = No Capturado
                        contadorrequisitoriados =contadorrequisitoriados + 1;

                    }
                    if(requisitoriado.getEstadoCaptura()== 1)
                    {//1 = Capturado
                        contadorcapturados =contadorcapturados +1;
                    }


                }

                //Used to select an item programmatically
              //  navigation.getMenu().getItem(0).setChecked(true);
                navigation.getMenu().getItem(0).setTitle("Requisitoriado (" + contadorrequisitoriados+")");
                navigation.getMenu().getItem(1).setTitle("Capturados (" + contadorcapturados+")");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("REQUISITORIADOS ERROR", "Failed to read value.", error.toException());
            }
        });

    }

}
