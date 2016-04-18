package br.edu.ifpb.asyncTask;

import android.util.Log;
import android.os.AsyncTask;

import br.edu.ifpb.Classes.ProcurarPessoa;
import br.edu.ifpb.Classes.HttpService;
import br.edu.ifpb.Classes.Pessoa;
import br.edu.ifpb.Classes.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.activity.Procurar;

public class ProcurarGetAsyncTask extends AsyncTask<Pessoa, Void, Response>{

    private ProcurarPessoa ProcurarPessoa;

    public ProcurarGetAsyncTask (Procurar ProcurarPessoa) {

        this.buscarNomeCallBack = buscarNomeCallBack;
    }

    @Override
    protected Response doInBackground(Pessoa... pessoas) {

        Response response = null;

        Pessoa pessoa = pessoas[0];

        Gson gson = new Gson();
        Log.i("EditTextListener", "doInBackground (JSON): " + pessoa);

        try {

            response = HttpService.sendJSONPostResquest("pesquisar/nome/", pessoa.getNome());

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {


        if(response == null)
            Log.i("É NULO","NULO");

        int codeHttp = response.getStatusCodeHttp();

        Log.i("EditTextListener", "Código HTTP: " + codeHttp
                + " Conteúdo: " + response.getContentValue());

        if (codeHttp != HttpURLConnection.HTTP_OK) {

            ProcurarPessoa.errorProcurar(response.getContentValue());

        } else {

            Gson gson = new Gson();
            List<Pessoa> pessoas = gson.fromJson(response.getContentValue(),
                    new TypeToken<ArrayList<Pessoa>>() {
                    }.getType());

            ProcurarPessoa.Procurar(pessoas);
        }
    }
}
