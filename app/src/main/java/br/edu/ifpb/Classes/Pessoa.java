package br.edu.ifpb.Classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pessoa implements Serializable{

    public Pessoa (String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    @SerializedName("id")
    private int id;

    @SerializedName("fullName")
    private String nome;

    public Pessoa() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
