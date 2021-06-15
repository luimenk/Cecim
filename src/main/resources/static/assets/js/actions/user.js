function valida(e){
    let arrayChecked = [];
    let checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
    for (let i = 0; i < checkboxes.length; i++) {
        arrayChecked.push(checkboxes[i].value);
    }

    var obj = {};
    let inputText = document.querySelectorAll('input[type=text]');
    for (let j = 0; j < inputText.length; j++) {
        obj[inputText[j].getAttribute("id")] = inputText[j].value;
    }

    let pass1 = document.getElementById('password').value;
    let pass2 = document.getElementById('password1').value;

    obj['password1'] = pass1;
    obj['password2'] = pass2;
    obj['permisos'] = arrayChecked;

    if (pass1 !== pass2) {
        swal("¡Alerta!", "Las contraseñas no coinciden", "warning");
    } else {
        save(obj);
    }
}

function valida2() {
    let arrayChecked = [];
    let checkboxes = document.querySelectorAll('input[type=checkbox]:checked');
    for (let i = 0; i < checkboxes.length; i++) {
        arrayChecked.push(checkboxes[i].value);
    }

    var obj = {};
    let inputText = document.querySelectorAll('input[type=text]');
    for (let j = 0; j < inputText.length; j++) {
        obj[inputText[j].getAttribute("id")] = inputText[j].value;
    }

    let pass1 = document.getElementById('password').value;
    let pass2 = document.getElementById('password1').value;
    let userId = document.getElementById('userId').value;

    obj['password1'] = pass1;
    obj['password2'] = pass2;
    obj['permisos'] = arrayChecked;
    obj['userId'] = userId;

    if (pass1 !== pass2) {
        swal("¡Alerta!", "Las contraseñas no coinciden", "warning");
    } else {
        save2(obj);
    }
}

function save(obj){
    var myjson = JSON.stringify(obj);
    console.log(myjson);
    $.ajax({
        type:'POST',
        url:'/user',
        data:myjson,
        cache:false,
        contentType: "application/json",
        processData: false,
        success: function(data){
            console.log("success");
            console.log(data.status);
            swal({
                title: "Registrado!",
                text: "Tu registro ha sido registrado exitosamente.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listUsuario";
            });
        },
        error: function(data){
            console.log("error");
            console.log(data.status);
            if (data.status === 409){
                swal("Alerta!", " El correo capturado ya ha sido utilizado. Favor de validar la información.", "warning");
            } else{
                swal("Error!", "Ha ocurrido un error. Favor de contactar a la Mesa de Ayuda y Servicios.", "error");
            }
        }
    });
}

function save2(obj){
    var myjson = JSON.stringify(obj);
    console.log(myjson);
    $.ajax({
        type:'POST',
        url:'/user/modificar',
        data:myjson,
        cache:false,
        contentType: "application/json",
        processData: false,
        success: function(data){
            console.log("success");
            console.log(data.status);
            swal({
                title: "Registrado!",
                text: "Tu registro ha sido registrado exitosamente.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/listUsuario";
            });
        },
        error: function(data){
            console.log("error");
            console.log(data.status);
            if (data.status === 409){
                swal("Alerta!", " El correo capturado ya ha sido utilizado. Favor de validar la información.", "warning");
            } else{
                swal("Error!", "Ha ocurrido un error. Favor de contactar a la Mesa de Ayuda y Servicios.", "error");
            }
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
            url:'/user/'+valor,
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
                    window.location = "/listUsuario";
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

function validaModificar(valor) {
    window.location = "/registroUsuario/" + valor;
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th>Correo electrónico</th>' +
        '<th>Puesto</th>' +
        '<th>Nombre completo</th>' +
        '<th class="disabled-sorting text-right">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th>Correo electrónico</th>' +
        '<th>Puesto</th>' +
        '<th>Nombre completo</th>' +
        '<th class="text-right">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/user", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td>' + field.userName + '</td>' +
                '<td>' + field.puesto + '</td>' +
                '<td>' + field.nombreUsuario + ' ' + field.apellidoUsuario +'</td>' +
                '<td class="text-right">' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar('+field.userId+')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar('+field.userId+')"><i class="fa fa-times"></i></a>' +
                '</td>' +
                '</tr>';
        });
        tbl += '</tbody>';
        $("#userTable").append(tbl);
        $('#userTable').DataTable({
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