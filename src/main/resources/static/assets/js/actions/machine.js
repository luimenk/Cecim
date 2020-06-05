function valida(e){
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
        url:'https://localhost:8444/machine',
        data:myjson,
        cache:false,
        contentType: "application/json",
        processData: false,
        success: function(data){
            console.log("success");
            console.log(data);
            swal({
                title: "Registrado!",
                text: "Tu registro ha sido registrado exitosamente.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/mostrarMaquinas";
            });
        },
        error: function(data){
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function validaEliminar(valor) {
    swal({
        title: "Estás seguro?",
        text: "No podrás recuperar este registro en el futuro!",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn btn-info btn-fill",
        confirmButtonText: "Si, eliminar!",
        cancelButtonClass: "btn btn-danger btn-fill",
        closeOnConfirm: false,
    }, function () {
        $.ajax({
            type:'DELETE',
            url:'https://localhost:8444/machine/'+valor,
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
                    window.location = "/mostrarMaquinas";
                });
            },
            error: function(data){
                console.log("error");
                console.log(data);
                swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
            }
        });
    });
}

function validaModificar() {
    alert("Modificará un almacen");
}

var tbl =
    '<thead>' +
    '<tr>' +
    '<th>Código Interno</th>' +
    '<th>Área Responsable</th>' +
    '<th>Nombre del Equipo o Instrumento</th>' +
    '<th>Tipo de Equipo</th>' +
    '<th>Tipo de Registro</th>' +
    '<th>Tipo de Servicio Requerido</th>' +
    '<th>Condición del equipo</th>' +
    '<th>Número de Serie</th>' +
    '<th>Modelo</th>' +
    '<th>Marca</th>' +
    '<th>Garantía</th>' +
    '<th>Fabricante</th>' +
    '<th>Largo</th>' +
    '<th>Ancho</th>' +
    '<th>Alto</th>' +
    '<th>Peso</th>' +
    '<th>Zona de Ubicación</th>' +
    '<th>Se anexa al plano de ubicación</th>' +
    '<th class="disabled-sorting text-right">Acciones</th>' +
    '</tr>' +
    '</thead>' +
    '<tfoot>' +
    '<tr>' +
    '<th>Código Interno</th>' +
    '<th>Área Responsable</th>' +
    '<th>Nombre del Equipo o Instrumento</th>' +
    '<th>Tipo de Equipo</th>' +
    '<th>Tipo de Registro</th>' +
    '<th>Tipo de Servicio Requerido</th>' +
    '<th>Condición del equipo</th>' +
    '<th>Número de Serie</th>' +
    '<th>Modelo</th>' +
    '<th>Marca</th>' +
    '<th>Garantía</th>' +
    '<th>Fabricante</th>' +
    '<th>Largo</th>' +
    '<th>Ancho</th>' +
    '<th>Alto</th>' +
    '<th>Peso</th>' +
    '<th>Zona de Ubicación</th>' +
    '<th>Se anexa al plano de ubicación</th>' +
    '<th class="text-right">Acciones</th>' +
    '</tr>' +
    '</tfoot>' +
    '<tbody>';
function cargarTabla() {
    $.getJSON("https://localhost:8444/machine", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td>' + field.codigoInterno + '</td>' +
                '<td>' + field.areaResponsable + '</td>' +
                '<td>' + field.nombreEquipoInstrumento + '</td>' +
                '<td>' + field.tipoEquipo + '</td>' +
                '<td>' + field.registro + '</td>' +
                '<td>' + field.tipoServicioRequerido + '</td>' +
                '<td>' + field.condicionEquipo + '</td>' +
                '<td>' + field.numeroSerie + '</td>' +
                '<td>' + field.modelo + '</td>' +
                '<td>' + field.marca + '</td>' +
                '<td>' + field.garantia + '</td>' +
                '<td>' + field.fabricante + '</td>' +
                '<td>' + field.largo + '</td>' +
                '<td>' + field.ancho + '</td>' +
                '<td>' + field.alto + '</td>' +
                '<td>' + field.peso + '</td>' +
                '<td>' + field.zonaUbicacion + '</td>' +
                '<td>' + field.planoAnexo + '</td>' +
                '<td class="text-right">' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar('+field.machineId+')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar('+field.machineId+')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#machineTable").append(tbl);
        $('#machineTable').DataTable({
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