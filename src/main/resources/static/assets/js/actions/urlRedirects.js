function permisos(){
    //username
}

function dashboard(e){
    window.location = "/dashboard";
}

function registerMachineDirect(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_REGISTRAREQUIPO") {
                cont++;
                window.location = "/registroMaquina";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function listMachineDirect(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTAREQUIPO") {
                cont++;
                window.location = "/mostrarMaquinas";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function registerUserDirect(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_REGISTRARUSUARIOS") {
                cont++;
                window.location = "/registerUsuario";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function listUserDirect(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTARUSUARIOS") {
                cont++;
                window.location = "/listUsuario";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function registerClientDirect(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_REGISTRARCLIENTES") {
                cont++;
                window.location = "/registroCliente";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function listClientDirect(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTARCLIENTES") {
                cont++;
                window.location = "/mostrarClientes";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function registerMethodDirect(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_REGISTRARMETODOS") {
                cont++;
                window.location = "/registroMetodo";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function listMethodDirect(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTARMETODOS") {
                cont++;
                window.location = "/mostrarMetodos";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

//Cálculos
function generadorReporteDirect(e) {
    window.location = "/generadorReporte";
}

function registerSolicitudServicio(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_REGISTRARSOLICITUDSERVICI") {
                cont++;
                window.location = "/registerSolicituedServicio";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function listSolicitudServicio(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTARSOLICITUDSERVICI") {
                cont++;
                window.location = "/listSolicitudServicio";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function listSolicitudServicioPagos(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_PAGOSSOLICITUDSERVICIOCLI") {
                cont++;
                window.location = "/listSolicitudServicioPagos";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

/*function registerCotizacionContrato(e) {
    window.location = "/registerCotizacionContrato";
}*/

function listCotizacionContrato(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTARCOTIZACIONCONTRA") {
                cont++;
                window.location = "/listCotizacionContrato";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function listSolicitudServicioInterno(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTARSOLICITUDSERVICI") {
                cont++;
                window.location = "/listSolicitudServicioInterno";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

/*function registerEtiquetas(e) {
    window.location = "/registerEtiquetas";
}

function listEtiquetas(e) {
    window.location = "/listEtiquetas";
}*/

function registerValidacion(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_REGISTRARRETENCION") {
                cont++;
                window.location = "/registerValidacion";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function registerValidacionFolio(e) {
    window.location = "/registerValidacion";
}

function listValidacion(e) {
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTARRETENCION") {
                cont++;
                window.location = "/listRecepcionValidacion";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function lecturaQR(){
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_REGISTRARLECTURAENSAYO") {
                cont++;
                window.location = "/lecturaQR";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function consultaListaEnsayos(e){
    let cont = 0;
    let param = document.getElementById("username").value;
    $.getJSON("/user/user/" + param, function (result) {
        $.each(result, function (i, field) {
            if (field.appRole.roleName === "ROLE_SUPERUSUARIO" || field.appRole.roleName === "ROLE_CONSULTARLISTAENSAYOS") {
                cont++;
                window.location = "/listEnsayos";
            }
        });
    }).done(function (){
        if (cont === 0 ){
            swal("¡Sin acceso!", "No tienes permisos para entrar aquí", "warning");
        }
    });
}

function listFRAAT(e) {
    window.location = "/listFRAAT";
}

function listFRADI(e) {
    window.location = "/listFRADI";
}

function listFRAES(e) {
    window.location = "/listFRAES";
}


function registerEspesor(e) {
    window.location = "/registerEspesor";
}



function registerFRADI(e) {
    window.location = "/registerFRADI";
}



function registerSelladoTemperatura(e) {
    window.location = "/registerSelladoTemperatura";
}

function listSelladoTemperatura(e) {
    window.location = "/listSelladoTemperatura";
}

function registerEspectrometriaInfrarroja(e) {
    window.location = "/registerEspectrometriaInfrarroja";
}

function listEspectrometriaInfrarroja(e) {
    window.location = "/listEspectrometriaInfrarroja";
}

function registerElongacionRuptura(e) {
    window.location = "/registerElongacionRuptura";
}

function listElongacionRuptura(e) {
    window.location = "/listElongacionRuptura";
}

function registerFRAPRR(e) {
    window.location = "/registerFRAPRR";
}

function listFRAPRR(e) {
    window.location = "/listFRAPRR";
}

function registerFRADSC(e) {
    window.location = "/registerFRADSC";
}

function listFRADSC(e) {
    window.location = "/listFRADSC";
}

function registerFRAGR(e) {
    window.location = "/registerFRAGR";
}

function listFRAGR(e) {
    window.location = "/listFRAGR";
}

function registerFRAHUM(e) {
    window.location = "/registerFRAHUM";
}

function listFRAHUM(e) {
    window.location = "/listFRAHUM";
}

function registerFRAPPG(e) {
    window.location = "/registerFRAPPG";
}

function listFRAPPG(e) {
    window.location = "/listFRAPPG";
}

function registerFRAAT(e) {
    window.location = "/registerFRAAT";
}


function registerFRAIF(e) {
    window.location = "/registerFRAIF";
}

function listFRAIF(e) {
    window.location = "/listFRAIF";
}

function registerFRAPO(e) {
    window.location = "/registerFRAPO";
}

function listFRAPO(e) {
    window.location = "/listFRAPO";
}

function registerFRATGA(e) {
    window.location = "/registerFRATGA";
}

function listFRATGA(e) {
    window.location = "/listFRATGA";
}

function registerFRANCP(e) {
    window.location = "/registerFRANCP";
}

function listFRANCP(e) {
    window.location = "/listFRANCP";
}

function registerFRAEAT(e) {
    window.location = "/registerFRAEAT";
}

function listFRAEAT(e) {
    window.location = "/listFRAEAT";
}

function registerFRAEAUV(e) {
    window.location = "/registerFRAEAUV";
}

function listFRAEAUV(e) {
    window.location = "/listFRAEAUV";
}

function registerFRAEAXE(e) {
    window.location = "/registerFRAEAXE";
}

function listFRAEAXE(e) {
    window.location = "/listFRAEAXE";
}

function registerFRAICO(e) {
    window.location = "/registerFRAICO";
}

function listFRAICO(e) {
    window.location = "/listFRAICO";
}

function registerFRAOIT(e) {
    window.location = "/registerFRAOIT";
}

function listFRAOIT(e) {
    window.location = "/listFRAOIT";
}