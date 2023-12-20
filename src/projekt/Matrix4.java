package projekt;

//Alle Operationen aendern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurueck
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {

	float[] values;

	public Matrix4() {
		// TODO mit der Identitaetsmatrix initialisieren
		float [] idMatrix = new float[]{
				1.0f, 	0.0f, 	0.0f,	0.0f,
				0.0f, 	1.0f,	0.0f, 	0.0f,
				0.0f,	0.0f, 	1.0f, 	0.0f,
				0.0f, 	0.0f, 	0.0f,	1.0f
		};
		this.values = idMatrix;
	}

	public Matrix4(Matrix4 copy) {
		// TODO neues Objekt mit den Werten von "copy" initialisieren
		Matrix4 m = new Matrix4();
		m.values = copy.getValuesAsArray();
	}

	public Matrix4(int width, int height, float fieldOfView, float near, float far) {
		// TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf.
		//  weitere Parameter hinzufuegen
		float aspectRatio = (float) width / height;
		float FOV = (float) Math.toRadians(fieldOfView);

		float [] pMatrix = new float[]{
				(2*near)/2, 			0.0f, 					0.0f,							0.0f,
				0.0f, 					(2*near)/2,				0.0f, 							0.0f,
				0.0f,					0.0f, 					(-far - near) / (far - near), 	-1.0f,
				0.0f, 					0.0f, 					(-2*(near*far))/(far-near),		0.0f
		};

		this.values = pMatrix;

	}

	public Matrix4 multiply(Matrix4 other) {
		// Tizian helped here to fix the result position allocation
		// TODO hier Matrizenmultiplikation "this = other * this" einfuegen
		// wegen reihenfolge ist wichtig matrizenmultiplikation beachten
		float[] result = new float[16];

		for (int k = 0; k < 16; k++) {
			int col = k / 4;
			int row = k % 4;
			for (int i = 0; i <4; i++) {
				result[row + col * 4] += other.values[i * 4 + row] * this.values[i + col * 4];
			}
		}
		this.values = result;
		return this;
	}

	public Matrix4 translate(float x, float y, float z) {
		// TODO Verschiebung um x,y,z zu this hinzufuegen
		float [] transMatrix = new float[]{
				1.0f, 	0.0f, 	0.0f,	0.0f,
				0.0f, 	1.0f,	0.0f, 	0.0f,
				0.0f,	0.0f, 	1.0f, 	0.0f,
				x, 	y, 	z,	1.0f
		};
		Matrix4 matrixTranslate = new Matrix4();
		matrixTranslate.values = transMatrix;

		return this.multiply(matrixTranslate);
	}

	public Matrix4 scale(float uniformFactor) {
		// TODO gleichmaessige Skalierung um Faktor "uniformFactor" zu this hinzufuegen
		float [] scaleMatrix = new float[]{
				uniformFactor, 	0.0f, 	0.0f,	0.0f,
				0.0f, 	uniformFactor,	0.0f, 	0.0f,
				0.0f,	0.0f, 	uniformFactor, 	0.0f,
				0.0f, 	0.0f, 	0.0f,	1.0f
		};
		Matrix4 matrixScale = new Matrix4();
		matrixScale.values = scaleMatrix;

		return this.multiply(matrixScale);
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		// TODO ungleichfoermige Skalierung zu this hinzufuegen
		float [] idMatrix = new float[]{
				sx, 	0.0f, 	0.0f,	0.0f,
				0.0f, 	sy,		0.0f, 	0.0f,
				0.0f,	0.0f, 	sz, 	0.0f,
				0.0f, 	0.0f, 	0.0f,	1.0f
		};
		Matrix4 matrixScale = new Matrix4();
		matrixScale.values = idMatrix;

		return this.multiply(matrixScale);
	}

	public Matrix4 rotateX(float angle) {
		// TODO Rotation um X-Achse zu this hinzufuegen
		double cosAlpha = Math.cos(angle);
		double sinAlpha = Math.sin(angle);
		float [] rotMatrix = new float[]{
				1.0f, 	0.0f, 	0.0f,	0.0f,
				0.0f, 	(float)cosAlpha,	(float)-sinAlpha, 	0.0f,
				0.0f,	(float) sinAlpha, 	(float) cosAlpha, 	0.0f,
				0.0f, 	0.0f, 	0.0f,	1.0f
		};
		Matrix4 matrixRotate = new Matrix4();
		matrixRotate.values = rotMatrix;

		return this.multiply(matrixRotate);
	}

	public Matrix4 rotateY(float angle) {
		// TODO Rotation um Y-Achse zu this hinzufuegen
		double cosAlpha = Math.cos(angle);
		double sinAlpha = Math.sin(angle);
		float [] rotMatrix = new float[]{
				(float)cosAlpha, 	0.0f, 	(float)-sinAlpha,	0.0f,
				0.0f, 	1.0f,	0.0f, 	0.0f,
				(float)sinAlpha,	0.0f, 	(float)cosAlpha, 	0.0f,
				0.0f, 	0.0f, 	0.0f,	1.0f
		};
		Matrix4 matrixRotate = new Matrix4();
		matrixRotate.values = rotMatrix;

		return this.multiply(matrixRotate);
	}

	public Matrix4 rotateZ(float angle) {
		// TODO Rotation um Z-Achse zu this hinzufuegen
		double cosAlpha = Math.cos(angle);
		double sinAlpha = Math.sin(angle);
		float [] idMatrix = new float[]{
				(float)cosAlpha, 	(float)-sinAlpha, 	0.0f,	0.0f,
				(float)sinAlpha, 	(float)cosAlpha,	0.0f, 	0.0f,
				0.0f,	0.0f, 	1.0f, 	0.0f,
				0.0f, 	0.0f, 	0.0f,	1.0f
		};
		Matrix4 tmpMatrix = new Matrix4();
		tmpMatrix.values = idMatrix;
		return this.multiply(tmpMatrix);
	}

	public float[] getValuesAsArray() {
		// TODO hier Werte in einem Float-Array mit 16 Elementen (spaltenweise gefuellt) herausgeben
        return this.values;
	}

	public void printValues(Matrix4 matrix) {
		for (int i = 0; i < 16; i++) {
			System.out.println(matrix.values[i]);
		}
	}


}
