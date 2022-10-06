<%@ include file="menu.jsp" %> 

 <div class="workplace" >
            <div class="dr"><span></span></div>

            <div class="row-fluid">
                
                <div class="span12">                    
                    <div class="head clearfix" style=" margin-top: 56px;" >
                        <div class="isw-grid"></div>
                        <h1>Liste des Professeurs</h1>      
                                                
                          </div>



         <div class="block-fluid">
                        <table cellpadding="0" cellspacing="0" width="100%" class="table">
                            <thead>
                                <tr>                                    

									<th>Nom</th>
									<th>Prenom</th>
									<th>Email</th>
									<th>Module</th>
                                     
									
                                    <th colspan="2" style="text-align: center;">Option</th>                             
                               
                            </thead>
                            <tbody>
								<c:forEach  items="${listeProfs }" var="prof">
									<tr>
										
										<td>${prof.nom }</td>
										<td>${prof.prenom }</td>
										<td>${prof.email }</td>
										<td>${prof.module.libele }</td>
										<td><a href="supprimerProf?idProf=${prof.id }">Supprimer prof</a></td>
									</tr>
								
								
								</c:forEach>
                                                                   
                                                
                            </tbody>
                        </table>
                    
                </div>                                
                
            </div>            
            
    </div>
    </div> 
               
               
               
               
               