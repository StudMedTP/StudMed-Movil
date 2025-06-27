package com.example.studmed.modelos

class ModeloUsuario {
    var nombres: String =  ""
    var tipoUsuario: String =  ""

    constructor() // Requerido por Firebase

    constructor(nombres: String, tipoUsuario: String) {
        this.nombres = nombres
        this.tipoUsuario = tipoUsuario
    }
}
