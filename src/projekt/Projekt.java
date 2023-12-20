package projekt;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import lenz.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

public class Projekt extends AbstractOpenGLBase {

	private int vaoId;
	private int vaoId2;
	private int vaoId3;
	private int attributePosition = 0;
	private int numberOfPoints;

	private float winkel = 0.01f;
	private Matrix4 matrixObject1 = new Matrix4();
	private Matrix4 matrixObject2 = new Matrix4();
	private Matrix4 matrixObject3 = new Matrix4();
	private Matrix4 projectionMatrix = new Matrix4(700,700,90.0f,1f,1000f);

	private ShaderProgram shaderProgram;
	private ShaderProgram shaderProgram2;

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		glUseProgram(shaderProgram.getId());

		shaderProgram2 = new ShaderProgram("texture");
		glUseProgram(shaderProgram2.getId());

		Texture texture = new Texture("blue1.png");
		glBindTexture(GL_TEXTURE_2D, texture.getId());

		Texture texture2 = new Texture("star2.png");
		glBindTexture(GL_TEXTURE_2D, texture2.getId());




		// OBJECT COORDS
		float [] cubeCoords = new float[]{
				// Front face
				-0.5f, -0.5f, 0.5f, 1,   // bottom left
				0.5f, -0.5f, 0.5f, 1,    // bottom right
				0.5f, 0.5f, 0.5f, 1,     // top right

				-0.5f, -0.5f, 0.5f, 1,   // bottom left
				0.5f, 0.5f, 0.5f, 1,     // top right
				-0.5f, 0.5f, 0.5f, 1,    // top left

				// Back face
				-0.5f, -0.5f, -0.5f, 1,  // bottom left
				0.5f, -0.5f, -0.5f, 1,   // bottom right
				0.5f, 0.5f, -0.5f, 1,    // top right

				-0.5f, -0.5f, -0.5f, 1,  // bottom left
				0.5f, 0.5f, -0.5f, 1,    // top right
				-0.5f, 0.5f, -0.5f, 1,   // top left

				// Top face
				-0.5f, 0.5f, 0.5f, 1,    // front left
				0.5f, 0.5f, 0.5f, 1,     // front right
				0.5f, 0.5f, -0.5f, 1,    // back right

				-0.5f, 0.5f, 0.5f, 1,    // front left
				0.5f, 0.5f, -0.5f, 1,    // back right
				-0.5f, 0.5f, -0.5f, 1,   // back left

				// Bottom face
				-0.5f, -0.5f, 0.5f, 1,   // front left
				0.5f, -0.5f, 0.5f, 1,    // front right
				0.5f, -0.5f, -0.5f, 1,   // back right

				-0.5f, -0.5f, 0.5f, 1,   // front left
				0.5f, -0.5f, -0.5f, 1,   // back right
				-0.5f, -0.5f, -0.5f, 1,  // back left

				// Left face
				-0.5f, -0.5f, 0.5f, 1,   // front bottom
				-0.5f, 0.5f, 0.5f, 1,    // front top
				-0.5f, 0.5f, -0.5f, 1,   // back top

				-0.5f, -0.5f, 0.5f, 1,   // front bottom
				-0.5f, 0.5f, -0.5f, 1,   // back top
				-0.5f, -0.5f, -0.5f, 1,  // back bottom

				// Right face
				0.5f, -0.5f, 0.5f, 1,    // front bottom
				0.5f, 0.5f, 0.5f, 1,     // front top
				0.5f, 0.5f, -0.5f, 1,    // back top

				0.5f, -0.5f, 0.5f, 1,    // front bottom
				0.5f, 0.5f, -0.5f, 1,    // back top
				0.5f, -0.5f, -0.5f, 1     // back bottom
		};


		float [] sphere = generateSphereCoordinates(30,30,1.0f);


		// COLOR
		float [] cubeColor = new float[]{
				// Front1
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.5f,
				// Front2
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.5f,
				0.0f, 0.5f, 0.0f,
				// Back1
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				// Back2
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				// Top1
				0.5f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.5f,
				0.0f, 0.5f, 0.0f,
				// Top2
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				// Bottom1
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				// Bottom2
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				// Left1
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				// Left2
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				// Right1
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				// Right2
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
		};

		float [] dreiecksFarben2 = new float[]{
				0.95f, 	0.8f, 	0.9f,
				0.95f, 	0.95f, 	0.7f,
				0.7f, 	0.9f, 	0.9f,
		};

		// normal vectors for phong shading
		float[] nVector = new float[] {
				//front
				0, 0, 1,
				0, 0, 1,
				0, 0, 1,
				0, 0, 1,
				0, 0, 1,
				0, 0, 1,
				//back
				0, 0, -1,
				0, 0, -1,
				0, 0, -1,
				0, 0, -1,
				0, 0, -1,
				0, 0, -1,
				//top
				0, 1, 0,
				0, 1, 0,
				0, 1, 0,
				0, 1, 0,
				0, 1, 0,
				0, 1, 0,
				//bottom
				0, -1, 0,
				0, -1, 0,
				0, -1, 0,
				0, -1, 0,
				0, -1, 0,
				0, -1, 0,
				//left
				-1, 0, 0,
				-1, 0, 0,
				-1, 0, 0,
				-1, 0, 0,
				-1, 0, 0,
				-1, 0, 0,
				//right
				1, 0, 0,
				1, 0, 0,
				1, 0, 0,
				1, 0, 0,
				1, 0, 0,
				1, 0, 0,
		};

