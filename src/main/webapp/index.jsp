<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Restaurant Ticket System</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 min-h-screen flex flex-col items-center justify-center">
    <div class="max-w-md mx-auto text-center p-8 bg-white rounded-lg shadow-lg">
        <h1 class="text-3xl font-bold text-gray-800 mb-6">Welcome to Our Restaurant</h1>

        <div class="space-y-4">
            <a href="order"
               class="inline-block w-full px-6 py-3 bg-indigo-600 text-white font-medium rounded-lg shadow-md hover:bg-indigo-700 transition duration-300">
               Place an Order
            </a>

            <a href="login"
               class="inline-block w-full px-6 py-3 border-2 border-indigo-600 text-indigo-600 font-medium rounded-lg hover:bg-indigo-50 transition duration-300">
               Admin Login
            </a>
        </div>

        <div class="mt-8">
            <img src="https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"
                 alt="Restaurant interior"
                 class="w-full h-48 object-cover rounded-lg">
        </div>
    </div>
</body>
</html>