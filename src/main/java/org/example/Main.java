package org.example;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");

        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        System.out.println("Configuring app with base dir: " + new java.io.File("./src/main/webapp/").getAbsolutePath() + "...");

        StandardContext ctx = (StandardContext) tomcat.addWebapp("", new java.io.File("./src/main/webapp/").getAbsolutePath());
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new org.apache.catalina.webresources.DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));

        ctx.setResources(resources);
        tomcat.enableNaming();
        tomcat.getConnector();

        try {
            System.out.println("Starting Tomcat...");
            tomcat.start();
        } catch (org.apache.catalina.LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();
    }
}