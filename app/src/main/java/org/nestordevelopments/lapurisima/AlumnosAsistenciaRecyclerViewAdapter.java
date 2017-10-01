package org.nestordevelopments.lapurisima;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.nestordevelopments.lapurisima.Modelo.Alumno;
import org.nestordevelopments.lapurisima.dummy.AlumnoContentBORRAR.AlumnoItem;
import org.nestordevelopments.lapurisima.AlumnosAsistenciaFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link AlumnoItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */

public class AlumnosAsistenciaRecyclerViewAdapter extends RecyclerView.Adapter<AlumnosAsistenciaRecyclerViewAdapter.ViewHolderAlumno> {

    private final List<Alumno> alumnos;
    private final AlumnosAsistenciaFragment.OnListFragmentInteractionListener mListener;

    public AlumnosAsistenciaRecyclerViewAdapter(List<Alumno> items, AlumnosAsistenciaFragment.OnListFragmentInteractionListener listener) {
        alumnos = items;
        mListener = listener;
    }

    @Override
    public ViewHolderAlumno onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alumno_item, parent, false);
        return new ViewHolderAlumno(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderAlumno holder, final int position) {
        holder.aItem = alumnos.get(position);
        holder.tvAlumno.setId(position);
        holder.tvAlumno.setText(alumnos.get(position).toString());
        //holder.mContentView.setText(mValues.get(position).content);

        if(alumnos.get(position).isSelected())
            holder.chAlumno.setChecked(true);
        else
            holder.chAlumno.setChecked(false);

        holder.tvAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.chAlumno.isChecked()) {
                    holder.chAlumno.setChecked(false);
                    alumnos.get(position).setSelected(false);
                }else {
                    holder.chAlumno.setChecked(true);
                    alumnos.get(position).setSelected(true);
                }
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.aItem);
                }
            }
        });

        /* NO ENTIENDO PORQUE NO VA, SE RAYA, HACE LO QUE QUIERE DESACTIVA Y ACTIVA COMO QUIERE
        holder.chAlumno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setChecked(isChecked);
                if(isChecked) {
                    System.out.println("pulsado");
                    mValues.get(position).alumno.setSelected(true);
                }else {
                    mValues.get(position).alumno.setSelected(false);
                }
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public class ViewHolderAlumno extends RecyclerView.ViewHolder {
        public final View aView;
        public final TextView tvAlumno;
        public final CheckBox chAlumno;

        // public final TextView mContentView;
        public Alumno aItem;

        public ViewHolderAlumno(View view) {
            super(view);
            aView = view;
            tvAlumno = (TextView) view.findViewById(R.id.textview_alumno);
            chAlumno = (CheckBox) view.findViewById(R.id.checkbox_alumno);
            // mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '"; //+ mContentView + " '";
        }
    }
}
