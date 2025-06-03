<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!-- request는 내장 객체이기 떄문에 servlet과 마찬가지로 쓸 수 있다. -->
    <%
        String menuName = (String) request.getAttribute("menuName");
        int amount = (int) request.getAttribute("amount");
        int orderPrice = (int) request.getAttribute("orderPrice");
    %>

    <h3>주문하신 음식 : <%= menuName %></h3>
    <h3>주문하신 수량 : <%= amount %></h3>
    <h3>결제하실 최종 금액 : <%= orderPrice %></h3>
</body>
</html>
