package com.webstart.algo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Servlet implementation class GraphServlet
 */
@WebServlet("/GraphServlet")
public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GraphServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            URL fileUrl = getClass().getResource(request.getContextPath()+"WebContent/JS/calc.js");              
            //engine.eval(Files.newBufferedReader(Paths.get("E:/Projects/webStart/WebStartApp/WebContent/JS/jquery.js"), StandardCharsets.UTF_8));
            engine.eval(Files.newBufferedReader(Paths.get("E:/Projects/webStart/WebStartApp/WebContent/JS/springy.js"), StandardCharsets.UTF_8));
            //engine.eval(Files.newBufferedReader(Paths.get("E:/Projects/webStart/WebStartApp/WebContent/JS/springyui.js"), StandardCharsets.UTF_8));
            //engine.eval("load('" + request.getContextPath()+"/WebContent/JS/calc.js" + "')");
            engine.eval(Files.newBufferedReader(Paths.get("E:/Projects/webStart/WebStartApp/WebContent/JS/calc.js"), StandardCharsets.UTF_8));
            Invocable inv = (Invocable)engine;                 

            /*Object calculator = engine.get("calculator");
            Object addResult = inv.invokeMethod(calculator, "add", 3, 4);
            System.out.println(addResult);*/
            String script = "var graph = new Springy.Graph();";
            engine.eval(script);
            Object obj = engine.get("graph");
            script = "var michael = graph.newNode({label: \'Michael\'});";
            engine.eval(script);
            script = "var jessica = graph.newNode({label: \'Jessica\'});";
            engine.eval(script);
            script = "var timothy = graph.newNode({label: \'Timothy\'});";
            engine.eval(script);
            script = "graph.newEdge(michael, jessica, {color: \'#CC333F\', weight: \'1\'});";
            engine.eval(script);
            script = "graph.newEdge(michael, timothy, {color: \'#EDC951\'});";
            engine.eval(script);
            
			/*RequestDispatcher rd = getServletContext().getRequestDispatcher("/HTMLs/Graph2.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>I Got it.</font>");
			rd.include(request, response);*/
            response.sendRedirect(request.getContextPath()+"/HTMLs/Graph2.html");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}
