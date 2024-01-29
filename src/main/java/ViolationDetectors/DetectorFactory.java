package ViolationDetectors;

/**
 * Violation detector factory
 *
 * @author S.L. Dasanayake
 * @author A. Mudalige
 * @author M.L.T. Perera
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code refactoring on 28/1/24
 * Original repo: https://bitbucket.org/lasithd2/seproject_framework_for_secure_coding/src/master/
 * @since 2018
 */

public class DetectorFactory {

    public ViolationDetector getViolatorType(String ViolatorType) {
        if (ViolatorType == null) {
            return null;
        }
        if (ViolatorType.equalsIgnoreCase("MethodLevelViolationDetector")) {
            return new MethodLevelViolationDetector();

        } else if (ViolatorType.equalsIgnoreCase("ClassLevelViolationDetector")) {
            return new ClassLevelViolationDetector();

        } else if (ViolatorType.equalsIgnoreCase("PackageLevelViolationDetector")) {
            return new PackageLevelViolationDetector();
        }
        return null;
    }
}
