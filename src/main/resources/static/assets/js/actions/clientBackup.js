var contClient = 0;
var datos={};
var contactosModificar={};
function agregaCliente() {
    var clienteExtra = "";
    contClient++;
    clienteExtra +=
        '<label>Nombre de la Persona de Contacto</label>' +
        '<input type="text" placeholder="Nombre de la Persona de Contacto" class="form-control" th:value="${nombrePersonaContacto}"' +
               'name="nombrePersonaContacto'+contClient+'" id="nombrePersonaContacto'+contClient+'" required="true">' +

        '<label>Correo Electrónico</label>' +
        '<input type="email" placeholder="Correo Electrónico" class="form-control" th:value="${email}"' +
               'name="email'+contClient+'" id="email'+contClient+'" required="true">' +

        '<label>Teléfono</label>' +
        '<input type="number" placeholder="Teléfono" class="form-control" th:value="${telefono}"' +
                'name="telefono'+contClient+'" id="telefono'+contClient+'" required="true">';

    $("#contactoExtra").append(clienteExtra);
}

function valida(e){
    var clientId = document.getElementById('clientId').value;
    var nombreRazonSocial = document.getElementById('nombreRazonSocial').value;
    var nombreComunEmpresa = document.getElementById('nombreComunEmpresa').value;
    var direccion = document.getElementById('direccion').value;
    var rfc = document.getElementById('rfc').value;
    var nombrePersonaContacto = document.getElementById('nombrePersonaContacto').value;
    var email = document.getElementById('email').value;
    var telefono = document.getElementById('telefono').value;

    var test = document.getElementsByTagName("input");
    console.log(test.length);

    var obj={};
    var listaContactos=[];
    var clave;
    var valor;
    var cont=0;
    var contacto={};
    for (var i=0; i < test.length; i++){
        if (i >= 5) {
            clave=test[i].getAttribute("id");
            valor=document.getElementById(clave).value;
            contacto[clave]=valor;
            cont=cont+1;
            if(cont===3){
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

    obj["contactos"]=listaContactos;


    console.log(obj);



    //var myjson = JSON.stringify(obj);

    /*console.log(myjson);*/


       save(obj);

    /*if (nombreRazonSocial != "" && nombreComunEmpresa != "" && direccion != "" && rfc != "" && nombrePersonaContacto != "" && email != "" && telefono != "") {
        //save(clientId, nombreRazonSocial, nombreComunEmpresa, direccion, rfc, nombrePersonaContacto, email, telefono);
    }*/
}

function save(obj){

    var estatus="";
    if (obj.clientId === "") {
        estatus = "r";
    } else {
        estatus = "m";
    }

    var myjson = JSON.stringify(obj);

    $.ajax({
        type:'POST',
        url:'http://localhost:8080/client',
        data:myjson,
        cache:false,
        contentType: "application/json" ,
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
                if (estatus == "r") {
                    window.location = "/registroCliente";
                } else {
                    window.location = "/mostrarClientes";
                }
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
        closeOnConfirm: false
    }, function () {
        $.ajax({
            type:'DELETE',
            url:'http://localhost:8080/client/'+valor,
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
                    closeOnConfirm: false
                }, function () {
                    window.location = "/mostrarClientes";
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
function validaModificarContactos(idCliente){
    var cliente=contactosModificar[idCliente];
    var tdNombre=cliente.nombrePersonaContacto;
    var tdEmail=cliente.email;
    var tdTelefono=cliente.telefono;


    //contactosModificar[idCliente]={
    //             "nombre":idCliente+index+nombre,
    //             "email":idCliente+index+email,
    //             "telefono":idCliente+index+telefono
    //         };

}

function tablaContactos(idCliente) {
    var cadena=datos[idCliente];
    var jsonArray=stringToJSON(cadena);
    var nombre="nombre";
    var email="email";
    var telefono="telefono";
    console.log(jsonArray);
    var tabla ='<table id="tableContactos" class="table table-striped table-no-bordered table-hover">'+
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Nombre del contacto</th>' +
        '<th class="text-center">E-mail</th>' +
        '<th class="text-center">Número de télefono</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Nombre del contacto</th>' +
        '<th class="text-center">E-mail</th>' +
        '<th class="text-center">Número de télefono</th>' +
        '<th class="text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    jsonArray.forEach(function(currentValue,index,arr) {
        contactosModificar[idCliente]={
            "nombre":idCliente+index+nombre,
            "email":idCliente+index+email,
            "telefono":idCliente+index+telefono
        };
        tabla += '<tr>' +
            '<td class="text-center" id="'+idCliente+index+nombre+'">' + currentValue.nombrePersonaContacto + '</td>' +
            '<td class="text-center" id="'+idCliente+index+email+'">' +currentValue.email + '</td>' +
            '<td class="text-center" id="'+idCliente+index+telefono+'">' + currentValue.telefono + '</td>' +

            '</tr>';
    });
    tabla += '</tbody>';
    tabla += '<button type="submit" class="btn btn-link btn-warning edit" onclick=""><i class="fa fa-edit"></i>Modificar contactos</button>';
    swal({
        title: "Contactos",
        html:tabla,
        width: '1200px',
        height:'1200px'
    });
    $('#tableContactos').DataTable({
        "pagingType": "full_numbers",
        responsive: true,
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Buscar Registros"
        }
    });
}

function stringToJSON(cadena) {
    cadena=cadena.replaceAll("=",":");
    cadena=cadena.replaceAll(" ","");
    cadena=cadena.replaceAll("-","");
    cadena=eliminarDiacriticos(cadena);
    cadena=fixJson(cadena);
    return $.parseJSON(cadena);
}

function eliminarDiacriticos(texto) {
    return texto.normalize('NFD').replace(/[\u0300-\u036f]/g,"");
}
String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.split(search).join(replacement);
};

function fixJson(badJSON) {
    return badJSON
    // Replace ":" with "@colon@" if it's between double-quotes
        .replace(/:\s*"([^"]*)"/g, function (match, p1) {
            return ': "' + p1.replace(/:/g, '@colon@') + '"';
        })
        // Replace ":" with "@colon@" if it's between single-quotes
        .replace(/:\s*'([^']*)'/g, function (match, p1) {
            return ': "' + p1.replace(/:/g, '@colon@') + '"';
        })
        // Add double-quotes around any tokens before the remaining ":"
        .replace(/(['"])?([a-z0-9A-Z_]+)(['"])?\s*:/g, '"$2": ')
        // Turn "@colon@" back into ":"
        .replace(/@colon@/g, ':');
}

function validaModificar(valor) {
    window.location = "/registroCliente/" + valor;
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th class="text-center">Nombre de la Razón Social</th>' +
        '<th class="text-center">Nombre Común de la Empresa</th>' +
        '<th class="text-center">Dirección</th>' +
        '<th class="text-center">RFC</th>' +
        '<th class="text-center">Contactos</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Nombre de la Razón Social</th>' +
        '<th class="text-center">Nombre Común de la Empresa</th>' +
        '<th class="text-center">Dirección</th>' +
        '<th class="text-center">RFC</th>' +
        '<th class="text-center">Contactos</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("http://localhost:8080/client", function (result) {
        $.each(result, function (i, field) {
            datos[field.clientId]=field.contactosDatos;
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.nombreRazonSocial + '</td>' +
                '<td class="text-center">' + field.nombreComunEmpresa + '</td>' +
                '<td class="text-center">' + field.direccion + '</td>' +
                '<td class="text-center">' + field.rfc + '</td>' +
                '<td class="text-center">' +
                '<button type="button" class="btn btn-success edit" onclick="tablaContactos('+field.clientId+')"><i class=""></i>Ver contactos</button>' + '</td>' +
                '<td class="text-center">' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar('+field.clientId+')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar('+field.clientId+')"><i class="fa fa-times"></i></button>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#clientTable").append(tbl);
        $('#clientTable').DataTable({
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

