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
                title: "¿Confirmar?",
                text: "¿Deseas aceptar o cancelar?",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "Aceptar",
                cancelButtonText: "Cancelar",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                    console.log("Aceptada. El código es: " + code.data);
                    window.location = code.data;
                } else {
                    console.log("Muestra rechazada.");
                    //cargarTabla();
                    swal("Calcelado", "Tu acción fue cancelada", "error");
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
    requestAnimationFrame(tick);
}