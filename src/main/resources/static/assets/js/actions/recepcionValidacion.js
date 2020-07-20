var video = document.createElement("video");
var canvasElement = document.getElementById("canvas");
var canvas = canvasElement.getContext("2d");
var loadingMessage = document.getElementById("loadingMessage");
var outputContainer = document.getElementById("output");
var outputMessage = document.getElementById("outputMessage");
var outputData = document.getElementById("outputData");
var i;

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
        /*for (i=0; i<8000; i++){
            console.log("Entró " + i + " veces");
            requestAnimationFrame(tick);
        }*/
        /*while (true){
            //console.log("Entró " + i + " veces");
            requestAnimationFrame(tick);
        }*/
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
            //requestAnimationFrame(tick);
            console.log("prueba de detención");

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
                    cargarTabla();
                    swal("Aceptado!", "Tu muestra fue aceptada.", "success");
                    deteccion();
                } else {
                    console.log("Muestra rechazada.");
                    cargarTabla();
                    swal("Rechazada", "Tu muestra fue rechazada", "error");
                    deteccion();
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
                    }*/
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
        $("#muestraTable").append(tbl);
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