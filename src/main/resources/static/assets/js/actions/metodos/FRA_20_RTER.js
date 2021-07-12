function valida() {
    var contador = 0;
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");

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
    var myjson = JSON.stringify(obj);

    $.getJSON("/FRARTER/busquedaFolio/" + obj["folioTecnica"], function (result) {
        swal({
            title: "¡Alerta!",
            text: "Este ensayo ya fue registrado, favor de consultar la lista de registros.",
            type: "warning",
            showCancelButton: false,
            confirmButtonClass: "btn btn-info btn-fill",
            confirmButtonText: "Ok",
            closeOnConfirm: false,
        }, function () {
            window.location = "/listFRARTER";

        });
    }).fail(function () {
        if (contador !== 0) {
            swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
        } else {
            document.getElementById("btnAceptar").disabled = true;

            save(myjson);
        }
    });
}

function validaAgregar() {
    document.getElementById("btnAceptar").disabled = true;
    var contador = 0;
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");

    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);

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

    obj["id"] = id;

    var formData = new FormData();
    formData.append('frarter', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    saveAgregar(formData, id);
}

function validaTerminar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    document.getElementById("btnAceptar").disabled = true;

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }

    obj["id"] = id;

    const signature = document.getElementById("sig-dataUrl").value;
    var block = signature.split(";");

    // Get the content type of the image
    var contentType = block[0].split(":")[1];// In this case "image/gif"

    // get the real base64 content of the file
    var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."

    // Convert it to a blob to upload
    var blob1 = b64toBlob(realData, contentType);

    var formData = new FormData();
    formData.append("signature", blob1);
    formData.append('frarter', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    saveTerminar(formData);
}

