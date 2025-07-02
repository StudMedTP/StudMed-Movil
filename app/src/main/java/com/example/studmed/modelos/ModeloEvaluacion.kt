package com.example.studmed.modelos

class ModeloEvaluacion {

    var id : String = ""
    var titulo : String = ""
    var descripcion : String = ""
    var fecha : String = ""
    var seccion : String = ""
    var codigo : String = ""

    constructor()

    constructor(id: String,
                titulo: String,
                descripcion: String,
                fecha: String,
                seccion: String,
                codigo: String ) {
        this.id = id
        this.titulo = titulo
        this.descripcion = descripcion
        this.fecha = fecha
        this.seccion = seccion
        this.codigo = codigo

    }

}