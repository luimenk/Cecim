function valida1(e){
    var username = document.getElementById('username').value;

    if (username != "") {
        save(username);
    }
}

function save(username){

    var obj = {
        "username":""+username+""
    };
    var myjson = JSON.stringify(obj);

    $.ajax({
        type:'POST',
        url:'/recuperaCuenta/correo',
        data:myjson,
        cache:false,
        contentType: "application/json",
        processData: false,
        success: function(data){
            console.log("success");
            console.log(data.status);
            swal({
                title: "Código generado!",
                text: "Se ha generado un código para recuperar la contraseña. Revise su correo electrónico por favor.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location = "/nameCodigo";
            });
        },
        error: function(data){
            console.log("error");
            console.log(data.status);
            if (data.status === 404){
                swal("Alerta!", " El correo no existe, favor de contactar al administrador del laboratorio.", "warning");
            } else{
                swal("Error!", "Ha ocurrido un error. Favor de contactar a la Mesa de Ayuda y Servicios.", "error");
            }

        }
    });
}

function valida2(e){
    var codigo = document.getElementById('codigo').value;
    var password = document.getElementById('password').value;
    var password2 = document.getElementById('password2').value;

    if (codigo != "", password != "", password2 != "") {
        save2(codigo, password, password2);
    }
}

function save2(codigo, password, password2){

    var obj = {
        "codigo":""+codigo+"",
        "password":""+password+""
    };
    var myjson = JSON.stringify(obj);

    $.ajax({
        type:'POST',
        url:'/recuperaCuenta/codigo',
        data:myjson,
        cache:false,
        contentType: "application/json",
        processData: false,
        success: function(data){
            console.log("success");
            console.log(data);
            swal({
                title: "Contraseña actualizada!",
                text: "Se ha enviado la nueva información a su correo electrónico. Favor de verificar para iniciar sesión.",
                type: "success",
                showCancelButton: false,
                confirmButtonClass: "btn btn-info btn-fill",
                confirmButtonText: "Ok",
                closeOnConfirm: false,
            }, function () {
                window.location.href = "/";
            });
        },
        error: function(data){
            console.log("error");
            console.log(data);
            swal("Error!", "Ha ocurrido un error. Favor de contactar al administrador.", "error");
        }
    });
}