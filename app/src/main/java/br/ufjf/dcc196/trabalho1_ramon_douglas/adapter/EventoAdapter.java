package br.ufjf.dcc196.trabalho1_ramon_douglas.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufjf.dcc196.trabalho1_ramon_douglas.R;
import br.ufjf.dcc196.trabalho1_ramon_douglas.contratos.EventoContract;
import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Evento;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolder> {
    private List<Evento> eventos;
    private OnEventoClickListener listener;
    private OnEventoLongClickListener longClickListener;
    private Cursor cursor;

    public EventoAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public interface OnEventoClickListener {
        void onEventoClick(View view, int position);
    }

    public interface OnEventoLongClickListener {
        void onEventoLongClickListener(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = (TextView) itemView.findViewById(R.id.item_nome);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEventoClick(v, position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            longClickListener.onEventoLongClickListener(v, position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onEventoClick(v, position);
                }
            }

        }

        @Override
        public boolean onLongClick(View v) {
            if (longClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    longClickListener.onEventoLongClickListener(v, position);
                }
            }
            return false;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaEvento = inflater.inflate(R.layout.lista_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(linhaEvento);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        int idxTitulo = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_TITULO);
//        cursor.moveToPosition(i);
//        viewHolder.txtNome.setText(cursor.getString(idxTitulo));
        viewHolder.txtNome.setText(eventos.get(i).getTitulo());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
//        return cursor.getCount();
    }

    public EventoAdapter(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void setOnEventoClickListener(OnEventoClickListener listener) {
        this.listener = listener;
    }

    public void setOnEventoLongClickListener(OnEventoLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}
