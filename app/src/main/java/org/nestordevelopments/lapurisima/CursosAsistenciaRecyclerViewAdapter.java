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
import org.nestordevelopments.lapurisima.dummy.AlumnoContent;
import org.nestordevelopments.lapurisima.dummy.CursoContent.CursoItem;

import java.io.IOException;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CursoItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CursosAsistenciaRecyclerViewAdapter extends RecyclerView.Adapter<CursosAsistenciaRecyclerViewAdapter.ViewHolder> {

    private final List<CursoItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private ViewHolder viewHolder;
    private AppCompatActivity activity;
    private AlumnosSQLiteHelper BDalumnos;

    public CursosAsistenciaRecyclerViewAdapter(List<CursoItem> items, OnListFragmentInteractionListener listener, AppCompatActivity activity) {
        mValues = items;
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.curso_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).curso);
        //holder.mContentView.setText(mValues.get(position).content);
        viewHolder=holder;


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("TOCADO");
                // Vacío la lista de alumnos para un curso y cargo los alumnos del nuevo curso pulsado
                AlumnoContent.ITEMS.clear();
                AlumnoContent.ITEMS.add(new AlumnoContent.AlumnoItem(new Alumno("Néstor","Martínez Ballester")));


                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                AlumnosAsistenciaFragment frag = (AlumnosAsistenciaFragment) (activity.getSupportFragmentManager().findFragmentById(R.id.frameLayoutBase2));

                ft.detach(frag).attach(frag).commit();
                BDalumnos.getAlumnos(16);


                //AlumnosAsistenciaFragment frag = .newInstance(AlumnoContent.ITEMS.size());
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
        return mValues.size();
    }

    public CursosAsistenciaRecyclerViewAdapter.ViewHolder getViewHolder() {
        return this.viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        // public final TextView mContentView;
        public CursoItem mItem;

        public ViewHolder(View view) {
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
