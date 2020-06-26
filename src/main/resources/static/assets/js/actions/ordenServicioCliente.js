var contMuestra = 0;

function agregarMuestra() {
    contMuestra++;
    var muestraAdicional = "";

    muestraAdicional += "<div class=\"row\">" +
        "                                    <label class=\"col-sm-2 control-label\">Método Solicitado: </label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <select class=\"selectpicker\" name=\"metodo" + contMuestra + "\" id=\"metodo" + contMuestra + "\" required=\"true\"" +
        "                                                    data-title=\"Selecciona un método...\"" +
        "                                                    data-style=\"btn-default btn-outline\"" +
        "                                                    data-menu-style=\"dropdown-blue\"" +
        "                                                    onchange=\"chequeo()\">";

    $.getJSON("/method", function (result) {
        $.each(result, function (i, field) {
            muestraAdicional += "<option value = \"" + field.cantidadMuestraEnsayo + "\">" + field.codigoMetodo + "</option>";
        });
        muestraAdicional +=
            "</select>" +
            "                                        </div>" +
            "                                    </div>" +
            "                                    <label class=\"col-sm-2 control-label\">Cantidad de Muestra:</label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\"" +
            "                                                   name=\"cantidadMuestra" + contMuestra + "\"" +
            "                                                   id=\"cantidadMuestra" + contMuestra + "\" required=\"true\" disabled>" +
            "                                        </div>" +
            "                                    </div>" +
            "                                </div>" +
            "                                <div class=\"row\">" +
            "                                    <label class=\"col-sm-2 control-label\">Número de ensayos solicitados: </label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\" "+
            "                                                   name=\"numeroEnsayos" + contMuestra + "\"" +
            "                                                   id=\"numeroEnsayos" + contMuestra + "\" required=\"true\">" +
            "                                        </div>" +
            "                                    </div>" +
            "                                    <label class=\"col-sm-2 control-label\">Descripción de la muestra:</label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\"" +
            "                                                   name=\"descripcionMuestra" + contMuestra + "\"" +
            "                                                   id=\"descripcionMuestra" + contMuestra + "\" required=\"true\">" +
            "                                        </div>" +
            "                                    </div>" +
            "                                </div>" +
            "                                <div class=\"row\">" +
            "                                    <label class=\"col-sm-2 control-label\">Observaciones y condiciones especiales:</label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\" " +
        "                                                   name=\"observacionesCondiciones" + contMuestra + "\"" +
        "                                                   id=\"observacionesCondiciones" + contMuestra + "\" required=\"true\">" +
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
    /*var test = document.getElementsByTagName("input");
    console.log(test.length);

    var obj={};
    var listaContactos=[];
    var clave;
    var valor;
    var cont=0;
    var contacto={};
    for (var i=0; i < test.length; i++){
        if (i >= 10) {
            clave=test[i].getAttribute("id");
            valor=document.getElementById(clave).value;
            contacto[clave]=valor;
            cont=cont+1;
            if(cont===5){
                listaContactos.push(contacto);
                contacto={};
                cont=0;
            }
        } else {
            clave=test[i].getAttribute("id");
            valor=document.getElementById(clave).value;
            obj[clave]=valor;
        }
    }

    obj["contactos"]=listaContactos;*/

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
        url:'/osc',
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
                window.location = "/listOrdenServicio";
            });
        },
        error: function(data){
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Fecha</th>' +
        '<th class="text-center">Cliente</th>' +
        '<th class="text-center">Personal</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Fecha</th>' +
        '<th class="text-center">Cliente</th>' +
        '<th class="text-center">Personal</th>' +
        '<th class="text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/osc", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioOrden + '</td>' +
                '<td class="text-center">' + field.fechaOrdenServicio + '</td>' +
                '<td class="text-center">' + field.client.nombreRazonSocial + '</td>' +
                '<td class="text-center">' + field.appUser.nombreUsuario + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar('+field.methodId+')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar('+field.methodId+')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#ordenServicioTable").append(tbl);
        $('#ordenServicioTable').DataTable({
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