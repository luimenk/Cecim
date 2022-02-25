function valida() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
    var repeticiones = document.getElementById("repeticiones").value;
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

    for (var i = 0; i < test2.length; i++) {
        clave = test2[i].getAttribute("id");
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
    obj["numeroRepeticiones"] = repeticiones;

    //var blob = document.getElementById("file").files[0];
    var formData = new FormData();

    //formData.append("imagen", blob);
    formData.append('fraoit', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    $.getJSON("/FRAOIT/idInterno/" + obj["idInternoMuestra"], function (result) {
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

function valida5() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    //var repeticiones = document.getElementById("repeticiones").value;
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
    //obj["numeroRepeticiones"] = repeticiones;

    const signature = document.getElementById("sig-dataUrl").value;
    var block = signature.split(";");

    // Get the content type of the image
    var contentType = block[0].split(":")[1];// In this case "image/gif"

    // get the real base64 content of the file
    var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."

    // Convert it to a blob to upload
    var blob1 = b64toBlob(realData, contentType);

    var blob = document.getElementById("file").files[0];
    var formData = new FormData();

    formData.append("imagen", blob);
    formData.append("signature", blob1);
    formData.append('fraoit', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
        document.getElementById("btnAceptar").disabled = false;
    } else {
        save2(formData);
    }
}

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
    formData.append('fraoit', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    saveModificar(formData);
}

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

function mostrarRepeticiones() {
    var mostrarDato = "";
    var repeticiones = document.getElementById("repeticiones").value;
    if (repeticiones === "1") {
        mostrarDato += "<div class=\"card\">" +
            "                                <div class=\"card-header\">" +
            "                                    <h4 class=\"card-title text-center\">Espesor(mm)</h4>" +
            "                                </div>" +
            "                                <div class=\"card-body\" style=\"overflow-x: scroll\">" +
            "                                    <div class=\"row\">" +
            "                                        <table class=\"table table-hover table-striped\">" +
            "                                            <thead>" +
            "                                            <tr>" +
            "                                                <th class=\"text-center\"></th>" +
            "                                                <th class=\"text-center\"># Dobleces</th>" +
            "                                                <th class=\"text-center\">E1</th>" +
            "                                                <th class=\"text-center\">E2</th>" +
            "                                                <th class=\"text-center\">E3</th>" +
            "                                            </tr>" +
            "                                            </thead>" +
            "                                            <tbody>" +
            "                                            <tr>" +
            "                                                <td class=\"text-center\">M1</td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"noDobleces0\" id=\"noDobleces0\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e10\" id=\"e10\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e20\" id=\"e20\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e30\" id=\"e30\"" +
            "                                                                               required=\"required\"></td>" +
            "                                            </tr>" +
            "                                            </tbody>" +
            "                                        </table>" +
            "                                    </div>" +
            "                                </div>" +
            "                                <div class=\"card-footer\">" +
            "                                    <h6 class=\"text-danger\">* Favor de verificar tu información.</h6>" +
            "                                </div>" +
            "                            </div>";
        document.getElementById("pesom2").value = "N/A";
        document.getElementById("pesom2").disabled = true;
        document.getElementById("posicionm2").value = "N/A";
        document.getElementById("posicionm2").disabled = true;
    }
    if (repeticiones === "2") {
        mostrarDato += "<div class=\"card\">" +
            "                                <div class=\"card-header\">" +
            "                                    <h4 class=\"card-title\">Espesor(mm)</h4>" +
            "                                </div>" +
            "                                <div class=\"card-body\" style=\"overflow-x: scroll\">" +
            "                                    <div class=\"row\">" +
            "                                        <table class=\"table table-hover table-striped\">" +
            "                                            <thead>" +
            "                                            <tr>" +
            "                                                <th class=\"text-center\"></th>" +
            "                                                <th class=\"text-center\"># Dobleces</th>" +
            "                                                <th class=\"text-center\">E1</th>" +
            "                                                <th class=\"text-center\">E2</th>" +
            "                                                <th class=\"text-center\">E3</th>" +
            "                                            </tr>" +
            "                                            </thead>" +
            "                                            <tbody>" +
            "                                            <tr>" +
            "                                                <td class=\"text-center\">M1</td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"noDobleces0\" id=\"noDobleces0\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e10\" id=\"e10\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e20\" id=\"e20\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e30\" id=\"e30\"" +
            "                                                                               required=\"required\"></td>" +
            "                                            </tr>" +
            "                                            <tr>" +
            "                                                <td class=\"text-center\">M2</td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"noDobleces1\" id=\"noDobleces1\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e11\" id=\"e11\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e21\" id=\"e21\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"e31\" id=\"e31\"" +
            "                                                                               required=\"required\"></td>" +
            "                                            </tr>" +
            "                                            </tbody>" +
            "                                        </table>" +
            "                                    </div>" +
            "                                </div>" +
            "                                <div class=\"card-footer\">" +
            "                                    <h6 class=\"text-danger\">* Favor de verificar tu información.</h6>" +
            "                                </div>" +
            "                            </div>";
        document.getElementById("pesom2").value = "";
        document.getElementById("pesom2").disabled = false;
        document.getElementById("posicionm2").value = "";
        document.getElementById("posicionm2").disabled = false;
    }

    $("#tablaRepeticiones").empty().append(mostrarDato);
}

function save(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAOIT', {
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
                window.location = "/listFRAOIT";
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

function saveModificar(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAOIT/modificar', {
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
                window.location = "/listFRAOIT";
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
                window.location = "/listFRAOIT";
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

function save2(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAOIT/finalizar', {
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
                window.location = "/listFRAOIT";
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

function validaImprimir(valor) {
    window.location = "/FRAOIT/imprimir/" + valor;
}

function generarListaFoliosOIT() {
    window.location = "/FRAOIT/imprimir3";
}

function validaModificar(valor) {
    window.location = "/modificarFRAOIT/" + valor;
}

function finalizarProceso(valor) {
    window.location = "/finalizarFRAOIT/" + valor;
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
    $.getJSON("/FRAOIT", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.metodoMuestra.folioTecnica + '</td>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.idInternoMuestra + '</td>' +
                '<td class="text-center">' + field.cantidadModificaciones + '</td>';
            if (field.estatus === "INICIADO") {
                tbl +=
                    '<td class="text-center"><button class="btn btn-danger" onclick="finalizarProceso(' + field.idFRAOIT + ')"><i class="fa fa-flag"></i> Finalizar</button></td>' +
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

                    '<button type="submit" class="btn btn-sm btn-info" title="Imprimir" onclick="validaImprimir(' + field.idFRAOIT + ')"><i class="fa fa-print"></i> </button>' +


                    '<button type="submit" class="btn btn-sm btn-warning" title="Modificar" onclick="validaModificar(' + field.idFRAOIT + ')"><i class="fa fa-edit"></i> </button>' +

                    '</td>' +
                    '</tr>';
            }
        });
        tbl += '</tbody>';
        $("#fraoitTable").append(tbl);
        $('#fraoitTable').DataTable({
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
