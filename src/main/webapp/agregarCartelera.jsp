<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ef_g4.Beans.Cartelera" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="val" scope="request" type="java.lang.String" class="java.lang.String"/>

<% ArrayList<String> listDatos=(ArrayList<String>) request.getAttribute("listDatos"); %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/styles_desplegable.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" crossorigin="anonymous"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU">
    <script src="https://kit.fontawesome.com/a8c05a362e.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="stylescss/styles_desplegable.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
            crossorigin="anonymous"></script>
    <title> Agregar Cartelera</title>


    <style>

        #popup:target{
            visibility: hidden; /* Se regresa a hidden para ocultar */
            opacity: 0; /* Se regresa a o para hacerlo "invisible" */
        }
        .overlay1{
            display: flex; justify-content: center ; align-items: center; position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(0, 0, 0, 0.7);
            transition: opacity 500ms;
            visibility: visible;
            opacity: 1;

        }

    </style>
</head>
<body class="sb-nav-fixed" style="background-image: url('https://scontent.flim9-1.fna.fbcdn.net/v/t31.18172-8/21762476_10155697802681310_6157505495657686704_o.jpg?_nc_cat=106&ccb=1-5&_nc_sid=730e14&_nc_ohc=fN69fUjvpzoAX9gtfdC&_nc_ht=scontent.flim9-1.fna&oh=b13945943602301e5734c007d9406a3f&oe=61D56359');
 background-repeat:no-repeat; background-size: cover">





<div class="container" >
    <div class="row justify-content-center" style="padding-top: 45px" >
        <div class="col-lg-5">
            <div class="card shadow-lg border-0 rounded-lg mt-5">

                <form>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="inputPelicula">Pelicula</label>
                            <select id="inputPelicula" class="form-control">
                                <option selected>Choose...</option>
                                <option>...</option>
                            </select>
                        </div>

                        <div class="form-group col-md-4">
                            <label for="inputCine">Cine</label>
                            <select id="inputCine" class="form-control">
                                <option selected>Choose...</option>
                                <option>...</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="3d">
                            <label class="form-check-label" for="3d">
                                Es 3D
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="gridCheck">
                            <label class="form-check-label" for="gridCheck">
                                Es doblada
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="gridChek">
                            <label class="form-check-label" for="gridChek">
                                Es subtitulada
                            </label>
                        </div>
                    </div>




                    <button type="submit" class="btn btn-primary">Agregar</button>

                </form>

            </div>
        </div>
    </div>


</div>






<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
        crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>
</html>
