/*
 * Please, keep the author reference
 */
package hsk.faces.renderkit.html_basic;

import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
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

public class PanelMessages extends HtmlBasicRenderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
        rendererParamsNotNull(context, component);
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", component);
        writeIdAttributeIfNecessary(context, writer, component);
        if (component.getRendersChildren()) {
            String style = (String) component.getAttributes().get("style");
            String styleClass = (String) component.getAttributes().get("styleClass");
            if (styleClass != null) {
                writer.writeAttribute("class", styleClass, "styleClass");
            }
            if (style != null) {
                writer.writeAttribute("style", style, "style");
            }
        }
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {
        rendererParamsNotNull(context, component);
        List<FacesMessage> messageList = context.getMessageList();
        if (messageList == null || messageList.isEmpty()) {
            return;
        }
        ResponseWriter writer = context.getResponseWriter();
        for (FacesMessage message : messageList) {
            writer.startElement("p", component);
            writer.startElement("b", component);
            writer.write(message.getSummary());
            writer.endElement("b");
            writer.write(message.getDetail());
            writer.endElement("p");
        }
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        rendererParamsNotNull(context, component);
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }
}
