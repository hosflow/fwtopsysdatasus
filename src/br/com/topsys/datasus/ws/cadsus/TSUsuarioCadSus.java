/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.topsys.datasus.ws.cadsus;

import br.com.topsys.datasus.ws.cadsus.TSEnderecoCadSus;
import java.io.Serializable;

/**
 *
 * @author André
 */
public class TSUsuarioCadSus implements Serializable{

    private String nome;
    private String nomeSocial;
    private String dataNascimento;
    private String nomeMae;
    private String nomePai;    
    private String cpf;
    private String cns;
    private String sexo;
    private String obito;
    private String raca;
    private String etnia;
    private String nacionalidade;
    private String cidade;
    private String rg;
    private String CTPS;
    private String CNH;
    private String tituloEleitor;
    private String pis;
    private String passaporte;
    private String telefone;
    private String email;
    private String idExterno;
    private Boolean flagVivo;
    
    
    
    
    
    private TSEnderecoCadSus enderecoCadSus;

    public TSUsuarioCadSus() {

    }

    public TSUsuarioCadSus(String nome, String dataNascimento, String nomeMae, String cpf, String cns) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.cpf = cpf;
        this.cns = cns;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public TSEnderecoCadSus getEnderecoCadSus() {
        return enderecoCadSus;
    }

    public void setEnderecoCadSus(TSEnderecoCadSus enderecoCadSus) {
        this.enderecoCadSus = enderecoCadSus;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getObito() {
        return obito;
    }

    public void setObito(String obito) {
        this.obito = obito;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCTPS() {
        return CTPS;
    }

    public void setCTPS(String CTPS) {
        this.CTPS = CTPS;
    }

    public String getCNH() {
        return CNH;
    }

    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public Boolean getFlagVivo() {
        return flagVivo;
    }

    public void setFlagVivo(Boolean flagVivo) {
        this.flagVivo = flagVivo;
    }
    
    

}
