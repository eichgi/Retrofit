<?php

/** json basic object via POST */

$nombre = $_POST['nombre'];
$edad = $_POST['edad'];
$email = $_POST['email'];

echo json_encode(compact('nombre', 'edad', 'email'));

