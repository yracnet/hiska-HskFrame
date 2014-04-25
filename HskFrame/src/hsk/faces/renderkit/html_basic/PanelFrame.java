/*
 * Please, keep the author reference
 */

package hsk.faces.renderkit.html_basic;

import hsk.faces.component.UIPanelFrame;
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


public class PanelFrame extends PanelRefresh {

    @Override
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {
        UIPanelFrame frame = (UIPanelFrame) component;
        UIComponent root = frame.getFacet(UIComponent.COMPOSITE_FACET_NAME);
        UIComponent facet = root.getFacet(frame.nameFacet());
        if (facet != null) {
            facet.encodeAll(context);
        }
        facet = root.getFacet(frame.nameFacet() + "_css");
        if (facet != null) {
            facet.encodeAll(context);
        }
        facet = root.getFacet(frame.nameFacet() + "_script");
        if (facet != null) {
            facet.encodeAll(context);
        }
    }
}
