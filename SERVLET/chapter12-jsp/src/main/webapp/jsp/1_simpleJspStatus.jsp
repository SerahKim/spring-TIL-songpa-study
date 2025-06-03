<!-- 1. 페이지 지시자 태그 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<!-- 2. jsp 주석 태그 -->
<%-- html 주석은 클라이언트 노출되지만, jsp 주석은 클라이언트에게 노출되지 않는다. --%>

<!-- 3. 선언 태그 -->
<!-- 서블릿으로 변환 시 선언 태그 내에 작성한 내용을 필드로 선언한다. -->
<%!
  private String name;
  private int age;
%>

<!-- 4. scriptlet 태그 -->
<%
  // 간단한 자바 코드를 작성할 수 있는 부분이다.
  name = "홍길동";
  age = 20;

  System.out.println("name = " + name);
  System.out.println("age = " + age);

  for(int i = 0; i < name.length(); i++) {
    System.out.println(name.charAt(i));
  }
%>
<!-- 5. expression 태그 -->
<!-- PrintWriter를 이용하여 브라우저에 값을 내보내 브라우저에서 보여지게 한다. -->
<!-- 자바 코드로 변환 시 out.print(); 괄호 안에 expression 태그 내에 작성한 내용이 들어간다.
     정상적으로 출력한 값을 작성하는 경우 out.print(name); 이런 식으로 표현되는데
     expression 태그 내에 세미콜론을 작성하면 out.print(name;); 형태가 되므로 compile 에러가 발생한다.
-->
name : <%= name %><br>
age : <%= age %>
</body>
</html>