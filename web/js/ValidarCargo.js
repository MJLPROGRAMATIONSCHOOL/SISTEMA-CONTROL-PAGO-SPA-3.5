$(document).ready(function () {
    var tabla = $("table#TablaCargo"),
            mdlCar = $("#mdlCargo"),
            frm = $("#frmCargo"),
            txtNombre = $("#txtNombre"),
            chkEstado = $("#chkVigencia"),
            _IDTEMP = -1,
            _ACCION = "agregarCargo";

    mdlCar.on("show.bs.modal", function () {
        frm[0].reset();
    });

    tabla.on("click", ".btn-warning", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        leerCargo(id);
        mdlCar.modal("show");
    });

    tabla.on("click", ".btn-danger", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        eliminarCargo(id);
    });


    frm.on("submit", function (e) {
        e.preventDefault();
        var obj = {
            NombreCargo: txtNombre.val(),
            Estado: chkEstado[0].checked,
            IdCargo: _IDTEMP
        };
        registrarCargo(JSON.stringify(obj));
    });

    function eliminarCargo(idTemp) {
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
                        url: "../cargo?accion=eliminarCargo",
                        type: 'POST',
                        dataType: 'json',
                        data: {id: idTemp},
                        success: function (data) {
                            swal("Eliminado!", data.msj, "success");
                            listarCargos();
                        }
                    });
                } else {
                    swal("Cancelado", "Operación cancelada!", "error");
                }
            });
    }
    ;

    function registrarCargo(json) {
        $.ajax({
            url: "../cargo?accion=" + _ACCION,
            type: 'POST',
            dataType: 'json',
            data: {car: json},
            success: function (data) {
                swal("Mensaje del Sistema", data.msj, "success");
                listarCargos();
                frm[0].reset();
                mdlCar.modal("hide");
            }
        });
    }
    ;

    function leerCargo(idTemp) {
        _IDTEMP = idTemp;
        _ACCION = "editarCargo";
        $.ajax({
            url: "../cargo?accion=leerCargo",
            type: 'POST',
            dataType: 'json',
            data: {id: idTemp},
            success: function (data) {
                txtNombre.val(data.NombreCargo);
                chkEstado[0].checked = data.Estado;
            }
        });
    }
    ;

    function listarCargos() {
        $.ajax({
            url: "../cargo?accion=listarCargo",
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                var tpl = "";
                for (var i = 0; i < data.length; i++) {
                    tpl += "<tr>"
                            + "<td>" + data[i].IdCargo + "</td>"
                            + "<td>" + data[i].NombreCargo + "</td>"
                            + "<td style=\"text-align: center\">" + (data[i].Estado === true ? '<span class =\"badge badge-blue-active\">Cargo Disponible</span>' : '<span class =\"badge badge-red-active\">Cargo No Disponible</span>') + "</td>"
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
    listarCargos();
});


