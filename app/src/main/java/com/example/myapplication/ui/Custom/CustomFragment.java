package com.example.myapplication.ui.Custom;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCustomBinding;

import org.w3c.dom.Text;

public class CustomFragment extends Fragment {

    private FragmentCustomBinding binding;

    Button add;
    AlertDialog dialog;
    LinearLayout layout;
    //Integer counter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CustomViewModel customViewModel = new ViewModelProvider(this).get(CustomViewModel.class);

        binding = FragmentCustomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        add = binding.add;
//
//        buildDialog();
//        Log.i("TAG", "1: ");
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.show();
//            }
//        });

        TextView tview = new TextView(getContext());
        customViewModel.getText().observe(getViewLifecycleOwner(), tview::setText);
        return root;

    }

//    private void buildDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View view = getLayoutInflater().inflate(R.layout.dialog, null);
//
//        final EditText name = view.findViewById(R.id.nameEdit);
//
//
//        builder.setView(view);
//        builder.setTitle("Enter name")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        addCard(name.getText().toString(), 1);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//        dialog = builder.create();
//    }
//
//
//    private TextView addCard(String name, int id) {
//        final View view = getLayoutInflater().inflate(R.layout.card, null);
//
//        TextView nameView = view.findViewById(R.id.text_pomodoro);
//        nameView.setId(id);
//        nameView.setText(name);
//
//        layout.addView(view);
//        return nameView;
//    }
//    private TextView addCardRest(String name, int id) {
//        final View view = getLayoutInflater().inflate(R.layout.cardrest, null);
//
//        TextView nameView = view.findViewById(R.id.text_pomodoro_rest);
//        nameView.setId(id);
//
//        layout.addView(view);
//        return nameView;
//    }


}