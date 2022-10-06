<%@ include file="menu.jsp" %> 

 <div class="workplace" >
            <div class="dr"><span></span></div>

            <div class="row-fluid">
                
                <div class="span12">                    
                    <div class="head clearfix" style=" margin-top: 56px;" >
                        <div class="isw-grid"></div>
                        <h1>Liste des Examens</h1>      
                                                
                          </div>



         <div class="block-fluid">
                        <table cellpadding="0" cellspacing="0" width="100%" class="table">
                            <thead>
                                <tr>         
                                <th>ID</th>                           
 	<th>Module</th>
	<th>Date Examen</th>
	<th>Coefficient</th>
 
                                    
									
                                    <th  style="text-align: center;">Option</th>                             
                               
                            </thead>
                            <tbody>
<c:forEach  items="${listeExams }" var="exam">
	<tr>
			<td>${exam.id }</td>
			<td>${exam.module.libele }</td>
			<td>${exam.dateExam }</td>
			<td>${exam.coefficient }</td>
 
		<td>	<a href="donnerNotes?idExam=${ exam.id }">Donner notes</a></td>
	</tr>
</c:forEach>

                                                                   
                                                
                            </tbody>
                        </table>
                    
                </div>                                
                
            </div>            
            
    </div>
    </div> 
               
               
               
               
               