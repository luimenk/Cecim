var video = document.createElement("video");
var canvasElement = document.getElementById("canvas");
var canvas = canvasElement.getContext("2d");
var loadingMessage = document.getElementById("loadingMessage");
var outputContainer = document.getElementById("output");
var outputMessage = document.getElementById("outputMessage");
var outputData = document.getElementById("outputData");
var i;
var valor;

function drawLine(begin, end, color) {
    canvas.beginPath();
    canvas.moveTo(begin.x, begin.y);
    canvas.lineTo(end.x, end.y);
    canvas.lineWidth = 4;
    canvas.strokeStyle = color;
    canvas.stroke();
}

// facingMode: entorno para obtener la cámara frontal de los móviles
function deteccion() {
    navigator.mediaDevices.getUserMedia({video: {facingMode: "environment"}}).then(function (stream) {
        video.srcObject = stream;
        video.setAttribute("playsinline", true); // required to tell iOS safari we don't want fullscreen
        console.log("Antes del play");
        video.play();
        console.log("Despues del play");
        requestAnimationFrame(tick);
        console.log("Despues del requestAnimationFrame fuera del tick");
    });
}

function tick() {
    loadingMessage.innerText = "Cargando video..."
    if (video.readyState === video.HAVE_ENOUGH_DATA) {
        loadingMessage.hidden = true;
        canvasElement.hidden = false;
        outputContainer.hidden = false;

        canvasElement.height = video.videoHeight;
        canvasElement.width = video.videoWidth;
        canvas.drawImage(video, 0, 0, canvasElement.width, canvasElement.height);
        var imageData = canvas.getImageData(0, 0, canvasElement.width, canvasElement.height);
        var code = jsQR(imageData.data, imageData.width, imageData.height, {
            inversionAttempts: "dontInvert",
        });
        if (code) {
            //AQUÍ ES LA DETECCIÓN DEL QR
            drawLine(code.location.topLeftCorner, code.location.topRightCorner, "#FF3B58");
            drawLine(code.location.topRightCorner, code.location.bottomRightCorner, "#FF3B58");
            drawLine(code.location.bottomRightCorner, code.location.bottomLeftCorner, "#FF3B58");
            drawLine(code.location.bottomLeftCorner, code.location.topLeftCorner, "#FF3B58");
            outputMessage.hidden = true;
            outputData.parentElement.hidden = false;
            outputData.innerText = code.data;

            swal({
                title: "¿Confirmar prueba?",
                text: "¿Deseas aceptar la prueba o rechazarla?",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "Aceptar la muestra",
                cancelButtonText: "Rechazar la muestra",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                    console.log("Aceptada. El código es: " + code.data);
                    cargarMedianteQR(code.data);
                    swal("Aceptado!", "Tu muestra fue aceptada.", "success");
                    deteccion();
                } else {
                    console.log("Muestra rechazada.");
                    //cargarTabla();
                    swal("Rechazada", "Tu muestra fue rechazada", "error");
                    deteccion();
                }
            });
            return "algo";
        } else {
            //AQUÍ ES CUANDO LO DEJA DE DETECTAR
            outputMessage.hidden = false;
            outputData.parentElement.hidden = true;
            console.log("En el else del IF CODE");
        }
    }
    //video.stop();
    requestAnimationFrame(tick);
}

