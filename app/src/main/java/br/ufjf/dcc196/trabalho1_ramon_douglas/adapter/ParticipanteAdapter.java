package br.ufjf.dcc196.trabalho1_ramon_douglas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufjf.dcc196.trabalho1_ramon_douglas.R;
import br.ufjf.dcc196.trabalho1_ramon_douglas.modelo.Participante;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ViewHolder> {
    private List<Participante> participantes;
    private OnParticipanteClickListener listener;
    private OnParticipanteLongClickListener longClickListener;

    public interface OnParticipanteClickListener {
        void onParticipanteClick(View view, int position);
    }

    public interface OnParticipanteLongClickListener {
        void onParticipanteLongClickListener(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = (TextView) itemView.findViewById(R.id.item_nome);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onParticipanteClick(v, position);
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
                            longClickListener.onParticipanteLongClickListener(v, position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    listener.onParticipanteClick(v, position);
                }
            }

        }

        @Override
        public boolean onLongClick(View v) {
            if(longClickListener != null) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    longClickListener.onParticipanteLongClickListener(v, position);
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
        View linhaParticipante = inflater.inflate(R.layout.lista_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(linhaParticipante);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtNome.setText(participantes.get(i).getNome());
    }

    @Override
    public int getItemCount() {
        return participantes.size();
    }

    public ParticipanteAdapter(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public void setOnParticipanteClickListener(OnParticipanteClickListener listener) {
        this.listener = listener;
    }

    public void setOnParticipanteLongClickListener(OnParticipanteLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}
