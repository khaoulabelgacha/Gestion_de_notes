

<%@ include file="../header.jspf" %> 



<div class="icon-bar" >
	<c:set var="active" value="" />
  <a class="${ fn:contains(pageContext.request.requestURI, 'index') ? 'active' : 'none'}" href="index"><i >Acceuil</i></a>
  <a class="${ fn:contains(pageContext.request.requestURI, 'programmerExamen') ? 'active' : 'none'}" href="programmerExamen" style="width : 50%;"><i >Programmer Exam</i></a>
  <a class="none" href="logout"><i>Logout</i></a>
</div> 

<center>Bonjour Mr ${ utilisateurConnecte.nom } ${ utilisateurConnecte.prenom }</center>
<br/>

<center>Module enseigné :  ${ utilisateurConnecte.module.libele }</center>
<br/><br/>

<div class="body">

<h3>Exam ${listeNotes.get(0).exam.id}  ${listeNotes.get(0).exam.dateExam}</h3>


 <div class="table-responsive">


  <table class="table">
	
<tr>
	<th>CNE</th>
	<th>Nom</th>
	<th>Note</th>
	<th>Action</th>
	
</tr>

<c:forEach items="${listeNotes}" var="note">
	<form method="post" action="donnerNotes">
	<tr>
		<td>${note.etudiant.cne } </td>		
		<td>${note.etudiant.nom } ${note.etudiant.prenom } </td>
		<td><input type="text" name="note" value="${note.note}"/></td>
		
		<input type="hidden" name="idEtudiant" value="${note.etudiant.id}"/>
		<input type="hidden" name="idExam" value="${note.exam.id}"/>
		<td><input type="submit" value="Enregistrer" /></td>
	</tr>
	</form>

</c:forEach>

</table>
</div>


<%-- <c:forEach items="${listeEtudiants }" var="etudiant"> --%>
<!-- 	<form method="post" action=""> -->
<%-- 		${etudiant.cne } ${etudiant.nom } :  --%>
<!-- 		<input type="text" name="note" value=""/> -->
<%-- 		<input type="hidden" name="idEtudiant" value="Enregistrer?idEdutiant=${etudiant.id}"/> --%>
<!-- 		<input type="submit" value="Enregistrer" /> -->
<!-- 		<br/> -->
<!-- 	</form> -->
<%-- </c:forEach> --%>



</div>