/**
 * swal({
title: "¿Confirmar prueba?",
text: "¿Deseas aceptar la prueba o rechazarla?",
type: "warning",
showCancelButton: true,
confirmButtonText: "Aceptar la muestra",
cancelButtonText: "Rechazar la muestra",
closeOnConfirm: false,
closeOnCancel: false
}, function (isConfirm) {
if (isConfirm){
    swal("Aceptado!", "Tu muestra fue aceptada.", "success");
    //video.play();
} else {
    swal("Rechazada", "Tu muestra fue rechazada", "error");
    //video.play();
}
/*$.ajax({
    type:'DELETE',
    url:'/method/'+valor,
    cache:false,
    contentType: "application/json",
    processData: false,
    success: function(data){
        console.log("success");
        console.log(data);
        swal({
            title: "Eliminado!",
            text: "Tu registro ha sido eliminado exitosamente",
            type: "success",
            showCancelButton: false,
            confirmButtonClass: "btn btn-info btn-fill",
            confirmButtonText: "Ok",
            closeOnConfirm: false,
        }, function () {
            window.location = "/mostrarMetodos";
        });
    },
    error: function(data){
        console.log("error");
        console.log(data);
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    }
});*/
//});

function cargarTabla() {
    var tbl =
        '<tbody>';
    $.getJSON("/solicitudServicioClienteMuestras", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td>' +
                '<div class="form-check">' +
                '<label class="form-check-label">' +
                '<input class="form-check-input" type="checkbox" value="">' +
                '<span class="form-check-sign"></span>' +
                '</label>' +
                '</div>' +
                '</td>' +
                '<td>' + field.solicitudServicioCliente.folioSolitudServicioCliente + ' ' + field.method.codigoMetodo + '</td>' +
                '<td class="td-actions text-right">' +
                '<button type="button" rel="tooltip" title="Edit Task" class="btn btn-info btn-simple btn-link">' +
                '<i class="fa fa-edit"></i>' +
                '</button>' +
                '<button type="button" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-link">' +
                '<i class="fa fa-times"></i>' +
                '</button>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#mediantefolio").empty().append(tbl);
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
    });
}

