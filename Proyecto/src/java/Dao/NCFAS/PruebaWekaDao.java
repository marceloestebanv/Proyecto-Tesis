/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.NCFAS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import static java.lang.System.in;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


import weka.core.converters.ConverterUtils.DataSource;
import weka.associations.Apriori;
import weka.core.Instances;

public class PruebaWekaDao {
    
public String consultaPalabra(String palabra) {
    
    String obtenerNum[] =palabra.split("");
    

     Matcher mat;
    Pattern pat1 = Pattern.compile("DIM1.*");
    Pattern pat2 = Pattern.compile("DIM2.*");
    Pattern pat3 = Pattern.compile("DIM3.*");
    Pattern pat4 = Pattern.compile("DIM4.*");
    Pattern pat5 = Pattern.compile("DIM5.*");
    Pattern pat6 = Pattern.compile("DIM6.*");
    Pattern pat7 = Pattern.compile("DIM7.*");
    Pattern pat8 = Pattern.compile("DIM8.*");
     
    String dim1="Entorno";
    String dim2="Competenicas Parentales";
    String dim3="Interacciones Familiares";
    String dim4="Seguridad Social";
    String dim5="Bienestar del Niño";
    String dim6="Vida Social Comunitaria";
    String dim7="Autonomía";
    String dim8="Salud Familiar";
    
    
    mat = pat1.matcher(palabra);
            if (mat.matches()) {
                palabra=dim1;
            }else{          
            mat = pat2.matcher(palabra);
            if(mat.matches()){
            palabra=dim2;
            }else{
            mat = pat3.matcher(palabra);
            if(mat.matches()){
            palabra=dim3;
            }else{
            mat = pat4.matcher(palabra);
            if(mat.matches()){
            palabra=dim4;
            }else{
            mat = pat5.matcher(palabra);
            if(mat.matches()){
            palabra=dim5;
            }else{
            mat = pat6.matcher(palabra);
            if(mat.matches()){
            palabra=dim6;
            }else{
            mat = pat7.matcher(palabra);
            if(mat.matches()){
            palabra=dim7;
            }else{
            mat = pat8.matcher(palabra);
            if(mat.matches()){
            palabra=dim8;
            }
            }
            }
            }
            }
            }
            }
            }
            
            if(obtenerNum[5].equals("-")){
            
            return palabra+ " con un valor= " + obtenerNum[5]+obtenerNum[6];
            
            }else{
            
            return palabra+ " con un valor= " + obtenerNum[5];
            }
}    
    
public String consultaValor(String dim2) {

    String valorDim2;
    String obtenerNum[] =dim2.split("");
    if(obtenerNum[5].equals("-")){
            
            valorDim2 =obtenerNum[5]+obtenerNum[6];
            
            }else{
            
            valorDim2 = obtenerNum[5];
            }
    
    return valorDim2;
}
    
public List<String> retornarReglas() throws DAOException, IOException, Exception{
    
  ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
      String pathArchivos=(String) servletContext.getRealPath("/")+"archivosVarios/"; // Sustituye "/" por el directorio ej: "/upload"
    FileWriter fichero = null; 
    PrintWriter pw = null; 
    String dataset=pathArchivos+"DataSetSimulado.arff";
    
    int retorno=0;
    List<String> almacenaRetorno = null;
    almacenaRetorno = new ArrayList<String>();
        
    final String nomFich = pathArchivos+"Resultado.txt";  
    Scanner in = null;
    int band=0;
 DataSource source = new DataSource(dataset);
 Apriori model = new Apriori();
 
    
 try{
    
    
    Instances data = source.getDataSet();
    
    model.buildAssociations(data);
    

    //FileOutputStream out = new FileOutputStream("test.txt");
    //ObjectOutputStream oout = new ObjectOutputStream(out);
         
    fichero = new FileWriter(pathArchivos+"Resultado.txt",true); 
    pw = new PrintWriter(fichero);
    BufferedWriter bw = new BufferedWriter(new FileWriter(pathArchivos+"Resultado.txt"));
    pw.println(model);
    
    //SE MUESTRA LOS RESULTADOS OBTENIDOS POR APRIORI
     System.out.println("LOS RESULTADOS DE APRIORI A DataSetSimulado.arff son:");
    System.out.println(model);
    bw.write("");
    fichero.close();
    
    
    System.out.println("Ahora vamos a leer los resultados obtenidos por apriori");
//BufferedReader bf = new BufferedReader(new FileReader("Resultado.txt"));
	// abre el fichero
	in = new Scanner(new FileReader(nomFich));
	 
	// lee el fichero palabra a palabra
	while (in.hasNext()) {
	 
	// lee primera palabra
	String palabra0 = in.next();
        
        List<String> almacenaAnterior = null;
        almacenaAnterior = new ArrayList<String>();
        String palabra1;
        String palabra2;
        String palabra3;
        String palabra4;
        String palabra5;
        String palabra6;
        
        String frase1;
        String frase2;
        String frase3;
        
        Pattern pat = Pattern.compile("DIM2.*"); 
        Pattern pat2 = Pattern.compile("==>");
        Pattern pat3 = Pattern.compile("^\\d.^");
        Pattern pat4 = Pattern.compile("DIM.*");
        
        String num1=("1.");
        String num2=("2.");
        String num3=("3.");
        String num4=("4.");
        String num5=("5.");
        String num6=("6.");
        String num7=("7.");
        String num8=("8.");
        String num9=("9.");
        String num10=("10.");
        
        
        Matcher mat;
           //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num1.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) { 
                    
                String valorDim2=consultaValor(palabra1);    
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    palabra4=consultaPalabra(palabra4);
                    frase1=("La dimensión Competencias Parentales con valor = " +valorDim2+ " afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();
                        String dimension=consultaPalabra(almacenaAnterior.get(2));
                        palabra5=consultaPalabra(palabra5);
                        frase1=(" La dimensión Competencias Parentales con valor = " +valorDim2+ " junto con la dimensión " + dimension + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){
                    String valorDim2=consultaValor(palabra4);
                    String dimension=consultaPalabra(almacenaAnterior.get(1));
                    frase1=("La dimensión Competencias Parentales con valor = " +valorDim2+ "  es afectada por la dimensión la " + dimension +  " una cantidad de " + almacenaAnterior.get(2) + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                        String valorDim2=consultaValor(palabra2);
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){ 
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            palabra5=consultaPalabra(palabra5);
                            frase1=("La dimensión Competencias Parentales con valor = " +valorDim2+ " junto con la dimensión " + dimension + "afectan a la dimensión " + palabra5 + " una cantidad de " + palabra3 + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){
                                String valorDim2=consultaValor(palabra5);
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            String dimension2=consultaPalabra(almacenaAnterior.get(2));
                            frase1=("La dimensión Competencias Parentales con valor = "+valorDim2+ "es afectada por la dimensión " + dimension + " y por la dimensión " + dimension2 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
                
                
                //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num2.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    palabra4=consultaPalabra(palabra4);
                    frase1=("La dimensión Competencias Parentales afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();
                        String dimension=consultaPalabra(almacenaAnterior.get(2));
                        palabra5=consultaPalabra(palabra5);
                        frase1=(" La dimensión Competencias Parentales  junto con la dimensión " + dimension + " afectan a la dimensión  " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){
                    String dimension=consultaPalabra(almacenaAnterior.get(1));
                    frase1=("La dimensión Competencias Parentales  es afectada por la dimensión la " + dimension +  " una cantidad de " + almacenaAnterior.get(2) + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){ 
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            palabra5=consultaPalabra(palabra5);
                            frase1=("La dimensión Competencias Parentales junto con la dimensión " + dimension + "afectan a la dimensión " + palabra5 + " una cantidad de " + palabra3 + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            String dimension2=consultaPalabra(almacenaAnterior.get(2));
                            frase1=("La dimensión Competencias Parentales es afectada por la dimensión " + dimension + " y por la dimensión " + dimension2 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num3.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    palabra4=consultaPalabra(palabra4);
                    frase1=("La dimensión Competencias Parentales afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();
                        String dimension=consultaPalabra(almacenaAnterior.get(2));
                        palabra5=consultaPalabra(palabra5);
                        frase1=(" La dimensión Competencias Parentales  junto con la dimensión " + dimension + " afectan a la dimensión  " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){
                    String dimension=consultaPalabra(almacenaAnterior.get(1));
                    frase1=("La dimensión Competencias Parentales  es afectada por la dimensión la " + dimension +  " una cantidad de " + almacenaAnterior.get(2) + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){ 
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            palabra5=consultaPalabra(palabra5);
                            frase1=("La dimensión Competencias Parentales junto con la dimensión " + dimension + "afectan a la dimensión " + palabra5 + " una cantidad de " + palabra3 + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            String dimension2=consultaPalabra(almacenaAnterior.get(2));
                            frase1=("La dimensión Competencias Parentales es afectada por la dimensión " + dimension + " y por la dimensión " + dimension2 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num4.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    palabra4=consultaPalabra(palabra4);
                    frase1=("La dimensión Competencias Parentales afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();
                        String dimension=consultaPalabra(almacenaAnterior.get(2));
                        palabra5=consultaPalabra(palabra5);
                        frase1=(" La dimensión Competencias Parentales  junto con la dimensión " + dimension + " afectan a la dimensión  " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){
                    String dimension=consultaPalabra(almacenaAnterior.get(1));
                    frase1=("La dimensión Competencias Parentales  es afectada por la dimensión la " + dimension +  " una cantidad de " + almacenaAnterior.get(2) + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){ 
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            palabra5=consultaPalabra(palabra5);
                            frase1=("La dimensión Competencias Parentales junto con la dimensión " + dimension + "afectan a la dimensión " + palabra5 + " una cantidad de " + palabra3 + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            String dimension2=consultaPalabra(almacenaAnterior.get(2));
                            frase1=("La dimensión Competencias Parentales es afectada por la dimensión " + dimension + " y por la dimensión " + dimension2 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num5.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    palabra4=consultaPalabra(palabra4);
                    frase1=("La dimensión Competencias Parentales afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();
                        String dimension=consultaPalabra(almacenaAnterior.get(2));
                        palabra5=consultaPalabra(palabra5);
                        frase1=(" La dimensión Competencias Parentales  junto con la dimensión " + dimension + " afectan a la dimensión  " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){
                    String dimension=consultaPalabra(almacenaAnterior.get(1));
                    frase1=("La dimensión Competencias Parentales  es afectada por la dimensión la " + dimension +  " una cantidad de " + almacenaAnterior.get(2) + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){ 
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            palabra5=consultaPalabra(palabra5);
                            frase1=("La dimensión Competencias Parentales junto con la dimensión " + dimension + "afectan a la dimensión " + palabra5 + " una cantidad de " + palabra3 + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            String dimension2=consultaPalabra(almacenaAnterior.get(2));
                            frase1=("La dimensión Competencias Parentales es afectada por la dimensión " + dimension + " y por la dimensión " + dimension2 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num6.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    palabra4=consultaPalabra(palabra4);
                    frase1=("La dimensión Competencias Parentales afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();
                        String dimension=consultaPalabra(almacenaAnterior.get(2));
                        palabra5=consultaPalabra(palabra5);
                        frase1=(" La dimensión Competencias Parentales  junto con la dimensión " + dimension + " afectan a la dimensión  " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){
                    String dimension=consultaPalabra(almacenaAnterior.get(1));
                    frase1=("La dimensión Competencias Parentales  es afectada por la dimensión la " + dimension +  " una cantidad de " + almacenaAnterior.get(2) + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){ 
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            palabra5=consultaPalabra(palabra5);
                            frase1=("La dimensión Competencias Parentales junto con la dimensión " + dimension + "afectan a la dimensión " + palabra5 + " una cantidad de " + palabra3 + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            String dimension2=consultaPalabra(almacenaAnterior.get(2));
                            frase1=("La dimensión Competencias Parentales es afectada por la dimensión " + dimension + " y por la dimensión " + dimension2 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num7.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    palabra4=consultaPalabra(palabra4);
                    frase1=("La dimensión Competencias Parentales afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();
                        String dimension=consultaPalabra(almacenaAnterior.get(2));
                        palabra5=consultaPalabra(palabra5);
                        frase1=(" La dimensión Competencias Parentales  junto con la dimensión " + dimension + " afectan a la dimensión  " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){
                    String dimension=consultaPalabra(almacenaAnterior.get(1));
                    frase1=("La dimensión Competencias Parentales  es afectada por la dimensión la " + dimension +  " una cantidad de " + almacenaAnterior.get(2) + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){ 
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            palabra5=consultaPalabra(palabra5);
                            frase1=("La dimensión Competencias Parentales junto con la dimensión " + dimension + "afectan a la dimensión " + palabra5 + " una cantidad de " + palabra3 + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            String dimension2=consultaPalabra(almacenaAnterior.get(2));
                            frase1=("La dimensión Competencias Parentales es afectada por la dimensión " + dimension + " y por la dimensión " + dimension2 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num8.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    palabra4=consultaPalabra(palabra4);
                    frase1=("La dimensión Competencias Parentales afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();
                        String dimension=consultaPalabra(almacenaAnterior.get(2));
                        palabra5=consultaPalabra(palabra5);
                        frase1=(" La dimensión Competencias Parentales  junto con la dimensión " + dimension + " afectan a la dimensión  " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){
                    String dimension=consultaPalabra(almacenaAnterior.get(1));
                    frase1=("La dimensión Competencias Parentales  es afectada por la dimensión la " + dimension +  " una cantidad de " + almacenaAnterior.get(2) + " veces.\n");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){ 
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            palabra5=consultaPalabra(palabra5);
                            frase1=("La dimensión Competencias Parentales junto con la dimensión " + dimension + "afectan a la dimensión " + palabra5 + " una cantidad de " + palabra3 + " veces.\n");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){
                            String dimension=consultaPalabra(almacenaAnterior.get(1));
                            String dimension2=consultaPalabra(almacenaAnterior.get(2));
                            frase1=("La dimensión Competencias Parentales es afectada por la dimensión " + dimension + " y por la dimensión " + dimension2 + " una cantidad de " + almacenaAnterior.get(3) + " veces.\n");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
        }// while (in.hasNext())
	 
 
 
 
 
 }  catch (FileNotFoundException e) {
	 System.out.println("Error abriendo el fichero "+nomFich);
        
	 } finally {
	 if (in!=null){
	 in.close();
         
         
         }
	 } // try
        
 
   return almacenaRetorno;
     //retorno=Integer.parseInt(arreglo2[3]);
     //System.out.println(lineas[18]);
}



}
