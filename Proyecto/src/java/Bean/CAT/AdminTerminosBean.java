/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;



import Model.CAT.AnalisisUtils;
import Model.CAT.Termino;
import Model.CAT.TerminoLamina;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jean
 */
@ManagedBean(name = "adminTerminosBean")
@ViewScoped

public class AdminTerminosBean {

    //Esta clase almacenara la lista de terminos que irán directamente
    
    /**
     * Creates a new instance of EstadisticaBean
     */
  
    
 //este atributo setea el id de la lamina que se quiere modificar
private int idLaminaPagina=0;
 

 private  List <TerminoLamina>[] term ;

 
  private TerminoLamina terminoLamina;
  
  private TerminoLamina termino;
  
  private String palabra;
  
    public AdminTerminosBean() throws IOException, FileNotFoundException, ClassNotFoundException {
  
   // term= RI.deserializarTerminos();
    
    
    
    
      ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        
       
         String pathDiccionario=(String) servletContext.getRealPath("/")+"/Terminos/Terminos.obj"; // Sustituye "/" por el directorio ej: "/upload"

          File f = new File(pathDiccionario);
       // if(f.exists() && !f.isDirectory()) { 

        if(!f.exists() ) { 
            
            System.out.println(" no existe la ruta");
            List<TerminoLamina>[] listaTerminosLamina= new ArrayList[10];
            
            System.out.println(" no existe el diccionario");
            for(List<TerminoLamina>listaTerm:listaTerminosLamina){
                if(listaTerm==null){
                    listaTerm=new ArrayList<>();
                }
             
                   
               }
            
            term=listaTerminosLamina;
            serializarTerminos();
            }else{
            term= RI.deserializarTerminos();
        }
            
    
    
    
    
    
    termino=new TerminoLamina();

        
    }
  
    
    public void agregarTermino(int idLamina){
        
        
                
       //Vemos que no sea null el id del relato en el arreglo de terminos asociados,le creamos una lista
              if (term[idLamina]==null){
              term[idLamina]=new ArrayList<>();
              
              }
              
        
    List<String> listae=new ArrayList<>();
     //listae.add("hola");
    
  
 String delimitadores= "[ .,;?!¡¿\'\"\\[\\]\\(\\)\\t]+";
String[] palabrasSeparadas = termino.getTermino().split(delimitadores);


    if(palabrasSeparadas.length==1){
        termino.setTermino(formatearTermino(termino.getTermino()));


         if(!existsTermino(termino.getTermino(),idLamina)){
            termino.setTerminosAsociados(listae);
            term[idLamina].add(termino);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "El término fue añadido "));
       
         }else{
        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El término ya existe, o forma parte de terminos asociados "));
            }
        
    }else{
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los términos deben ser de una palabra "));
        
    }
    
        termino= new TerminoLamina();
    }
  
    public String formatearTermino(String termino){
      
AnalisisUtils analisis = new AnalisisUtils(); 

   termino=analisis.quitarAcentos(termino);
    termino=analisis.quitarMayusculas(termino);
    termino=analisis.quitarNumeros(termino);
    
    return termino;
    }
    
    public boolean existsTermino(String palabra,int idLamina){
       boolean existe=false;
        for(TerminoLamina  terminoLam:term[idLamina]){
           if (terminoLam.getTermino().equals(palabra))
            return true;
           
           for(String termAsoc:terminoLam.getTerminosAsociados()){
               if (termAsoc.equals(palabra))
                   return true;
               
           }

        }
       return existe; 
    }
    
   public List<TerminoLamina> getTerminosLaminaId(int idLamina){
        

       return term[idLamina];
    }
   
   
   
   
    
     public void eliminarTermAsoc(String termAsoc){

       List<String> term= terminoLamina.getTerminosAsociados();
       term.remove(termAsoc);
       terminoLamina.setTerminosAsociados(term);
        
    }
     
          public void añadirTermAsoc(String termAsoc){
              
        
      
              
              
              
 String delimitadores= "[ .,;?!¡¿\'\"\\[\\]\\(\\)\\t]+";
String[] palabrasSeparadas =termAsoc.split(delimitadores);


    if(palabrasSeparadas.length==1){
            termAsoc=formatearTermino(termAsoc);              
              

            if(!existsTermino(termAsoc, idLaminaPagina)){
            terminoLamina.getTerminosAsociados().add(termAsoc);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", "El término asociado fue añadido "));      
    
            } else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El término asociado ya existe, o es parte de los Términos Generales de la Lámina "));  
      
         }
    
    
    }else{
        
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los términos deben ser de una palabra "));   
    }
    
   
          }
          
          public void añadirTerminoAsocDesdeRectificar(Termino termino){
              // desde rectificar terminos estamos agregando una nueva palabra al diccionario con id relato y termino asociado al cual hay que añadirla
             String palabraAñadir=termino.getPalabra();
            int  idRelato=termino.getIdLámina();
           String   termAsoc= termino.getTerminoAsociado();
              
              System.out.println("ela palabra a añadir es "+palabraAñadir);
              System.out.println(" el id relato es"+idRelato);
              System.out.println(" el termino al cual debemos asociarlo es "+termAsoc);
              
              
              //aca se agrega
              this.term[idRelato].get(getIndexTerminoLamina(idRelato, termAsoc)).getTerminosAsociados().add(palabraAñadir);
              
           
              
              
          }
          
            public void añadirNuevoTerminoDesdeRectificar(Termino termino){
              // desde rectificar terminos estamos agregando una nueva palabra al diccionario con id relato y termino asociado al cual hay que añadirla
              
                
              System.out.println("ela palabra a añadir es "+termino.getPalabra());
              System.out.println(" el id relato es"+termino.getIdLámina());
              System.out.println(" el nuevo Termino es  "+termino.getTerminoAsociado());
              
              String palabraAñadir=termino.getPalabra();
              int idRelato=termino.getIdLámina();
              String nuevoTermino=termino.getTerminoAsociado();
              int connotacion=termino.getConnotacion();
              
              
              
                      
             //verificar que no exista en la lista para añadirla si existe lanzar mensaje
              if(palabraAñadir.equals(nuevoTermino)){
                  
              //tener cuidado con el null en la lista
              TerminoLamina nuevo =new TerminoLamina(palabraAñadir, connotacion, new ArrayList());
                  this.term[idRelato].add(nuevo);
               }else{
                  List<String> lista= new ArrayList<>();
                  lista.add(palabraAñadir);
                   TerminoLamina nuevo =new TerminoLamina(nuevoTermino, connotacion, lista);
                   this.term[idRelato].add(nuevo);
                  
              }
              
            
              
              
          }
          
          public int getIndexTerminoLamina (int idRelato,String Palabratermino){
              //este metodo me retorna el indice donde se encuentra el termino lamina asociado a una palabra, si no esta es -1
              int numero=-1;
             for (int i = 0; i < term[idRelato].size(); i++) {
                      
                 if(term[idRelato].get(i).getTermino().equals(Palabratermino)){
                     System.out.println(" el id del termino es "+i);
                 return i;
                 }
                 
                  }
              return numero;
          }
          
          
          
          
        // public TerminoLamina getTerminoRectificar()
          
     
      public void remove(TerminoLamina termino, int idLamina) {
    try {
        
        System.out.println(" se quiere eliminar de la lista de terminos ");
        System.out.println("termino "+termino.getTermino());
        System.out.println("id de la lamina" + idLamina);
        
        //hay que borrar tambien los terminos del tesauro
        term[idLamina].remove(termino);
        System.out.println(" se ha eliminado");
        
        for (TerminoLamina termi: term[idLamina]){
            System.out.println("termino lamina: "+termi.getTermino());
        }
        
       
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
      
      
    
          
    public void editar( int idLamina ){
        
        System.out.println(" vamos a editar");
    }

        
       public void serializarTerminos() throws FileNotFoundException, IOException{
  //serialización
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
          
          //serialización
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(realPath+"/Terminos/Terminos.obj"))) {
                salida.writeObject(term);
               // RecuperacionInformacion.setListaTerminosLaminas2(term);
                System.out.println(" se serializó en AdminTerminosBean");
                FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Se han guardado los cambios",  "") );
               
            } 
           
       }
       
       
       
       
       public void deserializarTerminos() throws FileNotFoundException, IOException, ClassNotFoundException{
           
           ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
         
           //recuperar los terminos // setearle el rut del examinado para obtenerlo
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(realPath+"/Terminos/Terminos.obj"));
                term=(List<TerminoLamina>[])entrada.readObject();
                
                System.out.println("los terminos tienen "+ term.length);
       }
       
       public String clasificacion(int connotacion){
        //Metodo que dada una clasificacion me retorna el string correspondiente
        String clasif="Neutro";
        if (connotacion==0) return "Negativo";
        if (connotacion==1) return "Positivo";
        
            return clasif;
    }
 
      

    public List<TerminoLamina>[] getTerm() {
        return term;
    }

    public void setTerm(List<TerminoLamina>[] term) {
        this.term = term;
    }

    public TerminoLamina getTerminoLamina() {
        return terminoLamina;
    }

    public void setTerminoLamina(TerminoLamina terminoLamina) {
        this.terminoLamina = terminoLamina;
    }

    public TerminoLamina getTermino() {
        return termino;
    }

    public void setTermino(TerminoLamina termino) {
        this.termino = termino;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
       
        this.palabra = palabra;
    }

    public int getIdLaminaPagina() {
        return idLaminaPagina;
    }

    public void setIdLaminaPagina(int idLaminaPagina) {
        this.idLaminaPagina = idLaminaPagina;
    }
     
    
    
    
    
    
    
    public void gestionRectificarTerminos(Termino termino){
           
       //Termino  itemSelecionado = (Termino) event.getComponent().getAttributes().get("cursoItem");   
           
        RequestContext context = RequestContext.getCurrentInstance(); 
  
    
    context.addCallbackParam(":dlgmodificar2",termino);
    
       } 
    
    
    public  void cargarDiccionario () throws IOException, FileNotFoundException, ClassNotFoundException{
      
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        
       
         String pathDiccionario=(String) servletContext.getRealPath("/")+"/Terminos/Terminos.obj"; // Sustituye "/" por el directorio ej: "/upload"

          File f = new File(pathDiccionario);
       // if(f.exists() && !f.isDirectory()) { 

        if(!f.exists() ) { 
            
            System.out.println(" no existe la ruta");
            List<TerminoLamina>[] listaTerminosLamina= new ArrayList[10];
            
            System.out.println(" no existe el diccionario");
            for(List<TerminoLamina>listaTerm:listaTerminosLamina){
                if(listaTerm==null){
                    listaTerm=new ArrayList<>();
                }
             
                   
               }
            
            term=listaTerminosLamina;
            serializarTerminos();
            }
            

        
        
      List <TerminoLamina>[] terminos= RI.deserializarTerminos();
      
      System.out.println(" se cargó");
       
 String realPath=(String) servletContext.getRealPath("/")+"diccionario.txt"; // Sustituye "/" por el directorio ej: "/upload"

        
        List lista= new ArrayList();
       File archivo = null;
       FileReader fr = null;
      BufferedReader br = null;
 
      
      
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (realPath);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         //String[] palabras=null;
         int relatoModificar=100;
         
         while((linea=br.readLine())!=null){
           // System.out.println(linea);
        
              System.out.println(" una linea");
           String[] palabras=linea.split("\t");
            
           if (palabras.length<2){
                 //quiere decir que es el identificador del relato
              relatoModificar=Integer.parseInt(palabras[0].charAt((palabras[0].length())-1)+"");
                System.out.println("se modificará el relato"+relatoModificar);
            
            }else{
              //empezamos a cargar el objeto
               //en la posicion 0 esta el termino y en la 1 la connotación
               String[] encabezado=palabras[0].split(",");
               
               
               
               String terminoIngresar= encabezado[0];
               int connotación=Integer.parseInt(encabezado[1]);
               if(connotación==2){
                   connotación=-1;
                   System.out.println("se cambió connotación a -1"); 
               }
               
               
               //aca veremos los terminos asociados
               String[] asociados=palabras[1].split(",");

               //revisamos que el termino no exista en la posicion
               
               
             boolean existeTerm=existeTermino(terminos[relatoModificar-1], terminoIngresar);
               
               
               if(!existeTerm){
                   //si no existe el termino en ninguna parte hay que crear el objeto y agregarlo  a termino general 
                List<String> l = new ArrayList<>();
                   //vemos los terminos asociados
                 for  (String asociado : asociados) {
                     
                      if(!existeTermino(terminos[relatoModificar-1], asociado))
                          l.add(asociado);

                       }
      
                 
                  Set<String> unique = new HashSet<String>(l);
                  List<String> listaSinRep=new ArrayList<>();
                  listaSinRep.addAll(unique);
                  
                 
             TerminoLamina nuevoTermino=new TerminoLamina(terminoIngresar, connotación,listaSinRep );
              
            
             terminos[relatoModificar-1].add(nuevoTermino);
             
       
                   
               }else{
                  // si existe hay que revisar sus terminos generales para ver que no existan tambien
                  //vemos todos sus terminos generales y se los agregamos al temino, finalmente se lo setearemos
                   List<String> l = new ArrayList<>();
                   
                   //aca buscamos los terminos asociados del texto y llenamos una lista con los que no esten repetidos
                   for  (String asociado : asociados) {
                     
                      if(!existeTermino(terminos[relatoModificar-1], asociado))

                          l.add(asociado);

                       } 
                   
                   //aca agregamos a la lista original la lista de terminos
                      for (TerminoLamina terminoLamina1 : terminos[relatoModificar-1]) {
                   if (terminoLamina1.getTermino().equals(terminoIngresar)){
                       System.out.println(" añadiendo terminos asociados al termino"+terminoLamina1.getTermino());
                       for(String cadenas: l){
                           
                           //si ya no fue añadida
                           if(!terminoLamina1.getTerminosAsociados().contains(cadenas)){
                           terminoLamina1.getTerminosAsociados().add(cadenas);
                           }
                       }
                   }
                   
               }
                  
                 // fin del else que ve si el termino existe     
               }
               
               
                      
                
            }  
           
           
           for(List<TerminoLamina> list: terminos){
               for(TerminoLamina terminoL:list){
                   System.out.println(" termino"+terminoL.getTermino());
               }
           
           }
         
          term=terminos;
            serializarTerminos();
               
         }
         
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
       
       
       
    
    }
    
  
  
  
  public boolean existeTermino(List<TerminoLamina> terminoLamina,String terminoIngresar){
      
       boolean existeTerm=false;
               
               for (TerminoLamina termLamina : terminoLamina) {
                  // System.out.println(" a comparar"+termLamina.getTermino()+" con "+terminoIngresar);
                   if(termLamina.getTermino().equals(terminoIngresar))
                       existeTerm=true;
                   if(termLamina.getTerminosAsociados().contains(terminoIngresar))
                       existeTerm=true;
  
                   }
      
      return existeTerm;
  }
  
    
    
    
    
    
    
}
