function cotizacion(valor){
    window.location = "/solicitudServicioCliente/imprimirCotizacionContrato/" + valor;
}

function cotizacion2(valor){
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/solicitudServicioCliente/imprimirCotizacionContrato/" + id;
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">#</th>' +
        '<th class="text-center">Fecha</th>' +
        '<th class="text-center">Folio Solicitud Servicio</th>' +
        '<th class="text-center">Folio Cotización Contrato</th>' +
        '<th class="text-center">Personal Cecim</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">#</th>' +
        '<th class="text-center">Fecha</th>' +
        '<th class="text-center">Folio Solicitud Servicio</th>' +
        '<th class="text-center">Folio Cotización Contrato</th>' +
        '<th class="text-center">Personal Cecim</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/solicitudServicioCliente", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td class="text-center">' + (i+1) + '</td>' +
                '<td class="text-center">' + field.fechaRecepcionMuestras + '</td>' +
                '<td class="text-center">' + field.folioSolitudServicioCliente + '</td>' +
                '<td class="text-center">' + field.folioSolitudServicioCliente + '</td>' +
                '<td class="text-center">' + field.nombreFirmaReceptor + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="cotizacion(' + field.solicitudServicioClienteId + ')"><i class="fa fa-print"></i></button>' +
                // '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-edit"></i></button>' +
                // '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#cotizacionContratoTable").append(tbl);
        $('#cotizacionContratoTable').DataTable({
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