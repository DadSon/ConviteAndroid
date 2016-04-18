package ed.com.evento.meuappevento.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

import ed.com.evento.meuappevento.util.BuscarPessoaCallBack;
import ed.com.evento.meuappevento.util.HttpService;
import ed.com.evento.meuappevento.entidade.Pessoa;
import ed.com.evento.meuappevento.util.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lenildo on 14/04/2016.
 */
public class BuscarNomePostAsyncTask extends AsyncTask<Pessoa, Void, Response> {

    private BuscarPessoaCallBack buscarNomeCallBack;

    public BuscarNomePostAsyncTask(BuscarPessoaCallBack buscarNomeCallBack) {

        this.buscarNomeCallBack = buscarNomeCallBack;
    }

    @Override
    protected Response doInBackground(Pessoa... pessoas) {

        Response response = null;

        Pessoa pessoa = pessoas[0];
        Log.i("EditTextListener", "doInBackground (JSON): " + pessoa);

        try {

            Gson gson = new Gson();
            response = HttpService.sendJSONPostResquest("get-byname", gson.toJson(pessoa));

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {

        int codeHttp = response.getStatusCodeHttp();

        Log.i("EditTextListener", "Código HTTP: " + codeHttp
                + " Conteúdo: " + response.getContentValue());

        if (codeHttp != HttpURLConnection.HTTP_OK) {

            buscarNomeCallBack.errorBuscarNome(response.getContentValue());

        } else {

            Gson gson = new Gson();
            List<Pessoa> pessoas = gson.fromJson(response.getContentValue(),
                    new TypeToken<ArrayList<Pessoa>>(){}.getType());

            buscarNomeCallBack.backBuscarNome(pessoas);
        }
    }
}
