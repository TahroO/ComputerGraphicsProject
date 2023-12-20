package projekt;

/*
Taken from the Book Computer Graphics Programming in OpenGL with Java 3rd Edition
 */
public class Utils {

    // GOLD material - ambient, diffuse, specular, and shininess
    public static float[ ] goldAmbient() { return (new float [ ] {0.2473f, 0.1995f, 0.0745f, 1} ); }
    public static float[ ] goldDiffuse() { return (new float [ ] {0.7516f, 0.6065f, 0.2265f, 1} ); }
    public static float[ ] goldSpecular() { return (new float [ ] {0.6283f, 0.5558f, 0.3661f, 1} ); }
    public static float goldShininess() { return 51.2f; }
    // SILVER material - ambient, diffuse, specular, and shininess
    public static float[ ] silverAmbient() { return (new float [ ] {0.1923f, 0.1923f, 0.1923f, 1} ); }
    public static float[ ] silverDiffuse() { return (new float [ ] {0.5075f, 0.5075f, 0.5075f, 1} ); }
    public static float[ ] silverSpecular() { return (new float [ ] {0.5083f, 0.5083f, 0.5083f, 1} ); }
    public static float silverShininess() { return 51.2f; }
    // BRONZE material - ambient, diffuse, specular, and shininess
    public static float[ ] bronzeAmbient() { return (new float [ ] {0.2125f, 0.1275f, 0.0540f, 1} ); }
    public static float[ ] bronzeDiffuse() { return (new float [ ] {0.7140f, 0.4284f, 0.1814f, 1} ); }
    public static float[ ] bronzeSpecular() { return (new float [ ] {0.3935f, 0.2719f, 0.1667f, 1} ); }
    public static float bronzeShininess() { return 25.6f; }
}
