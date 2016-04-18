package br.edu.ifpb.Classes;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.edu.ifpb.R;
import br.edu.ifpb.AsyncTask.ProcurarGetAsyncTask;
import br.edu.ifpb.Classes.Pessoa;
import br.edu.ifpb.Classes.ProcurarPessoa;
import br.edu.ifpb.Classes.PessoasCustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class Procurar extends Activity implements TextWatcher,ProcurarPessoa, AdapterView.OnItemClickListener {

    private EditText procurar;

    List<Pessoa> pessoas;
    PessoasCustomAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nome_busca);

        procurar = (EditText) findViewById(R.id.nomeBuscaText);
        procurar.addTextChangedListener(this);

        ListView nomesListView = (ListView) findViewById(R.id.nomeListView);
        pessoas = new ArrayList<Pessoa>();
        adapter = new PessoasCustomAdapter(this, pessoas);

        nomesListView.setAdapter(adapter);

        nomesListView.setOnItemClickListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        Log.i("EditTextListener", "onTextChanged: " + charSequence);
        String nome = charSequence.toString();

        if (nome.length() >= 4) {

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);

            ProcurarGetAsyncTask buscarNomeAsyncTask = new ProcurarGetAsyncTask(this);
            ProcurarAsyncTask.execute(pessoa);
        }else{
            this.pessoas.clear();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void Procurar(List<Pessoa> names) {
        this.pessoas.clear();
        this.pessoas.addAll(pessoas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void errorBuscarNome(String error) {
        pessoas.clear();
        adapter.notifyDataSetChanged();

        Toast.makeText(this, error, Toast.LENGTH_LONG);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
