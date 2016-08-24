package org.nestordevelopments.lapurisima;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.nestordevelopments.lapurisima.CursosAsistenciaFragment.OnListFragmentInteractionListener;
import org.nestordevelopments.lapurisima.Modelo.Alumno;
import org.nestordevelopments.lapurisima.dummy.AlumnoContent;
import org.nestordevelopments.lapurisima.dummy.CursoContent.CursoItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CursoItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CursosAsistenciaRecyclerViewAdapter extends RecyclerView.Adapter<CursosAsistenciaRecyclerViewAdapter.ViewHolder> {

    private final List<CursoItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public CursosAsistenciaRecyclerViewAdapter(List<CursoItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
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

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                System.out.println("TOCADO");
                AlumnoContent.ITEMS.clear();
                AlumnoContent.ITEMS.add(new AlumnoContent.AlumnoItem(new Alumno("Néstor","Martínez Ballester")));
                //ACTUALIZAR EL FRAGMENT QUE CONTIENE LOS ALUMNOS
                Base2f.fragmentAlumnosAsistencias.getActivity().recreate();
                    //mListener.onListFragmentInteraction(holder.mItem);
                //}
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
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
