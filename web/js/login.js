$(document).ready(function(){
    //alert('hola');
    $('#frmLogin').on('submit',function(e){
        e.preventDefault();
        //alert('login');
        $.post('session?accion=identificar',{Usuario:$('#txtUsuario').val(),Password:$('#txtPass').val()},function(response){
            //alert(response.msje);
            alertify.alert('Alerta del Sistema !', response.msje, function () {
             });
            if(response.valido){
                setTimeout(function () {
                    location.href='Vistas/home.jsp';
                }, 1500);
                
            }
        });
    });
});