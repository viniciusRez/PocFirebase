package com.example.firebaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SquadEngenharia(val nome:String, val lider:String,val funcao:String ){
    constructor(nome: String,lider: String):this(nome,lider,"Criar produtos digitais que as pessoas amam usar.")
    init {
        require(nome.trim().length > 0){
            "Informe o nome do Squad"
        }
        require(lider.trim().length > 0){
            "Informe o nome do Lider"
        }
    }
}


class POO_Tester : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poo_tester)
    }
}