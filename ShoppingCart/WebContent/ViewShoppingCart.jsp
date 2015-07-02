<%@ page language="java" import="data.*,java.util.*,java.text.*" %>

<%-- Show the header with the shopping cart image --%>
<body><a href="/ShoppingCart/ShowProductCatalog.jsp">continue shopping</a></body>
<table border="0">
<tr><td><img src="cart4.png"><td><h1>Shopping Cart</h1>
</table>

<%
// Get the current shopping cart from the user's session.
    ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

// If the user doesn't have a shopping cart yet, create one.
    if (cart == null)
    {
        cart = new ShoppingCart();
        session.setAttribute("ShoppingCart", cart);
    }

// Get the items from the cart.
    Vector items = cart.getItems();


// If there are no items, tell the user that the cart is empty.
    if (items.size() == 0)
    {
        out.println("<h3>Your shopping cart is empty.</h3>");
    }
    else
    {
%>
<%-- Display the header for the shopping cart table --%>
<br>
<table border=4>
<tr><th>Description</th><th>Quantity</th><th>Price</th></tr>

<% 
        int numItems = items.size();

// Get a formatter to write out currency values.
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        for (int i=0; i < numItems; i++)
        {
            Item item = (Item) items.elementAt(i);

// Print the table row for the item.
            out.print("<tr><td>");
            out.print(item.description);
            out.print("</td><td>");
            out.print(item.orderQuantity);
            out.print("</td><td>");
            out.print(currency.format(item.price));

// Print out a link that allows the user to delete an item from the cart.
            out.println("</td><td>"+
                "<a href=\"/ShoppingCart/CheckoutServlet?cart="+
                i+"\">Confirm</a></td></tr>");
          
        }
    }
%>
</table>