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

<h1>Eliminar Cliente</h1>
<form action="hello-servlet" method="post">
    <label for="idEliminar">ID del Cliente a Eliminar:</label>
    <input type="text" id="idEliminar" name="idEliminar"><br>

    <input type="hidden" name="action" value="eliminar">
    <input type="submit" value="Eliminar Cliente">
</form>

<h1>Modificar Cliente</h1>
<form action="hello-servlet" method="post">
    <label for="idModificar2">ID del Cliente a Modificar:</label>
    <input type="text" id="idModificar2" name="idModificar2"><br>

    <label for="nombre2">Nuevo Nombre:</label>
    <input type="text" id="nombre2" name="nombre2"><br>

    <label for="edad2">Nueva Edad:</label>
    <input type="text" id="edad2" name="edad2"><br>

    <label for="correo2">Nuevo Correo:</label>
    <input type="text" id="correo2" name="correo2"><br>

    <label for="telefono2">Nuevo Teléfono:</label>
    <input type="text" id="telefono2" name="telefono2"><br>

    <label for="direccion2">Nueva Dirección:</label>
    <input type="text" id="direccion2" name="direccion2"><br>

    <input type="hidden" name="action" value="modificar">
    <input type="submit" value="Modificar Cliente">



</form>
<br><br>


<h1>Buscar Cliente por ID</h1>
<form id="searchForm">
    <label for="idBuscar">ID del Cliente a Buscar:</label>
    <input type="text" id="idBuscar" name="idBuscar">
    <input type="submit" value="Buscar Cliente">
</form>
<!-- Muestra el resultado de la búsqueda si se encuentra un cliente -->
<h2>Cliente Encontrado</h2>
<div id="resultDiv">
    <!-- El resultado de la búsqueda se mostrará aquí -->
</div>

<script>
    document.getElementById("searchForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Evita que el formulario se envíe de forma predeterminada

        var idBuscar = document.getElementById("idBuscar").value;

        // Realiza una solicitud AJAX para buscar el cliente por ID
        var xhr = new XMLHttpRequest();//XMLHttpRequest, que es utilizado para realizar solicitudes HTTP desde el navegador web.
        xhr.open("GET", "hello-servlet?action=buscar&idBuscar=" + idBuscar, true);

        //eso se utliza para cambiar de estado osea cada vez q ingreso un id dierente para buscar?
        xhr.onreadystatechange = function () {
                // Maneja la respuesta y muestra el resultado en el div de resultados
                var resultDiv = document.getElementById("resultDiv");
                resultDiv.innerHTML = xhr.responseText;
            }
        xhr.send();
    });
</script>

<h1>Lista de Clientes</h1>
<button id="listarClientes">Listar Clientes</button>

<table id="clientesTable" style="display:none;">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Edad</th>
        <th>Correo</th>
        <th>Teléfono</th>
        <th>Dirección</th>
    </tr>
    <!-- Los resultados se mostrarán aquí -->
</table>

<!-- Agrega JavaScript para manejar la solicitud AJAX -->
<script>
    document.getElementById("listarClientes").addEventListener("click", function () {
        // Realiza una solicitud AJAX para listar los clientes
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "hello-servlet?action=listar", true);

        xhr.onreadystatechange = function () {
                // Maneja la respuesta y muestra los resultados en la tabla de clientes
                var clientesTable = document.getElementById("clientesTable");
                clientesTable.style.display = "table"; // Muestra la tabla
                clientesTable.innerHTML = xhr.responseText;
        };

        xhr.send();
    });
</script>

</body>
</html>