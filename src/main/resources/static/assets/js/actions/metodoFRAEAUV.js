function valida() {
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }
    const url = document.URL;
    obj["id"] = url.substring(url.lastIndexOf('/') + 1);

    var myjson = JSON.stringify(obj);
    console.log(myjson);
    save(myjson);
}

function validaAgregar(){
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    const blob = document.getElementById("file").files[0];
    const tiempo = document.getElementById('tiempoExposicion').value;
    var obj = {};
    obj["id"] = id;
    obj["tiempoExposicion"] = tiempo;
    var formData = new FormData();
    formData.append("imagen", blob);
    formData.append("datos", new Blob([JSON.stringify({
        "tiempoExposicion" : tiempo,
        "id" : id
    })], {
        type: "application/json"
    }));

    saveAgregar(formData);
}

function validaTerminar(){
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var obj = {};
    var clave;
    var valor;
    var test = document.getElementsByTagName("input");

    for (var i = 0; i < test.length; i++) {
        clave = test[i].getAttribute("id");
        valor = document.getElementById(clave).value;
        obj[clave] = valor;
    }
    obj["id"] = id;

    var myjson = JSON.stringify(obj);
    console.log(myjson);
    saveTerminar(myjson);
}

function saveTerminar(myjson) {
    $.ajax({
        type: 'POST',
        url: '/FRAEAUV/terminar',
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
                window.location = "/listFRAEAUV";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function saveAgregar(myjson){
    console.log(myjson)
    var boundary = Math.random().toString().substr(2);
    fetch('/FRAEAUV/agregar', {
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
                window.location = "/listFRAEAUV";
            });
        } else {
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    }).catch(function (err) {
        swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
    });
}

function save(myjson) {
    $.ajax({
        type: 'POST',
        url: '/FRAEAUV',
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
                window.location = "/listFRAEAUV";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function validaImprimir(valor){
    window.location = "/FRAEAUV/imprimir/" + valor;
}

function validaEliminar(valor) {

}

function validaModificar() {

}

function iniciarProceso(valor){
    $.ajax({
        type: 'POST',
        url: '/FRAEAUV/iniciar/'+valor,
        cache: false,
        contentType: "application/json",
        processData: false,
        success: function (data) {
            console.log("success");
            console.log(data);
            swal({
                title: "Iniciado!",
                text: "Se inició el método.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listFRAEAUV";
            });
        },
        error: function (data) {
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function vistaAgregar(valor){
    window.location = "/agregarFRAEAUV/" + valor;
}

function verInfo(valor){
    window.location = "/verFRAEAUV/" + valor;
}

function volver(){
    window.location = "/listFRAEAUV";
}

function terminar(valor){
    window.location = "/terminarFRAEAUV/" + valor;
}

function verImagen(){

}

function cargarTabla2(){
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">#</th>' +
        '<th class="text-center">Tiempo de exposición</th>' +
        '<th class="text-center">Ver imagen</th>' +
        '<th class="text-center">Fecha y hora de registro</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRAEAUV/getAllBy/"+id, function (result) {
        $.each(result, function (i, field) {
            var mas = 1;
            tbl +=
                '<tr>' +
                '<td class="text-center">' + (parseInt(i) + 1) + '</td>' +
                '<td class="text-center">' + field.tiempoExposicion + '</td>' +
                '<td class="text-center"><button class="btn btn-success" onclick="verImagen(' + field.idFRAEAUVDATA + ')"><i class="fa fa-picture-o"></i>Ver Imagen</button></td>' +
                '<td class="text-center">' + field.createdAt + '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        tbl += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">#</th>' +
            '<th class="text-center">Tiempo de exposición</th>' +
            '<th class="text-center">Ver imagen</th>' +
            '<th class="text-center">Fecha y hora de registro</th>' +
            '</tr>' +
            '</tfoot>';
        $("#fraeauvTable2").append(tbl);
        $('#fraeauvTable2').DataTable({
            "pagingType": "full_numbers",
            "lengthMenu": [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ],
            responsive: false,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Buscar Registros",
            }
        });
    });
}


function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">#</th>' +
        '<th class="text-center">Folio Solicitud</th>' +
        '<th class="text-center">Fecha de inicio análisis</th>' +
        '<th class="text-center">Fecha final de análisis</th>' +
        '<th class="text-center">Temperatura</th>' +
        '<th class="text-center">Humedad Relativa</th>' +
        '<th class="text-center">Visualizar</th>' +
        '<th class="text-center">Iniciar</th>' +
        '<th class="text-center">Agregar</th>' +
        '<th class="text-center">Finalizar</th>' +
        '<th class="text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';
    $.getJSON("/FRAEAUV", function (result) {
        $.each(result, function (i, field) {
            if (field.estatus === "inicio"){

            }
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.idFRAEAUV + '</td>' +
                '<td class="text-center">' + field.folioSolicitudServicioInterno + '</td>' +
                '<td class="text-center">' + field.fechaInicioAnalisis + '</td>' +
                '<td class="text-center">' + field.fechaFinalAnalisis + '</td>' +
                '<td class="text-center">' + field.temperatura + '</td>' +
                '<td class="text-center">' + field.humedadRelativa + '</td>' +
                '<td class="text-center"><button class="btn btn-info" onclick="verInfo(' + field.idFRAEAUV + ')"><i class="fa fa-eye"></i>Ver</button></td>';
            if (field.estatus === "inicio"){
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" onclick="iniciarProceso(' + field.idFRAEAUV + ')"><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-warning" disabled><i class="fa fa-plus"></i>Agregar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" disabled><i class="fa fa-save"></i>Terminar</button></td>';
            } else  if (field.estatus === "terminado"){
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-warning" disabled><i class="fa fa-plus"></i>Agregar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" disabled><i class="fa fa-save"></i>Terminar</button></td>' +
                    '<td class="text-center">' +
                    '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.idFRAEAUV + ')"><i class="fa fa-print"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.idFRAEAUV + ')"><i class="fa fa-edit"></i></button>' +
                    '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.idFRAEAUV + ')"><i class="fa fa-times"></i></a>' +
                    '</td>' +
                    '</tr>';
            } else {
                tbl +=
                    '<td class="text-center"><button class="btn btn-success" disabled><i class="fa fa-flag"></i>Iniciar</button></td>' +
                    '<td class="text-center"><button class="btn btn-warning" onclick="vistaAgregar(' + field.idFRAEAUV + ')"><i class="fa fa-plus"></i>Agregar</button></td>' +
                    '<td class="text-center"><button class="btn btn-danger" onclick="terminar(' + field.idFRAEAUV + ')"><i class="fa fa-save"></i>Terminar</button></td>';
            }
        });
        tbl += '</tbody>';
        tbl += '<tfoot>' +
            '<tr>' +
            '<th class="text-center">#</th>' +
            '<th class="text-center">Folio Solicitud</th>' +
            '<th class="text-center">Fecha de inicio análisis</th>' +
            '<th class="text-center">Fecha final de análisis</th>' +
            '<th class="text-center">Temperatura</th>' +
            '<th class="text-center">Humedad Relativa</th>' +
            '<th class="text-center">Visualizar</th>' +
            '<th class="text-center">Iniciar</th>' +
            '<th class="text-center">Agregar</th>' +
            '<th class="text-center">Finalizar</th>' +
            '<th class="text-center">Acciones</th>' +
            '</tr>' +
            '</tfoot>';
        $("#fraeauvTable").append(tbl);
        $('#fraeauvTable').DataTable({
            "pagingType": "full_numbers",
            "lengthMenu": [
                [10, 25, 50, -1],
                [10, 25, 50, "All"]
            ],
            responsive: false,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Buscar Registros",
            }
        });
    });
}
