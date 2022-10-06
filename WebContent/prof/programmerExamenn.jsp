
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

Examen du Module : 
<b>
${ utilisateurConnecte .module.libele }
</b>

<br/><br/>
Specialité : 
${ utilisateurConnecte .module.filiere.libele }

<br/><br/><br/>

<form method="post" action="programmerExamen">

<div class="form-group">
	<label>Date Examen</label>
	<input class="form-control" type="text" name="dateExamen"  placeholder="mm/dd/YYYY" />
</div>


<div class="form-group">
	<label>coefficient</label>
	<input  class="form-control"  type="text" name="coefficient" />
</div>

<div class="form-group">
	<input type="submit" value="ajouter" />
</div>

</form>


</div>