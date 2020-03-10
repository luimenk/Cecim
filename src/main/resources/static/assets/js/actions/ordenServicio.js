var contMuestra = 0;

function agregarMuestra() {
    var muestraAdicional = "";

    muestraAdicional +=
        '<div class="row">' +
        '<label class="col-sm-2 control-label">Método Solicitado: </label>' +
        '<div class="col-sm-4 col-sm-offset-1">' +
        '<div class="form-group">' +
        '<select class="selectpicker" name="metodo'+contMuestra+'" id="metodo'+contMuestra+'" required="true"' +
                'data-title="Selecciona un método..."' +
                'data-style="btn-default btn-outline"' +
                'data-menu-style="dropdown-blue"' +
                'onchange="cantidadMuestrasFunction()">';

    $.getJSON("http://localhost:8080/method", function (result) {
        $.each(result, function (i, field) {
            muestraAdicional +=
                '<option value="' + field.cantidadMuestraEnsayo + '">' + field.codigoMetodo + '</option>';
        });
        muestraAdicional +=
            '</select>' +
            '</div>'+
            '</div>'+
            '<label class="col-sm-2 control-label">Cantidad de Muestra:</label>'+
            '<div class="col-sm-4 col-sm-offset-1">'+
            '<div class="form-group" id="textbox'+contMuestra+'">'+
            '</div>'+
            '</div>'+
            '</div>'+
            '<div class="row">'+
            '<label class="col-sm-2 control-label">Número de ensayos solicitados: </label>'+
            '<div class="col-sm-4 col-sm-offset-1">'+
            '<div class="form-group">'+
            '<input type="text" class="form-control" placeholder="Número de ensayos">'+
            '</div>'+
            '</div>'+
            '<label class="col-sm-2 control-label">Descripción de la muestra:</label>'+
            '<div class="col-sm-4 col-sm-offset-1">'+
            '<div class="form-group">'+
            '<input type="text" class="form-control" placeholder="Descripción de la Muestra">'+
            '</div>'+
            '</div>'+
            '</div>'+
            '<div class="row">'+
            '<label class="col-sm-2 control-label">Observaciones y condiciones especiales:</label>'+
            '<div class="col-sm-4 col-sm-offset-1">'+
            '<div class="form-group">'+
            '<input type="text" class="form-control" placeholder="Observaciones">'+
            '</div>'+
            '</div>'+
            '</div>';
        var cadena="$("+"\"#metodo"+contMuestra+"\""+")"+".selectpicker("+"\"refresh\")";

        $("#muestraExtra").append(muestraAdicional);
        eval(cadena);
        contMuestra++;
    });

    function cantidadMuestrasFunction() {
        var cantMues = document.getElementById('metodo'+contMuestra).value;
        var cantidadProducto =
            '<input type="text" placeholder="Cantidad de Muestra" class="form-control" value="' + cantMues + '"' +
            'name="nombrePersonaContacto" id="nombrePersonaContacto" disabled>';

        var cadena="$("+"\"#textbox"+contMuestra+"\""+")"+".empty().append("+cantidadProducto+")";
        eval(cadena);
        //$("#textbox").empty().append(cantidadProducto);
    }
}

