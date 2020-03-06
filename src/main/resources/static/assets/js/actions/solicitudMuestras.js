var contMuestra = 0;

function agregarMuestra() {
    var muestraAdicional = "";

    muestraAdicional +=
        '<div class="row">' +
        '<label class="col-sm-2 control-label">Método Solicitado: </label>' +
        '<div class="col-sm-4 col-sm-offset-1">' +
        '<div class="form-group">' +
        '<select class="selectpicker" id="'+contMuestra+'">';

    $.getJSON("http://localhost:8080/method", function (result) {
        $.each(result, function (i, field) {
            muestraAdicional +=
                '<option value="' + field.cantidadMuestraEnsayo + '">' + field.codigoMetodo + '</option>';
        });
        muestraAdicional +=
            '</select>' +
            '</div>'+
            '</div>'+
            '</div>';
        var cadena="$("+"\"#"+contMuestra+"\""+")"+".selectpicker("+"\"refresh\")";

        //$("#1")selectpicker("refresh")

        $("#muestraExtra").append(muestraAdicional);
        //$(cadena).selectpicker("refresh");
//        eval('$("'+cadena+'").selectpicker("refresh")');
        eval(cadena);
        contMuestra++;
        //var lista=muestra.getElementsByTagName("select");
        //         $.each(lista,function (i,field) {
        //             field.selectpicker("refresh");
        //         });
        //var cadena='#'+contMuestra;
        //$('"'+cadena+'"').selectpicker("refresh");
        //$("").selectpicker("refresh");

    });
}

function cargar() {
    var combo = "";
    contMuestra++;
    $.getJSON("http://localhost:8080/method", function (result) {
        $.each(result, function (i, field) {
            combo +=
                '<option value="' + field.cantidadMuestraEnsayo + '">' + field.codigoMetodo + '</option>';
            console.log(combo);
        });
        $("#cargarCombo").append(combo);
    });
}

function cantidadMuestrasFunction() {
    var cantMues = document.getElementById('metodo').value;
    var cantidadProducto =
        '<input type="text" placeholder="Cantidad de Muestra" class="form-control" value="' + cantMues + '"' +
        'name="nombrePersonaContacto" id="nombrePersonaContacto" disabled>';

    $("#textbox").empty().append(cantidadProducto);
}