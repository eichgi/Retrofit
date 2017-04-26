<?php

/** json basic object via GET */

$nombre = $_GET['nombre'];
$edad = $_GET['edad'];
$email = $_GET['email'];

echo json_encode(compact('nombre', 'edad', 'email'));

