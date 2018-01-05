package pl.pharmaway.prezentacjatrilac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import pl.pharmaway.prezentacjatrilac.view.ChooseAgentDialog;
import pl.pharmaway.prezentacjatrilac.view.ChooseLekarzDialog;

public class FormActivity extends AppCompatActivity
implements ChooseAgentDialog.AgentDialogListener, ChooseLekarzDialog.LekarzDialogListener {
    TextView agent;
    TextView lekarz;
    View next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        agent = findViewById(R.id.agent);
        lekarz = findViewById(R.id.lekarz);
        next = findViewById(R.id.next);

        if(savedInstanceState==null) {
            lekarz.setEnabled(false);
        } else {
            agent.setText(savedInstanceState.getString("agent"));
            lekarz.setText(savedInstanceState.getString("lekarz"));
            lekarz.setEnabled(savedInstanceState.getBoolean("lekarz_enabled"));
        }

        updateNext();

        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseAgentDialog.create().show(getSupportFragmentManager(), "tag");
            }
        });

        lekarz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseLekarzDialog.create(getAgentText()).show(getSupportFragmentManager(), "tag");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormActivity.this, Page1.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAgentSelected(String agent) {
        this.agent.setText(agent);
        this.lekarz.setEnabled(true);
        this.lekarz.setText("");
        updateNext();
    }

    @Override
    public void onLekarzSelected(String lekarz) {
        this.lekarz.setText(lekarz);
        updateNext();
    }

    public String getAgentText() {
        return agent.getText().toString();
    }

    void updateNext() {
        next.setEnabled(!TextUtils.isEmpty(this.lekarz.getText()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("agent", agent.getText().toString());
        outState.putString("lekarz", lekarz.getText().toString());
        outState.putBoolean("lekarz_enabled", lekarz.isEnabled());
    }
}
