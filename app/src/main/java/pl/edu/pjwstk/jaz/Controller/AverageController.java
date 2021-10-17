package pl.edu.pjwstk.jaz.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.AverageResult;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class AverageController {
    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers) {
        //numbers...

        if (numbers == null) {
            return new AverageResult("Please put parameters");
        } else {
            String[] table = numbers.split(",");
            float[] intTable = new float[table.length];
            float sum = 0;
            for (int i = 0; i < table.length; i++) {
                intTable[i] = Integer.parseInt(table[i]);
                sum = sum + intTable[i];
            }
            BigDecimal avgRound = new BigDecimal(sum / table.length);
            avgRound = avgRound.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
            return new AverageResult("Average equals: " + avgRound);
        }
    }
}
