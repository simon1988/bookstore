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
   	    	<c:if test="${username==null}"><a href="/login">Login</a></c:if>
	   	    <c:if test="${username!=null}">Welcome, ${username}</c:if>
	   	</li>
	   	<li class="fore2">
	   	    <a href="/register">Register</a>
   	    </li>
        <li class="spacer"></li>
	    <li class="fore3">
            <a href="/orders">My Orders</a>
        </li>
        <li class="spacer"></li>
	    <li class="fore4">
            <a href="/cart">My Cart</a>
        </li>
    	</ul>
    </div>
</div>