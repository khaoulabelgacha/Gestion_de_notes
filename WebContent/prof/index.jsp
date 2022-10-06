
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
 <head>        
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <!--[if gt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <![endif]-->
    
    
    <title>Gestion Notes</title>
<style type="text/css">
    ul.top-menu > li > .logout {
	color: #f2f2f2;
	font-size: 12px;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	border: 1px solid #64c3c2 !important;
	padding: 5px 15px;
	margin-right: 15px;
	background: #4ECDC4;
	margin-top: 15px;
   }
   
   ul.top-menu > li > a:hover, ul.top-menu > li > a:focus {
    border:1px solid #b6b6b6 !important;
    background-color: transparent !important;
    border-color: #b6b6b6 !important;
    text-decoration: none;
    border-radius: 4px;
    -webkit-border-radius: 4px;
    color: #b6b6b6 !important;
}

a.logo {
    font-size: 24px;
    color: #f2f2f2;
    float: left;
    margin-top: 15px;
    text-transform: uppercase;
}

a.logo b {
    font-weight: 900;
}

a.logo:hover, a.logo:focus {
    text-decoration: none;
    outline: none;
}

a.logo span {
    color: #4ECDC4;
}
</style>
<!-- end styles -->    

    <link rel="icon" type="image/ico" href="img/gs.png"/>
    
    <link href="../css/stylesheets.css" rel="stylesheet" type="text/css" />  
    <!--[if lt IE 8]>
        <link href="css/ie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->            
    <link rel='stylesheet' type='text/css' href='../css/fullcalendar.print.css' media='print' />
    
    <script type='text/javascript' src='../js/plugins/fullcalendar/jquery.min.js'></script>
    <script type='text/javascript' src='../js/plugins/fullcalendar/jquery-ui.min.js'></script>
    <script type='text/javascript' src='../js/plugins/jquery/jquery.mousewheel.min.js'></script>
    
    <script type='text/javascript' src='../js/plugins/cookie/jquery.cookies.2.2.0.min.js'></script>
    
    <script type='text/javascript' src='../js/plugins/bootstrap.min.js'></script>
    
    <script type='text/javascript' src='../js/plugins/charts/excanvas.min.js'></script>
    <script type='text/javascript' src='../js/plugins/charts/jquery.flot.js'></script>    
    <script type='text/javascript' src='../js/plugins/charts/jquery.flot.stack.js'></script>    
    <script type='text/javascript' src='../js/plugins/charts/jquery.flot.pie.js'></script>
    <script type='text/javascript' src='../js/plugins/charts/jquery.flot.resize.js'></script>
    
    <script type='text/javascript' src='../js/plugins/sparklines/jquery.sparkline.min.js'></script>
    
    <script type='text/javascript' src='../js/plugins/fullcalendar/fullcalendar.min.js'></script>
    
    <script type='text/javascript' src='../js/plugins/select2/select2.min.js'></script>
    
    <script type='text/javascript' src='../js/plugins/uniform/uniform.js'></script>
    
    <script type='text/javascript' src='../js/plugins/maskedinput/jquery.maskedinput-1.3.min.js'></script>
    
    <script type='text/javascript' src='../js/plugins/validation/languages/jquery.validationEngine-en.js' charset='utf-8'></script>
    <script type='text/javascript' src='../js/plugins/validation/jquery.validationEngine.js' charset='utf-8'></script>
    
    <script type='text/javascript' src='../js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='../js/plugins/animatedprogressbar/animated_progressbar.js'></script>
    
    <script type='text/javascript' src='../js/plugins/qtip/jquery.qtip-1.0.0-rc3.min.js'></script>
    
    <script type='text/javascript' src='../js/plugins/cleditor/jquery.cleditor.js'></script>
    
    <script type='text/javascript' src='../js/plugins/dataTables/jquery.dataTables.min.js'></script>    
    
    <script type='text/javascript' src='../js/plugins/fancybox/jquery.fancybox.pack.js'></script>
    
    <script type='text/javascript' src='../js/cookies.js'></script>
    <script type='text/javascript' src='../js/actions.js'></script>
    <script type='text/javascript' src='../js/charts.js'></script>
    <script type='text/javascript' src='../js/plugins.js'></script>
    <script type="text/javascript">
           function renderTime() {
               
            // Time
               var currentTime = new Date();
               var h = currentTime.getHours();
               var m = currentTime.getMinutes();
                   if(h == 24){
                       h = 0;
                   } else if(h > 12){
                       h = h - 0;
                   }
                   
                   if(h < 10){
                       h = "0" + h;
                   }
                   
                   if(m < 10){
                       m = "0" + m;
                   }
                   
                   
               var myClock = document.getElementById("ClockDisplay");
               myClock.textContent = " " +h+ ":" +m;
               myClock.innerText = " " +h+ ":" +m;

               setTimeout("renderTime()", 1000);
            }
            renderTime();
</script>    
</head>
<body onload="renderTime();" >
 
    <div class="header">

      <div class="top-menu">
        <ul class="nav pull-right top-menu" style="padding-top: 20px;">
          <li><a class="logout" href="logout">Se déconnecter</a></li>
        </ul>
      </div>           
           
      <!--logo start-->
      <a class="logo"><b>GESTI<span>ON</span></b>  <b><span>NO</span>TES</b></a>
      
      <!--logo end--> 
            
    <div id="ClockDisplay" style="color: white;font-weight: bold;font-size: 30px;margin-left: 700px;padding-top: 35px;"></div>
		<ul class="header_menu">
            <li class="list_icon"><a href="#">&nbsp;</a></li>
        </ul>    
    </div>
    
    <div class="menu">                
        
        <div class="breadLine">            
            <div class="arrow"></div>
            <div class="adminControl active">
               Bonjour Mr ${ utilisateurConnecte.nom } ${ utilisateurConnecte.prenom }
            </div>
        </div>
        
        <div class="admin">
          
          </br>Module: ${ utilisateurConnecte .module.libele }
            
        </br>
            
            <div class="info">
			
                <span style="font-size: 15px;">Bienvenue</span>
            </div>
        </div>
        
        <ul class="navigation">            
            <li class="active">
                <a href="index">
                    <span class="isw-grid"></span><span class="text">Acceuil</span>
                </a>
            </li>
           
			  </br>
           <li class="active">
                <a href="programmerExamen">
                    <span class="isw-grid"></span><span class="text">Programmer Examen</span>
                </a>
            </li>
         
          <li class="active">
                <a href="listerExamen">
                    <span class="isw-grid"></span><span class="text">Liste Examens</span>
                </a>
            </li>                
        </ul>
        
        <div class="dr"><span></span></div>
       
        
    </div>       
    <div class="content">
        
        
        <div class="breadLine">
            
            <ul class="breadcrumb">
                <li>Professeur <span class="divider">></span></li>                
                <li class="active">Acceuil</li>
            </ul>
                  </div> 
                  <br><br><br> <br><br>    
                 <div class="workplace">
         
            <table class="table table-striped">
            	<thead>
            	  <tr>
            		<th style="background: #64c3c2; border-color: white;">Nombre des Examens</th>
            	  </tr>	
            	</thead>
            	<tbody>
            	  <tr>
            	  	<td style="font-size: 15px;font-weight: bold;">${  nbrexam }</td>
            	  </tr>
            	</tbody>
            </table>
            
             
            </div>
      