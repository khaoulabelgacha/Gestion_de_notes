

<%@ include file="menu.jsp" %> 

<div class="workplace">
               <div class="dr"><span></span></div>
                
             <div class="span6">
                    <div class="head clearfix" style="width: 718px;margin-left: 141px; margin-top: 60px;" >
                        <div class="isw-plus"></div>
                        <h1>Ajouter Module</h1>
                    </div>
                    <div class="block-fluid" style="width: 718px;margin-left: 141px;">                        
                    <form method="post" action="ajouterModule">

                          <div class="row-form clearfix">
                            <div class="span9">
                            <label for="cin">Code:</label>        
                             <input value="" class="validate[required,maxSize[60]]" type="text" name="code" id="titre"/>
                             </div>
                           </div>
                           
                           <div class="row-form clearfix">
                            <div class="span9">
                            <label for="cin">Libele:</label>        
                             <input value="" class="validate[required,maxSize[60]]" type="text" name="libele" id="titre"/>
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