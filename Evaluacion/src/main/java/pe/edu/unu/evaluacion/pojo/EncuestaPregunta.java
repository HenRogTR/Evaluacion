package pe.edu.unu.evaluacion.pojo;
// Generated 08/02/2016 11:38:29 PM by Hibernate Tools 4.3.1



/**
 * EncuestaPregunta generated by hbm2java
 */
public class EncuestaPregunta  implements java.io.Serializable {


     private Integer idEncuestaPregunta;
     private Encuesta encuesta;

    public EncuestaPregunta() {
    }

    public EncuestaPregunta(Encuesta encuesta) {
       this.encuesta = encuesta;
    }
   
    public Integer getIdEncuestaPregunta() {
        return this.idEncuestaPregunta;
    }
    
    public void setIdEncuestaPregunta(Integer idEncuestaPregunta) {
        this.idEncuestaPregunta = idEncuestaPregunta;
    }
    public Encuesta getEncuesta() {
        return this.encuesta;
    }
    
    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }




}

