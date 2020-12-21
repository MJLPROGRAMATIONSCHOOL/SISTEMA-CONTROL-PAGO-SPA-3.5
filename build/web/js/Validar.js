$(document).ready(function () {
    var tabla = $("table#TablaOficina"),
            mdlOfi = $("#mdlOficina"),
            frm = $("#frmOficina"),
            txtNombre = $("#txtNombre"),
            txtDireccion = $("#txtDireccion"),
            chkEstado = $("#chkVigencia"),
            _IDTEMP = -1,
            _ACCION = "agregarOficina";

    mdlOfi.on("show.bs.modal", function () {
        frm[0].reset();
    });

    tabla.on("click", ".btn-warning", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        leerOficina(id);
        mdlOfi.modal("show");
    });

    tabla.on("click", ".btn-danger", function () {
        var id = $(this).parents("tr").children()[0].textContent;
        eliminarOficina(id);
    });


    frm.on("submit", function (e) {
        e.preventDefault();
        var obj = {
            Nombre: txtNombre.val(),
            Direccion: txtDireccion.val(),
            Estado: chkEstado[0].checked,
            IdOficina: _IDTEMP
        };
        registrarOficina(JSON.stringify(obj));
    });

    function eliminarOficina(idTemp) {
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
                        url: "../oficina?accion=eliminarOficina",
                        type: 'POST',
                        dataType: 'json',
                        data: {id: idTemp},
                        success: function (data) {
                            swal("Eliminado!", data.msj, "success");
                            listarOficinas();
                        }
                    });
                } else {
                    swal("Cancelado", "Operación cancelada!", "error");
                }
            });
    }
    ;

    function registrarOficina(json) {
        $.ajax({
            url: "../oficina?accion=" + _ACCION,
            type: 'POST',
            dataType: 'json',
            data: {of: json},
            success: function (data) {
                swal("Mensaje del Sistema", data.msj, "success");
                listarOficinas();
                frm[0].reset();
                mdlOfi.modal("hide");
            }
        });
    }
    ;

    function leerOficina(idTemp) {
        _IDTEMP = idTemp;
        _ACCION = "editarOficina";
        $.ajax({
            url: "../oficina?accion=leerOficina",
            type: 'POST',
            dataType: 'json',
            data: {id: idTemp},
            success: function (data) {
                txtNombre.val(data.Nombre);
                txtDireccion.val(data.Direccion);
                chkEstado[0].checked = data.Estado;
            }
        });
    }
    ;

    function listarOficinas() {
        $.ajax({
            url: "../oficina?accion=listarOficina",
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                var tpl = "";
                for (var i = 0; i < data.length; i++) {
                    tpl += "<tr>"
                            + "<td>" + data[i].IdOficina + "</td>"
                            + "<td>" + data[i].Nombre + "</td>"
                            + "<td>" + data[i].Direccion + "</td>"
                            + "<td style=\"text-align: center\">" + (data[i].Estado === true ? '<span class =\"badge badge-blue-active\">Surcusal Disponible</span>' : '<span class =\"badge badge-red-active\">Surcusal No Disponible</span>') + "</td>"
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
    listarOficinas();
});


