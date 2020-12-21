$(document).ready(function () {
    var tabla = $("table#TablaEmpresa"),
            mdlEmp = $("#mdlEmpresa"),
            frm = $("#frmEmpresa"),
            txtRut = $("#txtRut"),
            txtRazonSocial = $("#txtRazonSocial"),
            txtDireccion = $("#txtDireccion"),
            txtGiro = $("#txtGiro"),
            cbOficina = $("#cbOficina"),
            chkEstado = $("#chkVigencia"),
            _ACCION = "agregarEmpresa";

    tabla.on("click", ".btn-warning", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        leerEmpresa(id);
        mdlEmp.modal("show");
    });

    tabla.on("click", ".btn-danger", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        eliminarEmpresa(id);
    });


    frm.on("submit", function (e) {
        e.preventDefault();
        var obj = {
            RutEmpresa: txtRut.val(),
            RazonSocial: txtRazonSocial.val(),
            Direccion: txtDireccion.val(),
            Giro: txtGiro.val(),
            Estado: chkEstado[0].checked
        };
        registrarEmpresa(JSON.stringify(obj));
    });

    function eliminarEmpresa(RutEmpresa) {
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
                            url: "../empresa?accion=eliminarEmpresa",
                            type: 'POST',
                            dataType: 'json',
                            data: {id: RutEmpresa},
                            success: function (data) {
                                swal("Eliminado!", data.msj, "success");
                                listarEmpresas();
                            }
                        });
                    } else {
                        swal("Cancelado", "Operación cancelada!", "error");
                    }
                });
    }
    ;

    function registrarEmpresa(json) {
        $.ajax({
            url: "../empresa?accion=" + _ACCION,
            type: 'POST',
            dataType: 'json',
            data: {empre: json,
                IdOficina: cbOficina.val()
            },
            success: function (data) {
                swal("Mensaje del Sistema", data.msj, "success");
                listarEmpresas();
                frm[0].reset();
                mdlEmp.modal("hide");
            }
        });
    }
    ;

    function leerEmpresa(idTemp) {
        _ACCION = "editarEmpresa";
        $.ajax({
            url: "../empresa?accion=leerEmpresa",
            type: 'POST',
            dataType: 'json',
            data: {id: idTemp},
            success: function (data) {
                txtRut.val(data.RutEmpresa);
                txtRazonSocial.val(data.RazonSocial);
                txtDireccion.val(data.Direccion);
                txtGiro.val(data.Giro);
                cbOficina.val(data.oficinas.IdOficina);
                chkEstado[0].checked = data.Estado;
            }
        });
    }
    ;

    function listarEmpresas() {
        $.ajax({
            url: "../empresa?accion=listarEmpresa",
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                var tpl = "";
                for (var i = 0; i < data.length; i++) {
                    tpl += "<tr>"
                            + "<td>" + data[i].RutEmpresa + "</td>"
                            + "<td>" + data[i].RazonSocial + "</td>"
                            + "<td>" + data[i].Direccion + "</td>"
                            + "<td>" + data[i].Giro + "</td>"
                            + "<td>" + data[i].oficinas.Nombre + "</td>"
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
        $.get("../empresa?accion=cargarDatosCombo",
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
    listarEmpresas();
});