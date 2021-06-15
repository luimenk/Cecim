function valida() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
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
            console.log("Esto está mal");
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

    //var myjson = JSON.stringify(obj);

    $.getJSON("/FRAES/idInterno/" + obj["idInternoMuestra"], function (result) {
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
            formData.append('fraes', new Blob([JSON.stringify(obj)], {
                type: "application/json"
            }));
            save(formData);
        }
    });
}

function save(myjson) {
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAES', {
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
                window.location = "/listFRAES";
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
    window.location = "/FRAES/imprimir1/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function cambiarNumero() {
    let cambio = document.getElementById("normaReferencia").value;

    if (cambio === "ASTM D6988-13") {
        document.getElementById("numeroMediciones").value = "3";
    }

    if (cambio === "NMX-E-003-NYCE-2020") {
        document.getElementById("numeroMediciones").value = "10";
    }

    var mostrarDato = "";
    var repeticiones = document.getElementById("numeroMediciones").value;
    if (repeticiones === "10") {
        mostrarDato += '<div class="card">' +
            '                            <div class="card-header text-center">' +
            '                                <h4 class="card-title">Resultados</h4>' +
            '                            </div>' +
            '                            <div class="card-body" style="overflow-x: scroll">' +
            '                                <div class="row">' +
            '                                    <table class="table table-hover table-striped">' +
            '                                        <thead>' +
            '                                        <tr>' +
            '                                            <th class="text-center">Número</th>' +
            '                                            <th class="text-center">Largo (mm)</th>' +
            '                                            <th class="text-center">Ancho (mm)</th>' +
            '                                        </tr>' +
            '                                        </thead>' +
            '                                        <tbody>' +
            '                                        <tr>' +
            '                                            <td class="text-center">1</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo1" id="largo1"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho1" id="ancho1"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">2</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo2" id="largo2"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho2" id="ancho2"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">3</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo3" id="largo3"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho3" id="ancho3"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">4</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo4" id="largo4"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho4" id="ancho4"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">5</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo5" id="largo5"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho5" id="ancho5"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">6</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo6" id="largo6"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho6" id="ancho6"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">7</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo7" id="largo7"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho7" id="ancho7"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">8</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo8" id="largo8"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho8" id="ancho8"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">9</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo9" id="largo9"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho9" id="ancho9"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">10</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo10" id="largo10"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho10" id="ancho10"></td>' +
            '                                        </tr>' +
            '                                        </tbody>' +
            '                                    </table>' +
            '                                </div>' +
            '                            </div>' +
            '                            <div class="card-footer">' +
            '                                <h6 class="text-danger">* Favor de verificar tu información. Recuerda utilizar 3 decimales</h6>' +
            '                            </div>' +
            '                        </div>';
    }
    if (repeticiones === "3") {
        mostrarDato += '<div class="card">' +
            '                            <div class="card-header text-center">' +
            '                                <h4 class="card-title">Resultados</h4>' +
            '                            </div>' +
            '                            <div class="card-body" style="overflow-x: scroll">' +
            '                                <div class="row">' +
            '                                    <table class="table table-hover table-striped">' +
            '                                        <thead>' +
            '                                        <tr>' +
            '                                            <th class="text-center">Número</th>' +
            '                                            <th class="text-center">Largo (mm)</th>' +
            '                                            <th class="text-center">Ancho (mm)</th>' +
            '                                        </tr>' +
            '                                        </thead>' +
            '                                        <tbody>' +
            '                                        <tr>' +
            '                                            <td class="text-center">1</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo1" id="largo1"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho1" id="ancho1"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">2</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo2" id="largo2"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho2" id="ancho2"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">3</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo3" id="largo3"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho3" id="ancho3"></td>' +
            '                                        </tr>' +
            '                                        </tbody>' +
            '                                    </table>' +
            '                                </div>' +
            '                            </div>' +
            '                            <div class="card-footer">' +
            '                                <h6 class="text-danger">* Favor de verificar tu información. Recuerda utilizar 3 decimales</h6>' +
            '                            </div>' +
            '                        </div>';
    }

    $("#tablaResultados").empty().append(mostrarDato);
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
    $.getJSON("/FRAES", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>' +
                '<td class="text-center">' + field.fechaFinalAnalisis + '</td>' +
                '<td class="text-center">' + field.temperatura + '</td>' +
                '<td class="text-center">' + field.humedadRelativa + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAES + ')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAES + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAES + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#espesorTable").append(tbl);
        $('#espesorTable').DataTable({
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