<%@ include file="menu.jsp" %> 

 <div class="workplace" >
            <div class="dr"><span></span></div>

            <div class="row-fluid">
                
                <div class="span12">                    
                    <div class="head clearfix" style=" margin-top: 56px;" >
                        <div class="isw-grid"></div>
                        <h1>Liste des Filieres</h1>      
                                                
                          </div>



         <div class="block-fluid">
                        <table cellpadding="0" cellspacing="0" width="100%" class="table table-striped">
                            <thead>
                                <tr>                                    
 
									<th>Code</th>
									<th>Libele</th>
 
                                    <th  style="text-align: center;">Option</th>                             
                               
                            </thead>
                            <tbody>
								<c:forEach  items="${listeFilieres }" var="filiere">
									<tr>
										
										<td>${filiere.code }</td>
										<td>${filiere.libele }</td>
										<td><a href="supprimerFiliere?idFiliere=${filiere.id }">Supprimer filiere</a></td>
									</tr>
								</c:forEach>

                                                                   
                                                
                            </tbody>
                        </table>
                    
                </div>                                
                
            </div>            
            
    </div>
    </div> 
               
               
               
               
               