function saveTerminar(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRARTER/terminar', {
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
                window.location = "/listFRARTER";
            });
        } else {
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        document.getElementById("btnAceptar").disabled = false;
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function saveAgregar(myjson, id) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRARTER/agregar', {
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
                window.location = "/verFRARTER/" + id;
            });
        } else {
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function save(myjson) {
    $.ajax({
        type: 'POST',
        url: '/FRARTER',
        data: myjson,
        cache: false,
        contentType: "application/json",
        processData: false,
        success: function (data) {
            console.log("success");
            console.log(data);
            swal({
                title: "Registrado!",
                text: "Se ha sido registrado exitosamente.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listFRARTER";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            document.getElementById("btnAceptar").disabled = false;
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
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

function iniciarProceso(valor) {
    $.ajax({
        type: 'POST',
        url: '/FRARTER/iniciar/' + valor,
        cache: false,
        contentType: "application/json",
        processData: false,
        success: function (data) {
            console.log("success");
            console.log(data);
            swal({
                title: "Iniciado!",
                text: "Se inició el método.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listFRARTER";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function validaImprimir(valor) {
    window.location = "/FRARTER/imprimir/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function vistaAgregar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.getJSON("/FRARTER/" + id, function (result) {
        if (result.estatus === "terminado") {
            swal("¡Alerta!", "Este ensayo ya fue finalizado", "warning");
        } else {
            window.location = "/agregarFRARTER/" + id;
        }
    });
}

function verInfo(valor) {
    window.location = "/verFRARTER/" + valor;
}

function volver() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/verFRARTER/" + id;
}

function volver2() {
    window.location = "/listFRARTER";
}

function terminar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.getJSON("/FRARTER/" + id, function (result) {
        if (result.estatus === "terminado") {
            swal("¡Alerta!", "Este ensayo ya fue finalizado", "warning");
        } else {
            window.location = "/terminarFRARTER/" + id;
        }
    });
}

function verFirma(valor) {
    $.getJSON("/FRARTER/individual/" + valor, function (result) {
        swal({
            title: 'Rúbrica',
            html: '<img src="' + result.rubrica + '">'
        })
    });
}

function verFoto(valor) {
    $.getJSON("/FRARTER/individual/" + valor, function (result) {
        swal({
            title: 'Evidencia fotográfica',
            html: '<img src="' + result.pathImage + '" width="320" height="240">'
        })
    });
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio Solicitud</th>' +
        '<th class="text-center">Fecha de inicio análisis</th>' +
        '<th class="text-center">Fecha final de análisis</th>' +
        '<th class="text-center">Temperatura</th>' +
        '<th class="text-center">Humedad Relativa</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Folio Solicitud</th>' +
        '<th class="text-center">Fecha de inicio análisis</th>' +
        '<th class="text-center">Fecha final de análisis</th>' +
        '<th class="text-center">Temperatura</th>' +
        '<th class="text-center">Humedad Relativa</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/resistenciaRasgado", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>' +
                '<td class="text-center">' + field.fechaFinalAnalisis + '</td>' +
                '<td class="text-center">' + field.temperatura + '</td>' +
                '<td class="text-center">' + field.humedadRelativa + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRARTER + ')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRARTER + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRARTER + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#frarterTable").append(tbl);
        $('#frarterTable').DataTable({
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

function cargarTabla2() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    let columna = 1;
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Medición / ID UTM</th>' +
        '<th class="text-center">E1 (mm)</th>' +
        '<th class="text-center">E2 (mm)</th>' +
        '<th class="text-center">E3 (mm)</th>' +
        '<th class="text-center">Espesor promedio (mm)</th>' +
        '<th class="text-center">Fuerza de fluencia a la tensión (MPa)</th>' +
        '<th class="text-center">Elongación a la ruptura (%)</th>' +
        '<th class="text-center">Resistencia a la tensión (MPa)</th>' +
        '<th class="text-center">Pendiente (m)</th>' +
        '<th class="text-center">Coeficiente de correlación de Pearson, (r<sup>2</sup>)</th>' +
        '<th class="text-center">Módulo elástico<sup>*</sup> (MPa)</th>' +
        '<th class="text-center">Modificar</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRARTER/getAll1By/" + id, function (result) {
        for (let i = 0; i < 5; i++) {
            tbl +=
                '<tr>';
            if (result[i] === undefined) {
                tbl +=
                    '<td class="text-center">' + columna + ' / </td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center">' + columna + ' / ' + result[i].medicion + '</td>' +
                    '<td class="text-center">' + result[i].e1 + '</td>' +
                    '<td class="text-center">' + result[i].e2 + '</td>' +
                    '<td class="text-center">' + result[i].e3 + '</td>' +
                    '<td class="text-center">' + result[i].promedioEspesor + '</td>' +
                    '<td class="text-center">' + result[i].fuerzaFluenciaTension + '</td>' +
                    '<td class="text-center">' + result[i].elongacionRuptura + '</td>' +
                    '<td class="text-center">' + result[i].resistenciaTension + '</td>' +
                    '<td class="text-center">' + result[i].pendiente + '</td>' +
                    '<td class="text-center">' + result[i].coeficienteCorrelacionPearson + '</td>' +
                    '<td class="text-center">' + result[i].moduloElastico + '</td>' +
                    '<td class="text-center"><button class="btn btn-warning" onclick="modificarData(' + result[i].idFRARTERDATA01 + ')"><i class="fa fa-pencil-square-o"></i>Modificar</button></td>' +
                    '</tr>';
            }
            columna = columna + 1;
        }
        tbl += '</tbody>';
        tbl += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Medición / ID UTM</th>' +
            '<th class="text-center">E1 (mm)</th>' +
            '<th class="text-center">E2 (mm)</th>' +
            '<th class="text-center">E3 (mm)</th>' +
            '<th class="text-center">Espesor promedio (mm)</th>' +
            '<th class="text-center">Fuerza de fluencia a la tensión (MPa)</th>' +
            '<th class="text-center">Elongación a la ruptura (%)</th>' +
            '<th class="text-center">Resistencia a la tensión (MPa)</th>' +
            '<th class="text-center">Pendiente (m)</th>' +
            '<th class="text-center">Coeficiente de correlación de Pearson, (r<sup>2</sup>)</th>' +
            '<th class="text-center">Módulo elástico<sup>*</sup> (MPa)</th>' +
            '<th class="text-center">Modificar</th>' +
            '</tr>' +
            '</tfoot>';
        $("#frarterTable2").append(tbl);
        $('#frarterTable2').DataTable({
            "pagingType": "full_numbers",
            "lengthMenu": [
                [14, 28, 42, -1],
                [14, 28, 42, "All"]
            ],
            responsive: false,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Buscar Registros",
            }
        });
    });
    //Cargar tabla para medición 2
    let columna2 = 1;
    var tbl2 =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Medición / ID UTM</th>' +
        '<th class="text-center">E1 (mm)</th>' +
        '<th class="text-center">E2 (mm)</th>' +
        '<th class="text-center">E3 (mm)</th>' +
        '<th class="text-center">Espesor promedio (mm)</th>' +
        '<th class="text-center">Fuerza de fluencia a la tensión (MPa)</th>' +
        '<th class="text-center">Elongación a la ruptura (%)</th>' +
        '<th class="text-center">Resistencia a la tensión (MPa)</th>' +
        '<th class="text-center">Pendiente (m)</th>' +
        '<th class="text-center">Coeficiente de correlación de Pearson, (r<sup>2</sup>)</th>' +
        '<th class="text-center">Módulo elástico<sup>*</sup> (MPa)</th>' +
        '<th class="text-center">Modificar</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRARTER/getAll2By/" + id, function (result) {
        for (let i = 0; i < 5; i++) {
            tbl2 +=
                '<tr>';
            if (result[i] === undefined) {
                tbl2 +=
                    '<td class="text-center">' + columna2 + ' / </td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '</tr>';
            } else {
                tbl2 +=
                    '<td class="text-center">' + columna2 + ' / ' + result[i].medicion + '</td>' +
                    '<td class="text-center">' + result[i].e1 + '</td>' +
                    '<td class="text-center">' + result[i].e2 + '</td>' +
                    '<td class="text-center">' + result[i].e3 + '</td>' +
                    '<td class="text-center">' + result[i].promedioEspesor + '</td>' +
                    '<td class="text-center">' + result[i].fuerzaFluenciaTension + '</td>' +
                    '<td class="text-center">' + result[i].elongacionRuptura + '</td>' +
                    '<td class="text-center">' + result[i].resistenciaTension + '</td>' +
                    '<td class="text-center">' + result[i].pendiente + '</td>' +
                    '<td class="text-center">' + result[i].coeficienteCorrelacionPearson + '</td>' +
                    '<td class="text-center">' + result[i].moduloElastico + '</td>' +
                    '<td class="text-center"><button class="btn btn-warning" onclick="modificarData(' + result[i].idFRARTERDATA02 + ')"><i class="fa fa-pencil-square-o"></i>Modificar</button></td>' +
                    '</tr>';
            }
            columna2 = columna2 + 1;
        }
        tbl2 += '</tbody>';
        tbl2 += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Medición / ID UTM</th>' +
            '<th class="text-center">E1 (mm)</th>' +
            '<th class="text-center">E2 (mm)</th>' +
            '<th class="text-center">E3 (mm)</th>' +
            '<th class="text-center">Espesor promedio (mm)</th>' +
            '<th class="text-center">Fuerza de fluencia a la tensión (MPa)</th>' +
            '<th class="text-center">Elongación a la ruptura (%)</th>' +
            '<th class="text-center">Resistencia a la tensión (MPa)</th>' +
            '<th class="text-center">Pendiente (m)</th>' +
            '<th class="text-center">Coeficiente de correlación de Pearson, (r<sup>2</sup>)</th>' +
            '<th class="text-center">Módulo elástico<sup>*</sup> (MPa)</th>' +
            '<th class="text-center">Modificar</th>' +
            '</tr>' +
            '</tfoot>';
        $("#frarterTable3").append(tbl2);
        $('#frarterTable3').DataTable({
            "pagingType": "full_numbers",
            "lengthMenu": [
                [14, 28, 42, -1],
                [14, 28, 42, "All"]
            ],
            responsive: false,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Buscar Registros",
            }
        });
    });
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio técnica</th>' +
        '<th class="text-center">ID interno de la muestra</th>' +
        '<th class="text-center">Folio Solicitud</th>' +
        '<th class="text-center">Fecha de inicio análisis</th>' +
        '<th class="text-center">Iniciar</th>' +
        '<th class="text-center">Visualizar</th>' +
        '<th class="text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRARTER", function (result) {
        $.each(result, function (i, field) {
            if (field.estatus === "inicio") {

            }
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioTecnica + '</td>' +
                '<td class="text-center">' + field.idInternoMuestra + '</td>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>';
            if (field.estatus === "inicio") {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" onclick="iniciarProceso(' + field.idFRARTER + ')"><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" disabled><i class="fa fa-eye"></i>Ver detalles</button></td>';
            } else if (field.estatus === "terminado") {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verInfo(' + field.idFRARTER + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>' +
                    '<td class="text-center">' +
                    '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRARTER + ')"><i class="fa fa-print"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRARTER + ')"><i class="fa fa-edit"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRARTER + ')"><i class="fa fa-times"></i></a>' +
                    '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verInfo(' + field.idFRARTER + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>';
            }
        });
        tbl += '</tbody>';
        tbl += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Folio técnica</th>' +
            '<th class="text-center">ID interno de la muestra</th>' +
            '<th class="text-center">Folio Solicitud</th>' +
            '<th class="text-center">Fecha de inicio análisis</th>' +
            '<th class="text-center">Visualizar</th>' +
            '<th class="text-center">Iniciar</th>' +
            '<th class="text-center">Acciones</th>' +
            '</tr>' +
            '</tfoot>';
        $("#frarterTable").append(tbl);
        $('#frarterTable').DataTable({
            "pagingType": "full_numbers",
            "lengthMenu": [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ],
            responsive: false,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Buscar Registros",
            }
        });
    });
}