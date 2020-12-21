$(document).ready(function () {
    var tabla = $("table#TablaColaborador"),
            mdlcol = $("#mdlColaborador"),
            frm = $("#frmColaborador"),
            txtRut = $("#txtRut"),
            txtNombre = $("#txtNombre"),
            txtApellido = $("#txtApellido"),
            txtUsuario= $("#txtUsuario"),
            txtDireccion = $("#txtDireccion"),
            txtTelefono = $("#txtTelefono"),
            txtCorreo = $("#txtCorreo"),
            txtPass =$("#txtPass"),
            chkEstado = $("#chkVigencia"),
            cbOficina = $("#cbOficina"),
            cbCargo = $("#cbCargo"),
            _ACCION = "agregarColaborador";

    mdlcol.on("show.bs.modal", function () {
        frm[0].reset();
    });

    tabla.on("click", ".btn-warning", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        leerColaborador(id);
        mdlcol.modal("show");
    });

    tabla.on("click", ".btn-danger", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        eliminarColaborador(id);
    });

    frm.on("submit", function (e) {
        e.preventDefault();
        var obj = {
            RutColaborador: txtRut.val(),
            Nombre: txtNombre.val(),
            Apellido: txtApellido.val(),
            Usuario: txtUsuario.val(),
            Direccion: txtDireccion.val(),
            Telefono: txtTelefono.val(),
            Correo: txtCorreo.val(),
            Password: txtPass.val(),
            Estado: chkEstado[0].checked
        };
        registrarColaborador(JSON.stringify(obj));
    });

    function eliminarColaborador(RutColaborador) {
        swal({
            title: "Esta seguro de eliminar?",
            text: "Una vez eliminado ya no estara disponible!",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Si, eliminar!",
            cancelButtonText: "No, cancelar!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        $.ajax({
                            url: "../colaborador?accion=eliminarColaborador",
                            type: 'POST',
                            dataType: 'json',
                            data: {id: RutColaborador},
                            success: function (data) {
                                swal("Eliminado!", data.msj, "success");
                                listarColaboradores();
                            }
                        });
                    } else {
                        swal("Cancelado", "Operación cancelada!", "error");
                    }
                });
    }
    ;

    function registrarColaborador(json) {
        $.ajax({
            url: "../colaborador?accion=" + _ACCION,
            type: 'POST',
            dataType: 'json',
            data: {cola: json,
                IdOficina: cbOficina.val(),
                Codigo: cbCargo.val()
            },
            success: function (data) {
                swal("Mensaje del Sistema", data.msj, "success");
                listarColaboradores();
                frm[0].reset();
                mdlcol.modal("hide");
            }
        });
    }
    ;

    function leerColaborador(idTemp) {
        _ACCION = "editarColaborador";
        $.ajax({
            url: "../colaborador?accion=leerColaborador",
            type: 'POST',
            dataType: 'json',
            data: {id: idTemp},
            success: function (data) {
                txtRut.val(data.RutColaborador);
                txtNombre.val(data.Nombre);
                txtApellido.val(data.Apellido);
                txtUsuario.val(data.Usuario);
                txtDireccion.val(data.Direccion);
                txtTelefono.val(data.Telefono);
                txtCorreo.val(data.Correo);
                txtPass.val(data.Password);
                chkEstado[0].checked = data.Estado;
                cbOficina.val(data.oficinas.IdOficina);
                cbCargo.val(data.cargos.IdCargo);
            }
        });
    }
    ;

    function listarColaboradores() {
        $.ajax({
            url: "../colaborador?accion=listarColaborador",
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                var tpl = "";
                for (var i = 0; i < data.length; i++) {
                    tpl += "<tr>"
                            + "<td>" + data[i].RutColaborador + "</td>"
                            + "<td>" + data[i].Nombre + "</td>"
                            + "<td>" + data[i].Apellido + "</td>"
                            + "<td>" + data[i].Direccion + "</td>"
                            + "<td>" + data[i].Telefono + "</td>"
                            + "<td>" + data[i].Correo + "</td>"
                            + "<td>" + data[i].cargos.NombreCargo + "</td>"
                            + "<td style=\"text-align: center\">" + (data[i].Estado === true ? '<span class =\"badge badge-blue-active\">Disponible</span>' : '<span class =\"badge badge-red-active\">No disponible</span>') + "</td>"
                            + "<td style=\"text-align: center\"><button class=\"btn btn-warning\">"
                            + "<span class=\"fa fa-pencil\"></span> Editar</button> "
                            + "<button class=\"btn btn-danger\">"
                            + "<span class=\"glyphicon glyphicon-trash\"></span> Eliminar</button></td>"
                            + "</tr>";
                }
                tabla.find("tbody").html(tpl);
                tabla.dataTable();
            }
        });
    }
    ;
    function cargarCombos() {
        $.get("../colaborador?accion=cargarDatosCombo",
                //{accion: "cargarDatosCombo"},
                        function (data) {
                            listaOfi = data.oficinas;
                            var cadenaCombo = "<option value>:: Seleccione</option>";
                            for (var i = 0; i < listaOfi.length; i++) {
                                cadenaCombo += listaOfi[i].IdOficina, listaOfi[i].Nombre;
                            }
                            cbOficina.html(cadenaCombo);
                        });
            }

    cargarCombos();
    listarColaboradores();
});