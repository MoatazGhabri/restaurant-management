<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Form</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="bg-gray-50 min-h-screen">
        <div class="container mx-auto px-4 py-8">
            <div class="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden p-6">
                <h1 class="text-2xl font-bold text-gray-800 mb-6 text-center">Place Your Order</h1>

                <form action="order" method="post" class="space-y-4">
                    <div>
                        <label for="snack" class="block text-sm font-medium text-gray-700 mb-1">Sandwich</label>
                        <select name="snack" id="snack" required
                            class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                            <option value="">-- Select a Sandwich --</option>
                            <c:forEach var="snack" items="${snacks}">
                                <option value="${snack.id}">${snack.name} - $${snack.price}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label for="content" class="block text-sm font-medium text-gray-700 mb-1">Sandwich Content</label>
                        <select name="content" id="content" required
                            class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                            <option value="">-- Select content --</option>
                            <c:forEach var="content" items="${contents}">
                                <option value="${content.id}">${content.name} - $${content.price}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <label for="supplement" class="block text-sm font-medium text-gray-700 mb-1">Supplement</label>
                        <select name="supplement" id="supplement" required
                            class="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                            <option value="">-- Select supplement --</option>
                            <c:forEach var="supplement" items="${supplements}">
                                <option value="${supplement.id}">${supplement.name} - $${supplement.price}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Submit Button -->
                    <div class="pt-2">
                        <button type="submit"
                            class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                            Confirm
                        </button>
                    </div>
                </form>

                <!-- Manage Orders Link -->
                <div class="mt-6 text-center">
                    <a href="orders" class="text-indigo-600 hover:text-indigo-800 text-sm font-medium">
                        Manage Orders
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>