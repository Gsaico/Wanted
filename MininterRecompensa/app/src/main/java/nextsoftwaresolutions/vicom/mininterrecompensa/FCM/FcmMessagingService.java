package nextsoftwaresolutions.vicom.mininterrecompensa.FCM;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import nextsoftwaresolutions.vicom.mininterrecompensa.MainActivity;
import nextsoftwaresolutions.vicom.mininterrecompensa.Modelos.Requisitoriado;
import nextsoftwaresolutions.vicom.mininterrecompensa.R;
import nextsoftwaresolutions.vicom.mininterrecompensa.ToDoApplication;


/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */

public class FcmMessagingService extends FirebaseMessagingService {

//Context context;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       if(remoteMessage.getData().size()>0) {
           String dni, img_url;

           dni = remoteMessage.getData().get("dni");
           img_url = remoteMessage.getData().get("img_url");
            //Llamo a activity

           CargarDatosDeRequisitoriado(dni);


           Intent intent = new Intent(this, MainActivity.class);
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
           Uri sounduri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

           final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);


           builder.setContentTitle(dni);
           builder.setContentText(dni);
           builder.setContentIntent(pendingIntent);
           builder.setSound(sounduri);
           builder.setSmallIcon(R.drawable.ic_notification);

           ImageRequest imageRequest = new ImageRequest(img_url, new Response.Listener<Bitmap>() {

               @Override
               public void onResponse(Bitmap response) {
                   builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                   NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
               notificationManager.notify(1,builder.build());
               }
           }, 0, 0, null,Bitmap.Config.RGB_565, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

               }

           });

           MySingleton.getmInstance(this).addToRequestQue(imageRequest);

       }

    }

    public void CargarDatosDeRequisitoriado(String dni) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("REQUISITORIADOS");

        // RECUPERO TODOS LOS CHILD DE REQUISITORIADOS
        myRef.child(dni).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String keyrequisitorido;//Para almacenar el DNI = KEY
                Log.d("REQUISITORIADOS OK", "Value is: " + dataSnapshot);
                Requisitoriado requisitoriado = new Requisitoriado();

                requisitoriado = dataSnapshot.getValue(Requisitoriado.class);
                requisitoriado.setKey(dataSnapshot.getKey());

                // set from ToDOAplication
                ((ToDoApplication) getApplication().getApplicationContext()).setRequisitoriado(requisitoriado);

                Log.d("REQUISITORIADOMAMINA", "Value is: " + requisitoriado.getApellidoPaterno() );
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("REQUISITORIADOS ERROR", "Failed to read value.", error.toException());
            }
        });

    }

}
