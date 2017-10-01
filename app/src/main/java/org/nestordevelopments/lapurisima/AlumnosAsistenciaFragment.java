package org.nestordevelopments.lapurisima;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.nestordevelopments.lapurisima.Modelo.GMailSender;
import org.nestordevelopments.lapurisima.dummy.AlumnoContent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AlumnosAsistenciaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    GMailSender sender;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlumnosAsistenciaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AlumnosAsistenciaFragment newInstance(int columnCount) {
        AlumnosAsistenciaFragment fragment = new AlumnosAsistenciaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        sender = new GMailSender("pruebas@lapurisimavalencia.com", "12345678");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alumnos_asistencia, container, false);
        View rv = (View) view.findViewById(R.id.listAlumnosAsistencia);

        // Set the adapter
        if (rv instanceof RecyclerView) {
            Context context = rv.getContext();
            RecyclerView recyclerView = (RecyclerView) rv;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new AlumnosAsistenciaRecyclerViewAdapter(AlumnoContent.ITEMS, mListener));
        }

        Button btEmail = (Button) view.findViewById(R.id.buttonEnviarEmail);
        btEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EnvioMailAsyncTask().execute();
            }
        });
        if(AlumnoContent.ITEMS.isEmpty())
            btEmail.setEnabled(false);
        return view;
    }

    /*
    @Nullable
    @Override
    public View getView() {
        RecyclerView recyclerView = (RecyclerView) super.getView();
        AlumnosAsistenciaRecyclerViewAdapter adapter =(AlumnosAsistenciaRecyclerViewAdapter)recyclerView.getAdapter();
        for(int i=0;i<adapter.getItemCount();i++){
            CheckBox ch =
        }
        return super.getView();
    }
    */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void actualizaFragment(){
        android.support.v4.app.FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(AlumnoContent.AlumnoItem item);
    }

    private class EnvioMailAsyncTask extends AsyncTask <Void,Void,Boolean>{

        String mensaje;

        @Override
        protected void onPreExecute() {
            //TODO código del onPreExecute (Hilo Principal)
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getContext(), "Faltas enviadas correctamente.", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getContext(), "ERROR: No se han podido enviar las faltas.", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // DIÁLOGO QUE MUESTRE QUE SE ESTÁ ENVIANDO EL MENSAJE
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Boolean aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            // Add subject, Body, your mail Id, and receiver mail Id.
            try {
                Date fechaActual = new Date();
                DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");


                //TEMPORAL
                mensaje = "Buenos días,\nSu hijo "+AlumnoContent.ITEMS.get(0).toString()+" " +
                        "no ha asistido hoy "+formatoFecha.format(fechaActual)+" a las "+
                        formatoHora.format(fechaActual)+"."+"\nCualquier duda contacte con el Colegio.\n" +
                        "Le deseamos que pase un buen día.\n\nUn saludo.\n\nColegio La Purísima Franciscanas Valencia.";

                // A PARTIR DE AQUÍ TODO CORRECTO.
                sender.sendMail("Falta de Asistencia.", mensaje, "nestormartinez@lapurisimavalencia.com", "nesmaba@gmail.com");
                return true;
            } catch (Exception e) {
                System.out.println(e);
                Log.e("ERROR MSG", "Error enviando el mensaje");
                e.printStackTrace();
                return false;
            }

            /* VERSION 1 NO ME FUNCIONA
            Mail m = new Mail("erasmusplus@lapurisimavalencia.com", "*erasmus+");

            String[] toArr = {"nestormartinez@lapurisimavalencia.com", "nestormartinez@alu.lapurisimavalencia.com"};
            m.setTo(toArr);
            m.setFrom("erasmusplus@lapurisimavalencia.com");
            m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
            m.setBody("Email body.");

            try {
                // m.addAttachment("/sdcard/filelocation");

                if(m.send()) {
                    return true;
                } else {
                    return false;
                }
            } catch(Exception e) {
                //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                Log.e("MailApp", "Could not send email", e);
                return false;
            }
            */
        }
    }
}
