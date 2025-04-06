<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Order</title>
</head>
<body>
    <h1>Edit Order</h1>
    <form action="orders" method="post">
        <input type="hidden" name="id" value="${param.id}">

        <label>Ticket Number:</label>
        <input type="text" name="ticketNumber" value="${param.ticketNumber}" required><br>

        <label>Total Price:</label>
        <input type="number" step="0.01" name="totalPrice" value="${param.totalPrice}" required><br>

        <button type="submit">Update Order</button>
    </form>
</body>
</html>