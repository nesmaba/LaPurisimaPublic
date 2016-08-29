package org.nestordevelopments.lapurisima;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.nestordevelopments.lapurisima.dummy.AlumnoContent.AlumnoItem;
import org.nestordevelopments.lapurisima.AlumnosAsistenciaFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link AlumnoItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AlumnosAsistenciaRecyclerViewAdapter extends RecyclerView.Adapter<AlumnosAsistenciaRecyclerViewAdapter.ViewHolder> {

    private final List<AlumnoItem> mValues;
    private final AlumnosAsistenciaFragment.OnListFragmentInteractionListener mListener;

    public AlumnosAsistenciaRecyclerViewAdapter(List<AlumnoItem> items, AlumnosAsistenciaFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alumno_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setId(position);
        holder.mIdView.setText(mValues.get(position).alumno.toString());
        //holder.mContentView.setText(mValues.get(position).content);

        if(mValues.get(position).alumno.isSelected())
            holder.chAlumno.setChecked(true);
        else
            holder.chAlumno.setChecked(false);

        holder.mIdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.chAlumno.isChecked()) {
                    holder.chAlumno.setChecked(false);
                    mValues.get(position).alumno.setSelected(false);
                }else {
                    holder.chAlumno.setChecked(true);
                    mValues.get(position).alumno.setSelected(true);
                }
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
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
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final CheckBox chAlumno;

        // public final TextView mContentView;
        public AlumnoItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.textview_alumno);
            chAlumno = (CheckBox) view.findViewById(R.id.checkbox_alumno);
            // mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '"; //+ mContentView + " '";
        }
    }
}
