<%@ include file="menu.jsp" %> 

 <div class="workplace" >
            <div class="dr"><span></span></div>

            <div class="row-fluid">
                
                <div class="span12">                    
                    <div class="head clearfix" style=" margin-top: 56px;" >
                        <div class="isw-grid"></div>
                        <h1>Liste des Etudiants</h1>      
                                                
                          </div>



         <div class="block-fluid">
                        <table cellpadding="0" cellspacing="0" width="100%" class="table">
                            <thead>
                                <tr>                                    
                                  
									<th>CNE</th>
									<th>Nom</th>
									<th>Prenom</th>
									<th>Email</th>
									<th>Filiere</th>
                                    
									
                                    <th colspan="2" style="text-align: center;">Option</th>                             
                               
                            </thead>
                            <tbody>
								<c:forEach  items="${listeEtudiants }" var="etudiant">
									<tr>
											
											<td>${etudiant.cne }</td>
											<td>${etudiant.nom }</td>
											<td>${etudiant.prenom }</td>
											<td>${etudiant.email }</td>
											<td>${etudiant.filiere.libele }</td>
											<td><a href="supprimerEtudiant?idEtudiant=${etudiant.id }">Supprimer etudiant</a></td>
										
									</tr>
								</c:forEach>
                                                                   
                                                
                            </tbody>
                        </table>
                    
                </div>                                
                
            </div>            
            
    </div>
    </div> 
               
               
               
               
               