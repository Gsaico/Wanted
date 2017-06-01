package nextsoftwaresolutions.vicom.mininterrecompensa.Modelos;

/**
 * Created by Percy Saico Ccapa on 7/05/2017.
 */
public class Requisitoriado {
    //pojo

    private String key;//Identifica al N" DNI
    private String Nombres;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private int EstadoCaptura;// 0 o 1
    private String DescripcionDelito;
    private String FotoUrl;
    private String Observaciones;
    private int Recompensa;
    //agregador
    private String Alias;
    private String Peso;
    private String Estatura;
    private String Ojos;
    private String Cabello;
    private String CicatricesMarcas;
    private String Complexion;
    private String Ocupacion;
    private String LugarNacimiento;
    private String FechaNacimiento;
    private String Nacionalidad;
    private String LugarResidencia;



    public Requisitoriado() {
      //constructor vacio necesario para Firebase
    }

    public Requisitoriado(String key, String nombres, String apellidoPaterno, String apellidoMaterno, int estadoCaptura, String descripcionDelito, String fotoUrl, String observaciones, int recompensa, String alias, String peso, String estatura, String ojos, String cabello, String cicatricesMarcas, String complexion, String ocupacion, String lugarNacimiento, String fechaNacimiento, String nacionalidad, String lugarResidencia) {
        this.key = key;

        EstadoCaptura = estadoCaptura;
        DescripcionDelito = descripcionDelito;
        FotoUrl = fotoUrl;
        Observaciones = observaciones;
        Recompensa = recompensa;
        Alias = alias;
        Peso = peso;
        Estatura = estatura;
        Ojos = ojos;
        Cabello = cabello;
        CicatricesMarcas = cicatricesMarcas;
        Complexion = complexion;
        Ocupacion = ocupacion;
        LugarNacimiento = lugarNacimiento;
        FechaNacimiento = fechaNacimiento;
        Nacionalidad = nacionalidad;
        LugarResidencia = lugarResidencia;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getEstatura() {
        return Estatura;
    }

    public void setEstatura(String estatura) {
        Estatura = estatura;
    }

    public String getOjos() {
        return Ojos;
    }

    public void setOjos(String ojos) {
        Ojos = ojos;
    }

    public String getCabello() {
        return Cabello;
    }

    public void setCabello(String cabello) {
        Cabello = cabello;
    }

    public String getCicatricesMarcas() {
        return CicatricesMarcas;
    }

    public void setCicatricesMarcas(String cicatricesMarcas) {
        CicatricesMarcas = cicatricesMarcas;
    }

    public String getComplexion() {
        return Complexion;
    }

    public void setComplexion(String complexion) {
        Complexion = complexion;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
    }

    public String getLugarNacimiento() {
        return LugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        LugarNacimiento = lugarNacimiento;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public String getLugarResidencia() {
        return LugarResidencia;
    }

    public void setLugarResidencia(String lugarResidencia) {
        LugarResidencia = lugarResidencia;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public int getEstadoCaptura() {
        return EstadoCaptura;
    }

    public void setEstadoCaptura(int estadoCaptura) {
        EstadoCaptura = estadoCaptura;
    }

    public String getDescripcionDelito() {
        return DescripcionDelito;
    }

    public void setDescripcionDelito(String descripcionDelito) {
        DescripcionDelito = descripcionDelito;
    }

    public String getFotoUrl() {
        return FotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        FotoUrl = fotoUrl;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public int getRecompensa() {
        return Recompensa;
    }

    public void setRecompensa(int recompensa) {
        Recompensa = recompensa;
    }
}
