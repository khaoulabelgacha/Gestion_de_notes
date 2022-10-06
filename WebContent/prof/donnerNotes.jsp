<%@ include file="menu.jsp" %> 

 <div class="workplace" >
            <div class="dr"><span></span></div>

            <div class="row-fluid">
                
                <div class="span12">                    
                    <div class="head clearfix" style=" margin-top: 56px;" >
                        <div class="isw-grid"></div>
                        <h1>Donner Notes</h1>      
                                                
                          </div>



         <div class="block-fluid">
                        <table cellpadding="0" cellspacing="0" width="100%" class="table">
                            <thead>
                                <tr>                                    
 	<th>CNE</th>
	<th>Nom</th>
	<th>Note</th>
  
                                    
									
                                    <th  style="text-align: center;">Option</th>                             
                               
                            </thead>
                            <tbody>
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
                                                                   
                                                
                            </tbody>
                        </table>
                    
                </div>                                
                
            </div>            
            
    </div>
    </div> 
               
               
               
               
               