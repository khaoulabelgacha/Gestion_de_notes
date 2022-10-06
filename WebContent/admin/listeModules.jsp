<%@ include file="menu.jsp" %> 

 <div class="workplace" >
            <div class="dr"><span></span></div>

            <div class="row-fluid">
                
                <div class="span12">                    
                    <div class="head clearfix" style=" margin-top: 56px;" >
                        <div class="isw-grid"></div>
                        <h1>Liste des Modules</h1>      
                                                
                          </div>



         <div class="block-fluid">
                        <table cellpadding="0" cellspacing="0" width="100%" class="table">
                            <thead>
                                <tr>                                    

									<th>Code</th>
									<th>Libele</th>
								 	<th>Filiere</th>
                                    
									
                                    <th colspan="2" style="text-align: center;">Option</th>                             
                               
                            </thead>
                            <tbody>
								<c:forEach  items="${listeModules }" var="module">
									<tr>
									
										<td>${module.code }</td>
										<td>${module.libele }</td>
								<%-- 		<td>${module.coefficient }</td> --%>
										<td>${module.filiere.libele }</td>
										<td><a href="supprimerModule?idModule=${module.id }">Supprimer module</a></td>
									</tr>
								</c:forEach>

                                                                   
                                                
                            </tbody>
                        </table>
                    
                </div>                                
                
            </div>            
            
    </div>
    </div> 
               
               
               
               
               