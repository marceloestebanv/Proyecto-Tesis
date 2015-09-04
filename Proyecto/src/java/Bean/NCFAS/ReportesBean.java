/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.NCFAS;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean 
@Named(value = "reportesbean")
@SessionScoped
@RequestScoped

public class ReportesBean {
    
    
    
    
    
     public void reporNcfasPDF() throws JRException, IOException, Exception{
        
     Integer[] valores1;
     Integer[] valores2;
     Integer[] valores3;
     Integer[] valores4;
     Integer[] valores5;
     Integer[] valores6;
     Integer[] valores7;
     Integer[] valores8;
            
     
     ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
      String pathArchivos=(String) servletContext.getRealPath("/")+"/archivosVarios/"; 

        
          ListarItemsBean beanItems;
    beanItems= new ListarItemsBean();
    
    String nombrefamilia = beanItems.getNombrefamilia();
    
    valores1=beanItems.getValores1();
    valores2=beanItems.getValores2();
    valores3=beanItems.getValores3();
    valores4=beanItems.getValores4();
    valores5=beanItems.getValores5();
    valores6=beanItems.getValores6();
    valores7=beanItems.getValores7();
    valores8=beanItems.getValores8();
    
        //campo fijo (txtUsu.. valor del parmetro jasper, Mitocode... valor que le daremos desde java
        
    Map<String,Object> parametros= new HashMap<>();        
    parametros.put("field1",valores1[1]);
    parametros.put("field2",valores1[2]);
    parametros.put("field3",valores1[3]);
    parametros.put("field4",valores1[4]);
    parametros.put("field5",valores1[5]);
    parametros.put("field6",valores1[6]);
    parametros.put("field7",valores1[7]);
    
    parametros.put("field8",valores2[1]);
    parametros.put("field9",valores2[2]);
    parametros.put("field10",valores2[3]);
    parametros.put("field11",valores2[4]);
    parametros.put("field12",valores2[5]);
    parametros.put("field13",valores2[6]);
    parametros.put("field14",valores2[7]);
    parametros.put("field15",valores2[8]);
    
    parametros.put("field16",valores3[1]);
    parametros.put("field17",valores3[2]);
    parametros.put("field18",valores3[3]);
    parametros.put("field19",valores3[4]);
    parametros.put("field20",valores3[5]);
    parametros.put("field21",valores3[6]);
    parametros.put("field22",valores3[7]);
    parametros.put("field23",valores3[8]);
    
    parametros.put("field24",valores4[1]);
    parametros.put("field25",valores4[2]);
    parametros.put("field26",valores4[3]);
    parametros.put("field27",valores4[4]);
    parametros.put("field28",valores4[5]);
    parametros.put("field29",valores4[6]);
    parametros.put("field30",valores4[7]);
    parametros.put("field31",valores4[8]);
    
    parametros.put("field32",valores5[1]);
    parametros.put("field33",valores5[2]);
    parametros.put("field34",valores5[3]);
    parametros.put("field35",valores5[4]);
    parametros.put("field36",valores5[5]);
    parametros.put("field37",valores5[6]);
    parametros.put("field38",valores5[7]);
   
   
    parametros.put("field39",valores6[1]);
    parametros.put("field40",valores6[2]);
    parametros.put("field41",valores6[3]);
    parametros.put("field42",valores6[4]);
    parametros.put("field43",valores6[5]);
    parametros.put("field44",valores6[6]);
    
    
    
    parametros.put("field45",valores7[1]);
    parametros.put("field46",valores7[2]);
    parametros.put("field47",valores7[3]);
    parametros.put("field48",valores7[4]);
    parametros.put("field49",valores7[5]);
    parametros.put("field50",valores7[6]);
    
    
    parametros.put("field51",valores8[1]);
    parametros.put("field52",valores8[2]);
    parametros.put("field53",valores8[3]);
    parametros.put("field54",valores8[4]);
    parametros.put("field55",valores8[5]);
    parametros.put("field56",valores8[6]);
    parametros.put("field57",valores8[7]);
        
   
    System.out.println("el valor 1 es : " +valores1[1]);
    System.out.println("el último valor es : " +valores8[7]);
    
   // parametros.put("nombreUs",testBean.getUsuario().getNombre());
        
        
        
        File jasper= new File(pathArchivos+"reporteNcfas.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros);
       
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=NCFAS-Resultados-"+".pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
    }
     
     
      public void reporMineriaPDF(ActionEvent actionEvent) throws JRException, IOException, Exception{
        
    List<String> reglasEncontradas;
            
     
     ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
      String pathArchivos=(String) servletContext.getRealPath("/")+"/archivosVarios/"; 

        
          MineriaBean beanMineria;
    beanMineria= new MineriaBean();
    
    reglasEncontradas  = beanMineria.obtenerDim3();
        //campo fijo (txtUsu.. valor del parmetro jasper, Mitocode... valor que le daremos desde java
        
    Map<String,List> parametros= new HashMap<>();
    
    for(int i=1;reglasEncontradas.size(),i++){
    
    parametros.put("textoRegla", reglasEncontradas.get(i));
    }
    
    
   // parametros.put("nombreUs",testBean.getUsuario().getNombre());
        
        
        
        File jasper= new File(pathArchivos+"reporteMineria.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros);
       
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=Mineria-Resultados-"+".pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
                
                
             
                    
	



    }
    
}
