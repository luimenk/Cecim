function valida() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var contador = 0;
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
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

    $.getJSON("/FRAICO/busquedaFolio/" + obj["folioTecnica"], function (result) {
        swal({
            title: "¡Alerta!",
            text: "Este ensayo ya fue registrado, favor de consultar la lista de registros.",
            type: "warning",
            showCancelButton: false,
            confirmButtonClass: "btn btn-info btn-fill",
            confirmButtonText: "Ok",
            closeOnConfirm: false,
        }, function () {
            window.location = "/listFRAICO";

        });
    }).fail(function () {
        if (contador !== 0) {
            swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
            document.getElementById("btnAceptar").disabled = false;
        } else {
            let myjson = JSON.stringify(obj);
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
    formData.append('fraico', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    saveAgregar(formData, id);
}

function validaTerminar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var contador = 0;
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
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

    obj["id"] = id;

    const signature = document.getElementById("sig-dataUrl").value;
    var block = signature.split(";");

    // Get the content type of the image
    var contentType = block[0].split(":")[1];// In this case "image/gif"

    // get the real base64 content of the file
    var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."

    // Convert it to a blob to upload
    var blob1 = b64toBlob(realData, contentType);

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
        document.getElementById("btnAceptar").disabled = false;
    } else {
        var formData = new FormData();
        formData.append("signature", blob1);
        formData.append('fraico', new Blob([JSON.stringify(obj)], {
            type: "application/json"
        }));
        saveTerminar(formData);
    }
}

function saveTerminar(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAICO/terminar', {
        method: 'post',
        body: myjson
    }).then(function (response) {
        if (response.status === 200) {
            swal({
                title: "¡Realizado!",
                text: "Se ha finalizado exitosamente.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listFRAICO";
            });
        } else {
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        document.getElementById("btnAceptar").disabled = false;
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function saveAgregar(myjson){
    console.log(myjson)
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAICO/agregar', {
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
                window.location = "/listFRAICO";
            });
        } else {
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function saveAgregar(myjson, id) {
    console.log(myjson)
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAICO/agregar', {
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
                window.location = "/verFRAICO/"+id;
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
        url: '/FRAICO',
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
                window.location = "/listFRAICO";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function validaImprimir(valor){
    window.location = "/FRAICO/imprimir/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function iniciarProceso(valor){
    $.ajax({
        type: 'POST',
        url: '/FRAICO/iniciar/'+valor,
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
                window.location = "/listFRAICO";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function vistaAgregar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.getJSON("/FRAICO/" + id, function (result) {
        if (result.estatus === "terminado") {
            swal("¡Alerta!", "Este ensayo ya fue finalizado", "warning");
        } else {
            window.location = "/agregarFRAICO/" + id;
        }
    });
}

function verInfo(valor){
    window.location = "/verFRAICO/" + valor;
}

function volver() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/verFRAICO/" + id;
}

function volver2() {
    window.location = "/listFRAICO";
}

function terminar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.getJSON("/FRAICO/" + id, function (result) {
        if (result.estatus === "terminado") {
            swal("¡Alerta!", "Este ensayo ya fue finalizado", "warning");
        } else {
            window.location = "/terminarFRAICO/" + id;
        }
    });
}

function verImagen(){

}

function modificarData(valor) {
    alert("Próximamente disponible :)");
}

function cargarTabla2() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);

    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Tiempo de exposición (h)</th>' +
        '<th class="text-center">E1</th>' +
        '<th class="text-center">E2</th>' +
        '<th class="text-center">E3</th>' +
        '<th class="text-center">Promedio</th>' +
        '<th class="text-center">Medición 1</th>' +
        '<th class="text-center">Medición 2</th>' +
        '<th class="text-center">Promedio</th>' +
        '<th class="text-center">Modificar</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRAICO/getAllBy/" + id, function (result) {
        let sumaCiclos = 0;
        let columna = 0;
        let puntero = 0;
        $.each(result, function (i, field) {
            columna = columna + parseInt(field.tiempoExposicion);
            tbl +=
                '<tr>' +
                '<td class="text-center">' + columna + '</td>' +
                '<td class="text-center">' + field.e1 + '</td>' +
                '<td class="text-center">' + field.e2 + '</td>' +
                '<td class="text-center">' + field.e3 + '</td>' +
                '<td class="text-center">' + field.promedioEspesor + '</td>' +
                '<td class="text-center">' + field.medicion1 + '</td>' +
                '<td class="text-center">' + field.medicion2 + '</td>' +
                '<td class="text-center">' + field.promedioCarbonillo + '</td>' +
                '<td class="text-center"><button class="btn btn-warning" onclick="modificarData(' + result[puntero].idFRAICODATA + ')"><i class="fa fa-pencil-square-o"></i>Modificar</button></td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        tbl += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Tiempo de exposición (h)</th>' +
            '<th class="text-center">E1</th>' +
            '<th class="text-center">E2</th>' +
            '<th class="text-center">E3</th>' +
            '<th class="text-center">Promedio</th>' +
            '<th class="text-center">Medición 1</th>' +
            '<th class="text-center">Medición 2</th>' +
            '<th class="text-center">Promedio</th>' +
            '<th class="text-center">Modificar</th>' +
            '</tr>' +
            '</tfoot>';
        /*for (let i = 0; i < 56; i++) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + columna + '</td>';
            if (result[puntero] === undefined) {
                tbl +=
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '<td class="text-center">' + "." + '</td>' +
                    '</tr>';
            } else if (sumaCiclos === columna){
                let date = new Date(result[puntero].createdAt);
                tbl +=
                    '<td class="text-center">' + date.toLocaleString() + '</td>' +
                    '<td class="text-center"><button class="btn btn-info" onclick="verFirma(' + result[puntero].idFRAICODATA + ')"><i class="fa fa-pencil"></i>Ver Firma</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verFoto(' + result[puntero].idFRAICODATA + ')"><i class="fa fa-picture-o"></i>Ver Foto</button></td>';
                if (result[puntero].imagenSeleccionada === "Si"){
                    tbl +=
                        '<td class="text-center"><button class="btn btn-success" onclick="imagenNo(' + result[puntero].idFRAICODATA + ')"><i class="fa fa-check-circle"></i>Si</button></td>';
                } else {
                    tbl +=
                        '<td class="text-center"><button class="btn btn-default" onclick="imagenSi(' + result[puntero].idFRAICODATA + ')"><i class="fa fa-times-circle"></i>No</button></td>';
                }
                tbl +=
                    '<td class="text-center"><button class="btn btn-warning" onclick="modificarData(' + result[puntero].idFRAICODATA + ')"><i class="fa fa-pencil-square-o"></i>Modificar</button></td>' +
                    '</tr>';

                console.log(result.length);

                if (puntero+1<result.length){
                    sumaCiclos = parseInt(sumaCiclos) + parseInt(result[puntero+1].tiempoExposicion);
                }
                puntero++;
            } else {
                tbl +=
                    '<td class="text-center">' + "N/A" + '</td>' +
                    '<td class="text-center">' + "N/A" + '</td>' +
                    '<td class="text-center">' + "N/A" + '</td>' +
                    '<td class="text-center">' + "N/A" + '</td>' +
                    '<td class="text-center">' + "N/A" + '</td>' +
                    '</tr>';
            }
            columna = columna + 48;
        }*/
        /*tbl += '</tbody>';
        tbl += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Tiempo de exposición (h)</th>' +
            '<th class="text-center">E1</th>' +
            '<th class="text-center">E2</th>' +
            '<th class="text-center">E3</th>' +
            '<th class="text-center">Promedio</th>' +
            '<th class="text-center">Medición 1</th>' +
            '<th class="text-center">Medición 2</th>' +
            '<th class="text-center">Promedio</th>' +
            '<th class="text-center">Modificar</th>' +
            '</tr>' +
            '</tfoot>';*/
        $("#fraicoTable2").append(tbl);
        $('#fraicoTable2').DataTable({
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
    $.getJSON("/FRAICO", function (result) {
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
                    '<td class="text-center"><button class="btn btn-success" onclick="iniciarProceso(' + field.idFRAICO + ')"><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" disabled><i class="fa fa-eye"></i>Ver detalles</button></td>';
            } else if (field.estatus === "terminado") {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verInfo(' + field.idFRAICO + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>' +
                    '<td class="text-center">' +
                    '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAICO + ')"><i class="fa fa-print"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAICO + ')"><i class="fa fa-edit"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAICO + ')"><i class="fa fa-times"></i></a>' +
                    '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verInfo(' + field.idFRAICO + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>';
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
        $("#fraicoTable").append(tbl);
        $('#fraicoTable').DataTable({
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