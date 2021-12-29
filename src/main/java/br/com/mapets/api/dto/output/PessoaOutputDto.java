package br.com.mapets.api.dto.output;

import br.com.mapets.domain.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaOutputDto {

    private Integer id;

    private  String cpf;

    private String nome;

    private String sexo;

    private  String telefone;

    private String dtNascimento;

    private String endereco;

    private List<PetOutputDto> pets;

    private String tipoPessoa;

    private String cidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDtNascimento() {
        return this.dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<PetOutputDto> getPets() {
        return pets;
    }

    public void setPets(List<PetOutputDto> pets) {
        this.pets = pets;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public static List<PessoaOutputDto> buildPessoaDto(List<Pessoa> pessoas){
        List<PessoaOutputDto> pessoasDtoa = new ArrayList();
        for (Pessoa p: pessoas) {
            PessoaOutputDto pessoaInputDtoa = new PessoaOutputDto(p);
            pessoaInputDtoa.setPets(PetOutputDto.buildPetDto(p.getPet()));
            pessoasDtoa.add(pessoaInputDtoa);
        }

        return pessoasDtoa;
    }


    public PessoaOutputDto(){
        this.pets = new ArrayList();
    }

    public PessoaOutputDto(Pessoa pessoa) {
        this.pets = new ArrayList();
        this.id = pessoa.getId();
        this.cpf = pessoa.getCpf();
        this.nome = pessoa.getNome();
        this.sexo = pessoa.getSexo();
        this.telefone = pessoa.getTelefone();
        this.dtNascimento = pessoa.getDtNascimento();
        this.endereco = pessoa.getEndereco();
        this.tipoPessoa = pessoa.getTipoPessoaEnum().toString();
        this.cidade = pessoa.getCidade().getNome();
        this.pets = PetOutputDto.buildPetDto(pessoa.getPet());
    }
}