		float[] lightColor = new float[] {
				// Front1
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Front2
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Back1
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Back2
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Top1
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Top2
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Bottom1
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Bottom2
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Left1
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Left2
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Right1
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
				// Right2
				1f, 1f, 1f,
				1f, 1f, 1f,
				1f, 1f, 1f,
		};

		float[] uvCoords = new float[] {
				// Front1
				0f, 0f,
				1f, 0f,
				1f, 1f,
				// Front2
				1f, 0f,
				0f, 1f,
				1f, 1f,
				// Back1
				0f, 0f,
				1f, 0f,
				1f, 1f,
				// Back2
				1f, 0f,
				0f, 1f,
				1f, 1f,
				// Top1
				0f, 0f,
				1f, 0f,
				1f, 1f,
				// Top2
				1f, 0f,
				0f, 1f,
				1f, 1f,
				// Bottom1
				0f, 0f,
				1f, 0f,
				1f, 1f,
				// Bottom2
				1f, 0f,
				0f, 1f,
				1f, 1f,
				// Left1
				0f, 0f,
				1f, 0f,
				1f, 1f,
				// Left2
				1f, 0f,
				0f, 1f,
				1f, 1f,
				// Right1
				0f, 0f,
				1f, 0f,
				1f, 1f,
				// Right2
				1f, 0f,
				0f, 1f,
				1f, 1f,
		};

		// determines how many points are 1 pack of coordinates for 1 vertex
		// this is for a cube
		numberOfPoints = cubeCoords.length/4;

		//VAO
		vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		//VBO
		generateVBO(cubeCoords, 4, 0);
		generateVBO(cubeColor, 3, 1);



		generateVBO(nVector,3,2);
		generateVBO(lightColor, 3, 3);
		generateVBO(uvCoords,2,4);

		vaoId2 = glGenVertexArrays();
		glBindVertexArray(vaoId2);

		generateVBO(cubeCoords, 4,0);
		generateVBO(cubeColor, 3,1);

		vaoId3 = glGenVertexArrays();
		glBindVertexArray(vaoId3);

		generateVBO(cubeCoords, 4,0);
		generateVBO(lightColor, 3,1);
		generateVBO(nVector,3,2);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren // orndet objekte korrekt über und untereinander an
//		glEnable(GL_CULL_FACE); // backface culling aktivieren // uhrzeigersinn entfernt orientierung
		// kann weggelassen werden

	}

	@Override
	public void update() {
		// create new Matrix every turn to make sure values are correct
		matrixObject1 = new Matrix4();
		matrixObject2 = new Matrix4();
		matrixObject3 = new Matrix4();
		// animation of objects
		matrixObject1.rotateZ(winkel);
		matrixObject1.rotateY(winkel);
		matrixObject1.translate(0f, 0f, -5f);
		// hier animation steuern
		winkel += 0.01f;
		matrixObject2.rotateY(winkel * 5);
		matrixObject2.translate(0f,0,-3f);
		matrixObject2.multiply(matrixObject1);

		matrixObject3.scale(0.2f);
		matrixObject3.translate(0f, -1.5f, 0f);
		matrixObject3.rotateZ(winkel * -5);
		matrixObject3.multiply(matrixObject1);

	}

	@Override
	protected void render() {
		// SetBack of Display / Clear Buffers
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// draw first VAO Object
		glBindVertexArray(vaoId);
		// Transformation Matrix
		int loc = glGetUniformLocation(shaderProgram.getId(),"transformation");
		glUniformMatrix4fv(loc, false, matrixObject1.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, numberOfPoints);

		// draw second VAO Object
//		glBindVertexArray(vaoId2);
		glUniformMatrix4fv(loc, false, matrixObject2.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, numberOfPoints);

//		glBindVertexArray(vaoId3);
		glUniformMatrix4fv(loc, false, matrixObject3.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, numberOfPoints);



		// Projection Matrix
		int loc2 = glGetUniformLocation(shaderProgram.getId(), "projection");
		glUniformMatrix4fv(loc2, false, projectionMatrix.getValuesAsArray());


	}

	private void generateVBO(float[] valueArray, int sizeOfValueGroup, int attributePosition) {
		glBindBuffer(GL_ARRAY_BUFFER, glGenBuffers());
		glBufferData(GL_ARRAY_BUFFER, valueArray, GL_STATIC_DRAW);
		glVertexAttribPointer(attributePosition, sizeOfValueGroup, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(attributePosition);

	}

	private float[] generateSphereCoordinates(int numLatitude, int numLongitude, float radius) {
		List<Float> coordinatesList = new ArrayList<>();

		for (int lat = 0; lat <= numLatitude; lat++) {
			double theta = lat * Math.PI / numLatitude;
			double sinTheta = Math.sin(theta);
			double cosTheta = Math.cos(theta);

			for (int lon = 0; lon <= numLongitude; lon++) {
				double phi = lon * 2 * Math.PI / numLongitude;
				double sinPhi = Math.sin(phi);
				double cosPhi = Math.cos(phi);

				float x = (float) (radius * sinTheta * cosPhi);
				float y = (float) (radius * sinTheta * sinPhi);
				float z = (float) (radius * cosTheta);

				coordinatesList.add(x);
				coordinatesList.add(y);
				coordinatesList.add(z);
				coordinatesList.add(1f);
			}
		}

		// Convert the list to a float array
		float[] coordinatesArray = new float[coordinatesList.size()];
		for (int i = 0; i < coordinatesList.size(); i++) {
			coordinatesArray[i] = coordinatesList.get(i);
		}
		System.out.println(coordinatesArray.length);
		return coordinatesArray;
	}
}
