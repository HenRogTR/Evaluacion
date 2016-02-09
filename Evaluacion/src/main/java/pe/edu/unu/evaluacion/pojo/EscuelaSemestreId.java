package pe.edu.unu.evaluacion.pojo;
// Generated 08/02/2016 11:38:29 PM by Hibernate Tools 4.3.1



/**
 * EscuelaSemestreId generated by hbm2java
 */
public class EscuelaSemestreId  implements java.io.Serializable {


     private int escuelaIdEscuela;
     private int semestreIdSemestre;

    public EscuelaSemestreId() {
    }

    public EscuelaSemestreId(int escuelaIdEscuela, int semestreIdSemestre) {
       this.escuelaIdEscuela = escuelaIdEscuela;
       this.semestreIdSemestre = semestreIdSemestre;
    }
   
    public int getEscuelaIdEscuela() {
        return this.escuelaIdEscuela;
    }
    
    public void setEscuelaIdEscuela(int escuelaIdEscuela) {
        this.escuelaIdEscuela = escuelaIdEscuela;
    }
    public int getSemestreIdSemestre() {
        return this.semestreIdSemestre;
    }
    
    public void setSemestreIdSemestre(int semestreIdSemestre) {
        this.semestreIdSemestre = semestreIdSemestre;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EscuelaSemestreId) ) return false;
		 EscuelaSemestreId castOther = ( EscuelaSemestreId ) other; 
         
		 return (this.getEscuelaIdEscuela()==castOther.getEscuelaIdEscuela())
 && (this.getSemestreIdSemestre()==castOther.getSemestreIdSemestre());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getEscuelaIdEscuela();
         result = 37 * result + this.getSemestreIdSemestre();
         return result;
   }   


}

