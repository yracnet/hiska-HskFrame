/*
 * Please, keep the author reference
 */
package hsk.faces.component;

import com.sun.faces.context.flash.ELFlash;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.NavigationCase;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;

/**
 * @autor Willyams Ricardo Yujra Huanca
 * @email yracnet@gmail.com
 * @company http://www.hiskasoft.com
 * @version 2.7
 * @see https://github.com/yracnet/hiska-HskFrame
*/

public class UIPanelFrame extends UIPanelRefresh implements Serializable {

    @Override
    public String getKey(FacesContext context) {
        return (String) getAttrs(context).get("name");
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        super.restoreState(context, state);
        event = currentEvent(context);
    }

    @Override
    public void decode(FacesContext context) {
        event = currentEvent(context);
        super.decode(context);
    }

    @Override
    public void encodeAll(FacesContext context) throws IOException {
        event = currentEvent(context);
        super.encodeAll(context);
        nextEvent(context);
    }

    public static UIPanelFrame getCurrentPanelFrame(FacesContext context, UIComponent component) {
        String target = (String) component.getAttributes().get("target");
        if (target == null) {
            return null;
        }
        if (target.startsWith(":")) {
            target = target.substring(1);
        }
        UIPanelRefresh refresh = getPanelRefreshDeclared(context).get(target);
        if (refresh instanceof UIPanelFrame) {
            return (UIPanelFrame) refresh;
        }
        return null;
    }

    public boolean handleNavigation(FacesContext context, NavigationCase navegateCase, UIComponent component, boolean b) {
        String fromViewId = navegateCase.getFromViewId();
        String toViewId = navegateCase.getToViewId(context);
        if (toViewId != null && fromViewId.equals(toViewId) == false) {
            event.reset();
            try {
                event.url = navegateCase.getBookmarkableURL(context).getPath();
            } catch (MalformedURLException e) {
                throw new FacesException("NavegationCase BookmarkableURL Error: " + navegateCase, e);
            }
            event.eventId = component.getClientId();
            event.render = context.getExternalContext().getRequestParameterMap().get(PartialViewContext.PARTIAL_RENDER_PARAM_NAME);
            event.execute = context.getExternalContext().getRequestParameterMap().get(PartialViewContext.PARTIAL_EXECUTE_PARAM_NAME);
            event.fromViewId = context.getViewRoot().getViewId();
            event.toViewId = toViewId;
            event.state = StateFrame._open;
            return true;
        }
        return false;
    }

    public String getClientFrameId() {
        return getId() + ":frame";
    }

    public String nameFacet() {
        return event.state.toString();
    }

    enum StateFrame {

        _open, _close, _none;
    }

    class EventFrame implements Serializable {

        private String eventId = null;
        private String render = null;
        private String execute = null;
        private String toViewId = null;
        private String fromViewId = null;
        private String url = null;
        private StateFrame state = StateFrame._none;

        @Override
        public String toString() {
            return "EventFrame[" + eventId + ",{" + render + "},{" + execute + "}," + fromViewId + " to " + toViewId + ", " + state + "]";
        }

        private void reset() {
            eventId = null;
            render = null;
            execute = null;
            toViewId = null;
            fromViewId = null;
            url = null;
            state = StateFrame._none;
        }
    }

    private EventFrame event = null;

    private EventFrame currentEvent(FacesContext context) {
        Map<String, Object> scopeMap = (Map<String, Object>) getAttrs(context).get("scopeMap");
        event = (EventFrame) scopeMap.get(getId());
        if (event == null) {
            event = new EventFrame();
            scopeMap.put(getId(), event);
        }
        return event;
    }

    @Override
    public String getRendererType() {
        return "hsk.faces.CompositeFrame";
    }

    public boolean isOpen() {
        return event.state == StateFrame._open;
    }

    public boolean isClose() {
        return event.state == StateFrame._close;
    }

    public boolean isNone() {
        return event.state == StateFrame._none;
    }

    public String getFromViewId() {
        return event == null ? null : event.fromViewId;
    }

    public String getToViewId() {
        return event == null ? null : event.toViewId;
    }

    public String getRenderIds() {
        return event == null ? "0" : event.render;
    }

    public String getExecuteIds() {
        return event == null ? "0" : event.execute;
    }

    public String getEventId() {
        return event == null ? "this" : event.eventId;
    }

    public String getUrl() {
        return event == null ? null : event.url;
    }

    @Override
    public String toString() {
        return "UIPanelFrame[" + getId() + ", event:" + event + "]";
    }

    public String getScriptEvent() {
        if (event != null && event.url != null) {
            StringBuilder onclose = new StringBuilder();
            onclose.append("mojarra.ab('");
            onclose.append(event.eventId);
            onclose.append("',{type:'click'},'click','");
            //onclose.append(event.execute);
            onclose.append(0);
            onclose.append("','");
            onclose.append(event.render);
            onclose.append("',{});return false;");
            return onclose.toString();
        }
        return "return false;";
    }

    @Override
    public boolean isAutoRefresh() {
        return super.isAutoRefresh() && event.state != StateFrame._none;
    }

    public void nextEvent(FacesContext context) {
        if (event.state == StateFrame._open) {
            event.state = StateFrame._close;
            ELFlash flash = (ELFlash) context.getExternalContext().getFlash();
            flash.setRedirect(true);
            flash.doLastPhaseActions(context, true);
        } else if (event.state == StateFrame._close) {
            event.state = StateFrame._none;
            ELFlash flash = (ELFlash) context.getExternalContext().getFlash();
            flash.setRedirect(true);
            flash.doLastPhaseActions(context, true);
        } else {
        }
    }

}
