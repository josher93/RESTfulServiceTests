package com.android.restfulservicetests.customs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.restfulservicetests.R;
import com.android.restfulservicetests.model.Book;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geovanni on 03/12/2015.
 */
public class BookAdapter extends ArrayAdapter
{
    // Atributos
    //private static final String URL_BASE = "http://10.0.3.2/SimpleRESTServiceCRUD/BookService.svc";
    private static final String URL_BASE = "http://192.168.56.0/SimpleRESTServiceCRUD/BookService.svc";
    private static final String URL_JSON = "/Books";


    String url = "http://192.168.56.0:85/BookService.svc/Books/";

    private static final String TAG= "PostAdapter";
    List<Book> items;
    JsonObjectRequest jsArrayRequest;

    private final RequestQueue requestQueue;


    public BookAdapter(Context context)
    {
        super(context,0);
        // Crear nueva cola de peticiones
        requestQueue = Volley.newRequestQueue(context);

        //Gestionar petición del archivo JSON

        // Nueva petición JSONObject
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        items = parseJson(response);
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );

        requestQueue.add(jsArrayRequest);
    }

    @Override
    public int getCount()
    {
        return items != null ? items.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView)
        {
            //Si no existe, entonces inflarlo
            listItemView = layoutInflater.inflate(
                    R.layout.adapter_books,
                    parent,
                    false);
        }

        final TextView bookTitle = (TextView) listItemView.findViewById(R.id.tvName);
        final TextView bookDescription = (TextView) listItemView.findViewById(R.id.tvDescription);
        bookTitle.setText(items.get(position).getTitle());
        bookDescription.setText("ISBN: " + items.get(position).getISBN());

        return listItemView;
    }



    public List<Book> parseJson(JSONArray jsonArray)
    {
        List<Book> books = new ArrayList();

        for(int i=0; i<jsonArray.length(); i++)
        {

            try
            {
                JSONObject objeto = jsonArray.getJSONObject(i);
                Book book = new Book(objeto.getInt("BookId"), objeto.getString("Title"), objeto.getString("ISBN"));
                books.add(book);

            }
            catch (JSONException e)
            {
                Log.e(TAG, "Error de parsing: "+ e.getMessage());
            }
        }

        return books;
    }
}
