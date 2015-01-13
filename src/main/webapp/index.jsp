<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <title>Test jsp</title>
  <link rel="stylesheet" type="text/css" href="css/style.css"/>
  <script src="js/jquery-1.11.2.js"></script>
  <script src="js/tablesorter.js"></script>
  <script>
  /* лучше $(function () {
    ...
  }).
  То же самое, но короче.
  */
    $(document).ready(function () {
              $("#myTable").tablesorter();
            }
    );
  </script>
</head>
<body>
<form method="post" name="create" accept-charset="utf-8">
  <input type="hidden" name="form" value="create">
  <fieldset>
    <legend>Create new:</legend>
    <div align="center">
      <label for="name">Product name:</label>
      <input type="text" id="name" name="name" >

      <label for="category_name">Category name:</label>
      <input type="text" id="category_name" name="category_name">

      <label for="price">Price:</label>
      <input type="text" id="price" name="price">
      <button type="SUBMIT">Add</button>
    </div>
  </fieldset>
</form>

<form method="post">
  <input type="hidden" name="form" value="table">
  <fieldset>
    <legend>Search:</legend>
    <div align="center">
      <label for="nameSearch">Product name:</label>
      <input type="text" id="nameSearch" name="name" value="${criterions['name']}">

      <label for="category_name_search">Category name:</label>
      <input type="text" id="category_name_search" name="category_name" value="${criterions['category_name']}">

      <label for="price_from">Price from:</label>
      <input type="text" id="price_from" name="price_from" value="${criterions['price_from']}">

      <label for="price_to">Price to:</label>
      <input type="text" id="price_to" name="price_to" value="${criterions['price_to']}">

      <button type="SUBMIT">Search</button>
    </div>
  </fieldset>
  <table id="myTable" class="tablesorter">
    <thead>
    <tr>
      <th>Category</th>
      <th>Name</th>
      <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="product">
      <tr>
        <td><c:out value="${product.categoryEntity.name}"></c:out></td>
        <td><c:out value="${product.name}"></c:out></td>
        <td><c:out value="${product.price}"></c:out></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</form>
</body>
</html>
