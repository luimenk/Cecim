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
            muestraAdicional += "<option value = \"" + field.cantidadMuestraEnsayo + "\">" + field.codigoMetodo + "</option>";
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