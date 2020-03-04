<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Food Delivery</title>
<link href="${contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<nav>
      <ul>
        <button>X</button>
        <li>
          <a href="">Become a driver</a>
        </li>
        <li>
          <a href="">Login</a>
        </li>
        <li>
          <a href="${contextPath}/signup">Sign up</a>
        </li>
      </ul>
    </nav>
	<div class="container">
	</div>
	<script type="text/javascript">
    /* only execute this script when the document is ready */
    $(document).ready(function() {
      /* function called when you click of the button */
      $("button").click(function() {
        /* this if else to change the text in the button */
        if ($("button").text() == "☰") {
          $("button").text("🞬");
        } else {
          $("button").text("☰");
        }

        /* this function toggle the visibility of our "li" elements */
        $("li").toggle("slow");
      });
    });
  </script>
</body>
</html>