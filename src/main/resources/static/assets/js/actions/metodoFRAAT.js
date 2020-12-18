var canvas = document.getElementById('myCanvas');
var context = canvas.getContext('2d');
var dataURL;
const video = document.getElementById('video');
const button = document.getElementById('button');
const select = document.getElementById('select');
const snap = document.getElementById("snap");
let currentStream;

/*function stopMediaTracks(stream) {
    stream.getTracks().forEach(track => {
        track.stop();
    });
}

function gotDevices(mediaDevices) {
    select.innerHTML = '';
    select.appendChild(document.createElement('option'));
    let count = 1;
    mediaDevices.forEach(mediaDevice => {
        if (mediaDevice.kind === 'videoinput') {
            const option = document.createElement('option');
            option.value = mediaDevice.deviceId;
            const label = mediaDevice.label || `Camera ${count++}`;
            const textNode = document.createTextNode(label);
            option.appendChild(textNode);
            select.appendChild(option);
        }
    });
}

button.addEventListener('click', event => {
    if (typeof currentStream !== 'undefined') {
        stopMediaTracks(currentStream);
    }
    const videoConstraints = {};
    if (select.value === '') {
        videoConstraints.facingMode = 'environment';
    } else {
        videoConstraints.deviceId = {exact: select.value};
    }
    const constraints = {
        video: videoConstraints,
        audio: false
    };
    navigator.mediaDevices
        .getUserMedia(constraints)
        .then(stream => {
            currentStream = stream;
            video.srcObject = stream;
            return navigator.mediaDevices.enumerateDevices();
        })
        .then(gotDevices)
        .catch(error => {
            console.error(error);
        });
});

snap.addEventListener("click", function () {
    context.drawImage(video, 0, 0, 1920, 1080);
    dataURL = canvas.toDataURL();
    document.getElementById('canvasIMG').src = dataURL;
});

navigator.mediaDevices.enumerateDevices().then(gotDevices);

function b64toBlob(b64Data, contentType, sliceSize) {
    contentType = contentType || '';
    sliceSize = sliceSize || 512;

    var byteCharacters = atob(b64Data);
    var byteArrays = [];

    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
        var slice = byteCharacters.slice(offset, offset + sliceSize);

        var byteNumbers = new Array(slice.length);
        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        var byteArray = new Uint8Array(byteNumbers);

        byteArrays.push(byteArray);
    }

    var blob = new Blob(byteArrays, {type: contentType});
    return blob;
}*/

function valida() {
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");
    document.getElementById("btnAceptar").disabled = true;

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }

    obj["id"] = id;

    /*var block = dataURL.split(";");
    // Get the content type of the image
    var contentType = block[0].split(":")[1];// In this case "image/gif"
    // get the real base64 content of the file
    var realData = block[1].split(",")[1];// In this case "R0lGODlhPQBEAPeoAJosM...."

    // Convert it to a blob to upload
    /*var blob1 = b64toBlob(realData, contentType);
    console.log(blob.value);
    console.log(blob);*/

    var blob = document.getElementById("file").files[0];
    var blob2 = document.getElementById("file2").files[0];
    var blob3 = document.getElementById("file3").files[0];

    var formData = new FormData();
    //formData.append("imagen", blob);
    formData.append("imagen", blob);
    formData.append("imagen2", blob2);
    formData.append("imagen3", blob3);
    formData.append('fraat', new Blob([JSON.stringify(obj)], {
        type: "application/json"
    }));

    save(formData);
}

function mostrar(){
    var archivo = document.getElementById("file").files[0];
    var reader = new FileReader();
    if (file) {
        reader.readAsDataURL(archivo );
        reader.onloadend = function () {
            document.getElementById("img").src = reader.result;
        }
    }
}

function mostrar2(){
    var archivo2 = document.getElementById("file2").files[0];
    var reader2 = new FileReader();
    if (file2) {
        reader2.readAsDataURL(archivo2 );
        reader2.onloadend = function () {
            document.getElementById("img2").src = reader2.result;
        }
    }
}

function mostrar3(){
    var archivo3 = document.getElementById("file3").files[0];
    var reader3 = new FileReader();
    if (file3) {
        reader3.readAsDataURL(archivo3 );
        reader3.onloadend = function () {
            document.getElementById("img3").src = reader3.result;
        }
    }
}

function save(myjson) {
    console.log(myjson)
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAAT', {
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
                window.location = "/listFRAAT";
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
    window.location = "/FRAAT/imprimir/" + valor;
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
    $.getJSON("/FRAAT", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>' +
                '<td class="text-center">' + field.fechaFinalAnalisis + '</td>' +
                '<td class="text-center">' + field.temperatura + '</td>' +
                '<td class="text-center">' + field.humedadRelativa + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAAT + ')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAAT + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAAT + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#fraatTable").append(tbl);
        $('#fraatTable').DataTable({
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