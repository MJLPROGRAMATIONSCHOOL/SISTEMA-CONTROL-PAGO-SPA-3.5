/* global alertify */

$(document).ready(function () {
    var DOM = {
        frmLogin: $("#frmLogin"),
        txtUser: $("input#txtUsuario"),
        txtClave: $("input#txtPass"),
        alert: $("div#contenedor")
    };

    DOM.frmLogin.on("submit", function (e) {
        e.preventDefault();
        iniciarSession();
    });

    function iniciarSession() {
        var obj = {
            Usuario: DOM.txtUser.val(),
            Password: DOM.txtClave.val()};

        $.post("session?accion=identificar",
                {datos: JSON.stringify(obj)},
                function (data) {
                    if (data.rpt) {
                        DOM.alert[0].textContent = data.msj;
                        window.location.href = "Vistas/home.jsp";
                    } else {
                        DOM.alert[0].textContent = data.msj;
                    }
                });
    }
    
});
window.onload = iniciar;
function validar(frm) {
    var usuario = frm.txtUsuario,
            pass = frm.txtPass
    if (usuario.value === "") {
        usuario.focus();
        alertify.alert("Error", "Ingrese Usuario").set('label', 'OK');
        return false;
    } else if (pass.value === "") {
        pass.focus();
        alertify.alert("Error", "Digite su contraseña").set('label', 'OK');
        return false;
    } else {
        alertify.success("Usuario autenticado con exito");
    }
}