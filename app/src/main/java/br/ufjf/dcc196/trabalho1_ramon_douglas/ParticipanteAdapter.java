package br.ufjf.dcc196.trabalho1_ramon_douglas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ViewHolder> {
    private List<String> participantes;
    private OnParticipanteClickListener listener;

    public interface OnParticipanteClickListener{
        void onParticipanteClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = (TextView) itemView.findViewById(R.id.participante_nome);
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
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaParticipante = inflater.inflate(R.layout.participante_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(linhaParticipante);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtNome.setText(participantes.get(i));
    }

    @Override
    public int getItemCount() {
        return participantes.size();
    }

    public ParticipanteAdapter(List<String> participantes) {
        this.participantes = participantes;
    }

    public void setOnParticipanteClickListener(OnParticipanteClickListener listener) {
        this.listener = listener;
    }
}
