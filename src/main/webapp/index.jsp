<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<h1>Agregar Cliente</h1>
<form action="hello-servlet" method="post">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id"><br>

    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre"><br>

    <label for="edad">Edad:</label>
    <input type="text" id="edad" name="edad"><br>

    <label for="correo">Correo:</label>
    <input type="text" id="correo" name="correo"><br>

    <label for="telefono">Teléfono:</label>
    <input type="text" id="telefono" name="telefono"><br>

    <label for="direccion">Dirección:</label>
    <input type="text" id="direccion" name="direccion"><br>

    <input type="submit" value="Agregar Cliente">

</form>
</body>
</html>