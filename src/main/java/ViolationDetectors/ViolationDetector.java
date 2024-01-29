package ViolationDetectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Violation detector
 *
 * @author S.L. Dasanayake
 * @author A. Mudalige
 * @author M.L.T. Perera
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code refactoring on 28/1/24
 * Original repo: https://bitbucket.org/lasithd2/seproject_framework_for_secure_coding/src/master/
 * @since 2018
 */

public interface ViolationDetector {

    String rule1Detection();

    String rule2Detection();

    String rule3Detection();

    String rule4Detection();

    String rule5Detection();

    Map<String, ArrayList<Integer>> lce = new HashMap<String, ArrayList<Integer>>();

}
