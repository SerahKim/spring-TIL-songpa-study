<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>include directive</h1>
  <!--
  include 지시자 태그를 이용하여 file 속성에 jsp 경로를 지정하면
  해당 Jsp에 작성한 내용을 그대로 포함시켜 현재 jsp 파일을 동작시킨다.
  따라서 동일한 변수 이름을 include 이후에 또 사용하면 한 페이지 내에 동일한 변수가 생성되는 것이므로
  컴파일 에러가 발생한다.
  -->
  <div><%@ include file="today.jsp" %></div>
  <%
    // 동일한 변수 이름을 사용했기 때문에 컴파일 에러가 발생한다.
//    String output = "";
  %>
</body>
</html>
