function valida() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
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

    var blob = document.getElementById("file").files[0];
    var formData = new FormData();

    formData.append("imagen", blob);
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
            "                                    <h4 class=\"card-title\">Resultados</h4>" +
            "                                </div>" +
            "                                <div class=\"card-body\" style=\"overflow-x: scroll\">" +
            "                                    <div class=\"row\">" +
            "                                        <table class=\"table table-hover table-striped\">" +
            "                                            <thead>" +
            "                                            <tr>" +
            "                                                <th class=\"text-center\">Repetición</th>" +
            "                                                <th class=\"text-center\">Espesor (mm)</th>" +
            "                                                <th class=\"text-center\">Peso (mg)</th>" +
            "                                                <th class=\"text-center\">Posición en el portador de muestras de DSC</th>" +
            "                                            </tr>" +
            "                                            </thead>" +
            "                                            <tbody>" +
            "                                            <tr>" +
            "                                                <td class=\"text-center\">1</td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"espesor1\" id=\"espesor1\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"peso1\" id=\"peso1\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"text\" class=\"form-control\"" +
            "                                                                               name=\"ppmdsc1\" id=\"ppmdsc1\"" +
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
    }
    if (repeticiones === "2") {
        mostrarDato += "<div class=\"card\">" +
            "                                <div class=\"card-header\">" +
            "                                    <h4 class=\"card-title\">Resultados</h4>" +
            "                                </div>" +
            "                                <div class=\"card-body\" style=\"overflow-x: scroll\">" +
            "                                    <div class=\"row\">" +
            "                                        <table class=\"table table-hover table-striped\">" +
            "                                            <thead>" +
            "                                            <tr>" +
            "                                                <th class=\"text-center\">Repetición</th>" +
            "                                                <th class=\"text-center\">Espesor (mm)</th>" +
            "                                                <th class=\"text-center\">Peso (mg)</th>" +
            "                                                <th class=\"text-center\">Posición en el portador de muestras de DSC</th>" +
            "                                            </tr>" +
            "                                            </thead>" +
            "                                            <tbody>" +
            "                                            <tr>" +
            "                                                <td class=\"text-center\">1</td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"espesor1\" id=\"espesor1\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"peso1\" id=\"peso1\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"text\" class=\"form-control\"" +
            "                                                                               name=\"ppmdsc1\" id=\"ppmdsc1\"" +
            "                                                                               required=\"required\"></td>" +
            "                                            </tr>" +
            "                                            <tr>" +
            "                                                <td class=\"text-center\">2</td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"espesor2\" id=\"espesor2\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"number\" class=\"form-control\"" +
            "                                                                               name=\"peso2\" id=\"peso2\"" +
            "                                                                               required=\"required\"></td>" +
            "                                                <td class=\"text-center\"><input type=\"text\" class=\"form-control\"" +
            "                                                                               name=\"ppmdsc2\" id=\"ppmdsc2\"" +
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
                title: "Registrado!",
                text: "Se ha sido registrado exitosamente.",
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
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        document.getElementById("btnAceptar").disabled = false;
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
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
                title: "Registrado!",
                text: "Se ha sido registrado exitosamente.",
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
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        document.getElementById("btnAceptar").disabled = false;
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function validaImprimir(valor) {
    window.location = "/FRAOIT/imprimir/" + valor;
}

function finalizarProceso(valor){
    window.location = "/finalizarFRAOIT/" + valor;
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio Técnica</th>' +
        '<th class="text-center">Folio Solicitud</th>' +
        '<th class="text-center">ID interno de la muestra</th>' +
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
                '<td class="text-center">' + field.idInternoMuestra + '</td>';
            if (field.estatus === "INICIADO") {
                tbl += '<td class="text-center"><button class="btn btn-danger" onclick="finalizarProceso(' + field.idFRAOIT + ')"><i class="fa fa-flag"></i>Finalizar</button></td>';
            } else {
                tbl += '<td class="text-center">'+ field.estatus +'</td>';
            }
            tbl +=
                '<td class="text-center"><button className="btn btn-danger" disabled><i className="fa fa-eye"></i>Ver detalles</button></td>'+
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAOIT + ')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAOIT + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAOIT + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
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
