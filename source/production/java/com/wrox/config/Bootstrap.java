package com.wrox.config;

//import org.apache.jasper.servlet.JspServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@SuppressWarnings("unused")
public class Bootstrap implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext container) throws ServletException
    {
        //container.getServletRegistration("default").addMapping("/resource/*");

        addStaticHtmlFilesHandlingServlet(container);
        
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext webContext =
                new AnnotationConfigWebApplicationContext();
        webContext.register(WebServletContextConfiguration.class);
        ServletRegistration.Dynamic dispatcher = container.addServlet(
                "springWebDispatcher", new DispatcherServlet(webContext)
        );
        dispatcher.setLoadOnStartup(1);
        dispatcher.setMultipartConfig(new MultipartConfigElement(
                null, 20_971_520L, 41_943_040L, 512_000
        ));
        dispatcher.addMapping("/");

        AnnotationConfigWebApplicationContext restContext =
                new AnnotationConfigWebApplicationContext();
        restContext.register(RestServletContextConfiguration.class);
        DispatcherServlet servlet = new DispatcherServlet(restContext);
        servlet.setDispatchOptionsRequest(true);
        dispatcher = container.addServlet(
                "springRestDispatcher", servlet
        );
        dispatcher.setLoadOnStartup(2);
        dispatcher.addMapping("/services/Rest/*");
    }
    
    private void addStaticHtmlFilesHandlingServlet(ServletContext servletContext) {
    	
    	//servletContext.getServletRegistration("default").addMapping("/static/*", "*.html", "*.js");
    	servletContext.getServletRegistration("default").addMapping("*.html", "*.js", "*.css");
    	System.out.println("Mappings = " + servletContext.getServletRegistration("default").getMappings());
    	/*
        ServletRegistration.Dynamic servlet = servletContext.addServlet("HtmlsServlet", new JspServlet()); // org.apache.jasper.servlet.JspServlet
        servlet.setLoadOnStartup(1);
        servlet.addMapping("*.html");
        */
     }
}
