package br.com.mapets.api.dto.input;

import br.com.mapets.domain.model.Cidade;
import br.com.mapets.domain.model.Pessoa;
import br.com.mapets.domain.model.enuns.TipoPessoaEnum;
import br.com.mapets.domain.repository.CidadeRepository;

public class PessoaInputDto {

    private Integer id;

    private  String cpf;

    private String nome;

    private String sexo;

    private  String telefone;

    private String dtNascimento;

    private String endereco;

    private String tipoPessoa;

    private Integer cidade;

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

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Integer getCidade() {
        return cidade;
    }

    public void setCidade(Integer cidade) {
        this.cidade = cidade;
    }

    public Pessoa build(CidadeRepository cidadeRepository){
        Pessoa pessoa = new Pessoa();

        pessoa.setCpf(this.cpf);
        pessoa.setNome(this.nome);
        pessoa.setSexo(this.sexo);
        pessoa.setTelefone(this.telefone);
        pessoa.setDtNascimento(this.getDtNascimento());
        pessoa.setEndereco(this.endereco);
        pessoa.setTipoPessoaEnum(TipoPessoaEnum.valueOf(this.tipoPessoa.toUpperCase()));
        pessoa.setCidade(cidadeRepository.findById(this.cidade).get());
        System.out.println(pessoa.getCidade().getNome());

        return pessoa;
    }

    public Pessoa buildAlterar(Pessoa pessoa, CidadeRepository cidadeRepository){
        pessoa.setNome(this.nome.length() > 0 ? this.nome : pessoa.getNome());
        pessoa.setCpf(this.cpf.length() > 0 ? this.cpf : pessoa.getCpf());
        pessoa.setSexo(this.sexo.length() > 0 ? this.sexo : pessoa.getSexo());
        pessoa.setTelefone(this.telefone.length() > 0 ? this.telefone : pessoa.getTelefone());
        pessoa.setDtNascimento(this.dtNascimento.length() > 0 ? this.dtNascimento : pessoa.getDtNascimento());
        pessoa.setEndereco(this.endereco.length() > 0 ? this.endereco : pessoa.getEndereco());

        Cidade c = cidadeRepository.findById(this.cidade).get();
        pessoa.setCidade(c != null ? c : pessoa.getCidade());

        return pessoa;
    }


    public PessoaInputDto(){}

}
