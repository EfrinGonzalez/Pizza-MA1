package dk.itu.pizza.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


import dk.itu.pizza.model.Ingrediente;
import dk.itu.pizza.model.Pizza;
import dk.itu.pizza.model.Pizzas;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		List<Pizza> pizza = new ArrayList<Pizza>();		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		
		
		try {
				InputStream stream = getServletContext().getResourceAsStream("/WEB-INF/Schema_Xml/pizzas.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Pizzas.class);
				Pizzas pizzasRoot = (Pizzas) jaxbContext.createUnmarshaller().unmarshal(stream);
				
				ListIterator<Pizza> listIterator = pizzasRoot.getPizza().listIterator();
				
				while(listIterator.hasNext()){
					pizza.add(listIterator.next());
				}
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		request.setAttribute("pizzasList", pizza);
		request.getRequestDispatcher("listPizzas.jsp").forward(request, response);	
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
