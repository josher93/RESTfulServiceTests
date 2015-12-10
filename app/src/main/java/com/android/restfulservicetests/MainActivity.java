package com.android.restfulservicetests;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONException;

import com.android.restfulservicetests.customs.BookAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class MainActivity extends ActionBarActivity
{
    // Progress Dialog Object
    ProgressDialog prgDialog;
    TextView tvData;

    // Atributos
    ListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView)findViewById(R.id.DisplayJson);



    }

    public void getData(View view)
    {
        invokeWS();
    }

//    public void invokeWS()
//    {
//        // Show Progress Dialog
//        //prgDialog.show();
//        // Make RESTful webservice call using AsyncHttpClient object
//        AsyncHttpClient client = new AsyncHttpClient();
//        //client.get("http://192.168.2.2:9999/useraccount/login/dologin", params, new AsyncHttpResponseHandler()
//        client.get("http://10.0.2.2:80/SimpleRESTServiceCRUD/BookService.svc/Books", new AsyncHttpResponseHandler()
//        {
//            // When the response returned by REST has Http response code '200'
//            @Override
//            public void onSuccess(String response)
//            {
//                // Hide Progress Dialog
//                //prgDialog.hide();
//                try
//                {
//                    // JSON Object
//                    JSONObject obj = new JSONObject(response);
//                    // When the JSON response has status boolean value assigned with true
//                    if (obj.getBoolean("status"))
//                    {
//                        tvData.setText(response);
//                        Toast.makeText(getApplicationContext(), "Teoricamente te has conectado", Toast.LENGTH_LONG).show();
//                        // Navigate to Home screen
//
//                    }
//                    // Else display error message
//                    else
//                    {
//
//                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
//                    }
//                }
//                catch (JSONException e)
//                {
//                    // TODO Auto-generated catch block
//                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
//                    e.printStackTrace();
//
//                }
//            }
//
//            // When the response returned by REST has Http response code other than '200'
//            @Override
//            public void onFailure(int statusCode, Throwable error,
//                                  String content)
//            {
//                // Hide Progress Dialog
//                //prgDialog.hide();
//                // When Http response code is '404'
//                if (statusCode == 404)
//                {
//                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
//                }
//                // When Http response code is '500'
//                else if (statusCode == 500)
//                {
//                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
//                }
//                // When Http response code other than 404, 500
//                else
//                {
//                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }

    private void invokeWS(){
        AsyncHttpClient client=new AsyncHttpClient();
        client.get("http://192.168.56.1/SimpleRESTServiceCRUD/BookService.svc/Books" ,new AsyncHttpResponseHandler(){
                    @Override public void onSuccess(    String response){

                            tvData.setText(response);
//                        try {
//                                radioList=new ListDeezerDataReader<Radio>(Radio.class).readList(response);
//                                radioList=radioList.subList(28,radioList.size() - 1);
//                                searchComplete(radioList);
//                            }
//                            catch (IllegalStateException exception) {
//                                handleError(exception);
//                        }

                    }
                    @Override public void onFailure(    Throwable throwable,    String str){
                        super.onFailure(throwable,str);

                    }
                }
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
