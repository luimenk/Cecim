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

    $.getJSON("/FRADI/idInterno/" + obj["idInternoMuestra"], function (result) {
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
            formData.append('fradi', new Blob([JSON.stringify(obj)], {
                type: "application/json"
            }));
            save(formData);
        }
    });
}

function cambiarNumero() {
    let cambio = document.getElementById("tipoCamiseta").value;
    let campos;
    if (cambio === "Si"){
        campos ='<div class="card ">' +
            '                            <div class="card-header text-center">' +
            '                                <h4 class="card-title">Resultados</h4>' +
            '                            </div>' +
            '                            <div class="card-body" style="overflow-x: scroll">' +
            '                                <div class="row">' +
            '                                    <table class="table table-hover table-striped">' +
            '                                        <thead>' +
            '                                        <tr>' +
            '                                            <th class="text-center">Medición</th>' +
            '                                            <th class="text-center">Largo (cm)</th>' +
            '                                            <th class="text-center">Ancho (cm)</th>' +
            '                                            <th class="text-center">Fuelle derecho (cm)</th>' +
            '                                            <th class="text-center">Fuelle izquierdo (cm)</th>' +
            '                                        </tr>' +
            '                                        </thead>' +
            '                                        <tbody>' +
            '                                        <tr>' +
            '                                            <td class="text-center">1</td>' +
            '                                            <!--<td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo1" id="largo1"' +
            '                                                                           min="0" max="9999999" step=\'0.001\' value=\'0.000\'' +
            '                                                                           onkeydown="tresDecimales()"></td>-->' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo1" id="largo1"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho1" id="ancho1"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleDerecho1" id="fuelleDerecho1"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleIzquierdo1" id="fuelleIzquierdo1"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">2</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo2" id="largo2"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho2" id="ancho2"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleDerecho2" id="fuelleDerecho2"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleIzquierdo2" id="fuelleIzquierdo2"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">3</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo3" id="largo3"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho3" id="ancho3"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleDerecho3" id="fuelleDerecho3"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleIzquierdo3" id="fuelleIzquierdo3"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">4</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo4" id="largo4"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho4" id="ancho4"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleDerecho4" id="fuelleDerecho4"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleIzquierdo4" id="fuelleIzquierdo4"></td>' +
            '                                        </tr>' +
            '                                        <tr>' +
            '                                            <td class="text-center">5</td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo5" id="largo5"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="ancho5" id="ancho5"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleDerecho5" id="fuelleDerecho5"></td>' +
            '                                            <td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="fuelleIzquierdo5" id="fuelleIzquierdo5"></td>' +
            '                                        </tr>' +
            '                                        </tbody>' +
            '                                    </table>' +
            '                                </div>' +
            '                            </div>' +
            '                            <div class="card-footer ">' +
            '                                <h6 class="text-danger">* Favor de verificar tu información. Recuerda utilizar 2 decimales</h6>' +
            '                            </div>' +
            '                        </div>';
    }
    
    if (cambio === "No") {
        campos ='<div class="card ">' +
            '                            <div class="card-header text-center">' +
            '                                <h4 class="card-title">Resultados</h4>' +
            '                            </div>' +
            '                            <div class="card-body" style="overflow-x: scroll">' +
            '                                <div class="row">' +
            '                                    <table class="table table-hover table-striped">' +
            '                                        <thead>' +
            '                                        <tr>' +
            '                                            <th class="text-center">Medición</th>' +
            '                                            <th class="text-center">Largo (cm)</th>' +
            '                                            <th class="text-center">Ancho (cm)</th>' +
            '                                        </tr>' +
            '                                        </thead>' +
            '                                        <tbody>' +
            '                                        <tr>' +
            '                                            <td class="text-center">1</td>' +
            '                                            <!--<td class="text-center"><input type="number" class="form-control"' +
            '                                                                           name="largo1" id="largo1"' +
            '                                                                           min="0" max="9999999" step=\'0.001\' value=\'0.000\'' +
            '                                                                           onkeydown="tresDecimales()"></td>-->' +
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
            '                                        </tbody>' +
            '                                    </table>' +
            '                                </div>' +
            '                            </div>' +
            '                            <div class="card-footer ">' +
            '                                <h6 class="text-danger">* Favor de verificar tu información. Recuerda utilizar 2 decimales</h6>' +
            '                            </div>' +
            '                        </div>';
    }

    $("#tablaDatos").empty().append(campos);
}

function save(myjson) {
    /*$.ajax({
        type: 'POST',
        url: '/FRADI',
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
                window.location = "/listFRADI";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            document.getElementById("btnAceptar").disabled = false;
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });*/
    console.log(myjson);
    var boundary = Math.random().toString().substr(2);
    fetch('/FRADI', {
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
                window.location = "/listFRADI";
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
    window.location = "/FRADI/imprimir1/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function tresDecimales() {
    const input = document.querySelector("#largo1");
    const currentValue = input.value;
    const regex = /^\d{0,9}(\.\d{1,3})?$/;
    //const regex = /^(\d+)$|^(\d+\.{1}\d{2})$/;

    setTimeout(function(){
        const newValue = input.value

        if(!regex.test(newValue))
            input.value = currentValue;
    }, 0);
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
    $.getJSON("/FRADI", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>' +
                '<td class="text-center">' + field.fechaFinalAnalisis + '</td>' +
                '<td class="text-center">' + field.temperatura + '</td>' +
                '<td class="text-center">' + field.humedadRelativa + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRADI + ')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRADI + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRADI + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#dimensionTable").append(tbl);
        $('#dimensionTable').DataTable({
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