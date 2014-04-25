/*
 * Please, keep the author reference
 */
package hsk.faces.context;

import com.sun.faces.context.PartialViewContextImpl;
import hsk.faces.component.UIPanelRefresh;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;


/**
 * @autor Willyams Ricardo Yujra Huanca
 * @email yracnet@gmail.com
 * @company http://www.hiskasoft.com
 * @version 2.7
 * @see https://github.com/yracnet/hiska-HskFrame
*/

public class PartialViewRefreshContextFactory extends javax.faces.context.PartialViewContextFactory {

    @Override
    public PartialViewContext getPartialViewContext(FacesContext context) {
        return new AutoPartialViewContextImpl(context);
    }

    class AutoPartialViewContextImpl extends PartialViewContextImpl {

        private final FacesContext context;

        private AutoPartialViewContextImpl(FacesContext context) {
            super(context);
            this.context = context;
        }

        @Override
        public Collection<String> getRenderIds() {
            Collection<String> renderIds = new ArrayList(super.getRenderIds());
            Collection<UIPanelRefresh> refreshs = UIPanelRefresh.getPanelRefreshDeclared(context).values();
            for (UIPanelRefresh refresh : refreshs) {
                String id = refresh.getClientId();
                boolean auto = refresh.isAutoRefresh();
                //System.out.println(":::: " + auto +" : " + id + " IN " + renderIds);
                if (auto && !renderIds.contains(id)) {
                    renderIds.add(id);
                }
            }
            return renderIds;
        }
    }
}
