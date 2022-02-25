var contMuestra = -1;

function agregarMuestra() {
    var cantMuestra = document.getElementById("cantidadMuestras").value;
    var muestraAdicional = "";
    var options = "";

    if (cantMuestra === "" || cantMuestra < 1) {
        swal("Alerta!", "Ingresa la cantidad de muestras a registrar.", "warning");
    } else {
        $.getJSON("/method", function (result) {
            $.each(result, function (i, field) {
                options += '<option value = "' + field.methodId + '"> ' + field.codigoMetodo + '</option>';
            });
        })
            .done(function () {
                for (let j = 0; j < cantMuestra; j++) {
                    muestraAdicional +=
                        '<div class="row">' +
                        '                    <div class="col-md-12">' +
                        '                        <div class="card ">' +
                        '                            <div class="card-header text-center">' +
                        '                                <h4 class="card-title">Información de la muestra ' + (j + 1) + '</h4>' +
                        '                            </div>' +
                        '                            <div class="card-body ">' +
                        '                                <div class="row">' +
                        '                                    <label class="col-sm-2 control-label">ID cliente de la muestra:</label>' +
                        '                                    <div class="col-sm-4 col-sm-offset-1">' +
                        '                                        <div class="form-group">' +
                        '                                            <input type="text" class="form-control"' +
                        '                                                   name="idClienteMuestra' + j + '" id="idClienteMuestra' + j + '" required="true">' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                    <label class="col-sm-2 control-label">Tipo de muestra:</label>' +
                        '                                    <div class="col-sm-4 col-sm-offset-1">' +
                        '                                        <div class="form-group">' +
                        '                                            <input type="text" class="form-control"' +
                        '                                                   name="tipoMuestra' + j + '" id="tipoMuestra' + j + '" required="true">' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                </div>' +
                        '                                <div class="row">' +
                        '                                    <label class="col-sm-2 control-label">Descripción de la muestra:</label>' +
                        '                                    <div class="col-sm-4 col-sm-offset-1">' +
                        '                                        <div class="form-group">' +
                        '                                            <input type="text" class="form-control"' +
                        '                                                   name="descripcionMuestra' + j + '" id="descripcionMuestra' + j + '" required="true">' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                    <label class="col-sm-2 control-label">Método Solicitado:</label>' +
                        '                                    <div class="col-sm-4 col-sm-offset-1">' +
                        '                                        <div class="form-group">' +
                        '                                            <select multiple class="selectpicker" name="metodo' + j + '" id="metodo' + j + '" ' +
                        '                                                    required="required"' +
                        '                                                    data-title="Selecciona un método..."' +
                        '                                                    data-style="btn-info btn-fill btn-block"' +
                        '                                                    data-menu-style="dropdown-blue"' +
                        '                                                    onchange="chequeo()">';
                    muestraAdicional += options;
                    muestraAdicional +=
                        '                                            </select>' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                </div>' +
                        '                                <div class="row">' +
                        '                                    <label class="col-sm-2 control-label">Lote:</label>' +
                        '                                    <div class="col-sm-4 col-sm-offset-1">' +
                        '                                        <div class="form-group">' +
                        '                                            <input type="text" class="form-control"' +
                        '                                                   name="lote' + j + '" id="lote' + j + '" required="true">' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                    <label class="col-sm-2 control-label">Condiciones Especiales:</label>' +
                        '                                    <div class="col-sm-4 col-sm-offset-1">' +
                        '                                        <div class="form-group">' +
                        '                                            <input type="text" class="form-control"' +
                        '                                                   name="condicionesEspeciales' + j + '" id="condicionesEspeciales' + j + '" ' +
                        '                                                   required="true">' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                    <div class="col-sm-4 col-sm-offset-1">' +
                        '                                        <div class="form-group">' +
                        '                                            <input type="hidden" class="form-control"' +
                        '                                                   name="cantidadMuestra' + j + '" ' +
                        '                                                   id="cantidadMuestra' + j + '" required="true" disabled>' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                </div>' +
                        '                                <div class="row">' +
                        '                                    <label class="col-sm-2 control-label">Observaciones: </label>' +
                        '                                    <div class="col-sm-4 col-sm-offset-1">' +
                        '                                        <div class="form-group">' +
                        '                                            <input type="text" class="form-control"' +
                        '                                                   name="observaciones' + j + '" id="observaciones' + j + '" required="true">' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                </div>' +
                        '                            </div>' +
                        '                            <div class="card-footer text-center"></div>' +
                        '                        </div>' +
                        '                    </div>' +
                        '                </div>';
                }
                muestraAdicional +=
                    '<div class="row">' +
                    '                    <div class="col-md-12">' +
                    '                        <div class="card ">' +
                    '                            <div class="card-header text-center">' +
                    '                                <h4 class="card-title">Datos finales</h4>' +
                    '                            </div>' +
                    '                            <div class="card-body">' +
                    '                                <div class="row">' +
                    '                                    <label class="col-sm-8 control-label">Favor de responder si requiere devolución de' +
                    '                                        las muestras analizadas: </label>' +
                    '                                    <div class="col-sm-4 col-sm-offset-1">' +
                    '                                        <form id="radioMuestras" class="form-horizontal">' +
                    '                                            <div class="form-check form-check-radio checkbox-inline">' +
                    '                                                <label class="form-check-label">' +
                    '                                                    <input class="form-check-input" type="radio"' +
                    '                                                           name="radioNameMuestras" id="radioNameMuestrasSi" value="Si">' +
                    '                                                    <span class="form-check-sign"></span>' +
                    '                                                    Si' +
                    '                                                </label>' +
                    '                                            </div>' +
                    '                                            <div class="form-check form-check-radio checkbox-inline">' +
                    '                                                <label class="form-check-label">' +
                    '                                                    <input class="form-check-input" type="radio"' +
                    '                                                           name="radioNameMuestras" id="radioNameMuestrasNo" value="No"' +
                    '                                                           checked>' +
                    '                                                    <span class="form-check-sign"></span>' +
                    '                                                    No' +
                    '                                                </label>' +
                    '                                            </div>' +
                    '                                            <input type="hidden" class="form-control"' +
                    '                                                   name="devolucionMuestras" id="devolucionMuestras" value="No">' +
                    '                                        </form>' +
                    '                                    </div>' +
                    '                                </div>' +
                    '                                <br>' +
                    '                                <br>' +
                    '                                <br>' +
                    '                                <div class="row">' +
                    '                                    <div class="col-sm-10">' +
                    '                                        <p class="text-muted">' +
                    '                                            En caso de ser afirmativa la respuesta se le informa que las muestras a' +
                    '                                            devolver se cobrará gastos de envío.' +
                    '                                        </p>' +
                    '                                        <p class="text-danger">' +
                    '                                            Nota: La mayoría de los ensayos son destructivos.' +
                    '                                        </p>' +
                    '                                    </div>' +
                    '                                </div>' +
                    '                            </div>' +
                    '                            <div class="card-footer text-center">' +
                    '                                <button type="submit" class="btn btn-fill btn-primary" id="btnAceptar" onclick="valida()"><i' +
                    '                                        class="fa fa-floppy-o"></i>Registrar' +
                    '                                </button>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </div>' +
                    '                </div>';
                $("#muestraExtra").empty().append(muestraAdicional);
                for (let j = 0; j < cantMuestra; j++) {
                    var cadena = "$(" + "\"#metodo" + j + "\"" + ")" + ".selectpicker(" + "\"refresh\")";
                    eval(cadena);
                }
            })
            .fail(function () {
                swal("Error!", "Favor de contactar al administrador del sistema", "danger");
            });
    }
}

