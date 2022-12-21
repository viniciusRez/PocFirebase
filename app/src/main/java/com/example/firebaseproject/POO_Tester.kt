package com.example.firebaseproject

import CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

 interface Project{
      fun addSquad(infoSquad: Squad): Boolean
      fun deleteSquad(idSquad: Int)
}

class Smiles(val squads: MutableList < Squad > = mutableListOf()): Project{

    override fun addSquad(infoSquad: Squad): Boolean {
        val exists= squads.filter {squad -> squad.nome == infoSquad.nome /*|| squad.lider == infoSquad.lider*/}
        return if (exists.isEmpty()) {
            squads.add(infoSquad)
            (true)
        } else {
            (false)
        }
    }
    override fun deleteSquad(idSquad: Int){
        println(squads.removeAt(idSquad))
    }
}
class Squad(val nome: String, var lider: String, val funcao: String )//Construtor Primario
{
    constructor(nome: String, lider: String): this(nome, lider, "Criar produtos digitais que as pessoas amam usar.")//Construtor Secundario
 }



class POO_Tester : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle ?) {
        var newSquad: Squad
        val smiles = Smiles()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poo_tester)

        val btnCadastrar: Button = findViewById(R.id.btnSave)
        btnCadastrar.setOnClickListener {

            val txtSquad: EditText = findViewById(R.id.txtSquad)
            val txtLider: EditText = findViewById(R.id.txtLider)
            val txtFunction: EditText = findViewById(R.id.txtFuncao)

            val squadName: String = txtSquad.text.toString()
            val squadLider: String = txtLider.text.toString()
            val squadFunction: String = txtFunction.text.toString()

             if(squadName.isEmpty() || squadLider.isEmpty()){
                Toast.makeText(this,"Complete os Campos",Toast.LENGTH_SHORT).show()
            }else{
                 newSquad = if (squadFunction.isEmpty()) {
                Squad(squadName, squadLider)
                 } else {
                Squad(squadName, squadLider, squadFunction) }

                 if(smiles.addSquad(newSquad)){
                         Toast.makeText(this,"Squad cadastrado'",Toast.LENGTH_SHORT).show()
                 }else{
                     Toast.makeText(this,"Squad já cadastrado'",Toast.LENGTH_SHORT).show()
                 }
                 txtSquad.text.clear()
                 txtLider.text.clear()
                 txtFunction.text.clear()
            }

        }

        val btnDeletar: Button = findViewById(R.id.btnDelete)
        btnDeletar.setOnClickListener {

            val txtSquad: EditText = findViewById(R.id.txtSquad)
            val squadName: String = txtSquad.text.toString()

            smiles.squads.forEachIndexed {
                    index, it ->

               if(it.nome == squadName){
                   smiles.deleteSquad(index)
                   Toast.makeText(this,"Squad Deletada",Toast.LENGTH_SHORT).show()

               }
            }
            txtSquad.text.clear()

        }


        val recyclerViewSquads: RecyclerView = findViewById(R.id.recycleviewCadastrados)
        recyclerViewSquads.adapter = CustomAdapter(
            dataSet = smiles.squads
        )
    }
}


