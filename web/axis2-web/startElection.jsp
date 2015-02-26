<html>
<head>
<style media="screen" type="text/css">

form    {
background: -webkit-gradient(linear, bottom, left 175px, from(#CCCCCC), to(#EEEEEE));
background: -moz-linear-gradient(bottom, #CCCCCC, #EEEEEE 175px);
margin:auto;
position:relative;
width:550px;
height:450px;
font-family: Tahoma, Geneva, sans-serif;
font-size: 14px;
font-style: italic;
line-height: 24px;
font-weight: bold;
color: #09C;
text-decoration: none;
-webkit-border-radius: 10px;
-moz-border-radius: 10px;
border-radius: 10px;
padding:10px;
border: 1px solid #999;
border: inset 1px solid #333;
-webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
}

input {
width:375px;
display:block;
border: 1px solid #999;
height: 25px;
-webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
}

input.button {
	width:100px;
	position:relative;
	right:30px;
	bottom:20px;
	background:#09C;
	color:#fff;
	font-family: Tahoma, Geneva, sans-serif;
	height:30px;
	-webkit-border-radius: 15px;
	-moz-border-radius: 15px;
	border-radius: 15px;
	border: 1p solid #999;
	float: right;
	margin: 10 10 10 10;
}
input.button:hover {
	background:#fff;
	color:#09C;
}

</style>
</head>
<form id='login' action='/EVoting/election/startProcess' method='post' accept-charset='UTF-8'>
<h1>Election Initialization</h1>
<fieldset >
<legend>Enter Credentials</legend>

<label for='username' >UserName*:</label>
<input type='text' name='username' id='username'  maxlength="50" /><br/>
 
<label for='password' >Password 1*:</label>
<input type='password' name='password1' id='password' maxlength="50" /><br/>

<label for='password' >Password 2*:</label>
<input type='password' name='password2' id='password' maxlength="50" /><br/>

<label for='password' >Password 3*:</label>
<input type='password' name='password3' id='password' maxlength="50" /><br/>

</fieldset>
 
<br/>
<input type='reset' class="button" name='Submit' value='Reset' />
<input type='submit' class="button" name='Submit' value='Submit' />

<br/>
<br/>
</form>
</html>