function chequeo() {
    for (var i = 0; i <= contMuestra; i++) {
        document.getElementById("cantidadMuestra" + i).value = document.getElementById("metodo" + i).value;
    }
}

function valida() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
    var totalMuestras = document.getElementById("cantidadMuestras").value;
    document.getElementById("btnAceptar").disabled = true;
    var contador = 0;

    //var valoresMultiples = $('#metodo0').val();

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }

    /*for (var i = 0; i < test2.length; i++) {
        clave = test2[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }*/

    obj["empresa"] = document.getElementById("empresa").value;

    for (var i = 0; i <= totalMuestras; i++) {
        clave = "metodo" + i;
        valor = $('#metodo' + i).val();
        obj[clave] = valor;
    }

    obj["contMuestra"] = totalMuestras;
    //obj["valoresMultiples"] = valoresMultiples;

    var myjson = JSON.stringify(obj);
    save(myjson);
}

function valida3() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
    var cantidadMuestra = document.getElementById("cantidadDeMuestras").value;
    var contador = 0;

    var valoresMultiples = $('#metodo0').val();

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }

    /*for (var i = 0; i < test2.length; i++) {
        clave = test2[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }*/

    obj["empresa"] = document.getElementById("empresa").value;

    for (var i = 0; i <= (cantidadMuestra - 1); i++) {
        clave = "metodo" + i;
        valor = $('#metodo' + i).val();
        obj[clave] = valor;
        //obj[clave] = valor;
    }

    obj["contMuestra"] = document.getElementById("empresa").value;
    //obj["valoresMultiples"] = valoresMultiples;

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
    } else {
        var myjson = JSON.stringify(obj);
        //console.log(valoresMultiples);
        save(myjson);
    }
}