function cargarMedianteQR(valor){
    var frm = '<div class="row">' +
        '                        <div class="col-md-12">' +
        '                            <div class="card ">' +
        '                                <div class="card-header ">' +
        '                                    <h4 class="card-title">Verificación de etiqueta de identificación de muestra del cliente FEIM-SOC-007</h4>' +
        '                                </div>' +
        '                                <div class="card-body ">' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Cuenta con etiqueta de identificación la muestra: </label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <form id="radioEtiqueta" class="form-horizontal">' +
        '                                                <div class="form-check form-check-radio checkbox-inline">' +
        '                                                    <label class="form-check-label">' +
        '                                                        <input class="form-check-input" type="radio" name="radioNameEtiqueta" id="radioNameEtiquetaSi" value="Si" checked>' +
        '                                                        <span class="form-check-sign"></span>' +
        '                                                        Si' +
        '                                                    </label>' +
        '                                                </div>' +
        '                                                <div class="form-check form-check-radio checkbox-inline">' +
        '                                                    <label class="form-check-label">' +
        '                                                        <input class="form-check-input" type="radio" name="radioNameEtiqueta" id="radioNameEtiquetaNo" value="No">' +
        '                                                        <span class="form-check-sign"></span>' +
        '                                                        No' +
        '                                                    </label>' +
        '                                                </div>' +
        '                                                <input type="hidden" class="form-control"' +
        '                                                       name="cuentaConEtiqueta" id="cuentaConEtiqueta" value="Si">' +
        '                                            </form>' +
        '                                        </div>' +
        '' +
        '                                        <label class="col-sm-2 control-label">El cliente utilizó el formato FEIM-SOC-007: </label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <form id="radioFormato" class="form-horizontal">' +
        '                                                <div class="form-check form-check-radio checkbox-inline">' +
        '                                                    <label class="form-check-label">' +
        '                                                        <input class="form-check-input" type="radio" name="radioNameFormato" id="radioNameFormatoSi" value="Si" checked>' +
        '                                                        <span class="form-check-sign"></span>' +
        '                                                        Si' +
        '                                                    </label>' +
        '                                                </div>' +
        '                                                <div class="form-check form-check-radio checkbox-inline">' +
        '                                                    <label class="form-check-label">' +
        '                                                        <input class="form-check-input" type="radio" name="radioNameFormato" id="radioNameFormatoNo" value="No">' +
        '                                                        <span class="form-check-sign"></span>' +
        '                                                        No' +
        '                                                    </label>' +
        '                                                </div>' +
        '                                                <input type="hidden" class="form-control"' +
        '                                                       name="utilizoFeim" id="utilizoFeim" value="Si">' +
        '                                            </form>' +
        '                                        </div>' +
        '                                    </div>' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Cantidad de muestra entregada</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="cantidadMuestraEntregada" id="cantidadMuestraEntregada" required>' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="idMuestra" id="idMuestra" value="' + valor + '">' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '                                </div>' +
        '                                <div class="card-header ">' +
        '                                    <h4 class="card-title">Recepción y verificación registro y codificación de la muestra</h4>' +
        '                                </div>' +
        '                                <div class="card-body ">' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Fecha de recepción</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control datepicker"' +
        '                                                       name="fechaRecepcion" id="fechaRecepcion" required="true">' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Nombre de la persona que recibe</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="nombrePersonaRecibe" id="nombrePersonaRecibe" required>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Nombre de la persona que entrega</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="nombrePersonaEntrega" id="nombrePersonaEntrega" required>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Medio por el cuál se está recibiendo la muestra:</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <select class="selectpicker" name="medioRecepcion" id="medioRecepcion" required="true"' +
        '                                                        data-title="Selecciona un elemento..."' +
        '                                                        data-style="btn-default btn-outline"' +
        '                                                        data-menu-style="dropdown-blue">' +
        '                                                    <option value="Personal interno de CeCIM">Personal interno de CeCIM</option>' +
        '                                                    <option value="Personal del cliente">Personal del cliente</option>' +
        '                                                    <option value="Paquetería">Paquetería</option>' +
        '                                                </select>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Condiciones de la muestra 1</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="condicionesMuestra1" id="condicionesMuestra1" required>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                        <label class="col-sm-2 control-label">Condiciones de la muestra 2</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="condicionesMuestra2" id="condicionesMuestra2" required>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Cumple con la cantidad de muestra solicitada</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <form id="radioSolicitada" class="form-horizontal">' +
        '                                                <div class="form-check form-check-radio checkbox-inline">' +
        '                                                    <label class="form-check-label">' +
        '                                                        <input class="form-check-input" type="radio" name="radioNameSolicitada" id="radioNameSolicitadaSi" value="Si" checked>' +
        '                                                        <span class="form-check-sign"></span>' +
        '                                                        Si' +
        '                                                    </label>' +
        '                                                </div>' +
        '                                                <div class="form-check form-check-radio checkbox-inline">' +
        '                                                    <label class="form-check-label">' +
        '                                                        <input class="form-check-input" type="radio" name="radioNameSolicitada" id="radioNameSolicitadaNo" value="No">' +
        '                                                        <span class="form-check-sign"></span>' +
        '                                                        No' +
        '                                                    </label>' +
        '                                                </div>' +
        '                                                <input type="hidden" class="form-control"' +
        '                                                       name="cumpleCantidad" id="cumpleCantidad" value="Si">' +
        '                                            </form>' +
        '                                        </div>' +
        '                                        <label class="col-sm-2 control-label">Si no cumple, especifique la cantidad de muestra recibida</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="sinoEspecifiqueCantidad" id="sinoEspecifiqueCantidad" disabled>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Cantidad de muestra para análisis</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="cantidadMuestraAnalisis" id="cantidadMuestraAnalisis" required>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                        <label class="col-sm-2 control-label">Cantidad de muestra para retención</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="cantidadMuestraRetencion" id="cantidadMuestraRetencion" required>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '' +
        '                                    <div class="row">' +
        '                                        <label class="col-sm-2 control-label">Nombre de la persona que acondicionará las muestras</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="nombrePersonaAcondicionara" id="nombrePersonaAcondicionara" required>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                        <label class="col-sm-2 control-label">Ubicación de la muestra de retención</label>' +
        '                                        <div class="col-sm-4 col-sm-offset-1">' +
        '                                            <div class="form-group">' +
        '                                                <input type="text" class="form-control"' +
        '                                                       name="ubicacionMuestraRetencion" id="ubicacionMuestraRetencion" required>' +
        '                                            </div>' +
        '                                        </div>' +
        '                                    </div>' +
        '                                </div>' +
        '                                <div class="card-footer ">' +
        '                                    <button type="submit" class="btn btn-fill btn-primary" onclick="valida()">Cargar' +
        '                                        información' +
        '                                    </button>' +
        '                                </div>' +
        '                            </div>' +
        '                        </div>' +
        '                    </div>';
    //$("#medianteQR").append(frm);
    //document.getElementById("idMuestra").value = outputData.innerText;
    $("#medianteQR").empty().append(frm);

    var cadena = '$(\'#radioSolicitada input\').on(\'change\', function() {\n' +
        '        var discounted = document.getElementById(\'sinoEspecifiqueCantidad\');\n' +
        '        document.getElementById("cumpleCantidad").value = $(\'input[name=radioNameSolicitada]:checked\', \'#radioSolicitada\').val();\n' +
        '        if ($(\'input[name=radioNameSolicitada]:checked\', \'#radioSolicitada\').val() === "No"){\n' +
        '            document.getElementById("sinoEspecifiqueCantidad").disabled = false;\n' +
        '        } else if ($(\'input[name=radioNameSolicitada]:checked\', \'#radioSolicitada\').val() === "Si"){\n' +
        '            document.getElementById("sinoEspecifiqueCantidad").disabled = true;\n' +
        '        }\n' +
        '    });\n' +
        '    $(\'#radioEtiqueta input\').on(\'change\', function() {\n' +
        '        document.getElementById("cuentaConEtiqueta").value = $(\'input[name=radioNameEtiqueta]:checked\', \'#radioEtiqueta\').val();\n' +
        '    });\n' +
        '    $(\'#radioEtiqueta input\').on(\'change\', function() {\n' +
        '        document.getElementById("cuentaConEtiqueta").value = $(\'input[name=radioNameEtiqueta]:checked\', \'#radioEtiqueta\').val();\n' +
        '    });\n' +
        '    $(\'#radioFormato input\').on(\'change\', function() {\n' +
        '        document.getElementById("utilizoFeim").value = $(\'input[name=radioNameFormato]:checked\', \'#radioFormato\').val();\n' +
        '    });';

    var cadena2 = '$(\'#fechaRecepcion\').datetimepicker({\n' +
        '        format: \'YYYY-MM-DD\',\n' +
        '        sideBySide: true,\n' +
        '        icons: {\n' +
        '            up: "fa fa-chevron-circle-up",\n' +
        '            down: "fa fa-chevron-circle-down",\n' +
        '            next: \'fa fa-chevron-circle-right\',\n' +
        '            previous: \'fa fa-chevron-circle-left\'\n' +
        '        }\n' +
        '    });';
    var cadena3 = '$("#medioRecepcion").selectpicker("refresh")';

    eval(cadena);
    eval(cadena2);
    eval(cadena3);
}

function valida(){
    var obj={};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    var test2 = document.getElementsByTagName("select");

    for (var i=0; i < test.length; i++){
        clave=test[i].getAttribute("id");
        valor=document.getElementById(clave).value;
        obj[clave]=valor;
    }

    for (var i=0; i < test2.length; i++){
        clave=test2[i].getAttribute("id");
        valor=document.getElementById(clave).value;
        obj[clave]=valor;
    }

    var myjson = JSON.stringify(obj);
    console.log(myjson);
    save(myjson);
}

function save(myjson){
    $.ajax({
        type:'POST',
        url:'/recepcionVerificacion',
        data:myjson,
        cache:false,
        contentType: "application/json",
        processData: false,
        success: function(data){
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
        error: function(data){
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}