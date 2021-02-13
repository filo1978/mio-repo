
<c:if test="${msg!=null}">
	<div class="alert alert-danger">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Attenzione</strong> ${msg }
	</div>
</c:if>

<c:if test="${msgKO!=null}">
	<div class="alert alert-danger">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>Attenzione</strong> ${msgKO }
	</div>
</c:if>

<c:if test="${msgOK!=null}">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		<strong>${msgOK}</strong>
	</div>
</c:if>

<c:if test="${errors!=null&&!errors.isEmpty()}">
	<div class="alert alert-danger">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">&times;</button>
		ATTENZIONE: non &egrave; possibile procedere all'operazione richiesta
		a causa dei seguenti errori:
		<ul>
			<c:forEach var="error" items="${errors}">
				<li>${error}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>