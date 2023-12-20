package projekt;

public class Test {

    static float [] otherMatrix = new float[]{
            5.0f, 	0.0f, 	0.0f,	2.0f,
            0.0f, 	6.0f,	0.0f, 	0.0f,
            0.0f,	0.0f, 	7.0f, 	0.0f,
            0.0f, 	0.0f, 	0.0f,	1.0f
    };

    public static void main(String[] args) {
        Matrix4 matrix4 = new Matrix4();
        Matrix4 matrix41 = new Matrix4();
        matrix41.values = otherMatrix;
//        matrix4.multiply(matrix41);
        matrix4.translate(3,4,5);
        matrix4.printValues(matrix4);
    }
}
