<?php include 'database.php';?>
<?
require_once "recaptchalib.php";

// sua chave secreta
$secret = "***";
 
// resposta vazia
$response = null;
 
// verifique a chave secreta
$reCaptcha = new ReCaptcha($secret);

// se submetido, verifique a resposta
if ($_POST["g-recaptcha-response"]) {
$response = $reCaptcha->verifyResponse(
        $_SERVER["REMOTE_ADDR"],
        $_POST["g-recaptcha-response"]
    );
}

if ($response != null && $response->success) {

	date_default_timezone_set('America/Sao_Paulo');
	$data = date('d-m-Y H:i');

	$nome=$_POST['nome'];
	$email=$_POST['email'];
	$CPF=$_POST['CPF'];
	$Matricula=$_POST['Matricula'];
	$Titulo="Inscrição - $nome";
	$Destinatario="contato@uffs.tk";

	if (isset($_POST['oficinaEletronica']))
		$oficinaEletronica="Oficina de eletronica";

	if (isset($_POST['palestraSerious']))
		$palestraSerious="Palestra Serious";

	if (isset($_POST['oficinaGithub']))
		$oficinaGithub="Oficina Github";

	if (isset($_POST['palestraCTF']))
		$palestraCTF="Palestra CTF";

	if (isset($_POST['palestraRedhat']))
		$palestraRedhat="Palestra Redhat";

	if (isset($_POST['oficinaAnki']))
		$oficinaAnki="Oficina Anki";

	if (isset($_POST['palestraPython']))
		$palestraPython="Palestra Python";

	if (isset($_POST['palestraAmbiental']))
		$palestraAmbiental="Palestra Ambiental";

	if (isset($_POST['palestraBitcoin']))
		$palestraBitcoin="Palestra Bitcoin";

	if (isset($_POST['oficinaRobotica']))
		$oficinaRoboticaA="Oficina Robotica (A)";

	if (isset($_POST['oficinaRobotica2']))
		$oficinaRoboticaB="Oficina Robotica (B)";

	if (isset($_POST['palestraPriscila']))
		$palestraPriscila="Palestra Priscila";

	if (isset($_POST['palestraTato']))
		$palestraTato="Palestra DL e Tato";

	if (isset($_POST['palestraChatbot']))
		$palestraChatbot="Palestra Chatbot";

	if (isset($_POST['palestraLinux']))
		$palestraLinux="Palestra Linux";

	$mensagem1="

	--- Semana acadêmica ---
	   +- Inscricao.php -+

	Nome: $nome

	Email: $email

	CPF: $CPF

	Matrícula: $Matricula

	Data: $data

	Inscrição em:
		$oficinaEletronica
		$palestraSerious
		$oficinaGithub
		$palestraCTF
		$palestraRedhat
		$oficinaAnki
		$palestraAmbiental
		$palestraBitcoin
		$oficinaRoboticaA
		$oficinaRoboticaB
		$palestraPriscila
		$palestraTato
		$palestraChatbot
		$palestraLinux";


	$catTodas= $oficinaEletronica . " - " . $palestraSerious . " - " . $oficinaGithub . " - " . $palestraCTF . " - " . $palestraRedhat . " - " . $oficinaAnki . " - " . $palestraAmbiental . " - " . $palestraBitcoin . " - " . $oficinaRoboticaA . " - " . $oficinaRoboticaB . " - " . $palestraPriscila . " - " . $palestraTato . " - " . $palestraChatbot . " - " . $palestraLinux;

	mail("$Destinatario","$Titulo", "$mensagem1","From:$email");
	mysqli_query($connect,"INSERT INTO inscricoes(nome,email,CPF,matricula,inscricoes)
					VALUES('$nome','$email','$CPF','$Matricula', '$catTodas')");
	if(mysqli_affected_rows($connect) > 0){
		echo "<script>window.location='http://uffs.tk/confirmacao.html';alert('Sua inscricao foi feita com sucesso!');</script>";
		echo '<meta HTTP-EQUIV="Refresh" CONTENT="1; URL=http://uffs.tk/confirmacao.html">';
	} else {
		echo "<script>window.location='http://uffs.tk/erro.html';alert('Ocorreu um erro no banco de dados :(');</script>";
		echo '<meta HTTP-EQUIV="Refresh" CONTENT="1; URL=http://uffs.tk/erro.html">';
	}
}else {
	echo "<script>window.location='http://uffs.tk/erro.html';alert('Ocorreu um erro na sua verificação do reCaptcha, tente novamente.');</script>";
	echo '<meta HTTP-EQUIV="Refresh" CONTENT="1; URL=http://uffs.tk/erro.html">';
}

exit;
?>