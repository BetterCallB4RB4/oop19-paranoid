package paranoid.model.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import paranoid.common.Pair;
import java.util.ArrayList;
import java.util.List;
import paranoid.main.ParanoidApp;

public class ScoreManager {

    public static void saveScore(final Score score) throws IOException {
        try (
        BufferedWriter w = new BufferedWriter(new FileWriter(ParanoidApp.SCORE))
        ) {
            final List<Pair<String, Integer>> scoreList = score.getScore();
            for (final Pair<String, Integer> ele : scoreList) {
                w.write(ele.getX());
                w.newLine();
                w.write(ele.getY().toString());
                w.newLine();
            }
        }
    }

    public static Score loadScore() throws IOException {
        try (
                BufferedReader r = new BufferedReader(new FileReader(ParanoidApp.SCORE))
        ) {
            final List<Pair<String, Integer>> scoreList = new ArrayList<>();
            String name = null;
            int score;
            while ((name = r.readLine()) != null) {
                score = Integer.parseInt(r.readLine());
                scoreList.add(new Pair<>(name, score));
            }
            return new Score.Builder().fromExScore(scoreList).build();
        }
    }
}
