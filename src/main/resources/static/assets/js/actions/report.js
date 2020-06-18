function events() {
    onWizarTabChange();
}

function onWizarTabChange() {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href");// activated tab
        if(target==="#tab2"){
            addElementsSecondTab();
        }
    });
}

function addElementsSecondTab() {
    let content=document.getElementById("contenedor");
    content.innerHTML='';
    let contenedor=$("#contenedor");
    let numDias=document.getElementById("numDias").value;
    let tipoMuestra=document.getElementById("tipoMuestra").value;
    let numRepeticiones=document.getElementById("numRepeticiones").value;
    let numMuestra=document.getElementById("numMuestra").value;
    let numMuestras=document.getElementById("numMuestras").value;
    let descripcionMuestra=document.getElementById("descripcionMuestra").value;
    let numAnalistas=document.getElementById("numAnalistas").value;
    let tipoMaterial=document.getElementById("tipoMaterial").value;
    let provedor=document.getElementById("provedor").value;
    let valorReferencia=document.getElementById("valorReferencia").value;
    //numAnalisitas
    //numDias
    //numMuestras
    //numRepeticiones
    let cardAnalista;
    let cardDia;
    let contAnalista=0;
    let analistaForm;
    let nombreCardAnalista;
    let cardAnalistaJQuery;
    for(let i=0; i<numAnalistas; i++){
        cardAnalista="<div class=\"card\" id=\"analista"+contAnalista+"\">"+"</div>";
        contenedor.append(cardAnalista);
        nombreCardAnalista="analista"+contAnalista;
        cardAnalistaJQuery=$("#"+nombreCardAnalista);
        analistaForm=
            "<div class=\"row justify-content-center\">" +
            "   <div class=\"col-md-10\">" +
            "       <div class=\"form-group\">" +
            "           <label class=\"control-label\">Nombre de analista "+(i+1)+"</label>" +
            "           <input class=\"form-control\" type=\"text\" name=\"nombreAnalista\" required id=\"nombreAnalista"+(i+1)+"\""+  "/>" +
            "        </div>"+
            "   </div>" +
            "</div>";
        cardAnalistaJQuery.append(analistaForm);
        for(let j=0; j<numDias; j++){
            cardDia=
                "<div class=\"row justify-content-center\">" +
                "     <div class=\"col-md-10 style-1\" style=\"overflow-y: scroll; margin-bottom: 10px; height: 50vh\">" +
                "       <div class=\"card\">" +
                "           <div class=\"card-body\">" +
                "               <div class=\"form-group\">" +
            "                       <label class=\"control-label\">Día "+(j+1)+"</label>";
            for(let k=0; k<numMuestras; k++){
                cardDia+=
                    " <div class=\"row justify-content-center\">" +
                    "   <label class=\"control-label\">Medición "+(k+1)+"</label>" +
                    "</div>";
                cardDia+="<div class=\"row justify-content-center\">";
                for(let z=0; z<numRepeticiones; z++){
                    //numAnalista_numDia_numMuestra_numRepeticion
                    //ijkz
                    cardDia+=
                        "<div class=\"col-md-6\">" +
                        "    <div class=\"form-group\">" +
                        "       <label class=\"control-label\">Repetición "+(z+1)+"</label>" +
                        "       <input class=\"form-control\" type=\"number\" name=\""+""+(i+1)+""+(j+1)+""+""+(k+1)+""+(z+1)+ "\"" +
                        "           id=\""+""+(i+1)+""+(j+1)+""+(k+1)+""+(z+1)+"\" required />" +
                        "    </div>" +
                        "</div>";
                }
                cardDia+="</div>";
            }
            cardDia+="</div>";
            cardDia+="</div>";
            cardDia+="</div>";
            cardDia+="</div>";
            cardDia+="</div>";
            cardAnalistaJQuery.append(cardDia);
        }
        contAnalista++;
    }
}

function generarReporte() {
    let id;
    let obj={};
    let numRepeticiones=document.getElementById("numRepeticiones").value;
    let numDias=document.getElementById("numDias").value;
    let numMediciones=document.getElementById("numMuestras").value;

    let numMuestra=document.getElementById("numMuestra").value;
    let valorReferencia=document.getElementById("valorReferencia").value;

    obj["numRepeticiones"]=numRepeticiones;
    obj["numDias"]=numDias;
    obj["numMediciones"]=numMediciones;

    obj["numMuestra"]=numMuestra;
    obj["valorReferencia"]=valorReferencia;


    $("#contenedor :input").each(function(e){
        id = this.id;
        obj[id]=this.value;
    });

    let newObj=JSON.stringify(obj);
    subirDatos(newObj);
}

function subirDatos(obj) {
    $.ajax({
        cache: false,
        type: 'POST',
        url: '/reporteController',
        contentType: 'application/json; charset=utf-8',
        processData: false,
        data: obj,
        //xhrFields para convertir el arreglo de bytes en objeto blob
        xhrFields: {
            responseType: 'blob'
        },
        success: function (response, status, xhr) {
            var filename = "";
            var disposition = xhr.getResponseHeader('Content-Disposition');
            if (disposition) {
                var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                var matches = filenameRegex.exec(disposition);
                if (matches !== null && matches[1]) filename = matches[1].replace(/['"]/g, '');
            }
            var linkelem = document.createElement('a');
            try {
                var blob = new Blob([response], { type: 'application/octet-stream' });

                if (typeof window.navigator.msSaveBlob !== 'undefined') {
                    //   Método especifico para Internet Explorer
                    window.navigator.msSaveBlob(blob, filename);
                } else {
                    var URL = window.URL || window.webkitURL;
                    var downloadUrl = URL.createObjectURL(blob);

                    if (filename) {
                        // Se debe crear un elemento HREF temporal
                        var a = document.createElement("a");
                        // En navegador safari es necesario abrir el documento en otra pestaña
                        if (typeof a.download === 'undefined') {
                            window.location = downloadUrl;
                        } else {
                            a.href = downloadUrl;
                            a.download = filename;
                            document.body.appendChild(a);
                            a.target = "_blank";
                            a.click();
                        }
                    } else {
                        window.location = downloadUrl;
                    }
                }

            } catch (ex) {
                console.log(ex);
            }
        }
    });
}