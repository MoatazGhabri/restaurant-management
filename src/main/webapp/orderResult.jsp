<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Ticket</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style type="text/tailwindcss">
        @layer utilities {
            .dotted-border {
                border-bottom: 2px dotted #000;
            }
        }
    </style>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center p-4">
    <c:choose>
        <c:when test="${not empty order}">
            <!-- Ticket Container -->
            <div class="bg-white border-2 border-black rounded-lg shadow-lg w-full max-w-md p-6">
                <!-- Ticket Header -->
                <div class="text-center border-b-2 border-dotted pb-4 mb-4">
                    <h2 class="text-2xl font-bold text-gray-800">Restaurant Ticket</h2>
                    <div class="space-y-1 mt-2">
                        <p class="text-gray-600">
                            <span class="font-semibold">Ticket #:</span> ${order.ticketNumber}
                        </p>
                        <p class="text-gray-600">
                            <span class="font-semibold">Date:</span>
                            <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm" />
                        </p>
                        <p class="text-gray-600">
                            <span class="font-semibold">Admin:</span> ${admin.fullName}
                        </p>
                    </div>
                </div>

                <!-- Order Items -->
                <div class="space-y-3">
                    <c:set var="snack" value="" />
                    <c:set var="content" value="" />
                    <c:set var="comboPrice" value="0" />

                    <c:forEach var="item" items="${order.items}">
                        <c:choose>
                            <c:when test="${item.itemType == 'SNACK'}">
                                <c:set var="snack" value="${item.itemName}" />
                                <c:set var="comboPrice" value="${comboPrice + item.itemPrice}" />
                            </c:when>
                            <c:when test="${item.itemType == 'CONTENT'}">
                                <c:set var="content" value="${item.itemName}" />
                                <c:set var="comboPrice" value="${comboPrice + item.itemPrice}" />
                            </c:when>
                        </c:choose>
                    </c:forEach>

                    <!-- Main Item -->
                    <div class="flex justify-between">
                        <span class="font-medium">${snack} ${content}</span>
                        <span><fmt:formatNumber value="${comboPrice}" type="currency" /></span>
                    </div>

                    <%-- Supplements --%>
                    <c:forEach var="item" items="${order.items}">
                        <c:if test="${item.itemType == 'SUPPLEMENT'}">
                            <div class="flex justify-between text-gray-700">
                                <span>${item.itemName}</span>
                                <span><fmt:formatNumber value="${item.itemPrice}" type="currency" /></span>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

                <!-- Total -->
                <div class="flex justify-between font-bold text-lg mt-4 pt-4 border-t-2 border-black">
                    <span>Total:</span>
                    <span><fmt:formatNumber value="${order.totalPrice}" type="currency" /></span>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <!-- Error State -->
            <div class="bg-red-50 border-l-4 border-red-500 p-4 max-w-md">
                <div class="flex">
                    <div class="flex-shrink-0">
                        <svg class="h-5 w-5 text-red-500" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                            <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clip-rule="evenodd" />
                        </svg>
                    </div>
                    <div class="ml-3">
                        <h2 class="text-lg font-medium text-red-800">Error: No order data found</h2>
                        <p class="mt-1 text-sm text-red-700">Please check the server logs for more information</p>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <!-- Action Button -->
    <div class="fixed bottom-8 left-0 right-0 flex justify-center">
        <a href="order" class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
            Place Another Order
        </a>
    </div>
</body>
</html>