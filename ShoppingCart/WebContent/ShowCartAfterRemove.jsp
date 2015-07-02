<%@ page language="java"  contentType="text/html; charset=ISO-8859-1" import="data.*,java.util.*,java.text.*" %>
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <a href="/ShoppingCart/ShowProductCatalog.jsp">Continue Shopping</a> 
<table border=4>
<tr><th>Description</th><th>Quantity</th><th>Price</th></tr>
<%
ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

       Vector items = cart.getItems();

        int numItems = items.size();

// Get a formatter to write out currency values.
        NumberFormat currency = NumberFormat.getCurrencyInstance();
   
        for (int i=0; i < numItems; i++)
        {
        	if(i<numItems){
        		Item item = (Item) items.elementAt(i);
        		// Print the table row for the item.
                out.print("<tr><td>");
                out.print(item.description);
                out.print("</td><td>");
                out.print(item.orderQuantity);
                out.print("</td><td>");
                out.print(currency.format(item.price));
                
        	}
        	else
        		out.print("Shopping cart is empty");
          
        	 out.println("<a href=\"/ShoppingCart/RemoveItemServlet?item="+
                     i+"\">Remove</a></ br>");
        	 
        	 out.println("<a href=\"/ShoppingCart/CheckoutServlet?cart="+
                     i+"\">Confirm</a>");

        }
    
%>
</table>
</body>
</html>