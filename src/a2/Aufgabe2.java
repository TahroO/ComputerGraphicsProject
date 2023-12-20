package a2;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import javax.annotation.processing.Generated;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	private int vaoId;
	private int attributePostition = 0;
	private int numberOfTriangles;

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {
		// folgende Zeile laed automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		float [] dreiecksKoordinaten = new float[]{ -0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
				0.5f, 0.5f, 0f, 0.5f, 0.5f, 0f};

		// gibt an wie viele punkte vorhandne sind / weil x+y = 2 / Anzahl der Ecken welche gezeichnet werden
		// um zu definieren welche coords zusammengehören

		numberOfTriangles = dreiecksKoordinaten.length/2;


		float [] dreiecksFarben = new float[]{ 	0.95f, 	0.8f, 	0.9f,
				0.95f, 	0.95f, 	0.7f,
				0.7f, 	0.9f, 	0.9f,
				0.95f, 	0.8f, 	0.9f,
				0.95f, 	0.95f, 	0.7f,
				0.7f, 	0.9f, 	0.9f};

		//VAO
		vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		//VBO
		generateVBO(dreiecksKoordinaten, 2);
		generateVBO(dreiecksFarben, 3);

	}

	@Override
	public void update() {
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenflaeche leeren


		glBindVertexArray(vaoId);
		//zeichnet Dreiecke, beginnt bei Ecke 0
		//und verarbeitet gegebene Anzahl Ecken
		glDrawArrays(GL_TRIANGLES, 0, numberOfTriangles);
	}

	private void generateVBO(float[] valueArray, int sizeOfValueGroup) {
		glBindBuffer(GL_ARRAY_BUFFER, glGenBuffers());
		glBufferData(GL_ARRAY_BUFFER, valueArray, GL_STATIC_DRAW);
		glVertexAttribPointer(attributePostition, sizeOfValueGroup, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(attributePostition);

		attributePostition++;
	}
}