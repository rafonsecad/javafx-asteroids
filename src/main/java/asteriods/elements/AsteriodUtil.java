/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author rafael
 */
public class AsteriodUtil {
    public static Asteriod getRandomAsteriod(){
        int r = ThreadLocalRandom.current().nextInt(1, 3);
        switch(r){
            case 1:
                return new Ceres();
            case 2:
                return new Vesta();
        }
            return new Ceres();
    }
}
