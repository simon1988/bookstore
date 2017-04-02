<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="shortcut-2014">
    <div class="w">
    	<ul class="fl">
   	    <li class="fol1">
	   	    <a href="/index">Mainpage</a>
	   	</li>
	   	</ul>
    	<ul class="fr">
   	    <li class="fore1">
   	    	<c:if test="${username==null}"><a href="/login?fromurl=${currentURI}">Login</a></c:if>
	   	    <c:if test="${username!=null}">Welcome, <a href="/user/${username}">${username}</a></c:if>
	   	</li>
	   	<c:if test="${username==null}">
		   	<li class="fore2">
		   	    <a href="/register">Register</a>
	   	    </li>
   	    </c:if>
        <li class="spacer"></li>
	    <li class="fore3">
            <a href="/orders/${username}">My Orders</a>
        </li>
        <li class="spacer"></li>
	    <li class="fore4">
            <a href="/cart/${username}">My Cart</a>
        </li>
    	</ul>
    </div>
</div>