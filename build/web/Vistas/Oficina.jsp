<%-- 
    Document   : Oficina
    Created on : 30/11/2020, 11:21:26 PM
    Author     : lainc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    if (request.getSession().getAttribute("usuario") != null) {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema | Oficinas</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
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
                                    <img src="../dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs">Administrador</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
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
                            <img src="../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
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
                        <li><a href="../Vistas/home.jsp"><i class="fa fa-home"></i> <span>Inicio</span></a></li>
                        <!--<li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>-->
                        <li class="treeview active">
                            <a href="#"> <i class="glyphicon glyphicon-th-large"></i>
                                <span>Registro</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li class="active"><a href="../Vistas/Oficina.jsp"><i class="fa fa-bookmark-o"></i> Oficinas</a></li>
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
                        Gestión Surcusales
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Menu</a></li>
                        <li class="active">Surcusales</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content container-fluid">
                    <div>
                        <a data-toggle="modal" href="#mdlOficina" class="btn btn-success btn-flat"><i class="fa fa-plus"></i>  Nueva Surcusal</a>
                    </div>
                    <br>

                    <!-- Agregar Categoria Modal -->
                    <div class="modal fade in" id="mdlOficina" aria-hidden="false">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                    <h4 class="modal-title"> Gestion Surcusales</h4>
                                </div>
                                <form class="form" id="frmOficina">
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>Nombre Surcusal</label>
                                                    <div class="input-group">                                               
                                                        <span class="input-group-addon"><i class="fa fa-folder"></i></span>
                                                        <input name="txtNombre" id="txtNombre" autofocus="" required="" type="text" class="form-control" placeholder="Previred">
                                                    </div>
                                                    <label>Direccion</label>
                                                    <div class="input-group">                                               
                                                        <span class="input-group-addon"><i class="fa fa-folder"></i></span>
                                                        <input name="txtDireccion" id="txtDireccion" autofocus="" required="" type="text" class="form-control" placeholder="Copiapo 253">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">                                               
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="chkVigencia" checked id="chkVigencia"/>Activo
                                                </label>
                                            </div>                                                
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
                                <h3 class="box-title">Listado de Surcusales</h3>
                            </div>
                            <div class="box-body">
                                <div class="table-responsive" >                                 
                                    <table class="table table-bordered table-striped dataTable table-hover" id="TablaOficina" class="display">
                                        <thead>
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Surcusal</th>
                                                <th>Direccion</th>
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
        <script src="../js/Validar.js" type="text/javascript"></script>
        <script src="../bower_components/sweetAlert/sweetalert.min.js" type="text/javascript"></script>
    </body>
</html>
<%
    } else {
        response.sendRedirect("../identificar.jsp");
    }
%>