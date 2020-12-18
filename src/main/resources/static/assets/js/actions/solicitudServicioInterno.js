function validaImprimir6(){
    const url = document.URL;
    const id = url.substring(url.lastIndexOf('/') + 1);
    window.location = "/solicitudServicioCliente/imprimirSolicitudServicioInterno/" + id;
}

function verDetalles(valor){
    window.location = "/detalleSolicitudServicioInterno/" + valor;
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Fecha de envio de muestras</th>' +
        '<th class="text-center">Fecha de pago</th>' +
        '<th class="text-center">Fecha compromiso</th>' +
        '<th class="text-center">Ver detalles</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Fecha de envio de muestras</th>' +
        '<th class="text-center">Fecha de pago</th>' +
        '<th class="text-center">Fecha compromiso</th>' +
        '<th class="text-center">Ver detalles</th>' +
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
                '<td class="text-center"><button class="btn btn-success" onclick="verDetalles(' + field.solicitudServicioClienteId + ')"><i class="fa fa-eye"></i>Ver detalles</button></td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-info edit" onclick="validaImprimir(' + field.solicitudServicioClienteId + ')"><i class="fa fa-print"></i></button>' +
                // '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-edit"></i></button>' +
                // '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar(' + field.solicitudServicioClienteId + ')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#solicitudServicioInternoTable").append(tbl);
        $('#solicitudServicioInternoTable').DataTable({
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