
<jsp:useBean id="err" scope="request" type="java.lang.String" class="java.lang.String"/>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <title>Hello, world!</title>
    <style>
        #popup:target{
            visibility: hidden; /* Se regresa a hidden para ocultar */
            opacity: 0;  /*Se regresa a o para hacerlo "invisible" */
        }
        .overlay1{display: flex; justify-content: center ; align-items: center; position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(0, 0, 0, 0.7);
            background-repeat: repeat-y;
            transition: opacity 500ms;
            visibility: visible;
            opacity: 1} </style>


</head>
<body style="background-image: url('https://files.pucp.education/puntoedu/wp-content/uploads/2021/02/23003729/aerea-2019-e1605150409402-1536x909.jpg'); background-repeat: repeat-y; background-size: cover">

<section class="vh-100 bg-image">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 15px;">



                        <h4 class=" text-center mb-5" style="color: green" >INICIAR SESION </h4>


                        <div class="card-body">
                        <form method="post" action="<%=request.getContextPath()%>/LoginServlet">
                            <div class="form-floating mb-3">
                                <input class="form-control" id="inputDni" name="inputDni">
                                <label for="inputDni">Usuario</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input class="form-control" id="inputPassword" type="password" name="inputPassword">
                                <label for="inputPassword">Contrasenia</label>
                            </div>


                            <div>
                                <div style="float: left">
                                    <button class="btn btn-primary" type="submit">Ingresar</button>
                                </div>
                            </div>


                        </form>
                    </div>


                </div>
            </div>
        </div>
    </div>
    </div>
</section>

    <%if(!err.equalsIgnoreCase("")){%>
    <nav id="popup" class="overlay1">
        <div class=" popup card text-center ">
            <h5 class="card-header text-center text-light">Cartelera de Peliculas</h5>
            <div class="card-body">
                <h5 class="card-title p-2"><%=err%></h5>
                <a href="<%= request.getContextPath()%>/LoginServlet" class="btn btn-success mb-2">Aceptar</a>
            </div>
        </div>
    </nav>
    <%}%>



</body>
</html>
