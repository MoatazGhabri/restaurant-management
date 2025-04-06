<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ticket Details</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8">
        <div class="max-w-2xl mx-auto bg-white rounded-lg shadow-md overflow-hidden">
            <div class="px-6 py-4">
                <div class="flex justify-between items-center mb-6">
                    <h1 class="text-2xl font-bold text-gray-800">Ticket #${ticket.id}</h1>
                    <span class="px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full
                        ${ticket.status == 'PENDING' ? 'bg-yellow-100 text-yellow-800' :
                          ticket.status == 'COMPLETED' ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-800'}">
                        ${ticket.status}
                    </span>
                </div>

                <div class="border-t border-gray-200 py-4">
                    <dl>
                        <div class="bg-gray-50 px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4">
                            <dt class="text-sm font-medium text-gray-500">Customer Name</dt>
                            <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">${ticket.customerName}</dd>
                        </div>
                        <div class="bg-white px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4">
                            <dt class="text-sm font-medium text-gray-500">Table Number</dt>
                            <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">${ticket.tableNumber}</dd>
                        </div>
                        <div class="bg-gray-50 px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4">
                            <dt class="text-sm font-medium text-gray-500">Created At</dt>
                            <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">${ticket.createdAt}</dd>
                        </div>
                        <div class="bg-white px-4 py-3 sm:grid sm:grid-cols-3 sm:gap-4">
                            <dt class="text-sm font-medium text-gray-500">Special Requests</dt>
                            <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                                ${empty ticket.specialRequests ? 'None' : ticket.specialRequests}
                            </dd>
                        </div>
                    </dl>
                </div>

                <div class="mt-6 flex justify-between">
                    <a href="${pageContext.request.contextPath}/tickets"
                       class="bg-gray-500 hover:bg-gray-600 text-white font-bold py-2 px-4 rounded">
                        Back to List
                    </a>
                    <form action="${pageContext.request.contextPath}/tickets/${ticket.id}/status" method="POST" class="inline">
                        <button type="submit"
                                class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded">
                            Mark as Complete
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>