function valida4() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
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

    obj["idSolicitud"] = id;

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
    } else {
        var myjson = JSON.stringify(obj);
        //console.log(valoresMultiples);
        save2(myjson);
    }
}

function validaPagoAnticipo() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
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

    obj["idSolicitud"] = id;

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
    } else {
        var myjson = JSON.stringify(obj);
        urldirect = '/solicitudServicioCliente/confirmarFechas1';
        //console.log(valoresMultiples);
        save2(myjson, urldirect);
    }
}

function validaPagoFinal() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
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

    obj["idSolicitud"] = id;

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
    } else {
        var myjson = JSON.stringify(obj);
        //console.log(valoresMultiples);
        urldirect = '/solicitudServicioCliente/confirmarFechas2';
        save2(myjson, urldirect);
    }
}

function validaFechaCompromiso() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
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

    obj["idSolicitud"] = id;

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
    } else {
        var myjson = JSON.stringify(obj);
        urldirect = '/solicitudServicioCliente/confirmarFechas4';
        //console.log(valoresMultiples);
        save2(myjson, urldirect);
    }
}

function validaFechaRecepcion() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");
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

    obj["idSolicitud"] = id;

    if (contador !== 0) {
        swal("Alerta!", "Tienes uno o más campos vacíos. Favor de revisar.", "warning");
    } else {
        var myjson = JSON.stringify(obj);
        urldirect = '/solicitudServicioCliente/confirmarFechas3';
        //console.log(valoresMultiples);
        save2(myjson, urldirect);
    }
}

