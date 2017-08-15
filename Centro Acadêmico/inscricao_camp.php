<?php include 'database.php';?>
<?

$campeonato=$_POST['Campeonato'];
$team=$_POST['team'];
$team= str_replace(" ","_",$team);

$email=$_POST['email_camp'];

$nome1=$_POST['nome_camp1'];
$cpf1=$_POST['CPF_camp1'];

$nome2=$_POST['nome_camp2'];
$cpf2=$_POST['CPF_camp2'];

$nome3=$_POST['nome_camp3'];
$cpf3=$_POST['CPF_camp3'];

$nome4=$_POST['nome_camp4'];
$cpf4=$_POST['CPF_camp4'];

$nome5=$_POST['nome_camp5'];
$cpf5=$_POST['CPF_camp5'];

$Titulo="Inscrição - $tem";
$Destinatario="contato@uffs.tk";

$mensagem1="

--- Semana acadêmica ---
+- Inscricao_camp.php -+

Time: $nome
Campeonato: $campeonato
Email: $email

Participantes:
	$nome1 - $cpf1
	$nome2 - $cpf2
	$nome3 - $cpf3
	$nome4 - $cpf4
	$nome5 - $cpf5";

mail("$Destinatario","$Titulo", "$mensagem1","From:$email");

$table_name= $team . "_" . $campeonato;

mysqli_query($connect,"CREATE TABLE $table_name(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,nome TEXT(30) NOT NULL,cpf TEXT(30) NOT NULL,email TEXT(30) NOT NULL)");

mysqli_query($connect,"INSERT INTO $table_name(nome,cpf)
				VALUES('$nome1','$cpf1')");
mysqli_query($connect,"INSERT INTO $table_name(nome,cpf)
				VALUES('$nome2','$cpf2')");
mysqli_query($connect,"INSERT INTO $table_name(nome,cpf)
				VALUES('$nome3','$cpf3')");
mysqli_query($connect,"INSERT INTO $table_name(nome,cpf)
				VALUES('$nome4','$cpf4')");
mysqli_query($connect,"INSERT INTO $table_name(nome,cpf)
				VALUES('$nome5','$cpf5')");
mysqli_query($connect,"INSERT INTO $table_name(nome,cpf,email)
				VALUES('---','---','$email')");


if(mysqli_affected_rows($connect) > 0){
	echo "<script>window.location='http://uffs.tk/confirmacao.html';alert('Sua inscricao foi feita com sucesso!');</script>";
	echo '<meta HTTP-EQUIV="Refresh" CONTENT="1; URL=http://uffs.tk/confirmacao.html">';
} else {
	echo "Ocorreu um erro :(<br />";
	echo mysqli_error ($connect);
}
exit;
?>