function valida(e){
    var userName = document.getElementById('userName').value;
    var password = document.getElementById('password').value;
    var password1 = document.getElementById('password1').value;
    var nombreUsuario = document.getElementById('nombreUsuario').value;
    var apellidoUsuario = document.getElementById('apellidoUsuario').value;
    var nacimiento = document.getElementById('nacimiento').value;
    var puesto = document.getElementById('puesto').value;

    var rolUsuario = document.getElementById('rolUsuario').value;


    var test = document.getElementsByTagName("input");

    var obj={};
    var clave;
    var valor;
    for (var i=0; i < test.length; i++){
        clave=test[i].getAttribute("id");
        valor=document.getElementById(clave).value;
        obj[clave]=valor;
    }
    console.log(obj);

    var myjson = JSON.stringify(obj);
    //save(obj);

    if (password != password1) {
        swal("Error!", "Las contraseñas no coinciden. Favor de verificar", "error");
    } else if (userName != "" && password != "" && nombreUsuario != "" && apellidoUsuario != "" && nacimiento != "" && puesto != "" && rolUsuario != ""){
        save(userName, password, nombreUsuario, apellidoUsuario, nacimiento, puesto, rolUsuario);
    }
}

function save(userName, password, nombreUsuario, apellidoUsuario, nacimiento, puesto, rolUsuario){

    var obj = {
        "userName":""+userName+"",
        "password":""+password+"",
        "nombreUsuario":""+nombreUsuario+"",
        "apellidoUsuario":""+apellidoUsuario+"",
        "nacimiento":""+nacimiento+"",
        "puesto":""+puesto+"",
        "rolUsuario":""+rolUsuario+""
    };
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
                window.location = "/registerUsuario";
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

function validaModificar() {
    alert("Modificará un almacen");
}

function cargarTabla() {
    var tbl =
        '<thead>' +
        '<tr>' +
        '<th>Código Interno</th>' +
        '<th>Nombre del Equipo o Instrumento</th>' +
        '<th class="disabled-sorting text-right">Acciones</th>' +
        '</tr>' +
        '</thead>' +
        '<tfoot>' +
        '<tr>' +
        '<th>Código Interno</th>' +
        '<th>Nombre del Equipo o Instrumento</th>' +
        '<th class="text-right">Acciones</th>' +
        '</tr>' +
        '</tfoot>' +
        '<tbody>';
    $.getJSON("/user", function (result) {
        $.each(result, function (i, field) {
            tbl +=
                '<tr>' +
                '<td>' + field.appUser.userName + '</td>' +
                '<td>' + field.appRole.roleName + '</td>' +
                '<td class="text-right">' +
                '<button type="submit" class="btn btn-link btn-warning edit" onclick="validaModificar('+field.appUser.userId+')"><i class="fa fa-edit"></i></button>' +
                '<button type="submit" class="btn btn-link btn-danger remove" onclick="validaEliminar('+field.appUser.userId+')"><i class="fa fa-times"></i></a>' +
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