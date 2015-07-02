<%@ page language="java" import="data.*,java.net.*,java.text.*" %>
<html>
<body bgcolor="#ffffff">

<%
// Initialize the array of available products.
    Item[] catalog = new Item[] {
        new Item("X-1", "Widgets", 2999999.95, 5),
        new Item("GWU-123876345-27B/6",
            "Gizmos", 12000.00, 3),
        new Item("BCT-12", "DooHickeys", 3700.00, 3),
        new Item("EZ-1", "Gadgets",
            699.95, 4)};

%>

<!-- <a href="/ShoppingCart/ViewShoppingCart.jsp">View Shopping Cart</a> -->
<p>
<h1>Available Products</h1>
<table border="1">
<tr><th>Description</th><th>Quantity</th><th>Price</th></tr>
<%

// Get a currency formatter for showing the price.
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    for (int i=0; i < catalog.length; i++)
    {
        Item item = catalog[i];

// Create the URL for adding the item to the shopping cart.
        String addItemURL =
            "/ShoppingCart/AddToShoppingCartServlet?"+
            "productCode="+item.getProductCode()+
            "&description="+item.getDescription()+
            "&quantity="+item.getQuantity()+
            "&price="+item.getPrice();
%>
<tr><td><%=item.getDescription()%></td><td><%=item.getQuantity()%>
    </td><td><%=item.getPrice()%></td>
<td><a href="<%=addItemURL%>">Add to Shopping Cart</a></td></tr>
<%
    }
%>
</table>
</body>
</html>