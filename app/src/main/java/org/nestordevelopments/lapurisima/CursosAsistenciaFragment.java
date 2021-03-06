package org.nestordevelopments.lapurisima;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.nestordevelopments.lapurisima.Modelo.Curso;
import org.nestordevelopments.lapurisima.dummy.CursoContentBORRAR;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CursosAsistenciaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CursosAsistenciaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CursosAsistenciaFragment newInstance(int columnCount) {
        CursosAsistenciaFragment fragment = new CursosAsistenciaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos_asistencia, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setHasFixedSize(true);

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new CursosAsistenciaRecyclerViewAdapter(Curso.ITEMS, mListener, (AppCompatActivity) this.getActivity()));

            /*
            if(recyclerView!=null){
                ((CursosAsistenciaRecyclerViewAdapter)(recyclerView.getAdapter())).getViewHolder().mView.setOnClickListener(new View.OnClickListener() { //VIEWHOLDER ES NULL!!!!!
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            */
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        actualizarCursosAlumnosVistas();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void actualizarCursosAlumnosVistas(){
        //LEER DE LA BD LOS CURSOS Y CARGARLOS EN EL ARRAY DEL ADAPTER DE CURSOS

        //LEER DE LA BD LOS ALUMNOS Y CARGARLOS EN EL ARRAY DEL ADAPTER DE ALUMNOS
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
        void onListFragmentInteraction(CursoContentBORRAR.CursoItem item);
    }
}
