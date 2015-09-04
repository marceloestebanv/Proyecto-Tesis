/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;


 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
@ManagedBean
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        
        //obtener el real path del directorio
     //    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 //String realPath=(String) servletContext.getRealPath("/")+"resources\\imagen\\";
        images = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            images.add(i +".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}