package nextsoftwaresolutions.vicom.mininterrecompensa;

import android.app.Application;
import android.os.SystemClock;

import nextsoftwaresolutions.vicom.mininterrecompensa.Modelos.Requisitoriado;
/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */

public class ToDoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1000);

    }

    private Requisitoriado requisitoriado;

    public Requisitoriado getRequisitoriado() {
        return requisitoriado;
    }

    public void setRequisitoriado(Requisitoriado requisitoriado) {
        this.requisitoriado = requisitoriado;
    }
}
