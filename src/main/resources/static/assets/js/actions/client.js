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

        '<label>Cargo</label>' +
        '<input type="text" placeholder="Cargo" class="form-control" th:value="${nombrePersonaContacto}"' +
        'name="cargo'+contClient+'" id="cargo'+contClient+'" required="true">' +

        '<label>Correo Electrónico</label>' +
        '<input type="email" placeholder="Correo Electrónico" class="form-control" th:value="${email}"' +
        'name="email'+contClient+'" id="email'+contClient+'" required="true">' +

        '<label>Teléfono Celular</label>' +
        '<input type="number" placeholder="Teléfono" class="form-control" th:value="${telefono}"' +
        'name="telefonoCelular'+contClient+'" id="telefonoCelular'+contClient+'" required="true">' +

        '<label>Teléfono Oficina</label>' +
        '<input type="number" placeholder="Teléfono" class="form-control" th:value="${telefono}"' +
        'name="telefonoOficina'+contClient+'" id="telefonoOficina'+contClient+'" required="true">';
    $("#contactoExtra").append(clienteExtra);
}

function valida(e){
    var clientId = document.getElementById('clientId').value;
    var nombreRazonSocial = document.getElementById('nombreRazonSocial').value;
    var nombreComunEmpresa = document.getElementById('nombreComunEmpresa').value;
    var calle = document.getElementById('calle').value;
    var numero = document.getElementById('numero').value;
    var colonia = document.getElementById('colonia').value;
    var municipio = document.getElementById('municipio').value;
    var estado = document.getElementById('estado').value;
    var codigoPostal = document.getElementById('codigoPostal').value;
    var rfc = document.getElementById('rfc').value;
    var nombrePersonaContacto = document.getElementById('nombrePersonaContacto').value;
    var email = document.getElementById('email').value;
    var telefonoCelular = document.getElementById('telefonoCelular').value;
    var telefonoOficina = document.getElementById('telefonoOficina').value;

    var test = document.getElementsByTagName("input");
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

    obj["contactos"]=listaContactos;

    console.log("AQUI SE IMPRIME EL OBJ COMPLETO")
    console.log(obj);
    save(obj);
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

function cargarTablaMostrarContactos(jsonArray,idCliente,editar,nombreTabla) {
    var inpuNombre="nombre";
    var inputCargo="cargo";
    var inputEmail="email";
    //var inputTelefono="telefono";
    var inputTelefonoCelular="telefonoCelular";
    var inputTelefonoOficina="telefonoOficina";
    var tabla;
    tabla='<table id="tableContactos" class="table  table-no-bordered table-hover">'+
        '<thead>' +
        '<tr>';
    if(!editar){
        tabla += '<th class="text-center">Nombre del contacto</th>' +
            '<th class="text-center">Cargo</th>' +
            '<th class="text-center">E-mail</th>' +
            '<th class="text-center">Número de télefono Celular</th>' +
            '<th class="text-center">Número de télefono Oficina</th>' +
            '<th class="text-center">Eliminar</th>' +
            '</tr>' +
            '</thead>' +
            '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Nombre del contacto</th>' +
            '<th class="text-center">Cargo</th>' +
            '<th class="text-center">E-mail</th>' +
            '<th class="text-center">Número de télefono Celular</th>' +
            '<th class="text-center">Número de télefono Oficina</th>' +
            '<th class="text-center">Eliminar</th>' +
            '</tr>' +
            '</tfoot>' +
            '<tbody>';
    }else{
        tabla += '<th class="text-center">Nombre del contacto</th>' +
            '<th class="text-center">Cargo</th>' +
            '<th class="text-center">E-mail</th>' +
            '<th class="text-center">Número de télefono Celular</th>' +
            '<th class="text-center">Número de télefono Oficina</th>' +
            '</tr>' +
            '</thead>' +
            '<tfoot>' +
            '<tr>' +
            '<th class="text-center">Nombre del contacto</th>' +
            '<th class="text-center">Cargo</th>' +
            '<th class="text-center">E-mail</th>' +
            '<th class="text-center">Número de télefono Celular</th>' +
            '<th class="text-center">Número de télefono Oficina</th>' +
            '</tr>' +
            '</tfoot>' +
            '<tbody>';
    }
    var idEliminar;
    jsonArray.forEach(function(currentValue,index,arr) {
        if(editar){
            tabla += '<tr>' +
                '<td class="text-center "><input class="form-control" id="'+inpuNombre+index+'" value="'+currentValue.nombrePersonaContacto+'"/><span style=" color: rgba(0, 0, 0, 0);border-left-color: rgba(0, 0, 0, 0);    font-size:0; visibility: hidden;\n">'+currentValue.nombrePersonaContacto+'</span></td>' +
                '<td class="text-center "><input class="form-control" id="'+inputCargo+index+'" value="'+currentValue.cargo+'"/><span style=" color: rgba(0, 0, 0, 0);border-left-color: rgba(0, 0, 0, 0);    font-size:0; visibility: hidden;\n">'+currentValue.cargo+'</span></td>' +
                '<td class="text-center"><input class="form-control" id="'+inputEmail+index+'" value="'+currentValue.email+'"/><span style=" color: rgba(0, 0, 0, 0);border-left-color: rgba(0, 0, 0, 0);    font-size:0; visibility: hidden;\n">'+currentValue.email+'</span></td>' +
                '<td class="text-center"><input class="form-control"  type="number" id="'+inputTelefonoCelular+index+'" value="'+currentValue.telefonoCelular+'"/><span style=" color: rgba(0, 0, 0, 0);border-left-color: rgba(0, 0, 0, 0);    font-size:0; visibility: hidden;\n">'+currentValue.telefono+'</span></td>' +
                '<td class="text-center"><input class="form-control"  type="number" id="'+inputTelefonoOficina+index+'" value="'+currentValue.telefonoOficina+'"/><span style=" color: rgba(0, 0, 0, 0);border-left-color: rgba(0, 0, 0, 0);    font-size:0; visibility: hidden;\n">'+currentValue.telefono+'</span></td>' +
                '</tr>';
        }else{
            idEliminar=index+"_"+idCliente;
            tabla += '<tr>' +
                '<td class="text-center">' + currentValue.nombrePersonaContacto + '</td>' +
                '<td class="text-center">' + currentValue.cargo + '</td>' +
                '<td class="text-center">' +currentValue.email + '</td>' +
                '<td class="text-center">' + currentValue.telefonoCelular + '</td>' +
                '<td class="text-center">' + currentValue.telefonoOficina + '</td>' +
                '<td class="text-center"><button id="'+idEliminar+'" type="button" class="btn btn-success btn-danger remove" >Eliminar</button></td>' +
                '</tr>';
        }
    });
    tabla += '</tbody>';
    return tabla;
}

function tablaContactos(idCliente) {
    var jsonArray=datos[idCliente];
    console.log("datos antes de ",jsonArray);
    swal({
        title: "Contactos",
        html:cargarTablaMostrarContactos(jsonArray,idCliente,false,"tableContactos"),
        width: '1200px',
        height:'1200px',
        showCancelButton: true,
        cancelButtonText:"Regresar",
        confirmButtonClass: "btn btn-info btn-fill",
        confirmButtonText: "Editar",
        cancelButtonClass: "btn btn-danger btn-fill",
        closeOnConfirm: false
    },function () {
        swal({
            title: "Editar contactos",
            html:cargarTablaMostrarContactos(jsonArray,idCliente,true,"tableContactosEditar"),
            width: '1200px',
            height:'1200px',
            showCancelButton: true,
            cancelButtonText:"Cancelar",
            confirmButtonClass: "btn btn-info btn-fill",
            confirmButtonText: "Editar",
            cancelButtonClass: "btn btn-danger btn-fill",
            closeOnConfirm: false
        },function () {
            var obj={};
            var listaContactos=[];
            var contacto={};
            var clave;
            var valor;
            var cont=0;
            var inputs = document.getElementsByTagName("input");
            for (var i=0; i < inputs.length; i++){
                clave=inputs[i].getAttribute("id");
                if(clave!=null){
                    valor=document.getElementById(clave).value;
                    if(clave.includes("nombre")){
                        clave="nombrePersonaContacto";
                    }
                    if(clave.includes("cargo")){
                        clave="cargo";
                    }
                    if(clave.includes("email")){
                        clave="email";
                    }
                    if(clave.includes("telefonoCelular")){
                        clave="telefonoCelular";
                    }
                    if(clave.includes("telefonoOficina")){
                        clave="telefonoOficina";
                    }
                    contacto[clave]=valor;
                    cont=cont+1;
                    if(cont===5){
                        listaContactos.push(contacto);
                        contacto={};
                        cont=0;
                    }
                }
            }
            jsonArray=listaContactos;
            datos[idCliente]=jsonArray;
            obj = JSON.stringify(jsonArray);
            editarContactos(obj,idCliente,"Tu registro se ha actualizado exitosamente","Actualizado!");
        });
        $('#tableContactos').DataTable({
            "pagingType": "full_numbers",
            responsive: true,
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Buscar Registros"
            }
        });
    });
    $('#tableContactos').DataTable({
        "pagingType": "full_numbers",
        responsive: true,
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Buscar Registros"
        }
    });

    var buttons = document.getElementById('tableContactos').getElementsByTagName("button");
    for(var i=0;i<buttons.length;i++){
        buttons[i].addEventListener("click", function(event){
            var cadena=event.target.id;
            var idCliente=cadena.substring(2);
            eliminarContacto(cadena[0],"",jsonArray,idCliente);
            },
            false
        );
    }
}
//posicion a borrar en jsonArray, valor= id del cliente
function eliminarContacto(posicionArray,valor,jsonArray,idCliente) {
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
        jsonArray.splice(posicionArray,1);
        datos[idCliente]=jsonArray;
        jsonArray = JSON.stringify(jsonArray);
        editarContactos(jsonArray,idCliente,"Tu registro ha sido eliminado exitosamente","Eliminado!");
    });
}

