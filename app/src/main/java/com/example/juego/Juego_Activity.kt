package com.example.juego

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.random.Random

class Juego_Activity : AppCompatActivity() {
    lateinit var musicaFondo:MediaPlayer
    lateinit var respuestaUsuario:EditText
    lateinit var btnRespuesta:Button
    lateinit var sonidoRespuestaCorrecta:MediaPlayer
    lateinit var sonidoRespuestaIncorrecta:MediaPlayer
    var numeroGenerado = 0
    var numeroUsuario = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        initUI()
        musicaFondo = MediaPlayer.create(this, R.raw.fondo)
        sonidoRespuestaCorrecta = MediaPlayer.create(this,R.raw.fondo)
        sonidoRespuestaIncorrecta = MediaPlayer.create(this,R.raw.perder)
        reproduceMusica()
        generaNumero()

        btnRespuesta.setOnClickListener {
            val respuesta = respuestaUsuario.text.toString()
            if (respuesta.equals("")){
                Toast.makeText(this,"Ingresa un valor",Toast.LENGTH_LONG).show()
                sonidoIncorrecto()
            }else{
              numeroUsuario = respuesta.toInt()
              if(numeroGenerado == numeroUsuario){
                  sonidoCorrecto()
              }else{
                  sonidoIncorrecto()
                  Toast.makeText(this,"El valor era $numeroGenerado",Toast.LENGTH_LONG).show()

              }
              }
            }
        }
    fun reproduceMusica(){
        musicaFondo.isLooping = true
        musicaFondo.start() // no need to call prepare(); create() does that for you
    }

    fun sonidoCorrecto(){
        sonidoRespuestaCorrecta.start()
    }

    fun sonidoIncorrecto(){
        sonidoRespuestaIncorrecta.start()
    }

    fun initUI(){
        btnRespuesta = findViewById(R.id.btnComenzar)
        respuestaUsuario = findViewById(R.id.etEntradaUsuario)
    }
    fun generaNumero(){
        numeroGenerado = Random.nextInt(1,10)
    }
}