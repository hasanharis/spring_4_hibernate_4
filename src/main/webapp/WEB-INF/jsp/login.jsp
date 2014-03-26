<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <%@ include file="/WEB-INF/jsp/components/include.jsp" %>
    <title>Mera Mobile Celebrity</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="${contextPath }/css/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath }/css/style.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link href="${contextPath }/css/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->

    <script src="${contextPath }/js/jquery-1.7.1.min.js"></script>
    <script src="${contextPath }/css/bootstrap/js/bootstrap.js"></script>
  </head>

  <body>

    <jsp:include page="/header"></jsp:include>

    <div class="container-fluid">
      <div class="row-fluid">
        <jsp:include page="/menu"></jsp:include>
        
        <c:choose>
        	<c:when test="${user!=null }">
        		<c:set var="spanUnit" value="9"/>
        	</c:when>
        	<c:otherwise>
        		<c:set var="spanUnit" value="12"/>
        	</c:otherwise>
        </c:choose>
        
        <div class="span${spanUnit }">
          <div class="row-fluid">
            <form action="j_spring_security_check" method="post">
				<legend>Login</legend>	
					<c:if test="${param.error==1 }">
						<p class="alert alert-block alert-error fade in">Invalid credentials</p>
					</c:if>
					<label>Email Address</label>
					<input type="text" name="j_username"/>
					
					<label>Password:</label>
					<input type="password" name="j_password"/>
					
					<label class="checkbox">
						<input  type="checkbox" name="_spring_security_remember_me"/> Remember me
					</label>					
					
					<input type="submit" value="Login" class="btn btn-primary"/>
				</form>
				
            <%--
            <p><a class="btn btn-primary btn-large" href="${contextPath }/login">Login &raquo;</a></p>
             --%>
          </div>
        </div><!--/span-->
      </div><!--/row-->

      <hr>
      
      <jsp:include page="/footer"></jsp:include>
    </div><!--/.fluid-container-->

  </body>
</html>