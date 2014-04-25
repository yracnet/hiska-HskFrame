/*
 * Please, keep the author reference
 */
package hsk.faces.renderkit.html_basic;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * @autor Willyams Ricardo Yujra Huanca
 * @email yracnet@gmail.com
 * @company http://www.hiskasoft.com
 * @version 2.7
 * @see https://github.com/yracnet/hiska-HskFrame
 */


public class LinkFrameRendered extends com.sun.faces.renderkit.html_basic.CommandLinkRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        String target = (String) component.getAttributes().get("target");
        boolean openIFrame = "hsk-jsf-frm".equals(target);
        boolean returnIFrame = ":hsk-jsf-frm".equals(target);
        System.out.println(component + "----> " + openIFrame + " - " + returnIFrame);
        /*
        
        
         System.out.println("----> "+target);
         if (target != null && target.startsWith(":")) {
         String onclick = (String) component.getAttributes().get("onclick");
         System.out.println("----aadddd script " + onclick);
         onclick = "ifrm('" + target.substring(1) + "');" + onclick == null ? "" : onclick;
         component.getAttributes().put("onclick", onclick);
         }*/
        super.encodeBegin(context, component);
    }
}
