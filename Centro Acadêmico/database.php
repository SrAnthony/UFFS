<?php
$connect=mysqli_connect('***','***','***','***');
mysqli_query($connect,"SET NAMES 'utf8'");
mysqli_query($connect,'SET character_set_connection=utf8');
mysqli_query($connect,'SET character_set_client=utf8');
mysqli_query($connect,'SET character_set_results=utf8');
 
if(mysqli_connect_errno($connect)){
		echo "<script>window.location='index.html';alert('Não foi possível conectar ao servidor, tente novamente ou contate-nos.');</script>";
}
 
?>