function save(myjson) {
    $.ajax({
        type: 'POST',
        url: '/solicitudServicioCliente',
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
                window.location = "/listSolicitudServicio";
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

function save2(myjson, urldirect) {
    $.ajax({
        type: 'POST',
        url: urldirect,
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
                window.location = "/dashboard";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function validaEliminar() {

}

function validaModificar(valor) {
    window.location = "/registerSolicituedServicio/" + valor;
}

function validaImprimir(valor) {
    window.location = "/solicitudServicioCliente/imprimirSolicitud/" + valor;
}

function validaImprimir2(valor) {
    console.log(valor);
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/solicitudServicioCliente/imprimirSolicitud/" + id;
}

function validaImprimirEtiqueta(valor) {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/solicitudServicioCliente/imprimirEtiquetasIdentificacion/" + id;
}

function validaImprimirEtiquetasEnsayos(valor) {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/recepcionVerificacion/imprimirEtiquetasLaboratorioTodas/" + id;
}

function validaImprimirEtiquetasRetencion(valor) {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/solicitudServicioCliente/imprimirEtiquetasRetencionTodas/" + id;
}

/*function validaImprimirEtiquetaRetencion(valor) {
    window.location = "/solicitudServicioCliente/imprimirEtiquetasRetencion/" + valor;
}

function validaImprimirEtiquetaLaboratorio(valor) {
    window.location = "/solicitudServicioCliente/imprimirEtiquetasLaboratorio/" + valor;
}*/

/*function verMuestras(valor) {
    var tabla = '<table id="muestraTable" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">' +
        '<thead>' +
        '<tr>' +
        '<th class="text-center">ID cliente de la muestra</th>' +
        '<th class="text-center">Tipo de muestra</th>' +
        '<th class="text-center">Método solicitado</th>' +
        '<th class="text-center">Cantidad total muestra</th>' +
        '<th class="text-center">Etiqueta individual</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">ID cliente de la muestra</th>' +
        '<th class="text-center">Tipo de muestra</th>' +
        '<th class="text-center">Método solicitado</th>' +
        '<th class="text-center">Cantidad total muestra</th>' +
        '<th class="text-center">Etiqueta individual</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/solicitudServicioClienteMuestras/" + valor, function (result) {
        console.log(result);
        $.each(result, function (i, field) {
            tabla +=
                '<tr>' +
                '<td class="text-center">' + field.idClienteMuestra + '</td>' +
                '<td class="text-center">' + field.tipoMuestra + '</td>' +
                '<td class="text-center">' + field.method.codigoMetodo + '</td>' +
                '<td class="text-center">' + field.method.cantidadTotal + '</td>' +
                '<td class="text-center">' + '<button type="button" class="btn btn-link btn-info edit" data-valida data-url="' + field.solicitudServicioClienteMuestrasId + '"><span class="btn-label"><i class="fa fa-print" data-url="' + field.solicitudServicioClienteMuestrasId + '"></i></span></button>' + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tabla += '</tbody></table>';
        swal({
            title: 'Detalle de las muestras',
            html: tabla,
            width: '1200px',
            height: '1200px'
        });

        $('#muestraTable').DataTable({
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

        $('[data-valida]').on('click', (event, selector) => {
            console.log($(event.target).data('url'));
            validaImprimirFormato($(event.target).data('url'));
        });
    });
}*/

function informe(valor) {
    window.location = "/solicitudServicioCliente/imprimirInforme/" + valor;
}

function informe2(valor) {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/solicitudServicioCliente/imprimirInforme/" + id;
}

function cotizacion(valor) {
    window.location = "/solicitudServicioCliente/imprimirCotizacionContrato/" + valor;
}

function cotizacion2(valor) {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/solicitudServicioCliente/imprimirCotizacionContrato/" + id;
}

function verDetalles(valor) {
    window.location = "/detalleSolicitudServicio/" + valor;
}

function pagoInicial(valor) {
    window.location = "/registerSolicituedServicioFechaPagoAnticipo/" + valor;
}

function pagoFinal(valor) {
    window.location = "/registerSolicituedServicioFechaPagoFinal/" + valor;
}

function fechaRecepcion(valor) {
    window.location = "/registerSolicituedServicioFechaRecepcionMuestras/" + valor;
}

function fechaCompromiso(valor) {
    window.location = "/registerSolicituedServicioFechaCompromisoEntrega/" + valor;
}

function generarListas() {
    ///solicitudServicioCliente/generarListaSolicitud
    valor = document.getElementById("opciongenerar").value;
    if (valor === "") {
        swal("Alerta!", "Por favor selecciona una opción.", "warning");
    } else {
        window.location = "/solicitudServicioCliente/generarListaSolicitud/" + valor;
    }
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Cliente</th>' +
        '<th class="text-center">Fecha de recepción</th>' +
        '<th class="text-center">Fecha compromiso</th>' +
        '<th class="text-center">Ver detalles</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Cliente</th>' +
        '<th class="text-center">Fecha de recepción</th>' +
        '<th class="text-center">Fecha compromiso</th>' +
        '<th class="text-center">Ver detalles</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/solicitudServicioCliente", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolitudServicioCliente + '</td>' +
                '<td class="text-center">' + field.client.nombreRazonSocial + '</td>';
            if (field.fechaRecepcionMuestras === "") {
                tbl += '<td class="text-center"><button class="btn btn-danger" onclick="fechaRecepcion(' + field.solicitudServicioClienteId + ')"><i class="fa fa-calendar"></i>Fecha recepción muestras</button></td>';
            } else {
                tbl += '<td class="text-center">' + field.fechaRecepcionMuestras + '</td>';
            }
            if (field.fechaCompromisoEntrega === "") {
                tbl += '<td class="text-center"><button class="btn btn-danger" onclick="fechaCompromiso(' + field.solicitudServicioClienteId + ')"><i class="fa fa-calendar"></i>Fecha compromiso</button></td>';
            } else {
                tbl += '<td class="text-center">' + field.fechaCompromisoEntrega + '</td>';
            }

            if (field.fechaPago !== "" && field.fechaRecepcionMuestras !== "") {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" onclick="verDetalles(' + field.solicitudServicioClienteId + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>' +
                    '<td class="text-center">' +
                    '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.solicitudServicioClienteId + ')"><i class="fa fa-print"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-edit"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-times"></i></a>' +
                    '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center">-</td>' +
                    '<td class="text-center">' +
                    // '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.solicitudServicioClienteId + ')"><i class="fa fa-print"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-warning" onclick="validaModificar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-edit"></i></button>' +
                    // '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-times"></i></a>' +
                    '</td>' +
                    '</tr>';
            }
        });
        tbl += '</tbody>';
        $("#solicitudServicioClienteTable").append(tbl);
        $('#solicitudServicioClienteTable').DataTable({
            "order": [[ 0, "desc" ]],
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

function cargarTablaPagos() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Fecha pago inicial</th>' +
        '<th class="text-center">Fecha pago final</th>' +
        '<th class="text-center">Progreso de pago</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Fecha pago inicial</th>' +
        '<th class="text-center">Fecha pago final</th>' +
        '<th class="text-center">Progreso de pago</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/solicitudServicioCliente", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolitudServicioCliente + '</td>';
            if (field.fechaPago === "") {
                tbl += '<td class="text-center"><button class="btn btn-danger" onclick="pagoInicial(' + field.solicitudServicioClienteId + ')"><i class="fa fa-calendar"></i>Fecha pago inicial</button></td>';
            } else {
                tbl += '<td class="text-center">' + field.fechaPago + '</td>';
            }
            if (field.fechaPago2 === "") {
                tbl += '<td class="text-center"><button class="btn btn-danger" onclick="pagoFinal(' + field.solicitudServicioClienteId + ')"><i class="fa fa-calendar"></i>Fecha pago final</button></td>';
            } else {
                tbl += '<td class="text-center">' + field.fechaPago2 + '</td>';
            }
            tbl +=
                '<td class="text-center">' +
                '<div class="progress">' +
                '<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 30%;">' +
                '<span class="sr-only">60% Complete</span>' +
                '</div>' +
                '</div>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#solicitudServicioClienteTable").append(tbl);
        $('#solicitudServicioClienteTable').DataTable({
            "order": [[ 0, "desc" ]],
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