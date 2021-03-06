package pe.edu.unu.evaluacion.pojo;
// Generated 08/02/2016 11:38:29 PM by Hibernate Tools 4.3.1



/**
 * Administrador generated by hbm2java
 */
public class Administrador  implements java.io.Serializable {


     private Integer idAdministrador;
     private Usuario usuario;
     private String nombres;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private Boolean sexo;
     private String dni;
     private String ruc;
     private String telefonoFijo;
     private String celular;
     private String email;
     private String tipoDireccion;
     private String direccion;
     private String tipoLocalidad;
     private String localidad;
     private String referencia;
     private String registro;
     private Integer idPersona;

    public Administrador() {
    }

	
    public Administrador(Usuario usuario, String nombres, String apellidoPaterno, String apellidoMaterno, String dni, String registro) {
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.dni = dni;
        this.registro = registro;
    }
    public Administrador(Usuario usuario, String nombres, String apellidoPaterno, String apellidoMaterno, Boolean sexo, String dni, String ruc, String telefonoFijo, String celular, String email, String tipoDireccion, String direccion, String tipoLocalidad, String localidad, String referencia, String registro, Integer idPersona) {
       this.usuario = usuario;
       this.nombres = nombres;
       this.apellidoPaterno = apellidoPaterno;
       this.apellidoMaterno = apellidoMaterno;
       this.sexo = sexo;
       this.dni = dni;
       this.ruc = ruc;
       this.telefonoFijo = telefonoFijo;
       this.celular = celular;
       this.email = email;
       this.tipoDireccion = tipoDireccion;
       this.direccion = direccion;
       this.tipoLocalidad = tipoLocalidad;
       this.localidad = localidad;
       this.referencia = referencia;
       this.registro = registro;
       this.idPersona = idPersona;
    }
   
    public Integer getIdAdministrador() {
        return this.idAdministrador;
    }
    
    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public Boolean getSexo() {
        return this.sexo;
    }
    
    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getRuc() {
        return this.ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    public String getTelefonoFijo() {
        return this.telefonoFijo;
    }
    
    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTipoDireccion() {
        return this.tipoDireccion;
    }
    
    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTipoLocalidad() {
        return this.tipoLocalidad;
    }
    
    public void setTipoLocalidad(String tipoLocalidad) {
        this.tipoLocalidad = tipoLocalidad;
    }
    public String getLocalidad() {
        return this.localidad;
    }
    
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getReferencia() {
        return this.referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public String getRegistro() {
        return this.registro;
    }
    
    public void setRegistro(String registro) {
        this.registro = registro;
    }
    public Integer getIdPersona() {
        return this.idPersona;
    }
    
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }




}


