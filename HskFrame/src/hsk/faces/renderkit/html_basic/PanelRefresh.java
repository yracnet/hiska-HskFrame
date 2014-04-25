/*
 * Please, keep the author reference
 */
package hsk.faces.renderkit.html_basic;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


/**
 * @autor Willyams Ricardo Yujra Huanca
 * @email yracnet@gmail.com
 * @company http://www.hiskasoft.com
 * @version 2.7
 * @see https://github.com/yracnet/hiska-HskFrame
*/

public class PanelRefresh extends com.sun.faces.renderkit.html_basic.CompositeRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
        String style = (String) component.getAttributes().get("style");
        String styleClass = (String) component.getAttributes().get("styleClass");
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", component);
        writer.writeAttribute("id", component.getClientId(), "id");
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }
        if (style != null) {
            writer.writeAttribute("style", style, "style");
        }
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {
        super.encodeChildren(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
    }
}
