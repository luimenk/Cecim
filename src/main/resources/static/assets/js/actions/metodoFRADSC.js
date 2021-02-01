//Muestra la miniatura
function mostrar() {
    var archivo = document.getElementById("file").files[0];
    var reader = new FileReader();
    if (file) {
        reader.readAsDataURL(archivo);
        reader.onloadend = function () {
            document.getElementById("img").src = reader.result;
        }
    }
}

//1.- Primera etapa
function valida() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    document.getElementById("btnAceptar").disabled = true;
    var contador = 0;

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        if (valor === "") {
            contador++;
            break;
        } else {
            obj[clave] = valor;
        }
    }

    const url = document.URL;
    obj["id"] = url.substring(url.lastIndexOf('/') + 1);
    var formData = new FormData();

    formData.append('fraoit', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    $.getJSON("/FRADSC/idInterno/" + obj["idInternoMuestra"], function (result) {
        swal({
            title: "Error!",
            text: "Este ensayo ya fue realizado.",
            type: "error",
            showCancelButton: false,
            confirmButtonClass: "btn btn-info btn-fill",
            confirmButtonText: "Ok",
            closeOnConfirm: false,
        }, function () {
            window.location = "/lecturaQR";
        });
    }).fail(function () {
        if (contador !== 0) {
            swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
            document.getElementById("btnAceptar").disabled = false;
        } else {
            save(formData);
        }
    });
}

//2.- Primera etapa
function save(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRADSC', {
        method: 'post',
        body: myjson
    }).then(function (response) {
        if (response.status === 200) {
            swal({
                title: "¡Operación exitosa!",
                text: "Se ha registrado exitosamente.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listFRADSC";
            });
        } else {
            document.getElementById("btnAceptar").disabled = false;
            swal("¡Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        document.getElementById("btnAceptar").disabled = false;
        swal("¡Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

//1.- Segunda etapa
function valida5() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var contador = 0;
    document.getElementById("btnAceptar").disabled = true;

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        if (valor === "") {
            contador++;
            break;
        } else {
            obj[clave] = valor;
        }
    }

    const url = document.URL;
    obj["id"] = url.substring(url.lastIndexOf('/') + 1);

    var blob = document.getElementById("file").files[0];
    var formData = new FormData();

    formData.append("imagen", blob);
    formData.append('fradsc', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
        document.getElementById("btnAceptar").disabled = false;
    } else {
        save2(formData);
    }
}

//2.- Segunda etapa
function save2(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRADSC/finalizar', {
        method: 'post',
        body: myjson
    }).then(function (response) {
        if (response.status === 200) {
            swal({
                title: "¡Operación exitosa!",
                text: "Se ha registrado exitosamente.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listFRADSC";
            });
        } else {
            document.getElementById("btnAceptar").disabled = false;
            swal("¡Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        document.getElementById("btnAceptar").disabled = false;
        swal("¡Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

//1.- Modificar
function modificar() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    document.getElementById("btnAceptar").disabled = true;
    var contador = 0;

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }

    const url = document.URL;
    obj["id"] = url.substring(url.lastIndexOf('/') + 1);

    var blob = document.getElementById("file").files[0];
    var formData = new FormData();

    formData.append("imagen", blob);
    formData.append('fradsc', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    saveModificar(formData);
}

//2.- Modificar
function saveModificar(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRADSC/modificar', {
        method: 'post',
        body: myjson
    }).then(function (response) {
        if (response.status === 200) {
            swal({
                title: "¡Operación exitosa!",
                text: "Se ha modificado exitosamente.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listFRADSC";
            });
        } else if (response.status === 423) {
            swal({
                title: "¡Operación Rechazada!",
                text: "Se ha superado el número de modificaciones posibles.",
                type: "error",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listFRADSC";
            });
        } else {
            document.getElementById("btnAceptar").disabled = false;
            swal("¡Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        document.getElementById("btnAceptar").disabled = false;
        swal("¡Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function validaImprimir(valor){
    window.location = "/FRADSC/imprimir/" + valor;
}

function generarListaFoliosDSC() {
    window.location = "/FRADSC/imprimir3";
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function validaModificar(valor) {
    window.location = "/modificarFRADSC/" + valor;
}

function finalizarProceso(valor) {
    window.location = "/finalizarFRADSC/" + valor;
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio Técnica</th>' +
        '<th class="text-center">Folio Solicitud</th>' +
        '<th class="text-center">ID interno de la muestra</th>' +
        '<th class="text-center">Modificaciones restantes</th>' +
        '<th class="text-center">Estatus</th>' +
        '<th class="text-center">Detalles</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Folio Técnica</th>' +
        '<th class="text-center">Folio Solicitud</th>' +
        '<th class="text-center">ID interno de la muestra</th>' +
        '<th class="text-center">Modificaciones restantes</th>' +
        '<th class="text-center">Estatus</th>' +
        '<th class="text-center">Detalles</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/FRADSC", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.metodoMuestra.folioTecnica + '</td>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.idInternoMuestra + '</td>' +
                '<td class="text-center">' + field.cantidadModificaciones + '</td>';
            if (field.estatus === "INICIADO") {
                tbl +=
                    '<td class="text-center"><button class="btn btn-danger" onclick="finalizarProceso(' + field.idFRADSC + ')"><i class="fa fa-flag"></i> Finalizar</button></td>' +
                    '<td class="text-center"><button class="btn btn-default" disabled><i class="fa fa-eye"></i> Ver detalles</button></td>' +
                    '<td class="text-center">' +
                    '.' +
                    '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center">' + field.estatus + '</td>' +
                    '<td class="text-center"><button class="btn btn-default" disabled><i class="fa fa-eye"></i> Ver detalles</button></td>' +
                    '<td class="text-center">' +

                    '<button type="submit" class="btn btn-sm btn-info" title="Imprimir" onclick="validaImprimir(' + field.idFRADSC + ')"><i class="fa fa-print"></i> </button>' +


                    '<button type="submit" class="btn btn-sm btn-warning" title="Modificar" onclick="validaModificar(' + field.idFRADSC + ')"><i class="fa fa-edit"></i> </button>' +

                    '</td>' +
                    '</tr>';
            }
        });
        tbl += '</tbody>';
        $("#fradscTable").append(tbl);
        $('#fradscTable').DataTable({
            "pagingType": "full_numbers",
            "lengthMenu": [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ],
            responsive: true,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Buscar Registros",
            }
        });
    });
}