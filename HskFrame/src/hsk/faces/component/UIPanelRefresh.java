/*
 * Please, keep the author reference
 */
package hsk.faces.component;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.el.ELContext;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

/**
 * @autor Willyams Ricardo Yujra Huanca
 * @email yracnet@gmail.com
 * @company http://www.hiskasoft.com
 * @version 2.7
 * @see https://github.com/yracnet/hiska-HskFrame
*/

public class UIPanelRefresh extends UINamingContainer implements Serializable {

    public static final String HSK_COMPONENT_FAMILY = "hsk.faces.NamingContainer";
    public static final String HSK_COMPONENT_TYPE =  "hsk.faces.Composite";
    public static final String HSK_PANEL_REFRESH_KEY = UIPanelRefresh.class.getName();

    public static Map<String, UIPanelRefresh> getPanelRefreshDeclared(FacesContext context) {
        Map<String, UIPanelRefresh> map = (Map<String, UIPanelRefresh>) context.getExternalContext().getRequestMap().get(HSK_PANEL_REFRESH_KEY);
        if (map == null) {
            map = new HashMap();
            context.getExternalContext().getRequestMap().put(HSK_PANEL_REFRESH_KEY, map);
        }
        return map;
    }

    public Map<String, Object> getAttrs(FacesContext context) {
        ELContext elContext = context.getELContext();
        return (Map<String, Object>) elContext.getELResolver().getValue(elContext, this, "attrs");
    }

    public String getKey(FacesContext context) {
        return getId();
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        super.restoreState(context, state);
        getPanelRefreshDeclared(context).put(getKey(context), this);
    }

    @Override
    public void decode(FacesContext context) {
        getPanelRefreshDeclared(context).put(getKey(context), this);
        super.decode(context);
    }

    @Override
    public void encodeAll(FacesContext context) throws IOException {
        getPanelRefreshDeclared(context).put(getKey(context), this);
        super.encodeAll(context);
    }

    protected enum PropertyKeys {

        autoRefresh,
    }

    public boolean isAutoRefresh() {
        return Boolean.valueOf(getStateHelper().eval(PropertyKeys.autoRefresh, Boolean.TRUE).toString());
    }

    public void setAutoRefresh(boolean autoRefresh) {
        getStateHelper().put(PropertyKeys.autoRefresh, autoRefresh);
    }

    @Override
    public boolean isRendered() {
        return true;
    }

    @Override
    public boolean getRendersChildren() {
        return super.isRendered() && super.getRendersChildren();
    }

    @Override
    public String getFamily() {
        return HSK_COMPONENT_FAMILY;
    }
    
    @Override
    public String getRendererType(){
        return HSK_COMPONENT_TYPE;
    }

    @Override
    public boolean isTransient() {
        return false;
    }

}
