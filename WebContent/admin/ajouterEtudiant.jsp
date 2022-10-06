

<%@ include file="menu.jsp" %> 

<div class="workplace">
               <div class="dr"><span></span></div>
                
             <div class="span6">
                    <div class="head clearfix" style="width: 718px;margin-left: 141px; margin-top: 60px;" >
                        <div class="isw-plus"></div>
                        <h1>Ajouter Etudiant</h1>
                    </div>
                    <div class="block-fluid" style="width: 718px;margin-left: 141px;">                        
                    <form method="post" action="ajouterEtudiant">

                          <div class="row-form clearfix">
                            <div class="span9">
                            <label for="cin">CNE:</label>        
                             <input value="" class="validate[required,maxSize[60]]" type="text" name="cne" id="titre"/>
                             </div>
                           </div>
                           
                           <div class="row-form clearfix">
                            <div class="span9">
                            <label for="cin">Nom:</label>        
                             <input value="" class="validate[required,maxSize[60]]" type="text" name="nom" id="titre"/>
                             </div>
                           </div>
                           
                           <div class="row-form clearfix">
                            <div class="span9">
                            <label for="cin">Prenom:</label>        
                             <input value="" class="validate[required,maxSize[60]]" type="text" name="prenom" id="titre"/>
                             </div>
                           </div>
                           
                           <div class="row-form clearfix">
                            <div class="span9">
                            <label for="cin">Email:</label>        
                             <input value="" class="validate[required,maxSize[60]]" type="text" name="email" id="titre"/>
                             </div>
                           </div>
                           
                           <div class="row-form clearfix">
                            <div class="span9">
                            <label for="cin">Password:</label>        
                             <input value="" class="validate[required,maxSize[60]]" type="password" name="password" id="titre"/>
                             </div>
                           </div>

                         
                         <div class="row-form clearfix">
                            <div class="span9">
                            <label for="cin">filiere:</label>        
							 <input value="" class="validate[required,maxSize[60]]" type="text" name="filiere" id="titre"/>
                              </div>
                           </div>
						 
				 

 					   

                        <div class="row-form clearfix">
                            <div class="span9" >        
                             <button class="btn" type="submit" style="margin-left: 537px;"><span class="isw-plus"></span>&nbsp;Ajouter</button>
                            </div>
                        </div>
                        </form>                    
                           </div>
								</div>
									    </div>  </div> 
          </body>
         </html>