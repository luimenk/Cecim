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

    const signature = document.getElementById("sig-dataUrl").value;
    var block = signature.split(";");

    // Get the content type of the image
    var contentType = block[0].split(":")[1];// In this case "image/gif"

    // get the real base64 content of the file
    var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."

    // Convert it to a blob to upload
    var blob1 = b64toBlob(realData, contentType);

    //var blob = document.getElementById("file").files[0];
    var formData = new FormData();

    $.getJSON("/FRAIF/idInterno/" + obj["idInternoMuestra"], function (result) {
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
            //formData.append("imagen", blob);
            formData.append("signature", blob1);
            formData.append('fraif', new Blob([JSON.stringify(obj)], {
                type: "application/json"
            }));
            save(formData);
        }
    });
}

function save(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAIF', {
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
                window.location = "/listFRAIF";
            });
        } else {
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function validaImprimir(valor){
    window.location = "/FRAIF/imprimir/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function mostrarTablaResultados() {
    var mostrarDato = "";
    var repeticiones = document.getElementById("indiceFuidez").value;

    if (repeticiones === "No especificado") {
        mostrarDato += 
            '<div class="card">' +
            '    <div class="card-header">' +
            '        <h4 class="card-title">Resultados</h4>' +
            '    </div>' +
            '    <div class="card-body" style="overflow-x: scroll">' +
            '        <div class="row">' +
            '            <table class="table table-hover table-striped">' +
            '                <thead>' +
            '                <tr>' +
            '                    <th class="text-center">Número de corte</th>' +
            '                    <th class="text-center">W = Peso del filamento (g)</th>' +
            '                    <th class="text-center">MFI = Índice de fluidez (g/10min)</th>' +
            '                    <th class="text-center">MFI = FC x W</th>' +
            '                </tr>' +
            '                </thead>' +
            '                <tbody>' +
            '                <tr>' +
            '                    <td class="text-center">1</td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="pesoFilamento0" id="pesoFilamento0">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="indiceFluidez0" id="indiceFluidez0">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="mfi0" id="mfi0">' +
            '                    </td>' +
            '                </tr>' +
            '                <tr>' +
            '                    <td class="text-center">2</td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="pesoFilamento1" id="pesoFilamento1">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="indiceFluidez1" id="indiceFluidez1">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="mfi1" id="mfi1">' +
            '                    </td>' +
            '                </tr>' +
            '                <tr>' +
            '                    <td class="text-center">3</td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="pesoFilamento2" id="pesoFilamento2">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="indiceFluidez2" id="indiceFluidez2">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="mfi2" id="mfi2">' +
            '                    </td>' +
            '                </tr>' +
            '                <tr>' +
            '                    <td class="text-center">4</td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="pesoFilamento3" id="pesoFilamento3">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="indiceFluidez3" id="indiceFluidez3">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="mfi3" id="mfi3">' +
            '                    </td>' +
            '                </tr>' +
            '                </tbody>' +
            '            </table>' +
            '        </div>' +
            '    </div>' +
            '    <div class="card-footer">' +
            '        <h6 class="text-danger">* Favor de verificar tu información.</h6>' +
            '    </div>' +
            '</div>';
    } else {
        mostrarDato +=
            '<div class="card">' +
            '    <div class="card-header">' +
            '        <h4 class="card-title">Resultados</h4>' +
            '    </div>' +
            '    <div class="card-body" style="overflow-x: scroll">' +
            '        <div class="row">' +
            '            <table class="table table-hover table-striped">' +
            '                <thead>' +
            '                <tr>' +
            '                    <th class="text-center">Número de corte</th>' +
            '                    <th class="text-center">W = Peso del filamento (g)</th>' +
            '                    <th class="text-center">MFI = Índice de fluidez (g/10min)</th>' +
            '                </tr>' +
            '                </thead>' +
            '                <tbody>' +
            '                <tr>' +
            '                    <td class="text-center">1</td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="pesoFilamento0" id="pesoFilamento0">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="indiceFluidez0" id="indiceFluidez0">' +
            '                    </td>' +
            '                </tr>' +
            '                <tr>' +
            '                    <td class="text-center">2</td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="pesoFilamento1" id="pesoFilamento1">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="indiceFluidez1" id="indiceFluidez1">' +
            '                    </td>' +
            '                </tr>' +
            '                <tr>' +
            '                    <td class="text-center">3</td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="pesoFilamento2" id="pesoFilamento2">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="indiceFluidez2" id="indiceFluidez2">' +
            '                    </td>' +
            '                </tr>' +
            '                <tr>' +
            '                    <td class="text-center">4</td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="pesoFilamento3" id="pesoFilamento3">' +
            '                    </td>' +
            '                    <td class="text-center">' +
            '                        <input type="number" class="form-control" name="indiceFluidez3" id="indiceFluidez3">' +
            '                    </td>' +
            '                </tr>' +
            '                </tbody>' +
            '            </table>' +
            '        </div>' +
            '    </div>' +
            '    <div class="card-footer">' +
            '        <h6 class="text-danger">* Favor de verificar tu información.</h6>' +
            '    </div>' +
            '</div>';
    }
    $("#tablaRepeticiones").empty().append(mostrarDato);
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
    $.getJSON("/FRAIF", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>' +
                '<td class="text-center">' + field.fechaFinalAnalisis + '</td>' +
                '<td class="text-center">' + field.temperatura + '</td>' +
                '<td class="text-center">' + field.humedadRelativa + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAIF + ')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAIF + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAIF + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#fraifTable").append(tbl);
        $('#fraifTable').DataTable({
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