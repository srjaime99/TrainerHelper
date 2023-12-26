import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Sesion {
    private String deporte;
    private String materiales;
    private int duracionTotal;
    private int numeroParticipantes;

    private List<Ejercicio> listaEjercicios = new LinkedList<>();

    public Sesion(String deporte, String materiales, int duracionTotal, int numeroParticipantes){
        this.deporte = deporte;
        this.materiales = materiales;
        this.duracionTotal = duracionTotal;
        this.numeroParticipantes = numeroParticipantes;
    }

    public List<Ejercicio> crearSesion(int semilla){
        //extraer todos los ejercicios del deporte correspondiente de la lista listaEjercicios y eliminar los que no tienen el numero de personas adecuado
        //utilizando la semilla ir eliminando de los restantes hasta quedarnos con la duracion adecuada (necesitamos metodo para ver la duracion total de la lista)
        return this.listaEjercicios;
    }

    private int duracionListaEjercicios(List<Ejercicio> listaEjercicios){
        int duracionAcumulada = 0;
        //recorrer todos los ejercicios e ir sumando su duracion
        return duracionAcumulada;
    }

    //Getters y setters
    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public int getDuracionTotal() {
        return duracionTotal;
    }

    public void setDuracionTotal(int duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }
}