function editarContactos(jsonArray,idCliente,texto,titulo) {
    $.ajax({
        type:'POST',
        url:'http://localhost:8080/client/borrarContacto/'+idCliente,
        data:jsonArray,
        cache:false,
        contentType: "application/json",
        processData: false,
        success: function(data){
            console.log("success");
            console.log(data);
            swal({
                title: titulo,
                text: texto,
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false
            }, function () {
                tablaContactos(idCliente);
            });
        },
        error: function(data){
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}


function stringToJSON(cadena) {
    cadena=cadena.replaceAll("=",":");
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
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Nombre de la Razón Social</th>' +
        '<th class="text-center">Nombre Común de la Empresa</th>' +
        '<th class="text-center">Calle</th>' +
        '<th class="text-center">Número</th>' +
        '<th class="text-center">Colonia</th>' +
        '<th class="text-center">Municipio</th>' +
        '<th class="text-center">Estado</th>' +
        '<th class="text-center">Código Postal</th>' +
        '<th class="text-center">RFC</th>' +
        '<th class="text-center">Contactos</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th class="text-center">Folio</th>' +
        '<th class="text-center">Nombre de la Razón Social</th>' +
        '<th class="text-center">Nombre Común de la Empresa</th>' +
        '<th class="text-center">Calle</th>' +
        '<th class="text-center">Número</th>' +
        '<th class="text-center">Colonia</th>' +
        '<th class="text-center">Municipio</th>' +
        '<th class="text-center">Estado</th>' +
        '<th class="text-center">Código Postal</th>' +
        '<th class="text-center">RFC</th>' +
        '<th class="text-center">Contactos</th>' +
        '<th class="disabled-sorting text-center">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("http://localhost:8080/client", function (result) {
        $.each(result, function (i, field) {
            datos[field.clientId]=stringToJSON(field.contactosDatos);
            tbl +=
                '<tr>' +
                '<td class="text-center">' + field.folioCliente + '</td>' +
                '<td class="text-center">' + field.nombreRazonSocial + '</td>' +
                '<td class="text-center">' + field.nombreComunEmpresa + '</td>' +
                '<td class="text-center">' + field.calle + '</td>' +
                '<td class="text-center">' + field.numero + '</td>' +
                '<td class="text-center">' + field.colonia + '</td>' +
                '<td class="text-center">' + field.municipio + '</td>' +
                '<td class="text-center">' + field.estado + '</td>' +
                '<td class="text-center">' + field.codigoPostal + '</td>' +
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

