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

    $.getJSON("/FRAHUM/busquedaFolio/" + obj["folioTecnica"], function (result) {
        swal({
            title: "¡Alerta!",
            text: "Este ensayo ya fue registrado, favor de consultar la lista de registros.",
            type: "warning",
            showCancelButton: false,
            confirmButtonClass: "btn btn-info btn-fill",
            confirmButtonText: "Ok",
            closeOnConfirm: false,
        }, function () {
            window.location = "/listFRAHUM";

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

    obj["id"] = id;

    var formData = new FormData();
    formData.append('frahum', new Blob([JSON.stringify(obj)], {
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
    formData.append('frahum', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    saveTerminar(formData);
}

function saveTerminar(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAHUM/terminar', {
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
                window.location = "/listFRAHUM";
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
    fetch('/FRAHUM/agregar', {
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
                window.location = "/verFRAHUM/" + id;
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
        url: '/FRAHUM',
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
                window.location = "/listFRAHUM";
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
        url: '/FRAHUM/iniciar/' + valor,
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
                window.location = "/listFRAHUM";
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
    window.location = "/FRAHUM/imprimir/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function vistaAgregar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.getJSON("/FRAHUM/" + id, function (result) {
        if (result.estatus === "terminado") {
            swal("¡Alerta!", "Este ensayo ya fue finalizado", "warning");
        } else {
            window.location = "/agregarFRAHUM/" + id;
        }
    });
}

function verInfo(valor) {
    window.location = "/verFRAHUM/" + valor;
}

function volver() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/verFRAHUM/" + id;
}

function volver2() {
    window.location = "/listFRAHUM";
}

function terminar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.getJSON("/FRAHUM/" + id, function (result) {
        if (result.estatus === "terminado") {
            swal("¡Alerta!", "Este ensayo ya fue finalizado", "warning");
        } else {
            window.location = "/terminarFRAHUM/" + id;
        }
    });
}

function verFirma(valor) {
    $.getJSON("/FRAHUM/individual/" + valor, function (result) {
        swal({
            title: 'Rúbrica',
            html: '<img src="' + result.rubrica + '">'
        })
    });
}

function verFoto(valor) {
    $.getJSON("/FRAHUM/individual/" + valor, function (result) {
        swal({
            title: 'Evidencia fotográfica',
            html: '<img src="' + result.pathImage + '" width="320" height="240">'
        })
    });
}

function cargarTabla2() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    let columna = 0;
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Tiempo de secado (h)</th>' +
        '<th class="text-center">Peso charola (g)</th>' +
        '<th class="text-center">Peso inicial (g)</th>' +
        '<th class="text-center">Tiempo de reposo (min)</th>' +
        '<th class="text-center">Peso final (g)</th>' +
        '<th class="text-center">Peso seco (g)</th>' +
        '<th class="text-center">Pi - Ps</th>' +
        '<th class="text-center">% Humedad</th>' +
//        '<th class="text-center">Agregar</th>' +
        '<th class="text-center">Modificar</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRAHUM/getAll1By/" + id, function (result) {
        for (let i = 0; i < 3; i++) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + columna + '</td>';
            if (result[i] === undefined) {
                tbl +=
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
//                    '<td class="text-center"><button class="btn btn-success" onclick="vistaAgregar()"><i class="fa fa-plus"></i>Agregar</button></td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center">' + result[i].pesoCharola + '</td>' +
                    '<td class="text-center">' + result[i].pesoInicial + '</td>' +
                    '<td class="text-center">' + result[i].tiempoReposo + '</td>' +
                    '<td class="text-center">' + result[i].pesoFinal + '</td>' +
                    '<td class="text-center">' + result[i].pesoSeco + '</td>' +
                    '<td class="text-center">' + result[i].pips + '</td>' +
                    '<td class="text-center">' + result[i].porcentajeHumedad + '</td>' +
//                    '<td class="text-center">.</td>' +
                    '<td class="text-center"><button class="btn btn-warning" onclick="modificarData(' + result[i].idFRAHUMDATA01 + ')"><i class="fa fa-pencil-square-o"></i>Modificar</button></td>' +
                    '</tr>';
            }
            columna = columna + 4;
        }
        tbl += '</tbody>';
        tbl += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Tiempo de secado (h)</th>' +
            '<th class="text-center">Peso charola (g)</th>' +
            '<th class="text-center">Peso inicial (g)</th>' +
            '<th class="text-center">Tiempo de reposo (min)</th>' +
            '<th class="text-center">Peso final (g)</th>' +
            '<th class="text-center">Peso seco (g)</th>' +
            '<th class="text-center">Pi - Ps</th>' +
            '<th class="text-center">% Humedad</th>' +
//            '<th class="text-center">Agregar</th>' +
            '<th class="text-center">Modificar</th>' +
            '</tr>' +
            '</tfoot>';
        $("#frahumTable2").append(tbl);
        $('#frahumTable2').DataTable({
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
    let columna2 = 0;
    var tbl2 =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Tiempo de secado (h)</th>' +
        '<th class="text-center">Peso charola (g)</th>' +
        '<th class="text-center">Peso inicial (g)</th>' +
        '<th class="text-center">Tiempo de reposo (min)</th>' +
        '<th class="text-center">Peso final (g)</th>' +
        '<th class="text-center">Peso seco (g)</th>' +
        '<th class="text-center">Pi - Ps</th>' +
        '<th class="text-center">% Humedad</th>' +
//        '<th class="text-center">Agregar</th>' +
        '<th class="text-center">Modificar</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRAHUM/getAll2By/" + id, function (result) {
        for (let i = 0; i < 3; i++) {
            tbl2 +=
                '<tr>' +
                '<td class="text-center">' + columna2 + '</td>';
            if (result[i] === undefined) {
                tbl2 +=
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
//                    '<td class="text-center"><button class="btn btn-success" onclick="vistaAgregar()"><i class="fa fa-plus"></i>Agregar</button></td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '</tr>';
            } else {
                tbl2 +=
                    '<td class="text-center">' + result[i].pesoCharola + '</td>' +
                    '<td class="text-center">' + result[i].pesoInicial + '</td>' +
                    '<td class="text-center">' + result[i].tiempoReposo + '</td>' +
                    '<td class="text-center">' + result[i].pesoFinal + '</td>' +
                    '<td class="text-center">' + result[i].pesoSeco + '</td>' +
                    '<td class="text-center">' + result[i].pips + '</td>' +
                    '<td class="text-center">' + result[i].porcentajeHumedad + '</td>' +
//                    '<td class="text-center">.</td>' +
                    '<td class="text-center"><button class="btn btn-warning" onclick="modificarData(' + result[i].idFRAHUMDATA02 + ')"><i class="fa fa-pencil-square-o"></i>Modificar</button></td>' +
                    '</tr>';
            }
            columna2 = columna2 + 4;
        }
        tbl2 += '</tbody>';
        tbl2 += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Tiempo de secado (h)</th>' +
            '<th class="text-center">Peso charola (g)</th>' +
            '<th class="text-center">Peso inicial (g)</th>' +
            '<th class="text-center">Tiempo de reposo (min)</th>' +
            '<th class="text-center">Peso final (g)</th>' +
            '<th class="text-center">Peso seco (g)</th>' +
            '<th class="text-center">Pi - Ps</th>' +
            '<th class="text-center">% Humedad</th>' +
//            '<th class="text-center">Agregar</th>' +
            '<th class="text-center">Modificar</th>' +
            '</tr>' +
            '</tfoot>';
        $("#frahumTable3").append(tbl2);
        $('#frahumTable3').DataTable({
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
    $.getJSON("/FRAHUM", function (result) {
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
                    '<td class="text-center"><button class="btn btn-success" onclick="iniciarProceso(' + field.idFRAHUM + ')"><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" disabled><i class="fa fa-eye"></i>Ver detalles</button></td>';
            } else if (field.estatus === "terminado") {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verInfo(' + field.idFRAHUM + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>' +
                    '<td class="text-center">' +
                    '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAHUM + ')"><i class="fa fa-print"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAHUM + ')"><i class="fa fa-edit"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAHUM + ')"><i class="fa fa-times"></i></a>' +
                    '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verInfo(' + field.idFRAHUM + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>';
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
        $("#frahumTable").append(tbl);
        $('#frahumTable').DataTable({
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