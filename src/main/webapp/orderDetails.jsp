<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 text-gray-800 font-sans">

    <div class="max-w-3xl mx-auto p-6 mt-10 bg-white shadow-md rounded-lg">
        <h1 class="text-2xl font-bold mb-4 text-center">Order Details</h1>

        <c:if test="${not empty order}">
            <div class="mb-6">
                <p><span class="font-semibold">Ticket #:</span> ${order.ticketNumber}</p>
                <p><span class="font-semibold">Date:</span> <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm" /></p>
                <p><span class="font-semibold">Total:</span> <fmt:formatNumber value="${order.totalPrice}" type="currency" /></p>
            </div>

            <h3 class="text-lg font-semibold mb-2">Items:</h3>

            <c:choose>
                <c:when test="${not empty order.items}">
                    <div class="overflow-x-auto">
                        <table class="min-w-full bg-white border border-gray-300 rounded-lg">
                            <thead>
                                <tr class="bg-gray-100 text-left text-sm font-medium text-gray-600">
                                    <th class="py-2 px-4 border-b border-gray-200">Type</th>
                                    <th class="py-2 px-4 border-b border-gray-200">Name</th>
                                    <th class="py-2 px-4 border-b border-gray-200">Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${order.items}">
                                    <tr class="text-sm text-gray-700 hover:bg-gray-50">
                                        <td class="py-2 px-4 border-b border-gray-100">${item.itemType}</td>
                                        <td class="py-2 px-4 border-b border-gray-100">${item.itemName}</td>
                                        <td class="py-2 px-4 border-b border-gray-100">
                                            <fmt:formatNumber value="${item.itemPrice}" type="currency" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="text-sm text-gray-600">No items found for this order</p>
                </c:otherwise>
            </c:choose>
        </c:if>

        <div class="mt-6 text-center">
            <a href="orders" class="text-blue-600 hover:underline font-medium">← Back to Orders</a>
        </div>
    </div>

</body>
</html>
