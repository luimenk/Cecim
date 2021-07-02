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

    $.getJSON("/FRAEAUV/busquedaFolio/" + obj["folioTecnica"], function (result) {
        swal({
            title: "¡Alerta!",
            text: "Este ensayo ya fue registrado, favor de consultar la lista de registros.",
            type: "warning",
            showCancelButton: false,
            confirmButtonClass: "btn btn-info btn-fill",
            confirmButtonText: "Ok",
            closeOnConfirm: false,
        }, function () {
            window.location = "/listFRAEAUV";

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
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    const blob = document.getElementById("file").files[0];

    const signature = document.getElementById("sig-dataUrl").value;
    var block = signature.split(";");

    // Get the content type of the image
    var contentType = block[0].split(":")[1];// In this case "image/gif"

    // get the real base64 content of the file
    var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."

    // Convert it to a blob to upload
    var blob1 = b64toBlob(realData, contentType);

    let nombreAnalista = document.getElementById('nombreAnalista').value;
    let imagenSeleccionada = document.getElementById('imagenSeleccionada').value;
    let tiempoExposicion = document.getElementById('tiempoExposicion').value;
    document.getElementById("btnAceptar").disabled = true;
    var obj = {};
    obj["id"] = id;
    obj["nombreAnalista"] = nombreAnalista;
    obj["imagenSeleccionada"] = imagenSeleccionada;
    obj["tiempoExposicion"] = tiempoExposicion;
    var formData = new FormData();
    formData.append("imagen", blob);
    formData.append("signature", blob1);
    formData.append("datos", new Blob([JSON.stringify({
        "nombreAnalista": nombreAnalista,
        "id": id,
        "imagenSeleccionada": imagenSeleccionada,
        "tiempoExposicion": tiempoExposicion
    })], {
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
        formData.append('fraeauv', new Blob([JSON.stringify(obj)], {
            type: "application/json"
        }));
        saveTerminar(formData);
    }
}

function saveTerminar(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAEAUV/terminar', {
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
                window.location = "/listFRAEAUV";
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
    console.log(myjson)
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAEAUV/agregar', {
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
                window.location = "/verFRAEAUV/"+id;
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
        url: '/FRAEAUV',
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
                window.location = "/listFRAEAUV";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
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

function validaImprimir(valor) {
    window.location = "/FRAEAUV/imprimir/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function iniciarProceso(valor) {
    $.ajax({
        type: 'POST',
        url: '/FRAEAUV/iniciar/' + valor,
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
                window.location = "/listFRAEAUV";
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
    $.getJSON("/FRAEAUV/" + id, function (result) {
        if (result.estatus === "terminado") {
            swal("¡Alerta!", "Este ensayo ya fue finalizado", "warning");
        } else {
            window.location = "/agregarFRAEAUV/" + id;
        }
    });
}

function verInfo(valor) {
    window.location = "/verFRAEAUV/" + valor;
}

function volver() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/verFRAEAUV/" + id;
}

function volver2() {
    window.location = "/listFRAEAUV";
}

function terminar() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.getJSON("/FRAEAUV/" + id, function (result) {
        if (result.estatus === "terminado") {
            swal("¡Alerta!", "Este ensayo ya fue finalizado", "warning");
        } else {
            window.location = "/terminarFRAEAUV/" + id;
        }
    });
}

function verFirma(valor){
    $.getJSON("/FRAEAUV/individual/" + valor, function (result) {
        swal({
            title: 'Rúbrica',
            html: '<img src="' + result.rubrica + '">'
        })
    });
}

function verFoto(valor){
    $.getJSON("/FRAEAUV/individual/" + valor, function (result) {
        swal({
            title: 'Evidencia fotográfica',
            html: '<img src="' + result.pathImage + '" width="320" height="240">'
        })
    });
}

function modificarData(valor) {
    alert("Próximamente disponible :)");
}

function imagenSi(valor) {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.ajax({
        type: 'POST',
        url: '/FRAEAUV/imagenSi/' + valor,
        cache: false,
        contentType: "application/json",
        processData: false,
        success: function (data) {
            console.log("success");
            console.log(data);
            swal({
                title: "¡Realizado!",
                text: "Está imagen si se imprimirá.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/verFRAEAUV/"+id;
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function imagenNo(valor) {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    $.ajax({
        type: 'POST',
        url: '/FRAEAUV/imagenNo/' + valor,
        cache: false,
        contentType: "application/json",
        processData: false,
        success: function (data) {
            console.log("success");
            console.log(data);
            swal({
                title: "¡Realizado!",
                text: "Está imagen no se imprimirá.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/verFRAEAUV/"+id;
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function cargarTabla2() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);

    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Tiempo de exposición (h)</th>' +
        '<th class="text-center">Fecha de toma de fotografía</th>' +
        '<th class="text-center">Rubrica del analísta</th>' +
        '<th class="text-center">Fotografía tomada</th>' +
        '<th class="text-center">¿Se imprime esta imagen?</th>' +
        '<th class="text-center">Modificar</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRAEAUV/getAllBy/" + id, function (result) {
        let sumaCiclos = 0;
        let columna = 0;
        let puntero = 0;
        for (let i = 0; i < 56; i++) {
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
                    '<td class="text-center"><button class="btn btn-info" onclick="verFirma(' + result[puntero].idFRAEAUVDATA + ')"><i class="fa fa-pencil"></i>Ver Firma</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verFoto(' + result[puntero].idFRAEAUVDATA + ')"><i class="fa fa-picture-o"></i>Ver Foto</button></td>';
                if (result[puntero].imagenSeleccionada === "Si"){
                    tbl +=
                        '<td class="text-center"><button class="btn btn-success" onclick="imagenNo(' + result[puntero].idFRAEAUVDATA + ')"><i class="fa fa-check-circle"></i>Si</button></td>';
                } else {
                    tbl +=
                        '<td class="text-center"><button class="btn btn-default" onclick="imagenSi(' + result[puntero].idFRAEAUVDATA + ')"><i class="fa fa-times-circle"></i>No</button></td>';
                }
                tbl +=
                    '<td class="text-center"><button class="btn btn-warning" onclick="modificarData(' + result[puntero].idFRAEAUVDATA + ')"><i class="fa fa-pencil-square-o"></i>Modificar</button></td>' +
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
        }
        tbl += '</tbody>';
        tbl += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Tiempo de exposición (h)</th>' +
            '<th class="text-center">Fecha de toma de fotografía</th>' +
            '<th class="text-center">Rubrica del analísta</th>' +
            '<th class="text-center">Fotografía tomada</th>' +
            '<th class="text-center">¿Se imprime esta imagen?</th>' +
            '<th class="text-center">Modificar</th>' +
            '</tr>' +
            '</tfoot>';
        $("#fraeauvTable2").append(tbl);
        $('#fraeauvTable2').DataTable({
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
    $.getJSON("/FRAEAUV", function (result) {
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
                    '<td class="text-center"><button class="btn btn-success" onclick="iniciarProceso(' + field.idFRAEAUV + ')"><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" disabled><i class="fa fa-eye"></i>Ver detalles</button></td>';
            } else if (field.estatus === "terminado") {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verInfo(' + field.idFRAEAUV + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>' +
                    '<td class="text-center">' +
                    '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAEAUV + ')"><i class="fa fa-print"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAEAUV + ')"><i class="fa fa-edit"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAEAUV + ')"><i class="fa fa-times"></i></a>' +
                    '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="verInfo(' + field.idFRAEAUV + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>';
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
        $("#fraeauvTable").append(tbl);
        $('#fraeauvTable').DataTable({
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