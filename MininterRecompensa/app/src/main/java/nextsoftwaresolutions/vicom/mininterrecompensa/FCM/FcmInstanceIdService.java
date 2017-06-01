package nextsoftwaresolutions.vicom.mininterrecompensa.FCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh(){
        String fcm_token = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM_TOKEN",fcm_token);
    }


}
