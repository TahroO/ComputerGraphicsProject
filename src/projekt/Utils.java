package projekt;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static float[] readVertexCoordinates(String fileName) {
        List<Float> vertexList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("v ")) {
                    String[] parts = line.split("\\s+");
                    for (int i = 1; i < 4; i++) {
                        float vertexCoord = Float.parseFloat(parts[i]);
                        vertexList.add(vertexCoord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        float[] vertexArray = new float[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            vertexArray[i] = vertexList.get(i);
        }
        return vertexArray;
    }

    public static float[] readTextureCoordinates(String fileName) {
        List<Float> textureList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("vt ")) {
                    String[] parts = line.split("\\s+");
                    for (int i = 1; i < 3; i++) {
                        float textureCoord = Float.parseFloat(parts[i]);
                        textureList.add(textureCoord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        float[] textureArray = new float[textureList.size()];
        for (int i = 0; i < textureList.size(); i++) {
            textureArray[i] = textureList.get(i);
        }
        return textureArray;
    }

    public static float[] readNormalCoordinates(String fileName) {
        List<Float> normalList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("vn ")) {
                    String[] parts = line.split("\\s+");
                    for (int i = 1; i < 4; i++) {
                        float normalCoord = Float.parseFloat(parts[i]);
                        normalList.add(normalCoord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        float[] normalArray = new float[normalList.size()];
        for (int i = 0; i < normalList.size(); i++) {
            normalArray[i] = normalList.get(i);
        }
        return normalArray;
    }
}
