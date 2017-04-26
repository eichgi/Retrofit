<?php

$languages = [];

array_push($languages, [
    "name" => "PHP",
    "creator" => "Rasmus Lerdorf",
    "released" => 1995,
    "frameworks" => [
        "Laravel", "Symphony", "Slim"
    ]
]);
array_push($languages, [
    "name" => "Java",
    "creator" => "James Gosling",
    "released" => 1995,
    "frameworks" => [
        "Spring", "Play", "Groovy"
    ]
]);
array_push($languages, [
    "name" => "Javascript",
    "creator" => "Brendan Eich",
    "released" => 1995,
    "frameworks" => [
        "Express", "Sails", "Hapi"
    ]
]);
array_push($languages, [
    "name" => "Ruby",
    "creator" => "Yukihiro Matzumoto",
    "released" => 1995,
    "frameworks" => [
        "Rails", "Sinatra"
    ]
]);
array_push($languages, [
    "name" => "Python",
    "creator" => "Guido van Rossum",
    "released" => 1995,
    "frameworks" => [
        "Django", "Flask", "Pyramid"
    ]
]);

echo json_encode($languages);