<%-- 
    Document   : Pago
    Created on : 21/12/2020, 10:05:39 PM
    Author     : lainc
--%>

<%@page import="Modelo.Pago"%>
<%@page import="DAO.PagoDAO"%>
<%@page import="Modelo.Empresa"%>
<%@page import="java.util.List"%>
<%@page import="DAO.EmpresaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    if (request.getSession().getAttribute("usuario") != null) {
        EmpresaDAO empredao = new EmpresaDAO();
        PagoDAO pagdao = new PagoDAO();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Control | Pago</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
        <script src="../js/alertify.min.js" type="text/javascript"></script>
        <!-- Theme style -->
        <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="../dist/css/skins/skin-blue.min.css">
        <link href="../bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../bower_components/sweetAlert/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">

                <!-- Logo -->
                <a href="Principal.jsp" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>S</b>CP</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>SISTEMA CONTROL</b>PAGOS</span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">

                            <!-- User Account Menu -->
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="../dist/img/mjllogo.png" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs">Perfil</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="../dist/img/mjllogo.png" class="img-circle" alt="User Image">
                                        <p>${usuario.nombre}<br>
                                            Usted es     
                                            ${usuario.cargos.nombreCargo}
                                            <small>${usuario.correo}</small>
                                        </p>
                                    </li>

                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <a data-toggle="modal" href="#logout" class="btn btn-danger btn-flat"><i class="fa fa-power-off"></i>  Salir</a>                                            
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button -->

                        </ul>
                    </div>
                </nav>
            </header>
            <div class="modal fade" id="logout" aria-hidden="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><i class="fas fa-sign-in-alt"></i> Salir</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>¿Seguro que quieres salir?</p>
                        </div>
                        <div class="modal-footer">
                            <a type="button" class="btn btn-danger" href="../identificar.jsp">Sí, Salir</a>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                    <!-- Sidebar user panel (optional) -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="../dist/img/mjllogo.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>${usuario.cargos.nombreCargo}</p>
                            <!-- Status -->
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>

                    <!-- search form (Optional) -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->

                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">PRINCIPAL</li>
                        <!-- Optionally, you can add icons to the links -->
                        <!--<li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>-->
                        <li class="treeview">
                            <a href="#"> <i class="glyphicon glyphicon-th-large"></i>
                                <span>Registro</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li class="active"><a href="../Vistas/Oficina.jsp"><i class="fa fa-bookmark-o"></i> Surcusales</a></li>
                            </ul>
                        </li> 
                        <li class="treeview">
                            <a href="#">
                                <i class="glyphicon glyphicon-shopping-cart"></i>
                                <span>Pagos</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-cart-plus"></i> Nuevo Pago</a></li>
                                <li><a href="#"><i class="glyphicon glyphicon-list-alt"></i> Administrar Pagoss</a></li>

                            </ul>
                        </li>

                        <li>
                            <a href="../Vistas/Colaborador.jsp">
                                <i class="fa fa-user"></i> <span>Colaboradores</span>
                                <span class="pull-right-container">     
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="../Vistas/Empresa.jsp">
                                <i class="fa fa-group"></i> <span>Empresas</span>
                                <span class="pull-right-container">     
                                </span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="glyphicon glyphicon-signal"></i> <span>Reportes</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu menu-open" style="">
                                <li class=""><a href="#"><i class="fa fa-bar-chart"></i> Reporte de pagos</a></li>
                                <li class=""><a href="#"><i class="fa fa-bar-chart"></i> Reporte de inventario</a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-wrench"></i> <span>Configuración</span>

                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-circle-o"></i> General</a></li>
                                <li><a href="TipoPago.jsp"><i class="fa fa-circle-o"></i> Tipo Documento</a></li>
                            </ul>
                        </li>

                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-lock"></i> <span>Administrar accesos</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu menu-open">
                                <li class=""><a href="../Vistas/Cargo.jsp"><i class="glyphicon glyphicon-briefcase"></i> Cargos</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Gestión Pagos
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Menu</a></li>
                        <li class="active">Pagos</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content container-fluid">
                    <div>
                        <a data-toggle="modal" href="#mdlPago" class="btn btn-success btn-flat"><i class="fa fa-plus"></i>  Nuevo Pago</a>
                    </div>
                    <br>
                    <!-- Agregar Categoria Modal -->
                    <div class="modal fade in" id="mdlPago" aria-hidden="false">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                    <h4 class="modal-title"> Generar nuevo pago</h4>
                                </div>
                                <form class="form" id="frmPago">
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <!-- text input -->
                                                <div class="form-group">
                                                    <label>Fecha de Pago </label>
                                                    <input type="date" class="form-control" required id="fechaPago" name="fechaPago" >
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <!-- text input -->
                                                <div class="form-group">
                                                    <label>Empresa </label>
                                                    <select class="form-control select2"  name="cbEmpresa" required="" id="cbEmpresa">
                                                        <option value="0">Seleccione una Empresa</option>
                                                        <%  List<Empresa> empresas = null;
                                                            empresas = empredao.listar();
                                                            for (Empresa empre : empresas) {
                                                        %>
                                                        <option value="<%=empre.getRutEmpresa()%>"><%=empre.getRazonSocial()%></option>
                                                        <%}%>
                                                    </select>                                               
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-6">                                                
                                                <div class="form-group"> 
                                                    <label>Monto</label>
                                                    <div class="input-group">                                               
                                                        <span class="input-group-addon"><i class="fa fa-folder"></i></span>
                                                        <input name="txtMonto" id="txtMonto" autofocus="" required="" type="text" class="form-control" placeholder="$">
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="chkVigencia" checked id="chkVigencia"/>Activo
                                                        </label>
                                                    </div>                                                
                                                </div> 
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label>Servicio </label>
                                                    <br>
                                                    <div class="form-group">
                                                        <button type="button" class="btn btn-success" id="agregarServicio" 
                                                                name="agregarServicio" data-toggle="modal" data-target="#modal-agregar-servicio"
                                                                style="margin: 0 0 0 0;">Agregar Servicio</button>
                                                    </div>
                                                    <%-- <select class="form-control" required id="idLib" name="idLib">
                                                         <option value="0">Seleccione un Libro</option>
                                                         <%
                                                             List<Libro> libros = new ArrayList<Libro>();
                                                             libros = port.libroListar();

                                                            for (Libro lec : libros) {
                                                        %>
                                                        <option value="<%=lec.getIdLib()%>"><%=lec.getTitulo()%></option>
                                                        <%}%>
                                                    </select>  --%>                                             
                                                </div>
                                            </div>
                                            <table class="table table-bordered table-striped dataTable table-hover" class="display">
                                                <thead>
                                                    <tr>
                                                        <th>Número</th><th>Servicio</th><th>Accion</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="tablaAgregarServicio">
                                                    
                                                </tbody>
                                            </table>
                                        </div>                                      
                                    </div>
                                    <div class="modal-footer">
                                        <button type="reset" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close red"></i> Cancelar</button>
                                        <button type="submit" value="Registrar" class="btn btn-primary"><i class="fa fa-floppy-o"></i> Registrar</button>  
                                    </div>
                                </form>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div>
                    <div class="box">    
                        <div class="container-fluid" style="margin-top: 5px">
                            <div class="box-header with-border">             
                                <h3 class="box-title">Listado de Pagos</h3>
                            </div>
                            <div class="box-body">
                                <div class="table-responsive" >                                 
                                    <table class="table table-bordered table-striped dataTable table-hover" id="TablaPago" class="display">
                                        <thead>
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Fecha de emission</th>
                                                <th>Rut empresa</th>
                                                <th>Colaborador</th>
                                                <th style="text-align: center">Estado</th>
                                                <th style="text-align: center">Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="box-footer">

                            </div>
                        </div>
                    </div>
                </section>
                <!-- /.content -->
                <!-- *************  MODAL AGREGAR Tipo Pago ********************* -->

                <div class="modal fade" id="modal-agregar-servicio">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Agregar Servicio</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label>Servicios</label>
                                                <select class="form-control" required id="idPag" name="idPag" requeried>
                                                    <option value="">Seleccione un Servicio</option>
                                                    <%  List<Pago> pagos = null;
                                                        pagos = pagdao.listar();
                                                        for (Pago pa : pagos) {
                                                    %>
                                                    <option value="<%=pa.getIdPago()%>"><%=pa.getServicio()%></option>
                                                    
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer justify-content-between">
                                <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="fa fa-window-close"></span> Cerrar</button>
                                <button type="button" onclick="agregarServicio()" class="btn btn-primary "><span class="fa fa-save"></span> Agregar</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>

                <!-- *************  MODAL MOSTRAR DETALLES DEL SERVICIO ELEGIDO********************* -->
                <div class="modal fade" id="modal-mostrar-detalles">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Detalles del pago</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Servicio</th>
                                                        <th>Descripción</th>
                                                        <th>Rut Empresa</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="tablaMostrarDetalle">

                                                </tbody>
                                            </table>

                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="modal-footer justify-content-between">
                                <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="fas fa-window-close"></span> Cerrar</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.Fin del modal -->
            </div>
            <footer class="main-footer">
                <!-- To the right -->
                <div class="pull-right hidden-xs">
                    SysControl 
                </div>
                <!-- Default to the left -->
                <strong>Copyright &copy; 2020 <a href="#">SISTEMA CONTROL PAGOS.</strong> Todos los derechos reservados.
            </footer>

            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->
        <!-- jQuery 3 -->
        <script src="../bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="../dist/js/adminlte.min.js"></script>
        <script src="../bower_components/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/home.vista.js" type="text/javascript"></script>
        <script src="../bower_components/sweetAlert/sweetalert.min.js" type="text/javascript"></script>
    </body>
    <script>
        function CierraPopup() {
            $("#modal-lg").modal('hide'); //ocultamos el modal            
        }
    </script>
    <script>
        /***************************************************************************
         *          AGREGAR LIBRO
         *************************************************************************** 
         */
        function agregarServicio() {

            if ($('#idPag').val() != "") {


                var repetido = false;
                var idPag = $('#idPag').val();
                var valorCat = $("#idPag option:selected").text();


                var arr = $('#tablaAgregarServicio tr').find('td:first').map(function () {
                    return $(this).text();
                }).get();

                for (var i = 0; i < arr.length; i++) {
                    if (idPag == arr[i]) {
                        repetido = true;
                    }
                    console.log("Numero: " + arr[i]);
                }
                console.log(idPag + ", " + valorCat);

                if (!repetido) {
                    $('#tablaAgregarServicio').append('<tr><td>' + idPag + '</td><td>' + valorCat + '</td><td>\n\
                        <button type="button"  onclick="eliminarFilaServicio(this)" class="btn btn-danger"><span class="fa fa-save"></span> Eliminar</button>\n\
                        </td></tr>');

                    $("#modal-agregar-servicio").modal('hide');

                } else {

                    swal("Servicio repetido, elige otro por favor.!", {
                        icon: "warning"
                    });

                }

            } else {

                console.log("Seleccione un servicio");
                alert("Seleccione un servicio");
            }

        }
        /***************************************************************************
         *          ELIMINAR UNA FILA DE LA TABLA DONDE AGREGAMOS LOS SERVICIOS
         *************************************************************************** 
         */
        function eliminarFilaServicio(btn) {
            var row = btn.parentNode.parentNode;
            row.parentNode.removeChild(row);
        }
    </script>
    
</html>
<%
    } else {
        response.sendRedirect("../identificar.jsp");
    }
%>
