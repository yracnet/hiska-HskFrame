hiska-HskFrame
==============

Sample web application using Java Server Faces and Popup / IFrame navegation JSF2.0 + POPUP / IFRAME java


Hsk - Frame Navegation - Java Server Faces navegation into iframe an return to parent page
<h5>
Please, help me to test this component for JSF 2.0, 2.1 and 2.2
</h5>

<p>
CommandLink with arget can open a iframe(name reference) and navegate in this iframe until, the case negation toViewId is equals to ViewId caller.
</p>
<p>
Idea: uses for opene finder page for select a parameter's, this can share paremeters in SESSION SCOPE and FLASH SCOPE.
</p>
<ol>
<li>index.xhtml : neet 2 params then click "open" link, and define targer attribure</li>
<li>index.xhtml : automatic ajax-refresh to hsk:panelFrame with name equals to target attribute of command link invoked</li>
<li>hsk:panelFrame : create a basic estructure for include a iframe in the page, with a simple javascript.(you can modify this estructure for your cusstom project)</li>
<li>hsk:panelFrame/java script : detected a this navegation-case is equals to parent opener (window.parent/window.top).
<ol>
<li>TRUE: close the frame an call to ajax event defined in the commandLink invoked( parent.currentViewId and parent.eventAjax )</li>
<li>FALSE: open the iframe with url equals to navegation-case, and define 2 methods</li>
<li> Method 1: currentViewId(), define the viewId caller to iframe (view.viewId)</li>
<li> Method 2: eventAjax(), define a hack script to simulete click in the commandLink invoked</li>
</ol>
</li>
</ol>

<p>The HskFrame is a java project for lib the hsk:panelFrame and has all configuration and class for use-alone</p>
<p>The HskTest  is a java-web project for test the hsk-jsf-frame_1.5.jar in a simple test, when we need get 2 parameters (session, flash scope) from the popupX.xhtml pages to parent page (main is index.xhtml)</p>
