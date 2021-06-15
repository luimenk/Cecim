function valida() {
    var obj = {};
    var clave;
    var valor;
    var contador = 0;
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

    const url = document.URL;
    obj["id"] = url.substring(url.lastIndexOf('/') + 1);

    var blob = document.getElementById("file").files[0];
    var blob2 = document.getElementById("file2").files[0];

    const signature = document.getElementById("sig-dataUrl").value;
    var block = signature.split(";");

    // Get the content type of the image
    var contentType = block[0].split(":")[1];// In this case "image/gif"

    // get the real base64 content of the file
    var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."

    // Convert it to a blob to upload
    var blob1 = b64toBlob(realData, contentType);

    $.getJSON("/FRANCP/idInterno/" + obj["idInternoMuestra"], function (result) {
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
            var formData = new FormData();
            formData.append("imagen", blob);
            formData.append("imagen2", blob2);
            formData.append("signature", blob1);
            formData.append('francp', new Blob([JSON.stringify(obj)], {
                type: "application/json"
            }));
            save(formData);
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

function mostrar2(){
    var archivo2 = document.getElementById("file2").files[0];
    var reader2 = new FileReader();
    if (file2) {
        reader2.readAsDataURL(archivo2);
        reader2.onloadend = function () {
            document.getElementById("img2").src = reader2.result;
        }
    }
}

function save(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRANCP', {
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
                window.location = "/listFRANCP";
            });
        } else {
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        document.getElementById("btnAceptar").disabled = false;
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function validaImprimir(valor) {
    window.location = "/FRANCP/imprimir/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function tablaMuestras1() {
    let cantMuestra1 = document.getElementById("numeroTotalCapas1").value;
    let tabla =
        '<div class="row">' +
        '   <table class="table table-hover table-striped">' +
        '       <thead>' +
        '       <tr>' +
        '           <th class="text-center">Capa</th>' +
        '           <th class="text-center">Espesor por microscopía (um)</th>' +
        '       </tr>' +
        '       </thead>' +
        '       <tbody>';

    for (let i = 1; i <= cantMuestra1; i++) {
        tabla +=
            '<tr>' +
            '   <td class="text-center">' + i + '</td>' +
            '   <td class="text-center">' +
            '       <input type="number" class="form-control"' +
            '              name="1espesorPorMicroscopia' + i + '" id="1espesorPorMicroscopia' + i + '"></td>' +
            '</tr>';
    }
    tabla +=
        '       </tbody>' +
        '   </table>' +
        '</div>';

    $("#tablaMuestras1").empty().append(tabla);
}

function tablaMuestras2() {
    let cantMuestra2 = document.getElementById("numeroTotalCapas2").value;
    let tabla =
        '<div class="row">' +
        '   <table class="table table-hover table-striped">' +
        '       <thead>' +
        '       <tr>' +
        '           <th class="text-center">Capa</th>' +
        '           <th class="text-center">Espesor por microscopía (um)</th>' +
        '       </tr>' +
        '       </thead>' +
        '       <tbody>';

    for (let i = 1; i <= cantMuestra2; i++) {
        tabla +=
            '<tr>' +
            '   <td class="text-center">' + i + '</td>' +
            '   <td class="text-center">' +
            '       <input type="number" class="form-control"' +
            '              name="2espesorPorMicroscopia' + i + '" id="2espesorPorMicroscopia' + i + '"></td>' +
            '</tr>';
    }
    tabla +=
        '       </tbody>' +
        '   </table>' +
        '</div>';

    $("#tablaMuestras2").empty().append(tabla);
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
    $.getJSON("/FRANCP", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>' +
                '<td class="text-center">' + field.fechaFinalAnalisis + '</td>' +
                '<td class="text-center">' + field.temperatura + '</td>' +
                '<td class="text-center">' + field.humedadRelativa + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRANCP + ')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRANCP + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRANCP + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#francpTable").append(tbl);
        $('#francpTable').DataTable({
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