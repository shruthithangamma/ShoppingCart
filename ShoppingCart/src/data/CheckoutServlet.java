package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// First get the shipping values from the request.
        Shipping shipping = new Shipping();

        shipping.setName(request.getParameter("name"));
        shipping.setAddress1(request.getParameter("address1"));
        shipping.setAddress2(request.getParameter("address2"));
        shipping.setCity(request.getParameter("city"));
        shipping.setState(request.getParameter("state"));
        shipping.setPostalCode(request.getParameter("postalCode"));
        shipping.setCountry(request.getParameter("country"));
        shipping.setEmail(request.getParameter("email"));

// Next, get the billing values.
        Billing billing = new Billing();

        billing.setNameOnCard(request.getParameter("nameOnCard"));
        billing.setCreditCardType(request.getParameter("creditCardType"));
        billing.setCreditCardNumber(request.getParameter(
            "creditCardNumber"));
        billing.setCreditCardExpiration(request.getParameter(
            "creditCardExpiration"));

        HttpSession session = request.getSession();

// Get the cart.
        ShoppingCart cart =
            (ShoppingCart) session.getAttribute("ShoppingCart");

// If there is no shopping cart, create one (this should really be an error).
        if (cart == null)
        {
            cart = new ShoppingCart();

            session.setAttribute("ShoppingCart", cart);
        }

        try
        {
            String confirmation = cart.completeOrder(shipping, billing);

// Now display the cart and allow the user to check out or order more items.
            response.sendRedirect(response.encodeRedirectURL(
                "/ShoppingCart/ShowConfirmation.jsp"+
                "?confirmationNumber="+confirmation));
        }
        catch (ShoppingCartException exc)
        {
            PrintWriter out = response.getWriter();

            out.println("<html><body><h1>Error</h1>");
            out.println("The following error occurred while "+
                "processing your order:");
            out.println("<pre>");
            out.println(exc.getMessage());
            out.println("</pre>");
            out.println("</body></html>");
            return;
        }
	}

}
