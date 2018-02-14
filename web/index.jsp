<%@ page import="entity.Tag" %>
<%@ page import="java.util.*" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="entity.CustomArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <title>Title</title>
  <%
    if (application.getAttribute("list") == null) {
      ObjectMapper objectMapper = new ObjectMapper();
      CustomArrayList<Tag> customArrayList = new CustomArrayList<>();
      String list = objectMapper.writeValueAsString(customArrayList);
      application.setAttribute("list", list);
    }
  %>
</head>
<body class="container">
<div class="header">
  <% if (request.getAttribute("tags") != null) {
    List<Tag> tags = (List<Tag>) request.getAttribute("tags"); %>
  <div class="form-container">

    <table>
      <thead>
      <tr>
        <td>id</td>
        <td>name</td>
      </tr>
      </thead>
      <tbody>
      <%for (Tag tag : tags) {%>

      <tr>
        <td>
          <%=tag.getId()%>

        </td>
        <td>
          <%=tag.getName()%>

        </td>

      </tr>
      <%}%>
      </tbody>


    </table>
  </div>
  <%
  } else if (request.getAttribute("message") != null) { %>
  <%=request.getAttribute("message")%>
  <%
    }
  %>
</div>

<div>
  <ul>
    <li>
      <form method="post" action="/tagadd">
        <input type="text" name="id" placeholder="Введите id">
        <input type="text" name="name" placeholder="Введите name">
        <input type="submit" value="добавить объект"><br>
      </form>
    </li>
    <li>
      <form method="post" action="/tags">
        <input type="text" name="count" placeholder="Введите количество объектов к показу">
        <input type="submit" value="Показать объекты"><br>
      </form>
    </li>
    <li>
      <form method="post" action="/tagdelete">
        <input type="text" name="id" placeholder="Введите id">
        <input type="submit" value="Удалить объект">
      </form>
    </li>
    <li>
      <form method="post" action="/tagupdate">
        <input type="text" name="id" placeholder="Введите id">
        <input type="text" name="name" placeholder="Введите name">
        <input type="submit" value="Обновление по id">
      </form>
    </li>
    <li>
      <form method="post" action="/tagname">
        <input type="text" name="id" placeholder="Введите id">
        <input type="submit" value="Получить имя">
      </form>
    </li>
    <li>
      <form method="post" action="/tagsearch">
        <input type="text" name="word" placeholder="поиск от 3 символов">
        <input type="text" name="count" placeholder="Введите количество объектов к показу">
        <input type="submit" value="поиск">
      </form>
    </li>

  </ul>
</div>
</body>
</html>
