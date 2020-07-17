var contMuestra = 0;

function agregarMuestra() {
    contMuestra++;
    var muestraAdicional = "";
    
    muestraAdicional += " <div class=\"row\">" +
        "                                    <label class=\"col-sm-2 control-label\">ID cliente de la muestra:</label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <input type=\"text\" class=\"form-control\" " +
        "                                                   name=\"idClienteMuestra" + contMuestra + "\"  id=\"idClienteMuestra" + contMuestra + "\" required=\"true\">"+
        "                                        </div>" +
        "                                    </div>" +
        "                                    <label class=\"col-sm-2 control-label\">Tipo de muestra:</label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <input type=\"text\" class=\"form-control\"" +
        "                                                   name=\"tipoMuestra" + contMuestra + "\"  id=\"tipoMuestra" + contMuestra + "\" required=\"true\">" +
        "                                        </div>" +
        "                                    </div>" +
        "                                </div>" +
        "" +
        "                                <div class=\"row\">" +
        "                                    <label class=\"col-sm-2 control-label\">Descripción de la muestra:</label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <input type=\"text\" class=\"form-control\"" +
        "                                                   name=\"descripcionMuestra" + contMuestra + "\"  id=\"descripcionMuestra" + contMuestra + "\" required=\"true\">" +
        "                                        </div>" +
        "                                    </div>" +
        "                                    <label class=\"col-sm-2 control-label\">Método Solicitado:</label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <select class=\"selectpicker\" name=\"metodo" + contMuestra + "\" id=\"metodo" + contMuestra + "\" required=\"true\"" +
        "                                                    data-title=\"Selecciona un método...\"" +
        "                                                    data-style=\"btn-default btn-outline\"" +
        "                                                    data-menu-style=\"dropdown-blue\"" +
        "                                                    onchange=\"chequeo()\">";

    $.getJSON("/method", function (result) {
        $.each(result, function (i, field) {
            muestraAdicional += "<option value = \"" + field.methodId + "\">" + field.codigoMetodo + "</option>";
        });
        
        muestraAdicional += "</select>" +
            "                                        </div>" +
            "                                    </div>" +
            "                                </div>" +
            "                                <div class=\"row\">" +
            "                                    <label class=\"col-sm-2 control-label\">Condiciones Especiales:</label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\"" +
            "                                                   name=\"condicionesEspeciales" + contMuestra + "\"  id=\"condicionesEspeciales" + contMuestra + "\" required=\"true\">" +
            "                                        </div>" +
            "                                    </div>" +
          //  "                                    <label class=\"col-sm-2 control-label\">Cantidad de Muestra:</label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"hidden\" class=\"form-control\"" +
            "                                                   name=\"cantidadMuestra" + contMuestra + "\"" +
            "                                                   id=\"cantidadMuestra" + contMuestra + "\" required=\"true\" disabled>" +
            "                                        </div>" +
            "                                    </div>" +
            "                                </div>" +
            "                                <div class=\"row\">" +
            "                                    <label class=\"col-sm-2 control-label\">Observaciones: </label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\"" +
            "                                                   name=\"observaciones" + contMuestra + "\"  id=\"observaciones" + contMuestra + "\" required=\"true\">" +
            "                                        </div>" +
            "                                    </div>" +
            "                                </div>";

        var cadena = "$(" + "\"#metodo" + contMuestra + "\"" + ")" + ".selectpicker(" + "\"refresh\")";

        $("#muestraExtra").append(muestraAdicional);
        eval(cadena);
    });
}

function chequeo() {
    console.log("entro");
    for (var i = 0; i <= contMuestra; i++) {
        document.getElementById("cantidadMuestra"+i).value = document.getElementById("metodo"+i).value;
    }
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

    obj["contMuestra"]=contMuestra;

    var myjson = JSON.stringify(obj);
    console.log(myjson);
    save(myjson);
}

function save(myjson){
    $.ajax({
        type:'POST',
        url:'/solicitudServicioCliente',
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

function validaEliminar(){

}

function validaModificar(valor) {
    //window.location = "/registroCliente/" + valor;
}

function validaImprimir(valor) {
    window.location = "/solicitudServicioCliente/imprimirSolicitud/" + valor;
}

function validaImprimirEtiqueta(valor) {
    window.location = "/solicitudServicioCliente/imprimirEtiquetasIdentificacion/" + valor;
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Fecha de envio de muestras</th>' +
        '<th class="text-center">Fecha de pago</th>' +
        '<th class="text-center">Fecha compromiso</th>' +
        '<th class="text-center">Etiquetas</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Fecha de envio de muestras</th>' +
        '<th class="text-center">Fecha de pago</th>' +
        '<th class="text-center">Fecha compromiso</th>' +
        '<th class="text-center">Etiquetas</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/solicitudServicioCliente", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioSolitudServicioCliente + '</td>' +
                '<td class="text-center">' + field.fechaEnvioMuestras + '</td>' +
                '<td class="text-center">' + field.fechaPago + '</td>' +
                '<td class="text-center">' + field.fechaCompromisoEntrega + '</td>' +
                '<td class="text-center">' + '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimirEtiqueta('+field.solicitudServicioClienteId+')"><i class="fa fa-print"></i></button>' + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir('+field.solicitudServicioClienteId+')"><i class="fa fa-print"></i></button>' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#solicitudServicioClienteTable").append(tbl);
        $('#solicitudServicioClienteTable').DataTable({
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