
<%@ include file="/header.jspf" %> 


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
	<b>Liste des exams :</b>
	<br/>	<br/>
	
	
<ul>
	<c:forEach items="${listeExams }" var="exam"> 
	<li> Exam ${ exam.id } =>	${ exam.dateExam }
	<a href="donnerNotes?idExam=${ exam.id }">Donner notes</a>
	</li>
	</c:forEach>
	
</ul>
	
	
	<a class="btn btn-success" href="programmerExamen">programmer Examen</a>



</div>


