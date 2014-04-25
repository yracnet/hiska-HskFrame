/*
 * Please, keep the author reference
 */
package hsk.faces.application;

import hsk.faces.component.UIPanelFrame;
import java.util.Map;
import java.util.Set;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * @autor Willyams Ricardo Yujra Huanca
 * @email yracnet@gmail.com
 * @company http://www.hiskasoft.com
 * @version 2.7
 * @see https://github.com/yracnet/hiska-HskFrame
*/

public class CustomNavegationImpl extends ConfigurableNavigationHandler {

    ConfigurableNavigationHandler delegate;

    public CustomNavegationImpl(ConfigurableNavigationHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public NavigationCase getNavigationCase(FacesContext context, String fromAction, String outcome) {
        return delegate.getNavigationCase(context, fromAction, outcome);
    }

    @Override
    public Map<String, Set<NavigationCase>> getNavigationCases() {
        return delegate.getNavigationCases();
    }

    @Override
    public void handleNavigation(FacesContext context, String fromAction, String outcome) {
        UIComponent component = UIViewRoot.getCurrentComponent(context);
        UIPanelFrame frame = UIPanelFrame.getCurrentPanelFrame(context, component);
        if (frame != null) {
            NavigationCase navegateCase = delegate.getNavigationCase(context, fromAction, outcome);
            boolean resolver = frame.handleNavigation(context, navegateCase, component, true);
            if (resolver) {
                return;
            }
        }
        delegate.handleNavigation(context, fromAction, outcome);
    }
}
