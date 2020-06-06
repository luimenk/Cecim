function valida(e){
    var codigoMetodo = document.getElementById('codigoMetodo').value;
    var cantidadMuestraEnsayo = document.getElementById('cantidadMuestraEnsayo').value;
    var cantidadMuestraRetencion = document.getElementById('cantidadMuestraRetencion').value;
    var dimensionesCorteProbeta = document.getElementById('dimensionesCorteProbeta').value;
    var numeroProbetasMuestras = document.getElementById('numeroProbetasMuestras').value;
    var condicionesEspecialesAcondicionamiento = document.getElementById('condicionesEspecialesAcondicionamiento').value;

    if (codigoMetodo != "" &&
        cantidadMuestraEnsayo != "" &&
        cantidadMuestraRetencion != "" &&
        dimensionesCorteProbeta != "" &&
        numeroProbetasMuestras != "" &&
        condicionesEspecialesAcondicionamiento != "") {

        save(codigoMetodo, cantidadMuestraEnsayo, cantidadMuestraRetencion, dimensionesCorteProbeta, numeroProbetasMuestras, condicionesEspecialesAcondicionamiento);
    }
}

function save(codigoMetodo, cantidadMuestraEnsayo, cantidadMuestraRetencion, dimensionesCorteProbeta, numeroProbetasMuestras, condicionesEspecialesAcondicionamiento){

    var obj = {
        "codigoMetodo":""+codigoMetodo+"",
        "cantidadMuestraEnsayo":""+cantidadMuestraEnsayo+"",
        "cantidadMuestraRetencion":""+cantidadMuestraRetencion+"",
        "dimensionesCorteProbeta":""+dimensionesCorteProbeta+"",
        "numeroProbetasMuestras":""+numeroProbetasMuestras+"",
        "condicionesEspecialesAcondicionamiento":""+condicionesEspecialesAcondicionamiento+""
    };
    var myjson = JSON.stringify(obj);

    $.ajax({
        type:'POST',
        url:'/method',
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
                window.location = "/registroMetodo";
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
        });
    });
}

function validaModificar() {
    alert("Modificará un almacen");
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Código del Método</th>' +
        '<th class="text-center">Cantidad de Muestra para el Ensayo</th>' +
        '<th class="text-center">Cantidad de Muestra de Retención</th>' +
        '<th class="text-center">Dimensiones de Corte de la Probeta</th>' +
        '<th class="text-center">Número de Probetas o Muestras</th>' +
        '<th class="text-center">Condiciones Especiales de Acondicionamiento</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Código del Método</th>' +
        '<th class="text-center">Cantidad de Muestra para el Ensayo</th>' +
        '<th class="text-center">Cantidad de Muestra de Retención</th>' +
        '<th class="text-center">Dimensiones de Corte de la Probeta</th>' +
        '<th class="text-center">Número de Probetas o Muestras</th>' +
        '<th class="text-center">Condiciones Especiales de Acondicionamiento</th>' +
        '<th class="text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/method", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.codigoMetodo + '</td>' +
                '<td class="text-center">' + field.cantidadMuestraEnsayo + '</td>' +
                '<td class="text-center">' + field.cantidadMuestraRetencion + '</td>' +
                '<td class="text-center">' + field.dimensionesCorteProbeta + '</td>' +
                '<td class="text-center">' + field.numeroProbetasMuestras + '</td>' +
                '<td class="text-center">' + field.condicionesEspecialesAcondicionamiento + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar('+field.methodId+')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar('+field.methodId+')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#methodTable").append(tbl);
        $('#methodTable').DataTable({
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