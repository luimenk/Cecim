function valida() {
    var obj = {};
    var clave;
    var valor;
    var contador = 0;
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

    const url = document.URL;
    obj["id"] = url.substring(url.lastIndexOf('/') + 1);

    const signature = document.getElementById("sig-dataUrl").value;
    var block = signature.split(";");

    // Get the content type of the image
    var contentType = block[0].split(":")[1];// In this case "image/gif"

    // get the real base64 content of the file
    var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."

    // Convert it to a blob to upload
    var blob1 = b64toBlob(realData, contentType);

    //var myjson = JSON.stringify(obj);

    $.getJSON("/FRAGR/idInterno/" + obj["idInternoMuestra"], function (result) {
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
            formData.append("signature", blob1);
            formData.append('fragr', new Blob([JSON.stringify(obj)], {
                type: "application/json"
            }));
            save(formData);
        }
    });
}

function save(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAGR', {
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
                window.location = "/listFRAGR";
            });
        } else {
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
            document.getElementById("btnAceptar").disabled = false;
        }
    }).catch(function (err) {
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        document.getElementById("btnAceptar").disabled = false;
    });
}

function validaImprimir(valor){
    window.location = "/FRAGR/imprimir/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

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
    $.getJSON("/FRAGR", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>' +
                '<td class="text-center">' + field.fechaFinalAnalisis + '</td>' +
                '<td class="text-center">' + field.temperatura + '</td>' +
                '<td class="text-center">' + field.humedadRelativa + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAGR + ')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAGR + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAGR + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#fragrTable").append(tbl);
        $('#fragrTable').DataTable({
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