package hu.ait.android.tictactoe;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import hu.ait.android.tictactoe.view.TicTacToeView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutContent;
    private TicTacToeView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

        gameView =
                (TicTacToeView) findViewById(R.id.gameView);

        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameView.restartGame();
            }
        });

        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(
                R.id.shimmer_view_container);
        shimmerFrameLayout.startShimmerAnimation();
    }


    public void showTooManyCirclesMessage() {
        showSnackbarMessage(getString(R.string.text_too_many_circles));
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(layoutContent, message, Snackbar.LENGTH_LONG).
                setAction(R.string.action_clear, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gameView.restartGame();
                    }
                }).show();
    }

    public void showSimpleSnackbarMessage(String message) {
        Snackbar.make(layoutContent, message, Snackbar.LENGTH_LONG).show();
    }

}
