package org.nestordevelopments.lapurisima;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.nestordevelopments.lapurisima.CursosAsistenciaFragment.OnListFragmentInteractionListener;
import org.nestordevelopments.lapurisima.Modelo.Alumno;
import org.nestordevelopments.lapurisima.Modelo.AlumnosSQLiteHelper;
import org.nestordevelopments.lapurisima.Modelo.Curso;
import org.nestordevelopments.lapurisima.dummy.AlumnoContentBORRAR;
import org.nestordevelopments.lapurisima.dummy.CursoContentBORRAR.CursoItem;

import java.io.IOException;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CursoItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class CursosAsistenciaRecyclerViewAdapter extends RecyclerView.Adapter<CursosAsistenciaRecyclerViewAdapter.CursoViewHolder> {

    private final List<Curso> aCursos;
    private final OnListFragmentInteractionListener mListener;
    private CursosAsistenciaRecyclerViewAdapter.CursoViewHolder viewHolder;
    private AppCompatActivity activity;
    private AlumnosSQLiteHelper BDalumnos;

    public CursosAsistenciaRecyclerViewAdapter(List<Curso> cursos, OnListFragmentInteractionListener listener, AppCompatActivity activity) {
        aCursos = cursos;
        mListener = listener;
        this.activity = activity;
        this.BDalumnos = new AlumnosSQLiteHelper(activity, "faltas.sqlite", null, 1);

        try {
            this.BDalumnos.createDataBase();
            this.BDalumnos.openDataBase();
        } catch (IOException e) {
            System.out.println("ERROR abriendo la BD. Error: "+e);
            e.printStackTrace();
        }
    }

    @Override
    public CursosAsistenciaRecyclerViewAdapter.CursoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Método abstracto de RecyclerView.Adapter
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.curso_item, parent, false);
        return new CursosAsistenciaRecyclerViewAdapter.CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CursosAsistenciaRecyclerViewAdapter.CursoViewHolder holder, int position) {
        // Método abstracto de RecyclerView.Adapter
        holder.cItem = aCursos.get(position);
        holder.mIdView.setText(aCursos.get(position).getCurso());
        //holder.mContentView.setText(mValues.get(position).content);
        viewHolder=holder;

        final int curso= position;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            //mView es el view que aloja cada curso.
            @Override
            public void onClick(View v) {
                System.out.println("TOCADO "+curso);

                // Vacío la lista de alumnos para un curso y cargo los alumnos del nuevo curso pulsado

                AlumnoContentBORRAR.ITEMS.clear();
                //AlumnoContentBORRAR.ITEMS.add(new AlumnoContentBORRAR.AlumnoItem(new Alumno("Néstor","Martínez Ballester")));

                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                AlumnosAsistenciaFragment frag = (AlumnosAsistenciaFragment) (activity.getSupportFragmentManager().findFragmentById(R.id.frameLayoutBase2));

                ft.detach(frag).attach(frag).commit();

                for (Alumno alumno:BDalumnos.getAlumnos(curso)) {
                    AlumnoContentBORRAR.ITEMS.add(new AlumnoContentBORRAR.AlumnoItem(alumno));
                }


                //AlumnosAsistenciaFragment frag = .newInstance(AlumnoContentBORRAR.ITEMS.size());
                //ft.detach(frag).attach(frag).commit();
                //if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.

                //ACTUALIZAR EL FRAGMENT QUE CONTIENE LOS ALUMNOS
                //Base2f.fragmentAlumnosAsistencias.getActivity().recreate();
                //CursosAsistenciaRecyclerViewAdapter.this.notifyDataSetChanged();

                    //mListener.onListFragmentInteraction(holder.mItem);
                //}
            }
        });

    }

    @Override
    public int getItemCount() {
        // Método abstracto de RecyclerView.Adapter
        return aCursos.size();
    }

    public CursosAsistenciaRecyclerViewAdapter.CursoViewHolder getViewHolder() {
        return this.viewHolder;
    }

    public class CursoViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        // public final TextView mContentView;
        public Curso cItem;

        public CursoViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            // mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '"; //+ mContentView + " '";
        }
    }
}
