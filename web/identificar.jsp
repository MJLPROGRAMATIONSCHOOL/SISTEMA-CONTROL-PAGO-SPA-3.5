<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema | Iniciar Session</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link href="css/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/AdminLTE.min_1.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="#"><b>Sistema Control </b>Pago ©</a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">
                <p class="login-box-msg">Iniciar Session</p>

                <form id="frmLogin" onsubmit="return validar(this)">
                    <div class="form-group has-feedback">
                        <input type="text" name="txtUsuario" id="txtUsuario" value="" class="form-control"  placeholder="Usuario">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" name="txtPass" id="txtPass" value="" class="form-control" placeholder="******">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                            <div class="icheck-primary">
                                <input type="checkbox" checked="" id="remember">
                                <label for="remember">
                                    Recuérdame
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Ingresar</button>
                        </div>
                    </div>
                    <!--<div id="contenedor"></div> -->
                </form>
                <!-- prueba se puede quitar-->
                <div class="social-auth-links text-center mb-3">
                    <p>- Alerta del Sistema -</p>
                    <div id="contenedor" class="alert alert-success" role="alert">
                        <a href="#" class="btn btn-block btn-social btn-flat"><i class="fa fa-info"></i> Mensaje: 
                            ${msje}</a>
                    </div>
                </div>
                <p class="mb-1">
                    <a href="#">Olvidé mi contraseña</a>
                </p>
                <p class="mb-0">
                    <a href="#" class="text-center">Registrarme</a>
                </p>
            </div>
        </div>
        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- iCheck -->
        <script src="js/alertify.min.js" type="text/javascript"></script>
        <script src="plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <script src="js/alertify.js" type="text/javascript"></script>
        <script src="js/scriptSession.js" type="text/javascript"></script>
        <script src="../bower_components/sweetAlert/sweetalert.min.js" type="text/javascript"></script>
        <!-- <script>
             $(function () {
                 $('input').iCheck({
                     checkboxClass: 'icheckbox_square-blue',
                     radioClass: 'iradio_square-blue',
                     increaseArea: '20%' /* optional */
                 });
             });
         </script> -->
    </body>
</html>