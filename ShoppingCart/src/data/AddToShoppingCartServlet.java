package data;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tools.DBUtil;

/**
 * Servlet implementation class AddToShoppingCartServlet
 */
@WebServlet("/AddToShoppingCartServlet")
public class AddToShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String productCode = request.getParameter("productCode");
	        String description = request.getParameter("description");
	        int quantity = Integer.parseInt(request.getParameter("quantity"));
	        double price = Double.parseDouble(request.getParameter("price"));
	       /*EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "SELECT p from Product p WHERE p.productCode = :prodCode";
			TypedQuery<Item> q = em.createQuery(qString, Item.class);
			q.setParameter("prodCode", productCode); */

	// Now create an item to add to the cart.
	        Item item = new Item(productCode, description, price, quantity);

	        HttpSession session = request.getSession();

	// Get the cart.
	        ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

	// If there is no shopping cart, create one.
	        if (cart == null)
	        {
	            cart = new ShoppingCart();

	            session.setAttribute("ShoppingCart", cart);
	        }

	        cart.addItem(item);

	// Now display the cart and allow the user to check out or order more items.
	        response.sendRedirect(response.encodeRedirectURL(
	            "/ShoppingCart/ViewShoppingCart.jsp"));
	    }
	}


