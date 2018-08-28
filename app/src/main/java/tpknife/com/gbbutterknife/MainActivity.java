package tpknife.com.gbbutterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.gb.tanping.bindid.BindView;
import com.gb.tanping.bindid.GbButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_one)
    public TextView tvOne;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GbButterKnife.bind(this);

        tvOne.setText("test one");

        button.setText("test xxx");
    }
}
